package tr.edu.iyte.applicationservice;

import java.util.Random;

public class Application {
    private String applicationId;
    private String personalId;
    private String name;
    private String email;
    private double alesScore;
    private double ydsScore;
    private double gpaScore;

    public Application() {
        Random rand = new Random();
        this.applicationId = Integer.toString(rand.nextInt((99999 - 10000) + 1) + 10000);
    }

    public Application(String applicationId, String personalId, String name, String email,
                       double alesScore, double ydsScore, double gpaScore) {

        this.applicationId = applicationId;
        this.personalId = personalId;
        this.name = name;
        this.email = email;
        this.alesScore = alesScore;
        this.ydsScore = ydsScore;
        this.gpaScore = gpaScore;
    }

    public String getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(String applicationId) {
        this.applicationId = applicationId;
    }

    public String getPersonalId() { return personalId; }

    public void setPersonalId(String personalId) { this.personalId = personalId; }

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

    public double getGpaScore() { return gpaScore; }

    public void setGpaScore(double gpaScore) { this.gpaScore = gpaScore; }
}