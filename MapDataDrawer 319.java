import java.util.*;
import java.io.*;
import java.awt.*;

public class MapDataDrawer
{
  private int grid[][];
  
  public MapDataDrawer(String filename, int rows, int cols){
    String path = "C:\\Users\\Student Lab\\Desktop\\MountainPaths\\"+filename;
    int grids[][] = new int[rows][cols];
    this.grid = grids;
    int value[] = new int[405120];
    File file = new File(path);
    try {
      BufferedReader br = new BufferedReader(new FileReader(file));
      String line;
      
      while ((line = br.readLine()) != null) {
        String[] numbers = line.split("   ");
        
        for(int i =0; i<value.length; i++){
          //value[i] = Integer.parseInt(numbers[i]);  
          //System.out.print(value[i]+" ");
        }
        System.out.println();
      }
      br.close();
    } catch (IOException e) {
      
      e.printStackTrace();
    }
    
  }
  
  
  /**
   * @return the min value in the entire grid
   */
  public int findMinValuecolum(int colum){
    int min = grids[0][colum];
        for(int i = 0 ; i < grids.length ; i++){
            if(grids[i][col] < min){
                min = grids[i][col];
            }
        }
        return min;
      
  }
  /**
   * @return the max value in the entire grid
   */
  public int findMaxValuecolum(int colum){
    int max = grids[0][0];
        for(int x = 0; x < grids.length; x++) {
            for (int y = 0; y < grids[0].length; y++) {
                if(grids[x][y] > max){
                    max = grids[x][y];
                }
            }
        }
        return max;
     
    }
    
  
  /**
   * @param col the column of the grid to check
   * @return the index of the row with the lowest value in the given col for the grid
   */
  public  int indexOfMinInCol(int col){
    
    return -1;
  }
  
  /**
   * Draws the grid using the given Graphics object.
   * Colors should be grayscale values 0-255, scaled based on min/max values in grid
   */
  public void drawMap(Graphics g){
    
    
    
    
  }
  
  /**
   * Find a path from West-to-East starting at given row.
   * Choose a foward step out of 3 possible forward locations, using greedy method described in assignment.
   * @return the total change in elevation traveled from West-to-East
   */
  public int drawLowestElevPath(Graphics g, int row){
    return -1;
  }
  
  /**
   * @return the index of the starting row for the lowest-elevation-change path in the entire grid.
   */
  public int indexOfLowestElevPath(Graphics g){
    return -1;
    
  }
  
  
}