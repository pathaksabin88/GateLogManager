package domain;

/**
 * Created by Babu Sabin on 2/8/2017.
 */
public class ActiveUser {

    // stores activeuser info

    int id;
    String entryDateTime;
    String reason;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEntryDateTime() {
        return entryDateTime;
    }

    public void setEntryDateTime(String entryDateTime) {
        this.entryDateTime = entryDateTime;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
