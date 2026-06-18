package dev.nkudrin.slogar

data class SyllabificationResult(
    val original: String,
    val syllables: List<String>,
    val warnings: List<SyllabificationWarning> = emptyList(),
)
