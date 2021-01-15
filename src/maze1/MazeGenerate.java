package maze1;

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

public class MazeGenerate {

    // РџСЂР°РІРёРј СЃРё РѕР±РµРєС‚ РѕС‚ MazeStart Рё РґРІСѓРјРµСЂРµРЅ РјР°СЃРёРІ,
    // РєСЉРґРµС‚Рѕ С‰Рµ СЃСЉС…СЂР°РЅСЏРІР°РјРµ РёРЅС„РѕСЂРјР°С†РёСЏ Р·Р°
    // РєР»РµС‚РєРёС‚Рµ РЅР° Р»Р°Р±РёСЂРёРЅС‚Р°
    MazeVar obj;
    Cell[][] cells;

    // РљРѕРЅСЃС‚СЂСѓРєС‚РѕСЂ Р·Р° РѕР±С‰Рѕ РїРѕР»Р·РІР°РЅРµ
    public MazeGenerate(MazeVar obj) {
        try {
            int x = obj.getSizeX();
            int y = obj.getSizeY();
            this.obj = obj;
            obj.setSizeX(x);
            obj.setSizeY(y);
            cells = new Cell[obj.getSizeX()][obj.getSizeY()];

            // РЎР»РµРґ РєР°С‚Рѕ СЃРјРµ Р·Р°РґР°Р»Рё СЃС‚РѕР№РЅРѕСЃС‚РёС‚Рµ,
            // Р·Р°РїРѕС‡РІР°РјРµ РґР° РіРµРЅРµСЂРёСЂР°РјРµ Р»Р°Р±РёСЂРёРЅС‚Р°
            initializeCells();
            generateMaze();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (NullPointerException e1) {
            e1.printStackTrace();
        } catch (IllegalAccessError e2) {
            e2.printStackTrace();
        } catch (NoSuchMethodError e3) {
            e3.printStackTrace();

        } catch (UnknownError e5) {
            e5.printStackTrace();
        } catch (ArithmeticException e6) {
            e6.printStackTrace();
        }

    }

    // Р�РЅРёС†РёР°Р»РёР·РёСЂР°РјРµ РєР»РµС‚РєРёС‚Рµ
    private void initializeCells() { // РўРѕРІР° Рµ РєРѕР»РєРѕ РіРѕР»СЏРј С‰Рµ Рµ Р»Р°Р±РёСЂРёРЅС‚Р°

        try {
            for (int i = 0; i < obj.getSizeX(); i++) {

                for (int j = 0; j < obj.getSizeY(); j++) {

                    // РЎСЉР·РґР°РІР°РјРµ РЅРѕРІР° РєР»РµС‚РєР° РѕС‚ РґРІСѓРјРµСЂРЅРёСЏ РјР°СЃРёРІ
                    cells[i][j] = new Cell();
                    // Р—Р°РґР°РІР°РјРµ РІ РјР°СЃРёРІР°, С‡Рµ i = x, Рё С‡Рµ j = y;
                    cells[i][j].xC = i;
                    cells[i][j].yC = j;

                }
            }
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (NullPointerException e1) {
            e1.printStackTrace();
        } catch (NoSuchMethodError e3) {
            e3.printStackTrace();
        } catch (UnknownError e5) {
            e5.printStackTrace();

        } catch (IndexOutOfBoundsException e6) {
            e6.printStackTrace();
        }
    }

    public void generateMaze() {

        try {
            Random rand = new Random();

            int x = rand.nextInt(obj.getSizeX());
            int y = rand.nextInt(obj.getSizeY());

            // РџСЂР°РІРёРј СЃРё Stack, РєРѕР№С‚Рѕ РґР° РЅРё РґСЉСЂР¶Рё Р»РёСЃС‚ РѕС‚
            // Р»РѕРєР°С†РёРёС‚Рµ РЅР° РєР»РµС‚РєРёС‚Рµ РЅР° Р»Р°Р±РёСЂРёРЅС‚Р°
            Stack<Cell> cellStack = new Stack<Cell>();

            int totalCells = obj.getSizeX() * obj.getSizeY();
            int visitedCells = 1;

            // CurrentCell РЅРё Рµ РїСЂРѕРёР·РІРѕР»РЅРѕ РёР·Р±СЂР°РЅР° РєР»РµС‚РєР°
            Cell currentCell = cells[x][y];

            // РџСЂР°РІРёРј СЃРё Р»РёСЃС‚ РѕС‚ РєР»Р°СЃ Vertex, РєСЉРґРµС‚Рѕ РёРјР°
            // РїСЂРѕРјРµРЅР»РёРІРё, РєРѕРёС‚Рѕ С‰Рµ РёР·РїРѕР»Р·РІР°РјРµ
            ArrayList<Vertex> neighborCellList = new ArrayList<Vertex>();

            // РџСЂР°РІРёРј СЃРё РѕР±РµРєС‚ РѕС‚ С‚РѕР·Рё РєР»Р°СЃ
            Vertex tempV = new Vertex();

            while (visitedCells < totalCells) {

                neighborCellList.clear();

                // РЎСЉР·РґР°РІР°РјРµ РѕС‚РЅРѕРІРѕ РѕР±РµРєС‚Р°
                tempV = new Vertex();

                // РќРµ РіР»РµРґР° РЅР°Р№-РіРѕСЂРЅРёСЏ СЂРµРґ
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

                // РђРєРѕ РіРѕР»РµРјРёРЅР°С‚Р° РЅР° neighborCellList Рµ РїРѕ-РіРѕР»СЏРјР° РёР»Рё
                // СЂР°РІРЅР° РЅР° 1
                if (neighborCellList.size() >= 1) {

                    int r1 = rand.nextInt(neighborCellList.size());
                    tempV = neighborCellList.get(r1);

                    // РЎС‚РµРЅР°С‚Р° СЃС‚Р°РІР° СЂР°РІРЅР° РЅР° 0, С‚.Рµ. РёРјР° РїСЉС‚, РїСЂРµР·
                    // РєРѕР№С‚Рѕ РјРѕР¶Рµ РґР° СЃРµ РјРёРЅРµ
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

            // РЎС‚РµРЅР°С‚Р° РѕС‚ Р»СЏРІРѕ РѕС‚ РєСЉРґРµС‚Рѕ Р·Р°РїРѕС‡РІР°С€ РґР° Рµ
            // РїСЂР°Р·РЅР°, Рё РґР° РёРјР° РёР·С…РѕРґ
            cells[0][0].walls[3] = 0;
            cells[obj.getSizeX() - 1][obj.getSizeY() - 1].walls[1] = 0;
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (NullPointerException e1) {
            e1.printStackTrace();

        } catch (NoSuchMethodError e3) {
            e3.printStackTrace();

        } catch (UnknownError e5) {
            e5.printStackTrace();

        } catch (IndexOutOfBoundsException e6) {
            e6.printStackTrace();
        }

    }
}
