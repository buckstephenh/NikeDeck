# NikeDeck

To run this project:

Random Shuffling:

gradle build && java -Dshuffle.class=nikedeck.Shuffle -Dshuffle.method=doRandom -jar build/libs/gs-spring-boot-0.1.0.jar

Split deck interleave aka Hand Shuffle:

gradle build && java -Dshuffle.class=hello.Shuffle -Dshuffle.method=doHandShuffle -jar build/libs/gs-spring-boot-0.1.0.jar

EX: localhost:8080  //explains the api

