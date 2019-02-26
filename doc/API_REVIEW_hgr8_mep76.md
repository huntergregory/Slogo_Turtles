# API Review (hgr8)

## Part 1
1. What about your API/design is intended to be flexible?
    * We have a Command object hierarchy that has a common execute method such that other classes do not need to know which type of command the instance is. Our parser_public creates these abstract objects and generates a hierarchy of Command objects that have other objects as parameters. Later, during execution, this generic structure will lend itself to ease of execution.

3. How is your API/design encapsulating your implementation decisions?
    * Our external API primarily consists of a parse() method and a run() method that deal with our basic parsing and command execution functions. Our specific implementation of these functions are not necessary to have known to other classes, so it is all encapsulated within these methods and classes.

5. What exceptions (error cases) might occur in your part and how will you handle them (or not, by throwing)?
    * The parser_public will need to account for errors in command syntax and spelling. We will accomodate these by throwing to the frontend so that it can handle them and display an error box in the GUI to alert the user.

7. Why do you think your API/design is good (also define what your measure of good is)?
    * I think that our team's backend API is both flexible in adding new command types and provides all the functions it needs to the frontend with nitty-gritty implementation details and subclasses hidden.

## Part 2
1. How do you think Design Patterns are currently represented in the design or could be used to help improve the design?
    * We currently plan on using the Factory design pattern, implementing Reflection to make creating specific commands with a certain level of abstraction more generalized.

2. What feature/design problem are you most excited to work on?
    * I'm excited to work on the Command structuring and command parsing aspects of this program.

3. What feature/design problem are you most worried about working on?
    * I'm not excited about working on parsing text files or dealing with parsing control structures since they're difficult to think about for me until I actually start coding them and I don't have much experience with parsing a text file specifically.

4. Come up with at least five use cases for your part (it is absolutely fine if they are useful for both teams).
    * Some use cases I've been keeping in mind thus far have been:
        * User enters invalid command name
        * User enters command with invalid number of parameters
        * User enters correct command
        * User enters command with more commands nested within
        * User enters command sequence
        * User performs a combination of the aforementioned tasks