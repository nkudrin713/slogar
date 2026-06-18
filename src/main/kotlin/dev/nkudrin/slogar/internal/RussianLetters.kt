package dev.nkudrin.slogar.internal

internal object RussianLetters {
    private val vowels = setOf('а', 'е', 'ё', 'и', 'о', 'у', 'ы', 'э', 'ю', 'я')
    private val consonants = setOf(
        'б', 'в', 'г', 'д', 'ж', 'з', 'й', 'к', 'л', 'м', 'н',
        'п', 'р', 'с', 'т', 'ф', 'х', 'ц', 'ч', 'ш', 'щ',
    )
    private val signs = setOf('ь', 'ъ')

    fun isVowel(char: Char): Boolean = char.lowercaseChar() in vowels

    fun isConsonant(char: Char): Boolean = char.lowercaseChar() in consonants

    fun isShortI(char: Char): Boolean = char.lowercaseChar() == 'й'

    fun isSign(char: Char): Boolean = char.lowercaseChar() in signs

    fun isRussianLetter(char: Char): Boolean {
        val lower = char.lowercaseChar()
        return lower in vowels || lower in consonants || lower in signs
    }
}
