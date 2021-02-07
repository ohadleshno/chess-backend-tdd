package chess.modals.piece;

import chess.modals.Board;
import chess.modals.CellContent;
import chess.modals.Cord;

import java.util.LinkedList;
import java.util.List;

import static chess.modals.Consts.*;

public class KingPiece extends Piece {

    public KingPiece(boolean isWhiteTurn, Cord cord, Board board) {
        super(isWhiteTurn, cord, board);
    }

    @Override
    public List<Cord> getPossibleMoves() {
        List<Cord> possibleMoves = new LinkedList<>();
        addKingMoves(possibleMoves);
        return possibleMoves;
    }

    private void addKingMoves(List<Cord> possibleMoves) {
        addMoveIfEmpty(possibleMoves, new Cord(cord.getRow() + UP, cord.getCol()));
        addMoveIfEmpty(possibleMoves, new Cord(cord.getRow() + UP, cord.getCol() + RIGHT));
        addMoveIfEmpty(possibleMoves, new Cord(cord.getRow() + UP, cord.getCol() + LEFT));
        addMoveIfEmpty(possibleMoves, new Cord(cord.getRow(), cord.getCol() + RIGHT));
        addMoveIfEmpty(possibleMoves, new Cord(cord.getRow(), cord.getCol() + LEFT));
        addMoveIfEmpty(possibleMoves, new Cord(cord.getRow() + DOWN, cord.getCol() + LEFT));
        addMoveIfEmpty(possibleMoves, new Cord(cord.getRow() + DOWN, cord.getCol()));
        addMoveIfEmpty(possibleMoves, new Cord(cord.getRow() + DOWN, cord.getCol() + RIGHT));
    }

    private void addMoveIfEmpty(List<Cord> possibleMoves, Cord cord) {
        if (cord.isCordNotInRange()) {
            return;
        }

        if (this.board.getCell(cord).getCellContent() == CellContent.NONE) {
            possibleMoves.add(cord);
        }
    }
}
