package dev.nkudrin.slogar.internal.rules

import dev.nkudrin.slogar.internal.boundary.BoundaryRule
import dev.nkudrin.slogar.internal.boundary.SyllableBoundaryContext
import dev.nkudrin.slogar.internal.isRussianSign

internal object SeparatingSignRule : BoundaryRule {
    override fun findBoundary(context: SyllableBoundaryContext): Int? {
        return context.rightVowel.takeIf {
            context.between.lastOrNull()?.isRussianSign() == true
        }
    }
}
