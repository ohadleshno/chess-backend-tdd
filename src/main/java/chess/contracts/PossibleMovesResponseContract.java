package chess.contracts;


import chess.modals.Cord;

import java.util.List;


public class PossibleMovesResponseContract {

    private List<Cord> possibleMoves;


    public PossibleMovesResponseContract() {
    }

    public List<Cord> getPossibleMoves() {
        return possibleMoves;
    }

    public void setPossibleMoves(List<Cord> possibleMoves) {
        this.possibleMoves = possibleMoves;
    }
}
