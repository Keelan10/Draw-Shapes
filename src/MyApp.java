import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MyApp {

    public static void main(String[] args) {
        new MainFrame();
    }

}

class MainFrame extends JFrame {
    static DDAPanel DDAPanel = new DDAPanel();
    static BresenhamPanel BresenhamPanel = new BresenhamPanel();
    static MidpointCirclePanel CirclePanel = new MidpointCirclePanel();
    static IrregularPolygonPanel irregularPolygonPanel = new IrregularPolygonPanel();
    static RegularPolygonPanel RegularPolygonPanel = new RegularPolygonPanel();

    public MainFrame() {
        super("Scalable Coordinate System");

        CartesianPlane p = new CartesianPlane();
        add(p);

        ToolBar toolBar = new ToolBar();

        toolBar.Eraser.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                CartesianPlane.DDALines.clear();
                CartesianPlane.BrensenhamLines.clear();
                CartesianPlane.circles.clear();
                CartesianPlane.RegularPolygons.clear();
                CartesianPlane.irregularPolygons.clear();
                repaint();
            }
        });

        toolBar.palette.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                CartesianPlane.lineColor = JColorChooser.showDialog(null, "Select a color",
                        Color.black);
            }
        });

        toolBar.Fill.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                CartesianPlane.fillColor = JColorChooser.showDialog(null, "Select a color",
                        Color.black);
            }
        });

        toolBar.drawCircle.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Left-click and drag the cursor. Release to draw.", "How to?",
                        JOptionPane.INFORMATION_MESSAGE);
                DDAPanel.setVisible(false);
                BresenhamPanel.setVisible(false);
                RegularPolygonPanel.setVisible(false);
                irregularPolygonPanel.setVisible(false);
                CirclePanel.setVisible(true);
                p.setVisible(false);
                add(CirclePanel);
                revalidate();
            }
        });

        toolBar.lineThickness.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int z = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter thickness(1-5)"));
                if (z < 1) {
                    CartesianPlane.lineThickness = 1;
                } else if (z > 5) {
                    CartesianPlane.lineThickness = 5;
                } else {
                    CartesianPlane.lineThickness = z;
                }
            }
        });

        toolBar.drawIrreg.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Right-click to plot. Left click to draw.", "How to?",
                        JOptionPane.INFORMATION_MESSAGE);
                DDAPanel.setVisible(false);
                BresenhamPanel.setVisible(false);
                CirclePanel.setVisible(false);
                RegularPolygonPanel.setVisible(false);
                irregularPolygonPanel.setVisible(true);
                p.setVisible(false);
                add(irregularPolygonPanel);
                revalidate();
            }
        });

        toolBar.drawPolygon.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                RegularPolygonPanel.NumSides = Integer
                        .parseInt(JOptionPane.showInputDialog(null, "Enter Number of sides"));
                JOptionPane.showMessageDialog(null, "Left-click and drag the cursor. Release to draw.", "How to?",
                        JOptionPane.INFORMATION_MESSAGE);
                DDAPanel.setVisible(false);
                BresenhamPanel.setVisible(false);
                CirclePanel.setVisible(false);
                irregularPolygonPanel.setVisible(false);
                RegularPolygonPanel.setVisible(true);
                p.setVisible(false);
                add(RegularPolygonPanel);
                revalidate();
            }
        });

        toolBar.lineStyle.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Object[] options = { "Solid", "Dashed", "Dotted" };
                int x = JOptionPane.showOptionDialog(null, "Choose one to continue", "Line Style",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
                if (((String) options[x]).equalsIgnoreCase("SOLID")) {
                    CartesianPlane.lineStyle = "solid";
                } else if (((String) options[x]).equalsIgnoreCase("DASHED")) {
                    CartesianPlane.lineStyle = "dashed";
                } else if (((String) options[x]).equalsIgnoreCase("DOTTED")) {
                    CartesianPlane.lineStyle = "dotted";
                }
            }
        });

        toolBar.drawLine.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                Object[] options = { "DDA", "Bresenham" };
                int y = JOptionPane.showOptionDialog(null, "Choose one to continue", "Line Algorithm",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);
                JOptionPane.showMessageDialog(null, "Left-click and drag the cursor. Release to draw.", "How to?",
                        JOptionPane.INFORMATION_MESSAGE);

                if (((String) options[y]).equalsIgnoreCase("DDA")) {
                    BresenhamPanel.setVisible(false);
                    CirclePanel.setVisible(false);
                    RegularPolygonPanel.setVisible(false);
                    irregularPolygonPanel.setVisible(false);
                    DDAPanel.setVisible(true);
                    p.setVisible(false);
                    add(DDAPanel);
                    revalidate();
                } else if (((String) options[y]).equalsIgnoreCase("BRESENHAM")) {
                    DDAPanel.setVisible(false);
                    CirclePanel.setVisible(false);
                    RegularPolygonPanel.setVisible(false);
                    irregularPolygonPanel.setVisible(false);
                    BresenhamPanel.setVisible(true);
                    p.setVisible(false);
                    add(BresenhamPanel);
                    revalidate();
                }
            }
        });

        add(toolBar, BorderLayout.NORTH);

        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setResizable(false);
    }

}
