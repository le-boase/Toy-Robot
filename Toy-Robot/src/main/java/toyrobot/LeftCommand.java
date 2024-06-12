package toyrobot;

public class LeftCommand extends Command{
    @Override
    public boolean execute(Robot target) {
        target.turnLeft();
        target.setStatus("Turned left.");
        return true;
    }

    public LeftCommand() {
        super("left");
    }
}
