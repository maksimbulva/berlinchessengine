package ru.maksimbulva.berlinchess.engine.move

import ru.maksimbulva.berlinchess.engine.Piece
import ru.maksimbulva.berlinchess.engine.board.Cell

inline class Move(val encoded: Int) {

    val isValid get() = encoded != -1

    val dest get() = encoded and 63

    val origin get() = (encoded shr 6) and 63

    val pieceInt get() = (encoded shr 12) and 7

    val isCapture get() = (encoded and FlagCapture) != 0

    val isEnPassantCapture get() = (encoded and FlagEnPassantCapture) != 0

    constructor(piece: Piece, origin: Cell, dest: Cell) : this(
        (piece.ordinal shl 12) + (origin.index shl 6) + dest.index
    )

    override fun toString(): String {
        return "${Cell(origin)}-${Cell(dest)}"
    }

    companion object {
        private const val FlagCapture = 1 shl 18
        private const val FlagEnPassantCapture = 1 shl 19

        val Invalid = Move(-1)

        fun makeCapture(piece: Piece, origin: Cell, dest: Cell, capturedPieceInt: Int): Move {
            return Move(
                (piece.ordinal shl 12) + (origin.index shl 6) + dest.index +
                        (capturedPieceInt shl 15) + FlagCapture
            )
        }

        fun makeEnPassantCapture(origin: Cell, dest: Cell): Move {
            return Move(
                (Piece.Pawn.ordinal shl 12) + (origin.index shl 6) + dest.index +
                        (Piece.Pawn.ordinal shl 15) + FlagCapture + FlagEnPassantCapture
            )
        }
    }
}
