# slogar

Kotlin library for syllabification of Russian words.

The current `SchoolSimple` policy works with written words and does not implement phonetic
transcription or line-breaking rules. `slogar` preserves the original casing in returned syllables.

## Usage

Use the default configuration:

```kotlin
val result = Slogar().split("письмо")

result.syllables // ["пись", "мо"]
result.warnings // []
```

Configure an instance through the fluent builder:

```kotlin
val slogar = Slogar
    .strict(true)
    .normalizeYo(true)
    .build()
```

Available settings:

- `policy(...)` selects the boundary rule set. Version `0.1` provides `SchoolSimple` only.
- `normalizeYo(true)` treats `ё` as `е` during analysis without changing the returned text.
- `strict(true)` throws `IllegalArgumentException` for invalid input or an unresolved boundary.

## Rules

A syllable is built around a Russian vowel. Rules are applied to the characters between each pair
of adjacent vowels in this order:

1. Adjacent vowels are separated: `поэт` -> `по-эт`.
2. A separating `ь` or `ъ` remains in the previous syllable: `колья` -> `коль-я`,
   `подъезд` -> `подъ-езд`.
3. `й` after a vowel remains in the previous syllable: `майка` -> `май-ка`,
   `район` -> `рай-он`.
4. A single consonant moves to the next syllable: `мама` -> `ма-ма`.
5. In a consonant group, the boundary is placed before the last consonant: `окно` -> `ок-но`,
   `сестра` -> `сест-ра`, `письмо` -> `пись-мо`.

The number of syllables equals the number of Russian vowels. The letters `й`, `ь`, and `ъ` do not
form syllables by themselves.

## Input Contract

`split` accepts one Russian word. Leading and trailing whitespace is ignored during analysis, but
the exact input remains available in `Syllables.original`.

In the default non-strict mode:

- blank input returns no syllables and `BlankInput`;
- a word without vowels returns one chunk and `NoVowels`;
- Latin letters, digits, hyphens, or internal whitespace return one chunk and `NonRussianLetters`;
- an unmatched valid boundary returns one chunk and `UnresolvedBoundary`.

Warnings are returned through `Syllables.warnings`. Strict mode turns these invalid-input cases
into `IllegalArgumentException`.

## License

Licensed under the [Apache License 2.0](LICENSE).
