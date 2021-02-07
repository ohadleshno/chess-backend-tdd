package chess.intergration;

import chess.contracts.MovePieceContract;
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

}
