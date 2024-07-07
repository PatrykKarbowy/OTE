import picocli.CommandLine;
import utils.SearchConfig;
import worker.MainWorker;

public class Main {
    public static void main(String[] args){
        //Search Arguments parsing
        SearchConfig config = new SearchConfig();
        CommandLine cmd = new CommandLine(config);
        cmd.parseArgs(args);

        MainWorker mainWorker = new MainWorker(config);
        mainWorker.start();
    }
}
