package Math.Vector;

import Math.Exceptions.VectorExceptions.WrongDimensionVectorException;
import Math.Vector.Vectors.AbstractVector;
import Math.Vector.Vectors.Vector3D;

public final class VectorUtils {
    public static void multiply(AbstractVector vector, float number) {
        for(int i = 0; i < vector.getDimension(); i++) vector.set(vector.get(i) * number, i);
    }

    public static void divide(AbstractVector vector, float number) {
        if (Math.abs(number) < 1e-9) throw new ArithmeticException("You cant divide on 0 (zero)");
        for(int i = 0; i < vector.getDimension(); i++) vector.set(vector.get(i) / number, i);
    }

    public static float getLength(AbstractVector vector) {
        float squareSumOfAxes = 0;
        for(int i = 0; i < vector.getDimension(); i++) squareSumOfAxes += vector.get(i) * vector.get(i);
        return (float) Math.sqrt(squareSumOfAxes);
    }

    public static void addition(AbstractVector vector1, AbstractVector vector2) {
        checkVectorCompatibility(vector1, vector2);
        for(int i = 0; i < vector1.getDimension(); i++) vector1.set(vector1.get(i) + vector2.get(i), i);
    }

    public static void subtraction(AbstractVector vector1, AbstractVector vector2) {
        checkVectorCompatibility(vector1, vector2);
        for(int i = 0; i < vector1.getDimension(); i++) vector1.set(vector1.get(i) - vector2.get(i), i);
    }

    public static float getScalar(AbstractVector vector1, AbstractVector vector2) {
        checkVectorCompatibility(vector1, vector2);
        float sum = 0;
        for(int i = 0; i < vector1.getDimension(); i++) sum += vector1.get(i) * vector2.get(i);
        return sum;
    }

    public static void vectorProduct(Vector3D vector1, Vector3D vector2) {
        for(int i = 0; i < vector1.getDimension(); i++) vector1.set
                (vector1.get((i + 1) % 3) * vector2.get((i + 2) % 3) - vector1.get((i + 2) % 3) * vector2.get((i + 1) % 3), i);
    }

    private static void checkVectorCompatibility(AbstractVector vector1, AbstractVector vector2) {
        if (vector1.getClass() != vector2.getClass()) throw new WrongDimensionVectorException();
    }
}
