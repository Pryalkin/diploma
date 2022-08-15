package com.pryalkin.portal.entity;

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
public class OverviewStatistics implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    private Overview overview;
    @OneToMany
    private List<Emotion> emotions;
    @OneToMany
    private List<Rating> rating;
    @OneToMany
    private List<Comment> comments;
    @OneToMany
    private List<Viewing> views;
    @OneToMany
    private List<Popularity> popularity;

}
