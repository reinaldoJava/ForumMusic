package com.forum.music.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Table(name="Profile", schema="MyAppDb")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserProfile implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO,generator="native")
    @GenericGenerator(name = "native",strategy = "native")
    @Column(name = "profile_id")
    private Long id;

    @Column(name = "profile")
    private String profile;

    @Column(name = "description")
    private String description;

}
