package com.forum.music.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name="User", schema="MyAppDb")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO,generator="native")
    @GenericGenerator(name = "native",strategy = "native")
    @Column(name = "user_id")
    private Long id;

    @NotNull(message = "Name cannot be null")
    @NotBlank
    private String name;

    @NotNull
    @NotBlank
    private String password;

    @NotNull
    @NotBlank
    @Column(name = "nick_name",unique=true)
    private String nickName;

    @NotNull
    @NotBlank
    @Column(unique=true)
    @Email(regexp=".*@.*\\..*", message = "Email should be valid")
    private String email;

    @JsonIgnore
    @OneToOne(cascade = CascadeType.REFRESH,fetch = FetchType.EAGER)
    @JoinColumn(name = "profile_id")
    private UserProfile userProfile;
}
