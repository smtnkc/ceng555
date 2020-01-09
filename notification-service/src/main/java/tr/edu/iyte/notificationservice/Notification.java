package tr.edu.iyte.notificationservice;

public class Notification {

    private long id;
    private String mail;
    private String name;

    public Notification() {
    }

    public Notification(String mail, String name, long id){
        this.name=name;
        this.mail=mail;
        this.id=id;
    }
    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}