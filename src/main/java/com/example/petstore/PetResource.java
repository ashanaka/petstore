package com.example.petstore;

import java.util.ArrayList;
import java.util.List;
import java.net.URI;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PATCH;
import javax.ws.rs.DELETE;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;

import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;

@Path("/v1/pets")
@Produces("application/json")
public class PetResource {

    List<Pet> pets = new ArrayList<Pet>();

    public PetResource() {
        Pet pet1 = new Pet();
        pet1.setPetId(1);
        pet1.setPetAge(3);
        pet1.setPetName("Joma");
        pet1.setPetType("Lion");

        Pet pet2 = new Pet();
        pet2.setPetId(2);
        pet2.setPetAge(40);
        pet2.setPetName("Raja");
        pet2.setPetType("Elephant");

        pets.add(pet1);
        pets.add(pet2);
    }

    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "All Pets", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(ref = "Pet")))})
    @GET
    public Response getPets() {
        return Response.ok(pets).build();
    }

    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "Pet for id", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(ref = "Pet"))),
            @APIResponse(responseCode = "404", description = "No Pet found for the id.")})
    @GET
    @Path("{petId}")
    public Response getPet(@PathParam("petId") int petId) {
        if (petId < 0) {
            return Response.status(Status.NOT_FOUND).build();
        }
        Pet pet = new Pet();
        pet.setPetId(petId);
        pet.setPetAge(3);
        pet.setPetName("Buula");
        pet.setPetType("Dog");

        return Response.ok(pet).build();

    }

    @APIResponses(value = {
            @APIResponse(responseCode = "201", description = "Pet created", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(ref = "Pet")))})
    @POST
    public Response addPet(@RequestBody Pet pet) {
        pets.add(pet);
        return Response.created(URI.create("/v1/pets")).build();
    }

    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "Pet updated", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(ref = "Pet")))})
    @PATCH
    @Path("{petId}")
    public Response updatePet(@PathParam("petId") int petId, @RequestBody Pet petNew) {
        if (petId < 0) {
            return Response.status(Status.NOT_FOUND).build();
        }

        for (Pet pet : pets) {
            if (pet.getPetId() == petId) {
                pet.setPetAge(petNew.getPetAge());
                pet.setPetName(petNew.getPetName());
                pet.setPetType(petNew.getPetType());
                pet.setPetId(petNew.getPetId());
            }
        }
        return Response.created(URI.create("/v1/pets")).build();
    }

    @APIResponses(value = {
            @APIResponse(responseCode = "204", description = "Pet for id", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(ref = "Pet"))),
            @APIResponse(responseCode = "404", description = "No Pet found for the id.")})
    @DELETE
    @Path("{petId}")
    public Response removePet(@PathParam("petId") int petId) {
        if (petId < 0) {
            return Response.status(Status.NOT_FOUND).build();
        }
        for (Pet pet : pets) {
            if (pet.getPetId() == petId) {
                pets.remove(pet);
                return Response.ok().build();
            }
        }

        return Response.status(Status.NOT_FOUND).build();

    }

    @APIResponses(value = {
            @APIResponse(responseCode = "201", description = "Pet created", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(ref = "Pet")))})
    @POST
    @Path("{petId}/type")
    public Response addPetType(@PathParam("petId") int petId, @RequestBody String type) {
        if (petId < 0) {
            return Response.status(Status.NOT_FOUND).build();
        }

        for (Pet pet : pets) {
            if (pet.getPetId() == petId) {
                if (pet.getPetType().equals("")) {
                    pet.setPetType(type);
                    return Response.ok(pet.getPetType()).build();
                } else {
                    return Response.notModified().build();
                }
            }
        }
        return Response.created(URI.create("/v1/pets")).build();
    }

    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "Pet for id", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(ref = "Pet"))),
            @APIResponse(responseCode = "404", description = "No Pet found for the id.")})
    @GET
    @Path("{petId}/type")
    public Response getPetType(@PathParam("petId") int petId) {
        if (petId < 0) {
            return Response.status(Status.NOT_FOUND).build();
        }
        for (Pet pet : pets) {
            if (pet.getPetId() == petId) {
                return Response.ok(pet.getPetType()).build();
            }
        }

        return Response.status(Status.NOT_FOUND).build();

    }

    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "Pet updated", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(ref = "Pet")))})
    @PATCH
    @Path("{petId}/type")
    public Response updatePetType(@PathParam("petId") int petId, @RequestBody String type) {
        if (petId < 0) {
            return Response.status(Status.NOT_FOUND).build();
        }

        for (Pet pet : pets) {
            if (pet.getPetId() == petId) {
                pet.setPetType(type);
            }
        }
        return Response.created(URI.create("/v1/pets")).build();
    }

    @APIResponses(value = {
            @APIResponse(responseCode = "204", description = "Pet for id", content = @Content(mediaType = MediaType.APPLICATION_JSON, schema = @Schema(ref = "Pet"))),
            @APIResponse(responseCode = "404", description = "No Pet found for the id.")})
    @DELETE
    @Path("{petId}/type")
    public Response removePetType(@PathParam("petId") int petId) {
        if (petId < 0) {
            return Response.status(Status.NOT_FOUND).build();
        }
        for (Pet pet : pets) {
            if (pet.getPetId() == petId) {
                pet.setPetType("");
                return Response.ok().build();
            }
        }

        return Response.status(Status.NOT_FOUND).build();

    }
}



