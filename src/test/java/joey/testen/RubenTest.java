package joey.testen;
import net.sf.firemox.clickable.ability.Ability;
import net.sf.firemox.clickable.target.card.MCard;
import net.sf.firemox.clickable.target.player.Player;
import net.sf.firemox.stack.StackManager;
import net.sf.firemox.token.IdZones;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Created by ruben on 18-6-2017.
 */
public class RubenTest {

    private StackManager stackManager;
    private Ability ability;
    private Player player;
    private MCard mcard;
    private final int HAND = IdZones.HAND;

    @Before
    public void setUp() throws Exception {

        // set the objects for the test
        player  = mock(Player.class);
        ability = mock(Ability.class);
        mcard   = spy(MCard.class);
        stackManager = StackManager.getInstance();

        // set the card
        mcard.setIdZone(HAND);
        mcard.setOwner(player);

        // set the stack manager
        stackManager.zoneAbortion = 5;
        StackManager.StackElement elem = mock(StackManager.StackElement.class);
        elem.ctxtokenCard = mcard;
        StackManager.CONTEXTES.add(elem);

        // set the methods invoked by the test
        doReturn("test").when(mcard).getCardName();
        doNothing().when(mcard).registerAbilities(stackManager.zoneAbortion);
    }

    @Test
    public void abortion() {

        // abort the play of the card (creature)
        stackManager.abortion(mcard, ability);

        // check if card is moved to the zone abortion
        int zoneAbortion = stackManager.zoneAbortion;
        int result       = mcard.getIdZone();
        assertEquals(zoneAbortion, result);
    }

}