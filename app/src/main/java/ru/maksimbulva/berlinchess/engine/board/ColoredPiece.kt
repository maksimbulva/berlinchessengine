package ru.maksimbulva.berlinchess.engine.board

import ru.maksimbulva.berlinchess.engine.Piece
import ru.maksimbulva.berlinchess.engine.Player

inline class ColoredPiece(val encoded: Int) {
    val playerInt get() = encoded and 8
    val pieceInt get() = encoded and 7

    val player get() = if (playerInt == 0) Player.Black else Player.White

    val piece get() = when (pieceInt) {
        0 -> Piece.King
        1 -> Piece.Queen
        2 -> Piece.Rook
        3 -> Piece.Bishop
        4 -> Piece.Knight
        5 -> Piece.Pawn
        else -> throw Exception()
    }

    val isEmpty get() = encoded == -1
    val isNotEmpty get() = encoded != -1

    constructor(player: Player, piece: Piece) : this((player.ordinal shl 3) + piece.ordinal)

    companion object {
        val Empty = ColoredPiece(-1)
    }
}
