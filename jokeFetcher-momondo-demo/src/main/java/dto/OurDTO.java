package dto;

public class OurDTO {
    private String joke1;
    private String joke1Referance;
    private String joke2;
    private String joke2Referance;

    public OurDTO() {
    }

    public OurDTO(ChuckDTO chuckDTO, DadDTO dadDto) {
        this.joke1 = chuckDTO.getValue();
        this.joke1Referance = chuckDTO.getUrl();
        this.joke2 = dadDto.getJoke();
        this.joke2Referance = dadDto.getId();
    }

    public String getJoke1() {
        return joke1;
    }

    public void setJoke1(String joke1) {
        this.joke1 = joke1;
    }

    public String getJoke1Referance() {
        return joke1Referance;
    }

    public void setJoke1Referance(String joke1Referance) {
        this.joke1Referance = joke1Referance;
    }

    public String getJoke2() {
        return joke2;
    }

    public void setJoke2(String joke2) {
        this.joke2 = joke2;
    }

    public String getJoke2Referance() {
        return joke2Referance;
    }

    public void setJoke2Referance(String joke2Referance) {
        this.joke2Referance = joke2Referance;
    }
}
