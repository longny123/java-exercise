package coaching.csv;

/**
 * Configuration used to determine parsing strategy and writing strategy
 */
public class CsvFileConfig {
    private String delimiter = ",";

    private boolean quoted = true;

    /**
     * Character as splitting character between segments
     *
     * @param delimiter Delimiter character
     */
    public void setDelimiter(String delimiter) {
        this.delimiter= delimiter;
    }

    /**
     *
     *
     * @return
     */
    public String getDelimiter() {
        return delimiter;
    }

    /**
     * Is data wrapped with double quotes
     *
     * @param quoted true is double-quotes-wrapped data, otherwise false
     */
    public void setQuoteMode(boolean quoted) {
        this.quoted = quoted;
    }

    public boolean getQuoteMode() {
        return quoted;
    }
}
