package com.company;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Class that manages highscores.
 */
public class HighscoreManager {
    private final Map<String, Integer> levelNameToHighscore_;
    private final String filename_;

    private static class Highscore {
        public String levelName;
        public int score;

        public static class Builder {
            private final Highscore highscore_;

            public Builder() {
                highscore_ = new Highscore();
            }

            public Builder setLevelName(String levelName) {
                highscore_.levelName = levelName;
                return this;
            }

            public Builder setScore(int score) {
                highscore_.score = score;
                return this;
            }

            public Highscore get() {
                return highscore_;
            }
        }
    }

    private static class JsonModel {
        public ArrayList<Highscore> items  = new ArrayList<>();
    }

    /**
     * Default constructor.
     *
     * @param filename file that highscores will be saved to
     */
    public HighscoreManager(String filename) {
        levelNameToHighscore_ = new HashMap<String, Integer>();
        filename_ = filename;

        Gson gson = new Gson();
        try (Reader reader = new FileReader(filename)) {
            JsonModel jsonModel = gson.fromJson(reader, JsonModel.class);

            for (Highscore highscore : jsonModel.items) {
                levelNameToHighscore_.put(highscore.levelName, highscore.score);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Getter for highscore for specific level.
     *
     * @param levelName filename of a level
     * @return Highscore for 0 if recorded, 0 otherwise
     */
    public int get(String levelName) {
        return levelNameToHighscore_.getOrDefault(levelName, 0);
    }

    /**
     * Setter for highscore for specific level.
     *
     * @param levelName filename of a leven
     * @param score score to set
     */
    public void put(String levelName, int score) {
        levelNameToHighscore_.put(levelName, score);
    }

    /**
     * Saves current state of highscore manager to a file.
     */
    public void saveToFile() {
        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();

        Gson gson = builder.create();

        JsonModel jsonModel = new JsonModel();
        levelNameToHighscore_.forEach((levelName, score) -> {
            jsonModel.items.add(new Highscore.Builder().setLevelName(levelName).setScore(score).get());
        });

        try (Writer writer = new FileWriter(filename_)) {
            writer.write(gson.toJson(jsonModel));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
