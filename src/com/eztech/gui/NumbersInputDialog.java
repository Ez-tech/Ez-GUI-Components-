/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.eztech.gui;

import javax.swing.JButton;

/**
 *
 * @author yami
 */
public class NumbersInputDialog extends javax.swing.JDialog {

    /**
     * Creates new form NumbersInputDialog
     */
    static NumbersInputDialog instance = new NumbersInputDialog(null, "0.0");

    static synchronized public float getInput(java.awt.Frame parent, String text) {
        NumbersInputDialog instance = new NumbersInputDialog(parent, text);
        instance.setVisible(true);
        return instance.getValue();
    }

    private NumbersInputDialog(java.awt.Frame parent, String text) {
        super(parent, true);
        initComponents();
        setText(text);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tfNumber = new javax.swing.JTextField();
        b1 = new javax.swing.JButton();
        b2 = new javax.swing.JButton();
        b3 = new javax.swing.JButton();
        b4 = new javax.swing.JButton();
        b5 = new javax.swing.JButton();
        b6 = new javax.swing.JButton();
        b7 = new javax.swing.JButton();
        b8 = new javax.swing.JButton();
        b9 = new javax.swing.JButton();
        b0 = new javax.swing.JButton();
        bFloatPoint = new javax.swing.JButton();
        bClear = new javax.swing.JButton();
        bSign = new javax.swing.JButton();
        bC_One = new javax.swing.JButton();
        bOk = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        tfNumber.setFont(tfNumber.getFont().deriveFont(tfNumber.getFont().getSize()+18f));
        tfNumber.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tfNumber.setText("00.00");

        b1.setFont(new java.awt.Font("Times New Roman", 1, 35)); // NOI18N
        b1.setText("1");
        b1.setName("1"); // NOI18N
        b1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b1ActionPerformed(evt);
            }
        });

        b2.setFont(new java.awt.Font("Times New Roman", 1, 35)); // NOI18N
        b2.setText("2");
        b2.setName("1"); // NOI18N
        b2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b2ActionPerformed(evt);
            }
        });

        b3.setFont(new java.awt.Font("Times New Roman", 1, 35)); // NOI18N
        b3.setText("3");
        b3.setName("1"); // NOI18N
        b3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b3ActionPerformed(evt);
            }
        });

        b4.setFont(new java.awt.Font("Times New Roman", 1, 35)); // NOI18N
        b4.setText("4");
        b4.setName("1"); // NOI18N
        b4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b4ActionPerformed(evt);
            }
        });

        b5.setFont(new java.awt.Font("Times New Roman", 1, 35)); // NOI18N
        b5.setText("5");
        b5.setName("1"); // NOI18N
        b5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b5ActionPerformed(evt);
            }
        });

        b6.setFont(new java.awt.Font("Times New Roman", 1, 35)); // NOI18N
        b6.setText("6");
        b6.setName("1"); // NOI18N
        b6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b6ActionPerformed(evt);
            }
        });

        b7.setFont(new java.awt.Font("Times New Roman", 1, 35)); // NOI18N
        b7.setText("7");
        b7.setName("1"); // NOI18N
        b7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b7ActionPerformed(evt);
            }
        });

        b8.setFont(new java.awt.Font("Times New Roman", 1, 35)); // NOI18N
        b8.setText("8");
        b8.setName("1"); // NOI18N
        b8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b8ActionPerformed(evt);
            }
        });

        b9.setFont(new java.awt.Font("Times New Roman", 1, 35)); // NOI18N
        b9.setText("9");
        b9.setName("1"); // NOI18N
        b9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b9ActionPerformed(evt);
            }
        });

        b0.setFont(new java.awt.Font("Times New Roman", 1, 35)); // NOI18N
        b0.setText("0");
        b0.setName("1"); // NOI18N
        b0.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                b0ActionPerformed(evt);
            }
        });

        bFloatPoint.setFont(new java.awt.Font("Times New Roman", 1, 35)); // NOI18N
        bFloatPoint.setText(".");
        bFloatPoint.setName("1"); // NOI18N
        bFloatPoint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bFloatPointActionPerformed(evt);
            }
        });

        bClear.setFont(new java.awt.Font("Times New Roman", 1, 35)); // NOI18N
        bClear.setText("C");
        bClear.setName("1"); // NOI18N
        bClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bClearActionPerformed(evt);
            }
        });

        bSign.setFont(new java.awt.Font("Times New Roman", 1, 35)); // NOI18N
        bSign.setText("-/+");
        bSign.setName("1"); // NOI18N
        bSign.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bSignActionPerformed(evt);
            }
        });

        bC_One.setFont(new java.awt.Font("Times New Roman", 1, 35)); // NOI18N
        bC_One.setText("<=");
        bC_One.setName("1"); // NOI18N
        bC_One.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bC_OneActionPerformed(evt);
            }
        });

        bOk.setFont(new java.awt.Font("Times New Roman", 1, 35)); // NOI18N
        bOk.setText("OK");
        bOk.setName("1"); // NOI18N
        bOk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bOkActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tfNumber)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(b1)
                                    .addComponent(b4)
                                    .addComponent(b7))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(b2)
                                    .addComponent(b5)
                                    .addComponent(b8)))
                            .addComponent(b0, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(bFloatPoint, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(b3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(b6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(b9))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(bClear, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(bC_One, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(bOk))
                            .addComponent(bSign, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tfNumber, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(b2)
                            .addComponent(b1)
                            .addComponent(bClear))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(b4)
                                .addComponent(b5))
                            .addComponent(bC_One))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(b7)
                                    .addComponent(b8))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(b0))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(bSign, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(bOk, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(b3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(b6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(b9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(bFloatPoint)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void b1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b1ActionPerformed
        insertValue(evt);
    }//GEN-LAST:event_b1ActionPerformed

    private void b2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b2ActionPerformed
        insertValue(evt);
    }//GEN-LAST:event_b2ActionPerformed

    private void b3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b3ActionPerformed
        insertValue(evt);
    }//GEN-LAST:event_b3ActionPerformed

    private void b4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b4ActionPerformed
        insertValue(evt);
    }//GEN-LAST:event_b4ActionPerformed

    private void b5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b5ActionPerformed
        insertValue(evt);
    }//GEN-LAST:event_b5ActionPerformed

    private void b6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b6ActionPerformed
        insertValue(evt);
    }//GEN-LAST:event_b6ActionPerformed

    private void b7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b7ActionPerformed
        insertValue(evt);
    }//GEN-LAST:event_b7ActionPerformed

    private void b8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b8ActionPerformed
        insertValue(evt);
    }//GEN-LAST:event_b8ActionPerformed

    private void b9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b9ActionPerformed
        insertValue(evt);
    }//GEN-LAST:event_b9ActionPerformed

    private void b0ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_b0ActionPerformed
        String text = tfNumber.getText();
        text += ((JButton) (evt.getSource())).getText();
        tfNumber.setText(text);
    }//GEN-LAST:event_b0ActionPerformed

    private void bFloatPointActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bFloatPointActionPerformed
        String text = tfNumber.getText();
        text += ((JButton) (evt.getSource())).getText();
        tfNumber.setText(text);
    }//GEN-LAST:event_bFloatPointActionPerformed

    private void bClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bClearActionPerformed
        setText("0");
    }//GEN-LAST:event_bClearActionPerformed

    private void bSignActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bSignActionPerformed
        String text = tfNumber.getText();
        char sign = text.charAt(0);
        switch (sign) {
            case '-':
                text = text.substring(1);
                break;
            default:
                text = '-' + text;
        }
        setText(text);
    }//GEN-LAST:event_bSignActionPerformed

    private void bC_OneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bC_OneActionPerformed
        String text = tfNumber.getText();
        tfNumber.setText(text.substring(0, text.length() - 1));
    }//GEN-LAST:event_bC_OneActionPerformed

    private void bOkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bOkActionPerformed
        try {
            float number = Float.parseFloat(tfNumber.getText());
            dispose();
        } catch (Exception e) {
            System.err.println("Invalid Input");
        }
    }//GEN-LAST:event_bOkActionPerformed

    void insertValue(java.awt.event.ActionEvent evt) {
        String text = tfNumber.getText();
        text += ((JButton) (evt.getSource())).getText();
        setText(text);
    }

    public void setText(String text) {
        try {
            float number = Float.parseFloat(text);
            if (number != (int) number) {
                tfNumber.setText(number + "");
            } else {
                tfNumber.setText((int) number + "");
            }
        } catch (Exception e) {
            tfNumber.setText(text);
        }
    }

    public float getValue() {
        return Float.parseFloat(tfNumber.getText());
    }

    @Override
    public void setVisible(boolean b) {
        setLocationRelativeTo(null);
        super.setVisible(b);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton b0;
    private javax.swing.JButton b1;
    private javax.swing.JButton b2;
    private javax.swing.JButton b3;
    private javax.swing.JButton b4;
    private javax.swing.JButton b5;
    private javax.swing.JButton b6;
    private javax.swing.JButton b7;
    private javax.swing.JButton b8;
    private javax.swing.JButton b9;
    private javax.swing.JButton bC_One;
    private javax.swing.JButton bClear;
    private javax.swing.JButton bFloatPoint;
    private javax.swing.JButton bOk;
    private javax.swing.JButton bSign;
    private javax.swing.JTextField tfNumber;
    // End of variables declaration//GEN-END:variables
}
