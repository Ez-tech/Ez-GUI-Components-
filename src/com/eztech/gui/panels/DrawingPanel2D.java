package com.eztech.gui.panels;

/*
 * This code is based on an example provided by John Vella,
 * a tutorial reader.
 */
import com.eztech.gui.Rule;
import com.eztech.gui.util.Utilities;
import static com.eztech.gui.util.Utilities.mapValueToColor;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.*;

/* ScrollDemo2.java requires no other files. */
public class DrawingPanel2D extends DrawingPanel implements MouseListener, MouseMotionListener, MouseWheelListener {

    private final Dimension area; //indicates area taken up by graphics
    private DrawingPane drawingPane;
    Rectangle workingArea, SpecimenArea;
    private float scale = 1, MintScale = (float) 0.1;
    private int offsetX = 50, offsetY = 50;
    private Axis[] axises = {Axis.X, Axis.Y, Axis.Z};
    float[] maxValues = new float[axises.length];
    float[] minValues = new float[axises.length];
    JScrollPane scroller;
    Rule rulerX, rulerY;
    GradientDepthPanel depthPanel;
    JLabel lCoordinates = new JLabel("X = 0, Y= 0, Z = 0");

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
        scroller.setPreferredSize(new Dimension(300, 500));
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
        add(lCoordinates, BorderLayout.SOUTH);
        startRendering();
    }

    public void addGradientPanel(GradientDepthPanel gradientPanel) {
        this.depthPanel = gradientPanel;
        if (gradientPanel.isVertical) {
            add(gradientPanel, BorderLayout.EAST);
        } else {
            add(gradientPanel, BorderLayout.SOUTH);
        }
    }

    /**
     * The component inside the scroll pane.
     */
    public class DrawingPane extends JPanel {

        final Color backGround = Color.WHITE;
        final Color originAxis = Color.ORANGE;
        final Color coordinatesColor = Color.BLUE;
        BufferedImage bImage;

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            bImage = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_3BYTE_BGR);
            Graphics2D imageGraphics = bImage.createGraphics();
            imageGraphics.setColor(backGround);
            imageGraphics.fillRect(0, 0, getWidth(), getHeight());
            imageGraphics.translate(offsetX, drawingPane.getHeight() - offsetY);
            imageGraphics.setColor(originAxis);
            imageGraphics.drawLine(0, 0, 200, 0);
            imageGraphics.drawLine(0, -200, 0, 0);
            imageGraphics.setColor(coordinatesColor);
            int x = (int) (coordinates[axises[0].index] * scale);
            int y = (int) (coordinates[axises[1].index] * scale);
            imageGraphics.drawLine(-5 + x, -y, 5 + x, -y);
            imageGraphics.drawLine(x, 5 - y, x, -5 - y);
            lines.forEach((l) -> {
                int x1 = (int) (l.p0[axises[0].index] * scale);
                int y1 = (int) (l.p0[axises[1].index] * scale);
                int x2 = (int) (l.p1[axises[0].index] * scale);
                int y2 = (int) (l.p1[axises[1].index] * scale);
                if (l.color == null) {
                    l.color = mapValueToColor(l.p1[axises[2].index],
                            minValues[axises[2].index],
                            maxValues[axises[2].index]);
                }
                imageGraphics.setColor(l.color);
                imageGraphics.drawLine(x1, -y1, x2, -y2);
            });
            g.drawImage(bImage, 0, 0, null);
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
        this.getGraphics().translate(offsetX, drawingPane.getHeight() - offsetY);
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

    @Override
    public void addLine(float[] p0, float[] p1, Color c) {
        Line l = new Line(p0, p1, c);
        lines.add(l);
        for (int i = 0; i < axises.length; i++) {
            maxValues[i] = Math.max(maxValues[i], Math.max(p0[i], p1[i]));
            minValues[i] = Math.min(minValues[i], Math.min(p0[i], p1[i]));
        }
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

    public void setAxis(Axis... axis) {
        System.arraycopy(axis, 0, this.axises, 0, axis.length);
        changeFlag = true;
    }

    @Override
    public void clearScrean() {
        lines.clear();
        area.width = 0;
        area.height = 0;
        Arrays.fill(maxValues, 1);
        Arrays.fill(minValues, 0);
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
        pp.setLocation(cp);
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        float x = (e.getX() - offsetX) / scale;
        float y = (drawingPane.getHeight() - e.getY() - offsetY) / scale;
        Color zc = new Color(drawingPane.bImage.getRGB(e.getX(), e.getY()));
        float z = Utilities.mapColorToValue(zc, minValues[axises[2].index], maxValues[axises[2].index]);
        String sCoord = String.format("%s=%.1f, %s=%.1f, %s=%.1f",
                axises[0], x,
                axises[1], y,
                axises[2], z);
        drawingPane.setToolTipText(sCoord);
        lCoordinates.setText(sCoord);
        if (depthPanel != null) {
            depthPanel.setMaxValue(maxValues[axises[2].index]);
            depthPanel.setMinValue(minValues[axises[2].index]);
            depthPanel.setActualValue(z);
        }
        rulerY.setValue(e.getY());
        rulerX.setValue(e.getX());
    }
}
