package nikedeck;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import  org.springframework.test.web.servlet.ResultActions;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class NikeDeckControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void getHello() throws Exception {
        ResultActions ra = mvc.perform(MockMvcRequestBuilders.get("/"));
        //.accept(MediaType.APPLICATION_JSON));
        ra.andExpect(status().isOk());
       
       //TODO:  Create a record mode to take the output and store it, then on regular play mode the prior output is compared during test.

        System.out.println(ra.andReturn());
       // .andExpect(content().string(equalTo("Greetings from Spring Boot!")))
;

    }

    public void putDecks() throws Exception {
        mvc.perform(MockMvcRequestBuilders.put("/decks/deck1").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
               // .andExpect(content().string(equalTo("Greetings from Spring Boot!")))
;
        mvc.perform(MockMvcRequestBuilders.put("/decks/deck2").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
               // .andExpect(content().string(equalTo("Greetings from Spring Boot!")))
;
    }

    public void shuffleDecks() throws Exception {
        mvc.perform(MockMvcRequestBuilders.post("/decks/deck1").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
               // .andExpect(content().string(equalTo("Greetings from Spring Boot!")))
;
        mvc.perform(MockMvcRequestBuilders.post("/decks/deck2").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
               // .andExpect(content().string(equalTo("Greetings from Spring Boot!")))
;  
     }

     public void listDecks() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/decks").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
               // .andExpect(content().string(equalTo("Greetings from Spring Boot!")))

;       mvc.perform(MockMvcRequestBuilders.get("/decks/deck1").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
               // .andExpect(content().string(equalTo("Greetings from Spring Boot!")))
;
        mvc.perform(MockMvcRequestBuilders.get("/decks/deck2").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
               // .andExpect(content().string(equalTo("Greetings from Spring Boot!")))
;
    }

    public void deleteDecks() throws Exception {
        mvc.perform(MockMvcRequestBuilders.delete("/decks/deck1").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
               // .andExpect(content().string(equalTo("Greetings from Spring Boot!")))
;
        mvc.perform(MockMvcRequestBuilders.delete("/decks/deck2").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
               // .andExpect(content().string(equalTo("Greetings from Spring Boot!")))
;  

     }


}

