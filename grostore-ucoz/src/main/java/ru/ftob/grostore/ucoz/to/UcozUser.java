package ru.ftob.grostore.ucoz.to;

import com.fasterxml.jackson.annotation.*;

import java.time.LocalDateTime;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UcozUser {

    @JsonProperty("account")
    private String login;

    private String name;

    @JsonProperty("uid")
    private Integer id;

    @JsonProperty("phone")
    private String phone = "";

    //TODO add location mapping
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
    private LocalDateTime visited;

    //Caused by: java.time.format.DateTimeParseException: Text '0000-00-00'
    // could not be parsed: Invalid value for YearOfEra
    // (valid values 1 - 999999999/1000000000)
//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
//    private LocalDate birthday;

    public UcozUser() {
    }

    public UcozUser(String login, String fullName, String email, String password) {
        this(login, fullName, "", "", "", "", email, password, "");
    }

    public UcozUser(String login, String fullName, String phone, String country, String state, String city, String email, String password, String avatar) {
        this.login = login;
        this.name = fullName;
        this.phone = phone;
        this.country = country;
        this.state = state;
        this.city = city;
        this.email = email;
        this.password = password;
        this.avatar = avatar;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @JsonGetter("name")
    public String getName() {
        return name;
    }

    @JsonSetter("full_name")
    public void setName(String fullName) {
        this.name = fullName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public LocalDateTime getVisited() {
        return visited;
    }

    public void setVisited(LocalDateTime visited) {
        this.visited = visited;
    }

    @Override
    public String toString() {
        return "UcozUser{" +
                "login='" + login + '\'' +
                ", fullName='" + name + '\'' +
                ", id='" + id + '\'' +
                ", phone='" + phone + '\'' +
                ", country=" + country +
                ", state=" + state +
                ", city='" + city + '\'' +
                ", email='" + email + '\'' +
                ", avatar='" + avatar + '\'' +
                ", created=" + created +
                ", visited=" + visited +
                '}';
    }
}
