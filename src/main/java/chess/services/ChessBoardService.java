package chess.services;

import chess.exception.NoPieceException;
import chess.modals.*;
import chess.modals.piece.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

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
    }

    public List<Cord> getPossibleMoves(Cord cord) throws NoPieceException {
        return getPiece(cord).getPossibleMoves();
    }

    @NotNull
    private Piece getPiece(Cord cord) throws NoPieceException {
        PieceType pieceType = this.board.getCell(cord).getPieceType();
        switch (pieceType) {
            case KING:
                return  new KingPiece(isWhiteTurn, cord, board);
            case PAWN:
                return  new PawnPiece(isWhiteTurn, cord, board);
            case ROK:
                return  new RokPiece(isWhiteTurn, cord, board);
            case KNIGHT:
                return new KnightPiece(isWhiteTurn, cord, board);
            default:
                throw new NoPieceException("No piece on cord");
        }
    }


}
