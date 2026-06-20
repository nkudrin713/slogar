package dev.nkudrin.slogar.internal

import dev.nkudrin.slogar.RussianSyllabifierOptions
import dev.nkudrin.slogar.SyllabificationWarning
import dev.nkudrin.slogar.Syllables
import dev.nkudrin.slogar.internal.boundary.BoundaryRulePipelineResolver
import dev.nkudrin.slogar.internal.boundary.SyllableBoundaryContext

internal class RussianSyllabifier(
    private val options: RussianSyllabifierOptions,
) {
    private val pipeline = BoundaryRulePipelineResolver.forPolicy(options.policy)

    fun split(word: String): Syllables {
        val normalized = WordNormalizer.normalize(word, options)
        val warnings = buildWarnings(normalized).toMutableList()

        if (options.strict && warnings.isNotEmpty()) {
            throw IllegalArgumentException("Invalid word '$word': ${warnings.joinToString()}")
        }

        if (normalized.text.isEmpty()) {
            return Syllables(word, emptyList(), warnings)
        }

        if (SyllabificationWarning.NonRussianLetters in warnings) {
            return Syllables(word, listOf(normalized.original), warnings)
        }

        val vowelIndexes = normalized.text.indices.filter { normalized.text[it].isRussianVowel() }
        if (vowelIndexes.isEmpty()) {
            return Syllables(word, listOf(normalized.original), warnings)
        }
        if (vowelIndexes.size == 1) {
            return Syllables(word, listOf(normalized.original), warnings)
        }

        val boundaries = vowelIndexes.zipWithNext().map { (leftVowel, rightVowel) ->
            pipeline.findBoundary(
                SyllableBoundaryContext(normalized.text, leftVowel, rightVowel),
            ) ?: return unresolvedBoundary(word, normalized, warnings)
        }

        return Syllables(word, splitAt(normalized.original, boundaries), warnings)
    }

    private fun unresolvedBoundary(
        word: String,
        normalized: NormalizedWord,
        warnings: MutableList<SyllabificationWarning>,
    ): Syllables {
        if (options.strict) {
            throw IllegalArgumentException("Unable to determine every syllable boundary in '$word'")
        }

        warnings += SyllabificationWarning.UnresolvedBoundary
        return Syllables(word, listOf(normalized.original), warnings)
    }

    private fun splitAt(word: String, boundaries: List<Int>): List<String> = buildList {
        var start = 0
        boundaries.forEach { boundary ->
            add(word.substring(start, boundary))
            start = boundary
        }
        add(word.substring(start))
    }

    private fun buildWarnings(word: NormalizedWord): List<SyllabificationWarning> = buildList {
        if (word.text.isEmpty()) add(SyllabificationWarning.BlankInput)
        if (word.text.isNotEmpty() && !word.text.any(Char::isRussianVowel)) {
            add(SyllabificationWarning.NoVowels)
        }
        if (word.text.any { !it.isRussianLetter() }) {
            add(SyllabificationWarning.NonRussianLetters)
        }
    }
}
