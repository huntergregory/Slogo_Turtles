package parser;

public abstract class Command {

    // Execute constructed command
    public abstract double execute();

    // Check if command is valid
    private boolean isValidCommand() {
        return false;
    }

}
