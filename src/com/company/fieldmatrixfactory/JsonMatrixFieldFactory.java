package com.company.fieldmatrixfactory;

import com.company.Board;
import com.company.Matrix;
import com.company.Vector2d.ConstVector2d;
import com.company.field.*;
import com.google.gson.Gson;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

/**
 * Creates Matrix of AbstractFields from json file.
 */
public class JsonMatrixFieldFactory implements IFieldMatrixFactory {
    private String filename_;

    /**
     *  Default constructor for class.
     *
     * @param filename file with json data
     */
    public JsonMatrixFieldFactory(String filename) {
        filename_ = filename;
    }

    /**
     * Helper class for working with gson module.
     */
    private static class Field {
        public int x;
        public int y;
        public String type;

        public Double target_x;
        public Double target_y;
    }

    /**
     * Helper class for working with gson module.
     */
    private static class JsonModel {
        public Field[] items;
        public int width;
        public int height;

    }

    /**
     * Creates a Matrix of Abstract fields from json file.
     *
     * @param board Board to which assign fields.
     * @return Matrix of AbstractField
     */
    @Override
    public Matrix<AbstractField> create(Board board) {
        Gson gson = new Gson();

        try (Reader reader = new FileReader(filename_)) {
            JsonModel jsonModel = gson.fromJson(reader, JsonModel.class);

            Matrix<AbstractField> result = new Matrix<AbstractField>(jsonModel.width, jsonModel.height);

            for (Field field : jsonModel.items) {
                AbstractField matrix_field = null;

                if (field.type.equals("None")) {
                    matrix_field = new WalkableField(board, false);
                } else if (field.type.equals("Wall")) {
                    matrix_field = new WallField(board);
                } else if (field.type.equals("Consumable")) {
                    matrix_field = new WalkableField(board, true);
                } else if (field.type.equals("Enemy")) {
                    matrix_field = new EnemyField(board);
                } else if (field.type.equals("Teleport")) {
                    matrix_field = new TeleportingField(board, new ConstVector2d(field.x + 0.5, field.y + 0.5), new ConstVector2d(field.target_x, field.target_y));
                }

                if (matrix_field == null) throw new AssertionError();

                result.set(field.y, field.x, matrix_field);
            }


            for (int y = 0; y < result.getHeight(); ++y) {
                for (int x = 0; x < result.getWidth(); ++x) {
                    if (result.get(y, x) == null) {
                        throw new RuntimeException("Wrong json ctor");
                    }
                }
            }

            return result;

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
