package toyrobot.world;

import toyrobot.Direction;
import toyrobot.Position;
import toyrobot.maze.EmptyMaze;

import java.util.List;
import java.util.Random;


public class TextWorld implements IWorld{
    private final Position TOP_LEFT = new Position(-200,200);
    private final Position BOTTOM_RIGHT = new Position(100,-200);

    private Position position;
    Direction currentDirection = Direction.UP;


    public TextWorld(EmptyMaze maze){
        this.position = CENTRE;
    }

    @Override
    public UpdateResponse updatePosition(int nrSteps) {
        int newY = this.position.getY();
        int newX = this.position.getX();

        if (this.currentDirection==Direction.UP) {
            newY += nrSteps;
        } else if (this.currentDirection==Direction.DOWN) {
            newY -=nrSteps;
        } else if (this.currentDirection==Direction.RIGHT) {
            newX +=nrSteps;
        } else if (this.currentDirection==Direction.LEFT) {
            newX -=nrSteps;
        }

        Position newPosition = new Position(newX, newY);

        if (!newPosition.isIn(TOP_LEFT, BOTTOM_RIGHT)){
            return UpdateResponse.FAILED_OUTSIDE_WORLD;
        } else if (!isNewPositionAllowed(newPosition)) {
            return UpdateResponse.FAILED_OBSTRUCTED;
        } else {
            this.position = newPosition;
            return UpdateResponse.SUCCESS;
        }
    }


    @Override
    public void updateDirection(boolean turnRight) {
        if (turnRight) this.currentDirection=Direction.RIGHT;
        else this.currentDirection=Direction.LEFT;
    }


    @Override
    public Position getPosition() {
        return position;
    }


    @Override
    public Direction getCurrentDirection() {
        return this.currentDirection;
    }


    @Override
    public boolean isNewPositionAllowed(Position position) {
        List<Obstacle> obstacles = getObstacles();
        boolean withinConstraints = (
                position.getY()<=TOP_LEFT.getY() && position.getY()>=BOTTOM_RIGHT.getY()
                        && position.getX()>=TOP_LEFT.getX() && position.getX()<=BOTTOM_RIGHT.getX());
        boolean IsOutsideObstacle = position.getX()==obstacles.get(0).getBottomLeftX() || position.getY()==obstacles.get(0).getBottomLeftY();
        return withinConstraints && !IsOutsideObstacle;
    }


    @Override
    public boolean isAtEdge() {
        boolean isAtVerticalEdges = position.getX()==TOP_LEFT.getX() || position.getX()==BOTTOM_RIGHT.getX();
        boolean isAtHorizontalEdges = position.getY()==TOP_LEFT.getY() || position.getY()==BOTTOM_RIGHT.getY();
        return isAtVerticalEdges || isAtHorizontalEdges;
    }


    @Override
    public void reset() {
        this.position = CENTRE;
        this.currentDirection=Direction.UP;
    }

    @Override
    public List<Obstacle> getObstacles() {
        return List.of(new SquareObstacle(new Random().nextInt(-100, 100), new Random().nextInt(-200, 200)));
    }

    @Override
    public void showObstacles() {

    }

}
