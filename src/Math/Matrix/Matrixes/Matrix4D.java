package Math.Matrix.Matrixes;

import Math.Exceptions.MatrixExceptions.WrongDimensionMatrixException;
import Math.Exceptions.WrongAmountOfAxesGivenException;

import Math.Vector.Vectors.Vector4D;

public final class Matrix4D extends AbstractMatrix {
    public Matrix4D(Vector4D ... vectors) {
        this.vectors = new Vector4D[4];

        switch (vectors.length) {
            case 0 -> {
                for(int i = 0; i < 4; i++) this.vectors[i] = new Vector4D();
            }
            case 4 -> System.arraycopy(vectors, 0, this.vectors, 0, 4);
            default -> throw new WrongAmountOfAxesGivenException();
        }
    }

    public Matrix4D(AbstractMatrix matrix) {
        if (matrix.getClass() != this.getClass()) throw new WrongDimensionMatrixException();

        this.vectors = new Vector4D[4];
        for(int i = 0; i < 4 ; i++)
            this.vectors[i] = new Vector4D(matrix.vectors[i]);
    }
}
