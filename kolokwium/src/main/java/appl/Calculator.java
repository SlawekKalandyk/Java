package appl;

import excp.BadInputException;
import excp.NothingToSubtractFromException;
import excp.TooBigNumberException;

public abstract class Calculator {
    abstract void SaveToFile(String x, String fileName);
    abstract void Add(String x);
    abstract void Subtract(String x) throws NothingToSubtractFromException, BadInputException;
    abstract void Multiply(int x) throws TooBigNumberException;

}
