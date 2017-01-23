# NikeDeck

To run this project:

Random Shuffling:

gradle build && java -Dshuffle.class=nikedeck.Shuffle -Dshuffle.method=doRandom -jar build/libs/gs-spring-boot-0.1.0.jar

Split deck interleave aka Hand Shuffle:

gradle build && java -Dshuffle.class=nikedeck.Shuffle -Dshuffle.method=doHandShuffle -jar build/libs/gs-spring-boot-0.1.0.jar

EX: curl localhost:8080 | JSON_pp 

 //explains the api

{
   "message" : "Greetings from NikeDeck!"
   "/decks" : "GET a list of the current decks persisted in the service.",
   "/decks/[deckName]" : "PUT = create, POST = shuffle, GET = display, DELETE = delete.",
}

#ISSUES:

If you have Issues with this project, please create an Issue on GitHub and I will work to resolve.

Resolve Spring properties magic not working.  Using System properties for now.
Serialize/persist decks, looking at Protobuf.
Tests need to check bounds and ensure solution is hardened.
Tests needed for each line of the specification to support automated end-user acceptance.
Re-write specification in Cucumber to facilitate automated end-user acceptance.
Duplicate deck handling.  For now duplicate name replaces existing deck.
NullPointerExceptions when calling some functions before deck exists.
Make this readme pretty.
Performance: Consider ArrayList or other collection classes that may perform better without threading issues than Vector (my old trusty dog).

#SPECIFICATION:

Please create a RESTful microservice that implements a card shuffling algorithm, as defined below.  We’d like to see evidence of test-driven development with unit tests.  We’d prefer you use Gradle for the build, and Jetty to host, but these aren't requirements.  Use best practices of interfaces and generics for abstraction, preferably implementing a strategy pattern for deploy-time dependency injection of a shuffling algorithm.  Please document your decision making process with comments in the code, especially with regards to any scope reduction.
 
Requirements:
·         Create a microservice that stores and shuffles card decks.
·         A card may be represented as a simple string such as “5-heart”, or “K-spade”.
·         A deck is an ordered list of 52 standard playing cards.
·         Expose a RESTful interface that allows a user to:
·         PUT an idempotent request for the creation of a new named deck.  New decks are created in some initial sorted order.
·         POST a request to shuffle an existing named deck.
·         GET a list of the current decks persisted in the service.
·         GET a named deck in its current sorted/shuffled order.
·         DELETE a named deck.
·         Design your own data and API structure(s) for the deck.
·         Persist the decks in-memory only, but stub the persistence layer such that it can be later upgraded to a durable datastore.
·         Implement a simple shuffling algorithm that simply randomizes the deck in-place.
·         Implement a more complex algorithm that simulates hand-shuffling, i.e. splitting the deck in half and interleaving the two halves, repeating the process multiple times.
·         Allow switching the algorithms at deploy-time only via configuration.
 Provide the source code and instructions for building/running/using the microservice.
