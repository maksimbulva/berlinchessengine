package ru.maksimbulva.berlinchess.engine.fen

import ru.maksimbulva.berlinchess.engine.Piece
import ru.maksimbulva.berlinchess.engine.Player
import ru.maksimbulva.berlinchess.engine.board.Board
import ru.maksimbulva.berlinchess.engine.board.Cell
import ru.maksimbulva.berlinchess.engine.board.PieceOnBoard

internal object FenBoard {

    private const val RowSeparator = '/'

    fun decode(encoded: String): Board {
        val split = encoded.split(RowSeparator)
        require(split.size == 8) { "Invalid board encoding $encoded" }
        return Board(
            pieces = split.asReversed()
                .mapIndexed { index, encodedRow -> decodeRow(index, encodedRow) }
                .flatten()
        )
    }

    private fun decodeRow(row: Int, encodedRow: String): Collection<PieceOnBoard> {
        val decodedRow = encodedRow.flatMap {
            when {
                it.isDigit() -> (1..(it - '0')).map { null }
                it.isLetter() -> listOf(decodePlayer(it) to decodePiece(it))
                else -> throw IllegalArgumentException("Unexpected board char $it")
            }
        }
        require(decodedRow.size == 8) { "Invalid board row encoding $encodedRow" }
        return decodedRow
            .mapIndexedNotNull { column, playerToPiece ->
                playerToPiece?.let { PieceOnBoard(it.first, it.second, Cell(row, column))}
            }
    }

    private fun decodePlayer(c: Char) = if (c.isLowerCase()) {
        Player.Black
    } else {
        Player.White
    }

    private fun decodePiece(c: Char) = when (c.toLowerCase()) {
        'k' -> Piece.King
        'q' -> Piece.Queen
        'r' -> Piece.Rook
        'b' -> Piece.Bishop
        'n' -> Piece.Knight
        'p' -> Piece.Pawn
        else -> throw IllegalArgumentException("Unexpected piece char $c")
    }
}
