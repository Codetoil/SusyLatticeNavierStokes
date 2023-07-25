package io.github.codetoil.math.api.general;

public class DirectProductGroup<U extends Group<U>, V extends Group<V>>
        extends Group<DirectProductGroup<U, V>> {
    public final U groupU;
    public final V groupV;
    private final GroupElement<DirectProductGroup<U, V>> identity;

    public DirectProductGroup(U groupU, V groupV) {
        super(groupU.name + "×" + groupV.name);
        this.groupU = groupU;
        this.groupV = groupV;
        this.identity = new DirectProductGroupElement(this.groupU.getIdentity(), this.groupV.getIdentity());
    }

    @Override
    public GroupElement<DirectProductGroup<U, V>> getIdentity()
    {
        return this.identity;
    }

    public class DirectProductGroupElement extends GroupElement<DirectProductGroup<U, V>> {
        public final GroupElement<U> groupElementU;
        public final GroupElement<V> groupElementV;

        public DirectProductGroupElement(GroupElement<U> groupElementU, GroupElement<V> groupElementV) {
            super(DirectProductGroup.this);
            this.groupElementU = groupElementU;
            this.groupElementV = groupElementV;
        }
    }
}
