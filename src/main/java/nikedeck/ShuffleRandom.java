//ShuffleRandom.java is an implmentation of ShuffleInt for random shuffling

package nikedeck;

import java.util.Vector;
import java.util.Collections;
import nikedeck.ShuffleIntf;

public class ShuffleRandom implements ShuffleIntf {

    public Deck shuffle(Deck deck) {
    
        //for thread safety should we be making a copy before shuffling?
        Collections.shuffle(deck.deck);
        return deck;

    }
}
 
