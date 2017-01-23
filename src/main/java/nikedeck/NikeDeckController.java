/** Author stephenherronbuck@gmail.com 
 * NikeDeckController.java is the main router class for the RESTful implementation of NikeDeck,
 * a card shuffling service.
*/
 
package nikedeck;

import java.lang.reflect.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.*;
import java.util.HashMap;
import java.util.Vector;


@RestController
public class NikeDeckController {

//ConfigService for custom properties Spring flavor
@Autowired
private ConfigService configService;

//where the decks go in memory
private HashMap<String,Vector<String>> decks = new HashMap<String,Vector<String>>();

    //Root request with API instructions
    @RequestMapping("/")
    public String index() {
        String rest = "{\"message\":\"Greetings from NikeDeck!\"";
         rest += ",\"/decks/[deckName]\":\"PUT = create, POST = shuffle, GET = display, DELETE = delete.\"";
         rest += ",\"/decks\":\"GET a list of the current decks persisted in the service.\"}";
        return rest;
    }

    //Create a deck
    @RequestMapping(value = "/decks/{deckName}", method = RequestMethod.PUT)
    public @ResponseBody String createDeck(@PathVariable("deckName") String deckName) {

       Vector<String> deck = genDeck();
       saveDeck(deckName, deck);
 
       return fetchDeck(deckName);

    }
    
    //Shuffle a deck
    @RequestMapping(value = "/decks/{deckName}", method = RequestMethod.POST)
    public @ResponseBody String shuffleDeck(@PathVariable("deckName") String deckName) {
        
        Vector<String> deck = (Vector<String>)decks.get(deckName);        
        deck = shuffleDeck(deck);
        saveDeck(deckName,deck);
 
        return fetchDeck(deckName);

    }

    //Get a deck aka find and display
    @RequestMapping(value = "/decks/{deckName}", method = RequestMethod.GET)
    public @ResponseBody String getDeck(@PathVariable("deckName") String deckName) {
        
        return fetchDeck(deckName);

    }

    //Get decks aka find and display all decks
    @RequestMapping(value = "/decks", method = RequestMethod.GET)
    public @ResponseBody String getDecks() {
        
        return fetchDecks();
    }

    //Delete a deck
    @RequestMapping(value = "/decks/{deckName}", method = RequestMethod.DELETE)
    public @ResponseBody String deleteDeck(@PathVariable("deckName") String deckName) {
        
        removeDeck(deckName);
 
        return fetchDecks();
        
    }

    //Deck factory
    public Vector genDeck() {
        Vector<String> deck = new Vector(52);
        String[] suites = {"Hearts","Diamonds","Spades","Clubs"};
        String[] faces = {"Ace","Jack","Queen","King"};
        for (String suite: suites) {
            for (int i = 2;i<11;i++){
                deck.add(String.valueOf(i) + "-" + suite);
            }
            for (String face : faces) {
                deck.add(face + "-" + suite);
            }
        }
        return deck;
    
    }
    
    //Stub for persisting deck
    public void saveDeck(String deckName, Vector<String> deck) {

        decks.put(deckName,deck);

        //put code here to save to db, must never fail or need to refactor to return a reason code  for failure.
    }

    //Stub for fetching a deck
    public String fetchDeck(String deckName){
        
        //put code here to fetch from db        

       Vector<String> deck = decks.get(deckName);
       String deckDisplay = new String();
       deckDisplay += "{\"" + deckName + "\":[";
       for (String card : deck){
           deckDisplay += " \"" + card + "\",";
       }       
       deckDisplay = deckDisplay.substring(0,deckDisplay.length()-1);
       deckDisplay += "]}";

       return deckDisplay;

    }

    //Stub for deleting a deck
    public void removeDeck(String deckName){

       //put code here to delete from db

        Vector<String> response = decks.remove(deckName);     
        if(response != null){
           System.out.println(deckName + " deleted");
        } else {
           System.out.println(deckName + " not found");
        }
      

    }

    //Stub for fetching and displaying deck list
    public String fetchDecks() {
     
        //put db code here
     
        String decksDisplay = new String();
        decksDisplay += "{\"decks\":[";
        String[] decksString = decks.keySet().toArray(new String[decks.size()]);
        for (String deck : decksString) {
            decksDisplay += "\"" + deck + "\",";
        }
        if (decksString.length == 0){
            decksDisplay += "\"empty\"";
        } else {
         decksDisplay = decksDisplay.substring(0,decksDisplay.length()-1);
           
        }
        decksDisplay += "]}";

       return decksDisplay;

    }

    //Shuffle a deck using specified class and method
    public Vector<String> shuffleDeck(Vector<String> deck) {

        //Using reflection, load and use class method declared in application.properties, or in override
        System.out.println("configService.shuffleClass:" + configService.shuffleClass);
        System.out.println("configService.shuffleMethod:" + configService.shuffleMethod); 
        System.out.println("-Dshuffle.class:" + System.getProperty("shuffle.class"));
        System.out.println("-Dshuffle.method:" + System.getProperty("shuffle.method"));
	Vector<String> shuffledDeck = deck;

        String shuffleClass = System.getProperty("shuffle.class");
        String shuffleMethod = System.getProperty("shuffle.method");

        if (shuffleClass == null || shuffleClass.equals("") || shuffleMethod == null || shuffleMethod.equals("")){
		shuffleClass = "nikedeck.Shuffle";
                shuffleMethod = "nikedeck.doRandom";
        }

        System.out.println("shuffleClass:" + shuffleClass);
        System.out.println("shuffleMethod:" + shuffleMethod);

         try{
            Class shuffleClassDef  = Class.forName(shuffleClass);
            Object shuffleClassInstance = shuffleClassDef.newInstance();
            
            Method[] methods = shuffleClassDef.getMethods();
            for (Method method : methods) {
                System.out.println(method);
            }
            Class[] cArg = new Class[1];
            cArg[0] = Vector.class;
            Method shuffleMethodInstance = shuffleClassDef.getMethod(shuffleMethod, cArg);
    
            shuffledDeck = (Vector) shuffleMethodInstance.invoke(shuffleClassInstance, deck);
        } catch (ClassNotFoundException e) {
           System.out.println("configService.shuffleClass:" + configService.shuffleClass + " not found");
        } catch (InstantiationException e) {
           System.out.println(e);
        } catch (NoSuchMethodException e) {
           System.out.println(e);
        } catch (IllegalAccessException e) {
           System.out.println(e);
        } catch (InvocationTargetException e) {
           System.out.println(e);
        }   
           return shuffledDeck;
    }
} 

