## SLogo - Team 5 - README
#### (Harry Ross, David Miron, Hunter Gregory, Carter Gay) 
#### CS 308, Spring 2019

------------------------------------------------------------------

A development environment that helps users write SLogo programs.

Start Date: 2/14/19  
End Date: 3/9/19  
Hours worked: 150+  

------------------------------------------------------------------

#### Roles: 
Frontend - Carter Gay, Hunter Gregory  
Backend - Harry Ross, David Miron  

#### Resources:
Prof. Duvall & UTAs  
Java Documentation  

------------------------------------------------------------------

#### Starting the project:
```src/control/src/entry/Main.java``` -> This file will launch and initialize the application.

#### Testing files:
Backend: A main method in ```parser.CommandParser``` was used over the course of the project to test
functionality of the parser without relying on the frontend.

Frontend: ```ui.UIMain``` can be used to test frontend functionality.

Error handling: Our backend throws ParserExceptions if errors occur while parsing that
the frontend then catches and handles by displaying in the command prompt. These errors include:
- Incorrect parameter count for non-grouped commands
- Unterminated list or group
- Empty input
- Overwriting a standard SLogo command with a user defined command (i.e. ```to fd```)
- Bad command/variable/constant syntax, illegal characters
- A command that requires a list as a parameter is given something else

Certain errors are accounted for and avoided within the parser itself. These include:
- Uneven parameter count for grouping command -> pads extra parameter slots with 0's 
(i.e. ```( goto 3 4 5 )``` becomes ```( goto 3 4 5 0 )``` when executed)
- Undefined commands and variables -> returns ```0```
- A group with the first command having no parameters ignores the rest of the group without
error, and the rest of the group executes as a list.

#### Data/Resource files:
* Backend - No external resources.

* State Manager - Language files and Command Information, including number of parameters and 
translated Class names for reflection, list of Commands that support multiple inputs for grouping.  
    * Format of language properties files:  
    StandardizedCommandName = entered input (lower-case)  
    ex. ```Forward = forward|fd```  
    (separated by lines for each command)
    * Format of Command Information file:  
    StandardizedCommandName=command.class.name:#params
    ex. ```Sum=math_commands.SumCommand:2```  
    (separated by lines for each command)
    * Format of Multiple Input Enabled properties file:  
    Command class names separated by line

* Frontend - CSS stylesheet and turtle images

#### Using the program:
- Click "PARSE" button to run commands
- Use various on-screen controls to manipulate aspects of the environment
- Click the new tab button

#### Assumptions:
* Backend
    * Variable scope - It was not made clear in the assignment description if variables
    should have scope within subroutines. We chose to make all variables global for simplicity.

    * Parameter types - Commands that require a list as a parameter will not execute
    if given any other type of input. For example, ```dotimes [ :a 5 ] fd 50``` will not
    execute since ```fd 50``` is not in the form of a list.

    * Grouping - Grouping commands are classified in two categories: sequential and
    multi-input based on how they should intuitively act with many parameters. For example, 
    commands like ```( sum 1 2 3 4 5 )``` pass a group of parameters to a SumCommand object
    whereas commands like ```( fd 1 2 3 4 5 )``` execute sequential forward commands. User
    defined commands always are executed sequentially when placed in groups. Commands with no
    parameters ignore everything inside the group after the command itself without giving an error.
    The rest of the group instead gets executed as a list.

#### Missing features:
- Save/load functionality not implemented for turtle state
- UI Undo button does not work
- UI does not incorporate click-to-toggle for active turtles
- UI does not allow for editing user defined variables outside of commands
- UI does not update variables view automatically, requires button

#### Known bugs:
- Background color cannot be set to first palette in list
- Background color resets when certain areas of the workspace are clicked

#### Extra features:
- Grouping was added from the "Challenging Extensions" list
- The option was implemented to load a text file into the workspace.
- Animation was added from the "Challenging Extensions" list

#### Assignment impressions:
Harry - Many of the design specifications for the backend were left frustratingly ambiguous.
(i.e. which types of errors to check for, variable scope, if commands should accept
normal variables as parameters when they were expecting lists, etc.) I'm
unsure whether or not this was intentional, but making these points clearer from the start,
even if that meant telling us that areas of the design were intentionally
ambiguous, would have avoided confusion.