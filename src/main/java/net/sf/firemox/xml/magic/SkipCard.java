package net.sf.firemox.xml.magic;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * Created by Joey on 22-6-2017.
 */
public class SkipCard {

    /**
     * @param in ingekomen bestand vanuit klassen
     */
    public static void skipCard(BufferedReader in) throws IOException {
        String line = in.readLine();
        while (line != null && line.length() > 0) {
            line = in.readLine();
        }
    }
}
