package tr.edu.iyte.notificationservice;

import java.util.Random;

public class Application {
    private long id;
    private String name;
    private String email;
    private double alesScore;
    private double ydsScore;

    public Application() {
        Random rand = new Random();
        this.id = rand.nextInt((99999 - 10000) + 1) + 10000;
    }

    public Application(long id, String name, String email, double alesScore, double ydsScore) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.alesScore = alesScore;
        this.ydsScore = ydsScore;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    public double getAlesScore() {
        return alesScore;
    }

    public void setAlesScore(double alesScore) {
        this.alesScore = alesScore;
    }

    public double getYdsScore() { return ydsScore; }

    public void setYdsScore(double ydsScore) { this.ydsScore = ydsScore; }
}