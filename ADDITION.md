Harry Ross (hgr8)

## SLogo Addition

### Estimation: before looking at the old code:
- How long do you think it will take you to complete this new feature?  
I think it will take about an hour and a half to implement the new feature.

- How many files will you need to add or update? Why?  
I would estimate I have to edit around 6 or 7 files to accomodate the STAMP and CLEARSTAMPS commands
since these new commands will require new code in both the front and backend to achieve the desired
functionality.

### Review: after completing the feature:
- How long did it take you to complete this new feature?  
2 hours and 3 minutes

- How many files did you need to add or update? Why?  
7 Files Total:
    - 3 New Classes/Files:
        - StampCommand - New Command class to be called and executed
        - ClearStampsCommand - New Command Class to be called and executed
        - TurtleStamp - New extension of ImageTurtleView that removes bindings that update its display after instantiation
    - 4 Modified Classes/Files:
        - TurtleDisplay - Added methods to create, clear, and bind properties for stamps
        - TurtleView - Reworked listeners to be instance variables so they could be referenced in a remove listeners method
        - TurtleManager - Added methods to call from Command classes for creating/clearing stamps
        - CommandInfo.txt - Added additional fields so the parser would recognize new commands as valid

- Did you get it completely right on the first try?  
No, since mostly all of the code I edited was not my original code, it took some trial
and error to fully understand how some classes that other people wrote work.
    
### Analysis: what do you feel this exercise reveals about your project's design and documentation?
- Was it as good (or bad) as you remembered?  
The parts of my original code that were relevant in this extension were fairly minimal, however
my recollection was that all I would need to do to create new command types is create classes to
represent them and add fields to the CommandInfo file. This proved extremely accurate to what I ended
up doing, and I think the simplicity of that addition shows that I implemented good design for my original
code. As for the code I did not have much experience with before (mostly frontend turtle display classes),
I had some initial issues figuring out what different parts did and how they interacted with each
other to create the frontend visual. While debugging, it seemed like some methods and classes were confusingly named,
which hurt my ability to efficiently troubleshoot.

- What could be improved?
I think there could be better naming for frontend classes and methods to make their functions more clear
to an outsider. There was little useful documentation for these classes, and I feel like that would have
helped me implement more quickly.

- What would it have been like if you were not familiar with the code at all?
For most of the code I wrote, I was definitely in this position or close to it, since most
of my original code pertained to the command hierarchy and parser. As I stated at first, I had a good deal
of initial confusion figuring out what functionality was delegated to which frontend classes, but
in time I was able to figure it out.
