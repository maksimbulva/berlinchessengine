package ru.maksimbulva.berlinchess

import ru.maksimbulva.berlinchess.engine.Piece
import ru.maksimbulva.berlinchess.engine.Player
import ru.maksimbulva.berlinchess.engine.board.Cell
import ru.maksimbulva.berlinchess.engine.board.PieceOnBoard

internal fun p(s: String) = PieceOnBoard(
    player = if (s[0].isUpperCase()) Player.White else Player.Black,
    piece = when (s[0].toLowerCase()) {
        'k' -> Piece.King
        'q' -> Piece.Queen
        'r' -> Piece.Rook
        'b' -> Piece.Bishop
        'n' -> Piece.Knight
        'p' -> Piece.Pawn
        else -> throw IllegalArgumentException("Bad piece encoding $s")
    },
    cell = Cell(s.substring(1..2))
)
