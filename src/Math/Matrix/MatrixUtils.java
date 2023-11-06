package Math.Matrix;

import Math.Exceptions.MatrixExceptions.WrongDimensionMatrixException;
import Math.Exceptions.VectorExceptions.WrongDimensionVectorException;
import Math.Matrix.Matrixes.AbstractMatrix;
import Math.Matrix.Matrixes.Matrix2D;
import Math.Matrix.Matrixes.Matrix3D;
import Math.Matrix.Matrixes.Matrix4D;
import Math.Vector.VectorFactory;
import Math.Vector.VectorUtils;
import Math.Vector.Vectors.AbstractVector;
import Math.Vector.Vectors.Vector2D;
import Math.Vector.Vectors.Vector3D;

import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

public class MatrixUtils {
    public static final HashMap<Class<? extends AbstractMatrix>, Constructor<?>> demotionMatrix = new HashMap<>();
    public static final HashMap<Class<? extends AbstractMatrix>, Class<? extends AbstractVector>> demotedVectorForMatrix = new HashMap<>();

    static {
        try {
            demotionMatrix.put(Matrix3D.class, Matrix2D.class.getDeclaredConstructor(Array.newInstance(Vector2D.class, 0).getClass()));
            demotionMatrix.put(Matrix4D.class, Matrix3D.class.getDeclaredConstructor(Array.newInstance(Vector3D.class, 0).getClass()));

            demotedVectorForMatrix.put(Matrix3D.class, Vector2D.class);
            demotedVectorForMatrix.put(Matrix4D.class, Vector3D.class);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    public static void addition(AbstractMatrix matrix1, AbstractMatrix matrix2) {
        checkMatrixCompatibility(matrix1, matrix2);

        try {
            for (int i = 0; i < matrix1.getLength(); i++)
                VectorUtils.addition(matrix1.getVector(i), matrix2.getVector(i));
        }
        catch (WrongDimensionVectorException e) {
            throw new RuntimeException(e);
        }
    }

    public static void subtraction(AbstractMatrix matrix1, AbstractMatrix matrix2) {
        checkMatrixCompatibility(matrix1, matrix2);

        try {
        for(int i = 0; i < matrix1.getLength(); i++)
            VectorUtils.subtraction(matrix1.getVector(i), matrix2.getVector(i));
        }
        catch (WrongDimensionVectorException e) {
            throw new RuntimeException(e);
        }
    }

    public static void multiply(AbstractMatrix matrix, float number) {
        for(int i = 0; i < matrix.getLength(); i++) matrix.getVector(i).multiply(number);
    }

    public static void divide(AbstractMatrix matrix, float number) {
        if (Math.abs(number) < 1e-9) throw new ArithmeticException("You cant divide on 0 (zero)");
        for(int i = 0; i < matrix.getLength(); i++) matrix.getVector(i).divide(number);
    }

    public static AbstractVector multiplicationOnVector(AbstractMatrix matrix, AbstractVector vector) {
        if (matrix.getLength() != vector.getLength()) throw new WrongDimensionVectorException();

        AbstractVector vectorToReturn = VectorFactory.createClearVectorWithSameDimension(vector);

        for(int i = 0; i < matrix.getLength(); i++)
            for(int j = 0; j < matrix.getVectorLength(i); j++)
                vectorToReturn.set(vectorToReturn.get(i) + vector.get(j) * matrix.getVector(j).get(i), i);
        return vectorToReturn;
    }

    public static void transpose(AbstractMatrix matrix) {
        for(int i = 0; i < matrix.getLength(); i++)
            for(int j = i + 1; j < matrix.getLength(); j++) {
                matrix.getVector(i).set(matrix.getVector(i).get(j) + matrix.getVector(j).get(i), j);
                matrix.getVector(j).set(matrix.getVector(i).get(j) - matrix.getVector(j).get(i), i);
                matrix.getVector(i).set(matrix.getVector(i).get(j) - matrix.getVector(j).get(i), j);
            }
    }

    public static void matrixMultiplication(AbstractMatrix matrix1, AbstractMatrix matrix2) {
        AbstractMatrix newMatrix = MatrixFactory.createMatrixByMultiplication(matrix1, matrix2);

        for(int i = 0; i < newMatrix.getLength(); i++)
            for(int j = 0; j < newMatrix.getVectorLength(i); j++) matrix1.getVector(i).set(newMatrix.getVector(i).get(j), j);
    }

    public static AbstractMatrix getMinor(AbstractMatrix matrix, int y, int x) {
        AbstractMatrix minorMatrix;

        try {
            minorMatrix = (AbstractMatrix) MatrixUtils.demotionMatrix.get(matrix.getClass())
                    .newInstance(Array.newInstance(demotedVectorForMatrix.get(matrix.getClass()), 0));
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }

        for(int i = 0, xPassed = 0; i < matrix.getLength(); i++) {
            if (i == x) {
                xPassed = 1;
                continue;
            }
            for(int j = 0, yPassed = 0; j < matrix.getVectorLength(i); j++) {
                if (j == y) {
                    yPassed = 1;
                    continue;
                }
                minorMatrix.getVector(i - xPassed).set(matrix.getVector(i).get(j), j - yPassed);
            }
        }

        return minorMatrix;
    }

    public static void reverseMatrix(AbstractMatrix matrix) {
        AbstractMatrix reversedMatrix = MatrixFactory.createReversedMatrix(matrix);

        for(int i = 0; i < matrix.getLength(); i++)
            for(int j = 0; j < matrix.getVectorLength(i); j++) matrix.setElement(reversedMatrix.getElement(i, j), j, i);
    }

    public static float calculateCofactor(AbstractMatrix matrix, int y, int x) {
        return matrix.getMinor(y, x).getDeterminant() * ((x + y) % 2 == 0 ? 1 : -1);
    }

    private static void checkMatrixCompatibility(AbstractMatrix matrix1, AbstractMatrix matrix2) {
        if (matrix1.getClass() != matrix2.getClass()) throw new WrongDimensionMatrixException();
    }
}
