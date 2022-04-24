import java.awt.*;
import java.awt.geom.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

public class CartesianPlane extends JPanel implements MouseWheelListener, MouseMotionListener {

    static ArrayList<Line> DDALines = new ArrayList<Line>();
    static ArrayList<Line> BrensenhamLines = new ArrayList<Line>();
    static ArrayList<Circle> circles = new ArrayList<Circle>();
    static ArrayList<IrregularPolygon> irregularPolygons = new ArrayList<IrregularPolygon>();
    static ArrayList<RegularPolygon> RegularPolygons = new ArrayList<RegularPolygon>();
    ArrayList<Point> vertices = new ArrayList<Point>();

    static Color lineColor = Color.black;
    static int lineThickness = 3;
    static Color fillColor = new Color(0, 0, 0, 0);// opacity 0
    static String lineStyle = "SOLID";

    Point cursorPoint, virtual;
    static JLabel label = new JLabel("The cursor is at");

    public CartesianPlane() {
        setBackground(new Color(211, 211, 211));
        virtual = new Point();
        cursorPoint = new Point();

        addMouseWheelListener(this);
        addMouseMotionListener(this);
    }

    public Point pointTranslate(int x, int y) {
        double virtualX = (x - (getWidth() / 2)) / increment;
        double virtualY = ((getHeight() / 2) - y) / increment;
        virtual = new Point((int) virtualX, (int) virtualY);
        return virtual;
    }

    public void updateCursorPoint(int x, int y) {
        cursorPoint.x = x;
        cursorPoint.y = y;
        updateLabel();
    }

    protected void updateLabel() {
        String text = "The cursor is at (" + cursorPoint.x + "," + cursorPoint.y + ")";
        label.setText(text);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D ga = (Graphics2D) g;

        ga.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        ga.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        AffineTransform save = ga.getTransform();

        int halfWidth = getWidth() / 2;
        int halfHeight = getHeight() / 2;
        ga.translate(halfWidth, halfHeight);
        ga.drawLine(50, 650, 50, -650);

        int scaleDiv = 50;
        int scaleLab = scale;

        int scaleWidth = getWidth() / 2;
        int scaleHeight = getHeight() / 2;
        Point center = new Point(0, 0);

        ga.setColor(Color.WHITE);
        ga.drawLine(center.x, -scaleHeight, center.x, scaleHeight);
        ga.drawLine(-scaleWidth, center.y, scaleWidth, center.y);
        ga.setColor(Color.BLACK);
        ga.drawString("0", center.x, center.y);

        for (int i = 1; i <= halfWidth; i++) {
            ga.setColor(Color.WHITE);
            ga.drawLine(scaleDiv, scaleHeight, scaleDiv, -scaleHeight);
            ga.drawLine(-scaleDiv, scaleHeight, -scaleDiv, -scaleHeight);
            ga.drawLine(-scaleWidth, scaleDiv, scaleWidth, scaleDiv);
            ga.drawLine(-scaleWidth, -scaleDiv, scaleWidth, -scaleDiv);

            ga.setColor(Color.BLACK);
            String scaleLabel = "" + scaleLab + "";
            ga.drawString(scaleLabel, scaleDiv, center.y);
            ga.drawString(scaleLabel, center.x, -scaleDiv);

            scaleLabel = "" + -scaleLab + "";
            ga.drawString(scaleLabel, -scaleDiv, center.y);
            ga.drawString(scaleLabel, center.x, scaleDiv);

            scaleDiv += 50;
            scaleLab += scale;
        }

        ga.setTransform(save);

        for (Point vertex : vertices) {
            ga.drawString("X", vertex.x - 3, vertex.y + 2);
        }

        // draw all shapes
        for (Line line : DDALines) {
            DDAPanel.drawLine(ga, line.start, line.end, line.color, line.stroke, line.lineStyle);
        }

        for (Line line : BrensenhamLines) {
            BresenhamPanel.drawLine(ga, line.start, line.end, line.color, line.stroke, line.lineStyle);
        }

        for (Circle circle : circles) {
            MidpointCirclePanel.DrawCircle(ga, circle.center, circle.radius, circle.color, circle.stroke,
                    circle.lineStyle);
        }

        for (IrregularPolygon polygon : irregularPolygons) {
            polygon.DrawIrregularPolygon(ga);

        }

        for (RegularPolygon polygon : RegularPolygons) {
            polygon.DrawRegularPolygon(ga);

        }

        setLayout(new BorderLayout());
        add(label, BorderLayout.SOUTH);
    }

    private static int scale = 10;
    private static double increment = 5;

    private static int radIncrement = 1;

    public Point scalePoint(Point point, int amount) {

        int x1 = point.x;
        int y1 = point.y;

        int wid2 = getWidth() / 2;
        int hei2 = getHeight() / 2;

        int oldX = x1;
        int oldY = y1;

        int transX = Math.abs(wid2 - oldX) / radIncrement;
        int transY = Math.abs(hei2 - oldY) / radIncrement;

        if (amount == 10) {
            if ((x1 <= wid2) && (y1 <= hei2)) {
                x1 = x1 + transX;
                y1 = y1 + transY;
            }

            else if ((x1 >= wid2) && (y1 <= hei2)) {
                x1 = x1 - transX;
                y1 = y1 + transY;
            }

            else if ((x1 <= wid2) && (y1 >= hei2)) {
                x1 = x1 + transX;
                y1 = y1 - transY;
            }

            else if ((x1 >= wid2) && (y1 >= hei2)) {
                x1 = x1 - transX;
                y1 = y1 - transY;
            }
        }

        else if (amount == -10) {
            if ((x1 <= wid2) && (y1 <= hei2)) {
                x1 = x1 - transX;
                y1 = y1 - transY;
            }

            else if ((x1 >= wid2) && (y1 <= hei2)) {
                x1 = x1 + transX;
                y1 = y1 - transY;
            }

            else if ((x1 <= wid2) && (y1 >= hei2)) {
                x1 = x1 - transX;
                y1 = y1 + transY;
            }

            else if ((x1 >= wid2) && (y1 >= hei2)) {
                x1 = x1 + transX;
                y1 = y1 + transY;
            }

        }

        return new Point(x1, y1);
    }

    public void mouseWheelMoved(MouseWheelEvent e) {

        if (e.getScrollType() == MouseWheelEvent.WHEEL_UNIT_SCROLL) {
            int amount = e.getWheelRotation() * 10;
            if ((scale == 10) && (amount == -10)) {
                return;
            }

            if ((scale == 150) && (amount == 10)) {
                return;
            }

            else {
                scale += amount;
                increment = 50.0 / scale;
                radIncrement += (amount / 10);

                // scale all shapes
                for (Line line : DDALines) {
                    line.start = scalePoint(line.start, amount);
                    line.end = scalePoint(line.end, amount);

                }

                for (Line line : BrensenhamLines) {
                    line.start = scalePoint(line.start, amount);
                    line.end = scalePoint(line.end, amount);
                }

                for (Circle circle : circles) {
                    Point tempPoint = new Point(circle.center.x, circle.center.y + circle.radius);
                    tempPoint = scalePoint(tempPoint, amount);

                    circle.center = scalePoint(circle.center, amount);

                    int newRadius = (int) Math.sqrt(
                            Math.pow(circle.center.x - tempPoint.x, 2) + Math.pow(circle.center.y - tempPoint.y, 2));

                    circle.radius = newRadius;

                }
                for (IrregularPolygon irregularPoly : irregularPolygons) {

                    // scale points
                    for (int i = 0; i < irregularPoly.points.length; i++) {
                        irregularPoly.points[i] = scalePoint(irregularPoly.points[i], amount);
                    }
                    irregularPoly.updateXY();

                }

                for (RegularPolygon RegularPoly : RegularPolygons) {

                    // scale points
                    for (int i = 0; i < RegularPoly.points.length; i++) {
                        RegularPoly.points[i] = scalePoint(RegularPoly.points[i], amount);
                    }
                    RegularPoly.updateXY();

                }
            }
        }
        repaint();
    }

    // store information about shapes such as thickness, line style, color
    class Line {
        private Point start;
        private Point end;
        private Color color;
        private BasicStroke stroke;
        private String lineStyle;

        public Line(Point start, Point end, Color color, BasicStroke stroke, String lineStyle) {
            this.start = start;
            this.end = end;
            this.color = color;
            this.stroke = stroke;
            this.lineStyle = lineStyle;

        }
    }

    class Circle {
        private Point center;
        private int radius;
        private Color color;
        private BasicStroke stroke;
        private String lineStyle;

        public Circle(Point center, int radius, Color color, BasicStroke stroke, String lineStyle) {
            this.center = center;
            this.radius = radius;
            this.color = color;
            this.stroke = stroke;
            this.lineStyle = lineStyle;

        }
    }

    class IrregularPolygon {
        private Point[] points;
        private int[] x;
        private int[] y;
        private Color color;
        private Color fillColor;
        private BasicStroke stroke;

        public IrregularPolygon(ArrayList<Point> points, Color color, BasicStroke stroke, Color fillColor) {
            this.points = (Point[]) points.toArray(new Point[points.size()]);
            updateXY();

            this.color = color;
            this.stroke = stroke;
            this.fillColor = fillColor;
        }

        public void DrawIrregularPolygon(Graphics2D ga) {
            Polygon p = new Polygon(x, y, x.length);

            ga.setStroke(stroke);
            ga.setColor(color);
            ga.drawPolygon(p);
            ga.setColor(fillColor);
            ga.fill(p);
        }

        public void updateXY() {
            x = new int[this.points.length];
            y = new int[this.points.length];

            for (int i = 0; i < this.points.length; i++) {
                x[i] = this.points[i].x;
                y[i] = this.points[i].y;
            }
        }
    }

    class RegularPolygon {
        private Point[] points;
        private int[] x;
        private int[] y;
        private Color color;
        private Color fillColor;
        private BasicStroke stroke;

        public RegularPolygon(ArrayList<Point> points, Color color, BasicStroke stroke, Color fillColor) {

            this.points = (Point[]) points.toArray(new Point[points.size()]);
            updateXY();

            this.color = color;
            this.stroke = stroke;
            this.fillColor = fillColor;
        }

        public void DrawRegularPolygon(Graphics2D ga) {

            Polygon p = new Polygon(x, y, x.length);

            ga.setStroke(stroke);
            ga.setColor(color);
            ga.drawPolygon(p);
            ga.setColor(fillColor);
            ga.fill(p);
        }

        public void updateXY() {
            x = new int[this.points.length];
            y = new int[this.points.length];

            for (int i = 0; i < this.points.length; i++) {
                x[i] = this.points[i].x;
                y[i] = this.points[i].y;
            }
        }
    }

    public void mouseDragged(MouseEvent e) {
    }

    public void mouseMoved(MouseEvent e) {
        pointTranslate(e.getX(), e.getY());
        updateCursorPoint(virtual.x, virtual.y);
        repaint();
    }

}
