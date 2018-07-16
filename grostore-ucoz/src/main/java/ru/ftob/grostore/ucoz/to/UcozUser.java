package ru.ftob.grostore.ucoz.to;

import com.fasterxml.jackson.annotation.*;
import com.github.scribejava.core.model.Response;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UcozUser {

    @JsonProperty("user")
    private String login;

//    @JsonProperty("full_name")
    private String fullName;

    @JsonProperty("uid")
    private String id;

    @JsonProperty("phone")
    private String phone;

    @JsonProperty("country")
    private String country;

    @JsonProperty("state")
    private String state;

    @JsonProperty("city")
    private String city;

    @JsonProperty("email")
    private String email;

    @JsonProperty("password")
    private String password;

    @JsonProperty("avatar")
    private String avatar;

    @JsonProperty("reg_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime created;

    @JsonProperty("last_visit")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime lastVisit;

    //Caused by: java.time.format.DateTimeParseException: Text '0000-00-00'
    // could not be parsed: Invalid value for YearOfEra
    // (valid values 1 - 999999999/1000000000)
//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
//    private LocalDate birthday;


    public UcozUser() {
    }

    public UcozUser(String login, String fullName, String email, String password) {
        this.login = login;
        this.fullName = fullName;
        this.email = email;
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @JsonGetter("name")
    public String getFullName() {
        return fullName;
    }

    @JsonSetter("full_name")
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public LocalDateTime getLastVisit() {
        return lastVisit;
    }

    public void setLastVisit(LocalDateTime lastVisit) {
        this.lastVisit = lastVisit;
    }

    @Override
    public String toString() {
        return "UcozUser{" +
                "login='" + login + '\'' +
                ", fullName='" + fullName + '\'' +
                ", id='" + id + '\'' +
                ", phone='" + phone + '\'' +
                ", country=" + country +
                ", state=" + state +
                ", city='" + city + '\'' +
                ", email='" + email + '\'' +
                ", avatar='" + avatar + '\'' +
                ", created=" + created +
                ", lastVisit=" + lastVisit +
                '}';
    }
}
