import java.awt.*;
import javax.swing.*;

public class ToolBar extends JToolBar {

   JButton drawLine, lineThickness, palette, lineStyle, drawCircle, drawPolygon, drawIrreg,Fill, Eraser;

   public ToolBar() {

      setBackground(new Color(46, 46, 46));

      setLayout(new GridLayout());

      setToolbar();

   }

   void setToolbar() {

      // creating menus and submenus
      drawLine = new JButton();
      drawLine.setIcon(new ImageIcon("images/draw-line.png"));
      drawLine.setVerticalTextPosition(SwingConstants.BOTTOM);
      drawLine.setHorizontalTextPosition(SwingConstants.CENTER);
      drawLine.setForeground(Color.white);
      drawLine.setBackground(new Color(36, 36, 36, 230));

      lineThickness = new JButton();
      lineThickness.setIcon(new ImageIcon("images/thickness.png"));
      lineThickness.setVerticalTextPosition(SwingConstants.BOTTOM);
      lineThickness.setHorizontalTextPosition(SwingConstants.CENTER);
      lineThickness.setForeground(Color.white);
      lineThickness.setBackground(new Color(36, 36, 36, 230));

      palette = new JButton("");
      palette.setIcon(new ImageIcon("images/palette1.png"));
      palette.setVerticalTextPosition(SwingConstants.BOTTOM);
      palette.setHorizontalTextPosition(SwingConstants.CENTER);
      palette.setForeground(Color.white);
      palette.setBackground(new Color(36, 36, 36, 230));

      lineStyle = new JButton();
      lineStyle.setIcon(new ImageIcon("images/dashed-line.png"));
      lineStyle.setVerticalTextPosition(SwingConstants.BOTTOM);
      lineStyle.setHorizontalTextPosition(SwingConstants.CENTER);
      lineStyle.setForeground(Color.white);
      lineStyle.setBackground(new Color(36, 36, 36, 230));

      drawCircle = new JButton();
      drawCircle.setIcon(new ImageIcon("images/draw-circle.png"));
      drawCircle.setVerticalTextPosition(SwingConstants.BOTTOM);
      drawCircle.setHorizontalTextPosition(SwingConstants.CENTER);
      drawCircle.setForeground(Color.white);
      drawCircle.setBackground(new Color(36, 36, 36, 230));

      drawPolygon = new JButton();
      drawPolygon.setIcon(new ImageIcon("images/polygons.png"));
      drawPolygon.setVerticalTextPosition(SwingConstants.BOTTOM);
      drawPolygon.setHorizontalTextPosition(SwingConstants.CENTER);
      drawPolygon.setForeground(Color.white);
      drawPolygon.setBackground(new Color(36, 36, 36, 230));
      
      drawIrreg = new JButton();
      drawIrreg.setIcon(new ImageIcon("images/irregularpolygon.png"));
      drawIrreg.setVerticalTextPosition(SwingConstants.BOTTOM);
      drawIrreg.setHorizontalTextPosition(SwingConstants.CENTER);
      drawIrreg.setForeground(Color.white);
      drawIrreg.setBackground(new Color(36, 36, 36, 230));
      
      Fill = new JButton();
      Fill.setIcon(new ImageIcon("images/fill.png"));
      Fill.setVerticalTextPosition(SwingConstants.BOTTOM);
      Fill.setHorizontalTextPosition(SwingConstants.CENTER);
      Fill.setForeground(Color.white);
      Fill.setBackground(new Color(36, 36, 36, 230));
      
      Eraser = new JButton();
      Eraser.setIcon(new ImageIcon("images/eraser.png"));
      Eraser.setVerticalTextPosition(SwingConstants.BOTTOM);
      Eraser.setHorizontalTextPosition(SwingConstants.CENTER);
      Eraser.setForeground(Color.white);
      Eraser.setBackground(new Color(36, 36, 36, 230));

      JPanel panel = new JPanel();
      panel.setBackground(Color.black);

      add(drawLine);
      add(lineThickness);
      add(lineStyle);
      add(drawCircle);
      add(drawPolygon);
      add(drawIrreg);
      add(palette);
      add(Fill);
      add(Eraser);

      ToolTipManager.sharedInstance().setInitialDelay(0);
      ToolTipManager.sharedInstance().setDismissDelay(1500);
      drawLine.setToolTipText("Draw line");
      lineThickness.setToolTipText("Select a suitable thickness");
      lineStyle.setToolTipText("Choose line style");
      drawCircle.setToolTipText("Draw circle");
      drawPolygon.setToolTipText("Draw a regular polygon");
      drawIrreg.setToolTipText("Draw an irregular polygon");
      palette.setToolTipText("Add colour to the shape outline");
      Fill.setToolTipText("Polygon fill colour");
      Eraser.setToolTipText("Erase everything");
   }


}
