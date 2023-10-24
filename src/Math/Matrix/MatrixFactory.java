package Math.Matrix;

import Math.Exceptions.MatrixExceptions.WrongDimensionMatrixException;
import Math.Exceptions.WrongAmountOfAxesGivenException;
import Math.Matrix.Matrixes.AbstractMatrix;
import Math.Matrix.Matrixes.Matrix3D;
import Math.Matrix.Matrixes.Matrix4D;

import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class MatrixFactory {
    public static AbstractMatrix createZeroMatrix(int dimensions) {
        switch (dimensions) {
            case 3 -> {
                return new Matrix3D();
            }
            case 4 -> {
                return new Matrix4D();
            }
            default -> throw new WrongAmountOfAxesGivenException();
        }
    }

    public static AbstractMatrix createIdentityMatrix(int dimensions) {
        AbstractMatrix matrix = createZeroMatrix(dimensions);

        for(int i = 0; i < matrix.getDimension(); i++) {
            matrix.getVector(i).set(1, i);
        }

        return matrix;
    }

    public static AbstractMatrix createMatrixByMultiplication(AbstractMatrix matrix1, AbstractMatrix matrix2) {
        if (matrix1.getDimension() != matrix2.getVector(0).getDimension()) throw new WrongDimensionMatrixException();

        AbstractMatrix newMatrix;
        try {
            newMatrix = getConstructorOfMatrix(matrix1, Array.newInstance(matrix1.getVector(0).getClass(), 0).
                    getClass()).newInstance(Array.newInstance(matrix1.getVector(0).getClass(), 0));
        } catch (InstantiationException | InvocationTargetException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }

        for(int i = 0; i < matrix1.getVector(0).getDimension(); i++)
            for(int j = 0; j < matrix2.getDimension(); j++)
                for(int k = 0; k < matrix1.getDimension(); k++)
                    newMatrix.getVector(i).set(newMatrix.getVector(i).get(j) + matrix1.getVector(i).get(k) * matrix2.getVector(k).get(j), j);

        return newMatrix;
    }

    private static Constructor<? extends AbstractMatrix> getConstructorOfMatrix(AbstractMatrix matrix, Class<?> ... parameterTypes) {
        try {
            return matrix.getClass().getConstructor(parameterTypes);
        }
        catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }
}
// TODO:
// computing determinant creating Matrix2D??
// reversed matrix: http://mathprofi.ru/kak_naiti_obratnuyu_matricu.html