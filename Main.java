/*Name: Anela Karamustafic
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    static int[][] board = new int[4][4];
    static boolean playable = true;
    static int counter = 1;
    public static void main(String[] args) {
        System.out.println("Welcome to 2048\nPress w, s, a, or d to move: up, down, left, or right respectively.");
        System.out.println("You can quit any time by typing q, you can also restart by pressing r.\n");
        restartGame();
    }
    public static void restartGame(){
        for (int row = 0; row < board.length; row++){
            for (int col = 0; col < board[row].length; col++){
                board[row][col] = 0;
            }
        }
        newRandomNum(board);
        newRandomNum(board);
        printBoard(board);
        move(0);
    }

    public static void printBoard(int[][] arr){
        for (int row = 0; row < arr.length; row++){
            for (int col = 0; col < arr[row].length; col++){
                System.out.printf("%8d", arr[row][col]);
            }
            System.out.println();
        }
    }
    public static int[][] newRandomNum(int[][] arr){
        List<Integer> rowList = new ArrayList();
        List<Integer> colList = new ArrayList();
        for (int row = 0; row < arr.length; row++){
            for (int col = 0; col < arr[row].length; col++){
                if (arr[row][col] == 0){
                    rowList.add(row);
                    colList.add(col);
                }
            }
        }
        if (rowList.size() > 0) {
            int random = (int) (1 + Math.random() * rowList.size() - 1);
            arr[rowList.get(random)][colList.get(random)] = new2or4();
        }
        return arr;
    }
    public static int new2or4(){//the method that determines the probability of getting 2/4
        int random = (int)(1 + Math.random()*10);
        if (random > 8)
            return 4;
        else
            return 2;
    }
    public static boolean isMoveValid(int dir){//the method that determins if the move is valid or not
        //up
        if (dir == 1){
            for(int row = 0 ; row < board.length; row++) {
                for (int col = 0; col < board[row].length; col++) {
                    if (row == 0){
                        if (board[row][col] == 0 && (board[1][col] != 0 || board[2][col] != 0 || board[3][col] != 0)) {
                            counter++;
                            return true;
                        }
                    }
                    else if (board[row][col] == board[row-1][col] && board[row][col] != 0) {
                        counter++;
                        return true;
                    }
                    else if (board[row][col] != 0 && board[row-1][col] == 0) {
                        counter++;
                        return true;
                    }
                }
            }
        }
        //down
        else if (dir == 2){
            for(int row = 0 ; row < board.length; row++) {
                for (int col = 0; col < board[row].length; col++) {
                    if (row == 3){
                        if (board[row][col] == 0 && (board[0][col] != 0 || board[1][col] != 0 || board[2][col] != 0)) {
                            counter++;
                            return true;
                        }
                    }
                    else if (board[row][col] == board[row+1][col] && board[row][col] != 0) {
                        counter++;
                        return true;
                    }
                    else if (board[row][col] != 0 && board[row+1][col] == 0) {
                        counter++;
                        return true;
                    }
                }
            }
        }
        //left
        else if (dir == 3){
            for(int row = 0 ; row < board.length; row++) {
                for (int col = 0; col < board[row].length; col++) {
                    if (col == 0){
                        if (board[row][col] == 0 && (board[row][1] != 0 || board[row][2] != 0 || board[row][3] != 0)) {
                            counter++;
                            return true;
                        }
                    }
                    else if (board[row][col] == board[row][col-1] && board[row][col] != 0) {
                        counter++;
                        return true;
                    }
                    else if (board[row][col] != 0 && board[row][col-1] == 0) {
                        counter++;
                        return true;
                    }
                }
            }
        }
        //right
        else if (dir == 4){
            for(int row = 0 ; row < board.length; row++) {
                for (int col = 0; col < board[row].length; col++) {
                    if (col == 3){
                        if (board[row][col] == 0 && (board[row][0] != 0 || board[row][1] != 0 || board[row][2] != 0)) {
                            counter++;
                            return true;
                        }
                    }
                    else if (board[row][col] == board[row][col+1] && board[row][col] != 0) {
                        counter++;
                        return true;
                    }
                    else if (board[row][col] != 0 && board[row][col+1] == 0) {
                        counter++;
                        return true;
                    }
                }
            }
        }
        return false;
    }
    public static void clearScreen() {
        for (int i = 0; i < 60; i++)
            System.out.println();
    }
    public static void move(int direction){
        /*1 = up
         *2 = down
         *3 = left
         *4 = right*/
        boolean temp;
        while(playable) {
            direction = getKeyInput();
            int i = 1;
            int k = 0;
            while(i < 5) {
                if (boardFull() && !isMoveValid(i)) {
                    k++;}
                if (k == 4)
                    endGame();
                i++;}
	            if (direction == 1) {
	                //move up
	                System.out.println("Your move was valid: " + isMoveValid(direction));
	                System.out.println("Current number of valid moves: " + (counter/2));
	                temp = isMoveValid(direction);
	                if (temp)
	                    System.out.println("You moved up!");
	                
	                shiftAllColumns(board, 4);
	                for(int row = 0 ; row < board.length-1; row++) {
	                    for (int col = board.length - 1; col >= 0; col--) {
	                        if (board[row][col] == board[row + 1][col]) {
	                            board[row][col] = board[row][col] * 2;
	                            board[row + 1][col] = 0;
	                            shiftAllColumns(board, 4);
	                        }
	                    }
	                }
	                getMax();
	                if (temp)
	                    newRandomNum(board);              
	                printBoard(board);
	            }
	            else if (direction == 2) {
	                //move down
	                System.out.println("Your move was valid: " + isMoveValid(direction));
	                System.out.println("Current number of valid moves: " + (counter/2));
	                temp = isMoveValid(direction);
	                if (temp)
	                    System.out.println("You moved down!");
	
	                shiftAllColumns(board, 3);
	                for(int row = board.length-1 ; row > 0; row--) {
	                    for (int col = board.length - 1; col >= 0; col--) {
	                        if (board[row][col] == board[row - 1][col]) {
	                            board[row][col] = board[row][col] * 2;
	                            board[row - 1][col] = 0;
	                            shiftAllColumns(board, 3);
	                        }	
	                    }	
	                }
	                getMax();
	                if (temp) 
	                    newRandomNum(board);	                
	                printBoard(board);
	            } 
	            else if (direction == 3) {
	                //move left
	                System.out.println("Your move was valid: " + isMoveValid(direction));
	                System.out.println("Current number of valid moves: " + (counter/2));
	                temp = isMoveValid(direction);
	                if (temp)
	                    System.out.println("You moved left!");
	                //correct one
	                shiftAllRows(board, 1);
	                for(int row=0; row < board.length; row ++ ) {
	                    for (int col = 0; col < board.length - 1; col++) {
	                        if (board[row][col] == board[row][col + 1]) {
	                            board[row][col] = board[row][col] * 2;
	                            board[row][col + 1] = 0;
	                            shiftAllRows(board, 1);
	                        }
	                    }
	                }
	                getMax();
	                if (temp) {
	                    newRandomNum(board);
	                }
	                printBoard(board);
	            } 
	            else if (direction == 4) {
                //move right
	                System.out.println("Your move was valid: " + isMoveValid(direction));
	                System.out.println("Current number of valid moves: " + (counter/2));
	                temp = isMoveValid(direction);
	                if (temp)
	                    System.out.println("You moved right!");
	
	                shiftAllRows(board, 2);
	                for(int row=0; row < board.length; row ++ ) {
	                    for (int col = board.length - 1; col > 0; col--) {
	                        if (board[row][col] == board[row][col - 1]) {
	                            board[row][col] = board[row][col] * 2;
	                            board[row][col - 1] = 0;
	                            shiftAllRows(board, 2);
	                        }
	                    }
	                }
	                getMax();
	                if (temp) 
	                    newRandomNum(board);                
	                printBoard(board);
	            }
        }
    }
    public static boolean boardFull(){
        for (int row = 0; row < board.length; row++){
            for (int col = 0; col < board[row].length; col++){
                if (board[row][col] == 0)
                    return false;
            }
        }
        return true;
    }
    public static void shiftAllRows(int arr[][], int dir) { //method that shifts all rows (right/left)
        if (dir == 2)
            for(int row=0; row<=3; row++) {
                for (int i=3; i > 0; i--) {
                    if(i == 1 && arr[row][i] == 0){
                        arr [row][i]= arr[row][i-1];
                        arr [row][i-1]=0;}
                    else if(arr[row][i] == 0 && arr[row][i-1] == 0 && arr[row][i-2]!= 0  ) {
                        arr [row][i]= arr[row][i-2];
                        arr [row][i-2]=0;}
                    else if(arr[row][i] == 0) {
                        arr [row][i]= arr[row][i-1];
                        arr [row][i-1]=0;}
                    else continue;
                }
            }
        if (dir == 1)
            for(int row=0; row<=3; row++) {
                for (int i=0; i < 3; i++) {
                    if(i == 3 - 1 && arr[row][i] == 0){
                        arr [row][i]= arr[row][i+1];
                        arr [row][i+1]=0;}
                    else if(arr[row][i] == 0 && arr[row][i+1] == 0 && arr[row][i+2]!= 0  ) {
                        arr [row][i]= arr[row][i+2];
                        arr [row][i+2]=0;}
                    else if(arr[row][i] == 0) {
                        arr [row][i]= arr[row][i+1];
                        arr [row][i+1]=0;}
                    else continue;
                }
            }
    }
    public static void shiftAllColumns(int arr[][], int dir) { //method that shifts all columns (up/down)
        if ( dir == 3)
            for(int col=0; col<=3; col++){
                for (int i=3; i > 0; i--) {
                    if(i == 1 && arr[i][col] == 0){
                        arr [i][col]= arr[i-1][col];
                        arr [i-1][col]=0;}
                    else if(arr[i][col] == 0 && arr[i-1][col] == 0 && arr[i-2][col]!= 0  ) {
                        arr [i][col]= arr[i-2][col];
                        arr [i-2][col]=0;}
                    else if(arr[i][col] == 0) {
                        arr [i][col]= arr[i-1][col];
                        arr [i-1][col]=0;}
                    else continue;
                }
            }
        if (dir == 4)
            for(int col=0; col<=3; col++) {
                for (int i=0; i< 3 ; i++) {
                    if(i == 3-1 && arr[i][col] == 0){
                        arr [i][col]= arr[i+1][col];
                        arr [i+1][col]=0;}
                    else if(arr[i][col] == 0 && arr[i+1][col] == 0 && arr[i+2][col]!= 0  ) {
                        arr [i][col]= arr[i+2][col];
                        arr [i+2][col]=0;}
                    else if(arr[i][col] == 0) {
                        arr [i][col]= arr[i+1][col];
                        arr [i+1][col]=0;}
                    else continue;
                }
            }
    }
    public static int getKeyInput(){
        Scanner scanner = new Scanner(System.in);
        int direction = 0;
        String input = scanner.nextLine();
        
        if (input.equals("w")) {
            clearScreen();
            System.out.println("Entered: w");
            direction = 1;
        }
        else if (input.equals("s")) {
            clearScreen();
            System.out.println("Entered: s");
            direction = 2;
        }
        else if (input.equals("a")){
            clearScreen();
            System.out.println("Entered: a");
            direction = 3;
        }
        else if (input.equals("d")){
            clearScreen();
            System.out.println("Entered: d");
            direction = 4;
        }
        else if (input.equals("q")){
            System.out.print("Are you sure you want to quit? Enter yes to quit, enter no to continue: ");
            if (scanner.next().toLowerCase().equals("yes"))
                endGame();
            else
                System.out.println("Continue by entering another move.");
        }
        else if (input.equals("r")){
            System.out.print("Are you sure you want to restart? Enter yes to restart, enter no to continue: ");
            if (scanner.next().toLowerCase().equals("yes"))
                restartGame();
            else
                System.out.println("Continue by entering another move.");
        }
        return direction;
    }

    public static void getMax(){
        int max = board[0][0];
        for (int row = 0; row < board.length; row++){
            for (int col = 0; col < board[row].length; col++){
                if (board[row][col] > max) {
                    max = board[row][col];
                }
            }
        }
        System.out.println("Highest Number: " + max);
    }
    public static void endGame(){
        playable = false;
        System.out.println("Game Over! Thank you for playing!");
        System.out.println("Total number of valid moves: " + counter/2);
        int max = board[0][0];
        for (int row = 0; row < board.length; row++){
            for (int col = 0; col < board[row].length; col++){
                if (board[row][col] > max)
                    max = board[row][col];
            }
        }
        System.out.println("Highest number: " + max);
		if(max >= 2048)
			System.out.println("Congrats! You beat the game!");
	}    
}

