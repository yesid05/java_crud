/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.sql.SQLException;
import java.util.List;
import modelo.bo.Participante;
import modelo.dao.ParticipanteDAO;

/**
 *
 * @author YCA
 */
public class ParticipanteCtrl {

    private String msgError;

    private ParticipanteDAO participanteDAO;

    public ParticipanteCtrl() {
        participanteDAO = new ParticipanteDAO();
    }

    /**
     * Retorna un departamento realizada una búsqueda.
     *
     * @param unParticipante Objeto que encapsula el id del departamento para
     * realizar la b�squeda. <br />
     * @return Un participante
     * <b>null</b> si no encuentra un departamento.
     * @throws SQLException Si ocurre un error con la base de datos.
     */
    public Participante buscarPorClave(Participante unParticipante) {
        Participante participante = null;
        try {
            participante = participanteDAO.buscarPorClave(unParticipante);
        } catch (SQLException e) {
            msgError = e.getMessage();
        } finally {
            try {
                participanteDAO.cerrarConexion();
            } catch (SQLException ex) {
                msgError = "Error al intentar cerrar los recursos utilizados.";
            }
        }
        return participante;
    }

    /**
     * Retorna una lista de departamentos.
     *
     * @return una lista de participantes
     * @throws SQLException Si ocurre un error con la base de datos.
     */
    public List<Participante> buscarTodos() {
        List<Participante> listaParticipante = null;
        try {
            listaParticipante = participanteDAO.buscarTodos();
        } catch (SQLException e) {
            msgError = e.getMessage();
        } finally {
            try {
                participanteDAO.cerrarConexion();
            } catch (SQLException ex) {
                msgError = "Error al intentar cerrar los recursos utilizados.";
            }
        }
        return listaParticipante;

    }
    
    /**
     * Retorna una lista de departamentos.
     *
     * @return una lista de participantes
     * @throws SQLException Si ocurre un error con la base de datos.
     */
    public List<Participante> buscarTodos(Participante unParticipante) {
        List<Participante> listaParticipante = null;
        try {
            listaParticipante = participanteDAO.buscarTodos(unParticipante);
        } catch (SQLException e) {
            msgError = e.getMessage();
        } finally {
            try {
                participanteDAO.cerrarConexion();
            } catch (SQLException ex) {
                msgError = "Error al intentar cerrar los recursos utilizados.";
            }
        }
        return listaParticipante;

    }

    /**
     * Guarda un departamento en la base de datos.
     *
     * @param unParticipante Objeto que encapsula las caracter�sticas del
     * departamento a guardar. <br />
     * @return <b>true</b> Si se pudo guardar. <br />
     * <b>false</b> si ocurre un error.
     * @throws SQLException Si ocurre un error con la base de datos.
     */
    public boolean guardar(Participante unParticipante) {
        boolean seGuardo = false;
        try {
            seGuardo = participanteDAO.guardar(unParticipante);
        } catch (SQLException e) {
            msgError = e.getMessage();
        } finally {
            try {
                participanteDAO.cerrarConexion();
            } catch (SQLException ex) {
                msgError = "Error al intentar cerrar los recursos utilizados.";
            }
        }

        return seGuardo;
    }

    public boolean actualizarParticipante(Participante unParticipante) {
        boolean seActualizo = false;
        try {
            seActualizo = participanteDAO.actualizar(unParticipante);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            msgError = e.getMessage();
        } finally {
            try {
                participanteDAO.cerrarConexion();
            } catch (SQLException ex) {
                msgError = "Error al intentar cerrar los recursos utilizados.";
            }
        }

        return seActualizo;
    }

    /**
     * Elimina un departamento en la base de datos.
     *
     * @param unParticipante Objeto que encapsula el id del departamento para
     * poder eliminar. <br />
     * @return <b>true</b> Si se pudo eliminar. <br />
     * <b>false</b> si ocurre un error.
     * @throws SQLException Si ocurre un error con la base de datos.
     */
    public boolean eliminar(Participante unParticipante) {
        boolean seElimino = false;
        try {
            seElimino = participanteDAO.eliminar(unParticipante);
        } catch (SQLException e) {
            msgError = e.getMessage();
        } finally {
            try {
                participanteDAO.cerrarConexion();
            } catch (SQLException ex) {
                msgError = "Error al intentar cerrar los recursos utilizados.";
            }
        }

        return seElimino;
    }
    
    public String darMensaje(){
        return msgError;
    }

}
