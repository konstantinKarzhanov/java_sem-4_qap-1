package com.keyin;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class TestSuggestionEngine {
    private final Path path = Paths.get(ClassLoader.getSystemResource("words.txt").getPath());
    private final SuggestionEngine suggestionEngineUnderTheTest = new SuggestionEngine();

    @Test
    public void testCorrectWordNoSuggestions() throws IOException {
        suggestionEngineUnderTheTest.loadDictionaryData(this.path);
        String correctWord = "word";

        Assertions.assertEquals(suggestionEngineUnderTheTest.generateSuggestions(correctWord), "");
    }

    @Test
    public void testErrorWordSuggestionsExist() throws IOException {
        suggestionEngineUnderTheTest.loadDictionaryData(this.path);

        String errorWord = "worf";
        Assertions.assertFalse(suggestionEngineUnderTheTest.generateSuggestions(errorWord).isEmpty());
    }

    @Test
    public void testErrorWordSuggestionsLimit() throws IOException {
        suggestionEngineUnderTheTest.loadDictionaryData(this.path);

        String errorWord = "worf";
        Assertions.assertTrue(suggestionEngineUnderTheTest.generateSuggestions(errorWord).split("\\n").length <= 10);
    }

    @Test
    public void testErrorWordSuggestionsContainCorrectWord() throws IOException {
        suggestionEngineUnderTheTest.loadDictionaryData(this.path);

        String errorWord = "worf";
        Assertions.assertFalse(suggestionEngineUnderTheTest.generateSuggestions(errorWord).contains("word"));
    }
}
