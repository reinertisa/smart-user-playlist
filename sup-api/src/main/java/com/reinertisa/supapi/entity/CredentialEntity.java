package com.reinertisa.supapi.entity;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "credentials")
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class CredentialEntity extends Auditable {
    private String password;
    @OneToOne(targetEntity = UserEntity.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property="id")
    @JsonIdentityReference(alwaysAsId = true)
    @JsonProperty("user_id")
    private UserEntity userEntity;

    public CredentialEntity() {

    }

    public CredentialEntity(UserEntity userEntity, String password) {
        this.userEntity = userEntity;
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    @Override
    public String toString() {
        return "CredentialEntity{" +
                "password='" + password + '\'' +
                ", userEntity=" + userEntity +
                '}';
    }
}
