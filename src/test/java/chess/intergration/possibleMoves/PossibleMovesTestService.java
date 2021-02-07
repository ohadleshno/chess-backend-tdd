package chess.intergration.possibleMoves;

import chess.contracts.PossibleMovesResponseContract;
import chess.modals.Board;
import chess.modals.Cell;
import chess.modals.CellContent;
import chess.modals.Cord;
import chess.services.ChessBoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.stereotype.Component;

import static chess.intergration.Consts.URL_PATH;
import static chess.modals.Consts.ROWS_NUM;

@Component
public class PossibleMovesTestService {

    private static final String POSSIBLE_MOVES_ROUTE = URL_PATH + "/possibleMoves?row={row}&col={col}";

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private ChessBoardService chessBoardService;

    public void resetBoard() {
        this.chessBoardService.setBoard(new Board());
    }

    public void emptyBoard() {
        Cell[][] cells = new Cell[ROWS_NUM][ROWS_NUM];
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells.length; j++) {
                cells[i][j] = new Cell(null, CellContent.NONE);
            }
        }

        this.chessBoardService.setBoard(new Board(cells));
    }

    public PossibleMovesResponseContract getPossibleMoves(boolean isWhiteTurn, Cord cord) {
        chessBoardService.setWhiteTurn(isWhiteTurn);
        return this.restTemplate.getForObject(POSSIBLE_MOVES_ROUTE, PossibleMovesResponseContract.class, cord.getRow(), cord.getCol());
    }

    public void putPieceOnCell(Cord cord, Cell cell) {
        Cell[][] cells = chessBoardService.getBoard().getCells();
        cells[cord.getRow()][cord.getCol()] = cell;
        chessBoardService.setBoard(new Board(cells));
    }
}
