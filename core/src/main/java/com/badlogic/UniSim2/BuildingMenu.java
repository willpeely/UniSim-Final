package com.badlogic.UniSim2;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Array;

public class BuildingMenu {

    private Stage stage;
    private Buildings buildings;
    private Image menuBar;

    private final Skin skin;

    // Holds the count of each type of building
    private int accomodationCount, lectureHallCount, libraryCount, courseCount, foodZoneCount, recreationalCount, natureCount;
    private int[] buildingCounts;

    // Holds the labels that display the count of each building
    private Array<Label> countLabels;
    
    public BuildingMenu(Stage stage, Buildings buildings){
        this.stage = stage;
        Gdx.input.setInputProcessor(stage);
        this.buildings = buildings;

        skin = new Skin(Gdx.files.internal("ui/uiskin.json"));

        // Initializes each building count to 0
        accomodationCount = lectureHallCount = libraryCount = courseCount = foodZoneCount = recreationalCount = natureCount = 0;

        // Initializes buildingCounts with each building type 
        buildingCounts = new int[]
        {
            accomodationCount,
            lectureHallCount,
            libraryCount,
            courseCount,
            foodZoneCount,
            recreationalCount,
            natureCount
        };

        countLabels = new Array<>();
    }

    public void createBuildingMenu(){
        createMenuBar();
        createImageButtons();
        createCountLabels();
    }

    // Creates a button for each building type with a gap between them and a count label
    private void createImageButtons() {
        int buttonGap = Consts.BUILDING_BUTTON_GAP;

        // Iterating through each type of building
        for(Building.BuildingTypes type : Building.BuildingTypes.values()){

            createImageButton(type, buttonGap);
            buttonGap += Consts.BUILDING_BUTTON_GAP; 
        }
    }

    private void createImageButton(Building.BuildingTypes type, int buttonGap){

        int index = type.ordinal(); // Gets the index of type within BuildingTypes
        ImageButton button = setupImageButton(index, buttonGap); // Creates a button of the building type
        addImageButtonClick(button, type, index); // Adds a click listener to the button so we can do something when clicked     
        stage.addActor(button);
    }

    // Sets the texture, the building type, size and position of the button
    private ImageButton setupImageButton(int index, int buttonGap){

        Texture buttonUpTexture = Assets.buttonUpTextures[index]; // Texture when not hovering or clicking
        Texture buttonDownTexture = Assets.buttonDownTextures[index]; // Texture when hovering or clicking
        Drawable buttonUpDrawable = new TextureRegionDrawable(buttonUpTexture);
        Drawable buttonDownDrawable = new TextureRegionDrawable(buttonDownTexture);
        ImageButton.ImageButtonStyle buttonStyle = new ImageButton.ImageButtonStyle();

        buttonStyle.up = buttonUpDrawable;
        buttonStyle.down = buttonDownDrawable;
        buttonStyle.over = buttonDownDrawable;

        ImageButton button = new ImageButton(buttonStyle); // Creates a new button with the correct building button textures

        button.setSize(Consts.BUILDING_BUTTON_WIDTH, Consts.BUILDING_BUTTON_HEIGHT);
        button.setPosition(Consts.BUILDING_BUTTON_X_BOUNDARY, Consts.BUILDING_BUTTON_Y_BOUNDARY - buttonGap); // Places the button with a gap

        setUpCountLabel(index, button); // Sets up a count label for the button to count how many times the buildint type is placed

        return button;
    }

    // Adds a click listener so it can handle being clicked
    private void addImageButtonClick(ImageButton button, Building.BuildingTypes type, int index){
        button.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y){

                // Prevents clicking a button while already selecting a building
                if(!buildings.getCurrentlySelecting()){
                    Assets.click.play();
                    buildings.handleSelection(type); // Creates a building of whatever type the button pressed is
                    updateCountLabel(index); // Incriments the building count label by 1 and displays
                }
            }
        });
    }

    // One is created for each button to show how many of each building there are
    private void setUpCountLabel(int index, ImageButton button){

        int count = buildingCounts[index]; // Gets the count for the type of building using the type index in BuildingTypes
        Label countLabel = new Label(String.valueOf(count), skin);

        // Sets position of label to the top right of the button
        float x = button.getX() + button.getWidth(); 
        float y = button.getY() + button.getHeight();
        countLabel.setPosition(x, y);

        countLabel.setColor(Consts.COUNT_COLOR);
        countLabel.setFontScale(Consts.COUNT_SIZE);
        countLabels.add(countLabel);
    }

    // Incriments a count label by 1 at an index that references a building type
    private void updateCountLabel(int index){
        buildingCounts[index]++;
        int newCount = buildingCounts[index];
        countLabels.get(index).setText(String.valueOf(newCount));
    }

    // Adds the count labels to the stage
    private void createCountLabels(){
        for(Label countLabel : countLabels){
            stage.addActor(countLabel);
        }
    }

    private void createMenuBar(){
        menuBar = new Image(Assets.menuBarTexture);
        menuBar.setSize(Consts.MENU_BAR_WIDTH, Consts.MENU_BAR_HEIGHT);
        menuBar.setPosition(Consts.MENU_BAR_X, Consts.MENU_BAR_Y);
        stage.addActor(menuBar);
    }

    public void render(){
        stage.act(Gdx.graphics.getDeltaTime());
    }

    public void drawBuildingMenu(){
        stage.draw();
    }

    public void dispose(){
        stage.dispose();
    } 
}

    