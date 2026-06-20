package dev.nkudrin.slogar

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class SlogarTest {
    private val slogar = Slogar()

    @Test
    fun `returns school simple syllables when rules resolve every boundary`() {
        val cases = mapOf(
            "мама" to listOf("ма", "ма"),
            "поэт" to listOf("по", "эт"),
            "район" to listOf("рай", "он"),
            "окно" to listOf("ок", "но"),
            "письмо" to listOf("пись", "мо"),
            "колья" to listOf("коль", "я"),
            "ёлка" to listOf("ёл", "ка"),
        )

        cases.forEach { (word, expected) ->
            assertEquals(expected, slogar.split(word).syllables, word)
        }
    }

    @Test
    fun `preserves original casing when splitting word`() {
        assertEquals(listOf("Ок", "Но"), slogar.split("ОкНо").syllables)
    }

    @Test
    fun `preserves raw input and trims word before splitting`() {
        val result = slogar.split("  Мама  ")

        assertEquals("  Мама  ", result.original)
        assertEquals(listOf("Ма", "ма"), result.syllables)
    }

    @Test
    fun `returns original word when word contains one vowel`() {
        assertEquals(listOf("дом"), slogar.split("дом").syllables)
    }

    @Test
    fun `returns warning when word contains no vowels`() {
        val result = slogar.split("ммм")

        assertEquals(listOf("ммм"), result.syllables)
        assertEquals(listOf(SyllabificationWarning.NoVowels), result.warnings)
    }

    @Test
    fun `returns empty syllables and warning when input is blank`() {
        val result = slogar.split("   ")

        assertEquals(emptyList(), result.syllables)
        assertEquals(listOf(SyllabificationWarning.BlankInput), result.warnings)
    }

    @Test
    fun `returns whole word and warning when input contains unsupported characters`() {
        val cases = mapOf(
            "мама1" to listOf(SyllabificationWarning.NonRussianLetters),
            "ма-ма" to listOf(SyllabificationWarning.NonRussianLetters),
            "два слова" to listOf(SyllabificationWarning.NonRussianLetters),
            "test" to listOf(
                SyllabificationWarning.NoVowels,
                SyllabificationWarning.NonRussianLetters,
            ),
        )

        cases.forEach { (word, expectedWarnings) ->
            val result = slogar.split(word)

            assertEquals(listOf(word), result.syllables, word)
            assertEquals(expectedWarnings, result.warnings, word)
        }
    }

    @Test
    fun `throws exception when input is invalid in strict mode`() {
        val strictSlogar = Slogar(RussianSyllabifierOptions(strict = true))
        val invalidWords = listOf("   ", "ммм", "ма-ма", "мама1", "два слова", "test")

        invalidWords.forEach { word ->
            assertFailsWith<IllegalArgumentException>(word) {
                strictSlogar.split(word)
            }
        }
    }
}
