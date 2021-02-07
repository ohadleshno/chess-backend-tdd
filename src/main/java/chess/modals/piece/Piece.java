package chess.modals.piece;

import chess.modals.Board;
import chess.modals.CellContent;
import chess.modals.Cord;
import lombok.AllArgsConstructor;

import java.util.List;


public abstract class Piece {
    protected boolean isWhiteTurn;
    protected Cord cord;
    protected Board board;
    protected CellContent enemyCellContent;
    protected CellContent myCellContent;

    public Piece(boolean isWhiteTurn, Cord cord, Board board) {
        this.isWhiteTurn = isWhiteTurn;
        this.cord = cord;
        this.board = board;
        this.enemyCellContent = isWhiteTurn ? CellContent.BLACK_PIECE : CellContent.WHITE_PIECE;
        this.myCellContent = isWhiteTurn ? CellContent.WHITE_PIECE : CellContent.BLACK_PIECE;
    }

    public abstract List<Cord> getPossibleMoves();

    protected boolean isCellEmpty(Cord cord) {
        return this.board.getCell(cord).getCellContent() == CellContent.NONE;
    }

    protected boolean doesCellHasEnemyPiece(Cord cord) {
        return this.board.getCell(cord).getCellContent() == enemyCellContent;
    }

    protected boolean doesCellHasFriendPiece(Cord cord) {
        return this.board.getCell(cord).getCellContent() == myCellContent;
    }
}
