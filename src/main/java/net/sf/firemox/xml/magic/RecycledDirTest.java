package net.sf.firemox.xml.magic;

import net.sf.firemox.tools.MToolKit;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import java.io.*;



/**
 * Created by Joey on 22-6-2017.
 */
public class RecycledDirTest {
    /**
 * May the validated cards (recycled directory) be patched by the new ones?
 */
private static final boolean UPDATE_CARD = false;



//    public void recycledDir(File recycledDir) throws IOException {
//        // we get the XML file name for the generated card
//        String fileName = MToolKit.getExactKeyName(cardName) + ".xml";
//
//        // we test wether the card already exists in recycledDir
//        if (new File(recycledDir, fileName).exists()) {
//
//            // is already validated existing parsed card in recycledDir can be
//            // updated ?
//            if (UPDATE_CARD) {
//                // first, we copy the card to update to a file named "temp"
//                File patchFile = MToolKit.getFile(recycledDir.getAbsolutePath()
//                        + File.separator + fileName);
//                File tempFile = File.createTempFile(fileName, "temp");
//                FileUtils.copyFile(patchFile, tempFile);
//                final BufferedReader inExist = new BufferedReader(new FileReader(
//                        tempFile));
//                final PrintWriter outExist = new PrintWriter(new FileOutputStream(
//                        patchFile));
//                String lineExist = null;
//                boolean found = false;
//                while ((lineExist = inExist.readLine()) != null) {
//                    if (!found && lineExist.contains("name=\"")) {
//                        lineExist = lineExist
//                                .substring(0, lineExist.indexOf("name=\""))
//                                + "name=\""
//                                + cardName
//                                + lineExist.substring(lineExist.indexOf("\"", lineExist
//                                .indexOf("name=\"")
//                                + "name=\"".length() + 2));
//                        found = true;
//                    }
//                    outExist.println(lineExist);
//                }
//                IOUtils.closeQuietly(inExist);
//                IOUtils.closeQuietly(outExist);
//
//                if (!found) {
//                    System.err.println(">> Error patching card '" + cardName + "'");
//                    // } else {
//                    // patching card
//                }
//            }
//            skipCard(in);
//            continue;
//        }
//    }
}
