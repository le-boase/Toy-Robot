package toyrobot;

public class ForwardCommand extends Command {
    private final int nrSteps;
    public ForwardCommand(String argument) {
        super("forward", argument);
        this.nrSteps = Integer.parseInt(argument.trim());
    }
    @Override
    public boolean execute(Robot target) {
        //int nrSteps = Integer.parseInt(getArgument());
        boolean moved = target.updatePosition(nrSteps);
        if (moved){
            target.setStatus("Moved forward by "+nrSteps+" steps.");
        } else {
            target.setStatus("Sorry, I cannot go outside my safe zone.");
        }
        return true;
    }
}

