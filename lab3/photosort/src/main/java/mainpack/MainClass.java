package mainpack;

import io.indico.api.utils.IndicoException;

public class MainClass {
    public static void main(String[] argv) {
        PhotoSorting ps;
        try {
            ps = new PhotoSorting(argv[0]);
            ps.photoSort();
        } catch (IndicoException ie) {
            ie.printStackTrace();
        }
    }
}
