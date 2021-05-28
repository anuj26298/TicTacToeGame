import java.util.Arrays;
import java.util.Scanner;

public class TicTacToeGame {
    public static void main(String[] args) {
        System.out.println("__x-o-x-o__ Tic Tac Toe __x-o-x-o__");
        Scanner userInput = new Scanner(System.in);
        char controlVariable;
        do {
            char[] board = createBoard();
            TicTacToeGame tictactoe = new TicTacToeGame();
            char userLetter = chooseUserLetter(userInput);
            char computerLetter = (userLetter == 'X') ? 'O' : 'X';
            System.out.println("User Letter: " + userLetter);
            System.out.println("Computer Letter: " + computerLetter);

            tictactoe.showBoard(board);

            boolean tossResult = toss(userInput);

            boolean play = true;

            while (play) {
                if (tossResult) {
                    int index = getUserMove(board, userInput);
                    tictactoe.makeMove(board, index, userLetter);
                    tictactoe.showBoard(board);
                    System.out.println("============================");
                    play = tictactoe.checkWinning(board, userLetter, computerLetter);
                    if (play) {
                        board = tictactoe.computerInput(board, userLetter, computerLetter);
                        tictactoe.showBoard(board);
                        System.out.println("=============================");
                        play = tictactoe.checkWinning(board, userLetter, computerLetter);
                        if (!play)
                            System.out.println("Game Over!!!");
                    } else
                        System.out.println("Game Over!!!");
                } else {
                    board = tictactoe.computerInput(board, userLetter, computerLetter);
                    tictactoe.showBoard(board);
                    System.out.println("=============================");
                    play = tictactoe.checkWinning(board, userLetter, computerLetter);

                    if (play) {
                        int index = tictactoe.getUserMove(board, userInput);
                        tictactoe.makeMove(board, index, userLetter);
                        tictactoe.showBoard(board);
                        System.out.println("=============================");
                        play = tictactoe.checkWinning(board, userLetter, computerLetter);
                        if (!play)
                            System.out.println("Game Over!!!");
                    } else
                        System.out.println("Game Over!!!");
                }
            }
            System.out.println("Do you want to play again?(y/n)");
            controlVariable = userInput.next().charAt(0);
        }while (controlVariable == 'Y' || controlVariable == 'y');
    }


    private static char[] createBoard() {
        char[] board = new char[10];
        for (int i = 1; i < board.length; i++) {
            board[i] = ' ';
        }
        return board;
    }

    private static char chooseUserLetter(Scanner userInput) {
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

    private static int getUserMove(char[] board, Scanner userInput) {
        Integer[] validCells = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        while (true) {
            System.out.println("Enter your next move(1-9)");
            int index = userInput.nextInt();
            if (Arrays.asList(validCells).contains(index) && isSpaceFree(board, index))
                return index;
        }
    }

    private static boolean isSpaceFree(char[] board, int index) {
        return board[index] == ' ';
    }

    private static void makeMove(char[] board, int index, char letter) {
        boolean spaceFree = isSpaceFree(board, index);
        if (spaceFree)
            board[index] = letter;
    }

    private static boolean toss(Scanner userCall) {
        System.out.println("What's your Call? (Head or Tail)");
        String userTossCall;
        userTossCall = userCall.next();
        int turn = (int) (Math.random() * 2);
        String tossCall = new String[]{"Head", "Tail"}[turn];
        if (userTossCall.equals(tossCall)) {
            System.out.println("User Wins Toss");
            return true;
        } else {
            System.out.println("Computer Wins Toss");
            return false;
        }
    }

    private boolean checkWinning(char[] board, char userLetter, char computerLetter) {
        if (checkBoard(board, userLetter)) {
            System.out.println("You Won!!!");
            return false;
        }

        if (checkBoard(board, computerLetter)) {
            System.out.println("Computer Wins...");
            return false;
        }

        int flag = 0;
        for (int i = 0; i < 9; i++) {
            if (board[i] == ' ') {
                flag = 1;
                break;
            }
        }
        if (flag == 0) {
            System.out.println("Match tied");
            return false;
        }
        return true;
    }

    private boolean checkBoard(char[] board, char letter) {
        return (board[0] == letter && board[1] == letter && board[2] == letter) ||
                (board[3] == letter && board[4] == letter && board[5] == letter) ||
                (board[6] == letter && board[7] == letter && board[8] == letter) ||
                (board[0] == letter && board[3] == letter && board[6] == letter) ||
                (board[1] == letter && board[4] == letter && board[7] == letter) ||
                (board[2] == letter && board[5] == letter && board[8] == letter) ||
                (board[0] == letter && board[4] == letter && board[8] == letter) ||
                (board[2] == letter && board[4] == letter && board[6] == letter);
    }

    private boolean checkWinning(char[] tempBoard, char letter) {
        return checkBoard(tempBoard, letter);
    }

    private boolean winMove(int index, char[] board, char letter) {
        char[] tempBoard;
        tempBoard = board.clone();
        tempBoard[index] = letter;
        return checkWinning(tempBoard, letter);
    }

    private char[] computerInput(char[] board, char userLetter, char computerLetter) {
        for (int i = 0; i < 9; i++) {
            if (board[i] == ' ' && winMove(i, board, computerLetter)) {
                board[i] = computerLetter;
                return board;
            }
        }

        for (int i = 0; i < 9; i++) {
            if (board[i] == ' ' && winMove(i, board, userLetter)) {
                board[i] = computerLetter;
                return board;
            }
        }
        if (board[0] == ' ') {
            board[0] = computerLetter;
            return board;
        }
        if (board[2] == ' ') {
            board[2] = computerLetter;
            return board;
        }
        if (board[6] == ' ') {
            board[6] = computerLetter;
            return board;
        }
        if (board[8] == ' ') {
            board[8] = computerLetter;
            return board;
        }
        if (board[4] == ' ') {
            board[4] = computerLetter;
            return board;
        }
        if (board[1] == ' ') {
            board[1] = computerLetter;
            return board;
        }
        if (board[3] == ' ') {
            board[3] = computerLetter;
            return board;
        }
        if (board[5] == ' ') {
            board[5] = computerLetter;
            return board;
        }
        if (board[7] == ' ') {
            board[7] = computerLetter;
            return board;
        }

        return board;
    }

}
