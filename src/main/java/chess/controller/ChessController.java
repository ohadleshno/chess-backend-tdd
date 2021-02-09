package chess.controller;

import chess.contracts.MovePieceContract;
import chess.contracts.PossibleMovesResponseContract;
import chess.exception.NoPieceException;
import chess.modals.Board;
import chess.modals.Cord;
import chess.services.ChessBoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class ChessController {

    private final ChessBoardService chessBoardService;

    public ChessController(@Autowired ChessBoardService chessBoardService) {
        this.chessBoardService = chessBoardService;
    }

    @RequestMapping("possibleMoves")
    public PossibleMovesResponseContract getPossibleMoves(@RequestParam int row, @RequestParam int col) throws NoPieceException {
        return new PossibleMovesResponseContract(chessBoardService.getPossibleMoves(new Cord(row,col)));
    }

    @PostMapping("move")
    public void moveCell(@RequestBody MovePieceContract movePieceContract) {
        chessBoardService.move(movePieceContract.getFrom(), movePieceContract.getTo());
    }

    @RequestMapping("reset")
    public void moveCell() {
        chessBoardService.resetBoard();
    }
}
