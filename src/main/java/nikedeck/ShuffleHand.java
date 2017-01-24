//ShuffleHand.java is an implementation of ShuffleIntf for hand shuffling

package nikedeck;

import java.util.Vector;
import java.util.Collections;

public class ShuffleHand implements ShuffleIntf {

    public Deck shuffle(Deck deck) {
    
       //split the list in half and interleave
       Vector<String> newList = new Vector<String>(deck.deck.size());
       int loopSize = deck.deck.size()/2;
       for (int i=0;i<loopSize;i++){
            newList.add((String)deck.deck.elementAt(i));
            newList.add((String)deck.deck.elementAt(i+loopSize));
       }
       deck.deck = newList;
       return deck;
    }
}
 
