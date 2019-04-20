package ru.maksimbulva.berlinchess.engine.move

import org.junit.Assert.assertEquals
import org.junit.Test
import ru.maksimbulva.berlinchess.engine.fen.Fen

internal class GeneratedMoveCounterTest {

    // Also known as perft in computer chess literature
    @Test
    fun moveCountFromInitialPositionTest() {
        arrayOf(
            1 to 20L,
            2 to 400L,
            3 to 8902L,
            4 to 197_281L,
            5 to 4_865_609L,
            6 to 119_060_324L
        )
            .take(6)
            .forEach{ (depth, expectedMoveCount) ->
                val position = Fen.decode(Fen.InitialPosition)!!
                assertEquals(expectedMoveCount, GeneratedMoveCounter.moveCount(position, depth))
            }
    }

    @Test
    fun moveCountPawnEndgameTest() {
        val position = Fen.decode("7k/8/8/8/8/8/1KP5/8")!!
        assertEquals(9, GeneratedMoveCounter.moveCount(position, 1))
    }

    @Test
    fun moveCountBishopEndgameTest() {
        val position = Fen.decode("8/8/8/4K3/3B4/3k4/8/8")!!
        assertEquals(16, GeneratedMoveCounter.moveCount(position, 1))
    }

    @Test
    fun moveCountRookEndgameTest() {
        val position = Fen.decode("7k/8/8/8/8/2K5/1R6/8")!!
        assertEquals(21, GeneratedMoveCounter.moveCount(position, 1))
    }

    @Test
    fun moveCountQueenEndgameTest() {
        val position = Fen.decode("7K/8/8/4Q3/1k6/8/8/8")!!
        assertEquals(29, GeneratedMoveCounter.moveCount(position, 1))
    }
}
