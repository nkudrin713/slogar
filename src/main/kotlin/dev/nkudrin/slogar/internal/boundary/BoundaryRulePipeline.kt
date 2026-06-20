package dev.nkudrin.slogar.internal.boundary

internal class BoundaryRulePipeline(
    private val rules: List<BoundaryRule>,
) {
    fun findBoundary(context: SyllableBoundaryContext): Int? {
        return rules.firstNotNullOfOrNull { rule -> rule.findBoundary(context) }
    }
}
