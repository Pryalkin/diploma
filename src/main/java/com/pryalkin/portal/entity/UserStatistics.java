package com.pryalkin.portal.entity;

import com.pryalkin.portal.entity.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserStatistics implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    private User user;
    @OneToMany
    private List<Comment> comments;
    @OneToMany
    private List<Subcomment> subcomments;
    @OneToMany
    private List<User> subscriptions; // подписки
    @OneToMany
    private List<User> subscribes; // подписчики
    @OneToOne
    private OverviewStatistics overviewStatistics;
    @OneToMany
    private List<Popularity> popularity;
    @OneToMany
    private List<Emotion> emotions;
    @OneToMany
    private List<Viewing> views;

}
