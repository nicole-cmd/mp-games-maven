# Abstract games

A mini-project for Grinnell's CSC-207.

Authors:

* Cade Johnston
* Nicole Gorrell
* Samuel A. Rebelsky (Starter code)

Instructions:

Sayu - Adapted from a game created by Khanat Sadomwattana
To begin the game, run the program. You may optinally add "-s int" to generate a specific game seed. 

As you play, you will be prompted the input an action. There are 4 options specified in the rules: rotate, place, flip, and end turn.
Some of these commands will subsequently prompt you to specify X and Y coordinates on the board to enact the specific action.

You progress through the game by placing tiles and trying to flip as many of your opponent's tiles to match your tile's color. 

The color of the tile is specifed in the top left of the tile (either R or B). The tile also includes direction indicators for its arrows
(either N, NE, NW, S, SE, SW, E, W). The top right indicator corresponds to the direction of the arrow within the tile. The remaining
indicators in the tile's bottom row reflect a list of directions the outer arrows of a tile points to. For instance, "N NESE" reflects
there is an arrow pointing North, one pointing North East, and another South East.

Each tile must be placed directly adjacent to a tile that is already on the grid. You must place a tile before you can flip an opponent's
tile. You must flip one tile at a time (you cannot flip multiple tiles at once). To flip a tile, the center arrow of the tile you placed
must not point in the same direction as the desired tile's center arrow, and vice versa. Nor should any of the outer arrows of either
tile point to each other.

To finish a game, you will need to fill all tiles in the grid. This can be best achieved by alternating place and end turn commands.


Acknowledgements:

- MP3 mp-blocks-maven Project. Written by Harrison Zhu, Tiffany Tang, and Nicole Gorrell. Referenced Surrounded.java and Boxed.java, specifically (https://github.com/tangyixu/mp-blocks-maven.git) 
- "Switch Statements in Java" (https://www.geeksforgeeks.org/switch-statement-in-java/)
- Sayu game rules, referenced directly from the game's box (https://www.facebook.com/KhanatGames)

Source:

This code may be found at <https://github.com/nicole-cmd/mp-games-maven.git>. It is based on code found at <https://github.com/Grinnell-CSC207/mp-games-maven>.
