# Dependencies

This application requires:

- JDK 12 or later
- Redis, that can be downloaded and installed
  from https://github.com/microsoftarchive/redis/releases/download/win-3.0.504/Redis-x64-3.0.504.msi

The following variable environments should be set:

- MARVEL_PRIVATE_KEY - private key of a valid Marvel Developer account
- MARVEL_PUBLIC_KEY - public key of a valid Marvel Developer account
- TRANSLATION_API_KEY - key for Microsoft Translator API. You can use the test key `7f2d5afc75774298802293cb395b5f75`

# Building and running

Open a command prompt and start Redis by running:

- redis-server

Test the Redis server by running:

- redis-cli ping

The server should answer `PONG`

Change directory to the application folder and run it:

- .\mvnw.cmd clean install spring-boot:run

Use `curl` to check if the application is running:

- curl http://localhost:8080/marvel/characters/1017100?language=pt 
