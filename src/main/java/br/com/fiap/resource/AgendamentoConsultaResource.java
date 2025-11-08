package br.com.fiap.resource;

import br.com.fiap.bo.AgendamentoConsultaBO;
import br.com.fiap.to.AgendamentoConsultaTO;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.time.LocalDateTime;
import java.util.ArrayList;


@Path("/agendamento")
public class AgendamentoConsultaResource {

    private AgendamentoConsultaBO bo = new AgendamentoConsultaBO();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {
        ArrayList<AgendamentoConsultaTO> lista = bo.findAll();
        return Response.ok(lista).build();
    }

    @GET
    @Path("/{codigo}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findByCodigo(@PathParam("codigo") Long codigo) {
        AgendamentoConsultaTO a = bo.findByCodigo(codigo);
        return (a != null)
                ? Response.ok(a).build()
                : Response.status(Response.Status.NOT_FOUND).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response save(@Valid AgendamentoConsultaTO a) {
        try {
            // 🔹 Validação manual da data da consulta
            if (a.getDtHoraConsulta() != null && a.getDtHoraConsulta().isBefore(LocalDateTime.now())) {
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity("{\"erro\":\"A data da consulta deve ser posterior à data atual.\"}")
                        .build();
            }

            AgendamentoConsultaTO novo = bo.save(a);
            if (novo != null) {
                return Response.status(Response.Status.CREATED).entity(novo).build();
            }

            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("{\"erro\":\"Falha ao salvar consulta. Verifique os campos obrigatórios.\"}")
                    .build();

        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("{\"erro\":\"Erro no servidor: " + e.getMessage() + "\"}")
                    .build();
        }
    }

    @PUT
    @Path("/{codigo}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("codigo") Long codigo, @Valid AgendamentoConsultaTO a) {
        a.setCdAgendamento(codigo);

        // 🔹 Também valida a data na edição
        if (a.getDtHoraConsulta() != null && a.getDtHoraConsulta().isBefore(LocalDateTime.now())) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("{\"erro\":\"A data da consulta deve ser posterior à data atual.\"}")
                    .build();
        }

        AgendamentoConsultaTO atualizado = bo.update(a);
        return (atualizado != null)
                ? Response.ok(atualizado).build()
                : Response.status(Response.Status.BAD_REQUEST)
                .entity("{\"erro\":\"Falha ao atualizar consulta.\"}")
                .build();
    }

    @DELETE
    @Path("/{codigo}")
    public Response delete(@PathParam("codigo") Long codigo) {
        return bo.delete(codigo)
                ? Response.noContent().build()
                : Response.status(Response.Status.NOT_FOUND).build();
    }
}
