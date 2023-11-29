package com.dagnerchuman.springbootmicroservice3ApiGateway.Dto;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Table(name="EmailValues")
public class EmailValuesDto {

    private String mailFrom;
    private String mailTo;
    private String subject;
    private String username;
    private String tokenPassword;

    public EmailValuesDto(String mailFrom, String mailTo, String subject, String username, String tokenPassword) {
        this.mailFrom = mailFrom;
        this.mailTo = mailTo;
        this.subject = subject;
        this.username = username;
        this.tokenPassword = tokenPassword;
    }

    public EmailValuesDto(){

    }

}
