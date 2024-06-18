
package org.lflang.generator.cpp;

import org.lflang.Mock;
import org.lflang.generator.GeneratorBase;
import org.lflang.generator.LFGeneratorContext;
import org.lflang.generator.TargetTypes;
import org.lflang.generator.docker.DockerGenerator;
import org.lflang.scoping.LFGlobalScopeProvider;
import org.lflang.target.Target;

public class CppGenerator extends GeneratorBase {

    public CppGenerator(LFGeneratorContext context,
            LFGlobalScopeProvider scopeProvider) {
        super(context);
        throw new IllegalStateException(Mock.KOTLIN_MSG);
    }

    @Override
    public TargetTypes getTargetTypes() {
        throw new IllegalStateException(Mock.KOTLIN_MSG);
    }

    @Override
    public Target getTarget() {
        throw new IllegalStateException(Mock.KOTLIN_MSG);
    }
    
    @Override
    protected DockerGenerator getDockerGenerator(LFGeneratorContext context) {
      throw new IllegalStateException(Mock.KOTLIN_MSG);
    }

}
