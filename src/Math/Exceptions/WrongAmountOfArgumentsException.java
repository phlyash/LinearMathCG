package Math.Exceptions;

public class WrongAmountOfArgumentsException extends RuntimeException {
    public WrongAmountOfArgumentsException() {
        super("Wrong amount of arguments given");
    }
}
