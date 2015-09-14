/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eztech.gui.panels;

import com.eztech.gui.util.Line;
import com.eztech.gui.util.Utilities;
import static com.eztech.gui.util.Utilities.mapValueToColor;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.util.List;

/**
 *
 * @author yami
 */
public class LinesImage {

    final Color backGround = Color.WHITE;

    final Axis[] axises;
    final float[] minValues;
    final float[] maxValues;
    final float realWidth, realheight;
    final List<Line> lines;
    BufferedImage img;
    float scale = 2;

    public LinesImage(List<Line> lines, Axis[] axises) {
        this(lines, axises, Utilities.getMinimumValues(lines), Utilities.getMaximumValues(lines));
    }

    public LinesImage(List<Line> lines, Axis[] axises, float[] minValues, float[] maxValues) {
        this(lines, axises, minValues, maxValues, 2);
    }

    public LinesImage(List<Line> lines, Axis[] axises, float[] minValues, float[] maxValues, float scale) {
        this.maxValues = maxValues;
        this.minValues = minValues;
        this.axises = axises;
        this.lines = lines;
        this.realWidth = maxValues[axises[0].index] - minValues[axises[0].index];
        this.realheight = maxValues[axises[1].index] - minValues[axises[1].index];
        this.scale = scale;
        renderLinesToImage(scale);
    }

    private BufferedImage createScaledImage(float scale) {
        this.scale = scale;
        return new BufferedImage((int) (realWidth * scale) + 2,
                (int) (realheight * scale) + 2,
                BufferedImage.TYPE_3BYTE_BGR);
    }

    private BufferedImage createScaledImage(int width, int height) {
        return createScaledImage(getFitScale(width, height));
    }

    private float getFitScale(int width, int height) {
        float sw = width / realWidth;
        float sh = height / realheight;
        return sh > sw ? sw : sh;
    }

    private void renderLinesToImage(float scale) {
        img = createScaledImage(scale);
        Graphics2D imageGraphics = img.createGraphics();
        //Clear BackGround
        imageGraphics.setColor(backGround);
        imageGraphics.fillRect(0, 0, img.getWidth(), img.getHeight());
        imageGraphics.translate(0, img.getHeight());
        BasicStroke scaledStroke = new BasicStroke(2 + scale);
        BasicStroke normStroke = new BasicStroke(1 + scale / 8);
        lines.forEach((l) -> {
            float x1 = (l.p0[axises[0].index] - minValues[axises[0].index]) * scale;
            float y1 = (l.p0[axises[1].index] - minValues[axises[1].index]) * scale;
            float x2 = (l.p1[axises[0].index] - minValues[axises[0].index]) * scale;
            float y2 = (l.p1[axises[1].index] - minValues[axises[1].index]) * scale;
            if (l.color == null) {
                imageGraphics.setStroke(scaledStroke);
                l.color = mapValueToColor(l.p1[axises[2].index],
                        minValues[axises[2].index],
                        maxValues[axises[2].index]);
            } else {
                imageGraphics.setStroke(normStroke);
            }
            imageGraphics.setColor(l.color);
            imageGraphics.draw(new Line2D.Float(x1, -y1, x2, -y2));
        });
    }

    float lastscale;
    Image lastScaledImage;

    public Image getScaledImage(float scale) {
        return getScaledImage(scale, true);
    }

    public Image getScaledImage(float scale, boolean forceRender) {
        if (lastscale != scale || lastScaledImage == null) {
            if (scale > this.scale || forceRender) {
                renderLinesToImage(scale);
                lastScaledImage = img;
            } else {
                int width = (int) (img.getWidth() * scale);
                int height = (int) (img.getHeight() * scale);
                lastScaledImage = img.getScaledInstance(width, height, Image.SCALE_DEFAULT);
            }
            lastscale = scale;
        }
        return lastScaledImage;
    }

    public Image fiImage(int width, int height) {
        return getScaledImage(getFitScale(width, height), true);
    }

    public int getRGB(float x, float y) {
        int realx = (int) ((x + minValues[axises[0].index]) * scale);
        int realy = (int) ((y + minValues[axises[1].index]) * scale);
        if (realx > 0 && realx < img.getWidth() && realy > 0 && realy < img.getHeight()) {
            return img.getRGB(realx, realy);
        } else {
            return Integer.MAX_VALUE;
        }
    }
}
