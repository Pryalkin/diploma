package com.pryalkin.portal.entity;

import com.pryalkin.portal.entity.user.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Subcomment implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Size(min = 1, max = 500)
    private String subcomment;
    @OneToOne
    private User user;
    @Column(name = "date_of_creation")
    private Date dateOfCreation;
    @OneToMany
    private List<Popularity> popularity;

}
