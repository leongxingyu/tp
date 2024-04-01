# User Guide
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
    - [Editing product details: `edit`](#editing-product-details-edit)
    - [Deleting a product and its details: `delete`](#deleting-a-product-and-its-details-delete)
    - [Increasing quantity of existing product: `inflow`](#increasing-quantity-of-existing-product-inflow)
    - [Decreasing quantity of existing product: `outflow`](#decreasing-quantity-of-existing-product-outflow)
    - [Find keywords in all products: `find`](#find-keywords-in-all-products-find)
    - [Listing all products: `list`](#listing-all-products-list)
    - [Exiting the program: `exit`](#exiting-the-program-exit)
    - [Saving the data](#saving-the-data)
    - [Editing the data file](#editing-the-data-file)
- [Command Summary](#command-summary)

# Using This Guide
Below are the symbols used in this guide and the meaning of these symbols.

| **Symbol**           | **Meaning**                                                                                      |
|----------------------|--------------------------------------------------------------------------------------------------|
| :exclamation:        | The exclamation mark symbol indicates actions that you should be _cautious_ about.               |
| :information_source: | The **i** symbol indicates notes that are important for you when using the application.          |
| :bulb:               | The lightbulb symbol indicates tips that hold useful information or advice that you should know. |


# Quick Start

## Downloading StockPal

1.  Ensure you have [Java 11](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html) or above installed in your computer.

2.  Download the latest stockpal.jar from [here](https://github.com/AY2324S2-CS2113T-T09-3/tp/releases).

3.  Copy the file to the folder you want to use as the home folder for
    the application. For example,`C:\Users\setupuser\Documents\StockPal\stockpal.jar`.

## Running StockPal

1.  **For Windows users:**

    Open a command prompt terminal. You can do so by searching for `command
    prompt` in the Windows search bar. <br><br>

    **For MAC users:**

    Open a terminal. You can do so by searching for the `terminal` using the Spotlight icon in the menu bar. Click on the terminal.


2.  Determine the file path to the home folder of stockpal.jar. You can
    do so by navigating to stockpal.jar in File Explorer. For example,
    the image below shows that the file path to the home folder of
    stockpal.jar is `C:\Users\setupuser\Documents\StockPal`


3.  In the command prompt terminal, navigate to the home folder using
    the command `cd <file path to the home folder>`. For example, `cd
    C:\Users\setupuser\Documents\StockPal`.


4.  In the command prompt terminal, run StockPal using the command `java
    -jar stockpal.jar`.

# Glossary

PID (Product ID) - a number that we use to keep track of the products.

CLI (Command Line Interface) - a text-based interface used to interact with our program by entering commands into a terminal.

CSV (Comma-Separated Values) - a file format used to store tabular data. Each line represents a row of data, and each field within a row is separated by a comma

## Features
<div style="padding: 15px; border: 1px solid transparent; margin-bottom: 20px; border-radius: 4px; color: #31708f; background-color: #d9edf7;">
:information_source:<strong>Notes about the command format</strong>

<ul>
    <li>Words in UPPER_CASE are the parameters to be supplied by the user.</li>
    <li>Items in square brackets are optional.</li>
    <li>Parameters must be in the specified order.</li>
    <li>Commands are case-sensitive and must strictly follow case specified.</li>
</ul>
</div>

## Viewing help: `help`

List all available commands supported by Stockpal.

### When to use?

When starting as a new user of StockPal, you might be unfamiliar with the commands. Use this command to find
out what commands there are, and how to use it. 


Format: `help`

## Adding a new product: `new`

Creates a new product to the inventory and assigns a unique Product ID (PID) to it.

### When to use?

When starting as a new user of StockPal, use this command repeatedly to fill up your inventory to start tracking!
Subsequently, when your business introduces new products, use this command to add them in too!


Format: `new n/PRODUCT_NAME q/INITIAL_QUANTITY [p/PRICE] [d/DESCRIPTION]`

- PRODUCT_NAME is limited to 100 characters.
- INITIAL_QUANTITY must be an integer more than or equal to 0.
- PRICE must have an integer more than or equal to 0, and have exactly 2 decimal places.
- DESCRIPTION is limited to 100 characters.

### Example 1
Input:

```new n/Drinking Cup q/20 ```

Output:

```Product has been added.```

Explanation:

This command adds your product to the inventory. The product’s details are as follows:
- name of the product is `Drinking Cup`
- Quantity of chocolate Milk stock is `20` units


### Example 2
Input:

```new n/Chocolate Milk q/100 p/2.00 d/Marigold HL Milk```

Output:

```Product has been added.```

Explanation:

This command adds your product to the inventory. The product’s details are as follows:
- name of the product is `Chocolate Milk`
- Quantity of chocolate Milk stock is `100` units
- Price of each unit is `$2.00`
- Description of the Chocolate Milk product is `Marigold HL Milk`, which is the brand


## Editing product details: `edit`

Edits an existing product in the inventory at the specific PID by the input value(s).

Format: `edit PID [n/PRODUCT_NAME] [q/QUANTITY] [d/DESCRIPTION] [p/PRICE]`

- At least one optional field must be provided.
- PID must be a valid Product ID of an existing product.
- QUANTITY must be an integer more than or equals to 0.
- PRICE must have an integer more than or equals to 0, and have exactly 2 decimal places.
- DESCRIPTION is limited to 100 characters.

### Example 1
Context:
- Product `Sticky note`’s Product ID (PID) is 1.
- You wish to change `Sticky note`'s information as follows.
  - PRICE: $2.00
  - DESCRIPTION: 100 pieces per stack

Input:

```edit 1 p/2.00 d/100 pieces per stack```

Output: 

```Product details have been updated.```

### Example 2
Context
- `Highlighter`’s Product ID (PID) is 23.
- You wish to change `Highlighter`’s information as follows.
  - PRODUCT_NAME: Neon highlighter
  - QUANTITY: 200
  - PRICE: $1.50
  - DESCRIPTION: Erasable

Input:

```edit 23 n/Neon highlighter q/200 p/1.50 d/Erasable```

Output:

```Product details have been updated.```

## Increasing a product quantity: `inflow`

Increase the quantity of an existing product in the inventory at the specific PID.

Format: `inflow PID a/QUANTITY`

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
```inflow 1 a/10```

Output:
```Quantity updated. Quantity: 30```

Explanation:
The `inflow` command will update the quantity of the product by performing addition
of the current quantity and the inflow quantity.

## Decreasing a product quantity: `outflow`

Decrease the quantity of an existing product in the inventory at the specific PID.

Format: `outflow PID a/QUANTITY`

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
```outflow 1 a/10```

Output: 
```Warning! This product is low in quantity.```
```Quantity updated. Quantity: 10```

:bulb: Note that the warning will only appear once when the quantity first dips
below quantity of 20. All low quantity products will be displayed when you exit 
the program

### Example 2
Context:
- Product `Highlighter`'s Product ID (PID) is 23.
- Current QUANTITY of `Highlighter` is 50.
- Now, you sold 11 `Highlighter` and you wish to update the quantity.

Input:
```outflow 23 a/11```

Output:
```Quantity updated. Quantity: 39```

:bulb: Since the new quantity does not dip below 20, no warning is given. 

Explanation:
The `outflow` command will update the quantity of the product by performing subtraction
of the outflow quantity from the current quantity.


## Deleting a product and its details: `delete`

Deletes an existing product from the inventory.

Format: `delete PID`
- PID must be a valid Product ID of an existing product currently in the inventory.

### Example 1
Input:
`delete 1`

Output:
`Product has been deleted.`

### Example 2
Suppose the product with PID 3 is not in the inventory. As such you should not be able to delete anything.

Input:
`delete 3`

Output:
`Product with the following PID is not found: 3`

## Saving the data

StockPal data is saved in the hard disk automatically after any command that changes the data. There is no need to save manually.


## Editing the data file

StockPal inventory data is saved automatically as a CSV file `[JAR file location]/data/inventory.csv`, while the past transactions data is saved automatically as a JSON file `[JAR file location]/data/transactions.json`. Advanced users are welcome to update data directly by editing these data files, even though it is **highly not recommended**.

<div style="padding: 15px; border: 1px solid transparent; margin-bottom: 20px; border-radius: 4px; color: #a94442; background-color: #f2dede;">
:exclamation:<strong>Caution:</strong><br>
If your changes to the data file makes its format invalid, StockPal will not discard the data, but instead display an error that the data has erroneous input, and close the app thereafter. <br>
Furthermore, certain edits can cause StockPal to behave in unexpected ways (e.g., if a value entered is outside the acceptable range). Therefore, edit the data file <strong>only if</strong> you are confident that you can update it correctly, and it is <strong>recommended</strong> to make a backup of the file before editing it.
</div>

# Command Summary
This section provides a quick overview of all the commands. For more detailed information on the command format, click on the `command` to be redirected to the command’s details under the [Features](#features) section.

| **Command**                                                                                         | **Description**                                   |
|-----------------------------------------------------------------------------------------------------|---------------------------------------------------|
| [`help`](#viewing-help-help)                                                                        | List all available commands supported by Stockpal |
| [`new n/PRODUCT_NAME q/INITIAL_QUANTITY [p/PRICE] [d/DESCRIPTION]`](#adding-a-new-product-new)      | Creates a new product                             |
| [`edit PID [n/PRODUCT_NAME] [q/QUANTITY] [d/DESCRIPTION] [p/PRICE]`](#editing-product-details-edit) | Edit an existing product’s field                  |


# FAQ

**Q**: How do I transfer my data to another computer?

**A**: Install the app on the other computer and overwrite the empty `inventory.csv` and `transactions.json` file it creates with the file that contains the data of your previous StockPal home folder.


