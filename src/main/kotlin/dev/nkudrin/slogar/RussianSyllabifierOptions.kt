package dev.nkudrin.slogar

data class RussianSyllabifierOptions(
    val policy: SyllablePolicy = SyllablePolicy.SchoolPhonetic,
    val normalizeYo: Boolean = false,
    val strict: Boolean = false,
)
