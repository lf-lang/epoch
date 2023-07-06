/*************
 * Copyright (c) 2019, The University of California at Berkeley. Copyright (c)
 * 2019, TU Dresden
 * 
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 * 
 * 1. Redistributions of source code must retain the above copyright notice,
 * this list of conditions and the following disclaimer.
 * 
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 * this list of conditions and the following disclaimer in the documentation
 * and/or other materials provided with the distribution.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 ***************/

package org.lflang.generator;

import java.nio.file.Path;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.lsp4j.DiagnosticSeverity;
import org.eclipse.xtext.diagnostics.Severity;
import org.eclipse.xtext.validation.EObjectDiagnosticImpl;

import org.lflang.FileConfig;
import org.lflang.MessageReporter;
import org.lflang.MessageReporterBase;
import org.lflang.util.FileUtil;

/**
 * An message reporter that prints messages to the command line output and also
 * sets markers in the Epoch IDE.
 *
 * This class should only be used in EPOCH Mode.
 */
public class EclipseMessageReporter extends MessageReporterBase implements MessageReporter {
    
    private FileConfig fileConfig = null;

    public EclipseMessageReporter(FileConfig fc) {
        fileConfig = fc;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    protected void reportWithoutPosition(DiagnosticSeverity severity, String message) {
        report(null, null, severity, message);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    protected void reportOnNode(EObject node, DiagnosticSeverity severity, String message) {
        if (node != null) {
             var diagnostic = new EObjectDiagnosticImpl(convertToXtext(severity), null, message, node, null, -1, null);
             var range = new Range(
                     Position.fromOneBased(diagnostic.getLine(), diagnostic.getColumn()),
                     Position.fromOneBased(diagnostic.getLineEnd(), diagnostic.getColumnEnd()));
             var file = FileUtil.toPath(diagnostic.getUriToProblem());
             report(file, range, severity, message);
        }
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    protected void report(Path path, Range range, DiagnosticSeverity severity, String message) {
        @SuppressWarnings("resource")
        var console = severity == DiagnosticSeverity.Error ? System.err : System.out;
        if (range == null || severity == DiagnosticSeverity.Information) {
            console.println(String.format("%s: %s", severity.name(), message));
        } else {
            console.println(String.format("%s (%s): %s", severity.name(), range.toString(), message));
        }
        
        // Create marker in editor
        if (severity != DiagnosticSeverity.Information) {
            // Determine the iResource to report on
            IResource iResource = path != null ? FileUtil.getIResource(path) : null;
            // if we couldn't find an iResource (for whatever reason), then use the
            // iResource of the main file
            if (iResource == null) {
                iResource = fileConfig.iResource;
            }
    
            // Create a marker in the IDE for the error.
            // See: https://help.eclipse.org/2020-03/index.jsp?topic=%2Forg.eclipse.platform.doc.isv%2Fguide%2FresAdv_markers.html
            try {
                IMarker marker = iResource.createMarker(IMarker.PROBLEM);
    
                // Mark as LF compilation marker to be able to remove marker at next compile run
                marker.setAttribute(this.getClass().getName(), true);
    
                marker.setAttribute(IMarker.MESSAGE, message);
                marker.setAttribute(IMarker.SEVERITY, convertToMarker(severity));
                marker.setAttribute(IMarker.PRIORITY, IMarker.PRIORITY_HIGH);
                marker.setAttribute(IMarker.LINE_NUMBER, range != null? range.getStartInclusive().getOneBasedLine() : 1);
            } catch (CoreException e) {
                // Ignore, but print a warning
                System.err.println("WARNING: Setting markers in the IDE failed:\n" + e.toString());
            }
        }
    }

    /**
     * Clear markers in the IDE.
     * This has the side effect of setting the
     * iResource variable to point to the IFile for the Lingua Franca program.
     */
    @Override
    public void clearHistory() {
        super.clearHistory();
        try {
            IResource resource = FileUtil.getIResource(fileConfig.srcFile);
            IMarker[] markers = resource.findMarkers(null, true, IResource.DEPTH_INFINITE);
            
            for (IMarker marker : markers) {
                // Only remove those markers created by the LF compilation
                if (marker.getAttribute(this.getClass().getName(), false)) {
                    marker.delete();
                }
            }
        } catch (Exception e) {
            // Ignore, but print a warning
            System.err.println("WARNING: Deleting markers in the IDE failed:\n" + e.toString());
        }
    }
    
    /**
     * Convert from LF to Xtext severity class.
     * 
     * @param severity in LF
     * @return severity in Xtext
     */
    private Severity convertToXtext(DiagnosticSeverity severity) {
        switch (severity) {
            case Error: return Severity.ERROR;
            case Hint: return Severity.INFO;
            case Information: return Severity.INFO;
            case Warning: return Severity.WARNING;
            default: return Severity.IGNORE;
        }
    }
    
    /**
     * Convert from LF to IMarker severity class.
     * 
     * @param severity in LF
     * @return severity in IMarker
     */
    private int convertToMarker(DiagnosticSeverity severity) {
        switch (severity) {
            case Error: return IMarker.SEVERITY_ERROR;
            case Hint: return IMarker.SEVERITY_INFO;
            case Information: return IMarker.SEVERITY_INFO;
            case Warning: return IMarker.SEVERITY_WARNING;
            default: return IMarker.SEVERITY_INFO;
        }
    }
    
}
