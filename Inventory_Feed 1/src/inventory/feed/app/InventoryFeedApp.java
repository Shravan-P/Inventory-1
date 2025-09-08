package inventory.feed.app;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import inventory.feed.service.InventoryService;

public class InventoryFeedApp {
    private static final Logger logger = Logger.getLogger("InventoryFeedApp");

    private static final String INPUT_DIR = "D:\\litmus7\\Inventory_Feed 1\\src\\files\\input file";
    private static final String PROCESSED_DIR ="D:\\litmus7\\Inventory_Feed 1\\src\\files\\processed files";
    private static final String ERROR_DIR = "D:\\litmus7\\Inventory_Feed 1\\src\\files\\error files";

    public static void main(String[] args) {
        try {
            Files.createDirectories(Paths.get(PROCESSED_DIR));
            Files.createDirectories(Paths.get(ERROR_DIR));

            List<Path> fileList = new ArrayList<>(Files.list(Paths.get(INPUT_DIR)).toList());


            // Process each file
            InventoryService service = new InventoryService();
            for (Path file : fileList) {
            	logger.info("Found file: " + file.getFileName());
                service.processFile(file, PROCESSED_DIR, ERROR_DIR);
            }

        } catch (IOException e) {
            logger.severe("Error scanning input folder: ");
        }
    }
}
