# API Review

## Part 1

1. We have a abstract Command class that is subclasses by each specific command. This allows flexibility
by new classes subclassing Command.

2. Our API encapsulates design decisions by allowing the frontend and backend to call each others' public
methods. They don't know how each others' methods are implemented, but it does not matter.

Had a long conversation about how API works:

Frontend should not just implement things in new method.
- Have class for each external frontend api thing
- Have internal frontend api

Front
External
- move
- go home

These just call getX getY and setXY

Internal
- getX
- getY
- setXY        
