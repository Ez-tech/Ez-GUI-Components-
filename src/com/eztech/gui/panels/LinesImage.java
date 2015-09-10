/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eztech.gui.panels;

import com.eztech.gui.util.Line;
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

    final float scale = 20;
    final Axis[] axises;
    float[] minValues;
    float[] maxValues;
    BufferedImage img;

    public LinesImage(List<Line> lines, Axis[] axises, float[] minValues, float[] maxValues) {
        this.maxValues = maxValues;
        this.minValues = minValues;
        this.axises = axises;
        int w = (int) ((maxValues[axises[0].index] - minValues[axises[0].index]) * scale);
        int h = (int) ((maxValues[axises[1].index] - minValues[axises[1].index]) * scale);
        img = new BufferedImage(w, h, BufferedImage.TYPE_3BYTE_BGR);
        drawLines(lines);
    }

    private void drawLines(List<Line> lines) {
        Graphics2D imageGraphics = img.createGraphics();
        //Clear BackGround
        imageGraphics.setColor(backGround);
        imageGraphics.fillRect(0, 0, img.getWidth(), img.getHeight());
        imageGraphics.translate(0, img.getHeight());
        lines.forEach((l) -> {
            imageGraphics.setStroke(new BasicStroke(1 + scale));
            float x1 = (l.p0[axises[0].index] - minValues[axises[0].index]) * scale;
            float y1 = (l.p0[axises[1].index] - minValues[axises[1].index]) * scale;
            float x2 = (l.p1[axises[0].index] - minValues[axises[0].index]) * scale;
            float y2 = (l.p1[axises[1].index] - minValues[axises[1].index]) * scale;
            if (l.color == null) {
                l.color = mapValueToColor(l.p1[axises[2].index],
                        minValues[axises[2].index],
                        maxValues[axises[2].index]);
            } else {
                imageGraphics.setStroke(new BasicStroke(1));
            }
            imageGraphics.setColor(l.color);
            imageGraphics.draw(new Line2D.Float(x1, -y1, x2, -y2));
        });
    }

    float lastscale;
    Image lastScaledImage;

    public Image getScaledImage(float scale) {
        if (lastscale != scale || lastScaledImage == null) {
            lastscale = scale;
            lastScaledImage = img.getScaledInstance((int) (img.getWidth() * scale / this.scale),
                    (int) (img.getHeight() * scale / this.scale),
                    Image.SCALE_DEFAULT);
        }
        return lastScaledImage;
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
