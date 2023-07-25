package io.github.codetoil.math.api.general;

public abstract class GroupHomomorphism<A extends Group<A>, B extends Group<B>> {
    public final Group<A> groupA;
    public final Group<B> groupB;

    public GroupHomomorphism(Group<A> groupA, Group<B> groupB) {
        this.groupA = groupA;
        this.groupB = groupB;
    }

    public abstract GroupElement<B> apply(GroupElement<A> element);
}
