package ru.maksimbulva.berlinchess.engine.board

import org.junit.Assert.*
import org.junit.Test
import ru.maksimbulva.berlinchess.engine.Piece
import ru.maksimbulva.berlinchess.engine.Player

internal class ColoredPieceTest {

    @Test
    fun emptyColoredPieceTest() {
        assertTrue(ColoredPiece.Empty.isEmpty)
        assertFalse(ColoredPiece.Empty.isNotEmpty)
    }

    @Test
    fun encodingTest() {
        val piece = ColoredPiece(Player.White, Piece.Bishop)
        assertFalse(piece.isEmpty)
        assertTrue(piece.isNotEmpty)
        assertEquals(8, piece.playerInt)
        assertEquals(3, piece.pieceInt)
    }

    @Test
    fun equalsTest() {
        val pieces = arrayOf(
            ColoredPiece(Player.Black, Piece.Queen),
            ColoredPiece(Player.White, Piece.Knight),
            ColoredPiece(Player.Black, Piece.Queen)
        )

        assertTrue(pieces[0] == pieces[2])
        assertTrue(pieces[2] == pieces[0])
        assertFalse(pieces[0] == pieces[1])
        assertFalse(pieces[1] == pieces[0])
        assertFalse(pieces[1] == pieces[2])
    }
}
