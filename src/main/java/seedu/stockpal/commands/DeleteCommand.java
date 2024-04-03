package seedu.stockpal.commands;

import seedu.stockpal.common.FormatUtils;
import seedu.stockpal.common.Messages;
import seedu.stockpal.data.ProductList;
import seedu.stockpal.data.product.Pid;
import seedu.stockpal.exceptions.PidNotFoundException;
import seedu.stockpal.ui.Ui;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * DeleteCommand class is responsible for setting up and executing the delete feature to delete a particular product
 * of a specific PID from the product list of StockPal.
 */
public class DeleteCommand extends ListActionCommand {

    public static final String COMMAND_KEYWORD = "delete";
    public static final String COMMAND_DESCRIPTION = "Deletes an existing product from the inventory.";
    public static final String COMMAND_USAGE = "Format: delete PID";

    public static final String[] COMMAND_FLAGS = {
        "PID"
    };

    public static final String[] COMMAND_FLAG_DESCRIPTIONS = {
        "Product ID of product"
    };
    private static final Logger LOGGER = Logger.getLogger(DeleteCommand.class.getName());
    private final Pid pid;

    public DeleteCommand(Integer pid) {
        this.pid = new Pid(pid);
    }

    /**
     * Deletes a product with specific PID from the product list of StockPal.
     * @param productList The product list that StockPal is mainly using to store product details.
     * @throws PidNotFoundException If product with PID that user specified cannot be found in productList
     */
    @Override
    public void execute(ProductList productList) throws PidNotFoundException {
        productList.deleteProduct(pid);
        Ui.printDeleteSuccessMessage();
        LOGGER.log(Level.INFO, Messages.MESSAGE_DELETE_SUCCESS);
    }

    //@@author Kobot7
    public static String commandDetails() {
        String formattedDetails = FormatUtils.formatCommandDetails(COMMAND_KEYWORD, COMMAND_DESCRIPTION
                , COMMAND_USAGE, COMMAND_FLAGS, COMMAND_FLAG_DESCRIPTIONS);
        return formattedDetails;
    }
    //@@author cheeseong2001
}
