package chess.modals.piece;

import chess.modals.Board;
import chess.modals.CellContent;
import chess.modals.Cord;

import java.util.LinkedList;
import java.util.List;

import static chess.modals.Consts.*;

public class KnightPiece extends Piece {
    public KnightPiece(boolean isWhiteTurn, Cord cord, Board board) {
        super(isWhiteTurn, cord, board);
    }

    @Override
    public List<Cord> getPossibleMoves() {
        List<Cord> possibleMoves = new LinkedList<>();
        addMoveIfNotFriend(possibleMoves, new Cord(cord.getRow() + LEFT, cord.getCol() + (2 * UP)));
        addMoveIfNotFriend(possibleMoves, new Cord(cord.getRow() + RIGHT, cord.getCol() + (2 * UP)));
        addMoveIfNotFriend(possibleMoves, new Cord(cord.getRow() + RIGHT, cord.getCol() + (2 * DOWN)));
        addMoveIfNotFriend(possibleMoves, new Cord(cord.getRow() + LEFT, cord.getCol() + (2 * DOWN)));
        addMoveIfNotFriend(possibleMoves, new Cord(cord.getRow() + (2 * LEFT), cord.getCol() + DOWN));
        addMoveIfNotFriend(possibleMoves, new Cord(cord.getRow() + (2 * LEFT), cord.getCol() + UP));
        addMoveIfNotFriend(possibleMoves, new Cord(cord.getRow() + (2 * RIGHT), cord.getCol() + DOWN));
        addMoveIfNotFriend(possibleMoves, new Cord(cord.getRow() + (2 * RIGHT), cord.getCol() + UP));
        return possibleMoves;
    }

    private void addMoveIfNotFriend(List<Cord> possibleMoves, Cord cord) {
        if (cord.isCordNotInRange()) {
            return;
        }

        if (!this.doesCellHasFriendPiece(cord)) {
            possibleMoves.add(cord);
        }
    }
}
