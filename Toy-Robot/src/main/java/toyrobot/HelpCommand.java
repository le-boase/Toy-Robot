package toyrobot;

public class HelpCommand extends Command {

    public HelpCommand() {
        super("help");
    }

    @Override
    public boolean execute(Robot target) {
        target.setStatus("""
                I can understand these commands:
                OFF  - Shut down robot
                HELP - provide information about commands
                FORWARD - move forward by specified number of steps, e.g. 'FORWARD 10'
                BACK - move back by specified number of steps, e.g 'BACK 5'
                RIGHT - turn right, e.g 'RIGHT'
                LEFT - turn left, e.g 'LEFT'
                SPRINT - sprint forward by specified number of steps, e.g 'SPRINT 7'
                REPLAY - replay previous commands, e.g 'replay 5', 'replay reversed', 'replay 5-3', 'replay reversed 5'""");
        return true;
    }
}
