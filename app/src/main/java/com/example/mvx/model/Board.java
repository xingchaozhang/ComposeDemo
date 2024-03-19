package com.example.mvx.model;

import static com.example.mvx.model.Player.O;
import static com.example.mvx.model.Player.X;

public class Board {
    private Cell[][] cells = new Cell[3][3];

    private Player winner;
    private GameState state;
    private Player currentTurn;

    public Board() {
        restart();
    }

    /**
     *  Restart a new game.
     */
    public void restart() {
        clearCells();
        winner = null;
        currentTurn = Player.X;
        state = GameState.IN_PROGRESS;
    }

    /**
     * Use to mark the current player select which column and which row
     * If the current player selected area is not in the 9 area, it will be thought as invalid.
     * If the game is over, the current mark will be ignored.
     *
     * @param row 0..2
     * @param col 0..2
     * @return Return current player, if the click is invalid, return null.
     *
     */
    public Player mark( int row, int col ) {

        Player playerThatMoved = null;

        if(isValid(row, col)) {

            cells[row][col].setValue(currentTurn);
            playerThatMoved = currentTurn;

            if(isWinningMoveByPlayer(currentTurn, row, col)) {
                state = GameState.FINISHED;
                winner = currentTurn;

            } else {
                // Switch to another player and continue.
                flipCurrentTurn();
            }
        }

        return playerThatMoved;
    }

    public Player getWinner() {
        return winner;
    }

    private void clearCells() {
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                cells[i][j] = new Cell();
            }
        }
    }

    private boolean isValid(int row, int col ) {
        if( state == GameState.FINISHED ) {
            return false;
        } else if( isCellValueAlreadySet(row, col) ) {
            return false;
        } else {
            return true;
        }
    }

    private boolean isCellValueAlreadySet(int row, int col) {
        return cells[row][col].getValue() != null;
    }


    /**
     * @param player
     * @param currentRow
     * @param currentCol 
     * @return if current row or current column or two diagonal are the same player, then return true.
     *
     */
    private boolean isWinningMoveByPlayer(Player player, int currentRow, int currentCol) {

        return (cells[currentRow][0].getValue() == player         // 3-row
                && cells[currentRow][1].getValue() == player
                && cells[currentRow][2].getValue() == player
                || cells[0][currentCol].getValue() == player      // 3-column
                && cells[1][currentCol].getValue() == player
                && cells[2][currentCol].getValue() == player
                || currentRow == currentCol            // 3-diagonal
                && cells[0][0].getValue() == player
                && cells[1][1].getValue() == player
                && cells[2][2].getValue() == player
                || currentRow + currentCol == 2    // 3-back-diag
                && cells[0][2].getValue() == player
                && cells[1][1].getValue() == player
                && cells[2][0].getValue() == player);
    }

    private void flipCurrentTurn() {
        currentTurn = currentTurn == X ? O : X;
    }

}
