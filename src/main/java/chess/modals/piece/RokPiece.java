package chess.modals.piece;

import chess.modals.Board;
import chess.modals.Cord;

import java.util.LinkedList;
import java.util.List;

import static chess.modals.Consts.*;

public class RokPiece extends Piece {

    public RokPiece(boolean isWhiteTurn, Cord cord, Board board) {
        super(isWhiteTurn, cord, board);
    }

    @Override
    public List<Cord> getPossibleMoves() {
        List<Cord> possibleMoves = new LinkedList<>();
        addHorizontalMoves(possibleMoves, LEFT, FIRST_ROW - 1);
        addHorizontalMoves(possibleMoves, RIGHT, LAST_ROW + 1);
        addVerticalMoves(possibleMoves, DOWN, FIRST_ROW - 1);
        addVerticalMoves(possibleMoves, UP, LAST_ROW + 1);
        return possibleMoves;
    }

    private void addVerticalMoves(List<Cord> possibleMoves, int direction, int bound) {
        for (int col = cord.getCol() + direction; col != bound; col += direction) {
            Cord move = new Cord(this.cord.getRow(), col);
            if (isBlocked(possibleMoves, move)) {
                return;
            }
            possibleMoves.add(move);
        }
    }

    private void addHorizontalMoves(List<Cord> possibleMoves, int direction, int bound) {
        for (int row = cord.getRow() + direction; row != bound; row += direction) {
            Cord move = new Cord(row, cord.getCol());
            if (isBlocked(possibleMoves, move)) {
                return;
            }
            possibleMoves.add(move);
        }
    }

    private boolean isBlocked(List<Cord> possibleMoves, Cord move) {
        if (isCellEmpty(move)) {
            return false;
        }

        if (doesCellHasEnemyPiece(move)) {
            possibleMoves.add(move);
        }

        return true;
    }

}
