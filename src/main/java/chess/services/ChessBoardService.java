package chess.services;

import chess.exception.NoPieceException;
import chess.modals.*;
import chess.modals.piece.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
@NoArgsConstructor
@Getter
@Setter
public class ChessBoardService {

    private Board board = new Board();

    private boolean isWhiteTurn = true;

    public void move(Cord from, Cord to) {
        board.movePiece(from, to);
        isWhiteTurn = !isWhiteTurn;
    }

    public List<Cord> getPossibleMoves(Cord cord) throws NoPieceException {
        //todo add test
        if (cord.isCordNotInRange() || board.getCell(cord).getCellContent() == CellContent.NONE) {
            return Collections.emptyList();
        }
        return getPiece(cord).getPossibleMoves();
    }

    public void resetBoard() {
        isWhiteTurn = true;
        board = new Board();
    }

    @NotNull
    private Piece getPiece(Cord cord) throws NoPieceException {
        PieceType pieceType = this.board.getCell(cord).getPieceType();
        switch (pieceType) {
            case KING:
                return new KingPiece(isWhiteTurn, cord, board);
            case PAWN:
                return new PawnPiece(isWhiteTurn, cord, board);
            case ROK:
                return new RokPiece(isWhiteTurn, cord, board);
            case KNIGHT:
                return new KnightPiece(isWhiteTurn, cord, board);
            case BISHOP:
                return new BishopPiece(isWhiteTurn, cord, board);
            case QUEEN:
                return new QueenPiece(isWhiteTurn, cord, board);
            default:
                throw new NoPieceException("No piece on cord");
        }
    }


}
