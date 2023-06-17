
package org.lflang.generator.rust;

import org.lflang.KotlinMock;
import org.lflang.Target;
import org.lflang.generator.GeneratorBase;
import org.lflang.generator.LFGeneratorContext;
import org.lflang.generator.TargetTypes;
import org.lflang.scoping.LFGlobalScopeProvider;

public class RustGenerator extends GeneratorBase {

    public RustGenerator(LFGeneratorContext context,
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
