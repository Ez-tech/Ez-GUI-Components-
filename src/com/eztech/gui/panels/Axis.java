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
public enum Axis {
    X(0),
    Y(1),
    Z(2);
    int index;

    private Axis(int index) {
        this.index = index;
    }

}
