package dev.nkudrin.slogar.internal.boundary

import dev.nkudrin.slogar.SyllablePolicy
import dev.nkudrin.slogar.internal.rules.AdjacentVowelsRule
import dev.nkudrin.slogar.internal.rules.ConsonantGroupRule
import dev.nkudrin.slogar.internal.rules.SeparatingSignRule
import dev.nkudrin.slogar.internal.rules.ShortIRule
import dev.nkudrin.slogar.internal.rules.SingleConsonantRule

internal object BoundaryRulePipelineResolver {
    fun forPolicy(policy: SyllablePolicy): BoundaryRulePipeline {
        return when (policy) {
            SyllablePolicy.SchoolSimple -> schoolSimple()
        }
    }

    private fun schoolSimple(): BoundaryRulePipeline = BoundaryRulePipeline(
        listOf(
            AdjacentVowelsRule,
            SeparatingSignRule,
            ShortIRule,
            SingleConsonantRule,
            ConsonantGroupRule
        )
    )
}
