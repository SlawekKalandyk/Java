import java.util.*;

public class LiczbyPierwsze {
    private List<Integer> _tab = new ArrayList<Integer>();
    private int _liczba;

    public LiczbyPierwsze(int liczba) {
        _liczba = liczba;
        this.SzukanieLiczbPierwszych();
    }
    
    private void SzukanieLiczbPierwszych() {
        for(int i = 2; i < _liczba; ++i) {
            int licznik = 0;
            for(int j = 2; j < Math.sqrt(i) + 1; ++j) {
                if(i%j == 0) {
                    ++licznik;
                    break;
                }
            }
            if(licznik == 0 || i == 2) {
                _tab.add(i);
            }
            licznik = 0;
        }
    }
    public void WypiszLiczbyPierwsze() {
        if(_tab.isEmpty())
            System.out.println("Brak liczb pierwszych mniejszych od podanej liczby");
        else {
            System.out.println("Liczby pierwsze dla podanej liczby:");
            for (Integer i : _tab) {
                System.out.println(i);
            }
        }
    }
}