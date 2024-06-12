package toyrobot;

import toyrobot.maze.SimpleMazeRunner;
import toyrobot.world.IWorld;

import java.util.Objects;

public class MazerunCommand extends Command{
    private final String argument = getArgument();

    public MazerunCommand(String argument) {
        super("mazerun", argument);
    }

    @Override
    public boolean execute(Robot target) {
        if (Objects.equals(argument, "top")){
            new SimpleMazeRunner().mazeRun(target, IWorld.Direction.UP);
        };
        return true;
    }
}
