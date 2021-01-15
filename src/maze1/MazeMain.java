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
		initUI();
	}

	public static void main(String[] args) {

		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				

				int xSize = Integer.parseInt(JOptionPane.showInputDialog("xSize:"));
				int ySize = Integer.parseInt(JOptionPane.showInputDialog("ySize:"));
				int Cell = Integer.parseInt(JOptionPane.showInputDialog("Cell:"));

				obj.setSizeX(xSize);
				obj.setSizeY(ySize);
				obj.setCellSize(Cell);
				
				MazeMain mz = new MazeMain();

				mz.setVisible(true);
				mz.setResizable(false);

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