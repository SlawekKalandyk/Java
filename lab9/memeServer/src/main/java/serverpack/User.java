package serverpack;

public class User {
    private Integer id;
    private String password;

    public User(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }
}
