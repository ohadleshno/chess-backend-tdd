package chess.contracts;

import chess.modals.Cord;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
@NoArgsConstructor
public class MovePieceContract {
    private Cord from;
    private Cord to;
}
