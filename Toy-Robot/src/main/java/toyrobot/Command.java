package toyrobot;

public abstract class Command {
    private final String name;
    private String argument;

    public abstract boolean execute(Robot target);

    public Command(String name){
        this.name = name.trim().toLowerCase();
        this.argument = "";
    }

    public Command(String name, String argument) {
        this(name);
        this.argument = argument.trim();
    }

    public String getName() {                                                                           //<2>
        return name;
    }

    public String getArgument() {
        return this.argument;
    }

    public static Command create(String instruction) {
        String[] args = instruction.toLowerCase().trim().split(" ");
        String command = args[0];
        String argument = (args.length > 1) ? args[1] : "";
        String arguments = (args.length > 2) ? args[2] : "";


        switch (command) {
            case "shutdown":
            case "off":
                return new ShutdownCommand();
            case "help":
                return new HelpCommand();
            case "forward":
                return new ForwardCommand(argument);
            case "back":
                return new BackCommand(argument);
            case "right":
                return new RightCommand();
            case "left":
                return new LeftCommand();
            case "sprint":
                return new SprintCommand(argument);
            case "replay":
                if (args.length <= 2 ){return new ReplayCommand(argument);}
                else if (args.length == 3){return new ReplayCommand(argument, arguments);}
                return new ReplayCommand();
            case "mazerun":
                return new MazerunCommand(argument);
            default:
                throw new IllegalArgumentException("Unsupported command: " + instruction);
        }
    }
}

