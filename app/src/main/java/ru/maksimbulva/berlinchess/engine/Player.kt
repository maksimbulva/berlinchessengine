package ru.maksimbulva.berlinchess.engine

enum class Player(val playerInt: Int) {
    Black(0),
    White(8)
}

fun Player.other() = if (this == Player.Black) Player.White else Player.Black