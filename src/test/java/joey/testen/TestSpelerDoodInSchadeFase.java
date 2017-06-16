package joey.testen;

import net.sf.firemox.stack.StackManager;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

/**
 * Created by Joey on 13-6-2017.
 */
public class TestSpelerDoodInSchadeFase {

    @Test
    public void checkSpelerInSchadeFase() {
        //true or false
    }

    @Test
    public void levenspuntenNul() {
      // true or false

    }

    @Test
    public void spelerDood() {
        // true or false

    }

    @Test
    public void berichtMetWinnaarBepaling() {
        StackManager sm = mock(StackManager.class);

        assertEquals(true, sm.finishSpell().);
        // true or false

    }

}
