package data.dto;

import java.util.Date;

/**
 * Created by Vladimir on 1/6/2017.
 */

public class UserAccount {

    private Integer id;
    private String username;
    private String password;
    private String sex;
    private Date birthdate;
    private String sessionId;
    private Long tsMobile;
    private Long tsServer;
    private Long deltaT;


    public UserAccount() {

    }

    public UserAccount(String username, String password) {
        this.password = password;
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getTsMobile() {
        return tsMobile;
    }

    public void setTsMobile(Long tsMobile) {
        this.tsMobile = tsMobile;
    }

    public Long getTsServer() {
        return tsServer;
    }

    public void setTsServer(Long tsServer) {
        this.tsServer = tsServer;
    }

    public Long getDeltaT() {
        return deltaT;
    }

    public void setDeltaT(Long deltaT) {
        this.deltaT = deltaT;
    }
}
