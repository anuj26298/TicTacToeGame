import java.util.Arrays;
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

        showBoard(board);
        int userMove = getUserMove(board, userInput);
        makeMove(board, userMove, userLetter);
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

    private static void showBoard(char[] board) {
        System.out.println("| " + board[1] + " | " +
                board[2] + " | " + board[3] + " | ");
        System.out.println("| " + board[4] + " | " +
                board[5] + " | " + board[6] + " | ");
        System.out.println("| " + board[7] + " | " +
                board[8] + " | " + board[9] + " | ");
    }

    private static int getUserMove(char[] board, Scanner userInput){
        Integer[] validCells = {1,2,3,4,5,6,7,8,9};
        while (true) {
            System.out.println("Enter your next move(1-9)");
            int index = userInput.nextInt();
            if (Arrays.asList(validCells).contains(index) && isSpaceFree(board, index))
                return index;
        }
    }
    private static boolean isSpaceFree(char[] board, int index){
        return board[index] == ' ';
    }

    private static void makeMove(char[] board, int index, char letter){
        boolean spaceFree = isSpaceFree(board, index);
        if (spaceFree)
            board[index] = letter;
    }
}
