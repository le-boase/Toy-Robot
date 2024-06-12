package toyrobot;

public class SprintCommand extends Command {
    public boolean execute(Robot target) {
        int nrSteps = Integer.parseInt(getArgument());


        for (int i = nrSteps; i > 0; i--) {
            if (!new ForwardCommand(String.valueOf(i)).execute(target)) {
                return false;
            }else {
                Command forward = Command.create("forward " + i);
                //forward.execute(target);
                if (i != 1){
                System.out.println(target);}
            }
        }
        return true;
    }


    public SprintCommand(String argument) {
        super("sprint", argument);
    }

}
