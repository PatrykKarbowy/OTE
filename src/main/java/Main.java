import picocli.CommandLine;
import utils.SearchConfig;
import worker.MainWorker;

public class Main {
    public static void main(String[] args) throws InterruptedException{
        //Search Arguments parsing from CommandLine
        SearchConfig config = new SearchConfig();
        CommandLine cmd = new CommandLine(config);
        cmd.parseArgs(args);

        MainWorker mainWorker = new MainWorker(config);
        mainWorker.start();
        mainWorker.stop();
    }
}
