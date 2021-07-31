package coaching.csv;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * TODO Implement CSV parsing logic here
 */
public class DefaultCsvParser implements CsvParser {

    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultCsvParser.class);
    private CsvFileConfig csvFileConfig;
    private Scanner scanner;

    /**
     * Initialize parser
     *
     * @param file         CSV file
     * @param parserConfig Configuration
     */
    public DefaultCsvParser(File file, CsvFileConfig parserConfig) {
        if (Objects.isNull(file)) {
            LOGGER.error("File has nothing to parse");
        }
        this.csvFileConfig = parserConfig;

        try {
            this.scanner = new Scanner(file);
            LOGGER.info("Import data successful");
        } catch (FileNotFoundException e) {
            LOGGER.error("Can not read file: {}", Optional.ofNullable(file.getName()).orElse("null"));
            this.scanner.close();
        }
    }

    /**
     * {@inheritDoc}
     *
     * @throws IOException
     */
    @Override
    public void close() {
        if (this.scanner != null) {
            this.scanner.close();
            LOGGER.info("Close successfully");
        }
    }

    /**
     * {@inheritDoc}
     *
     * @return
     */
    @Override
    public boolean hasNext() {
       if (this.scanner != null) {
           this.scanner.hasNextLine();
       }
       LOGGER.info("Line not found");
       return false;
    }

    /**
     * {@inheritDoc}
     *
     * @return
     */
    @Override
    public CsvLine next() {
        if (this.scanner == null) {
            LOGGER.error("Can not read the file");
        }
            String line = this.scanner.next();
            if (csvFileConfig.getDelimiter() == "|") {
                return mapToCsvLine(Stream.of(line.split("\\|"))
                        .map(String::trim)
                        .collect(Collectors.toList()));
            }
            return mapToCsvLine(Stream.of(line.split(csvFileConfig.getDelimiter()))
                    .map(String::trim)
                    .collect(Collectors.toList()));
    }

    /**
     * Map each line to csv line
     *
     * @param reader list data
     * @return  csv line
     */
    private CsvLine mapToCsvLine(List<String> reader) {
        CsvLine csvLine = new CsvLine();
        for (int i = 0; i < reader.size(); i++) {
            csvLine.set(i, reader.get(i));
        }
        return csvLine;
    }
}
