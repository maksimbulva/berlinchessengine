package ru.maksimbulva.berlinchess.engine.board

import ru.maksimbulva.berlinchess.engine.Piece
import ru.maksimbulva.berlinchess.engine.Player

class PieceOnBoard(
    val player: Player,
    val piece: Piece,
    val cell: Cell
)
