package huds;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.RunnableAction;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.bksapps.jackthegiant.GameMain;

import helpers.GameInfo;
import helpers.GameManager;
import scenes.GamePlay;
import scenes.HighScore;
import scenes.OptionMenu;

/**
 * Created by TERRORMASTER on 3/10/2018.
 */

public class MainMenuButtons {

    private GameMain game;
    private Stage stage;
    private Viewport gameViewport;

    private ImageButton playBtn;
    private ImageButton highScoreBtn;
    private ImageButton optionsBtn;
    private ImageButton quitBtn;
    private ImageButton musicBtn;

    public MainMenuButtons(GameMain game){
        this.game=game;
        gameViewport= new FitViewport(GameInfo.WIDTH, GameInfo.HEIGHT,
                new OrthographicCamera());
        stage= new Stage(gameViewport, game.getBatch());
        Gdx.input.setInputProcessor(stage);
        createAndPositionButton();
        addAllListeners();
        stage.addActor(playBtn);
        stage.addActor(highScoreBtn);
        stage.addActor(optionsBtn);
        stage.addActor(quitBtn);
        stage.addActor(musicBtn);

    }

    void createAndPositionButton(){

        playBtn= new ImageButton(new SpriteDrawable(new Sprite(
                new Texture("Buttons/Main Menu/Start Game.png"))));

        highScoreBtn= new ImageButton(new SpriteDrawable(new Sprite(
                new Texture("Buttons/Main Menu/Highscore.png"))));

        optionsBtn= new ImageButton(new SpriteDrawable(new Sprite(
                new Texture("Buttons/Main Menu/Options.png"))));

        quitBtn= new ImageButton(new SpriteDrawable(new Sprite(
                new Texture("Buttons/Main Menu/Quit.png"))));

        musicBtn= new ImageButton(new SpriteDrawable(new Sprite(
                new Texture("Buttons/Main Menu/Music On.png"))));

        playBtn.setPosition(GameInfo.WIDTH/2-80, GameInfo.HEIGHT/2+50, Align.center);
        highScoreBtn.setPosition(GameInfo.WIDTH/2-60, GameInfo.HEIGHT/2-20, Align.center);
        optionsBtn.setPosition(GameInfo.WIDTH/2-40, GameInfo.HEIGHT/2-90, Align.center);
        quitBtn.setPosition(GameInfo.WIDTH/2-20, GameInfo.HEIGHT/2-160, Align.center);
        musicBtn.setPosition(GameInfo.WIDTH-13, 13, Align.bottomRight);

    }

    void addAllListeners(){
        playBtn.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                // Codes will be executed when we press the buttons
                GameManager.getInstance().gameStartedFromMainMenu=true;
                RunnableAction run= new RunnableAction();
                run.setRunnable(new Runnable() {
                    @Override
                    public void run() {
                        game.setScreen(new GamePlay(game));
                    }
                });

                SequenceAction sequenceAction= new SequenceAction();
                sequenceAction.addAction(Actions.fadeOut(1f));
                sequenceAction.addAction(run);
                stage.addAction(sequenceAction);

            }
        });

        highScoreBtn.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(new HighScore(game));
            }
        });

        optionsBtn.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(new OptionMenu(game));
            }
        });

        quitBtn.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                System.out.println("Quit Buttons was pressed");
            }
        });

        musicBtn.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                // Codes will be executed when we press the buttons
                System.out.println("Music Buttons was pressed");
            }
        });
    }

    public Stage getStage() {
        return stage;
    }



}//MainMenuButtons
