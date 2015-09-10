
import com.eztech.gui.panels.DrawingPanel2D;
import com.eztech.gui.panels.GradientDepthPanel;
import javax.swing.JFrame;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author yami
 */
public class TestClass {

    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                //Create and set up the window.
                DrawingPanel2D newContentPane;
                JFrame frame = new JFrame("Drawing Test");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setLocationRelativeTo(null);
                frame.setSize(500,500);
                //Create and set up the content pane.
                newContentPane = new DrawingPanel2D();
                newContentPane.setCoordinates(10, 50, 0);
                newContentPane.addLine(new float[]{10, 15, 6}, new float[]{50, 1, -1});
                newContentPane.addLine(new float[]{50, 1, -4}, new float[]{40, 30, -3});
                newContentPane.setOpaque(true); //content panes must be opaque
                newContentPane.addGradientPanel(new GradientDepthPanel(true));
                frame.setContentPane(newContentPane);
                //Display the window.
                frame.setVisible(true);
            }
        });
    }
}
