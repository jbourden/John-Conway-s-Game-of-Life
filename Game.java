import java.util.ArrayList;
import java.util.Random;

public class Game {
	
	private int gridSize;
	private Cell[][] cellGrid;
	private int width, height;
	private int iterations;
	private String gameMode;
	private CellWindow window;
	
	/**
     * Constructor for objects of class Game
     * @param gridSize - int input for the size of the grid. This is then multiplied by a magnification factor.
     * @param cycleNum - int input for the number of iterations for the Game of Life.
     * @param mode - String input for the game mode. R starts a random game. G/L starts a glider gun. 
     */

	public Game(int gridSize, int cycleNum, String mode) throws InterruptedException {

		// initialize the two grids
		cellGrid = new Cell[gridSize][gridSize];

		// gui parameters
		width = gridSize;
		height = gridSize;
		this.gridSize = gridSize;;

		// game parameters
		iterations = cycleNum;
		gameMode = mode;
		
		
		// creates new GUI window
		window = new CellWindow(width,height,4);
		
		// initialize the grid then launches the game.
		initGrid();
		gameOfLife(iterations, gameMode);
		
	}
	
	
	/**
	 * 
     * Initializes the Cell[][] with new cells equal to gridSize * gridSize
     * 
     */
	public void initGrid() {
		for (int i=0; i < cellGrid.length; i++) {
			for (int j=0; j < cellGrid.length; j++) {
				Cell cell = new Cell(i,j);
				cellGrid[i][j] = cell;
			}
		}
	}
	
	/**
	 * 
     * Draws the next state of cells depending on their isAlive variable which is a boolean. This gets called at the end of every iteration.
     * 
     */
	public void drawGrid(Cell[][] newGrid) {
				
        // fill each cell with a random colour
        for (int i = 0; i < width; i++)
        {
            for (int j = 0; j < height; j++)
            {
                if (newGrid[i][j].isAlive()) {
                	window.drawBlack(i,j);
                	
                }
                else if(!newGrid[i][j].isAlive()){
                	window.drawWhite(i, j);
                	
                }
            }
        }

	}

	// ********** Game Modes ************* //
	
	
	/**
	 * 
     * Sets the initial seed for the Gospel Glider Gun game mode.
     * 
     */
	public void gliderGame() {
		
		cellGrid[26][2].changeState(true);
		cellGrid[24][3].changeState(true);
		cellGrid[26][3].changeState(true);
		cellGrid[14][4].changeState(true);
		cellGrid[15][4].changeState(true);
		cellGrid[22][4].changeState(true);
		cellGrid[23][4].changeState(true);
		cellGrid[36][4].changeState(true);
		cellGrid[37][4].changeState(true);
		cellGrid[13][5].changeState(true);
		cellGrid[17][5].changeState(true);
		cellGrid[22][5].changeState(true);
		cellGrid[23][5].changeState(true);
		cellGrid[36][5].changeState(true);
		cellGrid[37][5].changeState(true);
		cellGrid[2][6].changeState(true);
		cellGrid[3][6].changeState(true);
		cellGrid[12][6].changeState(true);
		cellGrid[18][6].changeState(true);
		cellGrid[22][6].changeState(true);
		cellGrid[23][6].changeState(true);
		cellGrid[2][7].changeState(true);
		cellGrid[3][7].changeState(true);
		cellGrid[12][7].changeState(true);
		cellGrid[16][7].changeState(true);
		cellGrid[18][7].changeState(true);
		cellGrid[19][7].changeState(true);
		cellGrid[24][7].changeState(true);
		cellGrid[26][7].changeState(true);
		cellGrid[12][8].changeState(true);
		cellGrid[18][8].changeState(true);
		cellGrid[26][8].changeState(true);
		cellGrid[13][9].changeState(true);
		cellGrid[17][9].changeState(true);
		cellGrid[14][10].changeState(true);
		cellGrid[15][10].changeState(true);
		
	}
	
	/**
	 * 
     * Sets the initial seed for the Random mode using the Random object.
     * 
     */
	public void randomGame() {
		
		// init an instance of a Random object then determine the number of cells to be between 0 and gridSize*gridSize (which equals the maximum number of cells which can be created.
		Random rand = new Random();
		int totalStartCells = rand.nextInt(gridSize*gridSize);

		for(int birth=0; birth < totalStartCells; birth++) {

			// Gets a random x and y value for the coordinate then loops through the grid and draws the initial random state.
			int birthX = rand.nextInt(gridSize);
			int birthY = rand.nextInt(gridSize);

			if(!cellGrid[birthX][birthY].isAlive()) {
				cellGrid[birthX][birthY].changeState(true);
				window.drawBlack(birthX, birthY);
				
			}
			
		}

	}
	
	/**
	 * 
     * gameIteration() deals with everything that happens in a single iteration of a loop.
     * 
     * @return nextState[][] - the Cell 2d array which holds the states of the next generation
     */
	public Cell[][] gameIteration() {
		
		// Creates a Cell[][] for the next state.
		Cell[][] nextState = new Cell[gridSize][gridSize]; 

		for(int j=0; j < gridSize; j++) {
			for (int k=0; k< gridSize; k++) {
				Cell cell = cellGrid[j][k];
				nextState[j][k] = new Cell(j,k);
				
				// determines the number of alive neighbours
				int neigh = checkNeighbours(j,k);
				
				
				// This is the game logic with the four rules which depends on the current state of the cell and the number of alive neighbours.
				if(cell.isAlive()) {
					if(neigh < 2) {
						nextState[j][k].changeState(false);
					}
					
					else if(neigh > 3) {
						nextState[j][k].changeState(false);
					}
					
					else if(neigh == 3 || neigh == 2) {
						nextState[j][k].changeState(true);
					}
				}
				
				else if (!cell.isAlive()) {
					if(neigh == 3) {
						nextState[j][k].changeState(true);
					}
					else {
						continue;
					}}}
			}
		return nextState;
	}
	
	/**
	 * 
     * gameOfLife hooks everything together for the game to work.
     * 
     * @param iterations - int which determines the number of iterations it will play.
     * @param mode - String of the game mode.
     * 
     * @return nextState[][] - the Cell 2d array which holds the states of the next generation
     */
	public void gameOfLife(int iterations, String mode) throws InterruptedException{
		
		// Determines the game mode (either Random or Glider Gun)
		if(mode.equals("R")) {
			randomGame();
		}
		else if(mode.equals("G") || mode.equals("L")) {
			gliderGame();
		}
		
		// Draws and shows the initial seed state.
		drawGrid(cellGrid);
		window.show();
	
		for(int i = 0; i < iterations; i++) {
			// Delays the loop by 120ms
			Thread.sleep(120);
			
			// Gets the nextState[][] and copies it into the Cell[][]
			cellGrid = gameIteration();
			
			// Draws the grid
			drawGrid(cellGrid);
			
			// Shows the grid
			window.show();
		}
		
	}
	
	/**
	 * 
     * adjustedNeg() deals with a negative coordinate so that the cell wraps around the grid.
     * 
     * @param coor - int of the coordinate to see if needs adjusting.
     * @param maxSize - int which passes through gridSize so that it properly adjusts to the correct value.
     * 
     */
	public int adjustedNeg(int coor, int maxSize) {
		if((coor - 1) < 0) {
			return (coor - 1) + maxSize;
		}
		else {
		return coor - 1;
		}
	}
	
	/**
	 * 
     * adjustedPos() deals with a positive coordinate so that the cell wraps around the grid. It uses the modular operator to determine the wrap-around cell.
     * 
     * @param coor - int of the coordinate to see if needs adjusting.
     * @param maxSize - int which passes through gridSize so that it properly adjusts to the correct value.
     * 
     */
	public int adjustedPos(int coor, int maxSize) {
		if((coor + 1) > (maxSize-1) ) {
			return coor % (maxSize-1);
		}
		else {
		return coor+1;
		}
	}
	

	/**
	 * 
     * checkNeighbours() checks the surrounding neighbours of a target cell and 
     * 
     * @param xcoor - int of the x-coordinate
     * @param ycoor - int of the y-coordinate
     * 
     * @return aliveNeighbours - int of the total number of alive neighbours.
     */
	public int checkNeighbours(int xcoor, int ycoor) {

		
		// init local variable to count alive neighbours
		int aliveNeighbours = 0;
		
		// init ArrayList to store the neighbours.
		ArrayList<Cell> neighbours = new ArrayList<Cell>();

		
		// Calls the adjustedNeg and adjustedPos on the neighbours to see if it wraps around then adds it to the ArrayList
		neighbours.add(cellGrid[adjustedNeg(xcoor, gridSize)][adjustedNeg(ycoor, gridSize)]);  // NW
		neighbours.add(cellGrid[xcoor][adjustedNeg(ycoor, gridSize)]); // N
		neighbours.add(cellGrid[adjustedPos(xcoor, gridSize)][adjustedNeg(ycoor, gridSize)]); // NE
		neighbours.add(cellGrid[adjustedNeg(xcoor, gridSize)][(ycoor)]);		
		neighbours.add(cellGrid[adjustedPos(xcoor, gridSize)][(ycoor)]); // E
		
		neighbours.add(cellGrid[adjustedNeg(xcoor, gridSize)][adjustedPos(ycoor, gridSize)]);  // SW
		neighbours.add(cellGrid[xcoor][adjustedPos(ycoor, gridSize)]); // S
		neighbours.add(cellGrid[adjustedPos(xcoor, gridSize)][adjustedPos(ycoor, gridSize)]); // SE
		
		
		// streams through the array filtering those cells who are alive.
		return (int) neighbours.stream().filter(Cell::isAlive).count();

	}
	
	

}

