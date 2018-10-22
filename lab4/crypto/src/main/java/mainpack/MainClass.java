package mainpack;

public class MainClass {
    public static void main(String[] argv) {
        ROT11 r = new ROT11();
        String w = "abcdefghijklmnopqrstuvwxyz";
        System.out.println(r.crypt(w));
        System.out.println(r.decrypt(r.crypt(w)));

        Polybius p = new Polybius();
        System.out.println(p.crypt(w));
        System.out.println(p.decrypt(p.crypt(w)));
    }
}
