package dev.nkudrin.slogar.internal.boundary

internal data class SyllableBoundaryContext(
    val text: String,
    val leftVowel: Int,
    val rightVowel: Int,
) {
    val between: String = text.substring(leftVowel + 1, rightVowel)
}
