package dev.nkudrin.slogar.internal.boundary

import dev.nkudrin.slogar.SyllablePolicy
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

class BoundaryRulePipelineResolverTest {
    private val pipeline = BoundaryRulePipelineResolver.forPolicy(SyllablePolicy.SchoolSimple)

    @Test
    fun `returns boundaries from school simple rules`() {
        val cases = listOf(
            SyllableBoundaryContext("поэт", 1, 2) to 2,
            SyllableBoundaryContext("колья", 1, 4) to 4,
            SyllableBoundaryContext("район", 1, 3) to 3,
            SyllableBoundaryContext("мама", 1, 3) to 2,
            SyllableBoundaryContext("окно", 0, 3) to 2,
            SyllableBoundaryContext("письмо", 1, 5) to 4,
        )

        cases.forEach { (context, expected) ->
            assertEquals(expected, pipeline.findBoundary(context), context.text)
        }
    }

    @Test
    fun `returns null when no school simple rule matches`() {
        val context = SyllableBoundaryContext(
            text = "а-а",
            leftVowel = 0,
            rightVowel = 2,
        )

        assertNull(pipeline.findBoundary(context))
    }
}
