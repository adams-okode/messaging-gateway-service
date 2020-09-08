package com.decoded.messaging.data.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter(value = AccessLevel.PUBLIC)
@Setter(value = AccessLevel.PUBLIC)
@ToString
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String recepient;

    private MessageStatus status;

    private MessageType type;

    private Integer retries = 0;

    @Column(nullable = true)
    private String subject;

    @Column(columnDefinition = "text")
    private String content;

    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    protected Date creationDate;

    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    protected Date lastModifiedDate;

}
