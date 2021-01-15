package maze1;

public class MazeVar {

    private int sizeX;
    private int sizeY;
    private int cellSize;
    private int offsetX = 10;
    private int offsetY = 10;
    private int pointX;
    private int pointY;
    private int oldX;
    private int oldY;

    public int getPointX() {
        return pointX;
    }

    public void setPointX(int pointX) {
        this.pointX = pointX;
    }

    public int getPointY() {
        return pointY;
    }

    public void setPointY(int pointY) {
        this.pointY = pointY;
    }

    public int getOldX() {
        return oldX;
    }

    public void setOldX(int oldX) {
        this.oldX = oldX;
    }

    public int getOldY() {
        return oldY;
    }

    public void setOldY(int oldY) {
        this.oldY = oldY;
    }

    public int getOffsetX() {
        return offsetX;
    }

    public void setOffsetX(int offsetX) {
        this.offsetX = offsetX;
    }

    public int getOffsetY() {
        return offsetY;
    }

    public void setOffsetY(int offsetY) {
        this.offsetY = offsetY;
    }

    public int getSizeX() {
        return sizeX;
    }

    public void setSizeX(int sizeX) {
        if (sizeX > 0) {
            this.sizeX = sizeX;
        }
    }

    public int getSizeY() {
        return sizeY;
    }

    public void setSizeY(int sizeY) {
        if (sizeY > 0) {
            this.sizeY = sizeY;
        }
    }

    public int getCellSize() {
        return cellSize;
    }

    public void setCellSize(int cellSize) {
        if (cellSize > 0) {
            this.cellSize = cellSize;
        }
    }

    public MazeVar(int sizeX,int sizeY,int cellSize,int offsetX,int offsetY,int pointX,int pointY,int oldX, int oldY) {
          setSizeX(sizeX);
        setSizeY(sizeY);
         setCellSize(cellSize);
         setOffsetX(offsetX);
        setOffsetY(offsetY);
        setPointX(pointX);
        setPointY(pointY);
         setOldX(oldX);
        setOldY(oldY);
    }
    public MazeVar() {
        this(10, 10, 10, 10, 10, 10, 10, 10, 10);
    }
}
