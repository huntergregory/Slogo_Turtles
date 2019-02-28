SLogo API Plan
-----------------------
### OVERALL PICTURE
- Front end
     - update method to get data from backend each step
     - terminal for commands
     - window displaying turtle (same window as terminal)
     - send input string to parser_public
     - display error boxes when prompted
     - option to display command history in separate window
     - get command history from backend to display if needed
     - make new command (that is a combination of established commands)


- Parser/Backend
     - takes in strings, does internal stuff
     - throw errors for invalid commands, etc.
     - keeps track of old state
     - keep command history as Strings
     - current state of turtle (direction, position, etc.)
     - keep list of commands (commands should be abstract)
     - command properties for each language 
     - extension
         - add new commands

- Turtle
    - pic
    - x, y pos
    - velocity?
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

### Architecture Design Questions:
- When does parsing need to take place and what does it need to start properly?
    - Parsing takes place in the backend after user hits enter, it needs the String from frontend, String can be null or empty
- What is the result of parsing and who receives it?
    - The result of parsing is a valid command or error. The ui receives the it.
- When are errors detected and how are they reported?
    - Errors are detected during the parsing of the string. They are reported to the UI. Other errors can come from when GUI attempts to apply command that is syntactically valid
- What do commands know, when do they know it, and how do they get it?
    - Commands know their syntax, they know the GUI API to call functions once validity is established. They inherit a reference to the API through the Command super class.
- How is the GUI updated after a command has completed execution?
    - Command will call a method in GUI to execute, clear text prompt

### API Design:

FRONT END
* External
    - Commands applied to the turtle
    - Send text command to back end for parsing

* Internal
    - Update state of display
    - Take in input
    - Show past commands

BACK END
* External
    - Get command
    - Get command list
    - Get command history


* Internal
    - 
