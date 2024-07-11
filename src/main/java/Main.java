import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import picocli.CommandLine;
import utils.SearchConfig;
import worker.MainWorker;

public class Main {

    private static final Logger logger = LogManager.getLogger(Main.class);

    public static void main(String[] args) throws InterruptedException{

        //Search Arguments parsing from CommandLine
        SearchConfig config = new SearchConfig();
        CommandLine cmd = new CommandLine(config);
        cmd.parseArgs(args);
        MainWorker mainWorker = new MainWorker(config);

        logger.info("Starting program with following configuration:");

        logger.info("Searched phrase: {}",config.getSearchPhrase());
        logger.info("Products location: {}",config.getSearchLocation());
        logger.info("Price starting from: {}",config.getPriceFrom());
        logger.info("Maximum price: {}",config.getPriceTo());
        logger.info("Number of products to be saved into Excel file: {}",config.getSaveElements());

        mainWorker.start();
        mainWorker.stop();
    }
}
