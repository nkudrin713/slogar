package dev.nkudrin.slogar.internal

private val russianVowels = setOf('а', 'е', 'ё', 'и', 'о', 'у', 'ы', 'э', 'ю', 'я')
private val russianConsonants = setOf(
    'б', 'в', 'г', 'д', 'ж', 'з', 'й', 'к', 'л', 'м', 'н',
    'п', 'р', 'с', 'т', 'ф', 'х', 'ц', 'ч', 'ш', 'щ',
)
private val russianSigns = setOf('ь', 'ъ')

internal fun Char.isRussianVowel(): Boolean = lowercaseChar() in russianVowels

internal fun Char.isRussianConsonant(): Boolean = lowercaseChar() in russianConsonants

internal fun Char.isRussianShortI(): Boolean = lowercaseChar() == 'й'

internal fun Char.isRussianSign(): Boolean = lowercaseChar() in russianSigns

internal fun Char.isRussianLetter(): Boolean {
    val lower = lowercaseChar()
    return lower in russianVowels || lower in russianConsonants || lower in russianSigns
}
