package de.thi.association.beans;

import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class Association implements PanacheRepository<de.thi.association.entities.Association> {
    // auto implemented
}
