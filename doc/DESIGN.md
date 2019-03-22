# Design
#### REMOVE provides the high-level design goals of your project
#### REMOVE explains, in detail, how to add new features to your project
#### REMOVE justifies major design choices, including trade-offs (i.e., pros and cons), made in your project
#### REMOVE states any assumptions or decisions made to simplify or resolve ambiguities in your the project's 


### High-Level Overview
Our software system is composed of four different packages - control, frontend, state_manager, and backend. 
The control package serves to initializes the GUI, present a tab system to create, delete, or move between turtle workspaces, 
and provide a menu through which the user can interact with the current turtle workspace. 

These interactions are mediated through the frontend package, whose external API allows one to customize the UI with certain features, 
or populate the command terminal with a file.
The frontend is responsible for updating the display of turtles and their lines, displaying the correct features, 
and responding to user interaction. 

Responding to user interaction and updating display in the frontend are dependent on both the state_manager and backend package.
The state_manager is the bridge between the front and back end as it provides getters and setters for properties needed in both places.
This package contains many classes that are simple data containers, however some classes are robust object-oriented classes such as 
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

# TODO how to add new backend features
How to add new commands...


--- 
### Design Decisions & Trade-offs

* Frontend

- For the customization of features on the GUI, we fixed the locations of the command terminal and turtle display. The rationale
behind this decision was to keep the overall layout of the panels constant and that these two features should always be displayed.
However, the user can customize which selectors or displays are present on the two side panels and whether they are located on the
left or right panel. This makes sense since not all selectors / displays are vital to using the software.

- We originally planned to use Color Selectors to allow the user to select the background color and pen color. When these colors 
were required to carry indices, we opted for a set list of six colors to choose from. This severely limits the variety of colors 
to "draw" with. However, it would have been rather monotonous to construct indices for hundreds of colors for this project. 
Furthermore, any additional colors can be added with relative ease if desired.

--- 
### Assumptions/Simplifications

* Frontend

- To run / edit past commands and user commands, our GUI allows the user to select a command from a dropdown menu. The 
selected command then populates the command terminal, giving the user free reign to alter the command. 

- The user must refresh to see updated user states and variables rather than see changes immediately. We assumed that 
these views did not have to appear immediately.
