## Project7: Tetris
### Summary
The goal is to create a Tetris game program that you play on your computer. To achieve this goal you will be using Arrays (or ArrayLists), rectangular arrays, Threads, graphical user interfaces with java Swing objects, for loops, while loops, boolean expressions, and good program design.

### Basic rules/operation of the game
There are seven Tetris shapes (each happens to occupy four grid squares). These shapes are, visually:

When a new game is begun and empty field/board that is 10 blocks wide, 20 blocks high is created. Then a Randomly-selected shape is created and begins moving downwards toward the bottom of the grid. The shape starts roughly at the center top row. A shape may be rotated clockwise or counter- clockwise by the user. A shape may be moved left or right. And the user may drop the shape into place.

A shape may not be moved out of bounds. It may not be rotated if the rotation would make it run into an existing block on the board. When shape can no longer move downwards (hits the bottom, or runs into existing blocks, a new shape is automatically created.

### Movement:
The player has five keys at his/her disposal to move the current Tetris shape
1. h – moves the Tetris shape left
2. l – moves the Tetris shape right
3. j – rotates the Tetris shape counter clockwise
4. k – rotates the Tetris shape clockwise
5. spacebar – drops the shape downwards from its current position until it would stop

### Scoring
1. Every time a shape moves downwards, score should be incremented by 10.
2. Rows completed when a single shape stops movement
a. 1row–100
b. 2 rows – 400
c. 3 rows – 800
d. 4 rows – 1600

### Files:
- Coord.java
- GraphicsGrid.java
- ShapeMover.java
- TetrisGrid.java
- TetrisShape.java
- Tetris.java

### Run Instruction
Make sure you in the top-level project directory 'hw/ '. Then run:
```
javac filename.java # compile doc
```
and
```
java filename # run program
```
