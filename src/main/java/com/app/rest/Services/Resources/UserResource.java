package com.app.rest.Services.Resources;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.resource.spi.ConfigProperty;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.app.rest.Models.User;

@Path("users") // restful endpoints
public class UserResource {
    
    @Inject
    @ConfigProperty(description = "the size of the users list", defaultValue="5")
    private Integer usersSize; // init the size of users list


    // The persistence context is the first-level cache where all the entities are fetched from the database or saved to the database
    // Persistence context keeps track of any changes made into a managed entity.
    @PersistenceContext
    private EntityManager entityManager; 
    // EntityManager is the interface that lets us interact with the persistence context.
    // source: https://www.baeldung.com/jpa-hibernate-persistence-context

    @GET // Get request
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllUsers() {
        List<User> allUsers = this.entityManager
            .createQuery("SELECT user FROM User user", User.class)
            .setMaxResults(usersSize)
            .getResultList();
        
            return Response.ok(allUsers).build();
    }

}
