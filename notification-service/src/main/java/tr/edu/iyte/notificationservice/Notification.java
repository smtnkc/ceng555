package tr.edu.iyte.notificationservice;

public class Notification {

    private Application application;

    public Notification() {
    }

    public Notification(Application application) {
        this.application = application;
    }

    public Application getApplication() {
        return application;
    }

    public void setApplication(Application application) {
        this.application = application;
    }
}