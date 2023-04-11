package de.thi.association.entities;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;


@Entity
public class AssociationEntry {

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

    @JsonProperty("when")
    @Column(name="created_on")
    @CreationTimestamp
    private LocalDateTime when;
    //endregion

    //region Constructors
    protected AssociationEntry() {}

    @JsonCreator
    public AssociationEntry(@JsonProperty(value = "name", required = true) String name,
                            @JsonProperty(value = "when", required = true) LocalDateTime when,
                            @JsonProperty(value = "businessmail", required = true) String mail,
                            @JsonProperty(value = "password", required = true) String password) {
        this.name = name;
        this.when = when;
        this.businessmail = mail;
        this.password = hasPassword(password);
    }
    //endregion

    //region Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getWhen() {
        return when;
    }

    public void setWhen(LocalDateTime when) {
        this.when = when;
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

    //region Methods

    private String hasPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
            return new String(hash);
        } catch (NoSuchAlgorithmException e) {
            //TODO: create a more useful exception
            throw new RuntimeException(e);
        }
    }

    //endregion
}
