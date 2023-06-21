package de.thi.association.connector;

import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class AssociationPublisher {
    @Inject
    @Channel("new-association")
    Emitter<Long> emitter;

    public void announceNewAssociation(Long associationId){
        emitter.send(associationId);
    }

}