import java.awt.*;
import java.awt.event.*;

public class DDAPanel extends CartesianPlane implements MouseListener {

    Point start, end;

    public DDAPanel() {
        addMouseListener(this);
    }

    public static void drawLine(Graphics2D ga, Point start, Point end, Color color, BasicStroke thickness,
            String lineStyle) {
        ga.setStroke(thickness);
        ga.setColor(color);

        int drawn = 0;
        int skipped = 0;
        int drawSize = 0, skipSize = 0;

        double m = (end.getY() - start.getY()) / (end.getX() - start.getX());// gradient

        // setting line style attributes
        if (lineStyle.equalsIgnoreCase("solid")) {
            if (Math.abs(m) < 1) {
                drawSize = Math.abs(end.x - start.x) + 1;
            } else if (Math.abs(m) > 1) {
                drawSize = Math.abs(end.y - start.y) + 1;
            }
        } else if (lineStyle.equalsIgnoreCase("Dashed")) {
            drawSize = 15;
            skipSize = 10;
        } else if (lineStyle.equalsIgnoreCase("Dotted")) {
            drawSize = 2;
            skipSize = 5;
        }

        if (Math.abs(m) < 1) {
            Point tempPoint = new Point();

            // make sure start X coordinate is always greater than end X coordinate
            // otherwise for loop will not run
            if (start.getX() > end.getX()) {
                tempPoint = start;
                start = end;
                end = tempPoint;
            }

            double y = start.getY();
            for (int x = (int) start.getX(); x <= (int) end.getX(); x++, y += m) {

                // draw drawSize pixels, skip skipSize pixels and repeat
                if (drawn < drawSize) {
                    ga.drawLine(x, (int) y, x, (int) y);// to draw one pixel
                    drawn++;
                    continue;
                }

                skipped++;
                if (skipped == skipSize) {
                    drawn = 0;
                    skipped = 0;
                }

            }
        }

        else if (Math.abs(m) > 1) {
            Point tempPoint = new Point();

            // make sure start Y coordinate is always greater than end Y coordinate
            // otherwise for loop will not run
            if (start.getY() > end.getY()) {
                tempPoint = start;
                start = end;
                end = tempPoint;
            }

            double x = start.getX();
            for (int y = (int) start.getY(); y <= (int) end.getY(); y++, x += (1 / m)) {
                // draw drawSize pixels, skip skipSize pixels and repeat
                if (drawn < drawSize) {
                    ga.drawLine((int) x, y, (int) x, y);// to draw one pixel
                    drawn++;
                    continue;
                }

                skipped++;
                if (skipped == skipSize) {
                    drawn = 0;
                    skipped = 0;
                }

            }
        }
    }

    public void mouseClicked(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    public void mousePressed(MouseEvent e) {
        start = e.getPoint();
    }

    public void mouseReleased(MouseEvent e) {
        end = e.getPoint();
        DDALines.add(new Line(start, end, lineColor, new BasicStroke(lineThickness), lineStyle));
        repaint();
    }
}
