public class Servers {
    private String servernames;
    private int storage_free;
    private int storage_used;
    private Double cpu_load;

    public String getNaam() {
        return this.servernames;
    }

    public void setNaam(String servernames) {
        this.servernames = servernames;
    }

    public int getStorage_free() {
        return this.storage_free;
    }

    public void setStorage_free(int storage_free) {
        this.storage_free = storage_free;
    }

    public int getStorage_used() {
        return this.storage_used;
    }

    public void setStorage_used(int storage_used) {
        this.storage_used = storage_used;
    }

    public Double getCpu_load() {
        return this.cpu_load;
    }

    public void setCpu_load(Double cpu_load) {
        this.cpu_load = cpu_load;
    }

    public Servers(String servernames, int storage_free, int storage_used, Double cpu_load) {
        this.servernames = servernames;
        this.storage_free = storage_free;
        this.storage_used = storage_used;
        this.cpu_load = cpu_load;
    }
}