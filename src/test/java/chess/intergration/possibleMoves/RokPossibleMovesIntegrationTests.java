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
public class RokPossibleMovesIntegrationTests {

    @Autowired
    private PossibleMovesTestService possibleMovesTestService;

    @BeforeEach
    public void clean() {
        possibleMovesTestService.emptyBoard();
    }

    @Test
    public void GivenOnlyWhiteRokOnBoard__WhenRequestingPossibleMoves__ThenCanMoveAnyHorizontalPiece() {
        Cord rokLocation = new Cord(1, 1);
        possibleMovesTestService.putPieceOnCell(rokLocation, new Cell(PieceType.ROK, CellContent.WHITE_PIECE));

        PossibleMovesResponseContract PossibleMoves = possibleMovesTestService.getPossibleMoves(WHITE_TURN, rokLocation);

        assertThat(PossibleMoves.getPossibleMoves()).contains(new Cord(0, 1), new Cord(2, 1), new Cord(3, 1), new Cord(4, 1), new Cord(5, 1), new Cord(6, 1), new Cord(7, 1));
    }

    @Test
    public void GivenOnlyWhiteRokOnBoard__WhenRequestingPossibleMoves__ThenCanMoveAnyVerticalPiece() {
        Cord rokLocation = new Cord(1, 1);
        possibleMovesTestService.putPieceOnCell(rokLocation, new Cell(PieceType.ROK, CellContent.WHITE_PIECE));

        PossibleMovesResponseContract PossibleMoves = possibleMovesTestService.getPossibleMoves(WHITE_TURN, rokLocation);

        assertThat(PossibleMoves.getPossibleMoves()).contains(new Cord(1, 0), new Cord(1, 2), new Cord(1, 3), new Cord(1, 4), new Cord(1, 5), new Cord(1, 6), new Cord(1, 7));
    }

    @Test
    public void GivenWhiteRokOnBoardAndEnemyTwoStepsForward__WhenRequestingPossibleMoves__ThenCanMoveUntilVerticalEnemyPiece() {
        Cord rokLocation = new Cord(1, 1);
        Cord enemyLocation = new Cord(1, 3);
        possibleMovesTestService.putPieceOnCell(rokLocation, new Cell(PieceType.ROK, CellContent.WHITE_PIECE));
        possibleMovesTestService.putPieceOnCell(enemyLocation, new Cell(PieceType.ROK, CellContent.BLACK_PIECE));

        PossibleMovesResponseContract PossibleMoves = possibleMovesTestService.getPossibleMoves(WHITE_TURN, rokLocation);

        assertThat(PossibleMoves.getPossibleMoves()).contains(new Cord(1, 0), new Cord(1, 2), new Cord(1, 3));
    }

    @Test
    public void GivenWhiteRokOnBoardAndEnemyTwoStepsBackward__WhenRequestingPossibleMoves__ThenCanMoveUntilVerticalEnemyPiece() {
        Cord rokLocation = new Cord(1, 6);
        Cord enemyLocation = new Cord(1, 4);
        possibleMovesTestService.putPieceOnCell(rokLocation, new Cell(PieceType.ROK, CellContent.WHITE_PIECE));
        possibleMovesTestService.putPieceOnCell(enemyLocation, new Cell(PieceType.ROK, CellContent.BLACK_PIECE));

        PossibleMovesResponseContract PossibleMoves = possibleMovesTestService.getPossibleMoves(WHITE_TURN, rokLocation);

        assertThat(PossibleMoves.getPossibleMoves()).contains(new Cord(1, 4), new Cord(1, 5), new Cord(1, 7));
    }

    @Test
    public void GivenWhiteRokOnBoardAndEnemyTwoStepsRight__WhenRequestingPossibleMoves__ThenCanMoveUntilVerticalEnemyPiece() {
        Cord rokLocation = new Cord(1, 4);
        Cord enemyLocation = new Cord(3, 4);
        possibleMovesTestService.putPieceOnCell(rokLocation, new Cell(PieceType.ROK, CellContent.WHITE_PIECE));
        possibleMovesTestService.putPieceOnCell(enemyLocation, new Cell(PieceType.ROK, CellContent.BLACK_PIECE));

        PossibleMovesResponseContract PossibleMoves = possibleMovesTestService.getPossibleMoves(WHITE_TURN, rokLocation);

        assertThat(PossibleMoves.getPossibleMoves()).contains(new Cord(0, 4), new Cord(2, 4), new Cord(3, 4));
    }

    @Test
    public void GivenWhiteRokOnBoardAndEnemyOneStepsLeft__WhenRequestingPossibleMoves__ThenCanMoveUntilVerticalEnemyPiece() {
        Cord rokLocation = new Cord(7, 4);
        Cord enemyLocation = new Cord(6, 4);
        possibleMovesTestService.putPieceOnCell(rokLocation, new Cell(PieceType.ROK, CellContent.WHITE_PIECE));
        possibleMovesTestService.putPieceOnCell(enemyLocation, new Cell(PieceType.ROK, CellContent.BLACK_PIECE));

        PossibleMovesResponseContract PossibleMoves = possibleMovesTestService.getPossibleMoves(WHITE_TURN, rokLocation);

        assertThat(PossibleMoves.getPossibleMoves()).contains(new Cord(6, 4));
    }

    @Test
    public void GivenWhiteRokOnBoardAndFriendOneStepsLeft__WhenRequestingPossibleMoves__ThenCantMoveThere() {
        Cord rokLocation = new Cord(7, 4);
        Cord friendLocation = new Cord(6, 4);
        possibleMovesTestService.putPieceOnCell(rokLocation, new Cell(PieceType.ROK, CellContent.WHITE_PIECE));
        possibleMovesTestService.putPieceOnCell(friendLocation, new Cell(PieceType.ROK, CellContent.WHITE_PIECE));

        PossibleMovesResponseContract PossibleMoves = possibleMovesTestService.getPossibleMoves(WHITE_TURN, rokLocation);

        assertThat(PossibleMoves.getPossibleMoves()).doesNotContain(friendLocation);
    }
}
