package maze1;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Path2D;
import javax.swing.JPanel;

@SuppressWarnings("serial")
//с KeyListener ще имплементираме контролирането на квадратчето, което използваме за да решим лабиринта
public class MazeDraw extends JPanel implements KeyListener {
    
    MazeVar obj = new MazeVar();

    //В този клас, рисуваме лабиринта
    
	MazeGenerate m1;
	boolean erase;

        //Конструктор, в който задаваме стойности на променливи
	public MazeDraw(MazeGenerate m2, int cellSize2) {
		m1 = m2;
		obj.setCellSize(cellSize2);
                
                //pointX и pointY е където се намираш в момента на движение в лабиринта
		obj.setPointX(obj.getOffsetX()+ obj.getCellSize() / 2);
		obj.setPointY(obj.getOffsetY() + obj.getCellSize() / 2);
                
                //oldX и oldY е къде си се намирал преди (използваме го за collision)
                obj.setOldX(obj.getPointX());
                obj.setOldY(obj.getPointY());
                                
		addKeyListener(this);
	}
        
        //Тук рисуваме лабиринта
	private void doDrawing(Graphics g) {
            
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(Color.black);
		Dimension size = getSize();
                
                /*An Insets object is a representation of the borders of a container.
                It specifies the space that a container must leave at each of its edges. 
                The space can be a border, a blank space, or a title.*/
		Insets insets = getInsets();
                
		int w = size.width;
		int h = size.height;
                
                //Задаваме цвят на задния фон
		g2d.setBackground(Color.lightGray);
                
                //Позициониране на самия заден фон (той е правоъгълник)
		g2d.clearRect(0, 0, w, h);
                               
		int x, y;
                
		for (int i = 0; i < m1.obj.getSizeX(); i++) {
			x = i * obj.getCellSize() + obj.getOffsetX();

			for (int j = 0; j < m1.obj.getSizeY(); j++) {
				y = j * obj.getCellSize() + obj.getOffsetY();
                                
                                //Aко в cells има горна стена, да я нарусува
				if (m1.cells[i][j].walls[0] == 1) {
					g2d.drawLine(x, y, x + obj.getCellSize(), y);
				}
                                
                                //Aко в cells има дясна стена, да я нарусува
				if (m1.cells[i][j].walls[1] == 1) {
					g2d.drawLine(x + obj.getCellSize(), y, x + obj.getCellSize(), y + obj.getCellSize());
				}
                                
                                //Aко в cells има долна стена, да я нарусува
				if (m1.cells[i][j].walls[2] == 1) {
					g2d.drawLine(x, y + obj.getCellSize(), x + obj.getCellSize(), y + obj.getCellSize());
				}
                                
                                //Aко в cells има лява стена, да я нарусува
				if (m1.cells[i][j].walls[3] == 1) {
					g2d.drawLine(x, y, x, y + obj.getCellSize());
				}
			}
		}
		x = (obj.getOldX() - obj.getOffsetX() - obj.getCellSize() / 2) / obj.getCellSize();
		y = (obj.getOldY() - obj.getOffsetY() - obj.getCellSize() / 2) / obj.getCellSize();
                
                //The following is the code for the collision
		if (x >= 0 && x < m1.obj.getSizeX() && obj.getOldX() > obj.getPointX() && m1.cells[x][y].walls[3] == 1) {
                    
			obj.setPointX(obj.getOldX());
			System.out.println("You've hit a left wall!");
                        
		} else if (x >= 0 && x < m1.obj.getSizeX() && obj.getOldX() < obj.getPointX() && m1.cells[x][y].walls[1] == 1) {
                    
			obj.setPointX(obj.getOldX());
           		System.out.println("You've hit a right wall!");
                        
		} else if (y >= 0 && y < m1.obj.getSizeY() && obj.getOldY() > obj.getPointY() && m1.cells[x][y].walls[0] == 1) {
                    
			obj.setPointY(obj.getOldY());
			 System.out.println("You've hit an upper wall!");
                         
		} else if (y >= 0 && y < m1.obj.getSizeY() && obj.getOldY() < obj.getPointY() && m1.cells[x][y].walls[2] == 1) {
                    
			obj.setPointY(obj.getOldY());
			 System.out.println("You've hit a lower wall!");
                         
		}
                
                //Поставя текста от дясно на лабиринта
                
		g2d.drawString("Move: Arrow Keys / WASD", m1.obj.getSizeX() * obj.getCellSize() + obj.getOffsetX() + 20, 40);
                
		g2d.drawString("Restart: R", m1.obj.getSizeX() * obj.getCellSize() + obj.getOffsetX() + 20, 60);
                
                g2d.drawString("Good luck!", m1.obj.getSizeX() * obj.getCellSize() + obj.getOffsetX() + 20, 80);
                
                //Ако стигнеш до изхода на лабиринта, печелиш!
		if (y == m1.obj.getSizeY() - 1 && x == m1.obj.getSizeX() - 1) {
			g2d.drawString("You won!", m1.obj.getSizeX() * obj.getCellSize() + obj.getOffsetX() + 20, 100);
			resetGame();
		}

		int sizeSqr = obj.getCellSize() / 3 * 2;
		
		g.setColor(Color.BLUE);
		g.fillRect(obj.getPointX() - (sizeSqr / 2), obj.getPointY() - (sizeSqr / 2), sizeSqr, sizeSqr);
		g.setColor(Color.CYAN);
		g.fillRect(obj.getPointX() - (sizeSqr / 4), obj.getPointY() - (sizeSqr / 3), sizeSqr/2, sizeSqr/2);
	}

	@Override
	public void paintComponent(Graphics g) {
//		super.paintComponent(g);
		doDrawing(g);
	}

	@SuppressWarnings("static-access")
	@Override
        
        //Тук използваме KeyListener, и задаваме какво да се случи ако се натискат бутони
	public void keyPressed(KeyEvent key) {
            
		obj.setOldX(obj.getPointX());
                obj.setOldY(obj.getPointY());
                
                //За надолу
		if (key.getKeyCode() == key.VK_DOWN || key.getKeyCode() == key.VK_S) {
			obj.setPointY(obj.getPointY() + obj.getCellSize());
                        
                        
                //За нагоре        
		} else if (key.getKeyCode() == key.VK_UP || key.getKeyCode() == key.VK_W) {
			obj.setPointY(obj.getPointY() - obj.getCellSize());
                        
                
                //За наляво
		} else if (key.getKeyCode() == key.VK_LEFT || key.getKeyCode() == key.VK_A) {
			obj.setPointX(obj.getPointX() - obj.getCellSize());
                        
                
                // За надясно
		} else if (key.getKeyCode() == key.VK_RIGHT || key.getKeyCode() == key.VK_D) {
			obj.setPointX(obj.getPointX() + obj.getCellSize());
                        
                     
		} else if (key.getKeyCode() == key.VK_R) {
			resetGame();
		}
		repaint();
	}
        
        //Да се рестартира лабиринта и да се генерира наново
	private void resetGame() {
		obj.setPointX(obj.getOffsetX()+ obj.getCellSize() / 2);
		obj.setPointY(obj.getOffsetY() + obj.getCellSize() / 2);
		obj.setOldX(obj.getPointX());
                obj.setOldY(obj.getPointY());
		m1 = new MazeGenerate(m1.obj.getSizeX(), m1.obj.getSizeY());
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}
}
