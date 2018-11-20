package mainpack;

public class SubtitleEndsBeforeItStarts extends Exception {
    public SubtitleEndsBeforeItStarts(int start, int end) {
        super("Subtitle ends before it starts. Start: " + Integer.toString(start) +
                " End: " + Integer.toString(end));
    }
}
