package newpack;

import java.util.LinkedList;

public class Kadry {
    private LinkedList<Pracownik> pracownicy = new LinkedList<>();

    public void dodajPracownika(Pracownik pracownik) {
        pracownicy.add(pracownik);
    }

    public Pracownik znajdzPracownika(String pesel) {
        for (Pracownik pracownik : pracownicy) {
            if (pracownik.getPeselStr().equals(pesel))
                return pracownik;
        }

        return null;
    }

    public void usunPracownika(Pracownik pracownik) {
        pracownicy.remove(pracownik);
    }

    public void usunPracownika(String pesel) {
        pracownicy.remove(znajdzPracownika(pesel));
    }

    public void zmienWynagrodzenieBruttoPracownika(Pracownik pracownik, double noweWynagrodzenie) {
        pracownicy.get(pracownicy.indexOf(pracownik)).setWynagrodzenieBrutto(noweWynagrodzenie);
    }

    public double wynagrodzenieBruttoPracownika(Pracownik pracownik) {
        return pracownicy.get(pracownicy.indexOf(pracownik)).getWynagrodzenieBrutto();
    }

    public LinkedList<Pracownik> sortowaniePracownikow() {
        LinkedList<Pracownik> pracownicyTemp = new LinkedList<>(pracownicy);
        pracownicyTemp.sort(new SortPracownikow());
        return pracownicyTemp;
    }
}
