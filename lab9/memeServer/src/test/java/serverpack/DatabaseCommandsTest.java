package serverpack;

import org.junit.Test;

import java.io.File;
import java.util.ArrayList;

import static junit.framework.TestCase.assertTrue;

public class DatabaseCommandsTest {

    @Test
    public void registerSingleClientTest() {
        Integer id = 0;
        String password = "password";

        DatabaseCommands databaseCommands = new DatabaseCommands();
        databaseCommands.connect();

        boolean temp = databaseCommands.registerUser(id, password);
        databaseCommands.removeUser(id);
        assertTrue(temp);
    }

    @Test
    public void insertSingleImageIntoDatabaseTest() {
        Integer mid = 0;
        String name = "test";
        String path = "memeTemplates/Roll-Safe-Think-About-It.jpg";
        Integer cid = 0;

        DatabaseCommands databaseCommands = new DatabaseCommands();
        databaseCommands.connect();

        boolean temp = databaseCommands.insertMeme(mid, name, path, cid);
        databaseCommands.removeMeme(mid);
        assertTrue(temp);
    }

    /*
        TODO: better select tests, for now the test is kind of manual
     */
    @Test
    public void getMemeFromDatabase() { //can get meme from database with selectMemesByName
        Integer mid = 0;
        String name = "test";
        String path = "memeTemplates/Roll-Safe-Think-About-It.jpg";
        Integer cid = 0;

        DatabaseCommands databaseCommands = new DatabaseCommands();
        databaseCommands.connect();

        databaseCommands.insertMeme(mid, name, path, cid);

        ArrayList<Meme> selectedMemes = databaseCommands.selectMemesByName("test");
        File meme = selectedMemes.get(0).getMeme();

        for(Meme m: selectedMemes) {
            System.out.println(m.getId() + " " + m.getClientId() + " " + m.getName());
        }
        try {
            meme.createNewFile();
        }catch(Exception ex) {
            ex.printStackTrace();
        }

        databaseCommands.removeMeme(mid);
    }
}
