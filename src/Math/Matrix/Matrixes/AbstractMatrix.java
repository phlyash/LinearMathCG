package Math.Matrix.Matrixes;

import Math.Matrix.MatrixUtils;
import Math.Vector.Vectors.AbstractVector;

import java.util.Arrays;

public abstract class AbstractMatrix {
    protected AbstractVector[] vectors;

    public void addMatrix(AbstractMatrix matrix) {
        MatrixUtils.addition(this, matrix);
    }

    public void subtractMatrix(AbstractMatrix matrix) {
        MatrixUtils.subtraction(this, matrix);
    }

    public void multiply(float number) {
        MatrixUtils.multiply(this, number);
    }

    public void divide(float number) {
        MatrixUtils.divide(this, number);
    }

    public AbstractVector multiplyOnVector(AbstractVector vector) {
        return MatrixUtils.multiplicationOnVector(this, vector);
    }

    public void transpose() {
        MatrixUtils.transpose(this);
    }

    public void multiplyOnMatrix(AbstractMatrix matrix) {
        MatrixUtils.matrixMultiplication(this, matrix);
    }

    public AbstractVector getVector(int number) {
        return vectors[number];
    }

    public void setVector(AbstractVector vector, int number) {
        vectors[number] = vector;
    }

    public int getLength() {
        return vectors.length;
    }
    
    public int getVectorLength(int vector) {
        return vectors[vector].getLength();
    }

    public AbstractMatrix getMinor(int x, int y) {
        return MatrixUtils.getMinor(this, x, y);
    }

    public float getDeterminant() {
        float determinant = 0;
        for(int i = 0; i < this.getLength(); i++) {
            determinant += this.getVector(i).get(0) * this.getMinor(0, i).getDeterminant() * (i % 2 == 0 ? 1 : -1);
        }

        return determinant;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        AbstractMatrix that = (AbstractMatrix) object;
        return Arrays.equals(vectors, that.vectors);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(vectors);
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() +
                "{vectors=" + Arrays.toString(vectors) +
                '}';
    }
}

