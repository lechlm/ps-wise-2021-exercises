import static org.junit.Assert.*;
import org.junit.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import java.io.IOException;
import java.util.TreeMap;
import java.util.TreeSet;

public class WordIndexTest {
    @Test
    public void test_word_index_file1() throws IOException {
        TreeMap<String, TreeSet<Integer>> wordIndex = WordIndex.createWordIndex(
                "/Users/lechlm/Documents/OWN/ps/ps-wise-2021-exercises/session-01/file1.txt");
        assertThat(wordIndex.get("Auslesen"), hasItem(1));
        assertThat(wordIndex.size(), is(15));
    }

    @Test
    public void test_word_index_file2() throws IOException {
        TreeMap<String, TreeSet<Integer>> wordIndex = WordIndex.createWordIndex(
                "/Users/lechlm/Documents/OWN/ps/ps-wise-2021-exercises/session-01/file2.txt");
        assertEquals(wordIndex.size(), 15);
        assertTrue(wordIndex.get("Test").contains(1));
    }

    @Test
    public void test_word_index_file3() throws IOException {
        TreeMap<String, TreeSet<Integer>> wordIndex = WordIndex.createWordIndex(
                "/Users/lechlm/Documents/OWN/ps/ps-wise-2021-exercises/session-01/file3.txt");
        assertEquals(wordIndex.size(), 15);
        assertTrue(wordIndex.get("Test").contains(2));
    }

}