import java.awt.*;
import java.awt.event.*;

public class BresenhamPanel extends CartesianPlane implements MouseListener {
    Point start, end;

    public BresenhamPanel() {
        start = new Point();
        end = new Point();

        addMouseListener(this);

    }

    public static void drawLine(Graphics2D ga, Point start, Point end, Color color, BasicStroke thickness,
            String lineStyle) {
        ga.setStroke(thickness);
        ga.setColor(color);
        int startX = start.x;
        int startY = start.y;
        int endX = end.x;
        int endY = end.y;

        // makes sure start is the left most coordinate
        if (startX > endX) {
            int temp = startX;
            startX = endX;
            endX = temp;

            temp = startY;
            startY = endY;
            endY = temp;
        }

        int dx = endX - startX;
        int dy = endY - startY;
        double m = dy * 1.0 / dx;

        int P = 2 * dy - dx;// decision parameter

        int x = startX;
        int y = startY;

        int drawSize = 0, skipSize = 0, drawn = 0, skipped = 0;

        // setting line style attributes
        if (lineStyle.equalsIgnoreCase("solid")) {
            if (((m > 0) && (m < 1)) || (m > -1) && (m <= 0)) {
                drawSize = Math.abs(end.x - start.x) + 1;
            } else if ((m >= 1) || (m <= -1)) {
                drawSize = Math.abs(end.y - start.y) + 1;
            }
        } else if (lineStyle.equalsIgnoreCase("Dashed")) {
            drawSize = 15;
            skipSize = 10;
        } else if (lineStyle.equalsIgnoreCase("Dotted")) {
            drawSize = 2;
            skipSize = 5;
        }

        // Bresenham algorithm
        // for gradient between 0 and 1
        if ((m > 0) && (m < 1)) {
            for (int X = x; X <= endX; X++) {
                if (drawn < drawSize) {
                    ga.drawLine(X, y, X, y);
                    drawn++;

                } else {

                    skipped++;
                    if (skipped == skipSize) {
                        drawn = 0;
                        skipped = 0;
                    }
                }

                if (P < 0) {
                    P += 2 * dy;
                } else {
                    P += (2 * dy - 2 * dx);
                    y++;
                }

            }
        }
        // for gradient greater than 1
        else if (m >= 1) {
            for (int Y = y; Y <= endY; Y++) {
                if (drawn < drawSize) {
                    ga.drawLine(x, Y, x, Y);
                    drawn++;

                } else {

                    skipped++;
                    if (skipped == skipSize) {
                        drawn = 0;
                        skipped = 0;
                    }
                }

                if (P < 0) {
                    P += 2 * dx;
                } else {
                    P += (2 * dx - 2 * dy);
                    x++;
                }
            }
        }

        // For lines whose gradient are negative
        else if ((m > -1) && (m <= 0)) {
            if (dy > 0) {
                for (int X = x; X <= endX; X++) {
                    if (drawn < drawSize) {
                        ga.drawLine(X, y, X, y);
                        drawn++;

                    } else {

                        skipped++;
                        if (skipped == skipSize) {
                            drawn = 0;
                            skipped = 0;
                        }
                    }

                    if (P < 0) {
                        P += 2 * dy;
                    } else {
                        P += 2 * (dy - dx);
                        y--;
                    }
                }
            } else {
                for (int X = x; X <= endX; X++) {
                    if (drawn < drawSize) {
                        ga.drawLine(X, y, X, y);
                        drawn++;

                    } else {

                        skipped++;
                        if (skipped == skipSize) {
                            drawn = 0;
                            skipped = 0;
                        }
                    }

                    if (P < 0) {
                        P -= 2 * dy;
                    } else {
                        P -= 2 * (dy + dx);
                        y--;
                    }
                }
            }

        }
        // For lines who's gradient is greater than -1
        else if (m <= -1) {
            if (dy > 0) {
                for (int Y = y; Y >= endY; Y--) {
                    if (drawn < drawSize) {
                        ga.drawLine(x, Y, x, Y);
                        drawn++;

                    } else {

                        skipped++;
                        if (skipped == skipSize) {
                            drawn = 0;
                            skipped = 0;
                        }
                    }

                    if (P < 0) {
                        P += 2 * dx;
                    } else {
                        x++;
                        P += 2 * (dx - dy);
                    }
                }

            } else {
                for (int Y = y; Y >= endY; Y--) {
                    if (drawn < drawSize) {
                        ga.drawLine(x, Y, x, Y);
                        drawn++;

                    } else {

                        skipped++;
                        if (skipped == skipSize) {
                            drawn = 0;
                            skipped = 0;
                        }
                    }

                    if (P < 0) {
                        P += 2 * dx;
                    } else {
                        x++;
                        P += 2 * (dx + dy);
                    }
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
        BrensenhamLines.add(new Line(start, end, lineColor, new BasicStroke(lineThickness), lineStyle));
        repaint();

    }

}
