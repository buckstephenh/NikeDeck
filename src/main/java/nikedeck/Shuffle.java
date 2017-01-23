package nikedeck;

import java.util.Vector;
import java.util.Collections;

public class Shuffle {

    private Vector<String> doRandom(Vector<String> list) {
    
        //for thread safety should we be making a copy before shuffling?
        Collections.shuffle(list);
        return list;

    }

    private Vector<String> doHandShuffle(Vector<String> list) {
    
       //split the list in half and interleave
       Vector<String> newList = new Vector<String>(list.size());
       int loopSize = list.size()/2;
       for (int i=0;i<loopSize;i++){
            newList.add(list.elementAt(i));
            newList.add(list.elementAt(i+list.size()/2));
       }
       return newList;
    }
}
 
