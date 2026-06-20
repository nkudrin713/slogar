package dev.nkudrin.slogar

/**
 * Configures syllabification and input validation.
 *
 * @property policy rules used to determine syllable boundaries
 * @property normalizeYo whether `ё` should be treated as `е` during analysis; output is unchanged
 * @property strict whether invalid input and unresolved boundaries should throw an exception
 */
data class RussianSyllabifierOptions(
    val policy: SyllablePolicy = SyllablePolicy.SchoolSimple,
    val normalizeYo: Boolean = false,
    val strict: Boolean = false,
)
