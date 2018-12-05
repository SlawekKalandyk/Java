package newpack;

public class PracownikEtatowy extends Pracownik {
    public PracownikEtatowy(String pesel, double wynagrodzenie) {
        super(pesel, wynagrodzenie);
    }

    @Override
    public Double obliczenieWynagrodzenieNetto() {
        return getWynagrodzenieBrutto();
    }
}
