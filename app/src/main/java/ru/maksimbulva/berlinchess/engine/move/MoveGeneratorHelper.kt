package ru.maksimbulva.berlinchess.engine.move

import ru.maksimbulva.berlinchess.engine.board.Board
import ru.maksimbulva.berlinchess.engine.board.Cell
import ru.maksimbulva.berlinchess.engine.board.ColoredPiece
import ru.maksimbulva.berlinchess.engine.board.PieceOnBoard

internal fun generateMoveFromDeltas(
    board: Board,
    pieceOnBoard: PieceOnBoard,
    deltas: Array<Pair<Int, Int>>
): Sequence<Move> {

    val playerInt = pieceOnBoard.player.playerInt
    val piece = pieceOnBoard.piece
    val origin = pieceOnBoard.cell

    return deltas.asSequence()
        .map { (deltaRow, deltaColumn) ->
            val destRow = origin.row + deltaRow
            val destColumn = origin.column + deltaColumn
            if (destRow in 0..7 && destColumn in 0..7) {
                val dest = Cell(destRow, destColumn)
                val destCell: ColoredPiece = board.cell(dest)
                when {
                    destCell.isEmpty -> Move(piece, origin, dest)
                    destCell.playerInt == playerInt -> Move.Invalid
                    else -> Move.makeCapture(piece, origin, dest, destCell.pieceInt)
                }
            } else {
                Move.Invalid
            }
        }
}

internal fun generateLineMoves(
    board: Board,
    pieceOnBoard: PieceOnBoard,
    deltaRow: Int,
    deltaColumn: Int
): Sequence<Move> {

    val playerInt = pieceOnBoard.player.playerInt
    val piece = pieceOnBoard.piece
    val origin = pieceOnBoard.cell

    val maxLineLength = Math.min(
        when {
            deltaRow < 0 -> origin.row
            deltaRow > 0 -> 7 - origin.row
            else -> Int.MAX_VALUE
        },
        when {
            deltaColumn < 0 -> origin.column
            deltaColumn > 0 -> 7 - origin.column
            else -> Int.MAX_VALUE
        }
    )

    var noObstacle = true

    return (1..maxLineLength).asSequence()
        .map {
            if (!noObstacle) {
                return@map Move.Invalid
            }
            val cur = Cell(origin.row + it * deltaRow, origin.column + it * deltaColumn)
            val curCell = board.cell(cur)
            noObstacle = noObstacle && curCell.isEmpty
            when {
                curCell.isEmpty -> Move(piece, origin, cur)
                curCell.playerInt == playerInt ->Move.Invalid
                else -> Move.makeCapture(piece, origin, cur, curCell.pieceInt)
            }
        }
        // .takeWhile { noObstacle }
}
