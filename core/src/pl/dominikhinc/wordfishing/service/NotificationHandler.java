package pl.dominikhinc.wordfishing.service;

public interface NotificationHandler {

    public void showNotification(String title, String text, int secs, int id);
    public void cancelNotification(int id);
}
