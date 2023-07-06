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

import java.nio.file.Path;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.lsp4j.DiagnosticSeverity;
import org.lflang.DefaultMessageReporter;
import org.lflang.FileConfig;
import org.lflang.generator.Range;

/**
 * Error reporter that asserts the absence of errors.
 * 
 * @author Alexander Schulz-Rosengarten
 */
public class TestErrorReporter extends DefaultMessageReporter {

    // Central error detection because compilation will run in separate thread
    private static String ERROR = null;
  
    public static void consumeError() {
        if (ERROR != null) {
            String consumedError = ERROR;
            // Prepare for next test run
            ERROR = null;
            assertEquals("Error occured during code generation!", "", consumedError);
        }
    }
    
    public TestErrorReporter(FileConfig fc) {
    }
    public TestErrorReporter() {
    }
  
    /**
     * {@inheritDoc}
     */
    @Override
    protected void report(Path path, Range range, DiagnosticSeverity severity, String message) {
        super.report(path, range, severity, message);
        if (severity == DiagnosticSeverity.Error) {
            ERROR = message;
        }
    }
  
    /**
     * {@inheritDoc}
     */
    @Override
    protected void reportOnNode(EObject node, DiagnosticSeverity severity, String message) {
        super.reportOnNode(node, severity, message);
        if (severity == DiagnosticSeverity.Error) {
            ERROR = message;
        }
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    protected void reportWithoutPosition(DiagnosticSeverity severity, String message) {
        super.reportWithoutPosition(severity, message);
        if (severity == DiagnosticSeverity.Error) {
          ERROR = message;
      }
    } 

}
