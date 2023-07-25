package io.github.codetoil.math.test;


import io.github.codetoil.math.api.general.DirectProductGroup;
import io.github.codetoil.math.api.general.GroupRepresentation;
import io.github.codetoil.math.api.registry.Groups;
import io.github.codetoil.math.api.specific.GeneralLinear;
import org.ejml.simple.SimpleMatrix;

public class Main {
    public static void main(String[] args) {
        GeneralLinear GL3 = Groups.getGeneralLinearGroup(3);
        GeneralLinear.GeneralLinearElement random3 = GL3
                .new GeneralLinearElement(SimpleMatrix.random(3, 3));
        GeneralLinear GL5 = Groups.getGeneralLinearGroup(5);
        GeneralLinear.GeneralLinearElement random5 = GL5
                .new GeneralLinearElement(SimpleMatrix.random(5, 5));
        DirectProductGroup<GeneralLinear, GeneralLinear> dpGroup = new DirectProductGroup<>(GL3, GL5);
        DirectProductGroup<GeneralLinear, GeneralLinear>.DirectProductGroupElement randomDP = dpGroup
                .new DirectProductGroupElement(random3, random5);
        GroupRepresentation<GeneralLinear> groupRepresentation1 = GL3.new GeneralLinearRepresentation();
        GroupRepresentation<GeneralLinear> groupRepresentation1X1
                = GL3.new TensorProductRepresentation<
                GroupRepresentation<GeneralLinear>,
                GroupRepresentation<GeneralLinear>>(groupRepresentation1, groupRepresentation1);
        System.out.println(GL3.getName());
        random3.getMatrix().print();
        System.out.println(GL5.getName());
        random5.getMatrix().print();
        System.out.println(dpGroup.getName());
        System.out.println("No matrix: " + randomDP);
        System.out.println(GL3.getName());
        random3.getMatrix(groupRepresentation1X1).print();
    }
}