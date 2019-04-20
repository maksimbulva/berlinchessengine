package ru.maksimbulva.berlinchess.engine.move

import ru.maksimbulva.berlinchess.engine.board.PieceOnBoard
import ru.maksimbulva.berlinchess.engine.position.Position

internal interface IPieceMoves {
    fun semiLegalMoves(position: Position, pieceOnBoard: PieceOnBoard): Sequence<Move>
}
