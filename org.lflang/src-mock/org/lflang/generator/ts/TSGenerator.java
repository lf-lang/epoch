
package org.lflang.generator.ts;

import org.lflang.Mock;
import org.lflang.generator.GeneratorBase;
import org.lflang.generator.LFGeneratorContext;
import org.lflang.generator.TargetTypes;
import org.lflang.scoping.LFGlobalScopeProvider;
import org.lflang.target.Target;

public class TSGenerator extends GeneratorBase {

    public TSGenerator(LFGeneratorContext context) {
        super(context);
    }
  
    public TSGenerator(LFGeneratorContext context,
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

}
