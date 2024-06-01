package org.myorg.quickstart.src;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;

public class CsvToMapConverter {
    public static Map<String, String> get() {
//        String csvFilePath = "C:\\Users\\nksokolo\\Desktop\\flink\\sights.csv";
        String csvFilePath = "/root/data/sights.csv";

        Map<String, String> dataMap = new HashMap<>();

        try (Reader reader = new FileReader(csvFilePath);
             CSVParser csvParser = new CSVParser(reader, CSVFormat.newFormat(';'))) {

            for (CSVRecord record : csvParser) {
                String key = record.get(0);
                String value = record.get(1);
                dataMap.put(key, value);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    return dataMap;
    }
}