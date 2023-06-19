package JoaoPerera.persistence;

import java.io.*;

public class Archive {

    public static String readFile(String path){
        StringBuilder content = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line);
                content.append("\n");
            }
        }catch (IOException e) {
//            e.printStackTrace();
        }
        return content.toString();
    }
    public static void save(String filePath, String content) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, false))) {
            writer.write(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
