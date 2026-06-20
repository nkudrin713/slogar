package dev.nkudrin.slogar.internal.rules

import dev.nkudrin.slogar.internal.boundary.SyllableBoundaryContext
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

class ShortIRuleTest {
    @Test
    fun `returns index after short i when followed by consonant`() {
        val context = SyllableBoundaryContext(
            text = "майка",
            leftVowel = 1,
            rightVowel = 4,
        )

        assertEquals(3, ShortIRule.findBoundary(context))
    }

    @Test
    fun `returns index after short i when it is the only letter between vowels`() {
        val context = SyllableBoundaryContext(
            text = "район",
            leftVowel = 1,
            rightVowel = 3,
        )

        assertEquals(3, ShortIRule.findBoundary(context))
    }

    @Test
    fun `returns null when interval does not start with short i`() {
        val context = SyllableBoundaryContext(
            text = "мама",
            leftVowel = 1,
            rightVowel = 3,
        )

        assertNull(ShortIRule.findBoundary(context))
    }

    @Test
    fun `returns null when vowels are adjacent`() {
        val context = SyllableBoundaryContext(
            text = "поэт",
            leftVowel = 1,
            rightVowel = 2,
        )

        assertNull(ShortIRule.findBoundary(context))
    }
}
