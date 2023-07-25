package io.github.codetoil.math.api.general;

import io.github.codetoil.math.api.registry.Groups;
import io.github.codetoil.math.api.specific.GeneralLinear;

public abstract class Group<G extends Group<G>> {
    protected final String name;

    public Group(String name) {
        this.name = name;
    }

    public abstract GroupElement<G> getIdentity();

    public String getName() {
        return this.name;
    }

    public class TensorProductRepresentation<U extends GroupRepresentation<G>, V extends GroupRepresentation<G>>
            extends GroupRepresentation<G> {
        public final U groupRepresentationU;
        public final V groupRepresentationV;

        public TensorProductRepresentation(U groupRepresentationU, V groupRepresentationV) {
            super(Group.this, Groups.getGeneralLinearGroup(
                    ((GeneralLinear) groupRepresentationU.groupB).dimension *
                            ((GeneralLinear) groupRepresentationV.groupB).dimension,
                    groupRepresentationU.isComplex || groupRepresentationV.isComplex));
            this.groupRepresentationU = groupRepresentationU;
            this.groupRepresentationV = groupRepresentationV;
        }

        @Override
        public GroupElement<GeneralLinear> apply(GroupElement<G> element) {

            return ((GeneralLinear) this.groupB)
                    .new GeneralLinearElement(
                            element.getMatrix(this.groupRepresentationU)
                                    .kron(element.getMatrix(this.groupRepresentationV)));
        }
    }
}
