package com.eztech.gui.panels;

/*
 * This code is based on an example provided by John Vella,
 * a tutorial reader.
 */
import com.eztech.gui.Rule;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

/* ScrollDemo2.java requires no other files. */
public class DrawingPanel2D extends DrawingPanel implements MouseListener, MouseMotionListener, MouseWheelListener {

    private final Dimension area; //indicates area taken up by graphics
    private JPanel drawingPane;
    Rectangle workingArea, SpecimenArea;
    private int Mode = YX;
    private float scale = 1, MintScale = (float) 0.1;
    private int offsetX = 50, offsetY = 50;
    float maxX = 100, maxY = 100, maxZ, minY, minX, minZ;
    JScrollPane scroller;
    Rule rulerX, rulerY;

    public DrawingPanel2D() {
        super();
        setLayout(new BorderLayout());
        area = new Dimension(0, 0);
        lines = new ArrayList<>();
        //Set up the drawing area.
        drawingPane = new DrawingPane();
        drawingPane.setBackground(Color.white);
        drawingPane.addMouseListener(this);
        drawingPane.addMouseMotionListener(this);
        drawingPane.addMouseWheelListener(this);
        //Put the drawing area in a scroll pane.
        scroller = new JScrollPane(drawingPane);
        scroller.setPreferredSize(new Dimension(300, 300));
        rulerX = new Rule(Rule.HORIZONTAL, true);
        rulerY = new Rule(Rule.VERTICAL, true);
        rulerX.setPreferredWidth(320);
        rulerY.setPreferredHeight(480);
        //scroller.setColumnHeaderView(rulerX);
        //scroller.setRowHeaderView(rulerY);
        rulerX.setMinValue(-offsetX);
        rulerY.setMinValue(-offsetY);
        //Lay out this demo.
        add(scroller, BorderLayout.CENTER);
        startRendering();
    }

    /**
     * The component inside the scroll pane.
     */
    public class DrawingPane extends JPanel {

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.translate(offsetX, drawingPane.getHeight() - offsetY);
            g.setColor(Color.ORANGE);
            g.drawLine(0, 0, 200, 0);
            g.drawLine(0, -200, 0, 0);
            g.setColor(Color.BLUE);
            int x = (int) (coordinates[0] * scale);
            int y = (int) (coordinates[1] * scale);
            int z = (int) (coordinates[2] * scale);
            switch (Mode) {
                case YX:
                    break;
                case ZX:
                    y = z;
                    break;
                case ZY:
                    x = y;
                    y = z;
            }
            g.drawLine(-5 + x, -y, 5 + x, -y);
            g.drawLine(x, 5 - y, x, -5 - y);
            Line l;
            for (int i = 0; i < lines.size(); i++) {
                l = lines.get(i);
                int x1 = 0, y1 = 0, x2 = 0, y2 = 0;
                Color c = null;
                switch (Mode) {
                    case YX:
                        x1 = (int) (l.x1 * scale);
                        y1 = (int) (l.y1 * scale);
                        x2 = (int) (l.x2 * scale);
                        y2 = (int) (l.y2 * scale);
                        c = mapValueToColor(l.z1, minZ, maxZ);
                        break;
                    case ZX:
                        x1 = (int) (l.x1 * scale);
                        y1 = (int) (l.z1 * scale);
                        x2 = (int) (l.x2 * scale);
                        y2 = (int) (l.z2 * scale);
                        c = mapValueToColor(l.y1, minY, maxY);
                        break;
                    case ZY:
                        x1 = (int) (l.y1 * scale);
                        y1 = (int) (l.z1 * scale);
                        x2 = (int) (l.y2 * scale);
                        y2 = (int) (l.z2 * scale);
                        c = mapValueToColor(l.x1, minX, maxX);
                }
                c = l.color == null ? c : l.color;
                g.setColor(c);
                g.drawLine(x1, -y1, x2, -y2);
            }
        }
    }

    @Override
    public void updateDrawingPane() {
        drawingPane.revalidate();
        drawingPane.repaint();
    }

    public void IncrmentOffsets(int x, int y) {
        offsetX += x;
        offsetY += y;
        rulerX.setMinValue(-offsetX);
        rulerY.setMinValue(-offsetY);
        changeFlag = true;
    }

    public void IncrmentScale(float scle, int Hy, int xp, int yp) {
        float xr = (xp - offsetX) / scale;
        float yr = (Hy - yp - offsetY) / scale;
        scale += scle;
        if (scale < MintScale) {
            scale = MintScale;
        }
        offsetX = (int) (xp - scale * xr);
        offsetY = (int) (Hy - yp - scale * yr);
        rulerY.setScale(scale);
        rulerX.setScale(scale);
        changeFlag = true;
    }

    Rectangle getRectangle(Point p1, Point p2) {
        int x1 = p1.x < p2.x ? p1.x : p2.x;
        int y1 = p1.y > p2.y ? p1.y : p2.y;
        int x2 = p1.x > p2.x ? p1.x : p2.x;
        int y2 = p1.y < p2.y ? p1.y : p2.y;
        return new Rectangle(x1, y1, x2 - x1, y1 - y2);
    }

    Rectangle getRectangle(int x1, int y1, int x2, int y2) {
        return getRectangle(new Point(x1, y1), new Point(x2, y2));
    }

    Color mapValueToColor(float value, float min, float max) {
        int blue = (int) (((value - min) * 255) / (0 - min));
        blue = blue < 0 ? 0 : blue;
        blue = blue > 255 ? 255 : blue;
        return new Color(blue / 5, blue / 5, blue);
    }

    @Override
    public void addLine(float[] p1, float[] p2, Color c) {
        Line l = new Line();
        l.x1 = p1[0];
        l.y1 = p1[1];
        l.z1 = p1[2];
        l.x2 = p2[0];
        l.y2 = p2[1];
        l.z2 = p2[2];
        l.color = c;
        lines.add(l);
        maxX = Math.max(maxX, Math.max(p1[0], p2[0]));
        maxY = Math.max(maxY, Math.max(p1[1], p2[1]));
        maxZ = Math.max(maxZ, Math.max(p1[2], p2[2]));
        minX = Math.min(minX, Math.min(p1[0], p2[0]));
        minY = Math.min(minY, Math.min(p1[1], p2[1]));
        minZ = Math.min(minZ, Math.min(p1[2], p2[2]));
        changeFlag = true;
    }

    @Override
    public void removeLine(int index) {
        lines.remove(index);
        changeFlag = true;
    }

    @Override
    public void removeLastLine() {
        removeLine(lines.size() - 1);
    }

    @Override
    public void setMode(int Mode) {
        this.Mode = Mode;
        changeFlag = true;
    }

    @Override
    public void clearScrean() {
        lines.clear();
        area.width = 0;
        area.height = 0;
        minX = 0;
        maxX = 1;
        minY = 0;
        maxY = 1;
        minZ = 0;
        maxZ = 1;
        changeFlag = true;
    }

    //Handle mouse events.
    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        int Hy = drawingPane.getHeight(), xp = e.getX(), yp = e.getY();
        float incrmt = e.getWheelRotation() < 0 ? 0.1f : -0.1f;
        IncrmentScale(incrmt, Hy, xp, yp);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (SwingUtilities.isRightMouseButton(e)) {
            //This will clear the graphic objects.
            clearScrean();
        } else if (SwingUtilities.isMiddleMouseButton(e)) {
            scale = 1;
            offsetX = 50;
            offsetY = 50;
            rulerX.setMinValue(-offsetX);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        pp.setLocation(e.getPoint());
    }
    Point pp = new Point();

    @Override
    public void mouseDragged(MouseEvent e) {
        Point cp = e.getPoint();
        IncrmentOffsets(cp.x - pp.x, pp.y - cp.y);
//        int h = scroller.getHorizontalScrollBar().getValue() - (cp.x - pp.x);
//        int v = scroller.getVerticalScrollBar().getValue() - (cp.y - pp.y);
//        h = h > scroller.getHorizontalScrollBar().getMaximum() ? scroller.getHorizontalScrollBar().getMaximum() : h;
//        h = h < scroller.getHorizontalScrollBar().getMinimum() ? scroller.getHorizontalScrollBar().getMinimum() : h;
//        v = v > scroller.getVerticalScrollBar().getMaximum() ? scroller.getVerticalScrollBar().getMaximum() : v;
//        v = v < scroller.getVerticalScrollBar().getMinimum() ? scroller.getVerticalScrollBar().getMinimum() : v;
//        scroller.getHorizontalScrollBar().setValue(h);
//        scroller.getVerticalScrollBar().setValue(v);
        pp.setLocation(cp);
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        switch (Mode) {
            case YX:
                drawingPane.setToolTipText(String.format("X=%.1f ,Y=%.1f\n", (e.getX() - offsetX) / scale, (drawingPane.getHeight() - e.getY() - offsetY) / scale));
                break;
            case ZX:
                drawingPane.setToolTipText(String.format("X=%.1f ,Z=%.1f\n", (e.getX() - offsetX) / scale, (drawingPane.getHeight() - e.getY() - offsetY) / scale));
                break;
            case ZY:
                drawingPane.setToolTipText(String.format("Y=%.1f ,Z=%.1f\n", (e.getX() - offsetX) / scale, (drawingPane.getHeight() - e.getY() - offsetY) / scale));
        }
        rulerY.setValue(e.getY());
        rulerX.setValue(e.getX());
    }

//    
//
//
//
//Test in main
//
    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                //Create and set up the window.
                DrawingPanel2D newContentPane;
                JFrame frame = new JFrame("ScrollDemo2");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                //Create and set up the content pane.
                newContentPane = new DrawingPanel2D();
                newContentPane.setCoordinates(10, 50, 0);
                newContentPane.setOpaque(true); //content panes must be opaque
                frame.setContentPane(newContentPane);
                //Display the window.
                frame.pack();
                frame.setVisible(true);
            }
        });
    }

}
