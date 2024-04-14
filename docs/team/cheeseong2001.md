<!--- @@author cheeseong2001 --->
# Luan Chee Seong's Project Portfolio Page

## Project: StockPal

StockPal is an Inventory Management System, designed for small business owners to use for their daily operations to keep
track of their inventory. Users of StockPal interacts with it via the Command Line Interface, and it is written in Java.

Given below are my contributions to the project.

* **Parser**: It is one of the core component of StockPal.
  * What it does: 
    * Users first type in a single string of command, containing all the essential arguments for that 
    particular command. 
    * The Parser receives the string, and identifies the type of command the user is trying to perform. 
    * It then parses the string accordingly to the command requirements. 
    * It also alerts the user whenever an argument is missing, wrongly supplied or empty.
  * Justification: Without the parser, the string of command supplied is unable to create respective command objects to 
  carry out the required actions.
  * Highlights: This feature is affected by existing commands and commands to be added in the future. It required an 
  in-depth analysis of the command's structure and needs. The implementation too was challenging as it required changes 
  to existing commands and to the Parser's internal logical parsing as well.
  * Improvements done:
    * Improvements: [#180](https://github.com/AY2324S2-CS2113T-T09-3/tp/pull/180), [#183](https://github.com/AY2324S2-CS2113T-T09-3/tp/pull/183)
    * Bug fixing: [#48](https://github.com/AY2324S2-CS2113T-T09-3/tp/pull/48), [#55](https://github.com/AY2324S2-CS2113T-T09-3/tp/pull/55), [#176](https://github.com/AY2324S2-CS2113T-T09-3/tp/pull/176), 
    * Adding test cases: [#56](https://github.com/AY2324S2-CS2113T-T09-3/tp/pull/56), [#86](https://github.com/AY2324S2-CS2113T-T09-3/tp/pull/86)

* **New Feature**: Added a delete command to allow users to permanently delete an existing product in the inventory.
  * What it does: users type in a delete command, including the PID of a product to be deleted. E.g. `delete 2` will 
  delete a product with PID of 2 from the inventory.
  * Justification: This feature is useful when users do not want to keep track of the product anymore. For example:
  users stopped producing product, or product is out of stock and is no longer getting any inflow of it.
  
* **Code contributed**: [Reposense link](https://nus-cs2113-ay2324s2.github.io/tp-dashboard/?search=cheeseong2001&breakdown=true&sort=groupTitle%20dsc&sortWithin=title&since=2024-02-23&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other)

* **Documentation**:
  * User Guide:
    * Added documentation for the `delete` feature. [#86](https://github.com/AY2324S2-CS2113T-T09-3/tp/pull/86)
      * Provided examples on positive and negative examples.
    * Added documentation for the `exit` feature.
    * Added 'When to use' section to each feature
  * Developer Guide:
    * Added implementation details for `Parser` component. [#79](https://github.com/AY2324S2-CS2113T-T09-3/tp/pull/79)
      * Added class diagram and implementation details
    * Added implementation details for `delete` feature. [#78](https://github.com/AY2324S2-CS2113T-T09-3/tp/pull/78)
      * Added sequence diagram, implementation details.

* **Community**:
  * PRs reviewed (with non-trivial review comments): [#89](https://github.com/AY2324S2-CS2113T-T09-3/tp/pull/89), [#45](https://github.com/AY2324S2-CS2113T-T09-3/tp/pull/45)
  * Reported bugs and suggestions for other team's projects during PE Dry Run [Link to repo issues](https://github.com/cheeseong2001/ped/issues)
