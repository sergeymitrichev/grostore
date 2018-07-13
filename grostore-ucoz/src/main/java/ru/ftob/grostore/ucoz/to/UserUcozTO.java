package ru.ftob.grostore.ucoz.to;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.github.scribejava.core.model.Response;
import ru.ftob.grostore.model.User;

import java.time.LocalDate;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UserUcozTO extends UcozTO<User> {

    private String full_name;

    private String uid;

    //Caused by: java.time.format.DateTimeParseException: Text '0000-00-00'
    // could not be parsed: Invalid value for YearOfEra
    // (valid values 1 - 999999999/1000000000)
//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
//    private LocalDate birthday;

    private String city;

    private String email;

    public UserUcozTO() {
    }

    public UserUcozTO(Response response) {
        super();
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

//    public LocalDate getBirthday() {
//        return birthday;
//    }
//
//    public void setBirthday(LocalDate birthday) {
//        this.birthday = birthday;
//    }

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

    @Override
    public String toString() {
        return "UserUcozTO{" +
                "full_name='" + full_name + '\'' +
                ", uid='" + uid + '\'' +
//                ", birthday=" + birthday +
                ", city='" + city + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
