package coaching.csv;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

/**
 * TODO Implement CSV writing logic here
 */
public class DefaultCsvWriter implements CsvWriter {
    private File file;
    private CsvFileConfig csvFileConfig;
    private FileWriter fileWriter;

    /**
     * Initialize writer
     *
     * @param config Config
     * @param file   File to be written
     */
    public DefaultCsvWriter(CsvFileConfig config, File file) throws IOException {
        if (config == null || file == null) {
            throw new IOException();
        }
        this.file = file;
        this.csvFileConfig = config;
    }

    /**
     * {@inheritDoc}
     *
     * @param line CSV line
     */
    @Override
    public void write(CsvLine line) throws IOException {
        fileWriter = new FileWriter(file);
        String lineToWrite = line.getData()
                .stream()
                .map(s -> csvFileConfig.getQuoteMode() ? "\"" + s + "\"" : s)
                .collect(Collectors.joining(csvFileConfig.getDelimiter()));
        fileWriter.write(lineToWrite);
        fileWriter.flush();
    }

    /**
     * {@inheritDoc}
     *
     * @param lines Multiple CSV lines
     */
    @Override
    public void write(Collection<CsvLine> lines) throws IOException {
        for (CsvLine line : lines) {
            write(line);
        }
    }

    /**
     * {@inheritDoc}
     *
     * @throws IOException
     */
    @Override
    public void close() throws IOException {
        fileWriter.close();
    }
}
