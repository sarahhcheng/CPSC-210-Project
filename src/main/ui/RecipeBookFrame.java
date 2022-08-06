package ui;

import javax.swing.*;
import java.awt.*;

public class RecipeBookFrame extends JFrame {
    private JPanel banner;
    private JPanel panel;
    private JPanel padding;
    private JPanel bottomPadding;


    public RecipeBookFrame() {
        firstPanel();
        recipePanel();
        paddingPanel();
        getBottomPadding();

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
        this.setSize(1000, 1000);
        this.setLayout(new BorderLayout());
        this.getContentPane().setBackground(new Color(0xFCE7E1));
        this.getContentPane().add(banner, BorderLayout.NORTH);
        this.getContentPane().add(panel, BorderLayout.WEST);
        this.getContentPane().add(padding, BorderLayout.EAST);
        this.getContentPane().add(bottomPadding, BorderLayout.SOUTH);
        this.setVisible(true);

    }

    public void firstPanel() {
        banner = new JPanel();
        JLabel welcome = new JLabel("My Recipe Book", JLabel.CENTER);
        welcome.setFont(new Font("Serif", Font.PLAIN, 30));
        banner.add(welcome);
        banner.setAlignmentX(Component.TOP_ALIGNMENT);
        banner.setBackground(Color.white);
        banner.setPreferredSize(new Dimension(1000, 75));
        banner.setMaximumSize(new Dimension(1000, 100));
    }


    public void recipePanel() {
        panel = new JPanel();
        JLabel recipes = new JLabel("Recipes:" + "\n", JLabel.CENTER);
        recipes.setFont(new Font("Serif", Font.PLAIN, 30));
        panel.add(recipes);
        panel.setPreferredSize(new Dimension(200, 800));
        panel.setMaximumSize(new Dimension(200, 800));
    }


    public void paddingPanel() {
        padding = new JPanel();
        padding.setBackground(new Color(0xFCE7E1));
        padding.setPreferredSize(new Dimension(800, 600));
        padding.setMaximumSize(new Dimension(1000, 600));
    }

    public void getBottomPadding() {
        bottomPadding = new JPanel();
        bottomPadding.setPreferredSize(new Dimension(150, 200));
        bottomPadding.setMaximumSize(new Dimension(150, 200));
        bottomPadding.setBackground(new Color(0xE1FCFA));
    }
}