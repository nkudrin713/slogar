package dev.nkudrin.slogar.internal.boundary

internal fun interface BoundaryRule {
    fun findBoundary(context: SyllableBoundaryContext): Int?
}
