package maze1;
public class Cell {
	// dqsno, gore, lqvo, dolu

int[] walls={1,1,1,1};
int[] borders ={0,0,0,0};

int xC;
int yC;

public boolean checkWalls(){
    //0 = дясно, 1 = горе, 2 = ляво, 3 = долу
    return walls[0]==1 && walls[1]==1 && walls[2]==1 && walls[3]==1;
}
}
