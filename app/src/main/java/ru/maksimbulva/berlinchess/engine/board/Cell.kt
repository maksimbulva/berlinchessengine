package ru.maksimbulva.berlinchess.engine.board

inline class Cell(val index: Int) {
    val row get() = index shr 3
    val column get() = index and 7

    constructor(row: Int, column: Int) : this((row shl 3) + column)

    constructor(s: String) : this(row = s[1] - '1', column = s[0].toLowerCase() - 'a')

    fun deltaRow(delta: Int) = Cell(index + delta * 8)

    fun deltaColumn(delta: Int) = Cell(index + delta)

    override fun toString() = "${'a' + column}${'1' + row}"

    companion object {
        val e1 = Cell(0, 4)
        val f1 = Cell(0, 5)
        val g1 = Cell(0, 6)
        val e2 = Cell(1, 4)
        val a3 = Cell(2, 0)
        val f3 = Cell(2, 5)
        val e4 = Cell(3, 4)
        val b5 = Cell(4, 1)
        val e5 = Cell(4, 4)
        val c6 = Cell(5, 2)
        val e7 = Cell(6, 4)
        val b8 = Cell(7, 1)
        val e8 = Cell(7, 4)
    }
}
