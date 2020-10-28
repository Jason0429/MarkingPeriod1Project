import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
// import java.awt.LayoutManager;

public class BasicSwing implements ActionListener {
    
    private int count = 0;
    private JFrame frame;
    private JLabel label;
    private JPanel panel;
    private JButton button;


    public BasicSwing() {

        frame = new JFrame();
        panel = new JPanel();
        button = new JButton("Click me");
        label = new JLabel("Welcome to Hangman!");
        
        button.addActionListener(this);

        panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
        
        frame.add(panel);
        panel.add(label);
        panel.add(button);
        
        

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Basic Swing");
        frame.pack();
        frame.setSize(400, 300);
        frame.setResizable(false);
        frame.setVisible(true); // Put last
    }
    public static void main(String[] args) {
        BasicSwing window = new BasicSwing();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        count++;
        label.setText("Clicks: " + count);
    }
}
