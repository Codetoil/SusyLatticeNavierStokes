package io.github.codetoil.math.test;


import io.github.codetoil.math.api.general.DirectProductGroup;
import io.github.codetoil.math.api.general.GroupRepresentation;
import io.github.codetoil.math.api.registry.GroupRepresentations;
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

        GeneralLinear GL3C = Groups.getComplexGeneralLinearGroup(3);
        GeneralLinear.GeneralLinearElement random3C = GL3C
                .new GeneralLinearElement(SimpleMatrix.random_ZDRM(3, 3));


        GroupRepresentation<GeneralLinear> groupRepresentation1 = GroupRepresentations
                .getGeneralLinearRepresentation(GL3);

        GroupRepresentation<GeneralLinear> groupRepresentation2 = GroupRepresentations
                .getGeneralLinearRepresentation(GL5);

        GroupRepresentation<GeneralLinear> groupRepresentation1X1
                = GL3.new TensorProductRepresentation<
                GroupRepresentation<GeneralLinear>,
                GroupRepresentation<GeneralLinear>>(groupRepresentation1, groupRepresentation1);

        DirectProductGroup<GeneralLinear, GeneralLinear>.AlternateTensorProductRepresentation groupRepresentionDP =
                dpGroup.new AlternateTensorProductRepresentation(groupRepresentation1, groupRepresentation2);

        GroupRepresentation<GeneralLinear> groupRepresentation3C = GroupRepresentations
                .getGeneralLinearRepresentation(GL3C);

        System.out.println(GL3.getName() + ": dim " + groupRepresentation1.dimension);
        random3.getMatrix(groupRepresentation1).print();
        System.out.println(GL5.getName() + ": dim " + groupRepresentation2.dimension);
        random5.getMatrix(groupRepresentation2).print();
        System.out.println(dpGroup.getName() + ": dim " + groupRepresentionDP.dimension);
        randomDP.getMatrix(groupRepresentionDP).print();
        System.out.println(GL3.getName() + ": dim " + groupRepresentation1X1.dimension);
        random3.getMatrix(groupRepresentation1X1).print();
        System.out.println(GL3C.getName() + ": dim " + groupRepresentation3C.dimension);
        random3C.getMatrix(groupRepresentation3C).print();
    }
}