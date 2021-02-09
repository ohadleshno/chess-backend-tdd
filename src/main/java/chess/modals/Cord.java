package chess.modals;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import static chess.modals.Consts.FIRST_ROW;
import static chess.modals.Consts.LAST_ROW;

@AllArgsConstructor
@Getter
@Setter
@Builder(toBuilder = true)
@EqualsAndHashCode
@ToString
public class Cord {
    private int row;
    private int col;

    @JsonIgnore
    public boolean isCordNotInRange() {
        return row < FIRST_ROW || row > LAST_ROW || col > LAST_ROW || col < FIRST_ROW;
    }
}
