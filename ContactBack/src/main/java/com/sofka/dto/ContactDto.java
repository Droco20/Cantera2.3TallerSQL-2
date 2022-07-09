package com.sofka.dto;

/**Permite la comunicacion con la base de datos*/
public class ContactDto {

    private String name;
    private String phone;
    private String email;
    private String dateBirth;

    public ContactDto() {
    }

    public ContactDto(String name, String phone, String email, String dateBirth) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.dateBirth = dateBirth;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDateBirth() {
        return dateBirth;
    }

    public void setDateBirth(String dateBirth) {
        this.dateBirth = dateBirth;
    }
}
