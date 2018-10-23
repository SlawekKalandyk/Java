package newpack;

public class Student extends Pracownik {
    public Student(String pesel, double wynagrodzenie) {
        super(pesel, wynagrodzenie);
    }

    @Override
    public double obliczenieWynagrodzeniaNetto() {
        return getWynagrodzenieBrutto();
    }
}
