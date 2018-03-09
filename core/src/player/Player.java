package player;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

import helpers.GameInfo;

/**
 * Created by TERRORMASTER on 3/9/2018.
 */

public class Player extends Sprite{

    private World world;
    private Body body;

    public Player(World world, float X, float Y){
        super(new Texture("Players/Player 1.png"));
        this.world=world;
        setPosition(X, Y);
        createBody();
    }

    void createBody(){
        BodyDef bodyDef= new BodyDef();
        bodyDef.type= BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(getX()/GameInfo.PPM, getY()/GameInfo.PPM);

        body= world.createBody(bodyDef);
        body.setFixedRotation(true);
        PolygonShape shape= new PolygonShape();
        shape.setAsBox((getWidth()/2f-20)/GameInfo.PPM, (getHeight()/2f)/GameInfo.PPM);

        FixtureDef fixtureDef= new FixtureDef();
        fixtureDef.density=4f; //mass of the player
        fixtureDef.shape=shape;
        fixtureDef.friction=2f; //will make player not slide on surface
        Fixture fixture= body.createFixture(fixtureDef);
        fixture.setUserData("Player");
        shape.dispose();

    }

    public void movePlayer(float x){
        body.setLinearVelocity(x, body.getLinearVelocity().y);
    }

    public void updatePlayer(){
        this.setPosition(body.getPosition().x*GameInfo.PPM, body.getPosition().y*GameInfo.PPM);
    }

    public void drawPlayer(SpriteBatch batch){
        batch.draw(this, getX()+getWidth()/2f-5, getY()-getHeight()/2f);
    }

}//Player
