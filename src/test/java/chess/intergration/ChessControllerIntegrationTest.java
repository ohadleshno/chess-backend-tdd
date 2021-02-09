package chess.intergration;

import chess.contracts.MovePieceContract;
import chess.intergration.possibleMoves.PossibleMovesTestService;
import chess.modals.Board;
import chess.modals.Cord;
import chess.services.ChessBoardService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

import static chess.intergration.Consts.*;
import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class ChessControllerIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private ChessBoardService chessBoardService;

    @Autowired
    private PossibleMovesTestService testService;

    @Test
    public void GivenWhiteTurnOnInitialBoard__WhenMovingWhitePiece__ThenPieceWillLocationUpdate() {
        String url = URL_PATH + "/move";
        Board board = new Board();
        Cord to = INITIAL_WHITE_PAWN_CORD.toBuilder().row(INITIAL_WHITE_PAWN_CORD.getRow() - 1).build();
        board.movePiece(INITIAL_WHITE_PAWN_CORD, to);
        MovePieceContract movePieceContract = new MovePieceContract(INITIAL_WHITE_PAWN_CORD, to);
        restTemplate.postForEntity(url, movePieceContract, String.class);
        assertThat(chessBoardService.getBoard()).isEqualTo(board);
    }

    @Test
    public void WhenMoving__ThenChangeChangeColorTurn() {
        String url = URL_PATH + "/move";
        Cord to = INITIAL_WHITE_PAWN_CORD.toBuilder().row(INITIAL_WHITE_PAWN_CORD.getRow() - 1).build();
        MovePieceContract movePieceContract = new MovePieceContract(INITIAL_WHITE_PAWN_CORD, to);
        restTemplate.postForEntity(url, movePieceContract, String.class);
        assertThat(chessBoardService.isWhiteTurn()).isEqualTo(false);
    }

    @Test
    public void GivenBoard__WhenReseting__ThenWillResetToInit() {
        String url = URL_PATH + "/reset";
        testService.emptyBoard();
        restTemplate.getForEntity(url, String.class);
        assertThat(chessBoardService.getBoard()).isEqualTo(new Board());
        assertThat(chessBoardService.isWhiteTurn()).isEqualTo(true);
    }

}
