package ru.maksimbulva.berlinchess.engine.fen

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test
import ru.maksimbulva.berlinchess.engine.Player
import ru.maksimbulva.berlinchess.p
import ru.maksimbulva.berlinchess.engine.board.Board
import ru.maksimbulva.berlinchess.engine.position.Position

internal class FenDecodeTest {

    @Test
    fun decodeInitialPosition() {
        val expected = Position(Board(listOf(
            "Ke1", "Qd1", "Ra1", "Rh1", "Bc1", "Bf1", "Nb1", "Ng1", "Pa2", "Pb2", "Pc2", "Pd2",
            "Pe2", "Pf2", "Pg2", "Ph2", "ke8", "qd8", "ra8", "rh8", "bc8", "bf8", "nb8", "ng8",
            "pa7", "pb7", "pc7", "pd7", "pe7", "pf7", "pg7", "ph7"
        ).map { p(it) }
        ),
            playerToMove = Player.White
        )

        assertEquals(expected, Fen.decode(Fen.InitialPosition))
    }

    @Test
    fun decodingOfEmptyStringReturnsNull() {
        assertNull(Fen.decode(""))
    }
}
