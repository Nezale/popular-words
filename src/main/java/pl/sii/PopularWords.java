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
        String words[];
        try {
            BufferedReader br = new BufferedReader(new FileReader("src/main/resources/3esl.txt"));
            while ((line =br.readLine()) != null){
                words = line.split(" ");
                for (String w: words) {
                    if(popular.get(w)==null) {
                        popular.put(w, 1L);
                    } else
                        popular.replace(w,popular.get(w)+1);
                }
            }
        } catch (IOException e){
            e.printStackTrace();
        }

       return  popular
                .entrySet()
                .stream()
                .sorted(comparingByValue(Comparator.reverseOrder()))
                .limit(1000)
                .collect(
                        toMap(p -> p.getKey(), p -> p.getValue(), (p1, p2) -> p2,
                                LinkedHashMap::new));
    }
}