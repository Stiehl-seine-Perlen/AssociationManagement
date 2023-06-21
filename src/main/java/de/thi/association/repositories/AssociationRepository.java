package de.thi.association.repositories;

import de.benevolo.entities.association.Association;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class AssociationRepository implements PanacheRepository<Association> {
    // auto implemented
}
