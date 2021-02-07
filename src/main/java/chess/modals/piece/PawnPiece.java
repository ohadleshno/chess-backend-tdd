package chess.modals.piece;

import chess.modals.Board;
import chess.modals.CellContent;
import chess.modals.Cord;

import java.util.LinkedList;
import java.util.List;

import static chess.modals.Consts.*;

public class PawnPiece extends Piece {

    private final int moveDir;

    public PawnPiece(boolean isWhiteTurn, Cord cord, Board board) {
        super(isWhiteTurn, cord, board);
        this.moveDir = this.isWhiteTurn ? DOWN : UP;
    }

    @Override
    public List<Cord> getPossibleMoves() {
        List<Cord> possibleMoves = new LinkedList<>();
        addPawnForwardMoves(possibleMoves);
        addPawnDiagonalMoves(possibleMoves);
        return possibleMoves;
    }

    private void addPawnDiagonalMoves(List<Cord> possibleMoves) {
        Cord leftEnemy = new Cord(cord.getRow() + moveDir, cord.getCol() + RIGHT);
        Cord rightEnemy = new Cord(cord.getRow() + moveDir, cord.getCol() + LEFT);

        if (doesCellHasEnemyPiece(leftEnemy)) {
            possibleMoves.add(leftEnemy);
        }

        if (doesCellHasEnemyPiece(rightEnemy)) {
            possibleMoves.add(rightEnemy);
        }
    }

    private void addPawnForwardMoves(List<Cord> possibleMoves) {
        Cord oneStepForward = new Cord(cord.getRow() + moveDir, cord.getCol());
        Cord twoStepForward = new Cord(cord.getRow() + (2 * moveDir), cord.getCol());

        if (!isCellEmpty(oneStepForward)) {
            return;
        }

        possibleMoves.add(oneStepForward);

        if (isPawnOnInitialLocation(cord) && isCellEmpty(twoStepForward)) {
            possibleMoves.add(twoStepForward);
        }
    }

    private boolean isPawnOnInitialLocation(Cord cord) {
        return cord.getRow() == BLACK_SECOND_ROW || cord.getRow() == WHITE_SECOND_ROW;
    }
}
