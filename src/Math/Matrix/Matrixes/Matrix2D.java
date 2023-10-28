package Math.Matrix.Matrixes;

import Math.Exceptions.MatrixExceptions.WrongDimensionMatrixException;
import Math.Exceptions.WrongAmountOfAxesGivenException;
import Math.Vector.Vectors.Vector2D;

public final class Matrix2D extends AbstractMatrix {
    public Matrix2D(Vector2D ... vectors) {
        this.vectors = new Vector2D[2];

        switch (vectors.length) {
            case 0 -> {
                for(int i = 0; i < 2; i++) this.vectors[i] = new Vector2D();
            }
            case 2 -> System.arraycopy(vectors, 0, this.vectors, 0, 2);
            default -> throw new WrongAmountOfAxesGivenException();
        }
    }

    public Matrix2D(AbstractMatrix matrix) {
        if (matrix.getClass() != this.getClass()) throw new WrongDimensionMatrixException();

        this.vectors = new Vector2D[2];
        for(int i = 0; i < 2 ; i++)
            this.vectors[i] = new Vector2D(matrix.vectors[i]);
    }

    @Override
    public float getDeterminant() {
        return this.getVector(0).get(0) * this.getVector(1).get(1) -
                this.getVector(1).get(0) * this.getVector(0).get(1);
    }
}
