package seedu.stockpal.commands;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import seedu.stockpal.data.ProductList;
import seedu.stockpal.data.product.Pid;
import seedu.stockpal.exceptions.StockPalException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;


//@@author EdmundTangg
public class NewCommandTest {
    public ProductList productList;

    @BeforeEach
    public void setUp() {
        productList = new ProductList();
    }

    @Test
    void newCommand_allFieldsFilled_expectCorrectAllocation() throws StockPalException {
        NewCommand userInput = new NewCommand("chocolate", 100,
                2.00, "ingredient");
        userInput.execute(productList);

        assertEquals("chocolate", productList.getProducts().get(0).getName().getName());
        assertEquals(100, productList.getProducts().get(0).getQuantity().getQuantity());
        assertEquals(2.00, productList.getProducts().get(0).getPrice().getPrice());
        assertEquals("ingredient", productList.getProducts().get(0).getDescription().getDescription());
        assertEquals(1, productList.getProducts().get(0).getPid().getPid());
    }

    @Test
    void newCommand_compulsoryFieldsFilled_expectCorrectAllocation() throws StockPalException {
        NewCommand userInput = new NewCommand("chocolate",
                100, null, null);
        userInput.execute(productList);

        assertEquals("chocolate", productList.getProducts().get(0).getName().getName());
        assertEquals(100, productList.getProducts().get(0).getQuantity().getQuantity());
        assertNull(productList.getProducts().get(0).getPrice().getPrice());
        assertNull(productList.getProducts().get(0).getDescription().getDescription());
        assertEquals(1, productList.getProducts().get(0).getPid().getPid());
    }

    @Test
    void newCommand_twoProductsWithCompulsoryFieldsFilled_expectCorrectAllocation() throws StockPalException {
        NewCommand userInput1 = new NewCommand("chocolate", 100,
                2.00, "ingredient");
        userInput1.execute(productList);

        NewCommand userInput2 = new NewCommand("strawberry", 200,
                null, null);
        userInput2.execute(productList);

        assertEquals("chocolate", productList.products.get(0).getName().getName());
        assertEquals(1, productList.products.get(0).getPid().getPid());

        assertEquals("strawberry", productList.products.get(1).getName().getName());
        assertEquals(2, productList.products.get(1).getPid().getPid());
    }



    @Test
    void newCommand_duplicateItems_expectExecptionThrown() throws StockPalException {
        NewCommand userInput = new NewCommand("chocolate", 100,
                2.00, "ingredient");
        userInput.execute(productList);

        NewCommand duplicateInput = new NewCommand("chocolate", 100,
                3.00, "ingredients");
        //duplicateInput.execute(productList);

        assertThrows(StockPalException.class, () -> duplicateInput.execute(productList));

    }
    @Test
    void newCommand_withProductsDeleted_expectCorrectPidAllocation() throws StockPalException {
        NewCommand userInput = new NewCommand("chocolate", 100,
                2.00, "ingredient");
        userInput.execute(productList);

        NewCommand userInput2 = new NewCommand("banana", 100,
                2.00, "ingredient");
        userInput2.execute(productList);

        Pid pid = productList.getProducts().get(0).getPid();

        productList.deleteProduct(pid);

        NewCommand userInput3 = new NewCommand("watermelon", 100,
                2.00, "ingredient");
        userInput3.execute(productList);

        assertEquals(3, productList.products.get(1).getPid().getPid());

    }
}




