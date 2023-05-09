package de.thi.association.repositories;

import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class AssociationRepository implements PanacheRepository<de.benevolo.entities.association.Association> {
    // auto implemented
}
