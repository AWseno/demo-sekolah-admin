package com.demo.sekolahadmin.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "ADMIN")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "USERNAME")
    private String username;
    @Column(name = "NAME")
    private String name;
    @Column(name = "BIRTHDAY")
    private Date birthday;

    @Column(name = "RECORD_STATUS")
    private Character recordStatus;

    @Column(name = "CREATED_FROM")
    private String createdFrom;
    @Column(name = "CREATED_BY")
    private String createdBy;
    @Column(name = "CREATED_DATE")
    private Date createdDate;

    @Column(name = "LAST_UPDATED_FROM")
    private String lastUpdatedFrom;
    @Column(name = "LAST_UPDATED_BY")
    private String lastUpdatedBy;
    @Column(name = "LAST_UPDATED_DATE")
    private Date lastUpdatedDate;

}
