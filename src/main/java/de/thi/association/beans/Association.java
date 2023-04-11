package de.thi.association.beans;

import de.thi.association.entities.AssociationEntry;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class Association implements PanacheRepository<AssociationEntry> {
    // auto implemented
}
