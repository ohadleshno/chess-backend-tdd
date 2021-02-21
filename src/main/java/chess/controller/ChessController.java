package chess.controller;

import chess.contracts.MovePieceContract;
import chess.contracts.PossibleMovesResponseContract;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class ChessController {

    @RequestMapping("possibleMoves")
    public PossibleMovesResponseContract getPossibleMoves(@RequestParam int row, @RequestParam int col) {
        return new PossibleMovesResponseContract();
    }

    @PostMapping("move")
    public void moveCell(@RequestBody MovePieceContract movePieceContract) {
    }

    @RequestMapping("reset")
    public void moveCell() {

    }
}
