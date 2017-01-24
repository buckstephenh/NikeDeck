//Deck52.java is a subclass of Deck.java

package nikedeck;

public class Deck52 extends Deck<Deck52> {

    public static final int cardCount = 52;

    public Deck52(ShuffleIntf<Deck52> shuffleMethod){

       super(shuffleMethod);

    }

    protected Deck52 getType(){
          return this;
    }
}
