package main.print;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
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
                            "interpolationX",
                            "interpolationValue",
                            "f(x)",
                            "f",
                            "time",
                            "fallibility",
                            "relativeError"
                    )
                    .build();


            try (
                    BufferedWriter writer = Files.newBufferedWriter(Paths.get(nameFile + ".csv"));
                    CSVPrinter csvPrinter = new CSVPrinter(writer, csvFormat);
            ) {
                for (Character character :
                        characters) {
                    csvPrinter.printRecord(
                            character.interpolationX,
                            character.getInterpolationValue,
                            character.valueFx,
                            character.func,
                            character.time,
                            character.fallibility,
                            character.relativeError

                    );
                }

                csvPrinter.flush();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
