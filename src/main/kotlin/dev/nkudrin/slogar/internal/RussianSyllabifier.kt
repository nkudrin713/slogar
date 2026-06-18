package dev.nkudrin.slogar.internal

import dev.nkudrin.slogar.RussianSyllabifierOptions
import dev.nkudrin.slogar.SyllabificationResult
import dev.nkudrin.slogar.SyllabificationWarning

internal class RussianSyllabifier(
    private val options: RussianSyllabifierOptions,
) {
    fun split(word: String): SyllabificationResult {
        val normalized = WordNormalizer.normalize(word, options)
        val warnings = buildWarnings(normalized)

        if (normalized.text.isEmpty()) {
            return SyllabificationResult(word, emptyList(), warnings)
        }

        if (!normalized.text.any(RussianLetters::isVowel)) {
            return SyllabificationResult(word, listOf(word), warnings)
        }

        val syllables = RussianSyllableRules.split(normalized)
        return SyllabificationResult(word, syllables, warnings)
    }

    private fun buildWarnings(word: NormalizedWord): List<SyllabificationWarning> = buildList {
        if (word.text.isEmpty()) add(SyllabificationWarning.BlankInput)
        if (word.text.isNotEmpty() && !word.text.any(RussianLetters::isVowel)) {
            add(SyllabificationWarning.NoVowels)
        }
        if (word.text.any { !RussianLetters.isRussianLetter(it) }) {
            add(SyllabificationWarning.NonRussianLetters)
        }
    }
}
