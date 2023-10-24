package Math.Vector.Vectors;

import Math.Exceptions.VectorExceptions.WrongDimensionVectorException;
import Math.Vector.VectorUtils;

import java.util.Arrays;

public abstract class AbstractVector {
    protected float[] vector;

    public void multiply(float num) {
        VectorUtils.multiply(this, num);
    }

    public void divide(float num) {
        VectorUtils.divide(this, num);
    }

    public void normalize() {
        divide(getLength());
    }

    public void set(float number, int axis) {
        vector[axis] = number;
    }

    public float get(int axis) {
        return vector[axis];
    }

    public int getDimension() {
        return vector.length;
    }

    public void addVector(AbstractVector vectorToAdd) {
        VectorUtils.addition(this, vectorToAdd);
    }

    public void subtractVector(AbstractVector vectorToSubtract) {
        VectorUtils.subtraction(this, vectorToSubtract);
    }

    public float getScalarMultiplication(AbstractVector vectorToMultiplyBy) {
        return VectorUtils.getScalar(this, vectorToMultiplyBy);
    }

    public float getLength() {
        return VectorUtils.getLength(this);
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        AbstractVector that = (AbstractVector) object;
        return Arrays.equals(vector, that.vector);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(vector);
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() +
                "{vector=" + Arrays.toString(vector) +
                '}';
    }
}
