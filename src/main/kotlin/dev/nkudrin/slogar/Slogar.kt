package dev.nkudrin.slogar

import dev.nkudrin.slogar.internal.RussianSyllabifier

/**
 * Splits Russian words into syllables.
 *
 * Each input is treated as a single word. Leading and trailing whitespace is ignored during
 * syllabification, while the unmodified input remains available in [Syllables.original].
 *
 * @param options syllabification and input-validation options
 */
class Slogar(
    private val options: RussianSyllabifierOptions = RussianSyllabifierOptions(),
) {
    private val syllabifier = RussianSyllabifier(options)

    /**
     * Splits [word] and returns its syllables together with validation warnings.
     *
     * @throws IllegalArgumentException when strict mode is enabled and the input is invalid
     */
    fun split(word: String): Syllables = syllabifier.split(word)
}
