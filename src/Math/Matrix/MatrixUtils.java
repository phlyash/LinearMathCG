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

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

public class MatrixUtils {
    public static final HashMap<Class<? extends AbstractMatrix>, Constructor<?>> demotionMatrix = new HashMap<>();
    static {
        demotionMatrix.put(Matrix3D.class, Matrix2D.class.getConstructors()[0]);
        demotionMatrix.put(Matrix4D.class, Matrix3D.class.getConstructors()[0]);
    }

    public static void addition(AbstractMatrix matrix1, AbstractMatrix matrix2) {
        checkMatrixCompatibility(matrix1, matrix2);
        for(int i = 0; i < matrix1.getDimension(); i++) try {
            VectorUtils.addition(matrix1.getVector(i), matrix2.getVector(i));
        }
        catch (WrongDimensionVectorException e) {
            throw new RuntimeException(e);
        }
    }

    public static void subtraction(AbstractMatrix matrix1, AbstractMatrix matrix2) {
        checkMatrixCompatibility(matrix1, matrix2);
        for(int i = 0; i < matrix1.getDimension(); i++) try {
            VectorUtils.subtraction(matrix1.getVector(i), matrix2.getVector(i));
        }
        catch (WrongDimensionVectorException e) {
            throw new RuntimeException(e);
        }
    }

    public static void multiply(AbstractMatrix matrix, float number) {
        for(int i = 0; i < matrix.getDimension(); i++) matrix.getVector(i).multiply(number);
    }

    public static void divide(AbstractMatrix matrix, float number) {
        if (Math.abs(number) < 1e-9) throw new ArithmeticException("You cant divide on 0 (zero)");
        for(int i = 0; i < matrix.getDimension(); i++) matrix.getVector(i).divide(number);
    }

    public static AbstractVector multiplicationOnVector(AbstractMatrix matrix, AbstractVector vector) {
        if (matrix.getDimension() != vector.getDimension()) throw new WrongDimensionVectorException();
        AbstractVector vectorToReturn = VectorFactory.createClearVectorWithSameDimension(vector);
        for(int i = 0; i < matrix.getDimension(); i++)
            for(int j = 0; j < matrix.getDimension(); j++)
                vectorToReturn.set(vectorToReturn.get(i) + vector.get(j) * matrix.getVector(j).get(i), i);
        return vectorToReturn;
    }

    public static void transpose(AbstractMatrix matrix) {
        for(int i = 0; i < matrix.getDimension(); i++)
            for(int j = i + 1; j < matrix.getDimension(); j++) {
                matrix.getVector(i).set(matrix.getVector(i).get(j) + matrix.getVector(j).get(i), j);
                matrix.getVector(j).set(matrix.getVector(i).get(j) - matrix.getVector(j).get(i), i);
                matrix.getVector(i).set(matrix.getVector(i).get(j) - matrix.getVector(j).get(i), j);
            }
    }

    public static void matrixMultiplication(AbstractMatrix matrix1, AbstractMatrix matrix2) { // TODO think about how to save it into matrix1 without creating new matrix `cause its destroys all structure
        AbstractMatrix newMatrix = MatrixFactory.createMatrixByMultiplication(matrix1, matrix2);
        for(int i = 0; i < newMatrix.getDimension(); i++)
            for(int j = 0; j < newMatrix.getVector(i).getDimension(); j++) matrix1.getVector(i).set(newMatrix.getVector(i).get(j), j);
    }

    public static AbstractMatrix getMinor(AbstractMatrix matrix, int x, int y) { // TODO
        return null;
    }

    private static void checkMatrixCompatibility(AbstractMatrix matrix1, AbstractMatrix matrix2) {
        if (matrix1.getClass() != matrix2.getClass()) throw new WrongDimensionMatrixException();
    }
}
