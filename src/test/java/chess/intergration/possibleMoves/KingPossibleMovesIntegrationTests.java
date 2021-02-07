package chess.intergration.possibleMoves;

import chess.contracts.PossibleMovesResponseContract;
import chess.modals.Cell;
import chess.modals.CellContent;
import chess.modals.Cord;
import chess.modals.PieceType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static chess.intergration.Consts.*;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class KingPossibleMovesIntegrationTests {

    @Autowired
    private PossibleMovesTestService possibleMovesTestService;

    @Test
    public void GivenKingNotSurroundedAtAll__WhenRequestingPossibleMoves__ThenCanMoveOneStepForwardInAnyDirection() {
        possibleMovesTestService.emptyBoard();
        Cord cord = new Cord(4, 4);
        possibleMovesTestService.putPieceOnCell(cord, new Cell(PieceType.KING, CellContent.WHITE_PIECE));

        PossibleMovesResponseContract PossibleMoves = possibleMovesTestService.getPossibleMoves(WHITE_TURN, cord);

        assertThat(PossibleMoves.getPossibleMoves()).contains(new Cord(4, 3), new Cord(4, 5), new Cord(3, 3), new Cord(3, 4), new Cord(3, 5), new Cord(5, 5), new Cord(5, 4), new Cord(5, 3));
    }

    @Test
    public void GivenKingOnIntialLocation__WhenRequestingPossibleMoves__ThenCantMoveAnyWhere() {
        possibleMovesTestService.resetBoard();

        PossibleMovesResponseContract PossibleMoves = possibleMovesTestService.getPossibleMoves(BLACK_TURN, INITIAL_BLACK_KING_CORD);

        assertThat(PossibleMoves.getPossibleMoves()).isEmpty();
    }
}
