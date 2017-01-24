//DeckUno.java is a subclass of Deck.java

package nikedeck;

public class DeckUno extends Deck<DeckUno> {

    public static final int cardCount = 108;

    public DeckUno(ShuffleIntf<DeckUno> shuffleMethod){

       super(shuffleMethod);

    }

    protected DeckUno getType(){
          return this;
    }

}
