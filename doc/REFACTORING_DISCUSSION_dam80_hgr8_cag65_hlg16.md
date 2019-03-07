# Static Code Review and Refactoring

### Duplication Refectoring

* Condensed different types if ScrollableWindows_ into one ScrollableWindow class.

* EqualCommand and NotEqualCommand had identical execute methods, except the return values were opposite. NotEqualCommand now uses EqualCommand's execute method and flips the result.

* AndCommand and OrCommand had nearly identical execute methods. These have been consolidated into a single method in AndCommand that OrCommand now inherits.


### Long Methods

* Seperated addFeatures() into one method per feature. This reduces the length of the method and allows the features to be added in any order.

* Our three longest methods in backend are 23, 22, and 22 lines and are the only methods above 20 lines. Two of these exist in the backend for reading in a text file and creating a command from reflection, and one exists in evaluating a grouped command. The 23-line method was resolved by breaking out a new method from the body of this one. CreateCommand was reduced in size by condensing catch statements. The last of these methods is difficult to reduce in size since there's no clear smaller process that can be pulled out.

### Checklist Refactoring

* Reduced the amount of magic values used, espcially in the frontend.
* Remove unused imports
* Tell don't Ask: make more classes do stuff for themselves
* Use more interfaces to hide implementation details, and make receiving objects more general

### General Refactoring

* Removed underscores from names to follow Java naming conventions.

* Created properties files for ObservableLists diplayed in the UI. This includes the list of languages and color pallettes.
