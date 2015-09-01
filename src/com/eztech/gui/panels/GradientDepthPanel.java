/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eztech.gui.panels;

/**
 *
 * @author yami
 */
import com.eztech.gui.util.Utilities;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JPanel;

public class GradientDepthPanel extends JPanel {

    float minValue = 0, maxValue = 10, actualValue = 5;

    int numberOfTicks = 10;
    boolean isVertical;

    public GradientDepthPanel() {
        this(true);
    }

    public GradientDepthPanel(boolean isVertical) {
        this.isVertical = isVertical;
        if (isVertical) {
            setSize(30, HEIGHT);
            setMinimumSize(new Dimension(30, HEIGHT));
        } else {
            setSize(WIDTH, 30);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        int w, h, gradX, gradY, lenght;
        w = getWidth();
        h = getHeight();
        if (isVertical) {
            gradY = h;
            gradX = 0;
            lenght = h;
        } else {
            gradY = 0;
            gradX = w;
            lenght = w;
        }
        Color minColor = Utilities.mapValueToColor(minValue, minValue, maxValue);
        Color maxColor = Utilities.mapValueToColor(maxValue, minValue, maxValue);
        GradientPaint gp = new GradientPaint(0, 0, maxColor, gradX, gradY, minColor);
        g2d.setPaint(gp);
        g2d.fillRect(0, 0, w, h);
        for (int i = 1; i < numberOfTicks; i++) {
            g2d.setColor(Color.WHITE);
            int tickLength = (lenght / numberOfTicks) * i;
            if (isVertical) {
                g2d.drawLine(0, tickLength, w / 2, tickLength);
                g2d.drawString(String.format("%.1f", mapLengthTovalue(tickLength)), w / 2, tickLength);
            } else {
                g2d.drawLine(tickLength, 0, tickLength, h / 2);
                g2d.drawString(String.format("%.1f", mapLengthTovalue(tickLength)), tickLength + 1, h);
            }
        }
        if (!Float.isNaN(actualValue)) {
            g2d.setColor(Color.ORANGE);
            if (isVertical) {
                System.out.println(mapValueToLength(actualValue)+"  ,  "+h);
                g2d.fillRect(0, mapValueToLength(actualValue)-3, w, 6);
            } else {
                g2d.fillRect(mapValueToLength(actualValue)-3, 0, 6, h);
            }
        }
    }

    private int getLength() {
        if (isVertical) {
            return getHeight();
        } else {
            return getWidth();
        }
    }

    int mapValueToLength(float value) {
        int l = getLength();
        return (int) (l*(maxValue-value) / (maxValue - minValue));
    }

    float mapLengthTovalue(int lenV) {
        int l = getLength();
        return maxValue - (maxValue - minValue) * lenV/ l ;
    }

    public void setMinValue(float minValue) {
        this.minValue = minValue;
    }

    public void setMaxValue(float maxValue) {
        this.maxValue = maxValue;
    }

    public void setActualValue(float actualValue) {
        this.actualValue = actualValue;
        repaint();
    }

    public void setNumberOfTicks(int numberOfTicks) {
        this.numberOfTicks = numberOfTicks;
    }

}
