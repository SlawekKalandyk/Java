package appl;

import excp.BadInputException;
import excp.NothingToSubtractFromException;
import excp.TooBigNumberException;

import java.util.*;
import java.io.*;

import java.lang.String;

public class StringCalculator extends Calculator {
    public static void main(String[] argv) {
        StringCalculator calc = new StringCalculator("");
        calc.Add("abc");
        System.out.println("Addition of abc: ");
        System.out.println(calc.getResult());

        try {
            calc.Multiply(3);
        } catch (TooBigNumberException t) {
            t.printStackTrace();
        }

        System.out.println("Multiplication times 3: ");
        System.out.println(calc.getResult());

        try {
            calc.Subtract("bc");
        } catch (NothingToSubtractFromException n) {
            n.printStackTrace();
        } catch (BadInputException b) {
            b.printStackTrace();
        }

        System.out.println("Subtraction of bc: ");
        System.out.println(calc.getResult());
    }

    private String result;

    public StringCalculator() {
        result = "";
    }

    public StringCalculator(String str) {
        result = str;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    @Override
    public void Add(String x) {
        result += x;
    }

    @Override
    public void Subtract(String x) throws NothingToSubtractFromException, BadInputException {
        if (result.isEmpty())
            throw new NothingToSubtractFromException();

        String subtractionResult = "";
        String resultCopy = result;
        int counter = 0;
        if (result.length() > x.length()) {
            for (int i = 0; i < result.length() - x.length(); ++i) {
                if (result.substring(i,  i + x.length()).equals(x)) {
                    subtractionResult += result.substring(counter, i);
                    counter = i + x.length();
                }
            }
        }
        if (result.equals(resultCopy))
            throw new BadInputException();

        result = subtractionResult;
    }

    @Override
    public void Multiply(int x) throws TooBigNumberException {
        if (x > 5)
            throw new TooBigNumberException();

        String temporary = result;
        for (int i = 0; i < x - 1; ++i)
            this.Add(temporary);
    }

    public void SaveToFile(String x, String fileName) {
        try {
            FileWriter fileWriter = new FileWriter(fileName);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(x);

            bufferedWriter.close();
        } catch (IOException ex) {
            System.out.println("Error writing to file '" + fileName + "'");
        }
    }

}
