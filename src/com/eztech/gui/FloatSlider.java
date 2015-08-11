package com.eztech.gui;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.awt.Color;
import java.awt.Font;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Dictionary;
import java.util.Hashtable;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.plaf.UIResource;

/**
 *
 * @author yami
 */
public class FloatSlider extends JSlider {

    public static final int MULTIPLIER = 10;
    private float floatValue = 0;

    /**
     * Get the value of floatValue
     *
     * @return the value of floatValue
     */
    public float getFloatValue() {
        return floatValue;
    }

    public void setFloatValue(float floatValue) {
        firePropertyChange("floatValue", this.floatValue, floatValue);
        this.floatValue = floatValue;
        super.setValue((int) (floatValue * MULTIPLIER));
    }

    @Override
    public void setValue(int n) {
        setFloatValue((float) n / MULTIPLIER);
    }

    public void setFloatMaximum(float maximum) {
        int max = (int) maximum * MULTIPLIER;
        setMajorTickSpacing(max / 4);
        setMinorTickSpacing(getMajorTickSpacing() / 4);
        super.setMaximum(max);
    }

    public void setFloatMinimum(float minimum) {
        super.setMinimum((int) minimum * MULTIPLIER);
    }

    @Override
    public Hashtable createStandardLabels(int increment, int start) {
        if (start > getMaximum() || start < getMinimum()) {
            throw new IllegalArgumentException("Slider label start point out of range.");
        }

        if (increment <= 0) {
            throw new IllegalArgumentException("Label incremement must be > 0");
        }

        class SmartHashtable extends Hashtable<Object, Object> implements PropertyChangeListener {

            int increment = 0;
            int start = 0;
            boolean startAtMin = false;

            class LabelUIResource extends JLabel implements UIResource {

                public LabelUIResource(String text, int alignment) {
                    super(text, alignment);
                    setName("Slider.label");
                }

                @Override
                public Font getFont() {
                    Font font = super.getFont();
                    if (font != null && !(font instanceof UIResource)) {
                        return font;
                    }
                    return FloatSlider.this.getFont();
                }

                @Override
                public Color getForeground() {
                    Color fg = super.getForeground();
                    if (fg != null && !(fg instanceof UIResource)) {
                        return fg;
                    }
                    if (!(FloatSlider.this.getForeground() instanceof UIResource)) {
                        return FloatSlider.this.getForeground();
                    }
                    return fg;
                }
            }

            public SmartHashtable(int increment, int start) {
                super();
                this.increment = increment;
                this.start = start;
                startAtMin = start == getMinimum();
                createLabels();
            }

            @Override
            public void propertyChange(PropertyChangeEvent e) {
                if (e.getPropertyName().equals("minimum") && startAtMin) {
                    start = getMinimum();
                }

                if (e.getPropertyName().equals("minimum")
                        || e.getPropertyName().equals("maximum")) {
                    clear();
                    createLabels();
                    ((JSlider) e.getSource()).setLabelTable(this);
                }
            }

            void createLabels() {
                for (int labelIndex = start; labelIndex <= FloatSlider.this.getMaximum(); labelIndex += getMajorTickSpacing()) {
                    put(labelIndex, new LabelUIResource(String.format("%.1f", (float) labelIndex / MULTIPLIER), JLabel.CENTER));
                }
            }
        }

        SmartHashtable table = new SmartHashtable(increment, start);

        Dictionary labelTable = getLabelTable();

        if (labelTable != null && (labelTable instanceof PropertyChangeListener)) {
            removePropertyChangeListener((PropertyChangeListener) labelTable);
        }

        addPropertyChangeListener(table);

        return table;
    }

}
