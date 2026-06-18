package dev.nkudrin.slogar.internal

internal object RussianSyllableRules {
    private val boundaryRules = listOf(
        AdjacentVowelsRule,
        SeparatingSignRule,
        ShortIRule,
        SingleConsonantRule,
        ConsonantGroupRule,
    )

    fun split(word: NormalizedWord): List<String> {
        val vowelIndexes = word.text.indices.filter { RussianLetters.isVowel(word.text[it]) }

        if (vowelIndexes.size <= 1) {
            return listOf(word.original)
        }

        val boundaries = vowelIndexes.zipWithNext().map { (leftVowel, rightVowel) ->
            boundaryBetweenVowels(SyllableBoundaryContext(word.text, leftVowel, rightVowel))
        }

        return buildList {
            var start = 0
            for (boundary in boundaries) {
                add(word.original.substring(start, boundary))
                start = boundary
            }
            add(word.original.substring(start))
        }
    }

    private fun boundaryBetweenVowels(context: SyllableBoundaryContext): Int {
        return boundaryRules.firstNotNullOf { rule -> rule.findBoundary(context) }
    }
}

private data class SyllableBoundaryContext(
    val text: String,
    val leftVowel: Int,
    val rightVowel: Int,
) {
    val between: String = text.substring(leftVowel + 1, rightVowel)
}

private fun interface BoundaryRule {
    fun findBoundary(context: SyllableBoundaryContext): Int?
}

private object AdjacentVowelsRule : BoundaryRule {
    override fun findBoundary(context: SyllableBoundaryContext): Int? {
        return context.rightVowel.takeIf { context.between.isEmpty() }
    }
}

private object SeparatingSignRule : BoundaryRule {
    override fun findBoundary(context: SyllableBoundaryContext): Int? {
        return context.rightVowel.takeIf {
            context.between.lastOrNull()?.let(RussianLetters::isSign) == true
        }
    }
}

private object ShortIRule : BoundaryRule {
    override fun findBoundary(context: SyllableBoundaryContext): Int? {
        return (context.leftVowel + 2).takeIf {
            context.between.firstOrNull()?.let(RussianLetters::isShortI) == true
        }
    }
}

private object SingleConsonantRule : BoundaryRule {
    override fun findBoundary(context: SyllableBoundaryContext): Int? {
        return (context.leftVowel + 1).takeIf { context.between.length == 1 }
    }
}

private object ConsonantGroupRule : BoundaryRule {
    override fun findBoundary(context: SyllableBoundaryContext): Int {
        return context.rightVowel - 1
    }
}
