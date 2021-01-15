package maze1;

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

public class MazeGenerate {
    
    //Правим си обект от MazeStart и двумерен масив, където ще съхраняваме информация за клетките на лабиринта
    MazeVar obj = new MazeVar();
    Cell[][] cells;

    //Конструктор за подразбиране
    public MazeGenerate() {
        obj.setSizeX(25);
        obj.setSizeY(25);
        cells = new Cell[obj.getSizeX()][obj.getSizeY()];
        
        initializeCells();
        generateMaze();
    }

    //Конструктор за общо ползване
    public MazeGenerate(int x, int y) {
        
        obj.setSizeX(x);
        obj.setSizeY(y);
        cells = new Cell[obj.getSizeX()][obj.getSizeY()];
        
        //След като сме задали стойностите, започваме да генерираме лабиринта
        initializeCells();
        generateMaze();
    }

    //Инициализираме клетките
    private void initializeCells() { //Това е колко голям ще е лабиринта
       
        for (int i = 0; i < obj.getSizeX(); i++) {
            
            for (int j = 0; j < obj.getSizeY(); j++) {
                
                //Създаваме нова клетка от двумерния масив
                cells[i][j] = new Cell();
                //Задаваме в масива, че i = x, и че j = y;
                cells[i][j].xC = i;
                cells[i][j].yC = j;
                
            }
        }
    }

    public void generateMaze() {

        Random rand = new Random();

        int x = rand.nextInt(obj.getSizeX());
        int y = rand.nextInt(obj.getSizeY());
        
        //Правим си Stack, който да ни държи лист от локациите на клетките на лабиринта
        Stack<Cell> cellStack = new Stack<Cell>();
        
        int totalCells = obj.getSizeX() * obj.getSizeY();
        int visitedCells = 1;
        
        //CurrentCell ни е произволно избрана клетка
        Cell currentCell = cells[x][y];
        
        //Правим си лист от клас Vertex, където има променливи, които ще използваме
        ArrayList<Vertex> neighborCellList = new ArrayList<Vertex>();
        
        //Правим си обект от този клас
        Vertex tempV = new Vertex();

        while (visitedCells < totalCells) {
            
            neighborCellList.clear();

            //Създаваме отново обекта
            tempV = new Vertex();
            
            //Не гледа най-горния ред
            if (y >= 1 && cells[x][y - 1].checkWalls() == true) {
                tempV.x1 = x;
                tempV.y1 = y;
                tempV.x2 = x;
                tempV.y2 = y - 1;
                tempV.wall1 = 0;
                tempV.wall2 = 2;
                neighborCellList.add(tempV);
            }
            
            tempV = new Vertex();
            
            if (y + 1 < obj.getSizeY() && cells[x][y + 1].checkWalls() == true) {
                tempV.x1 = x;
                tempV.y1 = y;
                tempV.x2 = x;
                tempV.y2 = y + 1;
                tempV.wall1 = 2;
                tempV.wall2 = 0;
                neighborCellList.add(tempV);
            }
            
            tempV = new Vertex();
            
            if (x - 1 >= 0 && cells[x - 1][y].checkWalls() == true) {
                tempV.x1 = x;
                tempV.y1 = y;
                tempV.x2 = x - 1;
                tempV.y2 = y;
                tempV.wall1 = 3;
                tempV.wall2 = 1;
                neighborCellList.add(tempV);
            }
            
            tempV = new Vertex();
            
            if (x + 1 < obj.getSizeX() && cells[x + 1][y].checkWalls() == true) {
                tempV.x1 = x;
                tempV.y1 = y;
                tempV.x2 = x + 1;
                tempV.y2 = y;
                tempV.wall1 = 1;
                tempV.wall2 = 3;
                neighborCellList.add(tempV);
            }
            
            //Ако големината на neighborCellList е по-голяма или равна на 1
            if (neighborCellList.size() >= 1) {
                
                int r1 = rand.nextInt(neighborCellList.size());
                tempV = neighborCellList.get(r1);
                
                //Стената става равна на 0, т.е. има път, през който може да се мине
                cells[tempV.x1][tempV.y1].walls[tempV.wall1] = 0;
                cells[tempV.x2][tempV.y2].walls[tempV.wall2] = 0;
                
                cellStack.push(currentCell);
                currentCell = cells[tempV.x2][tempV.y2];
                
                x = currentCell.xC;
                y = currentCell.yC;

                visitedCells++;

            } else {
                currentCell = cellStack.pop();
                x = currentCell.xC;
                y = currentCell.yC;
            }
        }
        
        //Стената от ляво от където започваш да е празна, и да има изход
        cells[0][0].walls[3] = 1;
        cells[obj.getSizeX() - 1][obj.getSizeY() - 1].walls[1] = 0;
    }
}
