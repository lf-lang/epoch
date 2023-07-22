
package org.lflang.analyses.uclid;

import java.util.List;

import org.lflang.Mock;
import org.lflang.Target;
import org.lflang.generator.GeneratorBase;
import org.lflang.generator.LFGeneratorContext;
import org.lflang.generator.TargetTypes;
import org.lflang.lf.Attribute;

public class UclidGenerator extends GeneratorBase {

    public static final Thread runner = null;

    public UclidGenerator(LFGeneratorContext context,
        List<Attribute> properties) {
        super(context);
        throw new IllegalStateException(Mock.VERIFIER_MSG);
    }

    @Override
    public TargetTypes getTargetTypes() {
        throw new IllegalStateException(Mock.VERIFIER_MSG);
    }

    @Override
    public Target getTarget() {
        throw new IllegalStateException(Mock.VERIFIER_MSG);
    }

}
