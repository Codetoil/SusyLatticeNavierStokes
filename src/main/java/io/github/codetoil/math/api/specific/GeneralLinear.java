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
            matchesConstraints(matrix);
            this.matrix = matrix;
        }

        private void matchesConstraints(SimpleMatrix matrix) {
            if (matrix.getNumCols() != GeneralLinear.this.dimension)
                throw new RuntimeException("Wrong number of cols: Expected: " + GeneralLinear.this.dimension +
                        " Actual: " + matrix.getNumCols());
            if (matrix.getNumRows() != GeneralLinear.this.dimension)
                throw new RuntimeException("Wrong number of rows: Expected: " + GeneralLinear.this.dimension +
                        " Actual: " + matrix.getNumRows());
            if (matrix.getType().isReal() == GeneralLinear.this.isComplex)
                throw new RuntimeException("Wrong type: Expected: "
                        + (GeneralLinear.this.isComplex ? "complex" : "real")
                        + " Actual: "
                        + (matrix.getType().isReal() ? "real" : "complex") );;
        }

        public SimpleMatrix getMatrix() {
            return matrix;
        }
    }
}
