package toyrobot;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RobotTest {

    @Test
    void initialPosition() {
        Robot robot = new Robot("CrashTestDummy");
        assertEquals(Robot.CENTRE, robot.getPosition());
        assertEquals(Direction.NORTH, robot.getCurrentDirection());
    }

    @Test
    void dump() {
        Robot robot = new Robot("CrashTestDummy");
        assertEquals("[0,0] CrashTestDummy> Ready", robot.toString());
    }

    @Test
    void shutdown() {
        Robot robot = new Robot("CrashTestDummy");
        ShutdownCommand command = new ShutdownCommand();
        assertFalse(robot.handleCommand(command));
    }

    @Test
    void forward() {
        Robot robot = new Robot("CrashTestDummy");
        ForwardCommand command = new ForwardCommand("10");
        assertTrue(robot.handleCommand(command));
        Position expectedPosition = new Position(Robot.CENTRE.getX(), Robot.CENTRE.getY() + 10);
        assertEquals(expectedPosition, robot.getPosition());
        assertEquals("Moved forward by 10 steps.", robot.getStatus());
    }

    @Test
    void forwardforward() {
        Robot robot = new Robot("CrashTestDummy");
        assertTrue(robot.handleCommand(new ForwardCommand("10")));
        assertTrue(robot.handleCommand(new ForwardCommand("5")));
        assertEquals("Moved forward by 5 steps.", robot.getStatus());
    }

    @Test
    void tooFarForward() {
        Robot robot = new Robot("CrashTestDummy");
        assertTrue(robot.handleCommand(new ForwardCommand("1000")));
        assertEquals(Robot.CENTRE, robot.getPosition());
        assertEquals("Sorry, I cannot go outside my safe zone.", robot.getStatus());
    }

    @Test
    void help() {
        Robot robot = new Robot("CrashTestDummy");
        Command command = new HelpCommand();
        assertTrue(robot.handleCommand(command));
        assertEquals("""
                I can understand these commands:
                OFF  - Shut down robot
                HELP - provide information about commands
                FORWARD - move forward by specified number of steps, e.g. 'FORWARD 10'
                BACK - move back by specified number of steps, e.g 'BACK 5'
                RIGHT - turn right, e.g 'RIGHT'
                LEFT - turn left, e.g 'LEFT'
                SPRINT - sprint forward by specified number of steps, e.g 'SPRINT 7'
                REPLAY - replay previous commands, e.g 'replay 5', 'replay reversed', 'replay 5-3', 'replay reversed 5'""", robot.getStatus());
    }
}