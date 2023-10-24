package Math.Matrix.Matrixes;

import Math.Exceptions.WrongAmountOfAxesGivenException;
import Math.Vector.Vectors.Vector2D;

public class Matrix2D extends AbstractMatrix {
    public Matrix2D(Vector2D... vectors) {
        this.vectors = new Vector2D[2];
        switch (vectors.length) {
            case 0 -> {
                for(int i = 0; i < 2; i++) this.vectors[i] = new Vector2D();
            }
            case 2 -> System.arraycopy(vectors, 0, this.vectors, 0, 2);
            default -> throw new WrongAmountOfAxesGivenException();
        }
    }
}
