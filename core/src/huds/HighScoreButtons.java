package huds;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.bksapps.jackthegiant.GameMain;

import helpers.GameInfo;
import scenes.MainMenu;

/**
 * Created by TERRORMASTER on 3/10/2018.
 */

public class HighScoreButtons {

    private GameMain game;
    private Stage stage;
    private Viewport gameViewport;
    private ImageButton backBtn;
    private Label scoreLabel, coinLabel;

    public HighScoreButtons(GameMain game){
        this.game=game;
        gameViewport= new FitViewport(GameInfo.WIDTH, GameInfo.HEIGHT, new OrthographicCamera());
        stage= new Stage(gameViewport, game.getBatch());
        Gdx.input.setInputProcessor(stage);
        createAndPoisitonUIElements();
        stage.addActor(backBtn);
        stage.addActor(scoreLabel);
        stage.addActor(coinLabel);

    }

    void createAndPoisitonUIElements(){
        backBtn= new ImageButton(new SpriteDrawable(new Sprite(new Texture("Buttons/Options Menu/Back.png"))));
        backBtn.setPosition(17, 17, Align.bottomLeft);

        FreeTypeFontGenerator generator=
                new FreeTypeFontGenerator(Gdx.files.internal("Fonts/blow.ttf"));

        FreeTypeFontGenerator.FreeTypeFontParameter parameter=
                new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size=40;
        BitmapFont font= generator.generateFont(parameter);

        scoreLabel= new Label("100", new Label.LabelStyle(font, Color.WHITE));
        coinLabel= new Label("100", new Label.LabelStyle(font, Color.WHITE));

        scoreLabel.setPosition((GameInfo.WIDTH/2f)-(scoreLabel.getWidth()/2f), GameInfo.HEIGHT/2-120);
        coinLabel.setPosition((GameInfo.WIDTH/2f)-(coinLabel.getWidth()/2f), GameInfo.HEIGHT/2-215);

        backBtn.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(new MainMenu(game));
            }
        });

    }

    public Stage getStage() {
        return stage;
    }
}// HighScoreButtons
