package com.example.dx1221_elearning_wk3.main;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.Log;

import com.example.dx1221_elearning_wk3.R;
import com.example.dx1221_elearning_wk3.mgp2d.core.GameActivity;
import com.example.dx1221_elearning_wk3.mgp2d.core.GameEntity;
import com.example.dx1221_elearning_wk3.mgp2d.core.Vector2;

import java.util.ArrayList;

public class TrapManager extends GameEntity {

    enum TrapType {
        LIGHTNING,
        ARROW,
        COCONUT,
    }

    private static TrapManager instance = null;

    private ObjectPool<ArrowTrap> arrowTrapPool;
    private ObjectPool<CoconutTrap> coconutTrapPool;
    private ObjectPool<LightningTrap> lightningTrapPool;

    private ArrayList<Traps> activeTraps;

    private Bitmap arrowAsset;
    private Bitmap lightningAsset;
    private Bitmap coconutAsset;

    private int gameWidth;
    private int gameHeight;

    private TrapManager() {
        activeTraps = new ArrayList<>();

        // Initialize trap assets
        Bitmap arrowBmp = BitmapFactory.decodeResource(GameActivity.instance.getResources(), R.drawable.arrow);
        Bitmap coconutBmp = BitmapFactory.decodeResource(GameActivity.instance.getResources(), R.drawable.coconut1);
        Bitmap lightningBmp = BitmapFactory.decodeResource(GameActivity.instance.getResources(), R.drawable.lightning_trap_sliced);

        coconutAsset = Bitmap.createScaledBitmap(coconutBmp, 100, 100, true);
        arrowAsset = Bitmap.createScaledBitmap(arrowBmp, arrowBmp.getWidth() * 2, arrowBmp.getHeight() * 2, true);
        lightningAsset = lightningBmp;

        gameHeight = GameActivity.instance.getResources().getDisplayMetrics().heightPixels;
        gameWidth = GameActivity.instance.getResources().getDisplayMetrics().widthPixels;

        // Initialize object pools with a starting size of 5
        arrowTrapPool = new ObjectPool<>(() -> new ArrowTrap(arrowAsset), 5);
        coconutTrapPool = new ObjectPool<>(() -> new CoconutTrap(coconutAsset), 5);
        lightningTrapPool = new ObjectPool<>(() -> new LightningTrap(lightningAsset), 5);
    }

    public static synchronized TrapManager getInstance() {
        if (instance == null)
            instance = new TrapManager();

        return instance;
    }

    @Override
    public void onUpdate(float dt) {
        for (int i = 0; i < activeTraps.size(); i++) {
            Log.d("Active Traps:", "" + i + activeTraps.get(i));
            activeTraps.get(i).DoEffect(dt);
        }
    }

    public void spawnTrap(PlayerEntity player) {
        int randomTrap = (int) (Math.random() * TrapType.values().length);
        Log.d("Spawn Trap", "" + TrapType.values()[randomTrap]);

        Traps trap = null;
        switch (TrapType.values()[randomTrap]) {
            case ARROW:
                trap = arrowTrapPool.acquire();
                trap.Spawn(new Vector2(gameWidth, player.getPosition().y));
                break;

            case COCONUT:
                trap = coconutTrapPool.acquire();
                trap.Spawn(new Vector2(player.getPosition().x, -coconutAsset.getHeight()));
                break;

            case LIGHTNING:
                trap = lightningTrapPool.acquire();
                float posHeight = (float) (Math.random() * gameHeight);
                if (posHeight - lightningAsset.getHeight() <= 0) {
                    posHeight = 0f + lightningAsset.getHeight();
                } else if (posHeight + lightningAsset.getHeight() >= gameHeight) {
                    posHeight = gameHeight - lightningAsset.getHeight();
                }

                trap.Spawn(new Vector2((float) gameWidth, posHeight));
                break;
        }

        if (trap != null) {
            activeTraps.add(trap);
        }
    }

    public void DisableTrap(Traps trap) {
        activeTraps.remove(trap);

        if (trap instanceof ArrowTrap) {
            arrowTrapPool.release((ArrowTrap) trap);
        } else if (trap instanceof CoconutTrap) {
            coconutTrapPool.release((CoconutTrap) trap);
        } else if (trap instanceof LightningTrap) {
            lightningTrapPool.release((LightningTrap) trap);
        }

        trap.reset();
    }

    public void handleCollision(PlayerEntity player) {
        for (int i = 0; i < activeTraps.size(); i++) {
            if (activeTraps.get(i).isColliding(player)) {
                activeTraps.get(i).DoCollision(player);
                DisableTrap(activeTraps.get(i));
            }
        }
    }

    @Override
    public void onRender(Canvas canvas) {
        for (int i = 0; i < activeTraps.size(); i++) {
            activeTraps.get(i).onRender(canvas);
        }
    }

    public void clear() {
        for (Traps trap : activeTraps) {
            DisableTrap(trap);
        }
        activeTraps.clear();
    }
}
