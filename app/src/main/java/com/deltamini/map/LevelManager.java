package com.deltamini.map;

import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import com.google.gson.reflect.TypeToken;
import java.awt.Graphics2D;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Constructor;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Map;
import java.util.Map.Entry;

import com.deltamini.entity.*;
import com.deltamini.main.GamePanel;

/*
- loading maps
- changing maps (load new map and remove old map from memory)
- update tiles and map entities
- draw tiles and map entities
*/

public class LevelManager {
    private final GamePanel panel;
    private Level curLevel;
    private final ArrayList<Entity> entities = new ArrayList<Entity>();

    public LevelManager(GamePanel panel) {
        this.panel = panel;
    }

    public void loadLevel(String name, int width, int height) {
        this.curLevel = new Level(this.panel, name, width, height, panel.tileSize);
        loadEntities(name);
    }

    public void loadLevel(Level level) {
        this.curLevel = level;
        loadEntities(level.name);
    }

    public void loadEntities(String name) {
        Gson gson = new Gson();
        this.entities.clear();

        try (InputStream is = getClass().getClassLoader().getResourceAsStream("assets/maps/" + name + "/entities.json")) {
            
            if (is == null) {
                System.err.println("Error: File not found in assets folder!");
                return;
            }

            Type mapType = new TypeToken<Map<String, Object>>() {}.getType();

            try (Reader reader = new InputStreamReader(is, StandardCharsets.UTF_8)) {
                Map<String, Object> map = gson.fromJson(reader, mapType);
                Class<?> entityClass;
                LinkedTreeMap<?, ?> entityMap;
                
                for (Entry<String, Object> entity : map.entrySet()) {
                    entityMap = (LinkedTreeMap<?, ?>) entity.getValue();
                    entityClass = Class.forName("com.deltamini.entity." + entityMap.get("class").toString());
                    Constructor<?> entityConstructor = entityClass.getDeclaredConstructor(LinkedTreeMap.class, GamePanel.class);
                    this.entities.add((Entity) entityConstructor.newInstance(entityMap, this.panel));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void update() {
        curLevel.update();
        for (Entity entity : this.entities) {
            entity.update();
        }
    }

    public void draw(Graphics2D g2d) {
        this.curLevel.draw(g2d);
        for (Entity entity : this.entities) {
            entity.draw(g2d);
        }
    }
}