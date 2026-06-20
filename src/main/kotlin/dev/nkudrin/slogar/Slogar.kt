package dev.nkudrin.slogar

import dev.nkudrin.slogar.internal.RussianSyllabifier

class Slogar(
    private val options: RussianSyllabifierOptions = RussianSyllabifierOptions(),
) {
    private val syllabifier = RussianSyllabifier(options)

    fun split(word: String): Syllables = syllabifier.split(word)
}
