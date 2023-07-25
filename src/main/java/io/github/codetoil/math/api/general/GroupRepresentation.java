package io.github.codetoil.math.api.general;

import io.github.codetoil.math.api.specific.GeneralLinear;

public abstract class GroupRepresentation<G extends Group<G>> extends GroupHomomorphism<G, GeneralLinear> {
    public final boolean isComplex;
    public final int dimension;

    public GroupRepresentation(Group<G> group, GeneralLinear generalLinear) {
        super(group, generalLinear);
        this.isComplex = generalLinear.isComplex;
        this.dimension = generalLinear.dimension;
    }
}
