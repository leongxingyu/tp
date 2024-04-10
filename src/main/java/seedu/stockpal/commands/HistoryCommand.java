package seedu.stockpal.commands;

import seedu.stockpal.common.FormatUtils;
import seedu.stockpal.common.Messages;
import seedu.stockpal.data.ProductList;
import seedu.stockpal.data.TransactionList;
import seedu.stockpal.data.product.Pid;
import seedu.stockpal.exceptions.StockPalException;

import java.util.logging.Level;

import static seedu.stockpal.commands.EditCommand.logger;

//@@author EdmundTangg
public class HistoryCommand extends TransactionActionCommand {
    public static final String COMMAND_KEYWORD = "history";
    public static final String COMMAND_DESCRIPTION =
        "Displays the inflow and outflow history of a product.";
    public static final String COMMAND_USAGE =
        "history PID";
    public static final String[] COMMAND_FLAGS = {
        "PID"
    };
    public static final String[] COMMAND_FLAG_DESCRIPTIONS = {
        "Product ID of product"
    };
    private final Pid pid;

    public HistoryCommand(Integer pidValue) {
        this.pid = new Pid(pidValue);
    }

    @Override
    public void execute(ProductList productList, TransactionList transactionList) throws StockPalException {
        productList.findProductIndex(pid);
        TransactionList.findTransactions(transactionList, pid.getPid());

        logger.log(Level.INFO, Messages.MESSAGE_HISTORY);
    }

    //@@author Kobot7
    public static String commandDetails() {
        String formattedDetails = FormatUtils.formatCommandDetails(COMMAND_KEYWORD, COMMAND_DESCRIPTION
                , COMMAND_USAGE, COMMAND_FLAGS, COMMAND_FLAG_DESCRIPTIONS);
        return formattedDetails;
    }
}
