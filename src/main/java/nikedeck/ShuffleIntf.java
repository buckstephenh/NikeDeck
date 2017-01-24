//ShuffleIntf.java is the Shuffle Interface, for implementing Strategy Pattern with Generics
package nikedeck;

import java.util.Vector;

public interface ShuffleIntf<D extends Deck> {
    public Deck shuffle(D d);
}
