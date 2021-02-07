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

import static chess.intergration.Consts.*;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class PawnPossibleMovesIntegrationTests {

    @Autowired
    private PossibleMovesTestService possibleMovesTestService;

    @BeforeEach
    public void clean(){
        possibleMovesTestService.resetBoard();
    }

    @Test
    public void GivenWhitePawnOnInitialPlace__WhenRequestingPossibleMoves__ThenCanMoveOneStepForward() {
        PossibleMovesResponseContract PossibleMoves = possibleMovesTestService.getPossibleMoves(WHITE_TURN, INITIAL_WHITE_PAWN_CORD);

        Cord possibleMove = INITIAL_WHITE_PAWN_CORD.toBuilder().row(INITIAL_WHITE_PAWN_CORD.getRow() - 1).build();

        assertThat(PossibleMoves.getPossibleMoves()).contains(possibleMove);
    }

    @Test
    public void GivenWhitePawnOnInitialPlace__WhenRequestingPossibleMoves__ThenCanMoveTwoStepForward() {
        PossibleMovesResponseContract PossibleMoves = possibleMovesTestService.getPossibleMoves(WHITE_TURN, INITIAL_WHITE_PAWN_CORD);

        Cord possibleMove = INITIAL_WHITE_PAWN_CORD.toBuilder().row(INITIAL_WHITE_PAWN_CORD.getRow() - 2).build();

        assertThat(PossibleMoves.getPossibleMoves()).contains(possibleMove);
    }

    @Test
    public void GivenBlackPawnOnInitialPlace__WhenRequestingPossibleMoves__ThenCanMoveOneStepForward() {
        PossibleMovesResponseContract PossibleMoves = possibleMovesTestService.getPossibleMoves(BLACK_TURN, INITIAL_BLACK_PAWN_CORD);

        Cord possibleMove = INITIAL_BLACK_PAWN_CORD.toBuilder().row(INITIAL_BLACK_PAWN_CORD.getRow() + 1).build();

        assertThat(PossibleMoves.getPossibleMoves()).contains(possibleMove);
    }

    @Test
    public void GivenBlackPawnOnInitialPlace__WhenRequestingPossibleMoves__ThenCanMoveTwoStepForward() {
        PossibleMovesResponseContract PossibleMoves = possibleMovesTestService.getPossibleMoves(BLACK_TURN, INITIAL_BLACK_PAWN_CORD);

        Cord possibleMove = INITIAL_BLACK_PAWN_CORD.toBuilder().row(INITIAL_BLACK_PAWN_CORD.getRow() + 2).build();

        assertThat(PossibleMoves.getPossibleMoves()).contains(possibleMove);
    }

    @Test
    public void GivenEnemySoliderInDiagonalCells__WhenRequestingPossibleMoves__ThenCanMoveThere() {
        int initialBlackPawnCordCol = INITIAL_BLACK_PAWN_CORD.getCol();
        int initialBlackPawnCordRow = INITIAL_BLACK_PAWN_CORD.getRow();
        Cord enemyLocation = INITIAL_BLACK_PAWN_CORD.toBuilder().col(initialBlackPawnCordCol +1).row(initialBlackPawnCordRow +1).build();
        Cord secondEnemyLocation = INITIAL_BLACK_PAWN_CORD.toBuilder().col(initialBlackPawnCordCol -1).row(initialBlackPawnCordRow +1).build();
        possibleMovesTestService.putPieceOnCell(INITIAL_BLACK_PAWN_CORD, new Cell(PieceType.PAWN, CellContent.BLACK_PIECE));
        possibleMovesTestService.putPieceOnCell(enemyLocation, new Cell(PieceType.PAWN, CellContent.WHITE_PIECE));
        possibleMovesTestService.putPieceOnCell(secondEnemyLocation, new Cell(PieceType.PAWN, CellContent.WHITE_PIECE));

        PossibleMovesResponseContract PossibleMoves = possibleMovesTestService.getPossibleMoves(BLACK_TURN, INITIAL_BLACK_PAWN_CORD);

        assertThat(PossibleMoves.getPossibleMoves()).contains(enemyLocation, secondEnemyLocation);
    }

    @Test
    public void GivenFriendlySoliderInDiagonalCells__WhenRequestingPossibleMoves__ThenCantMoveThere() {
        Cord friendLocation = new Cord(2, 3);
        possibleMovesTestService.putPieceOnCell(new Cord(1, 2), new Cell(PieceType.PAWN, CellContent.BLACK_PIECE));
        possibleMovesTestService.putPieceOnCell(friendLocation, new Cell(PieceType.PAWN, CellContent.BLACK_PIECE));

        PossibleMovesResponseContract PossibleMoves = possibleMovesTestService.getPossibleMoves(BLACK_TURN, INITIAL_BLACK_PAWN_CORD);

        assertThat(PossibleMoves.getPossibleMoves()).doesNotContain(friendLocation);
    }

    @Test
    public void GivenPawnNotOnInitialLocation__WhenRequestingPossibleMoves__ThenCantDoTwoStepForward() {
        Cord location = INITIAL_BLACK_PAWN_CORD.toBuilder().row(INITIAL_BLACK_PAWN_CORD.getRow() +1).build();
        possibleMovesTestService.putPieceOnCell(location, new Cell(PieceType.PAWN, CellContent.BLACK_PIECE));

        PossibleMovesResponseContract PossibleMoves = possibleMovesTestService.getPossibleMoves(BLACK_TURN, INITIAL_BLACK_PAWN_CORD);
        Cord twoStepForward = location.toBuilder().row(location.getRow() +2).build();

        assertThat(PossibleMoves.getPossibleMoves()).doesNotContain(twoStepForward);
    }

    @Test
    public void GivenPawnWithFriendOneStepForward__WhenRequestingPossibleMoves__ThenCantDoTwoStepForward() {
        Cord friendLocation = INITIAL_BLACK_PAWN_CORD.toBuilder().row(INITIAL_BLACK_PAWN_CORD.getRow()+1).build();
        possibleMovesTestService.putPieceOnCell(friendLocation, new Cell(PieceType.PAWN, CellContent.BLACK_PIECE));

        PossibleMovesResponseContract PossibleMoves = possibleMovesTestService.getPossibleMoves(BLACK_TURN, INITIAL_BLACK_PAWN_CORD);
        Cord twoStepForward = INITIAL_BLACK_PAWN_CORD.toBuilder().row(INITIAL_BLACK_PAWN_CORD.getRow() +2).build();

        assertThat(PossibleMoves.getPossibleMoves()).doesNotContain(twoStepForward);
    }

    @Test
    public void GivenPawnWithFriendOneStepForward__WhenRequestingPossibleMoves__ThenCantMoveThere() {
        Cord friendLocation = INITIAL_BLACK_PAWN_CORD.toBuilder().row(INITIAL_BLACK_PAWN_CORD.getRow()+1).build();
        possibleMovesTestService.putPieceOnCell(friendLocation, new Cell(PieceType.PAWN, CellContent.BLACK_PIECE));

        PossibleMovesResponseContract PossibleMoves = possibleMovesTestService.getPossibleMoves(BLACK_TURN, INITIAL_BLACK_PAWN_CORD);

        assertThat(PossibleMoves.getPossibleMoves()).doesNotContain(friendLocation);
    }

    @Test
    public void GivenPawnWithFriendTwoStepForward__WhenRequestingPossibleMoves__ThenCantMoveThere() {
        Cord friendLocation = INITIAL_BLACK_PAWN_CORD.toBuilder().row(INITIAL_BLACK_PAWN_CORD.getRow()+2).build();
        possibleMovesTestService.putPieceOnCell(friendLocation, new Cell(PieceType.PAWN, CellContent.BLACK_PIECE));

        PossibleMovesResponseContract PossibleMoves = possibleMovesTestService.getPossibleMoves(BLACK_TURN, INITIAL_BLACK_PAWN_CORD);

        assertThat(PossibleMoves.getPossibleMoves()).doesNotContain(friendLocation);
    }

}
