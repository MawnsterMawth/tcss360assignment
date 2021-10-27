/*
 * Minesweeper solution
 * Fall 2021
 */
package asz4MinesweeperSolution;

import java.util.Scanner;

/**
 * This demonstrates generating hints for a minesweeper field.
 * 
 * @author Ashley Zheng
 * @version 1.0 14/10/2021
 */
public class Asz4MinesweeperSolution {
	
	// Fields for dimensions
	private static int myRows;
	private static int myColumns;
	
	/**
     * @main function
     * @param theArgs arguments
     * 
     */
	public static void main(String[] args) {
		int fieldCount = 0;
		Scanner input = new Scanner(System.in); // Input field length and width
		String fieldDimensions = input.nextLine(); 
		
		// Set the array size based on input
		setRows(Integer.parseInt(fieldDimensions.substring(0,fieldDimensions.indexOf(" "))));
		setColumns(Integer.parseInt(fieldDimensions.substring(fieldDimensions.indexOf(" ")+1, fieldDimensions.length())));
		
		// Stop getting input if either dimension is 0
		while(myRows > 0 && myColumns > 0) {
			fieldCount++; // Count the number of fields read
			
			char[][] mineArray = new char [myRows][myColumns];
			
			// Input the mines in each row into an array
			for(int i=0; i < myRows; i++) {
				String mineRow = input.nextLine();
				
				for (int j = 0; j < myColumns; j++) {
					mineArray[i][j] = mineRow.charAt(j);
				}
			}
			
			// Create and print the hint field
			System.out.println("Field #" + fieldCount + ":");
			createNumberField(mineArray);
			
			// Input and read the next field's dimensions
			fieldDimensions = input.nextLine();
			
			setRows(Integer.parseInt(fieldDimensions.substring(0,fieldDimensions.indexOf(" "))));
			setColumns(Integer.parseInt(fieldDimensions.substring(fieldDimensions.indexOf(" ")+1, fieldDimensions.length())));
			
			System.out.println(); // Put a line in between hint fields
		}
	}
	
	/**
	 * Modifier for the number of rows
	 * @param rowNum number of rows
	 */
	static void setRows(int theRowNum) {
		myRows = theRowNum;
	}
	
	/**
	 * Modifier for the number of columns
	 * @param colNum number of columns
	 */
	static void setColumns(int theColNum) {
		myColumns = theColNum;
	}
	
	/**
	 * Function for making and displaying the hint field
	 * @param mineField the field of mines to read
	 */
	static void createNumberField(char[][] mineField) {
		char numField[][] = new char[myRows][myColumns];
		
		// Count the mines adjacent to a point
		for(int i = 0; i < myRows; i++) {
			
			for(int j = 0; j < myColumns; j++) {
				int numberOfMines = 0;
				if(i > 0 && j > 0 && mineField[i - 1][j - 1] == '*') {
					numberOfMines++;
				}
				if(i > 0 && mineField[i - 1][j] == '*') {
					numberOfMines++;
				}
				if(j > 0 && mineField[i][j - 1] == '*') {
					numberOfMines++;
				}
				if(i < (myRows - 1) && j < (myColumns - 1) && mineField[i + 1][j + 1] == '*') {
					numberOfMines++;
				}
				if(i < (myRows - 1) && mineField[i + 1][j] == '*') {
					numberOfMines++;
				}
				if(j < (myColumns - 1) && mineField[i][j + 1] == '*') {
					numberOfMines++;
				}
				if(i > 0 && j < (myColumns - 1) && mineField[i - 1][j + 1] == '*') {
					numberOfMines++;
				}
				if(i < (myRows - 1) && j > 0 && mineField[i + 1][j - 1] == '*') {
					numberOfMines++;
				}
				
				numField[i][j] = Character.forDigit(numberOfMines,10);
				
				// Disregard the number if the point itself is a mine
				if(mineField[i][j] == '*') {
					numField [i][j] = '*';
				}
			}
		}
		
		// Print out the hint field
		for(int i = 0; i < myRows; i++) {
			for(int j = 0; j < myColumns; j++) {
				System.out.print(numField[i][j]);
			}
			System.out.println();
		}
	}
}
