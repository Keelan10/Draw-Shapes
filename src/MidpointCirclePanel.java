import java.awt.*;
import java.awt.event.*;

public class MidpointCirclePanel extends CartesianPlane implements MouseListener {

    int r;
    Point center;

    public MidpointCirclePanel() {

        addMouseListener(this);
    }

    public void mouseClicked(MouseEvent e) {
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
        circles.add(new Circle(center, r, lineColor, new BasicStroke(lineThickness), lineStyle));
        repaint();

    }

    public static void DrawCircle(Graphics2D ga, Point center, int r, Color color, BasicStroke thickness,
            String lineStyle) {
        ga.setStroke(thickness);
        ga.setColor(color);
        int pk = 1 - r;
        int y = r;

        int drawSize = 0, skipSize = 0, drawn = 1, skipped = 1;

        // setting line style attributes
        if (lineStyle.equalsIgnoreCase("solid")) {
            drawSize = y;
        } else if (lineStyle.equalsIgnoreCase("Dashed")) {
            drawSize = 15;
            skipSize = 10;
        } else if (lineStyle.equalsIgnoreCase("Dotted")) {
            drawSize = 2;
            skipSize = 5;
        }

        ga.drawLine(0 + center.x, y + center.y, 0 + center.x, y + center.y);

        for (int x = 1; x < y; x++) {

            if (pk < 0) {
                pk = pk + 2 * x + 1;
            } else {
                y--;
                pk = pk + 2 * x + 1 - 2 * y;
            }

            if (drawn < drawSize) {
                // HAS TO TRANSLATE BACK TO ORIGINAL POSITION OR WE CAN USE GA.TRANSLATE
                ga.drawLine(x + center.x, y + center.y, x + center.x, y + center.y);
                ga.drawLine(x + center.x, -y + center.y, x + center.x, -y + center.y);
                ga.drawLine(-x + center.x, y + center.y, -x + center.x, y + center.y);
                ga.drawLine(-x + center.x, -y + center.y, -x + center.x, -y + center.y);
                ga.drawLine(y + center.x, x + center.y, y + center.x, x + center.y);
                ga.drawLine(-y + center.x, x + center.y, -y + center.x, x + center.y);
                ga.drawLine(-y + center.x, -x + center.y, -y + center.x, -x + center.y);
                ga.drawLine(y + center.x, -x + center.y, y + center.x, -x + center.y);
                drawn++;

            } else {
                skipped++;
                if (skipped == skipSize) {
                    drawn = 0;
                    skipped = 0;
                }
            }

        }

    }
}
