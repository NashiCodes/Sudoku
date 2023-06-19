package JoaoPerera.persistence;

import JoaoPerera.model.Board;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.lang.reflect.Type;


public class BoardPersistence implements Persistence<Board> {

    private static final String PATH = DIRECTORY + File.separator + "solutions.json";

    @Override
    public void save(int[][] board) {
        Gson gson = new Gson();
        String json = gson.toJson(board);

        File dir = new File(DIRECTORY);
        if (!dir.exists())
            dir.mkdirs();

        Archive.save(PATH, json);
    }

    @Override
    public Board load() {
        Gson gson = new Gson();
        String json = Archive.readFile(PATH);

        Board board = new Board();
        if (!json.trim().isEmpty()) {
            Type type = new TypeToken<Board>() {
            }.getType();
            board = gson.fromJson(json, type);
            if (board == null) board = new Board();
        }
        return board;
    }
}
