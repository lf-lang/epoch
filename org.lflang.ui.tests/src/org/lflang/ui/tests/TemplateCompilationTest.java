/*************
* Copyright (c) 2022, Kiel University.
*
* Redistribution and use in source and binary forms, with or without modification,
* are permitted provided that the following conditions are met:
*
* 1. Redistributions of source code must retain the above copyright notice,
*    this list of conditions and the following disclaimer.
*
* 2. Redistributions in binary form must reproduce the above copyright notice,
*    this list of conditions and the following disclaimer in the documentation
*    and/or other materials provided with the distribution.
*
* THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND 
* ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED 
* WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE 
* DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR
* ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES 
* (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; 
* LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON 
* ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT 
* (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS 
* SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
***************/
package org.lflang.ui.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assume.assumeTrue;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.common.util.URI;
import org.eclipse.xtext.builder.EclipseResourceFileSystemAccess2;
import org.eclipse.xtext.generator.GeneratorContext;
import org.eclipse.xtext.generator.GeneratorDelegate;
import org.eclipse.xtext.generator.IOutputConfigurationProvider;
import org.eclipse.xtext.generator.OutputConfiguration;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.resource.XtextResourceSet;
import org.eclipse.xtext.util.CancelIndicator;
import org.eclipse.xtext.validation.CheckMode;
import org.eclipse.xtext.validation.IResourceValidator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.lflang.generator.MainContext;
import org.lflang.ui.wizard.LFProjectTemplateProvider;
import org.osgi.framework.FrameworkUtil;

import com.google.inject.Inject;
import com.google.inject.Injector;

/**
 * Tests if all LF file in templates (org.lflang.ui) can be compiled.
 * 
 * @author Alexander Schulz-Rosengarten
 */
@RunWith(Parameterized.class)
public class TemplateCompilationTest {
    
    static String[] TEMPLATES = {
        "/org/lflang/ui/wizard/templates/c/src/HelloWorld.lf",
        "/org/lflang/ui/wizard/templates/c/src/FederatedHelloWorld.lf",
        "/org/lflang/ui/wizard/templates/c/src/Parallel.lf",
        "/org/lflang/ui/wizard/templates/c/src/Pipeline.lf",
        "/org/lflang/ui/wizard/templates/c/src/Main.lf",
        "/org/lflang/ui/wizard/templates/cpp/src/HelloWorld.lf",
        "/org/lflang/ui/wizard/templates/py/src/HelloWorld.lf",
        "/org/lflang/ui/wizard/templates/ts/src/HelloWorld.lf",
        "/org/lflang/ui/wizard/templates/ts/src/WebServer.lf"
    };
    
    @Parameters(name = "{0}")
    public static Object[] data() throws IOException {
        return TEMPLATES;
    }
    
    private final String template;
    private final Injector injector;
    
    @Inject private GeneratorDelegate generator;
    @Inject private IResourceValidator validator;

    public TemplateCompilationTest(String template) {
        super();
        this.template = template;
        this.injector = new LFUiInjectorProvider().getInjector();
        this.injector.injectMembers(this);
        
        // Configure LF code generator to use special error reporter
        MainContext.EPOCH_ERROR_REPORTER_CONSTRUCTOR = TestErrorReporter::new;
    }
    
    @Test
    public void compileTemplate() throws CoreException, IOException {
        // Compiler restrictions
        System.out.println(Platform.getOS() + " " + Platform.OS_WIN32 + " " + (Platform.getOS() != Platform.OS_WIN32) +" "+ template.toLowerCase() + " " + (!template.toLowerCase().contains("federated")));
        assumeTrue("Federated LF programs with a C target are currently not supported on Windows", 
                Platform.getOS() != Platform.OS_WIN32 || !template.toLowerCase().contains("federated"));
        
        // Locate file
        var bundle = FrameworkUtil.getBundle(LFProjectTemplateProvider.class);
        assertNotNull(bundle);
        var context = bundle.getBundleContext();
        assertNotNull(context);

        var srcURL = bundle.getResource(template);
        assertNotNull(srcURL);
        
        // Names
        var projectName = template.replaceAll("/", "-");
        var filePath = new File(template);
        var fileName = filePath.getName();
        
        // Create test file
        var root = ResourcesPlugin.getWorkspace().getRoot();
        var project = root.getProject(projectName);
        if (!project.exists()) {
            project.create(null);
        }
        assertTrue(project.exists());
        if(!project.isOpen()) {
            project.open(null);
        }
        
        var iFile = project.getFile(fileName);
        var srcStream = srcURL.openStream();
        iFile.create(srcStream, true, null);
        srcStream.close();
        assertTrue(iFile.exists());
        
        // Create eResource
        var uri = URI.createPlatformResourceURI(iFile.getFullPath().toString(), true);
        var resourceSet = injector.getInstance(XtextResourceSet.class);
        var resource = (XtextResource) resourceSet.getResource(uri, true);

        // Validate
        var issues = validator.validate(resource, CheckMode.ALL, CancelIndicator.NullImpl);
        for (var i : issues) {
            assertEquals("Validation error!", "", i.getMessage());
        }
        
        // Compile
        var fsa = injector.getInstance(EclipseResourceFileSystemAccess2.class);
        fsa.setContext(project);
        var outputConfigurationMap = new HashMap<String, OutputConfiguration>();
        for (var config : injector.getInstance(IOutputConfigurationProvider.class).getOutputConfigurations()) {
            outputConfigurationMap.put(config.getName(), config);
        }
        fsa.setOutputConfigurations(outputConfigurationMap);
        var generatorContext = new GeneratorContext();
        generatorContext.setCancelIndicator(CancelIndicator.NullImpl);

        // Start code generation and compilation
        generator.generate(resource, fsa, generatorContext);
    }

}
