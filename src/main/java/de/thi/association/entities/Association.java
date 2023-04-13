package de.thi.association.entities;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;


@Entity
public class Association {

    //region Fields
    @Id
    @GeneratedValue
    private Long id;

    /**
     * association name
     */
    @JsonProperty("name")
    @Column(name="name")
    private String name;

    /**
     * business email address
     */
    @JsonProperty("businessmail")
    @Column(name="businessmail")
    private String businessmail;

    /**
     * password for association
     */
    @JsonProperty("password")
    @Column(name="password")
    private String password;

    @JsonProperty("created")
    @Column(name="created")
    @CreationTimestamp
    private LocalDateTime created;

    //endregion

    //region Constructors
    protected Association() {}

    @JsonCreator
    public Association(@JsonProperty(value = "name", required = true) String name,
                       @JsonProperty(value = "businessmail", required = true) String mail,
                       @JsonProperty(value = "password", required = true) String password) {
        this.name = name;
        this.businessmail = mail;
        this.password = password;
    }
    //endregion

    //region Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setWhen(LocalDateTime created) {
        this.created = created;
    }

    public String getBusinessmail() {
        return businessmail;
    }

    public void setBusinessmail(String businessmail) {
        this.businessmail = businessmail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    //endregion
}
