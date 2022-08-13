package com.pryalkin.portal.entity;

import com.pryalkin.portal.entity.user.User;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Subcomment implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Size(min = 1, max = 500)
    private String subcomment;
    @OneToOne
    private User user;
    @Column(name = "date_of_creation")
    private Date dateOfCreation;
    private Long rating;

}
