import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;


public class RegularPolygonPanel extends CartesianPlane implements MouseListener {

    private ArrayList<Point> vertices = new ArrayList<Point>();
    int NumSides;
    int r;
    Point center;

    public RegularPolygonPanel() {
        addMouseListener(this);
    }

    public void mouseClicked(MouseEvent e) {

        BasicStroke stroke = new BasicStroke();

        if (lineStyle.equalsIgnoreCase("solid")) {
            stroke = new BasicStroke(lineThickness);
        } else if (lineStyle.equalsIgnoreCase("dashed")) {
            stroke = new BasicStroke(lineThickness, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10.0f,
                    new float[] { 15,10 }, 0.0f);// draw 15, skip 10

        } else if (lineStyle.equalsIgnoreCase("dotted")) {
            stroke = new BasicStroke(lineThickness, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0,
                    new float[] { 1, 5 }, 0);// draw 1, skip 5
        }

        irregularPolygons.add(new IrregularPolygon(vertices, lineColor, stroke, fillColor));

        vertices.clear();
        repaint();

    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    public void mousePressed(MouseEvent e) {
        center = e.getPoint();
    }

    public void mouseReleased(MouseEvent e) {
        r = (int) Math.sqrt(Math.pow(center.x - e.getX(), 2) + Math.pow(center.y - e.getY(), 2));

        double theta = Math.PI / 2;
        double dtheta = (2 * Math.PI) / NumSides;

        BasicStroke stroke = new BasicStroke();

        if (lineStyle.equalsIgnoreCase("solid")) {
            stroke = new BasicStroke(lineThickness);
        } else if (lineStyle.equalsIgnoreCase("dashed")) {
            stroke = new BasicStroke(lineThickness, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10.0f,
                    new float[] { 10 }, 0.0f);// draw 10, skip 10

        } else if (lineStyle.equalsIgnoreCase("dotted")) {
            stroke = new BasicStroke(lineThickness, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0,
                    new float[] { 1, 5 }, 0);// draw 1, skip 5
        }

        for (int i = 0; i < NumSides; i++) {
            theta += dtheta;
            vertices.add(new Point((int) (center.x + r * Math.cos(theta)), (int) (center.y + r * Math.sin(theta))));

        }

        RegularPolygons.add(new RegularPolygon(vertices, lineColor, stroke, fillColor));

        vertices.clear();
        repaint();
    }

}

