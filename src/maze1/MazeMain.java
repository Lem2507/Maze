package maze1;

import java.util.Scanner;
import java.util.Set;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

@SuppressWarnings("serial")

public class MazeMain extends JFrame {

    static MazeVar obj = new MazeVar();

//Обект, който се обръща към конструктура maze в класа maze и му подава големината на лабиринта
    public MazeMain() {
        try {
            initUI();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (NullPointerException e1) {
            e1.printStackTrace();
        } catch (IllegalAccessError e2) {
            e2.printStackTrace();
        } catch (NoSuchMethodError e3) {
            e3.printStackTrace();
        } catch (VerifyError e4) {
            e4.printStackTrace();
        } catch (UnknownError e5) {
            e5.printStackTrace();
        } catch(StackOverflowError e6){e6.printStackTrace();}
        catch(OutOfMemoryError e7){e7.printStackTrace();}
      
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {

                try {
                    int xSize = Integer.parseInt(JOptionPane.showInputDialog("xSize:"));
                    int ySize = Integer.parseInt(JOptionPane.showInputDialog("ySize:"));
                    int Cell = Integer.parseInt(JOptionPane.showInputDialog("Cell:"));

                    obj.setSizeX(xSize);
                    obj.setSizeY(ySize);
                    obj.setCellSize(Cell);

                    MazeMain mz = new MazeMain();

                    mz.setVisible(true);
                    mz.setResizable(false);

                } catch (IllegalArgumentException e) {
                    e.printStackTrace();
                } catch (UnknownError e5) {
                    e5.printStackTrace();
                } catch (ArithmeticException e6) {
                    e6.printStackTrace();
                }
            }
        });
    }

    public void initUI() {
        setTitle("MazeGame");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(obj.getSizeX() * obj.getCellSize() + 200, obj.getSizeY() * obj.getCellSize() + 75);

        // Обект, който се обръща към конструктура в
        // MazeDisplay и задава стойности
        MazeGenerate m1 = new MazeGenerate(obj);
        MazeDraw md = new MazeDraw(obj, m1, obj.getCellSize());
        addKeyListener(md);
        setContentPane(md);
        md.setFocusable(true);

        setLocationRelativeTo(null);
    }
}
