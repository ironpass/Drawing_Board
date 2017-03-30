package main;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;
import objects.*;

public class DrawingBoard extends JPanel {

	private MouseAdapter mouseAdapter;
	private List<GObject> gObjects;
	private GObject target;

	private int gridSize = 10;

	public DrawingBoard() {
		gObjects = new ArrayList<GObject>();
		mouseAdapter = new MAdapter();
		addMouseListener(mouseAdapter);
		addMouseMotionListener(mouseAdapter);
		setPreferredSize(new Dimension(800, 600));
	}

	public void addGObject(GObject gObject) {
		// TODO: Implement this method.
		gObjects.add(gObject);
		repaint();
	}

	public void groupAll() {
		// TODO: Implement this method.
		CompositeGObject GO = new CompositeGObject();
		if (gObjects.get(0) instanceof CompositeGObject) {
			GO = (CompositeGObject)gObjects.get(0);
			gObjects.remove(0);
		}
		for (GObject o : gObjects) {
			GO.add(o);
		}
		gObjects.clear();
		GO.recalculateRegion();
		gObjects.add(GO);
		repaint();	
		
	}

	public void deleteSelected() {
		// TODO: Implement this method.
		gObjects.remove(target);
		repaint();
	}

	public void clear() {
		// TODO: Implement this method.
		gObjects.clear();
		repaint();
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		paintBackground(g);
		paintGrids(g);
		paintObjects(g);
	}

	private void paintBackground(Graphics g) {
		g.setColor(Color.white);
		g.fillRect(0, 0, getWidth(), getHeight());
	}

	private void paintGrids(Graphics g) {
		g.setColor(Color.lightGray);
		int gridCountX = getWidth() / gridSize;
		int gridCountY = getHeight() / gridSize;
		for (int i = 0; i < gridCountX; i++) {
			g.drawLine(gridSize * i, 0, gridSize * i, getHeight());
		}
		for (int i = 0; i < gridCountY; i++) {
			g.drawLine(0, gridSize * i, getWidth(), gridSize * i);
		}
	}

	private void paintObjects(Graphics g) {
		for (GObject go : gObjects) {
			go.paint(g);
		}
	}

	class MAdapter extends MouseAdapter {

		// TODO: You need some variables here

		private void deselectAll() {
			// TODO: Implement this method.
			for (GObject o : gObjects)
				o.deselected();
			target = null;
			repaint();
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO: Implement this method.
			deselectAll();
			for (GObject o : gObjects)
				if (o.pointerHit(e.getX(), e.getY())) {
					target = o;
					o.selected();
					break;
				}
			repaint();

		}

		@Override
		public void mouseDragged(MouseEvent e) {
			// TODO: Implement this method.
			target.move(e.getX(), e.getY());
			repaint();
		}
	}

}