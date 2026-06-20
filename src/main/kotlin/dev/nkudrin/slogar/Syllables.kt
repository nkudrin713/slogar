package dev.nkudrin.slogar

data class Syllables(
    val original: String,
    val syllables: List<String>,
    val warnings: List<SyllabificationWarning> = emptyList(),
)
