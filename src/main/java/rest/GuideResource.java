package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import entities.Guide;
import entities.Trip;
import entities.User;
import errorhandling.API_Exception;
import facades.GuideFacade;
import facades.OwnerFacade;
import facades.TripFacade;
import javassist.NotFoundException;
import utils.EMF_Creator;

import javax.persistence.EntityManagerFactory;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.awt.*;

@Path("guide")
public class GuideResource {
    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();
    private static final GuideFacade FACADE =  GuideFacade.getGuideFacade(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    @GET
    @Path("/all")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getAll() throws NotFoundException {
        return Response.ok().entity(GSON.toJson(FACADE.getAllGuides())).build();
    }
    @GET
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getUser(@PathParam("id") int id) throws API_Exception {
        return Response.ok().entity(GSON.toJson(FACADE.getGuideById(id))).build();
    }

    @POST
    @Path("/add")
    @Consumes({MediaType.APPLICATION_JSON})
    public Response create(String guide) {
        Guide guideTwo =GSON.fromJson(guide, Guide.class);
        Guide burner = new Guide(guideTwo.getGender(), guideTwo.getBirthYear(), guideTwo.getProfile(), guideTwo.getImageUrl());
        Guide newGuide = FACADE.createGuide(burner);
        return Response.ok().entity(GSON.toJson(newGuide)).build();
    }

}
