package dev.nkudrin.slogar

/**
 * Result of syllabifying one input string.
 *
 * @property original unmodified input passed to [Slogar.split]
 * @property syllables ordered syllable chunks preserving the input's casing
 * @property warnings non-fatal validation or processing issues
 */
data class Syllables(
    val original: String,
    val syllables: List<String>,
    val warnings: List<SyllabificationWarning> = emptyList(),
)
