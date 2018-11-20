package mainpack;

import java.util.ArrayList;
import java.util.regex.*;

public class MicroDVD {
    public static void delay(final String in, final String out, int delay, int fps)
            throws SubtitleEndsBeforeItStarts, InvalidInputFormat, NegativeFrameAfterDelaying {

        ArrayList<String> lines = new ArrayList<>(FileReadAndWrite.fileRead(in));
        ArrayList<String> processedLines = new ArrayList<>();
        int frameOffset = (delay * fps) / 1000;

        String pattern = "\\{(\\d+)}\\{(\\d+)}([^\\n]*)";
        Pattern p = Pattern.compile(pattern);

        for (String line : lines) {
            String processedLine = "";
            Matcher m = p.matcher(line);
            m.matches();

            try {
                Pattern numbers = Pattern.compile("(\\d+)");
                numbers.matcher(m.group(1));
                numbers.matcher(m.group(2));
            } catch (Exception e) {
                throw new InvalidInputFormat();
            }

            int start = Integer.parseInt(m.group(1));
            int end = Integer.parseInt(m.group(2));

            if (start > end)
                throw new SubtitleEndsBeforeItStarts(start, end);

            int startDelay = start + frameOffset;
            int endDelay = end + frameOffset;

            if (startDelay < 0 || endDelay < 0)
                throw new NegativeFrameAfterDelaying();

            processedLine += "{" + Integer.toString(startDelay) + "}";
            processedLine += "{" + Integer.toString(endDelay) + "}";
            processedLine += m.group(3);
            processedLines.add(processedLine);
        }

        FileReadAndWrite.fileWrite(processedLines, out);
    }
}
