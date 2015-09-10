/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eztech.gui.util;

import com.eztech.gui.panels.Axis;
import java.awt.Color;

/**
 *
 * @author yami
 */
public class Line {

    public float[] p0 = new float[Axis.values().length];
    public float[] p1 = new float[Axis.values().length];
    public Color color;

    public Line() {

    }

    public Line(float[] p0, float[] p1) {
        this(p0, p1, Color.BLACK);
    }

    public Line(float[] p0, float[] p1, Color c) {
        System.arraycopy(p0, 0, this.p0, 0, this.p0.length);
        System.arraycopy(p1, 0, this.p1, 0, this.p1.length);
        color = c;
    }
}
