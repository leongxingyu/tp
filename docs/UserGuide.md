---
title: User Guide
---

# Introduction
StockPal is a command line interface (CLI) application designed to help small E-commerce business owners who are 
just starting up to manage their stock-taking effectively. Users would mostly interact with StockPal via text commands.

StockPal is compatible for usage on Windows, Mac and Linux systems.

Not sure where to begin? Start by learning [how to utilize this user guide](#using-this-guide).

# Table of Contents

- [Introduction](#introduction)
- [Using This Guide](#using-this-guide)
- [Quick Start](#quick-start)
    - [Downloading StockPal](#downloading-stockpal)
    - [Running StockPal](#running-stockpal)
- [Features](#features)
    - [Viewing help: `help`](#viewing-help-help)
    - [Adding new product: `new`](#adding-a-new-product-new)
    - [Listing all products: `list`](#listing-all-products-list)
    - [Editing product details: `edit`](#editing-product-details-edit)
    - [Deleting a product and its details: `delete`](#deleting-a-product-and-its-details-delete)
    - [Increasing quantity of existing product: `inflow`](#increasing-a-product-quantity-inflow)
    - [Decreasing quantity of existing product: `outflow`](#decreasing-a-product-quantity-outflow)
    - [Viewing past inflow/outflow of existing product: `history`](#viewing-past-inflow--outflow-of-existing-product-history)
    - [Find a keyword in the Product list: `find`](#find-a-keyword-in-the-product-list-find)
    - [Exiting the program: `exit`](#exiting-the-program-exit)
    - [Saving the data](#saving-the-data)
    - [Editing the data file](#editing-the-data-file)
- [Command Summary](#command-summary)

<!--- @@author NgYaoDong --->
# Using This Guide
Below are the symbols used in this guide and the meaning of these symbols.

| **Symbol**           | **Meaning**                                                                                      |
|----------------------|--------------------------------------------------------------------------------------------------|
| :exclamation:        | The exclamation mark symbol indicates actions that you should be _cautious_ about.               |
| :information_source: | The **i** symbol indicates notes that are important for you when using the application.          |
| :bulb:               | The lightbulb symbol indicates tips that hold useful information or advice that you should know. |

<!--- @@author Kobot7 --->
# Quick Start

## Downloading StockPal

1.  Ensure you have [Java 11](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html) or above installed in your computer.

2.  Download the latest `stockpal.jar` from [here](https://github.com/AY2324S2-CS2113T-T09-3/tp/releases).

3.  Copy the file to the folder you want to use as the home folder for
    the application. For example,`C:\Users\setupuser\Documents\StockPal\stockpal.jar`.

## Running StockPal

1.  **For Windows users:**

    Open a command prompt terminal. You can do so by searching for `command
    prompt` in the Windows search bar.<br>

    <img src="images/Windows-find-cmd.png" alt="images/Windows-find-cmd.png" width="450px"/>
    <br><br>
    <!--- @@author leongxingyu --->
    
    **For MAC users:**

    Open a terminal. You can do so by searching for the `terminal` using the Spotlight icon in the menu bar. Click on the terminal.<br>

    <img src="images/Mac-find-cmd.png" alt="images/Mac-find-cmd.png" width="450px"/>
    <br><br>
<!--- @@author Kobot7 --->
2.  Determine the file path to the home folder of `stockpal.jar`. You can
    do so by navigating to `stockpal.jar` in File Explorer (Windows) or Finder (MAC). For example,
    the image below shows that the file path to the home folder of
    `stockpal.jar` is `C:\Users\setupuser\Documents\StockPal`.<br>

    <img src="images/Windows-home-folder.png" alt="images/Windows-home-folder.png" width="600px"/>
    <br><br>

3.  In the command prompt terminal, navigate to the home folder using
    the command `cd <file path to the home folder>`. For example, `cd
    C:\Users\setupuser\Documents\StockPal`.<br>

    <img src="images/cmd-navigate-to-home-folder.png" alt="images/cmd-navigate-to-home-folder.png" width="700px"/>
    <br><br>

4.  In the command prompt terminal, run StockPal using the command `java
    -jar ./stockpal.jar`.

# Glossary

PID (Product ID) - a number that we use to keep track of the products.

CLI (Command Line Interface) - a text-based interface used to interact with our program by entering commands into a terminal.

CSV (Comma-Separated Values) - a file format used to store tabular data. Each line represents a row of data, and each field within a row is separated by a comma

<!--- @@author NgYaoDong --->
## Features
<div style="padding: 15px; border: 1px solid transparent; margin-bottom: 20px; border-radius: 4px; color: #31708f; background-color: #d9edf7;">
:information_source: <strong>Notes about the command format</strong>

<ul>
    <li>Words in UPPER_CASE are the parameters to be supplied by the user.</li>
    <li>Items in square brackets are optional.</li>
    <li>Parameters must be in the specified order.</li>
    <li>Commands are case-sensitive and must strictly follow case specified.</li>
</ul>
</div>

<!--- @@author Kobot7 --->
## Viewing help: `help`

Provides command details for all or specific commands.

### When to use?
This is a useful command to start with if you are new to StockPal!

Format: `help [COMMAND]`
- COMMAND is the command for which you wish to display the help page.
- COMMAND must be a valid command keyword.
- List of valid command keywords: help, new, list, edit, delete, inflow, outflow, history, find, exit
- If COMMAND is left empty, command details for all commands will be displayed.

### Example 1
Input:

```
help inflow 
```

Output:
```
====================================================================================
Command: inflow

Description: Increases the quantity of a product from the existing amount.

Usage: inflow PID a/INCREMENT_AMOUNT

Options:
PID                   Product ID of product
INCREMENT_AMOUNT      Quantity of product to add
====================================================================================
```


<!--- @@author EdmundTangg --->
## Adding a new product: `new`

Creates a new product to the inventory and assigns a unique Product ID (PID) to it.

### When to use?

When starting as a new user of StockPal, use this command repeatedly to fill up your inventory to start tracking!
Subsequently, when your business introduces new products, use this command to add them in too!


Format:
```
new n/PRODUCT_NAME q/INITIAL_QUANTITY [p/PRICE] [d/DESCRIPTION]
```

- PRODUCT_NAME is limited to 100 characters.
- INITIAL_QUANTITY must be an integer more than or equal to 0.
- PRICE must be a numeric value more than or equal to 0, and have exactly 2 decimal places.
- DESCRIPTION is limited to 100 characters.

### Example 1
Input:

```
new n/Drinking Cup q/20 
```

Output:

```
Product has been added.
```

Explanation:

This command adds your product to the inventory. The product’s details are as follows:
- Name of the product is `Drinking Cup`
- Quantity of chocolate Milk stock is `20` units


### Example 2
Input:

```
new n/Chocolate Milk q/100 p/2.00 d/Marigold HL Milk
```

Output:

```
Product has been added.
```

Explanation:

This command adds your product to the inventory. The product’s details are as follows:
- Name of the product is `Chocolate Milk`
- Quantity of chocolate Milk stock is `100` units
- Price of each unit is `$2.00`
- Description of the Chocolate Milk product is `Marigold HL Milk`, which is the brand


<!--- @@author wjunjie01 --->
## Listing all products: `list`
Lists all products in inventory.

Format: `list [-sn] [-sq]`

Sorting:
- By default, products are sorted according to their PID.
- To sort according to their names, use the `-sn` field.
- To sort according to their quantity, use the `-sq` field.

:bulb: Some of the commands may require PID as a compulsory field. Use `list` to obtain
the required PID of the particular product you want to access.

### Example 1

Context:
- View a list sorted by product PID.

Input: `list`

Output:
```
====================================================================================
1. PID: 1  |  Name: Milk  |  Quantity: 40  |  Price: $8.00
Description: Made by happy cows!
====================================================================================
2. PID: 2  |  Name: Banana  |  Quantity: 50  |  Price: $5.00
Description: A bunch of bananas.
====================================================================================
3. PID: 3  |  Name: Corn  |  Quantity: 30  |  Price: $1.00
Description: It's corn!
====================================================================================
```

### Example 2

Context:
- Sort a list according to the quantity of the products.

Input: `list -sq`

Output:
```
====================================================================================
1. PID: 3  |  Name: Corn  |  Quantity: 30  |  Price: $1.00
Description: It's corn!
====================================================================================
2. PID: 1  |  Name: Milk  |  Quantity: 40  |  Price: $8.00
Description: Made by happy cows!
====================================================================================
3. PID: 2  |  Name: Banana  |  Quantity: 50  |  Price: $5.00
Description: A bunch of bananas.
====================================================================================
```


<!--- @@author Kobot7 --->
## Editing product details: `edit`

Edits an existing product in the inventory at the specific PID by the input value(s).

Format: 
```
edit PID [n/PRODUCT_NAME] [q/QUANTITY] [d/DESCRIPTION] [p/PRICE]
```

- At least one optional field must be provided.
- PID must be a valid Product ID of an existing product.
- QUANTITY must be an integer more than or equals to 0.
- PRICE must be a numeric value more than or equals to 0, and have exactly 2 decimal places.
- DESCRIPTION is limited to 100 characters.

### Example 1
Context:
- Product `Sticky note`’s Product ID (PID) is 1.
- You wish to change `Sticky note`'s information as follows.
  - PRICE: $2.00
  - DESCRIPTION: 100 pieces per stack

Input:

```
edit 1 p/2.00 d/100 pieces per stack
```

Output: 

```
Product details have been updated.
```

### Example 2
Context
- `Highlighter`’s Product ID (PID) is 23.
- You wish to change `Highlighter`’s information as follows.
  - PRODUCT_NAME: Neon highlighter
  - QUANTITY: 200
  - PRICE: $1.50
  - DESCRIPTION: Erasable

Input:

```
edit 23 n/Neon highlighter q/200 p/1.50 d/Erasable
```

Output:

```
Product details have been updated.
```


<!--- @@author cheeseong2001 --->
## Deleting a product and its details: `delete`

Deletes an existing product from the inventory.

Format: `delete PID`
- PID must be a valid Product ID of an existing product currently in the inventory.

### Example 1
Input:
```
delete 1
```

Output:
```
Product has been deleted.
```

### Example 2
Suppose the product with PID 3 is not in the inventory. As such you should not be able to delete anything.

Input:
`delete 3`

Output:
`Product with the following PID is not found: 3`


<!--- @@author leongxingyu --->
## Increasing a product quantity: `inflow`

Increase the quantity of an existing product in the inventory at the specific PID.

Format: 
```
inflow PID a/QUANTITY
```

- PID must be a valid Product ID of an existing product.
- QUANTITY must be an integer more than or equals to 0.

:bulb: Use this command instead of [edit](#editing-product-details-edit) if you already have an existing product
and just want to *increase* the quantity when you have new stocks. 

### Example 1
Context: 
- Product `Sticky note`'s Product ID (PID) is 1.
- Current QUANTITY of `Sticky note` is 20.
- Now, you have 10 new stocks for `Sticky note` and you wish to update the quantity.

Input:
```
inflow 1 a/10
```

Output:
```
Quantity updated. Quantity: 30
```

Explanation:
The `inflow` command will update the quantity of the product by performing addition
of the current quantity and the inflow quantity.

## Decreasing a product quantity: `outflow`

Decrease the quantity of an existing product in the inventory at the specific PID.

Format:
```
outflow PID a/QUANTITY
```

- PID must be a valid Product ID of an existing product.
- QUANTITY must be an integer more than or equals to 0.

:bulb: Use this command instead of [edit](#editing-product-details-edit) if you already have an existing product
and just want to *decrease* the quantity when you sell your stocks.

### Example 1
Context: 
- Product `Sticky note`'s Product ID (PID) is 1.
- Current QUANTITY of `Sticky note` is 20.
- Now, you sold 10 `Sticky note` and you wish to update the quantity.

Input:
```
outflow 1 a/10
```

Output: 
```Warning! This product is low in quantity.```
```Quantity updated. Quantity: 10```

:bulb: Note that the warning will only appear once when the quantity first dips
below quantity of 20. All low quantity products will be displayed when you exit 
the program.

### Example 2
Context:
- Product `Highlighter`'s Product ID (PID) is 23.
- Current QUANTITY of `Highlighter` is 50.
- Now, you sold 11 `Highlighter` and you wish to update the quantity.

Input:
```
outflow 23 a/11
```

Output:
```
Quantity updated. Quantity: 39
```

:bulb: Since the new quantity does not dip below 20, no warning is given. 

Explanation:
The `outflow` command will update the quantity of the product by performing subtraction
of the outflow quantity from the current quantity.


## Viewing past inflow / outflow of existing product: `history`

### When to use?
When you want to check on the previous inflow or outflow of a product, use this command!


Format:
```
history PID
```

- PID must be a valid PID of an existing product.


### Example 1
Input:
```
history 1
```

Output:
```
   ==========================================================================
   PID: 1  |  Change in quantity: 200  |  Date of inflow: 29-03-2024 16:47:31
   PID: 1  |  Change in quantity: 100  |  Date of outflow: 05-04-2024 10:00:00
```

**Explanation:** <br>
This command will find any inflow or outflow quantities of your product which has PID 1. <br>
As seen, you tried to: <br>
Increase the quantity of the product by 200 on 29th March 2024, at 16:47:31 hours <br>
Decrease the quantity of the product by 100 on 5th April 2024, at 10:00:00 hours <br>

### Example 2
Input:
```
history 3
```

Output:
```
   ==========================================================================
   PID: 3  |  Change in quantity: 200  |  Date of outflow: 18-03-2024 08:00:31
   PID: 3  |  Change in quantity:  40 |  Date of inflow: 18-03-2024 10:00:00
```

**Explanation:** <br>
This command will find any inflow or outflow quantities of your product which has PID 3. <br>
As seen, you tried to: <br>
Decrease the quantity of the product by 200 on 18th March 2024, at 08:00:31 hours <br>
Increase the quantity of the product by 400 on 18th March 2024, at 10:00:00 hours <br>


<!--- @@author EdmundTangg --->
## Find a keyword in the product list: `find`
### When to use?
This is a useful command for when you want to know if there is already an existing product that matches your keyword. 
Or when you just want to get the product’s PID in a long list of products.


Format: `find KEYWORD`
- Only the product name is searched. 
- The search is case-insensitive. e.g “find apple” will match “Apple”. 
- Partial or full words will be matched e.g. “find Appl” will match “Apples”.

### Example 1
Input:
```
find Cor
```

Output:
```
   ======================================================================
   PID: 1  |  Name: Corn  |  Quantity: 50  |  Price: $1.00
   Description: It's corn!
   ======================================================================
   PID: 5  |  Name: Corn Cup |  Quantity: 100
   ======================================================================
   PID: 10  |  Name: coriander |  Quantity: 1000
   ====================================================================== 
```

**Explanation:** <br>
This command will find any product name that contains Cor in the product’s name,
regardless of case sensitivity.

### Example 2
Input:
```
find THISISASUPERLONGSTRINGANDTHEREISNOMATCH
```

Output:
```
   No matches found.
```

**Explanation:** <br>
This command will find any product name that contains `THISISASUPERLONGSTRINGANDTHEREISNOMATCH` in the product’s name, regardless of case sensitivity.
There is however no match in any of the product’s names in the product list.


<!--- @@author cheeseong2001 --->
## Exiting the program: `exit`
Exits the program.

Format:
```
exit
```

### Example 1

Input:
```
exit
```

Output:
```
Exiting program, goodbye!
```

Explanation:
This command will exit the program. Our program will print a statement to bid you goodbye!


<!--- @@author NgYaoDong --->
## Saving the data

StockPal data is saved in the hard disk automatically after any command that changes the data. There is no need to save manually.


## Editing the data file

StockPal inventory data is saved automatically as a CSV file `[JAR file location]/data/inventory.csv`, while the past transactions data is saved automatically as a JSON file `[JAR file location]/data/transactions.json`. Advanced users are welcome to update data directly by editing these data files, even though it is **highly not recommended**.

<div style="padding: 15px; border: 1px solid transparent; margin-bottom: 20px; border-radius: 4px; color: #a94442; background-color: #f2dede;">
:exclamation:<strong>Caution</strong><br>
If your changes to the data file makes its format invalid, StockPal will not discard the data, but instead display an error that the data has erroneous input, and close the app thereafter. <br>
Furthermore, certain edits can cause StockPal to behave in unexpected ways (e.g., if a value entered is outside the acceptable range). Therefore, edit the data file <strong>only if</strong> you are confident that you can update it correctly, and it is <strong>recommended</strong> to make a backup of the file before editing it.
</div>

# Command Summary
This section provides a quick overview of all the commands. For more detailed information on the command format, click on the `command` to be redirected to the command’s details under the [Features](#features) section.


| **Command**                                                                                         | **Description**                                                                    |
|-----------------------------------------------------------------------------------------------------|------------------------------------------------------------------------------------|
| [`help [COMMAND]`](#viewing-help-help)                                                              | Provides command details for all or specific commands                              |
| [`new n/PRODUCT_NAME q/INITIAL_QUANTITY [p/PRICE] [d/DESCRIPTION]`](#adding-a-new-product-new)      | Creates a new product                                                              |
| [`list [-sn] [-sq]`](#listing-all-products-list)                                                    | Lists all products in the inventory                                                |
| [`edit PID [n/PRODUCT_NAME] [q/QUANTITY] [d/DESCRIPTION] [p/PRICE]`](#editing-product-details-edit) | Edits an existing product’s field                                                  |
| [`delete PID`](#deleting-a-product-and-its-details-delete)                                          | Deletes a product                                                                  |
| [`inflow PID a/QUANTITY`](#increasing-a-product-quantity-inflow)                                    | Increases the quantity of an existing product in the inventory at the specific PID |
| [`outflow PID a/QUANTITY`](#decreasing-a-product-quantity-outflow)                                  | Decreases the quantity of an existing product in the inventory at the specific PID |
| [`history PID`](#viewing-past-inflow--outflow-of-existing-product-history)                          | Finds the list of transactions for a particular product based on its PID           |
| [`find KEYWORD`](#find-a-keyword-in-the-product-list-find)                                          | Finds the list of products that contains the keyword in their name                 |
| [`exit`](#exiting-the-program-exit)                                                                 | Exits the program                                                                  |

# FAQ

**Q**: How do I transfer my data to another computer?

**A**: Install the app on the other computer and overwrite the empty `inventory.csv` and `transactions.json` file it creates with the file that contains the data of your previous StockPal home folder.


