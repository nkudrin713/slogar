package dev.nkudrin.slogar.internal.rules

import dev.nkudrin.slogar.internal.boundary.BoundaryRule
import dev.nkudrin.slogar.internal.boundary.SyllableBoundaryContext
import dev.nkudrin.slogar.internal.isRussianConsonant
import dev.nkudrin.slogar.internal.isRussianSign

internal object ConsonantGroupRule : BoundaryRule {
    override fun findBoundary(context: SyllableBoundaryContext): Int? {
        return context.rightVowel.minus(1).takeIf {
            context.between.length > 1 &&
                context.between.last().isRussianConsonant() &&
                context.between.all { it.isRussianConsonant() || it.isRussianSign() }
        }
    }
}
