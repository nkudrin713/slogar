package dev.nkudrin.slogar

import kotlin.test.Test
import kotlin.test.assertEquals

class SlogarTest {
    private val slogar = Slogar()

    @Test
    fun `splits words by school phonetic layers`() {
        val cases = mapOf(
            "мама" to listOf("ма", "ма"),
            "молоко" to listOf("мо", "ло", "ко"),
            "аист" to listOf("а", "ист"),
            "поэт" to listOf("по", "эт"),
            "коса" to listOf("ко", "са"),
            "кошка" to listOf("кош", "ка"),
            "сестра" to listOf("сест", "ра"),
            "майка" to listOf("май", "ка"),
            "район" to listOf("рай", "он"),
            "семья" to listOf("семь", "я"),
            "подъезд" to listOf("подъ", "езд"),
        )

        for ((word, syllables) in cases) {
            assertEquals(syllables, slogar.split(word).syllables, word)
        }
    }

    @Test
    fun `keeps source casing in result`() {
        assertEquals(listOf("Ма", "ма"), slogar.split("Мама").syllables)
    }

    @Test
    fun `returns warning for word without vowels`() {
        val result = slogar.split("ммм")

        assertEquals(listOf("ммм"), result.syllables)
        assertEquals(listOf(SyllabificationWarning.NoVowels), result.warnings)
    }
}
