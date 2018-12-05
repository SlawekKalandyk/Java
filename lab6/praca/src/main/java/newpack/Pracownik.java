package newpack;

public abstract class Pracownik {
    private PESEL pesel = new PESEL();
    private Double wynagrodzenieBrutto = 0.0;

    public Pracownik(String pesel, double wynagrodzenieBrutto) {
        this.pesel.setPesel(pesel);
        this.wynagrodzenieBrutto = wynagrodzenieBrutto;
    }

    public Double getWynagrodzenieBrutto() {
        return wynagrodzenieBrutto;
    }

    public void setWynagrodzenieBrutto(double noweWynagrodzenie) {
        this.wynagrodzenieBrutto = noweWynagrodzenie;
    }

    public String getPeselStr() {
        return pesel.getPesel();
    }

    public abstract Double obliczenieWynagrodzenieNetto();
}
