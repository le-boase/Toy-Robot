package toyrobot.world;

import toyrobot.Position;

public class SquareObstacle implements Obstacle{

    private int x;
    private int y;
    int[][] obstacle;


    public SquareObstacle(int x, int y){
        this.x = x;
        this.y = y;
    }


    @Override
    public int getBottomLeftX() {
        return this.x;
    }


    @Override
    public int getBottomLeftY() {
        return this.y;
    }


    @Override
    public int getSize() {
        return 5;
    }


    @Override
    public boolean blocksPath(Position a, Position b) {
        return this.x>a.getX() && this.x<b.getX() || this.y>a.getY() && this.y<b.getY();
    }


    @Override
    public boolean blocksPosition(Position position) {
        return position.getX()==this.x || position.getY()==this.y;
    }

}
