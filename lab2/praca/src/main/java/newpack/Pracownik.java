package newpack;

public abstract class Pracownik {
    private PESEL pesel = new PESEL();
    private double wynagrodzenieBrutto = 0;

    public Pracownik(String pesel, double wynagrodzenieBrutto) {
        this.pesel.setPesel(pesel);
        this.wynagrodzenieBrutto = wynagrodzenieBrutto;
    }

    public double getWynagrodzenieBrutto() {
        return wynagrodzenieBrutto;
    }

    public void setWynagrodzenieBrutto(double noweWynagrodzenie) {
        this.wynagrodzenieBrutto = noweWynagrodzenie;
    }

    public String getPeselStr() {
        return pesel.getPesel();
    }

    public abstract double obliczenieWynagrodzeniaNetto();
}
