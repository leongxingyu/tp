package seedu.stockpal.data;

import seedu.stockpal.common.Messages;
import seedu.stockpal.data.product.Pid;
import seedu.stockpal.ui.Ui;

import java.util.List;
import java.util.ArrayList;

import static seedu.stockpal.ui.Ui.printToScreen;

//@@author EdmundTangg
public class TransactionList {
    public List<Transaction> transactions = new ArrayList<>();

    /**
     * Add the new transaction into our transaction list
     *
     * @param toAdd new transaction to be added.
     */
    public void addTransaction(Transaction toAdd) {
        transactions.add(toAdd);
    }


    /**
     * Finds all inflow and outflow transactions for the particular product
     * and prints them out.
     *
     * @param transactionList TransactionList object.
     * @param inputPid Matching Pid to search for.
     */
    public static void findTransactions(TransactionList transactionList, Integer inputPid) {
        TransactionList findList = new TransactionList();

        for (int i = 0; i < transactionList.getSize(); i ++) {
            List<Transaction> transactions = transactionList.getTransactions();
            Transaction transaction = transactions.get(i);
            Integer pid = transaction.getPid();

            if (pid.equals(inputPid)) {
                findList.addTransaction(transaction);
            }
        }

        if (findList.isEmpty()) {
            printToScreen(Messages.MESSAGE_EMPTY_TRANSACTION_LIST);
            return;
        }

        Ui.printTransactionTasks(findList);
    }

    /**
     * @return the size of the transaction list.
     */
    public int getSize() {
        return transactions.size();
    }


    /**
     * @return the transaction list object.
     */
    public List<Transaction> getTransactions() {
        return transactions;
    }


    /**
     * @return true if the transaction object does not have any transactions.
     */
    public boolean isEmpty() {
        return transactions.isEmpty();
    }


    /**
     * @param i Index of the transaction.
     * @return the transaction.
     */
    public Transaction get(int i) {
        return transactions.get(i);
    }

    //@@author NgYaoDong
    /**
     * Delete Transactions related to the product getting deleted.
     *
     * @param pid PID of the product being deleted.
     */
    public void deleteTransactions(Pid pid) {
        transactions.removeIf(transaction -> transaction.getPid() == pid.getPid());
    }
}
