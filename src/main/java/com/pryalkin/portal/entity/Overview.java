package com.pryalkin.portal.entity;

import com.pryalkin.portal.entity.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Overview implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "group_overview")
    private String groupOverview;
    private String topic;
    @OneToMany
    private List<Message> messages;
    @Column(name = "date_of_creation")
    private Date dateOfCreation;
    @OneToMany
    private List<Image> images;
    @OneToOne
    private User user;

}
