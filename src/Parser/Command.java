package Parser;

abstract class Command {

    // Execute constructed command
    abstract double execute();

    // Check if command is valid
    private boolean isValidCommand() {
        return false;
    }
}
