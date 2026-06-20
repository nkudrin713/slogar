package dev.nkudrin.slogar.internal.boundary

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertNull

class BoundaryRulePipelineTest {
    private val context = SyllableBoundaryContext(
        text = "мама",
        leftVowel = 1,
        rightVowel = 3,
    )

    @Test
    fun `returns boundary when preceding rules return null`() {
        val pipeline = BoundaryRulePipeline(
            listOf(
                BoundaryRule { null },
                BoundaryRule { 2 },
            ),
        )

        assertEquals(2, pipeline.findBoundary(context))
    }

    @Test
    fun `returns first boundary when multiple rules match`() {
        var subsequentRuleCalled = false
        val pipeline = BoundaryRulePipeline(
            listOf(
                BoundaryRule { 2 },
                BoundaryRule {
                    subsequentRuleCalled = true
                    3
                },
            ),
        )

        assertEquals(2, pipeline.findBoundary(context))
        assertFalse(subsequentRuleCalled)
    }

    @Test
    fun `returns null when no rule matches`() {
        val pipeline = BoundaryRulePipeline(
            listOf(
                BoundaryRule { null },
                BoundaryRule { null },
            ),
        )

        assertNull(pipeline.findBoundary(context))
    }
}
