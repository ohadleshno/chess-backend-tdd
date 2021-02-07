package chess.contracts;


import chess.modals.Cord;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
@NoArgsConstructor
public class PossibleMovesResponseContract {

    private List<Cord> possibleMoves;
}
