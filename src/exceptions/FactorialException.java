package exceptions;

public class FactorialException extends Throwable {
    public FactorialException(){
        super("Faktorial neexistuje pro zaporna cisla");
    }
}
