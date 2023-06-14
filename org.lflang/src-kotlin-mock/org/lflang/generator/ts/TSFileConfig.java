package org.lflang.generator.ts;

import java.io.IOException;
import java.nio.file.Path;

import org.eclipse.emf.ecore.resource.Resource;
import org.lflang.FileConfig;
import org.lflang.KotlinMock;

public class TSFileConfig extends FileConfig {

    public TSFileConfig(Resource resource, Path srcGenBasePath,
            boolean useHierarchicalBin) throws IOException {
        super(resource, srcGenBasePath, useHierarchicalBin);
        throw new IllegalStateException(KotlinMock.MSG);
    }

    public Path getIncludePath() {
        throw new IllegalStateException(KotlinMock.MSG);
    }

    public String getRuntimeIncludePath() {
        throw new IllegalStateException(KotlinMock.MSG);
    }
}
