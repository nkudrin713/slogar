package dev.nkudrin.slogar.internal

import dev.nkudrin.slogar.RussianSyllabifierOptions
import kotlin.test.Test
import kotlin.test.assertEquals

class WordNormalizerTest {
    @Test
    fun `returns trimmed original and lowercase text when normalizing word`() {
        val result = WordNormalizer.normalize("  ЁлКа  ", RussianSyllabifierOptions())

        assertEquals("ЁлКа", result.original)
        assertEquals("ёлка", result.text)
    }

    @Test
    fun `replaces yo in normalized text when option is enabled`() {
        val options = RussianSyllabifierOptions(normalizeYo = true)

        val result = WordNormalizer.normalize("Ёлка", options)

        assertEquals("Ёлка", result.original)
        assertEquals("елка", result.text)
    }
}
