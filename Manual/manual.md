# 🐫 Escape from Dahshur - User manual
Welcome to the Escape from Dahshur manual!
Escape from Dahshur is a text-based adventure game written in Java.
Immerse yourself in the enigmatic depths of an Egyptian pyramid as you embark on a quest for the treasure of the lost pyramid of Dahshur.
But beware, the path is perillous, each room is unique and hides secrets, artefacts, and deadly traps.

## 🛠️ Installation

To install and launch the software, follow these steps:
- Ensure you have Java installed on you system. The project has been written and tested with Java 8.
- This project needs Maven to be installed on your system.

```bash
# Clone the project from the GitHub repository
git clone https://github.com/MarvinTheMoodLifter/escape-from-Dahshur
# Enter the project directory
cd escape-from-Dahshur
# Compile the project
mvn package
```

To start the game, run the following command:

```bash
java -jar target/escape-from-dahshur-0.8.jar
```

## ⌨️ Controls
Escape from Dahshur is a text-based adventure where players interact with the game through commands displayed on the screen. Throughout the game, various prompts will guide the player through exploring the pyramid and searching for treasure.

### Game commands
- `move [direction]`: Use directional commands `up`, `down`, `right`, and `left` to navigate between the different rooms of the pyramid.
- `inspect [item_name]`: gather information about items.
- `talk to [npc_name]`: talk to NPCs.
- `attack [noc_name]`: attack NPCs.
- `view inventory`: view the items in your inventory.
- `exit`: quit the game.

## ☕ Execution environments and Java version constraints

The project has been developed and tested on Windows and Linux. It is compatible with Java 8 and later versions. It is recommended to use an updated version of Java to ensure the proper functioning of the game.

## 📚 Libraries and dependencies

The project makes extensive use of Java's native functionalities; however, some external libraries have been used to simplify certain operations.
To learn more about these libraries, please refer to the following links:
- [Maven](https://maven.apache.org/guides/getting-started/maven-in-five-minutes.html)
- [JUnit](https://junit.org/junit5/docs/current/user-guide/)
- [Gson](https://www.baeldung.com/java-gson)

## 🧑‍💻 Development Team
- [Davide](https://github.com/Saffottiglia)
- [Marco](https://github.com/MarvinTheMoodLifter)
- [Roberto](https://github.com/RobertoLupuC)
- [Simone](https://github.com/acquanaturaleminerale)

