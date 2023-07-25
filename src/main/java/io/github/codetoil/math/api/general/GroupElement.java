package io.github.codetoil.math.api.general;

import io.github.codetoil.math.api.specific.GeneralLinear;
import org.ejml.simple.SimpleMatrix;

public class GroupElement<G extends Group<G>> {
    public final G group;

    public GroupElement(G group) {
        this.group = group;
    }

    public SimpleMatrix getMatrix(GroupRepresentation<G> groupRepresentation) {
        return ((GeneralLinear.GeneralLinearElement) groupRepresentation.apply(this)).getMatrix();
    }
}
