package dev.nkudrin.slogar

/** Describes a non-fatal issue encountered while processing input. */
enum class SyllabificationWarning {
    /** The input is empty or contains only whitespace. */
    BlankInput,

    /** The input contains no Russian vowels and cannot be divided into syllables. */
    NoVowels,

    /** The input contains characters outside the Russian alphabet. */
    NonRussianLetters,

    /** At least one syllable boundary could not be determined. */
    UnresolvedBoundary,
}
