package com.example.dx1221_elearning_wk3.main;

import android.content.Entity;

import com.example.dx1221_elearning_wk3.mgp2d.core.GameEntity;
import com.example.dx1221_elearning_wk3.mgp2d.core.Vector2;

import java.security.Policy;
import java.util.ArrayList;
import java.util.Objects;

public class ObjectPoolManager
{
    private static ObjectPoolManager instance = null;

    public static ArrayList<PooledObjects> EntityPool = new ArrayList<>();

    public static synchronized ObjectPoolManager getInstance()
    {
        if(instance == null)
            instance = new ObjectPoolManager();

        return instance;
    }

    public static GameEntity SpawnObject(GameEntity entity, Vector2 SpawnPos) throws CloneNotSupportedException {

        PooledObjects pool = null;
        for(int i = 0; i < EntityPool.size(); i++)
        {
            PooledObjects p = EntityPool.get(i);
            if(Objects.equals(p.LookUpName, entity.name))
            {
                pool = p;
                break;
            }

        }

        if(pool == null)
        {
            pool = new PooledObjects();
            pool.LookUpName = entity.name;
            EntityPool.add(pool);
        }

        GameEntity spawnableObj = null;
        for(int j = 0; j < pool.InactiveObjects.size(); j++)
        {
            GameEntity go  = pool.InactiveObjects.get(j);
            if(go == null)
            {
                spawnableObj = entity.Instantiate();
            }
            else
            {
                spawnableObj = go;
                break;
            }
        }

        if(spawnableObj != null)
        {
            spawnableObj.setPosition(SpawnPos) ;
            pool.InactiveObjects.remove(spawnableObj);
            spawnableObj.isActive = true;
        }

        return spawnableObj;
    }

    public static void ReturnObjectToPool(GameEntity entity)
    {
        PooledObjects pool = null;
        for(int i = 0; i < EntityPool.size(); i++)
        {
            PooledObjects p = EntityPool.get(i);
            if(Objects.equals(p.LookUpName, entity.name))
            {
                pool = p;
                break;
            }

        }

        if(pool == null) return;
        else
        {
            entity.isActive = false;
            pool.InactiveObjects.add(entity);
        }
    }


}

