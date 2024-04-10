package seedu.stockpal.commands;

import seedu.stockpal.common.FormatUtils;
import seedu.stockpal.common.Messages;
import seedu.stockpal.data.ProductList;
import seedu.stockpal.data.product.Product;
import seedu.stockpal.ui.Ui;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ListCommand extends ListActionCommand {
    public static final String COMMAND_KEYWORD = "list";
    public static final String COMMAND_DESCRIPTION = "List all products in inventory.";
    public static final String COMMAND_USAGE = "list [-sn|-sq]";

    public static final String[] COMMAND_FLAGS = {"-sn", "-sq"};

    public static final String[] COMMAND_FLAG_DESCRIPTIONS = {
        "Use this flag to sort by product name (alphabetically from A to Z)"
        , "Use this flag to sort by product quantity (ascending)"
    };
    private static final Logger LOGGER = Logger.getLogger(ListCommand.class.getName());

    private String sortType = null;

    public ListCommand(String sortType) {
        if (sortType != null) {
            this.sortType = sortType.trim();
        }
    }

    /**
     * Prints out all products that are in the list.
     * It sorts the products to either the name, quantity or by default, it's PID.
     * If the list is empty, it tells the user that the list is empty.
     *
     * @param productList contains all the products in the inventory.
     */
    @Override
    public void execute(ProductList productList) {
        if (productList.isEmpty()) {
            Ui.printEmptyListMessage();
            return;
        }

        ProductList newList = new ProductList();
        newList.products = new ArrayList<>(productList.getProducts());
        sortListAccordingly(newList);

        Ui.printListTasks(newList);
        LOGGER.log(Level.INFO, Messages.MESSAGE_LIST_SUCCESS);
    }

    /**
     * Sorts a new product list accordingly to either quantity or name.
     * This is to prevent modification to the actual list, when it's sorted.
     *
     * @param newList is a copy of the original list.
     */
    private void sortListAccordingly(ProductList newList) {
        if (sortType != null) {
            switch (sortType) {
            case "-sq":
                newList.products.sort(new SortByQuantity());
                break;
            case "-sn":
                newList.products.sort(new SortByName());
                break;
            default:
                break;
            }
        }
    }

    /**
     * Creates a comparator that sorts a product by quantity.
     */
    private static class SortByQuantity implements Comparator<Product> {
        @Override
        public int compare(Product a, Product b) {
            return a.getQuantity().getQuantity() - b.getQuantity().getQuantity();
        }
    }

    /**
     * Creates a comparator that sorts a product by name.
     */
    private static class SortByName implements Comparator<Product> {
        @Override
        public int compare(Product a, Product b) {
            return a.getName().getName().toLowerCase().
                    compareTo(b.getName().getName().toLowerCase());
        }
    }

    //@@author Kobot7
    public static String commandDetails() {
        String formattedDetails = FormatUtils.formatCommandDetails(COMMAND_KEYWORD, COMMAND_DESCRIPTION
                , COMMAND_USAGE, COMMAND_FLAGS, COMMAND_FLAG_DESCRIPTIONS);
        return formattedDetails;
    }
}
