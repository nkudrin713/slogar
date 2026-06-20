package dev.nkudrin.slogar.internal.rules

import dev.nkudrin.slogar.internal.boundary.SyllableBoundaryContext
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

class ConsonantGroupRuleTest {
    @Test
    fun `returns last consonant index when interval contains two consonants`() {
        val context = SyllableBoundaryContext(
            text = "окно",
            leftVowel = 0,
            rightVowel = 3,
        )

        assertEquals(2, ConsonantGroupRule.findBoundary(context))
    }

    @Test
    fun `returns last consonant index when interval contains more than two consonants`() {
        val context = SyllableBoundaryContext(
            text = "сестра",
            leftVowel = 1,
            rightVowel = 5,
        )

        assertEquals(4, ConsonantGroupRule.findBoundary(context))
    }

    @Test
    fun `returns null when interval contains one consonant`() {
        val context = SyllableBoundaryContext(
            text = "мама",
            leftVowel = 1,
            rightVowel = 3,
        )

        assertNull(ConsonantGroupRule.findBoundary(context))
    }

    @Test
    fun `returns null when vowels are adjacent`() {
        val context = SyllableBoundaryContext(
            text = "поэт",
            leftVowel = 1,
            rightVowel = 2,
        )

        assertNull(ConsonantGroupRule.findBoundary(context))
    }

    @Test
    fun `returns last consonant index when interval contains an internal sign`() {
        val context = SyllableBoundaryContext(
            text = "письмо",
            leftVowel = 1,
            rightVowel = 5,
        )

        assertEquals(4, ConsonantGroupRule.findBoundary(context))
    }

    @Test
    fun `returns null when interval ends with a sign`() {
        val context = SyllableBoundaryContext(
            text = "колья",
            leftVowel = 1,
            rightVowel = 4,
        )

        assertNull(ConsonantGroupRule.findBoundary(context))
    }
}
