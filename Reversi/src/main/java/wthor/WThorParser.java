package wthor;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

class MatchData {
    public int theoreticalScore;   // 終盤40手目以降が最善と仮定した場合の理論スコア
    public int[] hands;

    public MatchData(int theoreticalScore, int[] hands) {
        this.theoreticalScore = theoreticalScore;
        this.hands = hands;
    }
}

public class WThorParser {
    public static List<MatchData> parse(InputStream in) throws IOException {
        List<MatchData> results = new ArrayList<MatchData>();
        int numMatches = readHeader(in);
        System.out.println(numMatches);

        for (int i = 0; i < numMatches; ++i)
            results.add(readMatchData(in));

        return results;
    }

    private static int readHeader(InputStream in) throws IOException {
        reallySkip(in, 4);
        int numMatches = readLittleEndianInt(in);
        reallySkip(in, 8);

        return numMatches;
    }

    private static MatchData readMatchData(InputStream in) throws IOException {
        reallySkip(in, 7);

        int theoreticalBlackScore = in.read();
        int theoreticalScore = (theoreticalBlackScore - 32) * 2;

        int[] hands = new int[60];
        for (int i = 0; i < 60; ++i) {
            hands[i] = in.read();
        }

        return new MatchData(theoreticalScore, hands);
    }

    private static void reallySkip(InputStream in, long n) throws IOException {
        while (n > 0) {
            long skipped = in.skip(n);
            n -= skipped;
        }
    }

    private static int readLittleEndianInt(InputStream in) throws IOException {
        int a = in.read();
        int b = in.read();
        int c = in.read();
        int d = in.read();
        return a + (b << 8) + (c << 16) + (d << 24);
    }
}
