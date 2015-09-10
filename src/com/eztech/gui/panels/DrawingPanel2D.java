package com.eztech.gui.panels;

/*
 * This code is based on an example provided by John Vella,
 * a tutorial reader.
 */
import com.eztech.gui.Rule;
import com.eztech.gui.util.Line;
import com.eztech.gui.util.Utilities;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

/* ScrollDemo2.java requires no other files. */
public class DrawingPanel2D extends DrawingPanel implements MouseListener, MouseMotionListener, MouseWheelListener {

    private final DrawingPane drawingPane;
    Rectangle workingArea, SpecimenArea;
    private float scale = 1, MintScale = 0.1f;
    private int offsetX = 50, offsetY = 50;
    private final Axis[] axises = {Axis.X, Axis.Y, Axis.Z};
    float[] maxValues = new float[axises.length];
    float[] minValues = new float[axises.length];
    boolean linesChanged = true;
    GradientDepthPanel depthPanel;

    public DrawingPanel2D() {
        super();
        setLayout(new BorderLayout());
        lines = new ArrayList<>();
        //Set up the drawing area.
        drawingPane = new DrawingPane();
        drawingPane.addMouseListener(this);
        drawingPane.addMouseMotionListener(this);
        drawingPane.addMouseWheelListener(this);
        //Put the drawing area in a scroll pane.
        drawingPane.setPreferredSize(new Dimension(200, 200));
        add(drawingPane, BorderLayout.CENTER);
        startRendering();
    }

    public void addGradientPanel(GradientDepthPanel gradientPanel) {
        this.depthPanel = gradientPanel;
        if (gradientPanel.isVertical) {
            add(gradientPanel, BorderLayout.EAST);
        } else {
            add(gradientPanel, BorderLayout.PAGE_START);
        }
    }

    /**
     * The component inside the scroll pane.
     */
    public class DrawingPane extends JPanel {

        final Color backGround = Color.WHITE;
        final Color originAxis = Color.ORANGE;
        final Color coordinatesColor = Color.BLUE;
        LinesImage linesimg;

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            //Clear BackGround
            g.setColor(backGround);
            g.fillRect(0, 0, getWidth(), getHeight());
            //Translate to get normal Coordinates
            g.translate(offsetX, drawingPane.getHeight() - offsetY);
            if (!lines.isEmpty()) {
                if (linesChanged) {
                    linesimg = new LinesImage(lines, axises, minValues, maxValues);
                    linesChanged = false;
                }
                Image linesScaledImage = linesimg.getScaledImage(scale);
                g.drawImage(linesScaledImage,
                        (int) (minValues[axises[0].index] * scale),
                        (int) (-minValues[axises[1].index] * scale - linesScaledImage.getHeight(null)),
                        null);
            }
            //Draw Origins Axis
            g.setColor(originAxis);
            g.drawLine(0, 0, 200, 0);
            g.drawLine(0, -200, 0, 0);
            g.setColor(coordinatesColor);
            //Draw Current Coordinates
            int xCoord = (int) (coordinates[axises[0].index] * scale);
            int yCoord = (int) (coordinates[axises[1].index] * scale);
            g.drawLine(-5 + xCoord, -yCoord, 5 + xCoord, -yCoord);
            g.drawLine(xCoord, 5 - yCoord, xCoord, -5 - yCoord);
            Point p = getMousePosition();
            if (p != null) {
                float xMouse = ((float) p.getX() - offsetX) / scale;
                float yMouse = (drawingPane.getHeight() - (float) p.getY() - offsetY) / scale;
                Color zc = Color.white;
                if (linesimg != null) {
                    zc = new Color(linesimg.getRGB(xMouse, yMouse));
                }
                float zMouse = Utilities.mapColorToValue(zc, minValues[axises[2].index], maxValues[axises[2].index]);
                String sCoord = String.format("%s=%.1f, %s=%.1f, %s=%.1f",
                        axises[0], xMouse,
                        axises[1], yMouse,
                        axises[2], zMouse);
                if (depthPanel != null) {
                    depthPanel.setMaxValue(maxValues[axises[2].index]);
                    depthPanel.setMinValue(minValues[axises[2].index]);
                    depthPanel.setActualValue(zMouse);
                }
                g.drawString(sCoord, 5 - offsetX, offsetY - 5);
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
        this.getGraphics().translate(offsetX, drawingPane.getHeight() - offsetY);
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
        linesChanged = true;
    }

    @Override
    public void removeLine(int index) {
        super.removeLine(index);
        linesChanged = true;
    }

    public void setAxis(Axis... axis) {
        System.arraycopy(axis, 0, this.axises, 0, axis.length);
        linesChanged = true;
        changeFlag = true;
    }

    @Override
    public void clearScrean() {
        lines.clear();
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
            clearScrean();
        } else if (SwingUtilities.isMiddleMouseButton(e)) {
            scale = 1;
            offsetX = 50;
            offsetY = 50;
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
        changeFlag = true;
    }
}
