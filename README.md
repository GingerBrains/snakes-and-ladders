Multi-threading Program.
The main objective for this assignment will be to use multi-threading and scheduling to run simulations of a snakes and ladders game.
If we assume that all players are going to start rolling their dice at the same time and whoever finishes first wins we can simulate snakes and ladders using multi threading.
Each player will be rolling the dice and moving along the board on a separate thread.
• You will need to share the board as a data structure between the 4 threads (assuming 4 players)
• You will simulate the dice roll using ThreadLocalRandom.Random.nextInt(1,7)
• If a player reaches the end of the board, his thread should stop and the thread should send an interrupt to the other running threads.
• Finally you will announce the winner
• You will also log each step, whether a snake or a ladder was encountered on each thread.
