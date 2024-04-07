<!--- @@author EdmundTangg --->
# Edmund Tang's - Project Portfolio Page

## Overview
StockPal is an inventory management application, designed to help small business owners like you to manage your stock-taking effectively.
StockPal is a command line interface (CLI) application, which means that it is run using a text-based interface.

## Summary of Contributions

### Code contributed
Here is the link to my code:
[tP Code Dashboard](https://nus-cs2113-ay2324s2.github.io/tp-dashboard/?search=edmundtangg&breakdown=true&sort=groupTitle%20dsc&sortWithin=title&since=2024-02-23&timeframe=commit&mergegroup=&groupSelect=groupByRepos&checkedFileTypes=docs~functional-code~test-code~other&tabOpen=true&tabType=authorship&tabAuthor=EdmundTangg&tabRepo=AY2324S2-CS2113T-T09-3%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false)

<br/>

### Features implemented
**1. New command Feature**
[#30](https://github.com/AY2324S2-CS2113T-T09-3/tp/pull/30)

What it does:
This feature added the ability to add a product into the inventory.
A unique Product ID (PID) is assigned to it as well.

Justification:
This feature is the core of the application.
A user needs to be able to add a product in into the application before any further commands can run.

<br/>

**2. Find command Feature**
[#52](https://github.com/AY2324S2-CS2113T-T09-3/tp/pull/52)

What it does:
This feature added the ability to find a product's name based on a specific keyword.

Justification:

- This feature allows the owner to see which products' names matches the input keyword
- This feature allows the owner to obtain the products' PID in a long list of products

<br/>

**3. History command Feature**
[#69](https://github.com/AY2324S2-CS2113T-T09-3/tp/pull/69)

What it does:
This feature added the ability to check on any previous inflow or outflow of a product.

Justification:
This feature gives the owner insights on the details of change in quantity of the product (inflow/outflow)
,which is important for the owner to know.

Highlights:
Previously, we only had a main `Product` and `ProductList` class. 
`History` command however tapped on a whole different class: `Transaction`.
It requires an indepth analysis of how to incorporate it in our product because only `inflow`, `outflow` commands are affected. 


<br/>

### Enhancements to existing features:
Wrote tests for existing features, obtaining 100% tes coverage for all 3 features above.
[#83](https://github.com/AY2324S2-CS2113T-T09-3/tp/pull/83)
[#163](https://github.com/AY2324S2-CS2113T-T09-3/tp/pull/163)

<br/>

### Contributions to the UG
Added documentations for the features `add`, `find` and `history`.
[#92](https://github.com/AY2324S2-CS2113T-T09-3/tp/pull/92)

<br/>

### Contributions to the DG
Added documentations details for the features `add`, `find` and `history`.
[#92](https://github.com/AY2324S2-CS2113T-T09-3/tp/pull/92)

<br/>

### Contributions to team-based tasks
- Updating UG and DG docs: [#92](https://github.com/AY2324S2-CS2113T-T09-3/tp/pull/92)
   - Helped with the overall formatting and documentation 
   - Documentation of user stories 
- Assigning of issues to respective group members when necessary 

<br/>

### Contributions beyond the project team 
- Reported an above-average number of bugs in the PE-D (9 bugs to be exact)