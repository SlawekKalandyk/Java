package mainpack;

public class Fork {
    private boolean used = false;
    private Integer forkId;

    public Fork(Integer id) {
        this.forkId = id;
    }

    public synchronized void use() {
        used = true;
    }

    public synchronized void putDown() {
        used = false;
    }

    public boolean isUsed() {
        return used;
    }

    public Integer getForkId() {
        return forkId;
    }



}
