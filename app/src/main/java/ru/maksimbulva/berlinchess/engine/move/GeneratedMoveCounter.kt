package ru.maksimbulva.berlinchess.engine.move

import ru.maksimbulva.berlinchess.engine.position.Position

object GeneratedMoveCounter {

    fun moveCount(position: Position, depth: Int): Long {
        if (depth == 0) {
            return 1
        }

        val moves = MoveGenerator.moves(position)
        return if (depth > 1) {
            moves.fold(0L) { counter, move ->
                counter + moveCount(position.playMove(move), depth - 1)
            }
        } else {
            moves.size.toLong()
        }
    }
}
