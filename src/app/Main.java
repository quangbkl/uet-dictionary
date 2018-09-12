package app;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JFrame jf = new JFrame();
        JButton jb = new JButton("Click");
        jb.setBounds(100, 100, 70, 20);
        jf.add(jb);
        jf.setSize(450, 550);
        jf.setLayout(null);
        jf.setVisible(true);
    }
}
