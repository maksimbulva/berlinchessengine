package ru.maksimbulva.berlinchess.engine

import org.junit.Assert.assertEquals
import org.junit.Test

class PlayerTest {

    @Test
    fun otherPlayerTest() {
        assertEquals(Player.White, Player.Black.other())
        assertEquals(Player.Black, Player.White.other())
    }
}
