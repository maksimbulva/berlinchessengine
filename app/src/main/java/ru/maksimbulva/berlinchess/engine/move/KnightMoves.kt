package ru.maksimbulva.berlinchess.engine.move

import ru.maksimbulva.berlinchess.engine.board.PieceOnBoard
import ru.maksimbulva.berlinchess.engine.position.Position

internal object KnightMoves : IPieceMoves {

    val moveDeltas = arrayOf(
        -2 to -1,
        -2 to 1,
        -1 to -2,
        -1 to 2,
        1 to -2,
        1 to 2,
        2 to -1,
        2 to 1
    )

    override fun semiLegalMoves(position: Position, pieceOnBoard: PieceOnBoard): Sequence<Move> {
        return generateMoveFromDeltas(position.board, pieceOnBoard, moveDeltas)
    }
}
