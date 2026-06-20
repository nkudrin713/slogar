package dev.nkudrin.slogar.internal.rules

import dev.nkudrin.slogar.internal.isRussianConsonant
import dev.nkudrin.slogar.internal.boundary.BoundaryRule
import dev.nkudrin.slogar.internal.boundary.SyllableBoundaryContext

internal object SingleConsonantRule : BoundaryRule {
    override fun findBoundary(context: SyllableBoundaryContext): Int? {
        return context.leftVowel.plus(1)
            .takeIf {
                context.between.length == 1 && context.between.first().isRussianConsonant()
            }
    }
}
