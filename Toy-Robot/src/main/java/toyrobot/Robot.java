package toyrobot;

import java.util.Arrays;
import java.util.ArrayList;

public class Robot {
    private final Position TOP_LEFT = new Position(-200,200);
    private final Position BOTTOM_RIGHT = new Position(200,-200);

    public static final Position CENTRE = new Position(0,0);
    private final ArrayList<String> commandHistory;

    private Position position;
    private Direction currentDirection;
    private String status;
    private String name;
    private int currentDirectionIndex;

    public Robot(String name) {
        this.name = name;
        this.status = "Ready";
        this.position = CENTRE;
        this.currentDirection = Direction.NORTH;
        this.commandHistory = new ArrayList<>();
        
    }

    public String getStatus() {
        return this.status;
    }

    public Direction getCurrentDirection() {
        return this.currentDirection;
    }
    public void setCurrentDirection(Direction direction){
        this.currentDirection = direction;
    }


    public void addCommand(Command command) {
        String[] moveList = {"forward", "back", "left", "right", "sprint","replay"};

        if (Arrays.asList(moveList).contains(command.getName())){
            commandHistory.add(command.getName()+ " " + command.getArgument());
        }
    }

    public ArrayList<String> getCommandHistory() {
        return commandHistory;
    }
    
    public boolean handleCommand(Command command) {
        boolean executed = command.execute(this);
        if (executed) {
                addCommand(command);

        }
        return executed;
    }


    public boolean updatePosition(int nrSteps) {
        int newX = this.position.getX();
        int newY = this.position.getY();

        if (Direction.NORTH.equals(this.currentDirection)) {
            newY = newY + nrSteps;
        } else if (Direction.SOUTH.equals(this.currentDirection)) {
            newY = newY - nrSteps;
        } else if (Direction.EAST.equals(this.currentDirection)){
            newX = newX + nrSteps;
        }else if (Direction.WEST.equals(this.currentDirection)){
            newX = newX - nrSteps;
        }

        Position newPosition = new Position(newX, newY);
        if (newPosition.isIn(TOP_LEFT,BOTTOM_RIGHT)){
            this.position = newPosition;
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
       return "[" + this.position.getX() + "," + this.position.getY() + "] "
               + this.name + "> " + this.status;
    }
    public void  turnRight(){
        currentDirectionIndex += 1;
        if (currentDirectionIndex > 3){
            currentDirectionIndex = 1;
        }
        setCurrentDirection(Direction.values()[currentDirectionIndex]);
    }

    public void turnLeft(){
        currentDirectionIndex += 1;
        if(currentDirectionIndex > 0){
            currentDirectionIndex = 3;
        }
        setCurrentDirection(Direction.values()[currentDirectionIndex]);
    }


    public Position getPosition() {
        return this.position;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getName() {

        return name;
    }
}