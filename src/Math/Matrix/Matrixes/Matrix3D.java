package Math.Matrix.Matrixes;

import Math.Exceptions.WrongAmountOfAxesGivenException;
import Math.Vector.Vectors.Vector3D;

public class Matrix3D extends AbstractMatrix {
    public Matrix3D(Vector3D ... vectors) {
        this.vectors = new Vector3D[3];
        switch (vectors.length) {
            case 0 -> {
                for(int i = 0; i < 3; i++) this.vectors[i] = new Vector3D();
            }
            case 3 -> System.arraycopy(vectors, 0, this.vectors, 0, 3);
            default -> throw new WrongAmountOfAxesGivenException();
        }
    }
}
