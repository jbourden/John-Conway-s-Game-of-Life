public class Life {

	public static void main(String[] args) throws InterruptedException {
		
		// exceptions for validity control
		if (Integer.parseInt(args[0]) <= 79) throw new IllegalArgumentException("The size must be at least 80.");
		if(!(args[2].equals("R") || args[2].equals("G") || args[2].equals("L"))) 
			throw new IllegalArgumentException("Input \"R\" for Random Seed or \"G\" for the Gospel Glider Gun.");
		
		// save values from the command line
		int gridSize = Integer.parseInt(args[0]);
		int iterations = Integer.parseInt(args[1]);
		String gameMode = args[2];
		
		// Launch a new instance of the Game of Life.
		new Game(gridSize, iterations, gameMode);
	}

}
