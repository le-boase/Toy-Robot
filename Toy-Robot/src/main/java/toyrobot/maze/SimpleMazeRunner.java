package toyrobot.maze;

import toyrobot.ForwardCommand;
import toyrobot.Position;
import toyrobot.Robot;
import toyrobot.world.IWorld;


public class SimpleMazeRunner implements MazeRunner{


    public static final Position CENTRE = new Position(0,0);

    private Position position;
    private int cost = 0;




    @Override
    public boolean mazeRun(Robot target, IWorld.Direction edgeDirection) {
        int maximumMove = 100;
        int endGoal;
        if (edgeDirection==IWorld.Direction.UP) {
            endGoal =200;
            do {
                ForwardCommand forwardCommand = new ForwardCommand(String.valueOf(maximumMove));
                forwardCommand.execute(target);
                System.out.println(target.getStatus());
                this.cost +=1;
            }
            while (target.getPosition().getY()!= endGoal);
            target.setStatus(target.getPosition() + " "+ target.getName() +"> I am at the top edge. (Cost: "+ getMazeRunCost()+ " steps)");

        }
        else if(edgeDirection==IWorld.Direction.DOWN) endGoal =-200;
        else if (edgeDirection==IWorld.Direction.RIGHT) endGoal =100;
        else if (edgeDirection==IWorld.Direction.LEFT) endGoal =-200;
        return true;
    }


    @Override
    public int getMazeRunCost() {
        return this.cost;
    }

}
