import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.*;
import java.util.*;

public class WordIndex {
    private static final int LINES_PER_PAGE = 45;
    private static final int MAX_SIZE_LINE = 80;
    private static final int STOP_FREQUENCY_LIMIT = 100;

    public static String readLine(BufferedReader bufferedReader, int max_line_size) throws IOException {
        int currentPos=0;
        char[] data=new char[max_line_size];
        int firstElement = bufferedReader.read();
        if (firstElement == -1) {
            return null;
        }

        char currentChar= (char) firstElement;

        while(currentChar != Character.MAX_VALUE && !String.valueOf(currentChar).equals(System.lineSeparator()) &&
                (currentPos<max_line_size))
        {
            data[currentPos++]=currentChar;
            currentChar= (char) bufferedReader.read();
        }

        if(String.valueOf(currentChar).equals(System.lineSeparator()) && currentPos < 0)
            return(new String(data,0,currentPos-1));
        else
            return(new String(data,0,currentPos));
    }

    public static TreeMap<String, TreeSet<Integer>> createWordIndex(String file_path) throws IOException {
        HashMap<String, Integer> word_count = new HashMap<>();
        TreeMap<String, TreeSet<Integer>> word_index = new TreeMap<>(Comparator.comparing(String::toLowerCase));
        int line_count = 0;
        int page_number = 1;

        BufferedReader bufferedReader = new BufferedReader(new FileReader(file_path));
        while (true) {
            String line = readLine(bufferedReader, MAX_SIZE_LINE);
            if (line == null)
                break;

            line_count++;

            String[] words = line.split(" ");
            for (String word : words) {
                word = word.replaceAll(",", "");
                word = word.replaceAll("\r", "");
                if (!word.equals("e.g.") && !word.equals("i.e."))
                    word = word.replaceAll("\\.", "");


                if (word.isEmpty())
                    continue;

                if (!word_count.containsKey(word)) {
                    word_count.put(word, 0);
                    word_index.put(word, new TreeSet<>());
                }
                word_count.put(word, word_count.get(word) + 1);
                word_index.get(word).add(page_number);
            }

            if(line_count==LINES_PER_PAGE) {
                line_count = 0;
                page_number = page_number + 1;
            }
        }

        bufferedReader.close();

        for(Map.Entry<String, Integer> entry : word_count.entrySet())
            if (entry.getValue() > STOP_FREQUENCY_LIMIT)
                word_index.remove(entry.getKey());

        for(Map.Entry<String, TreeSet<Integer>> entry : word_index.entrySet()) {
            System.out.print(entry.getKey() + ": ");
            entry.getValue().forEach(element -> System.out.print(element + ", "));
            System.out.print('\n');
        }

        return word_index;
    }
}