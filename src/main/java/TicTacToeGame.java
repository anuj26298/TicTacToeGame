import java.util.Scanner;

public class TicTacToeGame {
    public static void main(String[] args) {
        System.out.println("__x-o-x-o__ Tic Tac Toe __x-o-x-o__");
        char[] board = createBoard();
        Scanner userInput = new Scanner(System.in);

        char userLetter = chooseUserLetter(userInput);
        char computerLetter = (userLetter == 'X') ? 'O' : 'X';
        System.out.println("User Letter: " + userLetter);
        System.out.println("Computer Letter: " + computerLetter);
    }

    private static char[] createBoard(){
        char[] board = new char[10];
        for (int i=1;i< board.length; i++){
            board[i] = ' ';
        }
        return board;
    }
    private static char chooseUserLetter(Scanner userInput){
        System.out.println("Choose Your Letter(X or O)");
        return userInput.next().toUpperCase().charAt(0);
    }
}
