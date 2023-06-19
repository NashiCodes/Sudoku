package JoaoPerera.persistence;

public interface Persistence<T> {
    String DIRECTORY = "data";
    void save(int[][] board);

    T load();
}
