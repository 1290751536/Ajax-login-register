package domain;

public class UserDomain {
    private String custNo;
    private String custPwd;
    private String email;

    @Override
    public String toString() {
        return "User{" +
                "custNo='" + custNo + '\'' +
                ", custPwd='" + custPwd + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    public String getCustNo() {
        return custNo;
    }

    public void setCustNo(String custNo) {
        this.custNo = custNo;
    }

    public String getCustPwd() {
        return custPwd;
    }

    public void setCustPwd(String custPwd) {
        this.custPwd = custPwd;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
