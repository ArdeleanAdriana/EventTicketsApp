package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@XmlRootElement
public class ActivityList {
    @XmlElement(name = "userActivity")
    private List<UserActivity> userActivityList;
}
