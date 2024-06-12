package toyrobot;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Collections;

public class ReplayCommand extends Command {
    //private final List<Command> commandHistory;
    private boolean reversed;
    private static ReplayCommand instance;
    String arguments = "";

    public ReplayCommand() {
        super("replay");
        this.reversed = false;
    }

    public ReplayCommand(String argument) {
        super("replay", argument);
        this.reversed = argument.equals("reversed");
    }

    public ReplayCommand(String argument, String arguments) {
        super("replay", argument);
        this.reversed = argument.equals("reversed");
        this.arguments = arguments;
    }

    @Override
    public boolean execute(Robot target) {
        ArrayList<String> commands = target.getCommandHistory();
//

        if (getArgument().isEmpty()) {executeCommands(target, commands);}
        else if (getArgument().equals("reversed") && !(this.arguments.contains("-"))){
//            if (reversed) {
//            Collections.reverse(commands);
//            }

            if (!this.arguments.isEmpty()) {
                int n = Integer.parseInt(this.arguments);
                executeCommands(target, (List<String>) commands.subList(commands.size() - n, commands.size()));
            }else{
                executeCommands(target, commands);}

        } else if (this.arguments.contains("-") || getArgument().contains("-")) {
            String [] range;
            if(!this.arguments.isEmpty()) { range = this.arguments.split("-");}
            else {range = getArgument().split("-");}
            int start = Integer.parseInt(range[0]);
            int end = Integer.parseInt(range[1]);
            executeCommands(target, (List<String>) commands.subList(commands.size() - start, commands.size() - end));

        } else {
            int n = Integer.parseInt(getArgument());
            executeCommands(target, (List<String>) commands.subList(commands.size() - n, commands.size()));
        }

        return true;
    }


    private void executeCommands(Robot target, List<String> commands) {
        if (reversed){
            Collections.reverse(commands);
        }
        for (String command : commands) {
            Command command1 = Command.create(command.trim());
            command1.execute(target);
            System.out.println(target);
        }
        target.setStatus("replayed " + commands.size() + " commands.");
    }
}


