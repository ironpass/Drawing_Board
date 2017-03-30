package objects;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

public class CompositeGObject extends GObject {

	private List<GObject> gObjects;

	public CompositeGObject() {
		super(0, 0, 0, 0);
		gObjects = new ArrayList<GObject>();
	}

	public void add(GObject gObject) {
		// TODO: Implement this method.
		gObjects.add(gObject);
	}

	public void remove(GObject gObject) {
		// TODO: Implement this method.
		gObjects.remove(gObject);
	}

	@Override
	public void move(int dX, int dY) {
		// TODO: Implement this method.
		for (GObject o: gObjects) {
			o.x += dX - super.x;
			o.y += dY - super.y;
		}
		super.x = dX;
		super.y = dY;
	}
	
	public void recalculateRegion() {
		// TODO: Implement this method.
		int xMax = -1,yMax = -1,xMin = -1,yMin = -1;
			for (GObject o: gObjects) {
			if (xMax == -1 && yMax == -1 && xMin == -1 && yMin == -1) {
				xMax = o.x+o.width; xMin = o.x;
				yMax = o.y+o.height; yMin = o.y;
			}
			if (xMin > o.x) {
				xMin = o.x;
			}
			if (yMin > o.y) {
				yMin = o.y;
			}
			if (xMax < o.x+o.width ) {
				xMax = o.x+o.width;
			}
			if (yMax < o.y+o.height) {
				yMax = o.y+o.height;
			}
		}
			super.x = xMin;
			super.y = yMin;
			super.width = xMax-xMin;
			super.height = yMax-yMin;	
	}

	@Override
	public void paintObject(Graphics g) {
		// TODO: Implement this method.
		for (GObject o : this.gObjects) {
			o.paintObject(g);
		}	
	}

	@Override
	public void paintLabel(Graphics g) {
		// TODO: Implement this method.
		g.drawString("Grouped", x, y+height+20);
	}
	
}
