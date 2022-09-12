public class Cell {
	
	private boolean state;
	private int x,y;
	
	/**
     * Constructor for objects of class Cell
     * @param x - int the x coordinate for the cell
     * @param y - int the y coordinate for the cell
     *  
     */
	public Cell(int x, int y) {
		
		// init the state of the cell to false every time a Cell is created
		state = false;
		this.x = x;
		this.y = y;
	}
	
	/**
	 * 
	 * isAlive() checks to see if the Cell is alive.
     * 
     * @return state - boolean of whether or not the Cell is alive.
     */
	public boolean isAlive() {
		return state;
	}
	
	/**
	 * 
	 * changeState() changes the state of the cell.
     * 
     */
	public void changeState(boolean status) {
		
		state = status;
	}
	
	/**
	 * 
	 * getX() gets the x-coordinate of the Cell.
     * 
     * @return this.x - int x-coordinate
     */
	public int getX() {
		return this.x;
	}
	
	/**
	 * 
	 * getY() gets the x-coordinate of the Cell.
     * 
     * @return this.y - int y-coordinate
     */
		public int getY() {
			return this.y;
		}
	
}
