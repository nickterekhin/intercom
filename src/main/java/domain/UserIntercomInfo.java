package domain;

/**
 * Created by Nick on 06.11.2014.
 */
public class UserIntercomInfo {
    private String jsonString;
    private Long inDate;
    private int isupdate;
    private Long fromRow;
    private Long toRow;

    public Long getFromRow() {
        return fromRow;
    }

    public void setFromRow(Long fromRow) {
        this.fromRow = fromRow;
    }

    public Long getToRow() {
        return toRow;
    }

    public void setToRow(Long toRow) {
        this.toRow = toRow;
    }

    public UserIntercomInfo(String jsonString, Long inDate, int isupdate, Long fromRow, Long toRow) {
        this.jsonString = jsonString;
        this.inDate = inDate;
        this.isupdate = isupdate;
        this.fromRow = fromRow;
        this.toRow = toRow;
    }

    public String getJsonString() {
        return jsonString;
    }

    public void setJsonString(String jsonString) {
        this.jsonString = jsonString;
    }

    public Long getInDate() {
        return inDate;
    }

    public void setInDate(Long inDate) {
        this.inDate = inDate;
    }

    public int getIsupdate() {
        return isupdate;
    }

    public void setIsupdate(int isupdate) {
        this.isupdate = isupdate;
    }
}
