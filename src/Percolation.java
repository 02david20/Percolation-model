import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;
public class Percolation {
          WeightedQuickUnionUF uf;
          private int size;
          private boolean opened[][];
          private int top = 0;
          private int bottom;
          
          // creates n-by-n grid, with all sites initially blocked
          public  Percolation(int n){
               if(n <=0) throw new IllegalArgumentException("");
              opened = new boolean[n][n]; 
              size = n;
              bottom = size*size+1;
              uf = new WeightedQuickUnionUF(size*size+2);
          }
      
          private int getIndex(int row, int col) {
               return (row-1) * size + col;
          }
          // opens the site (row, col) if it is not open already
          public void open(int row, int col) {
               if(!(0<row && row <= size) && !(0<col && col <= size)) throw new IllegalArgumentException("");
               opened[row-1][col-1] = true;
               if(row == 1) uf.union(top, getIndex(row,col));
               else if( row == size) uf.union(bottom, getIndex(row, col));
               else if(row > 1 && opened[row-1-1][col-1]) uf.union(getIndex(row, col),getIndex(row-1, col));
               else if(row < size && opened[row][col-1])  uf.union(getIndex(row,col),getIndex(row+1, col));
               else if(col > 1 && opened[row-1][col-1-1]) uf.union(getIndex(row, col),getIndex(row, col-1));
               else if(col < size && opened[row-1][col])  uf.union(getIndex(row, col),getIndex(row, col+1));
          };
          // is the site (row, col) open?
          public boolean isOpen(int row, int col) {
               return opened[row-1][col-1];
          } 
          // is the site (row, col) full?
          public boolean isFull(int row, int col) {
               if(1 <= row && row <= size && 1<=col && col <= size){
                    return uf.find(getIndex(row, col)) == 0;//top
               }else{
                    throw new IllegalArgumentException();
               }
          }
          // returns the number of open sites
          public int numberOfOpenSites() {
               int s = 0;
               for (int i = 0 ; i < size ; i++)
                    for (int j = 0; j < size; j ++) if(opened[i][j]) s++;
               return s;
          }
          // does the system percolate?
          public boolean percolates() {return uf.find(top) == uf.find(bottom);}
      
          // test client (optional)
          public static void main(String[] args){
               Percolation a = new Percolation(10);
          }
      
}
