package dto;

public class DadDTO {
    private String joke;
    private String id;

    public DadDTO() {
    }

    public DadDTO(String joke, String id) {
        this.joke = joke;
        this.id = id;
    }

    public String getJoke() {
        return joke;
    }

    public void setJoke(String joke) {
        this.joke = joke;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
