package helpers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Json;

/**
 * Created by TERRORMASTER on 3/12/2018.
 */

public class GameManager {

    private static final GameManager ourInstance = new GameManager();

    public GameData gameData;
    private Json json= new Json();
    private FileHandle fileHandle= Gdx.files.local("bin/GameData.json");


    public boolean gameStartedFromMainMenu, isPaused=true;
    public int lifeScore, coinScore, score;


    private GameManager() {

    }

    public void initializeGameData(){

        if(!fileHandle.exists()){

            gameData= new GameData();

            gameData.setHighScore(0);
            gameData.setCoinHighScore(0);

            gameData.setEasyDifficulty(false);
            gameData.setMediumDifficulty(true);
            gameData.setHardDifficulty(false);

            gameData.setMusicOn(true);

            saveData();
        }else{
            loadData();
        }
    }

    public void saveData(){
        if(gameData!=null){
            fileHandle.writeString(json.prettyPrint(gameData), false);
        }
    }

    public void loadData(){
        gameData=json.fromJson(GameData.class, fileHandle.readString());

    }


    public static GameManager getInstance() {
        return ourInstance;
    }


}// Game Manager
