package org.lflang;

import org.eclipse.core.runtime.Plugin;
import org.lflang.generator.EclipseErrorReporter;
import org.lflang.generator.MainContext;
import org.osgi.framework.BundleContext;

public class LFPluginActivator extends Plugin {

    /**
     * {@inheritDoc}
     */
    @Override
    public void start(BundleContext context) throws Exception {
        super.start(context);
        
        // Configure LF core to work in Epoch
        MainContext.EPOCH_ERROR_REPORTER_CONSTRUCTOR = EclipseErrorReporter::new;
    }
    
}
