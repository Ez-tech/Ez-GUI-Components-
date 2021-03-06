/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eztech.gui.panels;

import com.eztech.gui.util.Line;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;

/**
 * Drawing Panel is a abstract JPanel to be inserted in GUI that can be Either
 * 2D or 3D Viewer using the subclasses
 *
 * @author yami
 */
public abstract class DrawingPanel extends JPanel implements Runnable {

    /**
     * Mode Of Viewing The G-code Viewing Panel
     */
    public static final byte YX = 0, ZX = 1, ZY = 2;
    protected float[] coordinates = new float[3];
    protected ArrayList<Line> lines;
    protected boolean changeFlag = true;
    Thread updatingThread;

    public DrawingPanel() {
        updatingThread = new Thread(this);
        updatingThread.setName("Rendering Thread");
    }

    public void startRendering() {
        updatingThread.start();
    }

    public void setCoordinates(float x, float y, float z) {
        this.coordinates = new float[]{x, y, z};
    }

    /**
     * Add The CNC Configuration File to the defines the workspace , ..etc
     *
     * @param cncConfig configuration
     */
    /**
     * Add a line to draw it o the screen
     *
     * @param line
     */
    public abstract void addLine(Line line);
    
    public void addLine(float[] p0, float[] p1, Color c) {
        addLine(new Line(p0, p1, c));
    }
    
    public void addLines(List<Line> lines) {
        lines.forEach(line -> addLine(line));
    }

    public void addLine(float[] p1, float[] p2) {
        addLine(p1, p2, null);
    }

    /**
     * Add Lines to the Viewing Panel
     *
     * @param lines list of lines to be added
     * @param c list of the colors of the lines
     */
    public void addLines(float[][] lines, Color[] c) {
        for (int i = 1; i < lines.length; i += 2) {
            addLine(lines[i - 1], lines[i], c[i / 2]);
        }
    }

    public void addLines(float[][] lines) {

        for (int i = 1; i < lines.length; i += 2) {
            addLine(lines[i - 1], lines[i]);
        }
    }

    /**
     * Clear Lines from the Screen
     */
    public abstract void clearScrean();

    /**
     * Remove the last drawn line
     */
    public void removeLastLine() {
        removeLine(lines.size() - 1);
    }

    /**
     * Remove the drawing Line by index
     *
     * @param index the index of the line to be removed
     */
    public void removeLine(int index) {
        lines.remove(index);
        changeFlag = true;
    }

    /**
     * Redraw The Viewing Panel
     */
    public abstract void updateDrawingPane();
    int refreshRate = 20;

    @Override
    public void run() {
        int skips = 0;
        while (true) {
            try {
                if (this.isVisible()) {
                    if (changeFlag == true) {
                        updateDrawingPane();
                        changeFlag = false;
                    } else {
                        skips = (skips + 1) % (refreshRate / 2);
                        if (skips == 0) {
                            changeFlag = true;
                        }
                    }
                    Thread.sleep(1000 / refreshRate);
                } else {
                    Thread.sleep(500);
                }
            } catch (Exception e) {
                System.err.printf("Rendering Error in \"%s\" %s\n", this.getClass().getName(), e.getMessage());
            }
        }
    }

}
