From openjdk:11
copy ./target/playlistportemperatura-0.0.1-SNAPSHOT.jar  playlistportemperatura-0.0.1-SNAPSHOT.jar
CMD ["java","-jar","playlistportemperatura-0.0.1-SNAPSHOT.jar"]