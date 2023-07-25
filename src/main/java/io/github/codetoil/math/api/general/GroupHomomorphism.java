package io.github.codetoil.math.api.general;

public abstract class GroupHomomorphism<A extends Group<A>, B extends Group<B>> {
    public final A groupA;
    public final B groupB;

    public GroupHomomorphism(A groupA, B groupB) {
        this.groupA = groupA;
        this.groupB = groupB;
    }

    public abstract GroupElement<B> apply(GroupElement<A> element);
}
