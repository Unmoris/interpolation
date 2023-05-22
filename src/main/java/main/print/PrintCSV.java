package main.print;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;

import main.charecters.Character;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

public class PrintCSV {

    public static void print(List<Character> characters, String nameFile) {
        try {
            CSVFormat csvFormat = CSVFormat.EXCEL.builder()
                    .setDelimiter(';')
                    .setHeader(
                            "Name function",
                            "interpolationX",
                            "interpolationValue",
                            "f(x)",
                            "time",
                            "fallibility",
                            "relativeError",
                            "Delta center",
                            "Count Points",
                            "Length X"
                    )
                    .build();


            try (
                    BufferedWriter writer = Files.newBufferedWriter(
                            Paths.get("csv-res/"
                                    + nameFile + "_"
                                    + LocalDateTime.now().getHour() + "-"
                                    + LocalDateTime.now().getMinute() + "-"
                                    + LocalDateTime.now().getSecond() + "-"
                                    + ".csv"));
                    CSVPrinter csvPrinter = new CSVPrinter(writer, csvFormat);
            ) {
                for (Character character :
                        characters) {
                    csvPrinter.printRecord(
                            character.funcName,
                            character.interpolationX,
                            character.getInterpolationValue,
                            character.valueFx,
                            character.time,
                            character.fallibility,
                            character.relativeError,
                            character.deltaCenter,
                            character.countPoints,
                            character.lengthX
                    );
                }

                csvPrinter.flush();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
