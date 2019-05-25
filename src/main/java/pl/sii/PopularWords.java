package pl.sii;

import org.apache.commons.lang3.NotImplementedException;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

import static java.util.Map.Entry.comparingByValue;
import static java.util.stream.Collectors.toMap;

public class PopularWords {

    public static void main(String[] args) {
        PopularWords popularWords = new PopularWords();
        Map<String, Long> result = popularWords.findOneThousandMostPopularWords();
        result.entrySet().forEach(System.out::println);
    }

    public Map<String, Long> findOneThousandMostPopularWords(){

        Map<String,Long> popular = new HashMap<>();
        String line;
        Long number = 0L;
        try {
            BufferedReader br = new BufferedReader(new FileReader("src/main/resources/3esl.txt"));
            while ((line =br.readLine()) != null){
                if(popular.get(line)==null) {
                    popular.put(line, 1L);
                } else
                    popular.replace(line,popular.get(line)+1);
            }
        } catch (IOException e){
            e.printStackTrace();
        }
        return popular
                .entrySet()
                .stream()
                .sorted(comparingByValue(Comparator.reverseOrder()))
                .collect(
                        toMap(e -> e.getKey(), e -> e.getValue(), (e1, e2) -> e2,
                                LinkedHashMap::new));

    }
}