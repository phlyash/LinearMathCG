package Math.Exceptions.VectorExceptions;

public class WrongDimensionVectorException extends RuntimeException {
    public WrongDimensionVectorException() {
        super("Vectors with wrong dimension given");
    }
}
