package Math.Vector;

import Math.Vector.Vectors.AbstractVector;
import Math.Vector.Vectors.Vector3D;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public final class VectorFactory {
    public static AbstractVector createClearVectorWithSameDimension(AbstractVector vector) {
        try{
            return getConstructorOfVector(vector, float[].class).newInstance(new float[]{});
        }
        catch (InvocationTargetException | InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public static AbstractVector createMultipliedOnNumberVector(AbstractVector vector, float number) {
        return getResultOfVectorNumberOperation(vector, number, VectorUtils::multiply);
    }

    public static AbstractVector createDividedOnNumberVector(AbstractVector vector, float number) {
        return getResultOfVectorNumberOperation(vector, number, VectorUtils::divide);
    }

    public static AbstractVector createSummedVector(AbstractVector vector1, AbstractVector vector2) {
        return getResultOfVectorOperation(vector1, vector2, VectorUtils::addition);
    }

    public static AbstractVector createSubtractedVector(AbstractVector vector1, AbstractVector vector2) {
        return getResultOfVectorOperation(vector1, vector2, VectorUtils::subtraction);
    }

    public static Vector3D createVectorProduct(Vector3D vector1, Vector3D vector2) {
        return new Vector3D(
                vector1.get(1) * vector2.get(2) - vector1.get(2) * vector2.get(1),
                vector1.get(2) * vector2.get(0) - vector1.get(0) * vector2.get(2),
                vector1.get(0) * vector2.get(1) - vector1.get(1) * vector2.get(0)
        );
    }

    private static AbstractVector getResultOfVectorOperation(AbstractVector vector1,
                                                             AbstractVector vector2,
                                                             VectorFunction<AbstractVector, AbstractVector> function) {
        AbstractVector vectorToReturn;
        try {
            vectorToReturn = getConstructorOfVector(vector1, AbstractVector.class).newInstance(vector1);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
        function.apply(vectorToReturn, vector2);
        return vectorToReturn;
    }

    private static AbstractVector getResultOfVectorNumberOperation(AbstractVector vector1, float number,
                                                                   VectorFunction<AbstractVector, Float> function) {
        AbstractVector vectorToReturn;
        try {
            vectorToReturn = getConstructorOfVector(vector1, AbstractVector.class).newInstance(vector1);
            function.apply(vectorToReturn, number);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
        return vectorToReturn;
    }

    private static Constructor<? extends AbstractVector> getConstructorOfVector(AbstractVector vector,
                                                                                Class<?> ... parameterTypes) {
        try {
            return vector.getClass().getDeclaredConstructor(parameterTypes);
        }
        catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    @FunctionalInterface
    private interface VectorFunction<V, E> {
        void apply(V vector1, E vector2);
    }
}
