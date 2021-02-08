package chess.modals.piece;

import chess.modals.Board;
import chess.modals.Cord;

import java.util.LinkedList;
import java.util.List;

public class QueenPiece extends Piece {
    private final BishopPiece bishopPiece;
    private final RokPiece rokPiece;

    public QueenPiece(boolean isWhiteTurn, Cord cord, Board board) {
        super(isWhiteTurn, cord, board);
        this.bishopPiece = new BishopPiece(isWhiteTurn,cord,board);
        this.rokPiece = new RokPiece(isWhiteTurn,cord,board);
    }

    @Override
    public List<Cord> getPossibleMoves() {
        List<Cord> possibleMoves = new LinkedList<>();
        possibleMoves.addAll(bishopPiece.getPossibleMoves());
        possibleMoves.addAll(rokPiece.getPossibleMoves());
        return possibleMoves;
    }
}
