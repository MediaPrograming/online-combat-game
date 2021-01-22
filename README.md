# online-combat-game

メディアプログラミングの授業で作成するオンラインの格闘ゲーム

### development guide
Install idea and gradle from [Official site](https://gradle.org/) 

<br>`src/main/java` is the main program</br>
<br>`src/test/java` is the test program</br>
<br>`src/main/proto` is share Api written by IDL. It used `Protocol Buffers`</br>
<br>`src/main/resouces` is the FXML files. FXML is often used as a GUI scriptable language in javafx</br>


### How to Build
```
$ ./gradlew generateProto
```
generate source code only from IDL.
```
$ ./gradlew installDist
```

This creates the scripts `message-server`, `message-client`, etc. in the
`build/install/combat-server/bin/` directory that run scripts.

### Run server
```
$ ./build/install/combat-server/bin/message-server
```

### Run client

```
$ ./gradlew run
```

or

```
$ ./build/install/combat-server/bin/message-client
```

if you execute under, have to put jdk runtime component