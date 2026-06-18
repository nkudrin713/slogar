package dev.nkudrin.slogar.internal

import dev.nkudrin.slogar.RussianSyllabifierOptions

internal object WordNormalizer {
    fun normalize(word: String, options: RussianSyllabifierOptions): NormalizedWord {
        val trimmed = word.trim()
        val normalized = trimmed
            .lowercase()
            .let { if (options.normalizeYo) it.replace('ё', 'е') else it }

        return NormalizedWord(
            original = trimmed,
            text = normalized,
        )
    }
}
