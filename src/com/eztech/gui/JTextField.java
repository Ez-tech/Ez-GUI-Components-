/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eztech.gui;

import java.awt.Container;
import java.awt.Frame;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 *
 * @author yami
 */
public class JTextField extends javax.swing.JTextField {

    static boolean enableNumberPadPopUp = false;

    public static void setEnableNumberPadPopUp(boolean enableNumberPadPopUp) {
        JTextField.enableNumberPadPopUp = enableNumberPadPopUp;
    }

    public static boolean isEnableNumberPadPopUp() {
        return enableNumberPadPopUp;
    }

    public JTextField() {
        super();
        if (enableNumberPadPopUp) {
            addMouseListener(new MouseListener() {

                @Override
                public void mouseClicked(MouseEvent e) {
                    Container cont = getParent();
                    while (!(cont instanceof Frame)) {
                        cont = cont.getParent();
                    }
                    Float n = NumbersInputDialog.getInput((Frame) cont, getText());
                    setText(n.toString());
                }

                @Override
                public void mousePressed(MouseEvent e) {
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                }

                @Override
                public void mouseExited(MouseEvent e) {
                }
            });
        }
    }

}
