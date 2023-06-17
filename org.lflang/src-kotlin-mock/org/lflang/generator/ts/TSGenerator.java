
package org.lflang.generator.ts;

import org.lflang.KotlinMock;
import org.lflang.Target;
import org.lflang.generator.GeneratorBase;
import org.lflang.generator.LFGeneratorContext;
import org.lflang.generator.TargetTypes;
import org.lflang.scoping.LFGlobalScopeProvider;

public class TSGenerator extends GeneratorBase {

    public TSGenerator(LFGeneratorContext context,
            LFGlobalScopeProvider scopeProvider) {
        super(context);
        throw new IllegalStateException(KotlinMock.MSG);
    }

    @Override
    public TargetTypes getTargetTypes() {
        throw new IllegalStateException(KotlinMock.MSG);
    }

    @Override
    public Target getTarget() {
        throw new IllegalStateException(KotlinMock.MSG);
    }

}
