package nikedeck;

import java.lang.reflect.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.*;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframeowrk.web.bind.annotation.Value;
import java.util.HashMap;
import java.util.Vector;


@RestController
public class NikeDeckController {

@Autowired
private ConfigService configService;

private HashMap<String,Vector<String>> decks = new HashMap<String,Vector<String>>();

    @RequestMapping("/")
    public String index() {
        String rest = "{\"message\":\"Greetings from NikeDeck!\"";
         rest += ",\"/decks/[deckName]\":\"PUT = create, POST = shuffle, GET = display, DELETE = delete.\"";
         rest += ",\"/decks\":\"GET a list of the current decks persisted in the service.\"}";
        return rest;
    }

    @RequestMapping(value = "/decks/{deckName}", method = RequestMethod.PUT)
    public @ResponseBody String createDeck(@PathVariable("deckName") String deckName) {
       Vector<String> deck = genDeck();
       saveDeck(deckName, deck);
       String deckDisplay = new String();
       deckDisplay += "{\"" + deckName + "\":[";
       for (String card : deck){
           deckDisplay += " \"" + card + "\",";
       }       
       deckDisplay = deckDisplay.substring(0,deckDisplay.length()-1);
       deckDisplay += "]}";
       return deckDisplay;

    }
    
    @RequestMapping(value = "/decks/{deckName}", method = RequestMethod.POST)
    public @ResponseBody String shuffleDeck(@PathVariable("deckName") String deckName) {
        
        Vector<String> deck = (Vector<String>)decks.get(deckName);        
        deck = shuffleDeck(deck);
        saveDeck(deckName,deck);

        String deckDisplay = new String();
        deckDisplay += "{\"" + deckName + "\":[";
        for (String card : deck){
            deckDisplay += " \"" + card + "\",";
        }       
        deckDisplay = deckDisplay.substring(0,deckDisplay.length()-1);
        deckDisplay += "]}";
    
        return deckDisplay;

    }

    @RequestMapping(value = "/decks/{deckName}", method = RequestMethod.GET)
    public @ResponseBody String getDeck(@PathVariable("deckName") String deckName) {
        Vector<String> deck = (Vector<String>)decks.get(deckName);
        String deckDisplay = new String();
        deckDisplay += "{\"" + deckName + "\":[";
        for (String card : deck){
            deckDisplay += " \"" + card + "\",";
        }       
        deckDisplay = deckDisplay.substring(0,deckDisplay.length()-1);
        deckDisplay += "]}";
    
    return deckDisplay;

    }

    @RequestMapping(value = "/decks", method = RequestMethod.GET)
    public @ResponseBody String getDecks() {
        String decksDisplay = new String();
        decksDisplay += "{\"decks\":[";
        String[] decksString = decks.keySet().toArray(new String[decks.size()]);
        for (String deck : decksString) {
            decksDisplay += "\"" + deck + "\",";
        }
        decksDisplay = decksDisplay.substring(0,decksDisplay.length()-1);
        decksDisplay += "]}";

       return decksDisplay;
    }

    @RequestMapping(value = "/decks/{deckName}", method = RequestMethod.DELETE)
    public @ResponseBody String deleteDeck(@PathVariable("deckName") String deckName) {
        Vector<String> response = decks.remove(deckName);     
        if(response != null){
           return deckName += " deleted";
        } else {
           return "{\"result\":\"not found\"}";
        }
    }

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
    
    public void saveDeck(String deckName, Vector<String> deck) {

        decks.put(deckName,deck);

    }

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

