package ru.maksimbulva.berlinchess.engine.move

import org.junit.Assert.assertEquals
import org.junit.Test
import ru.maksimbulva.berlinchess.engine.Piece
import ru.maksimbulva.berlinchess.engine.Player
import ru.maksimbulva.berlinchess.engine.board.Cell
import ru.maksimbulva.berlinchess.engine.fen.Fen
import ru.maksimbulva.berlinchess.engine.position.Position

internal class PlayMoveTest {

    @Test
    fun playRuyLopezMovesTest() {
        val position = Fen.decode(Fen.InitialPosition)!!
        val actualPosition = playMoves(listOf(
            Move(Piece.Pawn, Cell.e2, Cell.e4),
            Move(Piece.Pawn, Cell.e7, Cell.e5),
            Move(Piece.Knight, Cell.g1, Cell.f3),
            Move(Piece.Knight, Cell.b8, Cell.c6),
            Move(Piece.Bishop, Cell.f1, Cell.b5)
        ), position)
        assertEquals(Player.Black, actualPosition.playerToMove)
        assertEquals(Fen.decode("r1bqkbnr/pppp1ppp/2n5/1B2p3/4P3/5N2/PPPP1PPP/RNBQK2R"), actualPosition)
    }

    private fun playMoves(moves: List<Move>, position: Position): Position {
        var newPosition = position
        moves.forEach { newPosition = newPosition.playMove(it) }
        return newPosition
    }
}
