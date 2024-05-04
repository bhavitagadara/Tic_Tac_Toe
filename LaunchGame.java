import java.util.Random;
import java.util.Scanner;

class TicTacToe{

    //object create krya vagar board ne bija class ma acces(use) krvo hoi to static lakhvu pase
    static char [][] board;

    //constructer
    public TicTacToe(){
        board = new char[3][3];
        initBoard(); 
    }

    //array chhe a object chhe in java & char ni default value hoi /0000(null char) atle space mate initBoard method kri
    //initialize board
    void initBoard(){
        for (int i=0; i<board.length ; i++){
            for(int j=0;j<board[i].length ; j++){
                board[i][j]=' ';
            }
        }
    }

    //for display the board
    static void dispBoard(){

        //box ma row banava aa s.o.p
        System.out.println("-------------");

        for (int i=0; i<board.length ; i++){

            //colunm mate sop
            System.out.print("| ");
            
            for(int j=0;j<board[i].length ; j++){ 

                System.out.print(board[i][j] + " | ");
            
            }
            System.out.println();// for cursor in next line mate
            System.out.println("-------------");
        }
    }

    static void placeMark(int row , int col , char mark){
         
        if (row >= 0 && row <= 2 && col >= 0 && col <= 2) {
            board[row][col] = mark;
        }
        else{
            System.out.println("Invalid Position");
        }
    }

    //column ma srkha check krva
    static boolean checkColWin()//boolean kemk true & false mate
    {
        for(int j=0 ; j<=2 ; j++){
            if (board[0][j]!=' ' &&  board[0][j] == board[1][j] && board[1][j] == board[2][j]) {
                return true;
            }
        }
        return false;
    }
    
    //row ma srkha check krva
    static boolean checkRowwin(){
        for(int i=0 ; i<=2 ; i++){
            if(board[i][0]!=' ' &&  board[i][0] == board[i][1] && board[i][1] == board[i][2]){
                return true;
            }
        }
        return false;
    }
    
    //diagonal ma srkha check krva
    static boolean checkDiagWin(){
        if (board[0][0]!=' ' &&   board[0][0] == board[1][1]
            && board[1][1] == board[2][2]
            || board[0][2]!=' ' && board[0][2] == board[1][1]
            && board[1][1] == board[2][0]) 
            {
            return true;
        }
        return false;
    }
    
    static boolean checkDraw(){
        for(int i=0 ; i<=2 ; i++){
            for(int j=0 ; j<=2 ; j++){
                if(board[i][j]== ' '){
                    return false;
                }
            }
        }
        return true;
    }

}

abstract class Player{
    String name;
    char mark;

    abstract void makeMove();

    boolean isValidMove(int row , int col){
        if(row >= 0 && row <= 2 && col >= 0 && col <=2){
            if(TicTacToe.board[row][col] == ' '){
                return true;
            }
        }
        return false;
    }
}

 //for two human player
class HumanPlayer extends Player{

    //two variable
    String name;
    char mark;

    //humanplayer constructor
    HumanPlayer(String name , char mark){
        this.name = name;
        this.mark = mark;
    }

    void makeMove(){
        Scanner scan = new Scanner(System.in);
        int row;
        int col;

        do{
            System.out.println("enter the row and column");
            row = scan.nextInt();
            col = scan.nextInt();
        }while(!isValidMove(row , col));

        TicTacToe.placeMark(row, col, mark);

    }
    boolean isValidMove(int row , int col){
        if(row >= 0 && row <= 2 && col >= 0 && col <=2){
            if(TicTacToe.board[row][col] == ' '){
                return true;
            }
        }
        return false;
    }

}

class AIPlayer extends Player{

    //two variable
    String name;
    char mark;

    //AIplayer constructor
    AIPlayer(String name , char mark){
        this.name = name;
        this.mark = mark;
    }

    void makeMove(){
        Scanner scan = new Scanner(System.in);
        int row;
        int col;

        do{
            Random r = new Random();
            row = r.nextInt(3); //3 includ na thy khali 0,1,2
            col =  r.nextInt(3);
        }while(!isValidMove(row , col));

        TicTacToe.placeMark(row, col, mark);

    }

}




public class LaunchGame {

    public static void main(String[] args) {
        TicTacToe t = new TicTacToe(); //obj creat thyo atle constuctor call thase
        
        HumanPlayer p1 = new HumanPlayer("player1" , 'X');
        HumanPlayer p2 = new HumanPlayer("player2" , 'O');
        //AIPlayer p2 = new AIPlayer("TAI" , 'O');


        // cp referance type assignment chhe currunt player mate object nathi
        //AIPlayer cp;
        HumanPlayer cp;
        cp = p1; //cp chhe a currunt player p1 ne point kre 
        
        while(true){ 
            
            System.out.println(cp.name + "turn");
            cp.makeMove();
            TicTacToe.dispBoard();
            if(TicTacToe.checkColWin() || TicTacToe.checkDiagWin() || TicTacToe.checkRowwin()){
                System.out.println(cp.name + "has won");
                break;
            }
            else if (TicTacToe.checkDraw()) {
                System.out.println("Game is draw");
                break;
            }
            else{
                if(cp == p1 ){
                    cp = p2;
                }
                else{
                    cp = p1;
                }
            }

        }
    }
}