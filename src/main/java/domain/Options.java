package domain;

/**
 * Created by Nick on 06.11.2014.
 */
public class Options {
    private Long usersQty =null;
    private Long pdate=null;
    private Integer isUpdate =null;

    public Long getUsersQty() {
        return usersQty;
    }

    public void setUsersQty(Long usersQty) {
        this.usersQty = usersQty;
    }

    public Long getPdate() {
        return pdate;
    }

    public void setPdate(Long pdate) {
        this.pdate = pdate;
    }

    public int getIsUpdate() {
        return isUpdate;
    }

    public void setIsUpdate(Integer isUpdate) {
        this.isUpdate = isUpdate;
    }

    public Options(Long usersQty, Long pdate, Integer isUpdate) {

        this.usersQty = usersQty;
        this.pdate = pdate;
        this.isUpdate = isUpdate;
    }
}
