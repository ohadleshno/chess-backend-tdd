package chess.intergration;

import chess.contracts.PossibleMovesResponseContract;
import chess.modals.Cord;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class PawnPossibleMovesIntegrationTests {

    @Autowired
    private TestRestTemplate restTemplate;
    public static final String URL_PATH = "http://localhost:9874/api";
    private static final String POSSIBLE_MOVES_ROUTE = URL_PATH + "/possibleMoves?row={row}&col={col}";


    @Test
    public void GivenWhitePawnOnInitialPlace__WhenRequestingPossibleMoves__ThenCanMoveOneStepForward() {
        PossibleMovesResponseContract possibleMoves = this.restTemplate.getForObject(POSSIBLE_MOVES_ROUTE, PossibleMovesResponseContract.class, 6, 2);

        Cord possibleMove = new Cord(5,2);

        assertThat(possibleMoves.getPossibleMoves()).contains(possibleMove);
    }
}
