package serverpack;

import java.io.File;

public class Meme {
    private Integer id;
    private String name;
    private File meme;
    private Integer clientId;

    public Meme(Integer id, String name, File meme, Integer clientId) {
        this.id = id;
        this.name = name;
        this.meme = meme;
        this.clientId = clientId;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public File getMeme() {
        return meme;
    }

    public Integer getClientId() {
        return clientId;
    }
}
