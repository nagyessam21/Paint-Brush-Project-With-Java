
package paint_brush;

import javax.swing.JFrame;

public class Paint_brush {

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setSize(800,800);
        Panel x=new Panel();
        frame.setContentPane(x);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Paint Brush");
        frame.setVisible(true);
        
    }
}
