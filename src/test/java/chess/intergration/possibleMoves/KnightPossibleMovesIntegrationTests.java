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
public class KnightPossibleMovesIntegrationTests {

    @Autowired
    private PossibleMovesTestService possibleMovesTestService;

    @BeforeEach
    public void clean() {
        possibleMovesTestService.emptyBoard();
    }

    @Test
    public void GivenOnlyWhiteKnightOnBoard__WhenRequestingPossibleMoves__ThenCanInLDirection() {
        Cord knightLocation = new Cord(4, 4);
        possibleMovesTestService.putPieceOnCell(knightLocation, new Cell(PieceType.KNIGHT, CellContent.WHITE_PIECE));
        PossibleMovesResponseContract PossibleMoves = possibleMovesTestService.getPossibleMoves(WHITE_TURN, knightLocation);

        assertThat(PossibleMoves.getPossibleMoves()).contains(new Cord(2, 3), new Cord(2, 5), new Cord(5, 6), new Cord(5, 2), new Cord(6, 3), new Cord(6, 5), new Cord(3, 2), new Cord(3, 6));
    }

    @Test
    public void GivenWhiteKnightOnBoardAndEnemyPiece__WhenRequestingPossibleMoves__ThenCanMoveToEnemyLocation() {
        Cord knightLocation = new Cord(4, 4);
        Cord enemyLocation = new Cord(2, 3);
        possibleMovesTestService.putPieceOnCell(knightLocation, new Cell(PieceType.KNIGHT, CellContent.WHITE_PIECE));
        possibleMovesTestService.putPieceOnCell(enemyLocation, new Cell(PieceType.KNIGHT, CellContent.BLACK_PIECE));
        PossibleMovesResponseContract PossibleMoves = possibleMovesTestService.getPossibleMoves(WHITE_TURN, knightLocation);

        assertThat(PossibleMoves.getPossibleMoves()).contains(enemyLocation);
    }

    @Test
    public void GivenWhiteKnightOnBoardAndFriendPiece__WhenRequestingPossibleMoves__ThenCantMoveToEnemyLocation() {
        Cord knightLocation = new Cord(4, 4);
        Cord friendLocation = new Cord(2, 3);
        possibleMovesTestService.putPieceOnCell(knightLocation, new Cell(PieceType.KNIGHT, CellContent.WHITE_PIECE));
        possibleMovesTestService.putPieceOnCell(friendLocation, new Cell(PieceType.KNIGHT, CellContent.WHITE_PIECE));
        PossibleMovesResponseContract PossibleMoves = possibleMovesTestService.getPossibleMoves(WHITE_TURN, knightLocation);

        assertThat(PossibleMoves.getPossibleMoves()).doesNotContain(friendLocation);
    }
}
