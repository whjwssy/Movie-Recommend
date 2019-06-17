package review.common.jwt;

public class Audience {

    private String clientId;
    private String base64Secret;
    private String name;
    private int expiresSecond;

    public Audience() {
        this.clientId = "098f6bcd4621d373cade4e832627b4f6";
        this.base64Secret = "MDk4ZjZiY2Q0NjIxZDM3M2NhZGU0ZTgzMjYyN2I0ZjY";
        this.name = "restapiuser";
        this.expiresSecond = 172800;
    }

    public String getClientId() {
        return clientId;
    }

    public String getBase64Secret() {
        return base64Secret;
    }

    public String getName() {
        return name;
    }

    public int getExpiresSecond() {
        return expiresSecond;
    }
}