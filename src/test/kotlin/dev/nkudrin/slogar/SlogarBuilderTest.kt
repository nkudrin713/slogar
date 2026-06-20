package dev.nkudrin.slogar

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class SlogarBuilderTest {
    @Test
    fun `returns configured instance when builder settings are chained`() {
        val slogar = Slogar
            .policy(SyllablePolicy.SchoolSimple)
            .normalizeYo(true)
            .strict(true)
            .build()

        assertEquals(listOf("Ёл", "ка"), slogar.split("Ёлка").syllables)
    }

    @Test
    fun `applies strict validation when enabled`() {
        val slogar = Slogar.strict(true).build()

        assertFailsWith<IllegalArgumentException> {
            slogar.split("два слова")
        }
    }

    @Test
    fun `uses latest value when setting is overridden`() {
        val slogar = Slogar
            .strict(true)
            .strict(false)
            .build()

        assertEquals(
            listOf(SyllabificationWarning.NonRussianLetters),
            slogar.split("два слова").warnings,
        )
    }
}
