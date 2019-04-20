package ru.maksimbulva.berlinchess.engine.board

import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class BoardTest {

    @Test
    fun equalsTest() {
        val boards = arrayOf(
            Board(wKingE1, bKingE8, wBishopA3),
            Board(wKingE1, bKingE8),
            Board(wBishopA3, bKingE8, wKingE1)
        )

        assertTrue(boards[0] == boards[2])
        assertTrue(boards[2] == boards[0])
        assertFalse(boards[1] == boards[0])
        assertFalse(boards[1] == boards[2])
    }
}
