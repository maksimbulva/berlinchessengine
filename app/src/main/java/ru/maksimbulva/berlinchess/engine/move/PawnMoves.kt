package ru.maksimbulva.berlinchess.engine.move

import ru.maksimbulva.berlinchess.engine.Piece
import ru.maksimbulva.berlinchess.engine.Player
import ru.maksimbulva.berlinchess.engine.board.Board
import ru.maksimbulva.berlinchess.engine.board.Cell
import ru.maksimbulva.berlinchess.engine.board.PieceOnBoard
import ru.maksimbulva.berlinchess.engine.position.Position

internal object PawnMoves : IPieceMoves {

    override fun semiLegalMoves(position: Position, pieceOnBoard: PieceOnBoard): Sequence<Move> {
        val buffer = mutableListOf<Move>()
        val board = position.board
        val player = pieceOnBoard.player
        val origin = pieceOnBoard.cell
        val deltaRow = if (player == Player.White) 1 else -1
        val cellForward = origin.deltaRow(deltaRow)
        val moveForward = makeMove(origin, cellForward, board)
        if (moveForward.isValid) {
            buffer.add(moveForward)
            if ((player == Player.White && origin.row == 1) ||
                (player == Player.Black && origin.row == 6)) {
                buffer.add(makeMove(origin, origin.deltaRow(2 * deltaRow), board))
            }
        }
        if (origin.column > 0) {
            buffer.add(makeCapture(pieceOnBoard, cellForward.deltaColumn(-1), board))
        }
        if (origin.column < 7) {
            buffer.add(makeCapture(pieceOnBoard, cellForward.deltaColumn(1), board))
        }

        if (position.isCanCaptureEnPassant &&
            Math.abs(position.enPassantCaptureColumn - origin.column) == 1 &&
            ((player == Player.White && origin.row == 4) ||
                    (player == Player.Black && origin.row == 3))
        ) {
            val dest = Cell(cellForward.row, position.enPassantCaptureColumn)
            buffer.add(Move.makeEnPassantCapture(origin, dest))
        }

        // TODO: pawn promotions
        return buffer.asSequence()
    }

    private fun makeMove(origin: Cell, dest: Cell, board: Board): Move {
        return if (dest.row in 1..6 && board.isEmpty(dest)) {
            Move(Piece.Pawn, origin, dest)
        } else {
            Move.Invalid
        }
    }

    private fun makeCapture(pieceOnBoard: PieceOnBoard, dest: Cell, board: Board): Move {
        val cell = board.cell(dest)
        return if (cell.isNotEmpty
            && cell.playerInt != pieceOnBoard.player.playerInt
            && dest.row in 1..6) {
            Move.makeCapture(Piece.Pawn, pieceOnBoard.cell, dest, cell.pieceInt)
        } else {
            Move.Invalid
        }
    }
}
