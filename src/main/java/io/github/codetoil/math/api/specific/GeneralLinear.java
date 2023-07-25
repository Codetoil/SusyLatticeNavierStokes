package io.github.codetoil.math.api.specific;

import io.github.codetoil.math.api.general.Group;
import io.github.codetoil.math.api.general.GroupElement;
import io.github.codetoil.math.api.general.GroupRepresentation;
import org.ejml.data.DMatrixRMaj;
import org.ejml.data.ZMatrixRMaj;
import org.ejml.simple.SimpleMatrix;

public class GeneralLinear extends Group<GeneralLinear> {
    public final int dimension;
    public final boolean isComplex;
    private final GroupElement<GeneralLinear> identity;

    public GeneralLinear(int dimension, boolean isComplex) {
        super("GL(" + dimension + ", " + (isComplex ? "C" : "R") + ")");
        this.isComplex = isComplex;
        this.dimension = dimension;
        this.identity = new GeneralLinearElement(SimpleMatrix.identity(this.dimension,
                isComplex ? ZMatrixRMaj.class : DMatrixRMaj.class));
    }

    @Override
    public GroupElement<GeneralLinear> getIdentity() {
        return identity;
    }


    public class GeneralLinearRepresentation extends GroupRepresentation<GeneralLinear> {
        public GeneralLinearRepresentation() {
            super(GeneralLinear.this, GeneralLinear.this);
        }


        @Override
        public GroupElement<GeneralLinear> apply(GroupElement<GeneralLinear> element) {
            return element;
        }
    }

    public class GeneralLinearElement extends GroupElement<GeneralLinear> {
        private final SimpleMatrix matrix;

        public GeneralLinearElement(SimpleMatrix matrix) {
            super(GeneralLinear.this);
            if (!matchesConstraints(matrix)) throw new RuntimeException("Doesn't match matrix constraints, " + matrix);
            this.matrix = matrix;
        }

        private boolean matchesConstraints(SimpleMatrix matrix) {
            return matrix.getNumCols() == GeneralLinear.this.dimension
                    && matrix.getNumRows() == GeneralLinear.this.dimension
                    && matrix.getType().isReal() != GeneralLinear.this.isComplex;
        }

        public SimpleMatrix getMatrix() {
            return matrix;
        }
    }
}
