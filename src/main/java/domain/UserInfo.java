package domain;


import java.util.Date;

/**
 * Created by Nick on 06.11.2014.
 */
public class UserInfo {
    private String UserName;
    private String FirstName;
    private String LastName;
    private String UserGUID;
    private String SubscribeDate;
    private String TrialEndDate;
    private Integer UserInvoiceCount;
    private String LastLoginDate;
    private Integer ContactCount;
    private Integer LoginCount;
    private Integer PurchaseOrdercount;
    private String Email;
    private String UserState;
    private String Phone;
    private String ApplicationName;
    private String Title;
    private String Language;
    private Boolean IsAscend;
    private String AscendType;

    public String getLanguage() {
        return Language;
    }

    public void setLanguage(String language) {
        Language = language;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getUserGUID() {
        return UserGUID;
    }

    public void setUserGUID(String userGUID) {
        UserGUID = userGUID;
    }

    public String getSubscribeDate() {
        return SubscribeDate;
    }

    public void setSubscribeDate(String subscribeDate) {
        SubscribeDate = subscribeDate;
    }

    public String getTrialEndDate() {
        return TrialEndDate;
    }

    public void setTrialEndDate(String trialEndDate) {
        TrialEndDate = trialEndDate;
    }

    public Integer getUserInvoiceCount() {
        return UserInvoiceCount;
    }

    public void setUserInvoiceCount(Integer userInvoiceCount) {
        UserInvoiceCount = userInvoiceCount;
    }

    public String getLastLoginDate() {
        return LastLoginDate;
    }

    public void setLastLoginDate(String lastLoginDate) {
        LastLoginDate = lastLoginDate;
    }

    public Integer getContactCount() {
        return ContactCount;
    }

    public void setContactCount(Integer contactCount) {
        ContactCount = contactCount;
    }

    public Integer getLoginCount() {
        return LoginCount;
    }

    public void setLoginCount(Integer loginCount) {
        LoginCount = loginCount;
    }

    public Integer getPurchaseOrdercount() {
        return PurchaseOrdercount;
    }

    public void setPurchaseOrdercount(Integer purchaseOrdercount) {
        PurchaseOrdercount = purchaseOrdercount;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getApplicationName() {
        return ApplicationName;
    }

    public void setApplicationName(String applicationName) {
        ApplicationName = applicationName;
    }

    public String getUserState() {
        return UserState;
    }

    public void setUserState(String userState) {
        UserState = userState;
    }

    public String getTitle() {
        return Title;
    }

    public Boolean getIsAscend() {
        return IsAscend;
    }

    public void setIsAscend(Boolean isAscend) {
        IsAscend = isAscend;
    }

    public String getAscendType() {
        return AscendType;
    }

    public void setAscendType(String ascendType) {
        AscendType = ascendType;
    }

    public void setTitle(String title) {
        if (title != null) {
            Title = title;
        }
        else
        {
            Title = "";
        }

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserInfo)) return false;

        UserInfo userInfo = (UserInfo) o;

        if (!ApplicationName.equals(userInfo.ApplicationName)) return false;
        if (!Email.equals(userInfo.Email)) return false;
        if (!FirstName.equals(userInfo.FirstName)) return false;
        if (!LastName.equals(userInfo.LastName)) return false;
        if (!UserGUID.equals(userInfo.UserGUID)) return false;
        if (!UserName.equals(userInfo.UserName)) return false;
        if (!UserState.equals(userInfo.UserState)) return false;
        if (!UserState.equals(userInfo.Title)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = UserName.hashCode();
        result = 31 * result + FirstName.hashCode();
        result = 31 * result + LastName.hashCode();
        result = 31 * result + UserGUID.hashCode();
        result = 31 * result + Email.hashCode();
        result = 31 * result + ApplicationName.hashCode();
        result = 31 * result + UserState.hashCode();
        result = 31 * result + Title.hashCode();
        return result;
    }
}
