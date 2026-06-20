package dev.nkudrin.slogar.internal.rules

import dev.nkudrin.slogar.internal.boundary.BoundaryRule
import dev.nkudrin.slogar.internal.boundary.SyllableBoundaryContext
import dev.nkudrin.slogar.internal.isRussianShortI

internal object ShortIRule : BoundaryRule {
    override fun findBoundary(context: SyllableBoundaryContext): Int? {
        return context.leftVowel.plus(2).takeIf {
            context.between.firstOrNull()?.isRussianShortI() == true
        }
    }
}
