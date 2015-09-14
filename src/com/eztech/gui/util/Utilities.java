/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eztech.gui.util;

import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author yami
 */
public class Utilities {

    public static Rectangle getRectangle(Point p1, Point p2) {
        int x1 = p1.x < p2.x ? p1.x : p2.x;
        int y1 = p1.y > p2.y ? p1.y : p2.y;
        int x2 = p1.x > p2.x ? p1.x : p2.x;
        int y2 = p1.y < p2.y ? p1.y : p2.y;
        return new Rectangle(x1, y1, x2 - x1, y1 - y2);
    }

    public static Rectangle getRectangle(int x1, int y1, int x2, int y2) {
        return getRectangle(new Point(x1, y1), new Point(x2, y2));
    }

    public static Color mapValueToColor(float value, float min, float max) {
        max = min < 0 ? 0 : max;
        int blue = (int) (((value - min) * 255) / (max - min));
        blue = blue < 0 ? 0 : blue;
        blue = blue > 255 ? 255 : blue;
        return new Color(blue / 5, blue / 5, blue);
    }

    public static float mapColorToValue(Color c, float min, float max) {
        float value = Float.NaN;
        if (!c.equals(Color.WHITE)) {
            int blue = c.getBlue();
            max = min < 0 ? 0 : max;
            value = (blue * (max - min) / 255) + min;
        }
        return value;
    }

    public static float[] getMaximumValues(List<Line> lines) {
        float[] maxValues = Arrays.copyOf(lines.get(0).p0, lines.get(0).p0.length);
        lines.forEach(line -> {
            for (int i = 0; i < line.p0.length; i++) {
                maxValues[i] = Math.max(maxValues[i], Math.max(line.p0[i], line.p1[i]));
            }
        });
        return maxValues;
    }

    public static float[] getMinimumValues(List<Line> lines) {
        float[] minValues = Arrays.copyOf(lines.get(0).p0, lines.get(0).p0.length);
        lines.forEach(line -> {
            for (int i = 0; i < line.p0.length; i++) {
                minValues[i] = Math.min(minValues[i], Math.min(line.p0[i], line.p1[i]));
            }
        });
        return minValues;
    }
}
