import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utils.GuiConfigurator;
import worker.MainWorker;

public class Main {

    private static final Logger logger = LogManager.getLogger(Main.class);

    public static void main(String[] args) {

        GuiConfigurator configApp = new GuiConfigurator(config -> {
            MainWorker mainWorker = new MainWorker(config);

            logger.info("Starting program with the following configuration:");
            logger.info(config);

            try {
                mainWorker.start();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            mainWorker.stop();
        });
        configApp.display();
    }
}
