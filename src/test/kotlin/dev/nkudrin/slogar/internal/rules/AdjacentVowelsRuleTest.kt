package dev.nkudrin.slogar.internal.rules

import dev.nkudrin.slogar.internal.boundary.SyllableBoundaryContext
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

class AdjacentVowelsRuleTest {
    @Test
    fun `returns right vowel index when vowels are adjacent`() {
        val context = SyllableBoundaryContext(
            text = "поэт",
            leftVowel = 1,
            rightVowel = 2,
        )

        assertEquals(2, AdjacentVowelsRule.findBoundary(context))
    }

    @Test
    fun `returns null when vowels are not adjacent`() {
        val context = SyllableBoundaryContext(
            text = "мама",
            leftVowel = 1,
            rightVowel = 3,
        )

        assertNull(AdjacentVowelsRule.findBoundary(context))
    }
}
