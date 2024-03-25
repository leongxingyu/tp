package seedu.stockpal.data;

import java.util.List;
import java.util.ArrayList;

import seedu.stockpal.common.Messages;
import seedu.stockpal.data.product.Product;
import seedu.stockpal.data.product.Pid;
import seedu.stockpal.data.product.Name;
import seedu.stockpal.data.product.Quantity;
import seedu.stockpal.data.product.Description;
import seedu.stockpal.data.product.Price;
import seedu.stockpal.exceptions.InsufficientAmountException;
import seedu.stockpal.exceptions.InventoryQuantityOverflowException;
import seedu.stockpal.exceptions.PidNotFoundException;
import seedu.stockpal.ui.Ui;
import static seedu.stockpal.ui.Ui.printToScreen;
import static seedu.stockpal.common.Messages.HORIZONTAL_LINE;


public class ProductList {
    public List<Product> products = new ArrayList<>();

    /**
     * @param toAdd Add a product to our list.
     */
    public void addProduct(Product toAdd) {
        products.add(toAdd);
    }

    /**
     * Finds product with a specific Pid and
     * returns its index in the product list.
     *
     * @param pid Product ID to search for.
     * @return Index of the product in the product list.
     *     If product is not in the product list, return -1.
     */
    public int findProductIndex(Pid pid) {
        for (int i = 0; i < products.size(); i++) {
            Product product = products.get(i);
            if (product.isPidMatch(pid)) {
                return i;
            }
        }
        return -1;
    }

    public void deleteProduct(Pid productPid) throws PidNotFoundException {
        int productIndex = findProductIndex(productPid);
        if (productIndex == -1) {
            throw new PidNotFoundException("Product with PID: " + productPid.toString() + " not found");
        }
        products.remove(productIndex);
    }

    public void updateProduct(int productIndex, Name newName, Quantity newQuantity
            , Description newDescription, Price newPrice) {
        Product updatedProduct = products.get(productIndex);

        if (!newName.isNull()) {
            updatedProduct.setName(newName);
        }
        if (!newQuantity.isNull()) {
            updatedProduct.setQuantity(newQuantity);
            updatedProduct.getQuantity().notifyLowQuantity(newQuantity);
        }
        if (!newDescription.isNull()) {
            updatedProduct.setDescription(newDescription);
        }
        if (!newPrice.isNull()) {
            updatedProduct.setPrice(newPrice);
        }
        products.set(productIndex, updatedProduct);
    }

    /**
     * Return true if the increase in quantity is successful.
     * Increases the quantity of the product with a specific PID.
     *
     * @param productIndex Product PID to update.
     * @param amountToIncrease Quantity of product to decrease.
     */
    public boolean increaseAmount(int productIndex, Integer amountToIncrease) {
        Product updatedProduct = products.get(productIndex);

        Quantity initialQuantity = updatedProduct.getQuantity();
        try {
            initialQuantity.updateIncreaseQuantity(initialQuantity, amountToIncrease);
            Ui.printToScreen("Quantity updated. " + initialQuantity);
            return true;
        } catch (InventoryQuantityOverflowException iqoe) {
            Ui.printToScreen("Overflow detected. No change to quantity. " + initialQuantity);
            return false;
        }
        //return updatedProduct.increaseQuantity(amountToIncrease);
    }


    /**
     * Return true if the decrease of quantity is successful.
     * Decreases the quantity of the product with a specific PID.
     *
     * @param productIndex Product PID to update
     * @param amountToDecrease Quantity of product to decrease
     */
    public boolean decreaseAmount(int productIndex, Integer amountToDecrease) {

        Product updatedProduct = products.get(productIndex);
        Quantity initialQuantity = updatedProduct.getQuantity();
        try {
            initialQuantity.updateDecreaseQuantity(initialQuantity, amountToDecrease);
            Ui.printToScreen("Quantity updated. " + updatedProduct.getQuantity());
            return true;
        } catch (InsufficientAmountException iae) {
            Ui.printToScreen("Insufficient amount in inventory. No change to quantity. "
                    + updatedProduct.getQuantity());
            return false;
        }
    }

    /**
     * @param productList ProductList object.
     * @param keyword Keyword to search for.
     */
    public static void findKeyword(ProductList productList, String keyword) {
        ProductList findList = new ProductList();
        for (int i = 0; i < productList.getSize(); i ++) {
            List<Product> products = productList.getProducts();
            Product product = products.get(i);
            String productName = product.getName().getName().toLowerCase();

            if (productName.equals(keyword)) {
                findList.addProduct(product);
            }
        }

        if (findList.isEmpty()) {
            printToScreen(Messages.MESSAGE_EMPTY_FIND_LIST);
            return;
        }

        Ui.printListTasks(findList);
    }

    public boolean isEmpty() {
        return products.isEmpty();
    }

    public int getSize() {
        return products.size();
    }

    public Product get(int i) {
        return products.get(i);
    }

    public List<Product> getProducts() {
        return products;
    }

    public String toSave(Integer productIndex) {
        Product currProduct = products.get(productIndex);
        return currProduct.toSave();
    }

    /**
     * Checks if product is low in quantity
     * If low, then prints out before the program exits
     * Else, if no low quantity products at all, print out "No product with low quantity"
     */
    public void printLowQuantityProducts () {
        boolean hasLowQuantity = false;

        Ui.printLowQuantityAlert();
        for (Product product : products) {
            Quantity productQuantity = product.getQuantity();
            if (productQuantity.isLowQuantity(product)) {
                Ui.printToScreen (product.getPid() + " | " + product.getName() + " | " +
                        productQuantity);
                Ui.printToScreen(HORIZONTAL_LINE);
                hasLowQuantity = true;
            }
        }

        if (!hasLowQuantity) {
            Ui.printNoLowQuantity();
            Ui.printToScreen(HORIZONTAL_LINE);
        }
    }
}
