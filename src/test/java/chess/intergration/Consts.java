package chess.intergration;

import chess.modals.Board;
import chess.modals.Cord;

public class Consts {
    public static final String URL_PATH = "http://localhost:9874/api";

    public static final Cord INITIAL_WHITE_PAWN_CORD = new Cord(6, 2);
    public static final Cord INITIAL_BLACK_PAWN_CORD = new Cord(1, 2);
    public static final Cord INITIAL_BLACK_KING_CORD = new Cord(0, 4);

    public static final boolean WHITE_TURN = true;
    public static final boolean BLACK_TURN = false;
    public static final Board INITIAL_BOARD = new Board();

}
