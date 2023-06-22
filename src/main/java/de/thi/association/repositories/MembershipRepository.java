package de.thi.association.repositories;

import de.benevolo.entities.association.Membership;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class MembershipRepository implements PanacheRepository<Membership> {
    // auto implemented
}
