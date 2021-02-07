package chess.unit.modals;

import chess.modals.*;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BoardTest {

    @Test
    public void boardOnStartWillHaveAllPiecesInTheRightLocation() {
        Board actual = new Board();
        assertThat(actual.getCells()).isEqualTo(new Cell[][]{
                {new Cell(PieceType.ROK, CellContent.BLACK_PIECE), new Cell(PieceType.KNIGHT, CellContent.BLACK_PIECE), new Cell(PieceType.BISHOP, CellContent.BLACK_PIECE), new Cell(PieceType.QUEEN, CellContent.BLACK_PIECE), new Cell(PieceType.KING, CellContent.BLACK_PIECE), new Cell(PieceType.BISHOP, CellContent.BLACK_PIECE), new Cell(PieceType.KNIGHT, CellContent.BLACK_PIECE), new Cell(PieceType.ROK, CellContent.BLACK_PIECE)},
                {new Cell(PieceType.PAWN, CellContent.BLACK_PIECE), new Cell(PieceType.PAWN, CellContent.BLACK_PIECE), new Cell(PieceType.PAWN, CellContent.BLACK_PIECE), new Cell(PieceType.PAWN, CellContent.BLACK_PIECE), new Cell(PieceType.PAWN, CellContent.BLACK_PIECE), new Cell(PieceType.PAWN, CellContent.BLACK_PIECE), new Cell(PieceType.PAWN, CellContent.BLACK_PIECE), new Cell(PieceType.PAWN, CellContent.BLACK_PIECE)},
                {new Cell(null, CellContent.NONE), new Cell(null, CellContent.NONE), new Cell(null, CellContent.NONE), new Cell(null, CellContent.NONE), new Cell(null, CellContent.NONE), new Cell(null, CellContent.NONE), new Cell(null, CellContent.NONE), new Cell(null, CellContent.NONE)},
                {new Cell(null, CellContent.NONE), new Cell(null, CellContent.NONE), new Cell(null, CellContent.NONE), new Cell(null, CellContent.NONE), new Cell(null, CellContent.NONE), new Cell(null, CellContent.NONE), new Cell(null, CellContent.NONE), new Cell(null, CellContent.NONE)},
                {new Cell(null, CellContent.NONE), new Cell(null, CellContent.NONE), new Cell(null, CellContent.NONE), new Cell(null, CellContent.NONE), new Cell(null, CellContent.NONE), new Cell(null, CellContent.NONE), new Cell(null, CellContent.NONE), new Cell(null, CellContent.NONE)},
                {new Cell(null, CellContent.NONE), new Cell(null, CellContent.NONE), new Cell(null, CellContent.NONE), new Cell(null, CellContent.NONE), new Cell(null, CellContent.NONE), new Cell(null, CellContent.NONE), new Cell(null, CellContent.NONE), new Cell(null, CellContent.NONE)},
                {new Cell(PieceType.PAWN, CellContent.WHITE_PIECE), new Cell(PieceType.PAWN, CellContent.WHITE_PIECE), new Cell(PieceType.PAWN, CellContent.WHITE_PIECE), new Cell(PieceType.PAWN, CellContent.WHITE_PIECE), new Cell(PieceType.PAWN, CellContent.WHITE_PIECE), new Cell(PieceType.PAWN, CellContent.WHITE_PIECE), new Cell(PieceType.PAWN, CellContent.WHITE_PIECE), new Cell(PieceType.PAWN, CellContent.WHITE_PIECE)},
                {new Cell(PieceType.ROK, CellContent.WHITE_PIECE), new Cell(PieceType.KNIGHT, CellContent.WHITE_PIECE), new Cell(PieceType.BISHOP, CellContent.WHITE_PIECE), new Cell(PieceType.QUEEN, CellContent.WHITE_PIECE), new Cell(PieceType.KING, CellContent.WHITE_PIECE), new Cell(PieceType.BISHOP, CellContent.WHITE_PIECE), new Cell(PieceType.KNIGHT, CellContent.WHITE_PIECE), new Cell(PieceType.ROK, CellContent.WHITE_PIECE)}
        });
    }

    @Test
    public void GivenBoardWhenMovingPieceThenBoardWillBeUpdated() {
        Board actual = new Board();
        actual.movePiece(new Cord(1, 3), new Cord(2, 3));
        assertThat(actual.getCells()).isEqualTo(new Cell[][]{
                {new Cell(PieceType.ROK, CellContent.BLACK_PIECE), new Cell(PieceType.KNIGHT, CellContent.BLACK_PIECE), new Cell(PieceType.BISHOP, CellContent.BLACK_PIECE), new Cell(PieceType.QUEEN, CellContent.BLACK_PIECE), new Cell(PieceType.KING, CellContent.BLACK_PIECE), new Cell(PieceType.BISHOP, CellContent.BLACK_PIECE), new Cell(PieceType.KNIGHT, CellContent.BLACK_PIECE), new Cell(PieceType.ROK, CellContent.BLACK_PIECE)},
                {new Cell(PieceType.PAWN, CellContent.BLACK_PIECE), new Cell(PieceType.PAWN, CellContent.BLACK_PIECE), new Cell(PieceType.PAWN, CellContent.BLACK_PIECE), new Cell(null, CellContent.NONE), new Cell(PieceType.PAWN, CellContent.BLACK_PIECE), new Cell(PieceType.PAWN, CellContent.BLACK_PIECE), new Cell(PieceType.PAWN, CellContent.BLACK_PIECE), new Cell(PieceType.PAWN, CellContent.BLACK_PIECE)},
                {new Cell(null, CellContent.NONE), new Cell(null, CellContent.NONE), new Cell(null, CellContent.NONE), new Cell(PieceType.PAWN, CellContent.BLACK_PIECE), new Cell(null, CellContent.NONE), new Cell(null, CellContent.NONE), new Cell(null, CellContent.NONE), new Cell(null, CellContent.NONE)},
                {new Cell(null, CellContent.NONE), new Cell(null, CellContent.NONE), new Cell(null, CellContent.NONE), new Cell(null, CellContent.NONE), new Cell(null, CellContent.NONE), new Cell(null, CellContent.NONE), new Cell(null, CellContent.NONE), new Cell(null, CellContent.NONE)},
                {new Cell(null, CellContent.NONE), new Cell(null, CellContent.NONE), new Cell(null, CellContent.NONE), new Cell(null, CellContent.NONE), new Cell(null, CellContent.NONE), new Cell(null, CellContent.NONE), new Cell(null, CellContent.NONE), new Cell(null, CellContent.NONE)},
                {new Cell(null, CellContent.NONE), new Cell(null, CellContent.NONE), new Cell(null, CellContent.NONE), new Cell(null, CellContent.NONE), new Cell(null, CellContent.NONE), new Cell(null, CellContent.NONE), new Cell(null, CellContent.NONE), new Cell(null, CellContent.NONE)},
                {new Cell(PieceType.PAWN, CellContent.WHITE_PIECE), new Cell(PieceType.PAWN, CellContent.WHITE_PIECE), new Cell(PieceType.PAWN, CellContent.WHITE_PIECE), new Cell(PieceType.PAWN, CellContent.WHITE_PIECE), new Cell(PieceType.PAWN, CellContent.WHITE_PIECE), new Cell(PieceType.PAWN, CellContent.WHITE_PIECE), new Cell(PieceType.PAWN, CellContent.WHITE_PIECE), new Cell(PieceType.PAWN, CellContent.WHITE_PIECE)},
                {new Cell(PieceType.ROK, CellContent.WHITE_PIECE), new Cell(PieceType.KNIGHT, CellContent.WHITE_PIECE), new Cell(PieceType.BISHOP, CellContent.WHITE_PIECE), new Cell(PieceType.QUEEN, CellContent.WHITE_PIECE), new Cell(PieceType.KING, CellContent.WHITE_PIECE), new Cell(PieceType.BISHOP, CellContent.WHITE_PIECE), new Cell(PieceType.KNIGHT, CellContent.WHITE_PIECE), new Cell(PieceType.ROK, CellContent.WHITE_PIECE)}
        });
    }
}
