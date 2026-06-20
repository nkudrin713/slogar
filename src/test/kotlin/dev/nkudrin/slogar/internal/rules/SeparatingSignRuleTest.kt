package dev.nkudrin.slogar.internal.rules

import dev.nkudrin.slogar.internal.boundary.SyllableBoundaryContext
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

class SeparatingSignRuleTest {
    @Test
    fun `returns right vowel index when interval ends with soft sign`() {
        val context = SyllableBoundaryContext(
            text = "колья",
            leftVowel = 1,
            rightVowel = 4,
        )

        assertEquals(4, SeparatingSignRule.findBoundary(context))
    }

    @Test
    fun `returns right vowel index when interval ends with hard sign`() {
        val context = SyllableBoundaryContext(
            text = "подъезд",
            leftVowel = 1,
            rightVowel = 4,
        )

        assertEquals(4, SeparatingSignRule.findBoundary(context))
    }

    @Test
    fun `returns null when interval has no separating sign`() {
        val context = SyllableBoundaryContext(
            text = "мама",
            leftVowel = 1,
            rightVowel = 3,
        )

        assertNull(SeparatingSignRule.findBoundary(context))
    }

    @Test
    fun `returns null when sign is not last in interval`() {
        val context = SyllableBoundaryContext(
            text = "письмо",
            leftVowel = 1,
            rightVowel = 5,
        )

        assertNull(SeparatingSignRule.findBoundary(context))
    }

    @Test
    fun `returns null when vowels are adjacent`() {
        val context = SyllableBoundaryContext(
            text = "поэт",
            leftVowel = 1,
            rightVowel = 2,
        )

        assertNull(SeparatingSignRule.findBoundary(context))
    }
}
