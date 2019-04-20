package ru.maksimbulva.berlinchess.engine.fen

import ru.maksimbulva.berlinchess.engine.Player
import ru.maksimbulva.berlinchess.engine.position.Position
import java.lang.Exception

internal object Fen {
    const val InitialPosition = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1"

    fun decode(encoded: String): Position? {
        return try {
            val split = encoded.split(' ', '\t')
            Position(
                board = FenBoard.decode(split[0]),
                playerToMove = Player.White
            )
        } catch (e: Exception) {
            // TODO: add some debug output
            null
        }
    }
}
