import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.MouseInputListener;
import java.awt.event.*;
import java.text.DecimalFormat;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

class Main extends JFrame {

    boolean full = false;

    public Main() {

    

    JPanel root = new JPanel();
    root.setLayout(new BorderLayout());

    root.setBorder(new EmptyBorder(10, 10, 10, 10));
    JLabel title = new JLabel("Choose an Option");
    title.setFont(new Font("Times New Roman", Font.PLAIN, 25));

    JPanel titlePanel = new JPanel();

    titlePanel.add(title);
    root.add(titlePanel, BorderLayout.NORTH);

    JPanel namePanel = new JPanel();
    namePanel.setLayout(new GridLayout(2, 2, 50, 50));
    namePanel.setBorder(new EmptyBorder(10, 10, 10, 10));

    JButton calc = new JButton("Tip Calculator");
    
    // add an action listener to make when you click the tips button it goes to another JPanel so you can calculate tips
    calc.addActionListener(e -> {
        
        // hide the root JPanel
        root.setVisible(false);
        
        // create a new "root" JPanel
        JPanel root2 = new JPanel();
        root2.setLayout(new BoxLayout(root2, BoxLayout.Y_AXIS));
        root2.setBorder(new EmptyBorder(10, 10, 10, 10));

        JPanel tips = new JPanel();
        tips.setLayout(new GridLayout(4, 1, 10, 10));
        tips.setBorder(new EmptyBorder(10, 10, 10, 10));
        this.setTitle("Tip Calculator");
        this.setSize(500, 350);
        
        // populate it with three text fields to calculate the answer
        JTextField amount = new JTextField("How much was the bill?");
        amount.setMaximumSize(new Dimension(400, 35));

        amount.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                amount.setText("");
            }
        });

        JTextField percent = new JTextField("What % of Tip?");
        percent.setMaximumSize(new Dimension(400, 35));
        percent.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                percent.setText("");
            }
        });

        JTextField people = new JTextField("How many people?");
        people.setMaximumSize(new Dimension(400, 35));
        people.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                people.setText("");
            }
        });

        // add a button onto the JPanel to calculate the answer
        JButton calculate = new JButton("Calculate");

        // add an action listener to do the calculations
        calculate.addActionListener(f -> {
            
            DecimalFormat df = new DecimalFormat("##.##");
            double Total = Double.parseDouble(amount.getText()) * (Double.parseDouble(percent.getText()) / 100.0 + 1) / Double.parseDouble(people.getText());
            
            JOptionPane.showMessageDialog(this, df.format(Total) + " is how much one person owes in total", "Total owed", JOptionPane.PLAIN_MESSAGE);
        });

        // add a button onto the JPanel to take the user back to the main screen
        JButton back = new JButton("Back");

        // add an action listener to actually take the user back to the main screen
        back.addActionListener(f -> {
            this.setSize(1000, 500);
            this.setTitle("Navigation");
            tips.setVisible(false);
            root.setVisible(true);
        });
        
        // add everything to the JPanel and add the JPanel to the JFrame
        tips.add(amount);
        tips.add(percent);
        tips.add(people);
        tips.add(calculate);
        root2.add(tips);
        root2.add(back);
        this.add(root2);
    });


    JButton google = new JButton("Get to Google");

    google.addActionListener(e -> {

        /**
         *  Citation:
         *  https://www.codejava.net/java-se/swing/how-to-create-hyperlink-with-jlabel-in-java-swing
         */
        try {

            Desktop.getDesktop().browse(new URI("https://www.google.com/"));

        } catch (IOException | URISyntaxException e1) {
            e1.printStackTrace();
        }
    });
    
    JButton fullscreen = new JButton("Fullscreen");
    fullscreen.addActionListener(e -> {
        
        if (!full) {
            this.setExtendedState(JFrame.MAXIMIZED_BOTH);
            repaint();
        } else {
            this.setSize(new Dimension(1000, 500));
            repaint();
        }
        full = !full;
    });

    JButton credits = new JButton("Credits");
    credits.addActionListener(e -> {
        JOptionPane.showMessageDialog(this, "This was created by Regin Potter", "Credits", JOptionPane.PLAIN_MESSAGE);

        
    });

    JPanel quitPanel = new JPanel();
    JButton exit = new JButton("Quit");
    exit.addActionListener(e -> {
        System.exit(0);
    });
    quitPanel.add(exit);

    namePanel.add(calc);
    namePanel.add(google);
    namePanel.add(fullscreen);
    namePanel.add(credits);

    root.add(namePanel, BorderLayout.CENTER);
    root.add(quitPanel, BorderLayout.SOUTH);


    this.getContentPane().add(root);
    this.setTitle("Navigation");
    this.setSize(1000, 1000);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.pack();
    this.setVisible(true);
    this.setSize(1000, 500);
    }

    public static void main(String[] args) {
    //new Main();

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Main();
            }
        });
    }
}