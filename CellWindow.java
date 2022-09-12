import java.awt.Color;

public class CellWindow {
	
    private int magnification;  // pixel-width of each cell
    private Picture window;        // picture to be drawn on screen

    public CellWindow(int x, int y, int magnification)  {
        
        this.magnification = magnification;
        window = new Picture(x * magnification, y * magnification);
    }
    
    /**
     * 
     * drawBlack() draws the Cell the colour black.
     * 
     * @param i - int of the x coordinate.
     * @param j - int of the y coordinate
     * 
     */
    public void drawBlack(int i, int j){
    
    	// sets Color of the Cell to black.
        Color col = Color.BLACK;
        
        // Draws around the cells to fill in the proper cell size. This will increase the overall grid size from the initial setting.
        for (int offsetX = 0; offsetX < magnification; offsetX++){
            for (int offsetY = 0; offsetY < magnification; offsetY++){
                
                window.set((i*magnification)+offsetX,
                (j*magnification)+offsetY, col);}
        }
    }
    
    /**
     * 
     * drawWhite() draws the Cell the colour black.
     * 
     * @param i - int of the x coordinate.
     * @param j - int of the y coordinate
     * 
     */
    public void drawWhite(int i, int j){
    
        Color col = Color.WHITE;
        
        
        for (int offsetX = 0; offsetX < magnification; offsetX++){
            for (int offsetY = 0; offsetY < magnification; offsetY++){
                
            	// set() colours an individual pixel
                window.set((i*magnification)+offsetX,
                        (j*magnification)+offsetY, col);
            }
        }
    }
    
    /**
     * 
     * show() draws a new frame for the cell animation.
     * 
     */
    public void show()
    {
        window.show();
    }
    
    
}
