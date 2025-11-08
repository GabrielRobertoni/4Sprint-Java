package br.com.fiap.resource;

import br.com.fiap.bo.FeedbackBO;
import br.com.fiap.to.FeedbackTO;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.ArrayList;


@Path("/feedback")
public class FeedbackResource {

    private FeedbackBO bo = new FeedbackBO();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {
        ArrayList<FeedbackTO> lista = bo.findAll();
        return (lista != null && !lista.isEmpty())
                ? Response.ok(lista).build()
                : Response.status(Response.Status.NOT_FOUND).build();
    }

    @GET
    @Path("/{codigo}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findByCodigo(@PathParam("codigo") Long codigo) {
        FeedbackTO resultado = bo.findByCodigo(codigo);
        return (resultado != null)
                ? Response.ok(resultado).build()
                : Response.status(Response.Status.NOT_FOUND).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response save(@Valid FeedbackTO f) {
        FeedbackTO resultado = bo.save(f);
        return (resultado != null)
                ? Response.status(Response.Status.CREATED).entity(resultado).build()
                : Response.status(Response.Status.BAD_REQUEST)
                .entity("{\"erro\":\"Falha ao salvar feedback\"}").build();
    }

    @PUT
    @Path("/{codigo}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@Valid FeedbackTO f, @PathParam("codigo") Long codigo) {
        f.setCdFeedback(codigo);
        FeedbackTO resultado = bo.update(f);
        return (resultado != null)
                ? Response.ok(resultado).build()
                : Response.status(Response.Status.BAD_REQUEST)
                .entity("{\"erro\":\"Falha ao atualizar feedback\"}").build();
    }

    @DELETE
    @Path("/{codigo}")
    public Response delete(@PathParam("codigo") Long codigo) {
        return bo.delete(codigo)
                ? Response.noContent().build()
                : Response.status(Response.Status.NOT_FOUND).build();
    }
}
