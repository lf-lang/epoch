/*************
 * Copyright (c) 2023, Kiel University.
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
package org.lflang.ui.actions;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Status;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IPartListener2;
import org.eclipse.ui.IPropertyListener;
import org.eclipse.ui.IStartup;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchPartConstants;
import org.eclipse.ui.IWorkbenchPartReference;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.statushandlers.StatusManager;
import org.eclipse.xtext.ui.editor.XtextEditor;
import org.lflang.generator.EclipseMessageReporter;

/**
 * Handles save events on LF editors.
 *
 * @author Alexander Schulz-Rosengarten
 */
public class EditorSaveHandler implements IPropertyListener, IPartListener2, IStartup {

  /** Editors with this adapter on it */
  private Set<IEditorPart> editors = new HashSet<>();

  /**
   * Performs the intended operations on save.
   */
  private void onSave(IEditorPart editor) {
    if (editor instanceof XtextEditor xtextEditor) {
      // Clear markers
      try {
        EclipseMessageReporter.removeMessageMarkers(xtextEditor.getResource());
      } catch (CoreException e) {
        StatusManager.getManager()
            .handle(new Status(Status.ERROR, "org.lflang.ui", "Could not delete error markers", e), StatusManager.LOG);
      }
    }
  }

  /**
   * Adds this listener to the editor if not yet registered.
   */
  private void register(IEditorPart editor) {
    if (editor != null && !editors.contains(editor)) {
      editors.add(editor);
      editor.addPropertyListener(this);
    }
  }

  /**
   * Removes this listener from the editor.
   */
  private void unregister(IEditorPart editor) {
    if (editor != null) {
      editors.remove(editor);
      editor.removePropertyListener(this);
    }
  }

  /**
   * Returns true iff the editor is for LF.
   */
  private boolean isLFEditor(IWorkbenchPart part) {
    if (part instanceof XtextEditor editor) {
      var input = editor.getEditorInput();
      if (input != null && input.getName().endsWith(".lf")) {
        return true;
      }
    }
    return false;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void earlyStartup() {
    IWorkbenchPage page = null;
    IWorkbenchWindow window = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
    if (window != null) {
      page = window.getActivePage();
    }

    if (page == null) {
      // Look for a window and find the associated page
      IWorkbenchWindow[] windows = PlatformUI.getWorkbench().getWorkbenchWindows();
      for (int i = 0; i < windows.length; i++) {
        var currWindow = windows[i];
        if (windows[i] != null) {
          window = windows[i];
          page = currWindow.getActivePage();
          if (page != null) {
            break;
          }
        }
      }
    }
    if (page != null) {
      page.addPartListener(this);
      // In case we missed opening event
      var editor = page.getActiveEditor();
      if (isLFEditor(editor)) {
        register(editor);
      }
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void propertyChanged(Object source, int propId) {
    if (source instanceof IEditorPart editor) {
      if (editors.contains(editor) && propId == IWorkbenchPartConstants.PROP_DIRTY && !editor.isDirty()) {
        // dirty flag changed and editor is not dirty -> saved
        onSave(editor);
      }
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void partClosed(IWorkbenchPartReference partRef) {
    var part = partRef.getPart(false);
    if (isLFEditor(part)) {
      unregister((IEditorPart) part);
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void partOpened(IWorkbenchPartReference partRef) {
    var part = partRef.getPart(false);
    if (isLFEditor(part)) {
      register((IEditorPart) part);
    }
  }

}
