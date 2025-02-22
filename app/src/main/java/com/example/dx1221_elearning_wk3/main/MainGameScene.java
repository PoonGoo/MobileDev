package com.example.dx1221_elearning_wk3.main;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.media.MediaPlayer;
import android.os.Vibrator;
import android.util.Log;

import com.example.dx1221_elearning_wk3.R;
import com.example.dx1221_elearning_wk3.mgp2d.core.GameActivity;
import com.example.dx1221_elearning_wk3.mgp2d.core.GameEntity;
import com.example.dx1221_elearning_wk3.mgp2d.core.GameScene;
import com.example.dx1221_elearning_wk3.mgp2d.core.Vector2;

import java.util.Vector;

public class MainGameScene extends GameScene {
    private Bitmap _backgroundBitmap;
    private Bitmap _backgroundBitmap1;
    private float _backgroundPosition;
    public static int screenWidth;
    public static int screenHeight;

    public Bitmap leftArrow;

    public Bitmap rightArrow;

    Vector<GameEntity> _gameEntities = new Vector<>();
    private MediaPlayer _ringSound;
    private static MediaPlayer _bgMusic;
/*
    private Vibrator _vibrator;
*/

    private PlayerEntity player;

    private TrapManager trapManager;
    private TouchHandler touchHandler;

    private PuzzlesManager puzzlesManager;
    private float TimeBeforeTrapSpawn;
    private float TimeBeforePuzzleSpawn;


    private Bitmap[] _backgroundLayers = new Bitmap[6]; // Array for 6 layers
    private float[] _layerPositions = new float[6];     // Position for each layer
    private float[] _layerSpeeds = {50f, 100f, 150f, 200f, 250f, 300f}; // Speeds for parallax effect
    private float[] _baseLayerSpeeds = {50f, 100f, 150f, 200f, 250f, 300f}; // Speeds for parallax effect

    public static float WorldSpeed = 200f;

    public static float speedMultipler;

    private Bitmap homeIcon;
    private Vector2 homeIconPosition;

    private int score;
    private float scoreIncrementTimer;
    private final float scoreIncrementInterval = 0.1f;
    private final int scoreIncrementAmt = 1;


    @Override
    public void onCreate() {
        PowerupManager.getInstance().addShield(1);
        super.onCreate();
        screenHeight = GameActivity.instance.getResources().getDisplayMetrics().heightPixels;
        screenWidth = GameActivity.instance.getResources().getDisplayMetrics().widthPixels;

        TimeBeforeTrapSpawn = 3f;
        TimeBeforePuzzleSpawn = 5f;

        score = 0;
        scoreIncrementTimer = 0f;

        speedMultipler = 1;
        for(int i = 0; i < _layerSpeeds.length; i++)
        {
            _layerSpeeds[i] = 50 + i * 50;
        }

        WorldSpeed = 200f;


        int[] layerResources = {
                R.drawable.layer1,
                R.drawable.layer2,
                R.drawable.layer3,
                R.drawable.layer4,
                R.drawable.layer5,
                R.drawable.layer6
        };

        for (int i = 0; i < _backgroundLayers.length; i++) {
            Bitmap bmp = BitmapFactory.decodeResource(GameActivity.instance.getResources(), layerResources[i]);
            _backgroundLayers[i] = Bitmap.createScaledBitmap(bmp, screenWidth, screenHeight, true);
        }

        // Initialize positions
        for (int i = 0; i < _layerPositions.length; i++) {
            _layerPositions[i] = 0;
        }

        Bitmap lArrow  = BitmapFactory.decodeResource(GameActivity.instance.getResources(), R.drawable.left_button);
        Bitmap rArrow = BitmapFactory.decodeResource(GameActivity.instance.getResources(), R.drawable.right_button);

        Bitmap bmp = BitmapFactory.decodeResource(GameActivity.instance.getResources(), R.drawable.home_icon);
        homeIcon = Bitmap.createScaledBitmap(bmp, (int) (screenHeight * 0.1f), (int) (screenHeight * 0.1f), true);
        homeIconPosition = new Vector2(screenWidth * 0.02f, screenHeight * 0.02f); // Top-left position with some margin

         leftArrow = Bitmap.createScaledBitmap(lArrow, (int)(screenHeight * 0.2f) ,(int)(screenHeight * 0.2f),true);
         rightArrow = Bitmap.createScaledBitmap(rArrow, (int)(screenHeight * 0.2f) ,(int)(screenHeight * 0.2f),true);
         player = PlayerEntity.getInstance();
        _gameEntities.add(new BackgroundEntity());
        _gameEntities.add(player);
        _gameEntities.add(new MovementButton(leftArrow, new Vector2(screenWidth * 0.1f, screenHeight * 0.7f), MovementButton.MovementType.LEFT));
        _gameEntities.add(new MovementButton(rightArrow, new Vector2(screenWidth * 0.2f, screenHeight * 0.7f), MovementButton.MovementType.RIGHT));
        trapManager = TrapManager.getInstance();
        touchHandler = TouchHandler.getInstance();


        puzzlesManager = PuzzlesManager.getInstance();
        _gameEntities.add(touchHandler);

/*        int musicVolume = SharedPrefManager.getInstance().readFromSharedPreferences(GameActivity.instance, "settings", "music_volume");
        int soundVolume = SharedPrefManager.getInstance().readFromSharedPreferences(GameActivity.instance, "settings", "sound_volume");

        _bgMusic = MediaPlayer.create(GameActivity.instance.getApplicationContext(), R.raw.main_game_music);
        _bgMusic.setLooping(true);
        _bgMusic.setVolume(musicVolume / 100f, musicVolume / 100f);
        _bgMusic.start();*/
    }

    void ResetVariables()
    {
        TimeBeforeTrapSpawn = 3f;
        TimeBeforePuzzleSpawn = 5f;

        score = 0;
        scoreIncrementTimer = 0f;

        speedMultipler = 1;
        for(int i = 0; i < _layerSpeeds.length; i++)
        {
            _layerSpeeds[i] = 50 + i * 50;
        }

        WorldSpeed = 200f;

        PlayerEntity.getInstance().Reset();
        TrapManager.getInstance().clear();
        PuzzlesManager.getInstance().Clear();
    }
    @Override
    public void onUpdate(float dt)
    {
        if(!puzzlesManager.playingPuzzle())
        {
            speedMultipler +=  dt * 0.03f;

            if(speedMultipler >= 10f)
            {
                speedMultipler = 10f;
            }
            Log.d("Speed Multiplier", " " + speedMultipler);
            WorldSpeed = _layerSpeeds[3];
            HandleBackground(dt);

            scoreIncrementTimer += dt;
            if (scoreIncrementTimer >= scoreIncrementInterval) {
                score += scoreIncrementAmt;
                scoreIncrementTimer -= scoreIncrementInterval;
            }

            for(GameEntity entity : _gameEntities)
            {
                entity.onUpdate(dt);
                if(entity instanceof TouchHandler)
                {
                    for(GameEntity other : _gameEntities)
                    {
                        if(other instanceof MovementButton && entity.isColliding(other))
                        {
                            Log.d("MovementButton", "Movement Button Pressed");
                            if(((TouchHandler) entity).Pressed())
                            {
                                ((MovementButton) other).Move(dt,player);
                            }
                        }

                    }

                    if((((TouchHandler) entity).Pressed() || ((TouchHandler) entity).SecondPressed()) && (((TouchHandler) entity).SecondTouchPos.x >= screenWidth / 2f))
                    {
                        Log.d("Fly", "FlyPressed");
                        player.Fly(dt);
                    }
                    else
                    {
                        player.EndFly();
                    }


                }
            }
            HandleTrapManger(dt);

            if(TimeBeforePuzzleSpawn >= 0)
            {
                TimeBeforePuzzleSpawn-= dt;
            }
            else
            {
                TimeBeforePuzzleSpawn = 10;
                puzzlesManager.StartPuzzle();
            }

        }
        else
        {
            puzzlesManager.onUpdate(dt);
            TouchHandler.getInstance().onUpdate(dt);

        }

        for (GameEntity entity : _gameEntities)
        {
            if (entity instanceof TouchHandler)
            {
                if (((TouchHandler) entity).Pressed() && entity.isColliding(homeIconPosition, homeIcon.getWidth(), homeIcon.getHeight()))
                {
                    Log.d("Dialogue Handler", "Pressed");

                    if (!HomeDialog.isShowing())
                    {
                        HomeDialog homeDialog = new HomeDialog();
                        homeDialog.show(GameActivity.instance.getFragmentManager(), "HomeDialog");
                    }
                }
            }
        }

        if(PlayerEntity.getInstance().isDead())
        {
            StopBackgroundMusic();

            int soundVolume = SharedPrefManager.getInstance().readFromSharedPreferences(GameActivity.instance, "settings", "sound_volume");
            float volume = soundVolume / 100f;

//            if (_bgMusic != null)
//            {
//                _bgMusic.stop();
//                _bgMusic.release();
//                _bgMusic = null;
//            }

            MediaPlayer loseGameSound = MediaPlayer.create(GameActivity.instance.getApplicationContext(), R.raw.lose_game_sound);
            loseGameSound.setVolume(volume, volume);

            loseGameSound.setOnCompletionListener(MediaPlayer::release);
            loseGameSound.start();

            Intent loseMenuIntent = new Intent(GameActivity.instance, LoseMenu.class);
            loseMenuIntent.putExtra("finalScore", score);
            GameActivity.instance.startActivity(loseMenuIntent);

            // Reset variables after intent
            ResetVariables();
            GameScene.exitCurrent();

        }
    }

    public static void StopBackgroundMusic() {
        if (_bgMusic != null) {
            if (_bgMusic.isPlaying()) {
                _bgMusic.stop();
            }
            _bgMusic.release();
            _bgMusic = null;
        }
    }

    private void HandleTrapManger(float dt)
    {
        trapManager.onUpdate(dt);
        trapManager.handleCollision(player);
        if(TimeBeforeTrapSpawn >= 0f)
        {
            TimeBeforeTrapSpawn -= dt;
        }
        else
        {
            TimeBeforeTrapSpawn = 3f;
            trapManager.spawnTrap(player);

        }
    }

    @Override
    public void onEnter() {
     /*   super.onEnter();
        int musicVolume = SharedPrefManager.getInstance().readFromSharedPreferences(GameActivity.instance, "settings", "music_volume");
        int soundVolume = SharedPrefManager.getInstance().readFromSharedPreferences(GameActivity.instance, "settings", "sound_volume");

        if(_bgMusic == null)
        {
            _bgMusic = MediaPlayer.create(GameActivity.instance.getApplicationContext(), R.raw.main_game_music);
            _bgMusic.setLooping(true);
            _bgMusic.setVolume(musicVolume / 100f, musicVolume / 100f);
            _bgMusic.start();
        }*/

        super.onEnter();
        int musicVolume = SharedPrefManager.getInstance().readFromSharedPreferences(GameActivity.instance, "settings", "music_volume");
        ResetVariables();

        _bgMusic = MediaPlayer.create(GameActivity.instance.getApplicationContext(), R.raw.main_game_music);
        _bgMusic.setLooping(true);
        _bgMusic.setVolume(musicVolume / 100f, musicVolume / 100f);
        _bgMusic.start();
    }

    private void HandleBackground(float dt)
    {
        for (int i = 0; i < _backgroundLayers.length; i++)
        {
            _layerSpeeds[i] =  _baseLayerSpeeds[i] * speedMultipler;
            _layerPositions[i] -= _layerSpeeds[i] * dt;
            if (_layerPositions[i] <= -screenWidth) {
                _layerPositions[i] += screenWidth;
            }
        }
    }



    @Override
    public void onRender(Canvas canvas) {

        // Draw background layers
        for (int i = 0; i < _backgroundLayers.length; i++) {
            canvas.drawBitmap(_backgroundLayers[i], _layerPositions[i], 0, null);
            canvas.drawBitmap(_backgroundLayers[i], _layerPositions[i] + screenWidth, 0, null);
        }

        if(!puzzlesManager.playingPuzzle())
        {
            for(GameEntity entity : _gameEntities)
            {
                entity.onRender(canvas);
            }
            trapManager.onRender(canvas);
            PowerupManager.getInstance().onRender(canvas);

        }
        else
        {
            puzzlesManager.onRender(canvas);
        }

        canvas.drawBitmap(homeIcon, homeIconPosition.x, homeIconPosition.y, null);

        Paint paint = new Paint();
        paint.setColor(Color.WHITE);
        paint.setTextSize(50); // Adjust size as needed
        paint.setTextAlign(Paint.Align.RIGHT);
        canvas.drawText("Score: " + score, screenWidth - 20, 60, paint);

    }
}
