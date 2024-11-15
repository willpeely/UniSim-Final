package com.badlogic.UniSim2.GUImanager;

import com.badlogic.UniSim2.Main;
import com.badlogic.UniSim2.resources.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.StretchViewport;

/**
 * This is the screen that shows when the game starts. It holds a start game
 * button which, when pressed, will start the game.
 */
public class StartScreen implements Screen {

    private Main game;
    private StretchViewport viewport;
    private Stage stage;
    private ImageButton startButton;

    public StartScreen(Main game){
        this.game = game;
        viewport = game.getViewport();
        stage = new Stage(viewport);
        addStartButton();
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
    }

    /**
     * Adds a start button to the menu.
     */
    private void addStartButton(){
        setupStartButton(); // Initializes startButton with the correct textures, size, and position
        addStartButtonClick(); // Adds a click listener to start button 
    }

    // Initializes startButton 
    private void setupStartButton(){

        // Setting up the textures
        Drawable startButtonUpDrawable = new TextureRegionDrawable(Assets.startButtonUpTexture); // Texture when not hovering or clicking
        Drawable startButtonDownDrawable = new TextureRegionDrawable(Assets.startButtonDownTexture); // Texture when hovering or clicking
        ImageButton.ImageButtonStyle startButtonStyle = new ImageButton.ImageButtonStyle();
        startButtonStyle.up = startButtonUpDrawable;
        startButtonStyle.down = startButtonDownDrawable;
        startButtonStyle.over = startButtonDownDrawable;

        // Initializing startButton and setting its size and position
        startButton = new ImageButton(startButtonStyle);
        startButton.setSize(Consts.START_BUTTON_WIDTH, Consts.START_BUTTON_HEIGHT);
        startButton.setPosition(Consts.START_BUTTON_X, Consts.START_BUTTON_Y);
    }

    /**
     * Ensures that when the start button is pressed, the game is played.
     */
    private void addStartButtonClick(){
        startButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y){
                game.startGame();
                dispose();
            }
        });
        stage.addActor(startButton);
    }

    // Draws the background of the start menu
    private void drawBackground(){
        SpriteBatch spriteBatch = new SpriteBatch();
        ScreenUtils.clear(Consts.BACKGROUND_COLOR);
        viewport.apply();
        spriteBatch.setProjectionMatrix(viewport.getCamera().combined);
        spriteBatch.begin();
        spriteBatch.draw(Assets.startBackgroundTexture, 0, 0, Consts.WORLD_WIDTH, Consts.WORLD_HEIGHT);
        spriteBatch.end();
        
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(Consts.BACKGROUND_COLOR);
        drawBackground();
        stage.act(delta);
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void hide() {
    }

    @Override
    public void dispose() {
    }
}