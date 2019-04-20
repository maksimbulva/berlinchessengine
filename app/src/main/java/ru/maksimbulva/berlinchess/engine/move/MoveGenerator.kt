package ru.maksimbulva.berlinchess.engine.move

import ru.maksimbulva.berlinchess.engine.Piece
import ru.maksimbulva.berlinchess.engine.Player
import ru.maksimbulva.berlinchess.engine.board.Cell
import ru.maksimbulva.berlinchess.engine.position.Position
import java.util.EnumMap

object MoveGenerator {
    private val pieceMoveGenerators = EnumMap<Piece, IPieceMoves>(mapOf(
        Piece.Pawn to PawnMoves,
        Piece.Knight to KnightMoves,
        Piece.Bishop to BishopMoves,
        Piece.Rook to RookMoves,
        Piece.Queen to QueenMoves,
        Piece.King to KingMoves
    ))

    fun moves(position: Position): Collection<Move> {
        val myKingCell = position.board.pieces(position.playerToMove)
            .filter { it.piece == Piece.King }
            .map { it.cell }
            .first()

        return semiLegalMoves(position)
            .filterNot { move ->
                val newKingCell = if (move.pieceInt == Piece.King.ordinal) {
                    Cell(move.dest)
                } else {
                    myKingCell
                }
                isCellAttacked(newKingCell, move, position)
            }
            .toList()
    }

    private fun semiLegalMoves(position: Position): Sequence<Move> {
        return position.board.pieces(position.playerToMove).asSequence()
            .map { pieceOnBoard ->
                pieceMoveGenerators[pieceOnBoard.piece]!!
                    .semiLegalMoves(position, pieceOnBoard)
                    .filter { it.isValid }
            }
            .flatten()
    }

    private fun isCellAttacked(targetCell: Cell, move: Move, position: Position): Boolean {
        // TODO - cleanup

        val targetRow = targetCell.row
        val targetColumn = targetCell.column
        val newPosition = position.playMove(move)
        val newBoard = newPosition.board
        val attacker = newPosition.playerToMove

        val pawnRowDelta = if (attacker == Player.White) 1 else -1
        if ((targetRow - pawnRowDelta) in 0..7) {
            if (targetColumn > 0) {
                val cell = newBoard.cell(Cell(targetRow - pawnRowDelta, targetColumn - 1))
                if (cell.isNotEmpty && cell.pieceInt == Piece.Pawn.ordinal &&
                        cell.player == attacker) {
                    return true
                }
            }
            if (targetColumn < 7) {
                val cell = newBoard.cell(Cell(targetRow - pawnRowDelta, targetColumn + 1))
                if (cell.isNotEmpty && cell.pieceInt == Piece.Pawn.ordinal &&
                        cell.player == attacker) {
                    return true
                }
            }
        }

        KnightMoves.moveDeltas.forEach {
            val row = targetRow - it.first
            val column = targetColumn - it.second
            if (row in 0..7 && column in 0..7) {
                val cell = newBoard.cell(Cell(row, column))
                if (cell.isNotEmpty && cell.pieceInt == Piece.Knight.ordinal &&
                        cell.player == attacker) {
                    return true
                }
            }
        }

        BishopMoves.lineDeltas.forEach { (deltaRow, deltaColumn) ->
            var curRow = targetRow - deltaRow
            var curColumn = targetColumn - deltaColumn
            while (curRow in 0..7 && curColumn in 0..7) {
                val cell = newBoard.cell(Cell(curRow, curColumn))
                if (cell.isNotEmpty) {
                    if (cell.player == attacker &&
                        (cell.pieceInt == Piece.Bishop.ordinal || cell.pieceInt == Piece.Queen.ordinal)) {
                        return true
                    }
                    break
                }
                curRow -= deltaRow
                curColumn -= deltaColumn
            }
        }

        RookMoves.lineDeltas.forEach { (deltaRow, deltaColumn) ->
            var curRow = targetRow - deltaRow
            var curColumn = targetColumn - deltaColumn
            while (curRow in 0..7 && curColumn in 0..7) {
                val cell = newBoard.cell(Cell(curRow, curColumn))
                if (cell.isNotEmpty) {
                    if (cell.player == attacker &&
                        (cell.pieceInt == Piece.Rook.ordinal || cell.pieceInt == Piece.Queen.ordinal)) {
                        return true
                    }
                    break
                }
                curRow -= deltaRow
                curColumn -= deltaColumn
            }
        }



        // TODO: King

        return false
    }
}
