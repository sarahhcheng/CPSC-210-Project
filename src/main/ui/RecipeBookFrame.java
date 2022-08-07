package ui;

import model.*;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class RecipeBookFrame extends JFrame implements ListSelectionListener {
    private JList list;
    private DefaultListModel listModel;

    private ImageIcon image;
    private JLabel displayImage;

    private JPanel banner;
    private JPanel panel;
    private JPanel padding;
    private JPanel bottomPadding;

    private JTextField recipeName;
    private JTextField ingredients;
    private JTextField instructions;
    private ArrayList<JTextField> listofInputs;


    private JButton addRecipe;
    private JButton removeRecipe;
    private JButton loadRecipe;

    private Recipe recipe;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;



    public RecipeBookFrame() {

        firstPanel();
        recipePanel();
        paddingPanel();
        getBottomPadding();

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("List of Recipes");
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
        banner.setBackground(new Color(0xE1FCFA));
        banner.setPreferredSize(new Dimension(1000, 75));
        banner.setMaximumSize(new Dimension(1000, 100));
        try {
            image = new ImageIcon(getClass().getResource("output-onlinepngtools (1).png"));
            displayImage = new JLabel(image);
            banner.add(displayImage);
        } catch (Exception e) {
            System.out.println("Image cannot be found");
        }

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
        bottomPadding.setPreferredSize(new Dimension(150, 100));
        bottomPadding.setMaximumSize(new Dimension(150, 100));
        bottomPadding.setBackground(new Color(0xE1FCFA));
        bottomPadding.setLayout(new BoxLayout(bottomPadding, BoxLayout.LINE_AXIS));
        addRecipe = new JButton("Add");
        removeRecipe = new JButton("Remove");
        loadRecipe = new JButton("Load recipe");

        recipeInputFields();

        bottomPadding.add(addRecipe);
        bottomPadding.add(removeRecipe);
        bottomPadding.add(loadRecipe);
        bottomPadding.add(Box.createHorizontalStrut(10));
        bottomPadding.add(new JSeparator(SwingConstants.VERTICAL));
        bottomPadding.add(Box.createHorizontalStrut(10));
    }

    public void recipeInputFields() {
        listofInputs = new ArrayList<>();
        recipeName = new JTextField();
        recipeName.setPreferredSize(new Dimension(20, 20));
        recipeName.setBounds(20,20, 20, 20);
        ingredients = new JTextField();
        ingredients.setPreferredSize(new Dimension(20, 1));
        ingredients.setBounds(100, 20, 20,20);



        instructions = new JTextField(1);

        listofInputs.add(recipeName);
        listofInputs.add(ingredients);
        listofInputs.add(instructions);

        bottomPadding.add(new JLabel("Recipe:  "));
        bottomPadding.add(recipeName);
        bottomPadding.add(new JLabel("Ingredients:  "));
        bottomPadding.add(ingredients);
        bottomPadding.add(new JLabel("Instructions:  "));
        bottomPadding.add(instructions);
    }


    // Effects: Adds Listener
    @Override
    public void valueChanged(ListSelectionEvent e) {
        for (JTextField textField : listofInputs) {
            textField.addActionListener((ActionListener) e);
            textField.getDocument().addDocumentListener((DocumentListener) e);
        }
    }

    public class RemoveRecipeListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }
}

