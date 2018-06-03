
package sudoku;
import java.util.Scanner;
class Position{
    int x;
    int y;
    Position(int a, int b){
        x = a;
        y = b;
    }
}

public class Sudoku {
    public static boolean isFilled(Position p, int[][] grid){
        return (grid[p.x][p.y] != 0);
    }
    public static boolean isValid(int k,Position p,int[][] grid){
        //check the row
        for(int j=0; j<9; j++){
            if(grid[p.x][j] == k) return false;
        }
        //check the column
        for(int i=0; i<9; i++){
            if(grid[i][p.y] == k) return false;
        }
        //check the box.
        //get the box start position.
        Position box = new Position(0,0);
        box.x = p.x - p.x%3;
        box.y = p.y - p.y%3;
        for(int i=0; i<3; i++){
            for(int j=0; j<3; j++){
                int x_c = box.x+i;
                int y_c = box.y +j;
                if(grid[x_c][y_c] == k){
                    return false;
                }
            }
        }
        return true;
    }
    
    public static Position nextPosition(Position p){
        if(p.y != 8) return (new Position(p.x,p.y+1));
        else return (new Position(p.x+1,0));
    }
    public static boolean isSolvable(Position p, int[][] grid){
        if(p.x == 9){
            for(int i=0; i<9; i++){
                for(int j=0; j<9; j++){
                    System.out.print(grid[i][j]+" ");
                }
                System.out.println();
            }
            return true;
        }
        if(isFilled(p,grid)){
            return isSolvable(nextPosition(p),grid);
        }else{
            for(int k=1; k<=9; k++){
                if( isValid(k,p,grid)){
                    grid[p.x][p.y] = k;
                    if(isSolvable(nextPosition(p), grid)){
                        return true;
                    }else grid[p.x][p.y] = 0;
                }
            }
            return false;
        }
    }
    
    public static void solveSudoku(int[][] grid){
        Position init = new Position(0,0);
        if( isSolvable(init,grid)){
            return;
        }else{
            System.out.println("Not solvable");
            return;
        }
    }
    
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int[][] grid = new int[9][9];
        for(int i=0; i<9; i++){
            for(int j=0; j<9; j++){
                grid[i][j] = in.nextInt();
            }
        }
        solveSudoku(grid);
    }
    
}
