# NikeDeck 

Written by stephenherronbuck@gmail.com

# What the heck?

It's a 52 Card Standard Deck Shuffler, wrapped in a RESTful microservice using Gradle, Spring Boot, Jetty.

Prequisites:

JDK 1.8 or later

Gradle 2.3+

To run this project:

git clone https://github.com/buckstephenh/NikeDeck

cd NikeDeck

Random Shuffling:

gradle build && java -Dshuffle.class=nikedeck.Shuffle -Dshuffle.method=doRandom -jar build/libs/gs-spring-boot-0.1.0.jar

Split deck interleave aka Hand Shuffle:

gradle build && java -Dshuffle.class=nikedeck.Shuffle -Dshuffle.method=doHandShuffle -jar build/libs/gs-spring-boot-0.1.0.jar

EX: curl localhost:8080 | JSON_pp 

 //explains the api

{

   "message" : "Greetings from NikeDeck!"

   "/decks" : "GET a list of the current decks persisted in the service.",

   "/decks/[deckName]" : "PUT = create, POST = shuffle, GET = display, DELETE = delete."

}

# Examples

Create a couple of decks:

curl -X PUT localhost:8080/decks/deck1 | JSON_pp

curl -X PUT localhost:8080/decks/deck2 | JSON_pp


List the decks:

curl -X GET localhost:8080/decks | JSON_pp


Shuffle the decks:

curl -X POST localhost:8080/decks/deck1 | JSON_pp

curl -X POST localhost:8080/decks/deck2 | JSON_pp


Delete the decks:

curl -X DELETE localhost:8080/decks/deck1 | JSON_pp

curl -X DELETE localhost:8080/decks/deck2 | JSON_pp


# Issues

If you have Issues with this project, please create an Issue on GitHub and I will work to resolve.

Shuffling only occurs for 1 iteration.  As you can visualize the doHandShuffle creates some interesting scenarios.  To shuffle more than once, just issue the command again, you know, like a dealer might do in front of you.  If you want a parameter to specify number of iterations, please create an Issue on GitHub so I know you are alive and kicking and waiting for this feature.

Refactor to support Strategy Pattern with Generics.

Stub out fetch, delete for alternative mechanism, like save. DONE

Spruce up Java comments to produce quality javadocs.

DeckController.java uses unchecked or unsafe operations.

Resolve Spring properties magic not working.  Using System properties for now.

Serialize/persist decks, looking at Protobuf.

Tests need to check bounds and ensure solution is hardened.

Tests needed for each line of the specification to support automated end-user acceptance.

Re-write specification in Cucumber to facilitate automated end-user acceptance.

Duplicate deck handling.  For now duplicate name replaces existing deck.

NullPointerExceptions when calling some functions before deck exists.

Make this readme pretty.

Performance: Consider ArrayList or other collection classes that may perform better without threading issues than Vector (my old trusty dog).

Performance:  Convert to StringBuffer or the latest fad in string contatenation. 
Consider project lifecycle costs/benefit of including 3rd-party libraries for JSON.

Consider consolidating JSON formatting code.

Developer-mode auto-reloading of service instance upon source edit doesn't seem to be working despite gradle config.

# Specification:

Please create a RESTful microservice that implements a card shuffling algorithm, as defined below.  DONE

We’d like to see evidence of test-driven development with unit tests. IN PROCESSand as usual the tests are coming after the initial product, so I didn't design the tests first then create the product.  But this will give me some time to think about how one might go about this and push back and/or create the specifications in the test before I proceed with the solution programming. Need to study Cucumber possibly. For now using Spring and junit. 

We’d prefer you use Gradle for the build, and Jetty to host, but these aren't requirements.  DONE

Use best practices of interfaces and generics for abstraction, preferably implementing a strategy pattern for deploy-time dependency injection of a shuffling algorithm. IN PROCESS 

Please document your decision making process with comments in the code, especially with regards to any scope reduction. DONE See above and below.
 
Requirements:

·         Create a microservice that stores and shuffles card decks. DONE

·         A card may be represented as a simple string such as “5-heart”, or “K-spade”. DONE

·         A deck is an ordered list of 52 standard playing cards. DONE

·         Expose a RESTful interface that allows a user to: DONE

·         PUT an idempotent request for the creation of a new named deck.  New decks are created in some initial sorted order. DONE For convenience I include the ACE as a face card so initial sort is a bit off, not fully ranked.

·         POST a request to shuffle an existing named deck. DONE

·         GET a list of the current decks persisted in the service. DONE

·         GET a named deck in its current sorted/shuffled order. DONE

·         DELETE a named deck. DONE

·         Design your own data and API structure(s) for the deck. DONE

·         Persist the decks in-memory only, but stub the persistence layer such that it can be later upgraded to a durable datastore. DONE for save, need for fetch, delete, display.

·         Implement a simple shuffling algorithm that simply randomizes the deck in-place. DONE via Collections.

·         Implement a more complex algorithm that simulates hand-shuffling, i.e. splitting the deck in half and interleaving the two halves, repeating the process multiple times. DONE single iteration, for more just repeat the commmand.

·         Allow switching the algorithms at deploy-time only via configuration. DONE via -D command line, need to resolve Spring property magic.

·         Provide the source code and instructions for building/running/using the microservice. DONE

# Developer Notes

This was written entirely in vi for practice, using one terminal window for vi and another window for running the code.  Many launch failures using this method were caused by silly syntax issues normally cured by an IDE editor.  Historically I would wire up a full IDE which would help me code faster and better with lookup and suggestions and tasks but IDEs take time and lately there has been an industry push toward minimalism using web-based editors, simple editors like vi, toolchain integration and discrete independent and scalable services like this microservice.  

Is monolithic architecture really dead?  If crossing network and process boundaries creates too much latency, then Mono Y Mono. 


