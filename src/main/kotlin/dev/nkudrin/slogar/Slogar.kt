package dev.nkudrin.slogar

import dev.nkudrin.slogar.internal.RussianSyllabifier

/**
 * Splits Russian words into syllables.
 *
 * Each input is treated as a single word. Leading and trailing whitespace is ignored during
 * syllabification, while the unmodified input remains available in [Syllables.original].
 */
class Slogar private constructor(
    options: RussianSyllabifierOptions,
) {
    private val syllabifier = RussianSyllabifier(options)

    /** Creates a [Slogar] instance with default settings. */
    constructor() : this(RussianSyllabifierOptions())

    /**
     * Splits [word] and returns its syllables together with validation warnings.
     *
     * @throws IllegalArgumentException when strict mode is enabled and the input is invalid
     */
    fun split(word: String): Syllables = syllabifier.split(word)

    companion object {
        /** Starts a builder with the selected syllabification [policy]. */
        @JvmStatic
        fun policy(policy: SyllablePolicy): Builder = Builder().policy(policy)

        /** Starts a builder with `ё` normalization configured. */
        @JvmStatic
        fun normalizeYo(enabled: Boolean): Builder = Builder().normalizeYo(enabled)

        /** Starts a builder with strict input validation configured. */
        @JvmStatic
        fun strict(enabled: Boolean): Builder = Builder().strict(enabled)
    }

    /** Builds an immutable [Slogar] configuration. */
    class Builder internal constructor() {
        private var policy: SyllablePolicy = SyllablePolicy.SchoolSimple
        private var normalizeYo: Boolean = false
        private var strict: Boolean = false

        /** Selects the rule set used to determine syllable boundaries. */
        fun policy(policy: SyllablePolicy): Builder = apply {
            this.policy = policy
        }

        /** Configures whether `ё` is treated as `е` during analysis. */
        fun normalizeYo(enabled: Boolean): Builder = apply {
            normalizeYo = enabled
        }

        /** Configures whether invalid input should throw an exception. */
        fun strict(enabled: Boolean): Builder = apply {
            strict = enabled
        }

        /** Creates a [Slogar] instance with the current settings. */
        fun build(): Slogar {
            return Slogar(
                RussianSyllabifierOptions(
                    policy = policy,
                    normalizeYo = normalizeYo,
                    strict = strict,
                ),
            )
        }
    }
}
