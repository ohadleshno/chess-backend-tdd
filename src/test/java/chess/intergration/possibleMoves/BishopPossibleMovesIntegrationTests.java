package chess.intergration.possibleMoves;

import chess.contracts.PossibleMovesResponseContract;
import chess.modals.Cell;
import chess.modals.CellContent;
import chess.modals.Cord;
import chess.modals.PieceType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static chess.intergration.Consts.WHITE_TURN;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class BishopPossibleMovesIntegrationTests {

    @Autowired
    private PossibleMovesTestService possibleMovesTestService;

    @BeforeEach
    public void clean() {
        possibleMovesTestService.emptyBoard();
    }


    @Test
    public void GivenWhiteBishop__WhenRequestingPossibleMoves__ThenMoveAnyDiagonalDirection() {
        Cord cord = new Cord(4, 4);
        possibleMovesTestService.putPieceOnCell(cord, new Cell(PieceType.BISHOP, CellContent.WHITE_PIECE));

        PossibleMovesResponseContract PossibleMoves = possibleMovesTestService.getPossibleMoves(WHITE_TURN, cord);

        assertThat(PossibleMoves.getPossibleMoves()).contains(new Cord(0, 0),
                new Cord(1, 1), new Cord(2, 2),
                new Cord(3, 3), new Cord(5, 5),
                new Cord(6, 6), new Cord(7, 7),
                new Cord(7, 1), new Cord(6, 2),
                new Cord(5, 3), new Cord(3, 5),
                new Cord(2, 6), new Cord(1, 7));
    }

    @Test
    public void GivenWhiteBishopBlockedByFriend__WhenRequestingPossibleMoves__ThenCantThroughHim() {
        Cord cord = new Cord(4, 4);
        Cord friendCord = new Cord(5, 5);
        possibleMovesTestService.putPieceOnCell(cord, new Cell(PieceType.BISHOP, CellContent.WHITE_PIECE));
        possibleMovesTestService.putPieceOnCell(friendCord, new Cell(PieceType.BISHOP, CellContent.WHITE_PIECE));

        PossibleMovesResponseContract PossibleMoves = possibleMovesTestService.getPossibleMoves(WHITE_TURN, cord);

        assertThat(PossibleMoves.getPossibleMoves()).doesNotContain(new Cord(5, 5), new Cord(6, 6), new Cord(7, 7));
    }

    @Test
    public void GivenWhiteBishopBlockedByEnemy__WhenRequestingPossibleMoves__ThenMoveCanMoveToHisLocation() {
        Cord cord = new Cord(4, 4);
        Cord friendCord = new Cord(5, 5);
        possibleMovesTestService.putPieceOnCell(cord, new Cell(PieceType.BISHOP, CellContent.WHITE_PIECE));
        possibleMovesTestService.putPieceOnCell(friendCord, new Cell(PieceType.BISHOP, CellContent.BLACK_PIECE));

        PossibleMovesResponseContract PossibleMoves = possibleMovesTestService.getPossibleMoves(WHITE_TURN, cord);

        assertThat(PossibleMoves.getPossibleMoves()).contains(new Cord(5, 5));
    }

    @Test
    public void GivenWhiteBishopBlockedByEnemy__WhenRequestingPossibleMoves__ThenMoveCantMoveAfterHisLocation() {
        Cord cord = new Cord(4, 4);
        Cord friendCord = new Cord(5, 5);
        possibleMovesTestService.putPieceOnCell(cord, new Cell(PieceType.BISHOP, CellContent.WHITE_PIECE));
        possibleMovesTestService.putPieceOnCell(friendCord, new Cell(PieceType.BISHOP, CellContent.WHITE_PIECE));

        PossibleMovesResponseContract PossibleMoves = possibleMovesTestService.getPossibleMoves(WHITE_TURN, cord);

        assertThat(PossibleMoves.getPossibleMoves()).doesNotContain(new Cord(6, 6), new Cord(7, 7));
    }
}
