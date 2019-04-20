package ru.maksimbulva.berlinchess.engine.move

import ru.maksimbulva.berlinchess.engine.board.PieceOnBoard
import ru.maksimbulva.berlinchess.engine.position.Position

internal object QueenMoves : IPieceMoves {

    private val lineDeltas = arrayOf(
        -1 to -1, -1 to 0, -1 to 1,
        0 to -1, 0 to 1,
        1 to -1, 1 to 0, 1 to 1
    )

    override fun semiLegalMoves(position: Position, pieceOnBoard: PieceOnBoard): Sequence<Move> {
        return lineDeltas.asSequence()
            .map { generateLineMoves(position.board, pieceOnBoard, it.first, it.second) }
            .flatten()
    }
}
