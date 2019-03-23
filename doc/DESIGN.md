# Design

### High-Level Overview
Our software system is composed of four different packages - control, frontend, state_manager, and backend. 
The control module serves to initializes the GUI, present a tab system to create, delete, or move between turtle workspaces, 
and provide a menu through which the user can interact with the current turtle workspace.

These interactions are mediated through the frontend package, whose external API allows one to customize the UI with certain features, 
or populate the command terminal with a file.
The frontend is responsible for updating the display of turtles and their lines, displaying the correct features, 
and responding to user interaction. 

Responding to user interaction and updating display in the frontend are dependent on both the state_manager and backend package.
The state_manager is the bridge between the front and back end as it provides getters and setters for properties needed in both places.
This package contains a few classes that are data containers, but other classes are robust object-oriented classes such as 
InputTranslator or TurtleManager. The frontend is able to create bindings with or add change listeners to these properties, 
allowing it to update the display in real time whenever states are changed.

Finally, the backend performs the behind the scenes work of not only parsing and error-checking input text, but also executing the 
associated commands. It communicates with the state_manager package to get or set property values and to know what language the input text should be.

--- 
### Adding New Features
How to add new features that you can add and use in your customized turtle workspace:

*General Walkthrough*
- Create a concrete extension of the Feature class
- Add a key with your new feature's name and its class' relative file path in features.properties

*Detailed Walkthrough*
- Go to frontend/src/ui_private/features
    - Path 1: 
        - Decide whether you want to make a concrete extension of the Selector, Slider, or ScrollableWindow class (abstract extensions of Feature)
        - Extend one of those classes
    - Path 2:
        - Create another abstract extension of Feature
        - Extend that new class
    - Path 3:
        - Extend Feature itself with a concrete class
- Go to frontend/resources_frontend
- Add a key of your feature's name to features.properties
    - The key will be the display name. Use an underscore for a space
- Add a relative path of the feature's class, starting at frontend/src/ui_private/features and using "." instead of a "/"

How to add new state for the frontend and/or backend to use:
- Go to the state_manager and add a new instance variable of a bindable property in StateManager, TurtleManager, Turtle, etc. (wherever makes sense pedagogically)
- Add getters and setters for the property

# How to add new backend features
How to add a new command:
- Create a new object for the new Command that is a child of Command and exists within the backend.commands package.
- Write the execute method for this command within new object, make sure it passes its parameters with a call to super constructor.
- Standardized name for new command must be compliant with the global command syntax, i.e. only letters, no numbers or characters
and must not take the name of a previously defined command in any language
- Add command class name to state_manager/resources_state_manager/CommandInfo.txt in the format:
    ```<NewCommandStandardizedName>=<path from backend.commands>.<NewCommandClassName>:<NumberOfParams>```
- Add language support by adding new command to each language file in state_manager/resources_state_manager/languages
with the following format:
    ```<NewCommandStandardizedName> = <NameInLanguage>|<AltNameInLanguage>```
- Parser and CommandFactory will now recognize new command as valid and create accordingly based on the definition established
in newly created command class.

--- 
### Design Decisions & Trade-offs

Frontend

- For the customization of features on the GUI, we fixed the locations of the command terminal and turtle display. The rationale
behind this decision was to keep the overall layout of the panels constant and that these two features should always be displayed.
However, the user can customize which selectors or displays are present on the two side panels and whether they are located on the
left or right panel. This makes sense since not all selectors / displays are vital to using the software.

- We originally planned to use Color Selectors to allow the user to select the background color and pen color. When these colors 
were required to carry indices, we opted for a set list of six colors to choose from. This severely limits the variety of colors 
to "draw" with. However, it would have been rather monotonous to construct indices for hundreds of colors for this project. 
Furthermore, any additional colors can be added with relative ease if desired.

Backend

- For the backend, we made the design decision to have every command be executable and return a double with a common method between all implementations.
We devised a system where command hierarchies would be stored in a nested format, where the inputs to one command would
be commands in of themselves. Then, when it comes time to execute a command, it will have to execute the nested
commands within the execution of the overarching command. This system provides lots of flexibility in what can be 
considered a command and how inputs can be used in many differing ways between commands. In an input with multiple 
commands, the parser executes one completed command hierarchy at a time, so, for example, the input ```fd sum 50 40 fd 3``` 
will execute ```fd sum 50 40``` before ```fd 3``` is even parsed. With a system like this, the user can have custom commands
defined and used in the same input string as long as they're not nested in one another.

- Out of the possible extensions for the final implementation, we did not pursue undo/redo or recursive functionality.
In the case of undo/redo, this decision was made because our system for keeping track of multiple workspaces as essentially
collections of other objects with their own nested instance variables would have been incredibly difficult to store as a 
simple state without adding hundreds of lines of copy code into all of our various state objects. In the case of recursion,
the methodology for parsing that we had been using for every other aspect of the project would not have lent itself well
to a recursive-enabled environment at all. To avoid changing too much of our original parser design, we decided to forgo
implementing recursion and focus energy on implementing grouping, the other "Challenging Extension."

--- 
### Assumptions/Simplifications

Frontend

- To run / edit past commands and user commands, our GUI allows the user to select a command from a dropdown menu. The 
selected command then populates the command terminal, giving the user free reign to alter the command. 

- The user must refresh to see updated user states and variables rather than see changes immediately. We assumed that 
these views did not have to appear immediately.

Backend

- For the SLogo environment, we made the assumption that all user defined variables are defined and accessible
in a global scope within a given workspace. There is no concept of a variable only being defined a certain way within a
sub-procedure of a program.

- For the parser, we made no assumptions about the syntax of commands and parameters, so there is comprehensive error 
checking in place to ensure that commands and variables are declared and populated in exactly the way they are described
in the project write-up. One area where we did make some assumptions, however, was in our methodology for grouping.
We divided up the entire list of commands into two smaller groups: commands that group in a multi-input fashion,
and commands that group in a sequential fashion. 
    - For example, a grouping for ```( sum 30 20 2 43 )``` will be treated essentially
as a multi-input command, as the math commands and a couple others are flexible enough that they are not bound to a 
finite number of parameters. However, in an example like ```( bk 2 6 2 9 )```, the input will be treated as if the user
had entered a sequence of individual commands, i.e. ```bk 2 bk 6 bk 2 bk 9``` and will be executed as a list in that regard.
    - In the case of a grouping for a multi-input command where the user inputs a number of parameters that is not a direct
multiple of the argument count of that command, i.e. ```( goto 3 4 5 2 1 )```, the grouping command will populate the remaining parameters
with 0's, so the previous example would translate to a sequence equivalent to ```goto 3 4 goto 5 2 goto 1 0```.