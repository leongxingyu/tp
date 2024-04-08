# Ko Jia Ling's Project Portfolio page 

## Project - StockPal
StockPal is a command line interface (CLI) application designed to help small E-commerce business owners to manage their stock-taking effectively. Users would mostly interact with StockPal via text commands.

Given below are my contributions to the project.

- **Code contributed:**
[tP Code Dashboard Link](https://nus-cs2113-ay2324s2.github.io/tp-dashboard/?search=kobot7&sort=groupTitle%20dsc&sortWithin=title&since=2024-02-23&timeframe=commit&mergegroup=&groupSelect=groupByRepos&breakdown=false&tabOpen=true&tabType=authorship&tabAuthor=Kobot7&tabRepo=AY2324S2-CS2113T-T09-3%2Ftp%5Bmaster%5D&authorshipIsMergeGroup=false&authorshipFileTypes=docs~functional-code~test-code~other&authorshipIsBinaryFileTypeChecked=false&authorshipIsIgnoredFilesChecked=false).


- **New feature:** Added the ability to edit product details.
  - What it does: Allows the user to make modifications to the product name, quantity, price and description.
  - Justification: This feature improves the application significantly as a user can make mistakes when adding new products
    and the application should provide a convenient way to rectify them. Product details may also change over time (e.g. price)
    and the application should allow users to update the necessary details.


- **New feature:** Added the ability to display help page for commands.
  - What it does: Allows the user to view the help page for either all or specific commands.
  - Justification: Users may not know what features are available, or may forget the specific fields that a command requires. 
    This feature improves the user's experience by providing them a convenient way to obtain guidance on how to use the
    commands instead of referring to the external user guide.
  - Highlights: This enhancement requires comings up with an efficient way to standardise formatting of the help page for
    every command. Coming up with the logic was challenging as the way our components were structured. i.e. command information
    was stored within each command as static variables and hence unable to use polymorphism to access them.


- **Enhancement to existing features**
  - Reformat `help` feature code to avoid long methods and similar code: [#53](https://github.com/AY2324S2-CS2113T-T09-3/tp/pull/53)


- **Documentation:**
  - User Guide:
    - Added instructions for the [Quick Start section](https://ay2324s2-cs2113t-t09-3.github.io/tp/UserGuide.html#quick-start), detailing how to download and run StockPal.
    - Added documentation for the features [help](https://ay2324s2-cs2113t-t09-3.github.io/tp/UserGuide.html#viewing-help-help)
    and [edit](https://ay2324s2-cs2113t-t09-3.github.io/tp/UserGuide.html#editing-product-details-edit).

  - Developer Guide: [#77](https://github.com/AY2324S2-CS2113T-T09-3/tp/pull/77)
    - Created initial class diagram for [Data component](https://ay2324s2-cs2113t-t09-3.github.io/tp/DeveloperGuide.html#data-component).
    - Created sequence diagram for [Command component](https://ay2324s2-cs2113t-t09-3.github.io/tp/DeveloperGuide.html#data-component).
    - Added implementation details of the [edit](https://ay2324s2-cs2113t-t09-3.github.io/tp/DeveloperGuide.html#edit-product-feature) feature.


- **Contributions to team-based tasks:**
  - Created base code structure ([Relevant PR](https://github.com/AY2324S2-CS2113T-T09-3/tp/pull/25/files)) with assistance from @wjunjie01 to fix Gradle issues.
  - Formatting and bug fixes for User Guide (Relevant PRs: [#93](https://github.com/AY2324S2-CS2113T-T09-3/tp/pull/93), [#95](https://github.com/AY2324S2-CS2113T-T09-3/tp/pull/95))
  - Updating docs that are not specific to a feature: Quick Start (UG), Data & Command Component (DG)


- **Review/mentoring contributions:**
  - Regularly reviewed team's PRs. >20 PRs reviewed (non-exhaustive list):
    [#40](https://github.com/AY2324S2-CS2113T-T09-3/tp/pull/40),
    [#43](https://github.com/AY2324S2-CS2113T-T09-3/tp/pull/43),
    [#69](https://github.com/AY2324S2-CS2113T-T09-3/tp/pull/69),
    [#174](https://github.com/AY2324S2-CS2113T-T09-3/tp/pull/174)


- **Community Contributions**
  - Review other team's PRs: [CS2113-W13-4](https://github.com/nus-cs2113-AY2324S2/tp/pull/19/files/6cb9d12d024fda0a8c5d69744f7185bba1c50bc1), [CS2113-W14-2](https://github.com/nus-cs2113-AY2324S2/tp/pull/18/files/103a5a77732b785f43d8b3f588563e97f3e63b51)
  - Reported an above-average number of bugs in the PE-D: [PE-D issues](https://github.com/Kobot7/ped/issues)