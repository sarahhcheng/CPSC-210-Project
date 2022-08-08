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
import java.io.FileNotFoundException;
import java.io.IOException;
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
    private JTextField ingredientName;
    private JTextField ingredientQuantity;
    private JTextField ingredientUnit;
    private JTextField instructions;
    private JTextArea displayRecipe;
    private ArrayList<JTextField> listofInputs;


    private JButton addRecipe;
    private JButton removeRecipe;
    private JButton saveButton;
    private JButton loadRecipe;
    private JButton addInstructions;
    private JButton addIngredients;

    private Recipe recipe;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;



    public RecipeBookFrame() {

        listModel = new DefaultListModel<>();
        list = new JList<>(listModel);
        recipeList = new JScrollPane(list);
        displayRecipe = new JTextArea();
        list.addListSelectionListener(new UpdateDisplay());

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
        displayRecipe.setPreferredSize(new Dimension(400, 400));
        displayRecipe.setBackground(new Color(0xFCE7E1));
        displayRecipe.setEditable(false);
        padding.add(displayRecipe);
    }

    public void getBottomPadding() {
        bottomPadding = new JPanel();
        bottomPadding.setPreferredSize(new Dimension(150, 100));
        bottomPadding.setMaximumSize(new Dimension(150, 100));
        bottomPadding.setBackground(new Color(0xE1FCFA));
        bottomPadding.setLayout(new FlowLayout());


        createButtons();
        createInputFields();
        inputField1();
        inputField2();

        bottomPadding.add(addRecipe);
        bottomPadding.add(removeRecipe);
        bottomPadding.add(saveButton);
        bottomPadding.add(addIngredients);
        bottomPadding.add(addInstructions);
        bottomPadding.add(loadRecipe);
        bottomPadding.add(Box.createHorizontalStrut(10));
        bottomPadding.add(new JSeparator(SwingConstants.VERTICAL));
        bottomPadding.add(Box.createHorizontalStrut(10));
    }

    public void createButtons() {
        addRecipe = new JButton("+");
        addRecipe.setFont(new Font("Arial", Font.BOLD, 15));
        addRecipe.addActionListener(new AddListener());
        addRecipe.addActionListener(new AutoSave());

        removeRecipe = new JButton("-");
        removeRecipe.setFont(new Font("Arial", Font.BOLD, 15));
        removeRecipe.addActionListener(new Remover());
        removeRecipe.addActionListener(new AutoSave());

        addIngredients = new JButton("+ Ingredient ");
        addIngredients.setFont(new Font("Serif", Font.TRUETYPE_FONT, 15));
        addIngredients.addActionListener(new AddIngredients());

        saveButton = new JButton("Save ");
        saveButton.setFont(new Font("Serif", Font.TRUETYPE_FONT, 15));
        saveButton.addActionListener(new AutoSave());

        addInstructions = new JButton("+ Instructions");
        addInstructions.setFont(new Font("Serif", Font.TRUETYPE_FONT, 15));
        addInstructions.addActionListener(new AddInstruction());

        loadRecipe = new JButton("Load recipe");
        loadRecipe.setFont(new Font("Serif", Font.TRUETYPE_FONT, 15));
        loadRecipe.addActionListener(new Load());


    }

    public void createInputFields() {
        listofInputs = new ArrayList<>();

        recipeName = new JTextField();
        recipeName.setPreferredSize(new Dimension(125, 30));
        recipeName.setBounds(20,20, 20, 20);
        ingredientName = new JTextField();
        ingredientName.setPreferredSize(new Dimension(180, 30));
        ingredientQuantity = new JTextField();
        ingredientQuantity.setPreferredSize(new Dimension(180, 30));
        ingredientUnit = new JTextField();
        ingredientUnit.setPreferredSize(new Dimension(180, 30));
        instructions = new JTextField();
        instructions.setPreferredSize(new Dimension(190, 30));
        durationTimes = new JTextField();
        durationTimes.setPreferredSize(new Dimension(40, 30));
        serves = new JTextField();
        serves.setPreferredSize(new Dimension(40, 30));
        listofInputs.add(recipeName);
        listofInputs.add(durationTimes);
        listofInputs.add(serves);
        listofInputs.add(ingredientName);
        listofInputs.add(ingredientQuantity);
        listofInputs.add(ingredientUnit);
        listofInputs.add(instructions);
    }

    public void inputField1() {
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
    }

    public void inputField2() {
        JLabel simpleingredients = new JLabel("\n" + " Ingredients:");
        simpleingredients.setFont(new Font("Serif", Font.HANGING_BASELINE, 18));
        bottomPadding.add(simpleingredients);
        bottomPadding.add(ingredientName);

        JLabel ingredientQuant = new JLabel("  Quantity:");
        ingredientQuant.setFont(new Font("Serif", Font.HANGING_BASELINE, 18));
        bottomPadding.add(ingredientQuant);
        bottomPadding.add(ingredientQuantity);

        JLabel ingredientU = new JLabel("  Units:");
        ingredientU.setFont(new Font("Serif", Font.HANGING_BASELINE, 18));
        bottomPadding.add(ingredientU);
        bottomPadding.add(ingredientUnit);

        JLabel simpleinstructions = new JLabel("  Instructions:");
        simpleinstructions.setFont(new Font("Serif", Font.HANGING_BASELINE, 18));
        bottomPadding.add(simpleinstructions);
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


// The AddListener class, allows the add button to add the recipe to the display
    class AddListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Recipe newRecipe = createRecipe();
            recipeBook.addRecipe(newRecipe);
            listModel.addElement(newRecipe.getName());
            displayRecipe.setText(newRecipe.toString());
            list.setSelectedIndex(listModel.size() - 1);
        }

        // EFFECTS: returns the recipe given by the user's input in the input fields
        public Recipe createRecipe() {
            String name = recipeName.getText();
            String inputDurationMinutes = durationTimes.getText();
            String inputServes = serves.getText();

            Recipe newRecipe = new Recipe(name, Integer.parseInt(inputDurationMinutes),
                    Integer.parseInt(inputServes), "");

            Instructions instructions1 = new Instructions();
            newRecipe.setInstructions(instructions1);
            return newRecipe;

        }
    }

    class AddIngredients implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                String name = ingredientName.getText();
                Double quant = Double.parseDouble(ingredientQuantity.getText());
                String unit = ingredientUnit.getText();
                Ingredient newIngredient = new Ingredient(name, quant, unit);
                Recipe r = recipeBook.getMyRecipes().get(list.getSelectedIndex());
                r.addIngredient(newIngredient);
                displayRecipe.setText(r.toString());
            } catch (NumberFormatException ex) {
                System.out.println("Wrong unit value");
            } catch (ArrayIndexOutOfBoundsException ex) {
                //Do nothing
            }
        }
    }

    class AddInstruction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                String name = instructions.getText();
                Recipe r = recipeBook.getMyRecipes().get(list.getSelectedIndex());
                r.getInstructions().addStep(name);
                displayRecipe.setText(r.toString());
            } catch (ArrayIndexOutOfBoundsException ex) {
                //Do nothing
            }
        }
    }

    class AutoSave implements ActionListener {

        // EFFECTS: saves the recipes of the recipebook
        @Override
        public void actionPerformed(ActionEvent e) {
            saveRecipes();
        }

        public void saveRecipes() {
            try {
                jsonWriter = new JsonWriter("./data/RecipeBook.json");
                jsonWriter.open();
                jsonWriter.write(recipeBook);
                jsonWriter.close();
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }

    class Load implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            loadingRecipe();
        }

        public void loadingRecipe() {
            try {
                jsonReader = new JsonReader("./data/RecipeBook.json");
                recipeBook = jsonReader.read();
                listModel.clear();
                for (Recipe recipe1: recipeBook.getMyRecipes()) {
                    listModel.addElement(recipe1.getName());
                }

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    class Remover implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (list.getSelectedIndex() < 0 || list.getSelectedIndex() >= listModel.size()) {
                return; //invalid index
            }
            int index = list.getSelectedIndex();
            Recipe r = recipeBook.getMyRecipes().get(list.getSelectedIndex());
            listModel.remove(index);
            recipeBook.removeRecipe(r);
            if (index == list.getSelectedIndex()) {
                list.setSelectedIndex(index - 1);
            } else {
                list.setSelectedIndex(index);
            }
        }
    }

    class UpdateDisplay implements ListSelectionListener {
        @Override
        public void valueChanged(ListSelectionEvent e) {
            Recipe r;
            try {
                int index = list.getSelectedIndex();
                r = recipeBook.getMyRecipes().get(index);
            } catch (ArrayIndexOutOfBoundsException ex) {
                if (listModel.size() == 0) {
                    r = recipeBook.getMyRecipes().get(listModel.size());
                } else {
                    r = recipeBook.getMyRecipes().get(listModel.size() - 1);
                }
            }
            if (listModel.size() == 0) {
                displayRecipe.setText("");
            } else {
                displayRecipe.setText(r.toString());
            }
        }
    }
}

