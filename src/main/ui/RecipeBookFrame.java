package ui;

import model.*;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.plaf.basic.BasicBorders;
import javax.swing.plaf.basic.BasicSplitPaneUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class RecipeBookFrame extends JFrame implements ListSelectionListener {
    private JList list;
    private DefaultListModel listModel;
    private JScrollPane recipeList;
    private RecipeBook recipeBook;

    private ImageIcon image;
    private JLabel displayImage;

    private JPanel banner;
    private JPanel panel;
    private JPanel padding;
    private JPanel bottomPadding;

    private JTextField recipeName;
    private JTextField durationTimes;
    private JTextField serves;
    private JTextField ingredients;
    private JTextField instructions;
    private JTextArea displayRecipe;
    private ArrayList<JTextField> listofInputs;


    private JButton addRecipe;
    private JButton removeRecipe;
    private JButton loadRecipe;

    private Recipe recipe;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;



    public RecipeBookFrame() {

        listModel = new DefaultListModel<>();
        list = new JList<>(listModel);
        recipeList = new JScrollPane(list);
        displayRecipe = new JTextArea();

        firstPanel();
        recipePanel();
        paddingPanel();
        getBottomPadding();
        recipeBook = new RecipeBook("sjd", "sjsj");

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
        recipes.setFont(new Font("Serif", Font.HANGING_BASELINE, 30));
        panel.setBackground(new Color(0xFEF2D7));
        panel.add(recipes);
        panel.setPreferredSize(new Dimension(200, 800));
        panel.setMaximumSize(new Dimension(200, 800));
        recipeList.createVerticalScrollBar();
        recipeList.setPreferredSize(new Dimension(200, 800));
        panel.add(recipeList, BorderLayout.WEST);

    }

    public void paddingPanel() {
        padding = new JPanel();
        padding.setBackground(new Color(0xFCE7E1));
        padding.setPreferredSize(new Dimension(800, 600));
        padding.setMaximumSize(new Dimension(1000, 600));
        padding.add(displayRecipe);
    }

    public void getBottomPadding() {
        bottomPadding = new JPanel();
        bottomPadding.setPreferredSize(new Dimension(150, 100));
        bottomPadding.setMaximumSize(new Dimension(150, 100));
        bottomPadding.setBackground(new Color(0xE1FCFA));
        bottomPadding.setLayout(new FlowLayout());

        addRecipe = new JButton("+");
        addRecipe.setFont(new Font("Arial", Font.BOLD, 15));
        addRecipe.addActionListener(new AddListener(addRecipe));

        removeRecipe = new JButton("-");
        removeRecipe.setFont(new Font("Arial", Font.BOLD, 15));
        loadRecipe = new JButton("Load recipe");
        loadRecipe.setFont(new Font("Serif", Font.TRUETYPE_FONT, 15));

        createInputFields();
        recipeInputFields();

        bottomPadding.add(addRecipe);
        bottomPadding.add(removeRecipe);
        bottomPadding.add(loadRecipe);
        bottomPadding.add(Box.createHorizontalStrut(10));
        bottomPadding.add(new JSeparator(SwingConstants.VERTICAL));
        bottomPadding.add(Box.createHorizontalStrut(10));
    }

    public void createInputFields() {
        listofInputs = new ArrayList<>();

        recipeName = new JTextField();
        recipeName.setPreferredSize(new Dimension(125, 30));
        recipeName.setBounds(20,20, 20, 20);
        ingredients = new JTextField();
        ingredients.setPreferredSize(new Dimension(180, 30));
        instructions = new JTextField();
        instructions.setPreferredSize(new Dimension(190, 30));
        durationTimes = new JTextField();
        durationTimes.setPreferredSize(new Dimension(40, 30));
        serves = new JTextField();
        serves.setPreferredSize(new Dimension(40, 30));
        listofInputs.add(recipeName);
        listofInputs.add(durationTimes);
        listofInputs.add(serves);
        listofInputs.add(ingredients);
        listofInputs.add(instructions);
    }

    public void recipeInputFields() {
        JLabel simplerecipe = new JLabel("Recipe:");
        simplerecipe.setFont(new Font("Serif", Font.HANGING_BASELINE, 18));
        bottomPadding.add(simplerecipe);
        bottomPadding.add(recipeName);

        JLabel durationField = new JLabel("  Duration:");
        durationField.setFont(new Font("Serif", Font.HANGING_BASELINE, 18));
        bottomPadding.add(durationField);
        bottomPadding.add(durationTimes);

        JLabel serveInputs = new JLabel("  Serves:");
        serveInputs.setFont(new Font("Serif", Font.HANGING_BASELINE, 18));
        bottomPadding.add(serveInputs);
        bottomPadding.add(serves);

        JLabel simpleingredients = new JLabel("\n" + " Ingredients:");
        simpleingredients.setFont(new Font("Serif", Font.HANGING_BASELINE, 18));
        bottomPadding.add(simpleingredients);
        bottomPadding.add(ingredients);

        JLabel simpleinstructions = new JLabel("  Instructions:");
        simpleinstructions.setFont(new Font("Serif", Font.HANGING_BASELINE, 18));
        bottomPadding.add(simpleinstructions);
        bottomPadding.add(instructions);

    }

    public void setUpButtonListeners() {
        ActionListener buttonListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getActionCommand().equals("Add")) {
                    System.out.println("Add button has been clicked");
                } else if (e.getActionCommand().equals("Remove")) {
                    System.out.println("Remove button has been clicked");
                }
            }
        };
        addRecipe.addActionListener(buttonListener);
    }


    // Effects: Adds Listener
    @Override
    public void valueChanged(ListSelectionEvent e) {
        for (JTextField textField : listofInputs) {
            textField.addActionListener((ActionListener) e);
            textField.getDocument().addDocumentListener((DocumentListener) e);
        }
    }

//    public class RemoveRecipeListener implements ActionListener {
//
//        @Override
//        public void actionPerformed(ActionEvent e) {
//
//        }
//    }
//
//    // EFFECTS: removes everything in the recipe book
//    public void clearRecipeBook() {
//        for (JTextField inputFields: listofInputs) {
//            inputFields.remove(inputFields);
//        }
//    }
//
//    // EFFECTS: does not add the recipe if not all input fields are used
//    public void checkEmptyInputs() {
//        for (JTextField inputFields: listofInputs) {
//            String name = inputFields.getText();
//
//            if (name == "") {
//                inputFields.selectAll();
//                return;
//            }
//        }
//    }
// The AddListener class, allows the add button to add the recipe to the display
    class AddListener implements ActionListener {
        private JButton button;

        public AddListener(JButton button) {
            this.button = button;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            Recipe newRecipe = createRecipe();
            recipeBook.addRecipe(newRecipe);
            listModel.addElement(newRecipe.getName());
            displayRecipe.setText(newRecipe.toString());
        }

        public void resetInputs() {
            for (JTextField inputs: listofInputs) {
                inputs.requestFocusInWindow();
                inputs.setText("");
            }
        }

        // EFFECTS: returns the recipe given by the user's input in the input fields
        public Recipe createRecipe() {
            String name = recipeName.getText();
            String inputIngredients = ingredients.getText();
            String inputInstructions = instructions.getText();
            String inputDurationMinutes = durationTimes.getText();
            String inputServes = serves.getText();

            Recipe newRecipe = new Recipe(name, Integer.parseInt(inputDurationMinutes),
                    Integer.parseInt(inputServes), "");
            newRecipe.addIngredient(new Ingredient(inputIngredients, 0,"fjfvn"));
            Instructions instructions1 = new Instructions();
            instructions1.addStep(inputInstructions);
            newRecipe.setInstructions(instructions1);
            return newRecipe;

        }

    }


}

