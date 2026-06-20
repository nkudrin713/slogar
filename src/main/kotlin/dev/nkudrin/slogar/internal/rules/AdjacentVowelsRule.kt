package dev.nkudrin.slogar.internal.rules

import dev.nkudrin.slogar.internal.boundary.BoundaryRule
import dev.nkudrin.slogar.internal.boundary.SyllableBoundaryContext

internal object AdjacentVowelsRule : BoundaryRule {
    override fun findBoundary(context: SyllableBoundaryContext): Int? {
        return context.rightVowel.takeIf { context.between.isEmpty() }
    }
}
