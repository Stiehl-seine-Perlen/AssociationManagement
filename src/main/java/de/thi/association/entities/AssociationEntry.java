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
public class AssociationEntry {

    @Id
    @GeneratedValue
    private Long id;

    /**
     * association name
     */
    @JsonProperty("name")
    @Column(name="name")
    private String name;

    @JsonProperty("when")
    @Column(name="created_on")
    @CreationTimestamp
    private LocalDateTime when;

    protected AssociationEntry() {}
    @JsonCreator
    public AssociationEntry(@JsonProperty(value = "name", required = true) String name,
                            @JsonProperty(value = "when", required = true) LocalDateTime when) {
        this.name = name;
        this.when = when;
    }

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
}
