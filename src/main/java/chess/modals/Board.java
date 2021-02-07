package chess.modals;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import static chess.modals.Consts.ROWS_NUM;

@Getter
@EqualsAndHashCode
@AllArgsConstructor
public class Board {
    private final Cell[][] cells;

    public Board() {
        cells = new Cell[ROWS_NUM][ROWS_NUM];
        resetBoard();
    }

    public Cell getCell(Cord cord) {
        return this.cells[cord.getRow()][cord.getCol()];
    }

    public void movePiece(Cord from, Cord to) {
        Cell temp = cells[from.getRow()][from.getCol()];
        cells[from.getRow()][from.getCol()] = cells[to.getRow()][to.getCol()];
        cells[to.getRow()][to.getCol()] = temp;
    }

    private void resetBoard() {
        cells[0] = createFirstRow(CellContent.BLACK_PIECE);

        for (int i = 0; i < cells.length; i++) {
            cells[1][i] = new Cell(PieceType.PAWN, CellContent.BLACK_PIECE);
        }

        setEmptyPieces();

        for (int i = 0; i < cells.length; i++) {
            cells[6][i] = new Cell(PieceType.PAWN, CellContent.WHITE_PIECE);
        }

        cells[7] = createFirstRow(CellContent.WHITE_PIECE);
    }

    private Cell[] createFirstRow(CellContent cellContent) {
        return new Cell[]{new Cell(PieceType.ROK, cellContent),
                new Cell(PieceType.KNIGHT, cellContent),
                new Cell(PieceType.BISHOP, cellContent),
                new Cell(PieceType.QUEEN, cellContent),
                new Cell(PieceType.KING, cellContent),
                new Cell(PieceType.BISHOP, cellContent),
                new Cell(PieceType.KNIGHT, cellContent),
                new Cell(PieceType.ROK, cellContent)
        };
    }

    private void setEmptyPieces() {
        for (int i = 2; i < 6; i++) {
            for (int j = 0; j < cells.length; j++) {
                cells[i][j] = new Cell(null, CellContent.NONE);
            }
        }
    }
}
