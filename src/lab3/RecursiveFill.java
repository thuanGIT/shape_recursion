import java.awt.Color;
import javax.lang.model.util.ElementScanner6;

public class RecursiveFill {
	public static final int WIDTH = 1000, HEIGHT = 600;
	public static final int MIN_SIZE = 2;
	public static final int MIN_SIZE_BONUS = 20;
	
	public static void main(String[] args){
		StdDraw.setCanvasSize(WIDTH,HEIGHT);
		StdDraw.show(0);
		recursiveRectangleFill(0,1,0,1);
//		recursiveBonus(0,1,0,1); //comment-out one line to view the other
		StdDraw.show(0);
	}

	public static void recursiveRectangleFill(double minX, double maxX, double minY, double maxY){
		// Check the height and width for base case
		// Treat the dimension as fractions of the entire canvas pixels
		if ((maxX - minX)*WIDTH < MIN_SIZE || (maxY - minY)*HEIGHT < MIN_SIZE) {
			return;
		}

	

		//Calculate the width of sub triangle
		double width = (maxX - minX)/3.0;
		// The height of sub triangle
		double height = (maxY - minY)/3.0;  

		//Calculate the coordinate of the triangle's centre
		double X = minX + width*3/2, Y = minY + height*3/2;
		
		// Randomize the gradient
		int r = (int)(Math.random()*256);
		int g = (int)(Math.random()*256);
		int b = (int)(Math.random()*256);

		//Set the pen colors
		StdDraw.setPenColor(r, g, b);

		// Fill the middle rectangle
		StdDraw.filledRectangle(X, Y, width/2.0, height/2.0);		
		/*

		WITHOUT LOOP: (Use as REFERENCE to calculate the coefficient below)

		recursiveRectangleFill(minX, minX + width, minY, minY + height); // Bottom left
		recursiveRectangleFill(minX, minX + width, minY + height, minY + 2*height); // Centre Left 
		recursiveRectangleFill(minX, minX + width, minY + 2*height, maxY);// Top left

		recursiveRectangleFill(minX + width, minX + 2*width, minY, minY + height); // Bottom centre
		recursiveRectangleFill(minX + width, minX + 2*width, minY + 2*height, maxY); //Top centre
		

		recursiveRectangleFill(minX + 2*width, maxX, minY, minY + height); // Bottom right
		recursiveRectangleFill(minX + 2*width, maxX, minY + height, minY + 2*height); // Centre Left
		recursiveRectangleFill(minX + 2*width, maxX, minY + 2*height, maxY); // Top right
		*/
		
		// (0 1 2) (3 4 5) (6 7 8)
		for (int i = 0; i < 9; i++) { //i to keep track on X and Y
			if (i == 4) continue; // Skip this iteration since the we have only 2 middle rectangle to paint

			// Calculate how many times to add the width to have the new minX, maxX
			int Xcoefficient = i/3; // Using / to mimic outer loop
			// Calculate how many times to add the height to have the new minY, maxY
			int Ycoefficient = i%3; //Using % to mimic inner loop
			
			//Calculations below are based on observations when writing without loop as in above block comments
			//Calculate new minX
			double newMinX = minX + Xcoefficient*width;

			//Calculate new maxX
			double newMaxX = (Xcoefficient == 2)? maxX: minX + (Xcoefficient + 1)*width;

			// Calculate new minY
			double newMinY = minY + Ycoefficient*height;

			// Calculate new MaxY
			double newMaxY = (Ycoefficient == 2)? maxY:minY + (Ycoefficient + 1)*height;

			// Fill the sub triangle
			recursiveRectangleFill(newMinX, newMaxX, newMinY, newMaxY);
		}

	}//recursiveRectangleFill
	
	public static void recursiveBonus(double minX, double maxX, double minY, double maxY) {
		//Intialize properties for the first circle
		double radius = 0.3;
		int r = 137, g = 139, b = 255;
		recursiveBonus((maxX - minX)/2.0, (maxY - minY)/2.0, radius, r, g, b);
	}//recursiveBonus

	public static void recursiveBonus(double centreX, double centreY, double radius, int r, int g, int b) {
		
		//Base case. Use HEIGHT since we see in the example output, there is not a lot of recursively drawn circles
		//Use a smaller upper bound
		if (radius*WIDTH< MIN_SIZE_BONUS) return;

		//Set the pen colors
		StdDraw.setPenColor(r, g, b);
		StdDraw.filledCircle(centreX, centreY, radius);

		
		// Fill in the circle
		//I use Color Meter to inspect the RGB color code
		// By inspection, Blue is constant

		//Top left is less blue
		// Smaller red , bigger green 
		recursiveBonus(centreX - radius*Math.cos(Math.toRadians(45)), centreY + radius*Math.cos(Math.toRadians(45)), radius/2, r - 45, g + 35, b);

		
		//Bottom left is more blue
		//Smaller red by , smaller blue 
		recursiveBonus(centreX - radius*Math.cos(Math.toRadians(45)), centreY - radius*Math.cos(Math.toRadians(45)), radius/2, r + 30, g + 35, b);
		
		//Bottom right is more red
		//Bigger red, smaller green
		recursiveBonus(centreX + radius*Math.cos(Math.toRadians(45)), centreY - radius*Math.cos(Math.toRadians(45)), radius/2, r + 35, g - 45, b);

		//Top right is less red
		//Bigger red , bigger green 
		recursiveBonus(centreX + radius*Math.cos(Math.toRadians(45)), centreY + radius*Math.cos(Math.toRadians(45)), radius/2, r + 35, g + 35, b);

		
		
	
	}

}
