package chess.modals.piece;

import chess.modals.Board;
import chess.modals.Cord;

import java.util.LinkedList;
import java.util.List;

import static chess.modals.Consts.*;

public class BishopPiece extends Piece {
    public BishopPiece(boolean isWhiteTurn, Cord cord, Board board) {
        super(isWhiteTurn, cord, board);
    }

    @Override
    public List<Cord> getPossibleMoves() {
        List<Cord> possibleMoves = new LinkedList<>();

        addDiagonalMoves(possibleMoves, RIGHT, UP);
        addDiagonalMoves(possibleMoves, LEFT, UP);
        addDiagonalMoves(possibleMoves, LEFT, DOWN);
        addDiagonalMoves(possibleMoves, RIGHT, DOWN);
        return possibleMoves;
    }

    private void addDiagonalMoves(List<Cord> possibleMoves, int rowDirection, int colDirection) {
        Cord move = new Cord(cord.getRow() + rowDirection, cord.getCol() + colDirection);

        while (!move.isCordNotInRange()) {
            if (isBlocked(possibleMoves, move)) {
                return;
            }

            possibleMoves.add(move);
            move = new Cord(move.getRow() + rowDirection, move.getCol() + colDirection);
        }
    }
}
