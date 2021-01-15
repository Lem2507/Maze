package maze1;

import java.util.Scanner;
import java.util.Set;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
@SuppressWarnings("serial")

public class MazeMain extends JFrame  {
    
MazeVar obj = new MazeVar();

//Обект, който се обръща към конструктура maze в класа maze и му подава големината на лабиринта
MazeGenerate m1=new MazeGenerate(obj.getSizeX(), obj.getSizeY());

 
 public MazeMain(){
     initUI();
 }
    public static void main(String[] args) {
             
        SwingUtilities.invokeLater(new Runnable(){
        @Override
        public void run(){
            
//            MazeVar obj = new MazeVar();
//        
//        Scanner scan = new Scanner(System.in);
//        
//        int xSize = scan.nextInt();
//        int ySize = scan.nextInt();
//        int Cell = scan.nextInt();
//        
//        obj.setSizeX(xSize);
//        obj.setSizeY(ySize);
//        obj.setCellSize(Cell);
            MazeMain mz = new MazeMain();
            mz.setVisible(true);
            mz.setResizable(false);
   
        }
        });
    }
    
    public void initUI(){
        setTitle("MazeGame");
        MazeVar obj = new MazeVar();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(obj.getSizeX()*obj.getCellSize()+200,obj.getSizeY()*obj.getCellSize()+75);
        
        //Обект, който се обръща към конструктура в MazeDisplay и задава стойности
        MazeDraw md=new MazeDraw(m1, obj.getCellSize());
        addKeyListener(md);
        setContentPane(md);
        md.setFocusable(true);
        
        setLocationRelativeTo(null);
    }
}