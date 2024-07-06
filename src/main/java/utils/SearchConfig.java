package utils;
import picocli.CommandLine.Option;
import picocli.CommandLine.Command;

@Command(name = "Config", mixinStandardHelpOptions = true, version = "Config 1.0",
        description = "Search configuration")
public class SearchConfig {

    @Option(names = {"-p", "--phrase"}, description = "Phrase to be found", required = true)
    private String searchPhrase;

    @Option(names = {"-l", "--location"}, description = "Location to search in", required = true)
    private String searchLocation;

    @Option(names = {"-e", "--elements"}, description = "Number of elements to include in excel file", defaultValue = "5")
    private int saveElements;

    public String getSearchPhrase() {
        return searchPhrase;
    }

    public String getSearchLocation() {
        return searchLocation;
    }

    public int getSaveElements() {
        return saveElements;
    }
}
