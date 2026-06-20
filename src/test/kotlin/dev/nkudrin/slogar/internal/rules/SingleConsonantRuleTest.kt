package dev.nkudrin.slogar.internal.rules

import dev.nkudrin.slogar.internal.boundary.SyllableBoundaryContext
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

class SingleConsonantRuleTest {
    @Test
    fun `returns consonant index when interval contains one consonant`() {
        val context = SyllableBoundaryContext(
            text = "мама",
            leftVowel = 1,
            rightVowel = 3,
        )

        assertEquals(2, SingleConsonantRule.findBoundary(context))
    }

    @Test
    fun `returns null when vowels are adjacent`() {
        val context = SyllableBoundaryContext(
            text = "поэт",
            leftVowel = 1,
            rightVowel = 2,
        )

        assertNull(SingleConsonantRule.findBoundary(context))
    }

    @Test
    fun `returns null when interval contains a consonant group`() {
        val context = SyllableBoundaryContext(
            text = "окно",
            leftVowel = 0,
            rightVowel = 3,
        )

        assertNull(SingleConsonantRule.findBoundary(context))
    }

    @Test
    fun `returns null when interval contains a non-consonant`() {
        val context = SyllableBoundaryContext(
            text = "а-а",
            leftVowel = 0,
            rightVowel = 2,
        )

        assertNull(SingleConsonantRule.findBoundary(context))
    }
}
