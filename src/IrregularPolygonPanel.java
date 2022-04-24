import java.awt.*;
import java.awt.event.*;

public class IrregularPolygonPanel extends CartesianPlane implements MouseListener {

    public IrregularPolygonPanel() {
        addMouseListener(this);
    }

    public void mouseClicked(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {// left click
            if (vertices.size() > 1) {
                BasicStroke stroke = new BasicStroke();

                if (lineStyle.equalsIgnoreCase("solid")) {
                    stroke = new BasicStroke(lineThickness);
                } else if (lineStyle.equalsIgnoreCase("dashed")) {
                    stroke = new BasicStroke(lineThickness, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER, 10.0f,
                            new float[] { 15, 10 }, 0.0f);// draw 10, skip 10

                } else if (lineStyle.equalsIgnoreCase("dotted")) {
                    stroke = new BasicStroke(lineThickness, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0,
                            new float[] { 1, 5 }, 0);// draw 2, skip 5
                }

                irregularPolygons.add(new IrregularPolygon(vertices, lineColor, stroke, fillColor));
            }
            vertices.clear();

        } else if (e.getButton() == MouseEvent.BUTTON3) {// right click to plot points
            vertices.add(e.getPoint());
        }

        repaint();

    }

    public void mouseEntered(MouseEvent e) {}

    public void mouseExited(MouseEvent e) {}

    public void mousePressed(MouseEvent e) {}

    public void mouseReleased(MouseEvent e) {}
}
