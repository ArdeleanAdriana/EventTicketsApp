package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@XmlRootElement(name = "userActivity")
@XmlAccessorType(XmlAccessType.FIELD)

public class UserActivity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private Long id;
    @Column(nullable = false, unique = true, updatable = false)
    private String uid;

    @XmlElement(name = "operation")
    private String op;
    @XmlElement(name = "timestamp")
    private String timestamp;


    @ManyToOne
    @JoinColumn(name = "utilizator_id",
            referencedColumnName = "id")
    @XmlElement(name = "utilizator")
    private Utilizator utilizator;
}
