package org.lflang.generator.cpp;

import java.io.IOException;
import java.nio.file.Path;

import org.eclipse.emf.ecore.resource.Resource;
import org.lflang.FileConfig;
import org.lflang.KotlinMock;

public class CppFileConfig extends FileConfig {

    public CppFileConfig(Resource resource, Path srcGenBasePath,
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