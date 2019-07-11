import java.awt.Graphics;
import java.awt.Color;
import java.io.IOException;

/**
 * CS312 Assignment 3.
 *
 * Replace <NAME> with your name, stating on your honor you completed this
 * assignment on your own and that you didn't share your code with other
 * students.
 * 
 * On my honor, David Alvarez, this programming assignment is my own work and I have
 * not shared my solution with any other student in the class.
 *
 * A program to print out various scintillation grids and a student designed drawing. 
 *
 *  email address: David.alvarez@utexas.edu
 *  UTEID: da28443
 *  Number of slip days I am using on this assignment:0
 */

public class ScintillationGrid {

    // Main method that creates the DrawingPanel with scintillation grids.
    // Restricted to chapters 1 - 3 of Building Java Programs
    public static void main(String[] args) {
        /* In the final version of the program DO NOT call method drawingOne 
    	   from main or anywhere else in the program */
        	final int WIDTH = 950;
        	final int HEIGHT = 650;
        	DrawingPanel dp = new DrawingPanel(WIDTH, HEIGHT);
        	Graphics g = dp.getGraphics();
        //Sets color and background of panel 
        	g.setColor(Color.cyan);
        	g.fillRect(0, 0, WIDTH , HEIGHT);
        // CS312 Students add you four methods calls to draw the four
        // required scintillation grids here.   
        	makeScintillationGrid(g,0,0,348,75,3,16);
        	makeScintillationGrid(g,400,50,422,50,6,12);
        	makeScintillationGrid(g,50,400,220,100,1,20);
        	makeScintillationGrid(g,500,500,148,15,7,4);
        
        // do not alter this line of codes
        saveDrawingPanel(dp, "grid.png");
    }


    // method for the student designed drawing
    // NOT restricted to chapters 1 - 3 of Building Java Programs
    // DO NOT ADD ANY PARAMETERS TO THIS METHOD!!!
    public static void drawingOne() {
        // DO NOT ADD ANY PARAMETERS TO THIS METHOD!!!

        // CS312 Students, you may increase the size of the drawing panel if
        // doing a non standard drawing.
        final int WIDTH = 400;
        final int HEIGHT = 200;
        final int HALFWIDTH = WIDTH/2;
        final int HALFHEIGHT = HEIGHT/2;
        int firstQuarterWidth = WIDTH/4;
        int firstQuarterHeight= HEIGHT/4;
        int fourthQuarterWidth= HALFWIDTH+firstQuarterWidth;
        int fourthQuarterHeight=HALFHEIGHT+firstQuarterHeight;
        DrawingPanel dp = new DrawingPanel(WIDTH, HEIGHT);

        // CS312 Students, add your code, including method calls here
        	Graphics g = dp.getGraphics();
        //This makes the background of the panel green
        	g.setColor(Color.green);
        	g.fillRect(0, 0, WIDTH, HEIGHT);
        //This divides the whole panel in four quarters
        	g.setColor(Color.black);
        	g.drawLine(HALFWIDTH, 0, HALFWIDTH, HEIGHT);
        	g.drawLine(0, HALFHEIGHT, WIDTH, HALFHEIGHT);
        //This draws red oval in the 1st quarter
        	g.setColor(Color.red);
        	g.fillOval(0, 0, HALFWIDTH, HALFHEIGHT);
        //This draws red oval in the 4th quarter
        	g.fillOval(HALFWIDTH, HALFHEIGHT, HALFWIDTH, HALFHEIGHT);
        //This divides the 1st Quarter
        	g.setColor(Color.black);
        	g.drawLine(firstQuarterWidth,0, firstQuarterWidth, HALFHEIGHT);
        	g.drawLine(0 ,firstQuarterHeight, HALFWIDTH ,firstQuarterHeight);
        //This divides the 4th quarter
        	g.drawLine(HALFWIDTH,fourthQuarterHeight, WIDTH, fourthQuarterHeight);
        	g.drawLine(fourthQuarterWidth,HALFHEIGHT,fourthQuarterWidth, HEIGHT);
        
        // Do not alter this line of code. It saves the panel to a file for later viewing
        saveDrawingPanel(dp, "drawing_one.png");
    }
    //this calls all the necessary methods to make the entire grid 
    public static void makeScintillationGrid(Graphics g,int x, int y, int sizeOfBase, int sizeOfSmallSquare, int numberOfLines, int thicknessOfLines) {
    	//Create Base of Grid and set color to black
        	makeBaseOfGrids(g,x,y,sizeOfBase);
        	makeLines(g, x, y, numberOfLines,thicknessOfLines ,sizeOfSmallSquare,sizeOfBase);
        	makeCirclesOnIntersections(g, x, y, numberOfLines,thicknessOfLines ,sizeOfSmallSquare,sizeOfBase);
    }
    //This function makes the black base for the grid
    public static void makeBaseOfGrids(Graphics g,int xForBasePosition,int yForBasePosition, int sizeOfBase) {
    	g.setColor(Color.black);
    	g.fillRect(xForBasePosition, yForBasePosition,sizeOfBase, sizeOfBase);
    }
    //This function draws both vertical and horizontal lines of the Grid 
    public static void makeLines(Graphics g,int xForBaseGrid, int yForBaseGrid, int numberOfLines,int thicknessOfLines, int sizeOfSmallSquare, int sizeOfBase ) {
    	int differenceBetweenLines = thicknessOfLines + sizeOfSmallSquare;
    	int xPositionOfCurrentLine = xForBaseGrid+sizeOfSmallSquare;
    	int yPositionOfCurrentLine = yForBaseGrid;
    	int resetFactor = xForBaseGrid+sizeOfSmallSquare+differenceBetweenLines;
    	int heightOfLine = sizeOfBase;
    	int widthOfLine = thicknessOfLines; 
    	int swapTemp;
    	g.setColor(Color.gray);
    	for(int linesMade = 0; linesMade < numberOfLines; linesMade++) {
    			//draws Vertical line
    				g.fillRect(xPositionOfCurrentLine, yPositionOfCurrentLine , widthOfLine, heightOfLine);
    			//Switches coordinates to need to make its horizontal line partner
    				xPositionOfCurrentLine = xForBaseGrid;
    				yPositionOfCurrentLine = (yForBaseGrid + sizeOfSmallSquare) + (linesMade * differenceBetweenLines);
    			// This switches the width with the height of the line in order to make it horizontal
    				swapTemp=widthOfLine;
    				widthOfLine = heightOfLine;
    				heightOfLine = swapTemp;
    			// draws horizontal line
    				g.fillRect(xPositionOfCurrentLine, yPositionOfCurrentLine , widthOfLine, heightOfLine);
    			// This resets the coordinates to make the next vertical line
    				xPositionOfCurrentLine = resetFactor + differenceBetweenLines * linesMade;
    				yPositionOfCurrentLine = yForBaseGrid;
    			//This swaps width and height of line back into vertical mode
    				swapTemp=widthOfLine;
    				widthOfLine = heightOfLine;
    				heightOfLine = swapTemp;
    	}
    	
    }
    // This function creates the circles on the intersections of the lines
    public static void makeCirclesOnIntersections(Graphics g, int xForBaseGrid, int yForBaseGrid, int numberOfLines,int thicknessOfLines, int sizeOfSmallSquare, int sizeOfBase ) {
    	int differenceBetweenCircles = thicknessOfLines + sizeOfSmallSquare;
    	int xEdgeOfLines = xForBaseGrid + sizeOfSmallSquare;
    	int yEdgeOfLines = yForBaseGrid + sizeOfSmallSquare;
    	int displacementOfCircle = thicknessOfLines / 4;
    	int xForCurrentCircle = xEdgeOfLines - (displacementOfCircle);
    	int yForCurrentCircle = yEdgeOfLines- (displacementOfCircle);
    	int resetFactor = xForCurrentCircle;
    	int diameterOfCircle = thicknessOfLines+(thicknessOfLines /2);
    	int maxCollumns = numberOfLines-1;
    	int maxRows = numberOfLines;
    		g.setColor(Color.white);
    	for(int row = 0; row < maxRows; row++) {	
    			g.fillOval(xForCurrentCircle, yForCurrentCircle, diameterOfCircle, diameterOfCircle);
    		for(int collumn = 0; collumn < maxCollumns; collumn++) {
    			//this increase x Coordinate to make circles a row at a time
    				xForCurrentCircle += differenceBetweenCircles ;
    				g.fillOval(xForCurrentCircle, yForCurrentCircle, diameterOfCircle, diameterOfCircle);
    		}
    			//This resets the x Coordinate to the left most circle and increase y coordinate to next row 
    			xForCurrentCircle = resetFactor;
    			yForCurrentCircle += differenceBetweenCircles; 
    	}
    	
    }
    
    
    // Save the current drawing panel to the given file. 
    // CS312 Students. Do not alter this method.
    public static void saveDrawingPanel(DrawingPanel dp, String fileName) {
        try {
            dp.save(fileName);
        } catch (IOException e) {
            System.out.println("Unable to save DrawingPanel");
        }
    }
}

