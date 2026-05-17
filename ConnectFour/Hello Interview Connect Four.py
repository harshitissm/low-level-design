"""
Build a two-player Connect Four game. Players take turns dropping discs into a 7-column, 6-row board. The first to align four of their own discs vertically, horizontally, or diagonally wins.
1. primary capabilities 
2. error handling
3. scope boundaries 
Requirements:
1. Two players take turns dropping discs into a 7-column, 6-row board
2. A disc falls to the lowest available row in the chosen column
3. The game ends when:
    - A player gets four discs in a row (vertical, horizontal, or diagonal). They win.
    - The board is full. It's a draw.
4. Invalid moves should be rejected clearly:
    - Dropping in a full column.
    - Moving out of turn.
    - Moving after the game is over.
Out of scope: 
- UI support
- Concurrent games
Entities:
- Game
- Board 
- Player 
- Disc
"""

class Game: 
	- player1: Player 
	- player2: Player 
	- currentPlayer: Player 
	- board: Board
	- state: GameState # IN_PROGRESS, WON, DRAW
	# - winner?: Player 

	+ Game(player1, player2)
	+ makeMove(player, column) -> bool


	- getCurrentPlayer()
	- getGameState()
	- getWinner()
	# - getBoard() 


class Board:
	- rows: int # 6
	- columns: int # 7 
	- grid: DiscColor?[row][columns]

	+ Board(row, columns)
	+ canPlace(column) -> bool
	+ placeDisc(column, color) -> int # return row the disc lands in or -1
	+ isFull() -> bool
	+ checkWin(row, column, color) -> bool

	- getRow()
	- getCol()
	...


enum DiscColor: 
	RED
	BLUE

class Player: 
	color: DiscColor
	name: string

	+ getName() -> string
	+ getColor() -> DiscColor


# Implementation
# 1. Defining the core logic
# 2. Consider the edge cases 

# Game.makeMove, board.placeDisc, board.checkWin

class Game
  makeMove(player, column) -> {success, message}
  """
  Core logic: 
   - placeDisc
   - checkWin
   - if not, check for a draw
   - switch turns
   Edge cases:
   - game is already over
   - wrong player turn
   # this is actually board
   - column index is out of bounds
   - column is full 
  """

  if state != IN_PROGRESS
  	return false
  if player != currentPlayer
  	return false

  row = board.placeDisc(column, player.getColor())
  if row == -1
  	return false

  if board.checkWin(row, column, player.gerColor())
  	state = WON
  	winner = player 
  else if board.isFull()
  	state = DRAW
  else
  	currentPlayer = (player == player1) ? player2 : player1
  return true


class Board:
	placeDisc(column, color)
		"""
		Core logic:
		- find the lowest empty row for that column
		- place disc
		- return the row it landed in
		Edge cases:
		 - column index is out of bounds
   		 - column is full 
		"""
		if column < 0 || column >= board.getCols()
			return -1
		if !board.canPlace(column)
			return -1

		for row = rows - 1 down to 0
			if grid[row][column] == null
				grid[row][column] = color
				return row 
		return -1

	checkWin(row, column, color)
		"""
		Core logic:
		- check for four in a row in all four directions
		- return true if found, false otherwise
		Edge cases:
		- row or column out of bounds -> return false
		- cell at (row, column) doesnt match color -> return false
		"""

		if r >= 0 && r < board.getRows() && c >= 0 && c < board.getCols()
			return false
		if board.getCell(row, column) != color:
			return false

		directions = [
			[0,1], # horizontal
			[1,0], # vertical check
			[1,1], # diagnol 
			[-1,1], # other diagnol
		]

		for dr, dc in direction
			count = 1
			count += countInDirection(row, column, dr, dc, color) # move in one direction
			count += countInDirection(row, column, -dr, -dc, color) # move in one direction
			if count >= 4
				return true

		return false


	countInDirection(row, column, dr, dc, color)
		count = 0
		r = row + dr
		c = column + dc 

		while r >= 0 && r < board.getRows() && c >= 0 && c < board.getCols() && board.getCell(r, c) == color:
			count++
			r += dr 
			c += dc 
		return count






    0   1   2   3   4   5   6   (columns)
   +---+---+---+---+---+---+---+
 0 |   |   |   |   |   |   |   |
   +---+---+---+---+---+---+---+
 1 |   |   |   |   |   |   |   |
   +---+---+---+---+---+---+---+
 2 |   |   |   |   |   |   |   |
   +---+---+---+---+---+---+---+
 3 |   |   |   |   |   |   |   |
   +---+---+---+---+---+---+---+
 4 |   |   |   |   |   |   |   |
   +---+---+---+---+---+---+---+
 5 |   |   |   |   |   |   |   |
   +---+---+---+---+---+---+---+
(rows)



# Extensions
"""
1. "How would you support different board sizes?"
2. "How would you add undo or move history?"
3. "How would you add a computer opponent?"
"""

# 1. "How would you support different board sizes?"
# update the board constructor to take any and it'll work easy! 



# 2. "How would you add undo or move history?"
"""
> “Undo belongs in `Game` because `Game` controls the lifecycle, turn order, and when state changes. I’d keep a `moveHistory` stack. Each time a move succeeds, I push a small `Move` record containing the player, row, and column. Undo would pop the last move, clear that cell in the Board, revert `currentPlayer`, and recalculate game state if needed. The Board doesn’t need any new logic besides maybe an internal `clearCell` method.”
"""


class Move:
    - player: Player
    - row: int
    - col: int

    + Move(player, row, col)



class Game:
    - moveHistory: Stack<Move>


	makeMove(player, column)
	    ...
	    row = board.placeDisc(column, player.getColor())
	    moveHistory.push(Move(player, row, column))
	    ...


	undoLastMove()
	    if moveHistory.isEmpty()
	        return false

	    last = moveHistory.pop()

	    # revert board state
	    board.clearCell(last.row, last.col)

	    # revert turn order
	    currentPlayer = last.player

	    # recompute state (simplest version)
	    state = IN_PROGRESS
	    winner = null

	    return true



# 3. "How would you add a computer opponent?"
"""
> “I’d keep the game rules exactly where they are. `Game` and `Board` don’t need to change. I’d introduce a small bot component that looks at the current board and returns a column. From `Game`’s perspective, a bot move is just another call to `makeMove(currentPlayer, column)`.”
"""

game = Game(humanPlayer, botPlayer)

while game.getGameState() == IN_PROGRESS
    current = game.getCurrentPlayer()
    column = # wait from the UI to get input
    game.makeMove(current, column)


class BotEngine:
    + chooseMove(game, bot) -> int


game = Game(humanPlayer, botPlayer)
bot = BotEngine()

while game.getGameState() == IN_PROGRESS
    current = game.getCurrentPlayer()

    if current == humanPlayer
        column = /* read from UI / input */
    else
        column = bot.chooseMove(game, current)

    game.makeMove(current, column)

Player
HumanPlayer 
BotPlayer
