
import java.util.*;
import java.io.*;
import java.awt.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Collections;

public class MapDataDrawer {

    private int[][] grid;

    public MapDataDrawer(String filename, int rows, int cols) throws IOException {
        grid = new int[rows][cols];
        scanIn(filename, grid);
        // initialize grid 
        //read the data from the file into the grid
    }

    public void scanIn(String filename, int[][] grid) throws FileNotFoundException, IOException {
        try (Scanner read = new Scanner(new File(filename))) {
            int i = 0, j = 0;
            while (i != grid.length) {
                grid[i][j++] = Integer.parseInt(read.next());
                if (j == grid[0].length) {
                    i++;
                    j = 0;
                }
            }
        }
        /**
         * @return the min value in the entire grid
         */
    }

    public int findMinValue() {
        int min = grid[0][0];
        for (int x = 0; x < grid.length; x++) {
            for (int y = 0; y < grid[0].length; y++) {
                if (grid[x][y] < min) {
                    min = grid[x][y];
                }
            }
        }
        return min;

    }

    public int findMaxValue() {
        int max = grid[0][0];
        for (int x = 0; x < grid.length; x++) {
            for (int y = 0; y < grid[0].length; y++) {
                if (grid[x][y] > max) {
                    max = grid[x][y];
                }
            }
        }
        return max;

    }

    public int indexOfMinInCol(int colum) {
            int keep =0;
        int mincolum = grid[0][colum];
        for (int a = 0; a < grid.length; a++) {
            if (grid[a][colum] < mincolum) {
                mincolum = grid[a][colum];
                keep = a;
            }

        }
        return  keep;

        /**
         * @param col the column of the grid to check
         * @return the index of the row with the lowest value in the given col
         * for the grid
         */
    }

    /**
     * Draws the grid using the given Graphics object. Colors should be gray
     * scale values 0-255, scaled based on min/max values in grid
     */
    public void drawMap(Graphics g) {
        int minVa = findMinValue();
        int maxVa = findMaxValue();
        for (int x = 0; x < 480; x++) {
            for (int y = 0; y < 844; y++) {
                int c = (grid[x][y] - minVa)  / ((maxVa- minVa)/225);
                g.setColor(new Color(c, c, c));
                g.fillRect(y, x, 1, 1);
            }
        }

    }

    /**
     * Find a path from West-to-East starting at given row. Choose a forward
     * step out of 3 possible forward locations, using greedy method described
     * in assignment.
     *
     * @return the total change in elevation traveled from West-to-East
     */
    public int drawLowestElevPath(Graphics g, int row) {
         int top,mid,low,sum=0;
    int lengthTop,lengthMid,lengthLow;
    int findMin;
    for(int j=1;j<grid[row].length;j++){
      
      int currow = grid[row][j-1];
      
      if(row==0){
        mid=grid[row][j];
        low=grid[row+1][j];
        lengthMid = Math.abs(currow-mid);
        lengthLow = Math.abs(currow-low);
        findMin = Math.min(lengthMid,lengthLow);

        if(lengthMid>lengthLow&&lengthMid!=lengthLow){
          row++;
        }
      }
      else if(row==grid.length-1){
        top=grid[row-1][j];
        mid=grid[row][j];
        lengthTop = Math.abs(currow-top);
        lengthMid = Math.abs(currow-mid);
        findMin = Math.min(lengthMid,lengthTop);

        if(lengthMid>lengthTop&&lengthMid!=lengthTop){
          row--;
        }
      }
      else{
        top=grid[row-1][j];
        mid=grid[row][j];
        low=grid[row+1][j];
        lengthTop = Math.abs(currow-top);
        lengthMid = Math.abs(currow-mid);
        lengthLow = Math.abs(currow-low);
        findMin = Math.min(Math.min(lengthMid,lengthTop),lengthLow);

        if(lengthTop!=lengthMid&&lengthMid!=lengthLow&&lengthTop!=lengthLow){
          if(lengthTop==findMin){
            row--;
          }
          else if(lengthLow==findMin){
            row++;
          }
        }
        else if((findMin==lengthTop||findMin==lengthLow)&&findMin!=lengthMid){
          int rd = (int)(Math.random()*1)+1;
          if(rd==2)
            row--;
          else
            row++;
        }
      }
      sum+=findMin;
      g.fillRect(j, row, 1,1);
      
    }
    return sum;
  }
  
    
       /* int start = grid[row][0];
        int st;
        int nd;
        int rd;
        int min;
        for(int i =0 ; i < 844 ; i++){
            g.fillRect(i, row, 1, 1);
        }
        for(int i = 1;i<=grid[0].length-1;i++){
            System.out.println(grid[row][i]);
            
            int current = grid[row][i];
            st = grid[row-1][i];
            nd = grid [row][i];
            rd = grid [row+1][i];
            //Find the far from the current value.
            int fartop = Math.abs(start - st);
            int farmid = Math.abs(start - nd);
            int farlow = Math.abs(start - rd);
            
            min = Math.min(Math.min(fartop, farmid),farlow);
            
            if(min == farmid){
                 g.fillRect(i, row, 1, 1);
                 start = grid [row ][i];
            }
            if(min == fartop && min != farmid && min != farlow){
                g.fillRect(i, row-1, 1, 1);
                 start = grid [row-1 ][i];
            }
            if(min == farlow){
                g.fillRect(i, row+1, 1, 1);
                 start = grid [row+1 ][i];
            }
            if(min == farlow && min == fartop){
                int a = (int )(Math. random() * 10 + 1);
                if(a < 5){
                g.fillRect(i, row-1, 1, 1);
                 start = grid [row-1 ][i];
                }else{
                g.fillRect(i, row+1, 1, 1);
                 start = grid [row+1 ][i];
                }
            }
            
       }
      return -1; 
    }*/
        







    

    /**
     * @return the index of the starting row for the lowest-elevation-change
     * path in the entire grid.
     */
    public int indexOfLowestElevPath(Graphics g) {
       int index=0;
    int min=drawLowestElevPath(g, 0);
    for(int i=1;i<grid.length;i++){
      if(drawLowestElevPath(g, i)<=min){
        min=drawLowestElevPath(g, i);
        index=i;
      }
    }
    return index;
    
  }
  
  
}