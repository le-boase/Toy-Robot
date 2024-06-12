package toyrobot.maze;

import toyrobot.Position;
import toyrobot.world.Obstacle;
import toyrobot.world.SquareObstacle;

import java.util.List;

public class


SimpleMaze implements Maze{


    @Override
    public List<Obstacle> getObstacles() {
        return List.of(new SquareObstacle(1, 1));
    }


    @Override
    public boolean blocksPath(Position a, Position b) {
        return false;
    }

}
