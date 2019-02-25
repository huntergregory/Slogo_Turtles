#### Name: David Liu

SLogo API Review

Part 1

##### What about your API/design is intended to be flexible?

The API/design is intended to be flexible in that we have a "Window" abstract class and a "Controls" (i.e. Button) abstract class. Each of these is flexible in that a new Window can be extended and implemented with the parameters and variables necessary to disply what is wanted. A new button can be developed with a particular onClick function. Each of these can take whatever parameter is necessary, whether that be a Turtle object, a Pen object, etc. 

##### How is your API/design encapsulating your implementation decisions?

Our API/design was encapsulating our implementation decisions by expressing a Window in the most general form to hide a lot of the implementation. This will also be the case with the Controls (buttons). Finally, the WindowLayoutManager will help to control moving the groups to the appropriate place on the screen.

##### What exceptions (error cases) might occur in your part and how will you handle them (or not, by throwing)?

The error cases that may occur will be handled by the HandleError class from the backend and will be shown to the user. Therefore, I will try and catch invalid parser_private.commands.

##### Why do you think your API/design is good (also define what your measure of good is)?

I think our API/design is good based on how we have an abstract class to handle all the different windows and buttons. I believe that the measure of good is being able to hide as much of the implementation as possible giving the user or other people as little access to information as possible. 

Part 2

##### How do you think Design Patterns are currently represented in the design or could be used to help improve the design?

Design Patterns are currently represented through the abstraction from the Window class and Button class as well as windowLayoutManager. There will probably also be a controller to help contect the front end components. This could be used to help improve the overall design that includes the Window and Button classes.

##### What feature/design problem are you most excited to work on?

I'm most excited to work on the Windows, specifically the Console.

##### What feature/design problem are you most worried about working on?

At the same time, I am also most worried about the Console.

##### Come up with at least five use cases for your part (it is absolutely fine if they are useful for both teams).

1. Minimizing the window
2. Closing a window
3. Clicking the setPenColor Button
4. Clicking the SwitchLanguages Button
5. Use the WindowLayoutManager to move a window

#### Name: Jacob Mullett
SLogo API Review

Part 1

##### What about your API/design is intended to be flexible?
The ability to change anything in the turtle in the backend and simply pass a immutable version of the turtle as a response every time, and an inheritance hiearchy of Result classes allows for different kinds of parser_private.commands and responses to be run. For example, there is a ErrorResult class which can be sent. 
##### How is your API/design encapsulating your implementation decisions?
Our API has a small number of fields required as well as number of methods which need to be fufilled, and so for example as long as the front-end passes a command string, it doesn't matter if this was auto-generated from a `changeColor` event via  a window or if this was an actual command coming from the user themselves. 

##### What exceptions (error cases) might occur in your part and how will you handle them (or not, by throwing)?
All errors that are encountered from parser_private.commands will be thrown back to the user via the ErrorResult response from the backend. This can be a bad syntax command, uninitalized variable or command, divide by 0/arithemetic error, or operation errors such as trying to move the turtle out of bounds. 

##### Why do you think your API/design is good (also define what your measure of good is)?
I think our API is 'good' as it allows for a variety of implementations in both the front end and the backend without the other side having to know about what the implementations details are. 

Part 2

##### How do you think Design Patterns are currently represented in the design or could be used to help improve the design?
We are using the Model-View-Controller design pattern to interact between the front end and backend in a clean way. A Factory could be useful for creating different kinds of Result handlers depending on what kind of result it is in a clean way.

##### What feature/design problem are you most excited to work on?
I am the most excited to work on the command line and displaying previous parser_private.commands and their results.

##### What feature/design problem are you most worried about working on?
I am most worried to work on the TurtleViewer, and displaying movement and lines of the turtle when it could be a non-linear path.

##### Come up with at least five use cases for your part (it is absolutely fine if they are useful for both teams).
1. Changing the color of the line
2. Re-playing an old command, using the up and down arrows you can browse previous parser_private.commands.
3. Displaying errors by checking fo the ErrorResult class and making a pop-up appear.
4. Clicking on the turtle and changing the image via an image browser
5. Making animations for the turtle using an AnimationsGenerator depending on the kind of path generated by the turtle.

#### Name: Carter Gay 

* What about your API/design is intended to be flexible?

My API is flexible because it contains a Turtle object with public methods to handle all 
behavior from the parser_private.commands. A new subclass or public method can be added to handle new parser_private.commands 
that cause different types of behavior. The public methods map the numerous parser_private.commands down to a few types of behavior, eliminating the need for the frontend to be aware which parser_private.commands were called. The frontend API only cares about the changes to the state of the Turtle.

* How is your API/design encapsulating your implementation decisions?

My API is encapsulating our team's implementation decisions by creating public methods for the 
backend to get the required information about the current state of the Turtle (position, pen) to determine the result of a parsed command. Then, the backend will call the public method that expresses the change in the state of the Turtle. These methods will take a Turtle. All changes to the display will be handled internally by the frontend. This allows the frontend to only care about parser_private.commands that will cause the display to change.

* What exceptions (error cases) might occur in your part and how will you handle them (or not, by throwing)?

One exception will be when the user enters the invalid command. First, the frontend will pass the entered string to the backend for parsing. If the string is not a valid command, the backend will call the public method for displaying an error, which will highlight the input text box in red.

* Why do you think your API/design is good (also define what your measure of good is)?

My API is good because it allows for extensions in the behavior of the Turtle. Also, it allows the frontend to only have to know about changes to the state of the Turtle or things that will lead to changes in the display.

* How do you think Design Patterns are currently represented in the design or could be used to help improve the design?

The Model, View, Controller design pattern is currently represented in my design.

* What feature/design problem are you most excited to work on?

I am most excited to work the animtion of the Turtle. I also want to model Terminal for viewing past parser_private.commands.

* What feature/design problem are you most worried about working on?

I am most worried about handling complex parser_private.commands that require conditionals and repeating past parser_private.commands.

* Come up with at least five use cases for your part (it is absolutely fine if they are useful for both teams).

- fd 50:
    - User enters "fd 50" into the text prompt and presses "run"
    - Private ActionEvent methods in GUI process button input and retrieve text input from prompt.
    - Call to parse("fd 50")
    - Parser identifies "fd" command as valid, searches for single parameter as defined by the syntax of fd.
    - Finds valid parameter 50, creates parser_private.ActionCommand object for verified input.
    - Execute fd parser_private.ActionCommand in Parser.
    - Execute method for fd command will tell GUI to move Turtle by an amount. 
    - GUI will call Turtle.getPosition and Turtle.setPosition to move Turtle by desired amount.

- The user enters "LEFT SUM 60 32"
    - Call to parse("LEFT SUM 60 32")
    - Parser identifies "LEFT" as valid command with one parameter.
    - Parser looks for parameter and finds SUM, a command with 2 parameters.
    - Parser looks for two parameters for SUM, finds 60, 32.
    - Parser creates SUM parser_private.EvalCommand and executes.
    - Parser uses return value of sum command as parameter in new LEFT parser_private.ActionCommand.
    - Parser executes LEFT command, sends call to UI API for the turtle to rotate 92 degrees.
    - Turtle updates its heading and the visualization is refreshed to reflect change.

- The user inputs "cs"
    - Call to parse("cs")
    - parser_public identifies "cs" as a valid parser_private.ActionCommand, and calls that command's execute method
    - this method calls the front end clearScreen method
    - the frontend calls turtle's reset method
    - the turtle recenters itself and tells its pen to deleteLines

- The user types "50 fd"
    - call to parse("50 fd")
    - parser_public notices that this is bad syntax and throws an InvalidCommandError
    - the front end catches this error and displays its message in a nice, red display

- The user types "REPEAT 5 [ fd 50 lt 30 ]"
    - call to parse ("REPEAT 5 [ fd 50 lt 30 ]")
    - parser_public creates list of 10 parser_private.commands to run, fd 50, lt 30
    - parser_public calls appropriate frontend api methods

- The user types "PENDOWN?"
    - call to parse("PENDOWN?")
    - parser_public identifies PENDOWN? as a valid command
    - backend calls public method getIsPenDown()