package toyrobot;

public class RightCommand extends Command{
    @Override
    public boolean execute(Robot target) {
        target.turnRight();
        target.setStatus("Turned right.");
        return true;
    }

    public RightCommand() {
        super("right");
    }
}
