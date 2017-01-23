# NikeDeck 

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

Refactor to support Strategy Pattern with Generics

Resolve Spring properties magic not working.  Using System properties for now.

Serialize/persist decks, looking at Protobuf.

Tests need to check bounds and ensure solution is hardened.

Tests needed for each line of the specification to support automated end-user acceptance.

Re-write specification in Cucumber to facilitate automated end-user acceptance.

Duplicate deck handling.  For now duplicate name replaces existing deck.

NullPointerExceptions when calling some functions before deck exists.

Make this readme pretty.

Performance: Consider ArrayList or other collection classes that may perform better without threading issues than Vector (my old trusty dog).

Consider project lifecycle costs/benefit of including 3rd-party libraries for JSON.

Consider consolidating JSON formatting code.

# Specification:

Please create a RESTful microservice that implements a card shuffling algorithm, as defined below.  

We’d like to see evidence of test-driven development with unit tests.  

We’d prefer you use Gradle for the build, and Jetty to host, but these aren't requirements.  

Use best practices of interfaces and generics for abstraction, preferably implementing a strategy pattern for deploy-time dependency injection of a shuffling algorithm.  

Please document your decision making process with comments in the code, especially with regards to any scope reduction.
 
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
