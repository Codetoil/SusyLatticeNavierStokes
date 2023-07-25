package io.github.codetoil.math.api.general;

import io.github.codetoil.math.api.registry.Groups;
import io.github.codetoil.math.api.specific.GeneralLinear;

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

    public class AlternateTensorProductRepresentation extends GroupRepresentation<DirectProductGroup<U, V>> {
        public final GroupRepresentation<U> groupRepresentationU;
        public final GroupRepresentation<V> groupRepresentationV;

        public AlternateTensorProductRepresentation(GroupRepresentation<U> groupRepresentationU,
                                                    GroupRepresentation<V> groupRepresentationV) {
            super(DirectProductGroup.this, Groups.getGeneralLinearGroup(
                    ((GeneralLinear) groupRepresentationU.groupB).dimension *
                            ((GeneralLinear) groupRepresentationV.groupB).dimension,
                    groupRepresentationU.isComplex || groupRepresentationV.isComplex));
            this.groupRepresentationU = groupRepresentationU;
            this.groupRepresentationV = groupRepresentationV;
        }

        @Override
        public GroupElement<GeneralLinear> apply(GroupElement<DirectProductGroup<U, V>> element) {
            return ((GeneralLinear) this.groupB)
                    .new GeneralLinearElement(
                            ((DirectProductGroupElement) element).groupElementU.getMatrix(this.groupRepresentationU)
                                    .kron(
                                            ((DirectProductGroupElement) element).groupElementV
                                            .getMatrix(this.groupRepresentationV)));
        }
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
