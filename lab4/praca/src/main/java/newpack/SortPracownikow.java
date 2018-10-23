package newpack;

import java.util.Comparator;

public class SortPracownikow implements Comparator<Pracownik> {
    public int compare(Pracownik pracownik1, Pracownik pracownik2) {
        double wynagrodzenie1 = pracownik1.getWynagrodzenieBrutto();
        double wynagrodzenie2 = pracownik2.getWynagrodzenieBrutto();

        if (wynagrodzenie1 < wynagrodzenie2)
            return -1;
        else if (wynagrodzenie1 > wynagrodzenie2)
            return 1;
        else
            return 0;
    }
}
