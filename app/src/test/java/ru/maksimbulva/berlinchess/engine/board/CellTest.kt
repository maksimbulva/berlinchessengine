package ru.maksimbulva.berlinchess.engine.board

import org.junit.Assert.assertEquals
import org.junit.Test

internal class CellTest {

    @Test
    fun cellEncodingTest() {
        val cell = Cell(row = 3, column = 2)
        assertEquals(3, cell.row)
        assertEquals(2, cell.column)
    }
}