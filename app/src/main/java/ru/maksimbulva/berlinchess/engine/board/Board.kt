package ru.maksimbulva.berlinchess.engine.board

import ru.maksimbulva.berlinchess.engine.Player
import ru.maksimbulva.berlinchess.engine.move.Move

class Board(
    private val cells: Array<ColoredPiece>
) {
    private val hashCodeValue: Int by lazy { cells.contentHashCode() }

    constructor(pieces: Collection<PieceOnBoard>) : this(
        cells = Array(64) { ColoredPiece.Empty }.apply {
            pieces.forEach {
                this[it.cell.index] = ColoredPiece(it.player, it.piece)
            }
        }
    )

    constructor(vararg pieces: PieceOnBoard) : this(pieces.toList())

    fun isEmpty(cellIndex: Int) = cells[cellIndex].encoded == -1

    fun isEmpty(cell: Cell) = cells[cell.index].encoded == -1

    fun player(cell: Cell) = cells[cell.index].player

    fun cell(cellIndex: Int) = cells[cellIndex]

    fun cell(cell: Cell) = cells[cell.index]

    fun pieces(player: Player): List<PieceOnBoard> {
        // TODO - optimize me
        return cells
            .mapIndexed { index, coloredPiece -> index to coloredPiece }
            .filter { (_, coloredPiece) ->
                coloredPiece.isNotEmpty && coloredPiece.player == player
            }
            .map { (index, coloredPiece) ->
                PieceOnBoard(player, coloredPiece.piece, Cell(index))
            }
    }

    fun playMove(move: Move): Board {
        val cells = this.cells.clone()
        val coloredPiece = cells[move.origin]
        cells[move.origin] = ColoredPiece.Empty
        cells[move.dest] = coloredPiece
        if (move.isEnPassantCapture) {
            val originRow = Cell(move.origin).row
            val destColumn = Cell(move.dest).column
            cells[Cell(originRow, destColumn).index] = ColoredPiece.Empty
        }
        return Board(cells)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        val o = other as? Board
        if (o === null) return false
        return cells.contentEquals(o.cells)
    }

    override fun hashCode() = hashCodeValue
}
