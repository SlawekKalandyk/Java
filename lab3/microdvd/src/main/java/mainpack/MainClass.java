package mainpack;

public class MainClass {
    public static void main(String[] argv) {
        try {
            MicroDVD.delay(argv[0], argv[1], Integer.parseInt(argv[2]), Integer.parseInt(argv[3]));
        } catch (InvalidInputFormat i) {
            i.printStackTrace();
        } catch (SubtitleEndsBeforeItStarts s) {
            s.printStackTrace();
        } catch (NegativeFrameAfterDelaying n) {
            n.printStackTrace();
        }
    }
}
