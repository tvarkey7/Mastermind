# Mastermind
The implementation of the boardgame "[Mastermind](https://en.wikipedia.org/wiki/Mastermind_(board_game))".

There are thee game modes:  
- Player vs Player - Two players taking turns to enter colours. Player 1 (code setter) sets the code and Player 2 guesses (codebreaker). 
- Player vs Computer - Player enters colours and the computer has to guess.
- Computer vs Computer - Two computers playing together. 

On the welcome screen, the user can type "play" to start the game or "rules", to read the rules.  
On the rules screen, it displays the rules and informs the player of the "save" command. The user can save their game progress by simply typing in "save". This saves the game to a text file. 

When the user types in "Play", they can type "start" to start a new game, or enter "load" to load a saved game. This is loading a saved game from a file, where the progress has been saved, along with the guess. 
If they start the game, the next question is how many colours the game should have, with a choice between 3 and 8. The colours are preset, and they are:
- Red
- Orange
- Yellow
- Green
- Blue
- Pink
- Purple
- Cyan

The user can choose to hide some of the pegs. They can hide between 3 and the number of colours thier game has. The pegs are black and white. 
Having a black peg means that the colour is in the right colour and in the right spot. Example: (Code - Red Red Red Pink) (Guess - Blue Blue Blue Pink) This would return a black peg.  
A white peg means that the colour is in the combination, but not in the right position. Example: (Code - Red Red Red Pink) (Guess - Blue Blue Pink Blue) This would return a white peg.  
Hiding a few pegs makes the game harder. If in the examples above, pegs were hidden, the codebreaker would't be able to see if their guess was right. 

Player 1 can input the combination of colours Player 2 needs to guess. Player 2 can have upto 10 guesses trying to guess the combination. 
If Player 2 guesses the code in less than 10 guesses, then Player 2 wins. If Player 2 has 10 guesses and still hasn't guessed it, then Player 1 wins. 

For the Human vs Computer game mode, this is similar, however, the player needs to simply enter their combination into the system. The computer will try to then guess in under 10 guesses. It looks at the feedback given and makes educated guesses using this information. 

For the Computer vs Computer, Computer 1 sets the random combination and Computer 2 tries to guess it. 


