public class TicTacToeGame {
    public static void main(String[] args) {
        System.out.println("__x-o-x-o__ Tic Tac Toe __x-o-x-o__");
        char[] board = createBoard();
    }

    private static char[] createBoard(){
        char[] board = new char[10];
        for (int i=1;i< board.length; i++){
            board[i] = ' ';
        }
        return board;
    }
}
