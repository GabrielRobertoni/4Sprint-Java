package br.com.fiap.resource;

import br.com.fiap.bo.LoginBO;
import br.com.fiap.to.LoginTO;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

@Path("/login")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class LoginResource {

    private LoginBO bo = new LoginBO();

    @GET
    public Response findAll() {
        List<LoginTO> lista = bo.findAll();
        return Response.ok(lista).build();
    }

    @GET
    @Path("/{id}")
    public Response findByCodigo(@PathParam("id") Long id) {
        LoginTO login = bo.findByCodigo(id);
        return (login != null) ? Response.ok(login).build() : Response.status(404).build();
    }

    @POST
    public Response save(@Valid LoginTO login) {
        LoginTO novo = bo.save(login);
        return (novo != null)
                ? Response.status(Response.Status.CREATED).entity(novo).build()
                : Response.status(Response.Status.BAD_REQUEST).entity("Erro ao cadastrar login.").build();
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") Long id, @Valid LoginTO login) {
        login.setIdLogin(id);
        LoginTO atualizado = bo.update(login);
        return (atualizado != null)
                ? Response.ok(atualizado).build()
                : Response.status(Response.Status.BAD_REQUEST).entity("Erro ao atualizar login.").build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        return bo.delete(id)
                ? Response.noContent().build()
                : Response.status(Response.Status.NOT_FOUND).entity("Login não encontrado.").build();
    }
}
