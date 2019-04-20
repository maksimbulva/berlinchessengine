package ru.maksimbulva.berlinchess.engine.position

import ru.maksimbulva.berlinchess.engine.Piece
import ru.maksimbulva.berlinchess.engine.Player
import ru.maksimbulva.berlinchess.engine.board.Board
import ru.maksimbulva.berlinchess.engine.board.Cell
import ru.maksimbulva.berlinchess.engine.move.Move
import ru.maksimbulva.berlinchess.engine.other

class Position(
    val board: Board,
    val playerToMove: Player,
    val isCanCaptureEnPassant: Boolean = false,
    val enPassantCaptureColumn: Int = 0
) {

    fun playMove(move: Move): Position {
        // TODO: remove these debug checks
        require(move.origin != move.dest)
        require(board.cell(move.origin).isNotEmpty)
        require(board.cell(move.origin).player == playerToMove)
        if (move.isCapture) {
            if (move.isEnPassantCapture) {

            } else {
                require(board.cell(move.dest).isNotEmpty)
                require(board.cell(move.dest).player != playerToMove)
            }
        } else {
            require(board.cell(move.dest).isEmpty)
        }

        var isCanCaptureEnPassant = false
        var enPassantCaptureColumn = 0
        if (move.pieceInt == Piece.Pawn.ordinal && Math.abs(move.dest - move.origin) == 16) {
            isCanCaptureEnPassant = true
            enPassantCaptureColumn = Cell(move.origin).column
        }

        return Position(
            board = board.playMove(move),
            playerToMove = playerToMove.other(),
            isCanCaptureEnPassant = isCanCaptureEnPassant,
            enPassantCaptureColumn = enPassantCaptureColumn
        )
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        val o = other as? Position
        if (o === null) return false
        return board == o.board
    }

    override fun hashCode(): Int {
        return board.hashCode()
    }
}
