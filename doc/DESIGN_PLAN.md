SLogo Team 5 Plan
---------------------------
### API Specifications
* Frontend
    * Internal
        - Update state of display
        - Add turtleView logo
        - Update state of turtleView
        - Display error
        - Show past commands in new window
        - Grab previous commands
        - Commands applied to the turtleView
        - Send text command to back end for parsing
        - Set language
        - See variables currently available in the environment
        - Access help about available commands
    * External
        - Actions
            - setPosition
            - setHeading
            - setPenDown
            - clearScreen
        - Queries
            - getPenIsDown
            - getPosition
            - getHeading
            - getLanguage
* Backend
    * Internal
        - 
    * External
        - Parse command
        - Get command history
        - Get variable list


### Introduction
- For this project, our team will be designing an interpreter that can parse inputs into commands for a visualization environment to carry out and display. We plan on implementing a backend that primarily consists of the parser_public and a frontend that primarily consists of visualization and holding information for the objects being displayed. The frontend will provide relevant information about items being displayed or modified to the backend when it is parsing commands that require it, and the backend will call methods in the frontend API when it is required by the parsed input.

### Design Overview
- Frontend
     - Terminal for commands
     - Window displaying turtleView (same window as terminal)
     - Send input string to parser_public
     - Display error boxes when prompted
     - Option to display command history in separate window
     - Get command history from backend to display if needed
     - Make new command (that is a combination of established commands)


- Parser/Backend
     - Takes in strings, does internal stuff
     - Throw errors for invalid commands, etc.
     - Keep command history as Strings
     - Keep list of commands (commands should be abstract)
     - Command properties for each language 
     - Extension
         - Add new commands

- Turtle
    - pic
    - x, y pos
    - direction/angle
    - visible
    - pen

- Pen
    - marker shape
    - color
    - on/off (up/down)
    - frequency of marking
    - mark will be dots instead of lines

- Command class
    - action
    - syntax/error checking, reports if valid to parser_public, which reports to GUI
    - ^^list of parameters

![](https://i.imgur.com/AQAOvc1.jpg)

We decided to add more public methods to the front end (external API) to match every basic command "Turtle Command" at this [link](https://www2.cs.duke.edu/courses/spring19/compsci308/assign/03_slogo/commands.php). See java API code below.

### User Interface
- The user interface will contain a text box on the right panel that will allow the user to type in commands. Furthermore, there will be a buttons to select the language of the commands, color of the pen, and background color. Invalid commands will cause the text box to be highlighted in red. Past commands and environment vairables will be displayed above the text box. Finally, the larger left panel will display the turtleView in its habitat. Errors will be reported to the user in JavaFX Alert boxes.

![](https://i.imgur.com/0inBEvm.jpg)

### API Details
- Frontend / Internal
    - The interal API of the frontend will handle all aspects of display and interactions with the user. This incudes getting input from the text box and responding to changes in pen color, background, and language. It needs to know the current state of these items. It also handles the disply of past commands and environment variables which can be stored. Exceptions can be thrown for invalid commands.

```java
interface FrontendInternal {

    commands
    void setLanguage();
    
    // Set the color of the pen
    void setPenColor();
    
    // Set input string based on user command
    void setInput();
    
    // Add input string to command history
    void addCommandToHistory();
    
    // Add variable to environment variable list
    void addVariable();
    
    // Update the state of the turtleView
    void updateDisplay();
    
    // Set the background color
    void setBackground();

    variablesVariables
    void displayVariables();
    
    // Display command history
    void displayPastCommands();
    
}
```

- Frontend / External
    - The external API of the frontend will contain getX() methods to supply the backend with information about the turtleView's current state. There will also be public void methods that are called by the backend after parsing a valid instruction. These will include setPosition, setHeading, setPenDown, and clearScreen, and more.

```java
interface FrontendExternal {

    // Update the position of the turtleView
    void setPosition(double x, double y);
        
    // Update heading of turtleView
    void setHeading(double angle);
    
    // Change status of pen 
    void setPenDown(boolean down);
    
    // Moves turtleView backwards or forwards
    void moveForwards(double amount);
    
    // Rotates counter-clockwise (positive) or clockwise
    void rotate(double amount);
    
    // Rotates turtleView to face a point
    void towards(double x, double y);
    
    // Hide or show the turtleView
    void setVisible(boolean visible);
    
    // true if turtleView is visible
    void getVisible();
    
    // Remove turtleView and lines from screen
    void clearScreen();
    
    // Determine if pen is down
    boolean getPenIsDown();
    
    // Determine position of Turtle
    Point getPosition();
    
    // Determine heading of Turtle
    double getHeading();
    
    commands
    String getLanguage();
    
}
```


- Backend / External
    - The external portion of the backend primarily provides the parse method to be called by the UI. This method will read a command and call different portions of the front end's external api, depending on the command. Also available are getVariables, and getCommandHistory, to supply this information to the frontend to be displayed. This API extends to new commands, because a new command will just be parsed, and the internals of the backend will parse this new command.

```java
interface BackendExternal {

    // Run a command given by the user, return the appropriate value based on the program
    double run(String program);
    
    variablesVariables
    Map<String, Double> getVariables();
    
    commands
    Liscommands> getCommandHistory();
}
```

- Backend / Internal
    - The internal API of the backend consists of a heirarchy of classes used to describe different commands. The hecommandsis heirarchy is the abstract class Command, which has the abstract command execute, which returns a double based on the command. There are two abstract subclasses of this, ActionCommand and EvalCommand. Both of these return a value, however EvalCommands just perform some evaluation or look up a value, while ActionCommands perform some action and return a status. Subclasses of ActionCommands and EvalCommands should implement the execute() function.

```java

// Parser class
public class CommandParser {
    // Loops until overall input is empty
    public Command parse(String program);
    // Loops until individual command hierarchy is satisfied
    private Command makeCommand(String input);
    // Checks to see if String chunk is a number
    private boolean isNumeric(String input);
}

public class TextFileParser {
    private void generateParamNumMap();
    private void generateFormalNameMap();
}

// Class to represent a command to be executed
abstract class Command {
    // Execute constructed command
    abstract double execute();
    // Check if command is valid
    private checkIfValidCommand();
}

// A command that involves some action of the front end
abstract class ActionCommand extends Command {

}

// A command that evaluates some expression or gets some data
abstract class EvalCommand extends Command {

}
```



### API Example Code

- fd 50:
    - User enters "fd 50" into the text prompt and presses "run"
    - Private ActionEvent methods in GUI process button input and retrieve text input from prompt.
    - Call to parse("fd 50")
    - Parser identifies "fd" command as valid, searches for single parameter as defined by the syntax of fd.
    - Finds valid parameter 50, creates ActionCommand object for verified input.
    - Execute fd ActionCommand in Parser.
    - Execute method for fd command will tell GUI to move Turtle by an amount. 
    - GUI will call Turtle.getPosition and Turtle.setPosition to move Turtle by desired amount.

- The user enters "LEFT SUM 60 32"
    - Call to parse("LEFT SUM 60 32")
    - Parser identifies "LEFT" as valid command with one parameter.
    - Parser looks for parameter and finds SUM, a command with 2 parameters.
    - Parser looks for two parameters for SUM, finds 60, 32.
    - Parser creates SUM EvalCommand and executes.
    - Parser uses return value of sum command as parameter in new LEFT ActionCommand.
    - Parser executes LEFT command, sends call to UI API for the turtleView to rotate 92 degrees.
    - Turtle updates its heading and the visualization is refreshed to reflect change.

- The user inputs "cs"
    - Call to parse("cs")
    - parser_public identifies "cs" as a valid ActionCommand, and calls that command's execute method
    - this method calls the front end clearScreen method
    - the frontend calls turtleView's reset method
    - the turtleView recenters itself and tells its pen to deleteLines

- The user types "50 fd"
    - call to parse("50 fd")
    - parser_public notices that this is bad syntax and throws an InvalidCommandError
    - the front end catches this error and displays its message in a nice, red display

- The user types "REPEAT 5 [ fd 50 lt 30 ]"
    - call to parse ("REPEAT 5 [ fd 50 lt 30 ]")
    - parser_public creates list of 10 commands to run,commandslt 30
    - parser_public calls appropriate frontend api methods

- The user types "PENDOWN?"
    - call to parse("PENDOWN?")
    - parser_public identifies PENDOWN? as a valid command
    - backend calls public method getIsPenDown()

```java
ui.forward(50);
ui.left(30);
ui.forward(50);
ui.left(30);
ui.forward(50);
ui.left(30);
ui.forward(50);
ui.left(30);
ui.forward(50);
ui.left(30);
```

- The ui wants to get current variables
```java
Map<String, Double> vars = backend.getVariables();

// UI Displays the variables
```

### Design Considerations

- In the backend, we need to better flesh out how we will implement user-defined commands and nescommandsand parsing.

- We're assuming that users should only be able to update certain features via GUI buttons/dropdown menus, and hence will not be able to do certain actions via the command line. 
    - Examples:
        - setting the language
        - setting the pen color
        - setting the background color

- We discussed having multiple turtles as well as animating a turtleView's movement (as opposed to performing all actions immediately), and we made sure that our front end's external and internal API were open to these changes if they come along down the road. For the former, we could create getters and setters identical to the current ones, except we would append a turtleView index in each method's signature. In the latter, we would give the Turtle object a moveTo and rotateTo method with an additional duration parameter in the method signature.

### Team Responsibilities

- Carter and Hunter will work primarily on the frontend tasks such as the desgin of the GUI and updating the state of the Turtle. Furthermore, they will help design the interpretation of commands hierarccommandsrry and David will work primarily on the backend tasks such as the parser_public and command translation hierarchy. Secondarily, they will help the frontend with JavaFX if they need it.
