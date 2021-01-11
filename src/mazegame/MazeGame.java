package mazegame;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
@SuppressWarnings("serial")
public class MazeGame extends JFrame {
 int sizeX=100;
 int sizeY=100;
 int cellSize=100;
 Maze m1=new Maze(sizeX, sizeY);
 public MazeGame(){
     initUI();
 }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable(){
        @Override
        public void run(){
            MazeGame mz=new MazeGame();
            mz.setVisible(true);
            mz.setResizable(false);
   
        }
        });
    }
    
    public void initUI(){
        setTitle("Maze Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(sizeX*cellSize+200,sizeY*cellSize+75);
        
        MazeDisplay md=new MazeDisplay(m1, cellSize);
        add(md);
        addKeyListener(md);
        setContentPane(md);
        md.setFocusable(true);
        
        setLocationRelativeTo(null);
    }
}