# API Changes

The external frontend and backend APIs had to change significantly over the course
of the project as our team's understanding of APIs deepened. Originally, 
we envisioned a system of interaction where turtles would be stored in the frontend
and be updated by the backend. There were unforeseen strings attached with this type of design
that led to circular dependencies in our code. The revised system we came up with splits turtle 
data between front and backend, with each housing visualization and state data, respectively.
Bindings exist between front and backend to allow for each modification of turtle properties
between various aspects of the project.

Additionally, we weren't aware that the frontend API should consider allowing multiple features to be added interactively until we read the Piazza post for Slogo FAQ.
The main part of the frontend API (adding left and right features), remained the same after the change in the middle of sprint 1.

Our external APIs are as follow:

-------------------------------------------

#### Module backend
```java
public class CommandParser {
    
    public CommandParser(StateManager stateManager) throws ParserException;
    
    public parseAndRun(String program) throws ParserException;
}
```

### Module state_manager
```java
public class CommandHistory {

    public CommandHistory();
    
    public void addCommand(String program);

    public ListProperty getCommandsProperty();
}

public class GlobalCommands {

    public GlobalCommands();

    public void addCommand(String commandName, ICommand args, ICommand body, IUserCommand newCommand);

    public int getParamCount(String command);

    public boolean isDefined(String command);

    public ICommand getCommand(String commandName, List<ICommand> params);
}

public class GlobalVariables {
        public GlobalVariables();
        
        public double getVariable(String name);
    
        public VariablesGroup getVariablesGroup();
    
        public void setVariable(String name, double value);
}

public interface ICommand {

    double execute();

    int size();

    ICommand getParam(int index);

    List<ICommand> getParams();

    void injectStateManager(StateManager stateManager);
}

public class InputTranslator {
    
    public InputTranslator() throws ParserException;

    public void changeLanguage(String language) throws ParserException;

    public boolean isNormalCommand(String command);

    public boolean hasMultiInputGrouping(String command);

    public List<String> getChunks(String input) throws ParserException;

    public boolean isVariable(String text);

    public boolean isConstant(String text);
}

public interface IUserCommand extends ICommand {

    void assignParams(List<ICommand> params);

    void applyArgsAndBody(ICommand args, ICommand Body);

    int size();

    IUserCommand getNewInstance();
}

public class Palette {

    public Palette(int id, int red, int green, int blue);

    public Palette(int id, Color color);

    public SimpleIntegerProperty getIdProperty();

    public SimpleObjectProperty<Color> getColorProperty();

    public int getId();

    public Color getColor();
}

public class PaletteManager {

    public static final List<Palette> defaultPalettes;

    public PaletteManager();

    public Palette getDefaultBackgroundColor();

    public Palette getDefaultPenColor();

    public Palette getPalette(int index);

    public void setPalette(int index, int red, int green, int blue);
}

public class ParserException extends Exception {
    
    public ParserException(String message);
}

public class NoTurtleException extends RuntimeException {
    
    public NoTurtleException();
}

public class Pen {

    public Pen(Palette color);

    public void eraseLines();

    public void setColor(Palette color);

    public void setThickness(int thickness);

    public void setIsDown(boolean isDown);

    public SimpleObjectProperty<Palette> getPaletteProperty();

    public SimpleIntegerProperty getThicknessProperty();

    public SimpleBooleanProperty getIsDownProperty();

    public BooleanProperty getEraseProperty();

    public boolean getIsDown();

    public Palette getColor();
}

public class StateManager {

    public StateManager() throws ParserException;

    public TurtleManager getTurtleManager();

    public GlobalVariables getVariables();

    public GlobalCommands getCommands();

    public CommandHistory getCommandHistory();

    public Palette getBackgroundColor();

    public InputTranslator getInputTranslator();

    public PaletteManager getPaletteManager();

    public void setBackgroundColor(int index);
}

public class Turtle {
    
        public Turtle(int turtleID, double pwidth, double pheight, Palette penColor);
    
        public void setPosition(double x, double y);
    
        public void setHeading(double heading);
    
        public void setShowing(boolean bool);
    
        public void eraseLines();
    
        public void setActive(boolean active);
        
        public IntegerProperty getTurtleIDProperty();
    
        public ObjectProperty<Point2D> getPositionProperty();
    
        public DoubleProperty getHeadingProperty();
    
        public BooleanProperty getShowingProperty();
    
        public BooleanProperty getActiveProperty();
    
        public Pen getPen();
    
        public int getID();
    
        public Point2D getPosition();
    
        public double getHeading();
    
        public boolean getShowing();
    
        public boolean getIsActive();
        
        public void setAnimationDuration(double duration);
                
        public DoubleProperty getAnimationDurationProperty();
}

public class TurtleManager {

    public static final String ID_VARNAME = "ID";

    public TurtleManager(GlobalVariables variables, PaletteManager paletteManager);

    public void setPanelWidthHeight(double width, double height);

    public List<Turtle> getTurtles();

    public Turtle getTurtle(int id);

    public ObjectProperty<Turtle> getNewTurtleProperty();

    public List<Turtle> getActiveTurtles();

    public void addTurtle(int id);

    public double runTurtleCommand(Function<Turtle, Double> func);

    public List<Integer> getTurtlesWithCondition(Predicate<Turtle> tester);

    public void setTurtlesActive(List<Integer> ids);

    public void revertActiveTurtles();

    public Palette getBackgroundColor();
}

public class VariablesGroup {

    public VariablesGroup();

    public double getVariable(String variable);

    public void setVariable(String variable, double value);

    public boolean isEmpty();

    public boolean hasVariable(String variable);

    public Map<String, Double> getMap();
}
```

#### Module frontend
```java
public class UIBuilder {
    
    public UIBuilder(CommandParser backend, StateManager stateManager, double width, double height);
    
    public static void addStyle(Scene scene);
    
    public BorderPane getContent();
    
    public void addLeftFeature(String label);
    
    public void addRightFeature(String label);
    
    public void removeFeature(String label);
    
    public String[] getFeatureNames();
    
    public String[] getLeftFeatures();
    
    public String[] getRightFeatures();
    
    public void setText(File file);
}
```
    