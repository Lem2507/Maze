package mazegame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Path2D;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import javax.swing.JPanel;
import javax.swing.Timer;

@SuppressWarnings("serial")
public class MazeDisplay extends JPanel implements KeyListener {

	Maze m1;
	int offsetX = 10;
	int offsetY = 10;
	int cellSize = 20;
	Integer moveCounter = 0;
	int pointX, pointY, oldX, oldY;
	boolean erase;

	public MazeDisplay(Maze m2, int cellSize2) {
		m1 = m2;
		cellSize = cellSize2;
		pointX = offsetX + cellSize / 2;
		pointY = offsetY + cellSize / 2;
		oldX = pointX;
		oldY = pointY;
		addKeyListener(this);
	}

	private void doDrawing(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(Color.blue);
		Dimension size = getSize();
		Insets insets = getInsets();
		int w = size.width - insets.left - insets.right;
		int h = size.height - insets.top - insets.bottom;
		g2d.setBackground(Color.white);
		g2d.clearRect(0, 0, w, h);
		Path2D mazeShape = new Path2D.Double();
		int x, y;
		for (int i = 0; i < m1.sizeX; i++) {
			x = i * cellSize + offsetX;

			for (int j = 0; j < m1.sizeY; j++) {
				y = j * cellSize + offsetY;
				if (m1.cells[i][j].walls[0] == 1) {
					mazeShape.moveTo(x, y);
					mazeShape.lineTo(x + cellSize, y);
					g2d.drawLine(x, y, x + cellSize, y);
				}
				if (m1.cells[i][j].walls[1] == 1) {
					mazeShape.moveTo(x + cellSize, y);
					mazeShape.lineTo(x + cellSize, y + cellSize);
					g2d.drawLine(x + cellSize, y, x + cellSize, y + cellSize);
				}
				if (m1.cells[i][j].walls[2] == 1) {
					mazeShape.moveTo(x, y + cellSize);
					mazeShape.lineTo(x + cellSize, y + cellSize);
					g2d.drawLine(x, y + cellSize, x + cellSize, y + cellSize);
				}
				if (m1.cells[i][j].walls[3] == 1) {
					mazeShape.moveTo(x, y);
					mazeShape.lineTo(x, y + cellSize);
					g2d.drawLine(x, y, x, y + cellSize);
				}
			}
		}
		x = (oldX - offsetX - cellSize / 2) / cellSize;
		y = (oldY - offsetY - cellSize / 2) / cellSize;
		if (x >= 0 && x < m1.sizeX && oldX > pointX && m1.cells[x][y].walls[3] == 1) {
			pointX = oldX;
			pointY = oldY;
			// System.out.println("Ti se blusna v lqva stena");
		} else if (x >= 0 && x < m1.sizeX && oldX < pointX && m1.cells[x][y].walls[1] == 1) {
			pointX = oldX;
			pointY = oldY;
			// System.out.println("Ti se blusna v dqsna stena");
		} else if (y >= 0 && y < m1.sizeY && oldY > pointY && m1.cells[x][y].walls[0] == 1) {
			pointX = oldX;
			pointY = oldY;
			// System.out.println("Ti se blusna v gorna stena");
		} else if (y >= 0 && y < m1.sizeY && oldY < pointY && m1.cells[x][y].walls[2] == 1) {
			pointX = oldX;
			pointY = oldY;
			// System.out.println("Ti se blusna v dolna stena");
		}
		if (pointX != oldX || pointY != oldY) {
			moveCounter++;
		}
		g2d.drawString("Moves: " + moveCounter.toString(), m1.sizeX * cellSize + offsetX + 20, 20);
		g2d.drawString("Move: Arrow Keys / WASD", m1.sizeX * cellSize + offsetX + 20, 40);
		g2d.drawString("Restart: R", m1.sizeX * cellSize + offsetX + 20, 60);
		if (y == m1.sizeY - 1 && x == m1.sizeX - 1) {
			g2d.drawString("You won!", m1.sizeX * cellSize + offsetX + 20, 100);
			resetGame();
		}

		int sizeSqr = cellSize / 3 * 2;
		
		g.setColor(Color.GRAY);
		g.fillRect(oldX - (sizeSqr / 2), oldY - (sizeSqr / 2), sizeSqr, sizeSqr);
		g.setColor(Color.LIGHT_GRAY);
		g.fillRect(oldX - (sizeSqr / 4), oldY - (sizeSqr / 4), sizeSqr/2, sizeSqr/2);
		
		g.setColor(Color.BLUE);
		g.fillRect(pointX - (sizeSqr / 2), pointY - (sizeSqr / 2), sizeSqr, sizeSqr);
		g.setColor(Color.CYAN);
		g.fillRect(pointX - (sizeSqr / 4), pointY - (sizeSqr / 4), sizeSqr/2, sizeSqr/2);
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		doDrawing(g);
	}

	@SuppressWarnings("static-access")
	@Override
	public void keyPressed(KeyEvent key) {
		oldX = pointX;
		oldY = pointY;
		if (key.getKeyCode() == key.VK_DOWN || key.getKeyCode() == key.VK_S) {
			pointY = pointY + cellSize;
			if (pointY > offsetY + (cellSize * m1.sizeY)) {
				pointY = oldY;
			}
		} else
		if (key.getKeyCode() == key.VK_UP || key.getKeyCode() == key.VK_W) {
			pointY = pointY - cellSize;
			if (pointY < offsetY) {
				pointY = oldY;
			}
		} else
		if (key.getKeyCode() == key.VK_LEFT || key.getKeyCode() == key.VK_A) {
			pointX = pointX - cellSize;
			if (pointX < offsetX) {
				pointX = oldX;
			}
		} else
		if (key.getKeyCode() == key.VK_RIGHT || key.getKeyCode() == key.VK_D) {
			pointX = pointX + cellSize;
			if (pointX > offsetX + (cellSize * m1.sizeX)) {
				pointX = oldX;
			}
		} else
		if (key.getKeyCode() == key.VK_R) {
			resetGame();
		}
		repaint();
	}

	private void resetGame() {
		pointX = offsetX + cellSize / 2;
		pointY = offsetY + cellSize / 2;
		oldX = pointX;
		oldY = pointY;
		moveCounter = 0;
		m1 = new Maze(m1.sizeX, m1.sizeY);
		
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
