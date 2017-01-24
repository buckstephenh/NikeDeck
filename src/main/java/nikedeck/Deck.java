//Deck.java is a generic kind of deck, where specific types can inherit
package nikedeck;

import java.util.Vector;
import nikedeck.ShuffleIntf;

public abstract class Deck<D extends Deck<D>> {

  public static final ShuffleIntf<Deck52> DECK52 = new ShuffleHand();
  public static final ShuffleIntf<DeckUno> DECKUNO = new ShuffleRandom();

  public Vector<String> deck;

  private final ShuffleIntf<D> shuffleIntf;

  public Deck (ShuffleIntf<D> shuffleIntf){
    this.shuffleIntf = shuffleIntf;
  }
 
  protected abstract D getType();

   public Deck shuffle(Deck deck){
       return deck.shuffle(getType());
  }
}
