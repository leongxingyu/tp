---
title: Ng Yao Dong's Project Portfolio Page
---

## Project: StockPal

### Overview

StockPal is a desktop inventory management application used for keeping track of your item stocks. The user interacts
with it using a Command Line Interface (CLI). It is written in Java, and has about 6k LoC.

### Summary of Contributions

Given below are my contributions to the project.

* **New Feature**: Added storage ability of the list of products (Pull
  Request [#41](https://github.com/AY2324S2-CS2113T-T09-3/tp/pull/41))
    * What it does: Allows data of the list of products to be automatically saved into a CSV file that the user can use
      in Excel.
    * Justification: This feature improves the product significantly because a user does not have to perform a separate
      command to save and export the data that they entered into the application.
    * Highlights: This enhancement requires an in-depth understanding of converting from normal data into a format
      savable into CSV.
      The implementation too was challenging as it required adjustments to the `Reader` and `Writer` components for the
      conversion of data into save file and vice versa.
    * Credits: [OpenCSV Library](https://opencsv.sourceforge.net/)

* **New Feature**: Added storage ability of the list of transactions (Pull
  Request [#84](https://github.com/AY2324S2-CS2113T-T09-3/tp/pull/84))
    * What it does: Allows data of the list of transactions to be automatically saved into a JSON file in the hard disk.
    * Justification: This feature improves the functionality of the product for the user as the user will be able to
      track their history of past transactions of their products.
    * Highlights: This enhancement requires an in-depth understanding of the JSON file format to be able to convert from
      data into JSON format and back.
    * Credits: [JSON-Java](https://github.com/stleary/JSON-java)

**Code contributed**: [RepoSense link](https://nus-cs2113-ay2324s2.github.io/tp-dashboard/?search=ngyaodong&breakdown=true)

* **Project management**:
    * Setup of GitHub team organisation + repository
    * Maintaining of the issue tracker
    * Managed releases `v1.0` - `v2.0` (2 releases) on GitHub
    * Updated [`README.md`](https://ay2324s2-cs2113t-t09-3.github.io/tp/) for GitHub pages website (Pull Request [#98](https://github.com/AY2324S2-CS2113T-T09-3/tp/pull/98))

* **Enhancements to existing features**:
    * Refactored code to reduce coupling between classes (Pull Request [#50](https://github.com/AY2324S2-CS2113T-T09-3/tp/pull/50))
    * Added code to ensure deletion of transactions when products are deleted (Pull Request [#175](https://github.com/AY2324S2-CS2113T-T09-3/tp/pull/175))

* **Documentation**:
    * User Guide:
        * Added documentation for the features `saving data` and `editing data file`,
          and added message boxes and guide for using them. (Pull Request [#85](https://github.com/AY2324S2-CS2113T-T09-3/tp/pull/85)
        * Did an overall check and improved on the styling of the User Guide. (Pull Request [#98](https://github.com/AY2324S2-CS2113T-T09-3/tp/pull/98))
    * Developer Guide:
        * Added structure for DG, Architecture Diagram and Class Diagram for `Storage` component. (Pull Request [#67](https://github.com/AY2324S2-CS2113T-T09-3/tp/pull/67))
        * Added Table of Contents for the Developer Guide. (Pull Request [#98](https://github.com/AY2324S2-CS2113T-T09-3/tp/pull/98))
        * Added and edited Class diagrams for `Command`, `UI`, `Data` classes, and user stories. (Pull Request [#168](https://github.com/AY2324S2-CS2113T-T09-3/tp/pull/168))

* **Community**:
    * PRs reviewed (with non-trivial review comments): [#40](https://github.com/AY2324S2-CS2113T-T09-3/tp/pull/40), [#69](https://github.com/AY2324S2-CS2113T-T09-3/tp/pull/69), [\#163](https://github.com/AY2324S2-CS2113T-T09-3/tp/pull/163)
    * Reported an above-average number of bugs in PE-D (9 bugs in total) [Link to GitHub Repository](https://github.com/NgYaoDong/ped/issues)

[//]: # (    * Reported bugs and suggestions for other teams in the class &#40;examples: [1]&#40;&#41;, [2]&#40;&#41;, [3]&#40;&#41;&#41;)

* **Tools**:
    * Integrated third party libraries (OpenCSV, JSON-Java) to the project (Pull Requests [#28](https://github.com/AY2324S2-CS2113T-T09-3/tp/pull/28), [#84](https://github.com/AY2324S2-CS2113T-T09-3/tp/pull/84))
