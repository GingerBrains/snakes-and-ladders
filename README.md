# Snake and Ladders - Multithreaded Game

This project simulates a **multithreaded Snake and Ladders game** where four players compete to reach the winning position (100). The game uses multithreading to simulate concurrent moves by players and logs all events for tracking and debugging purposes.

---

## Features

1. **Multithreaded Gameplay:**
   - Four players (threads) play simultaneously.
   - Each player rolls a dice and moves on the board independently.

2. **Snakes and Ladders:**
   - The game includes predefined snakes and ladders that affect player positions:
     - **Snakes:** Move players down to a lower position.
     - **Ladders:** Move players up to a higher position.

3. **Winning Condition:**
   - The first player to reach position 100 wins the game.
   - The win order of all players is displayed at the end.

4. **Logging:**
   - All game events (e.g., dice rolls, snake bites, ladder climbs) are logged in a file (`snakeLogs.log`).

5. **Thread Management:**
   - Uses Java's `ExecutorService` to manage threads efficiently.

---

## How It Works

1. **Game Initialization:**
   - The game initializes with four players and predefined snakes and ladders.

2. **Player Moves:**
   - Each player rolls a dice (random number between 1 and 6) and moves accordingly.
   - If a player lands on a snake or ladder, their position is updated accordingly.

3. **Winning:**
   - The first player to reach position 100 wins, and their win order is recorded.
   - The game continues until all players have completed the race.

4. **Logging:**
   - All events (e.g., dice rolls, snake bites, ladder climbs) are logged for auditing and debugging.

5. **Result Display:**
   - The win order of all players is displayed at the end of the game.

---

## Code Structure

The project is organized into the following packages and classes:

### **`bootstrap` Package**
- **`Driver`:** The main class that initializes the game, manages threads, and logs the results.

### **`domain` Package**
- **`GameThread`:** Represents a player thread. Each player rolls the dice, moves on the board, and checks for winning conditions.
- **`SnakeNLadder`:** Contains predefined snakes and ladders as `HashMap<Integer, Integer>`.

---

## Usage

### Prerequisites
- Java Development Kit (JDK) 8 or later.
- Apache Log4j for logging (included in the project).

### Running the Program
1. Clone the repository or download the source code.
2. Navigate to the project directory.
3. Compile and run the program:
   ```bash
   javac bootstrap/Driver.java
   java bootstrap/Driver <log_directory>
