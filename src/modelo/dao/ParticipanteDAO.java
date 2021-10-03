/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.bo.Participante;
import modelo.conexion.Conexion;

/**
 *
 * @author YCA
 */
public class ParticipanteDAO {

    public final static String BD_PK_ALIAS = "alias";

    public final static String BD_CL_NOMBRE = "nombre";

    public final static String BD_CL_APELLIDO = "apellido";

    public final static String BD_CL_EMAIL = "email";

    public final static String BD_CL_CELULAR = "celular";

    public final static String BD_CL_CONTRASENA = "contrasena";

    public final static String BD_CL_FECHA_NACIMIENTO = "fecha_nto";

    public final static String BD_TABLA = "participante";

    /**
     * Establece la conexión con la base de datos.
     */
    private Connection conexion = null;

    /**
     * Ejecuta querys sobre la tabla.
     */
    private PreparedStatement pstm = null;

    /**
     * Guarda el resultado de los querys
     */
    private ResultSet rs = null;

    public ParticipanteDAO() {
    }

    /**
     * Retorna un participante realizada una búsqueda.
     *
     * @param unParticipante Objeto que encapsula el id del participante para
     * realizar la búsqueda. <br />
     * @return Un participante
     * <b>null</b> si no encuentra un participante.
     * @throws SQLException Si ocurre un error con la base de datos.
     */
    public Participante buscarPorClave(Participante unParticipante) throws SQLException {
        Participante participante = null;
        // establece la conexion
        conexion = Conexion.darConexion();
        // define un query
        String sql = "";
        sql += "select * from " + BD_TABLA + " ";
        sql += "where " + BD_PK_ALIAS + " = ? ";

        // prepara el query a ejecutar.
        pstm = conexion.prepareStatement(sql);

        // seteamos los valores de los parametros
        pstm.setString(1, unParticipante.getAlias());

        // ejecuta el query y guarda el resultado en rs.
        rs = pstm.executeQuery();

        if (rs != null) {
            rs.next();
            // contruye un participante.
            participante = new Participante();
            participante.setAlias(rs.getString(BD_PK_ALIAS));
            participante.setNombre(rs.getString(BD_CL_NOMBRE));
            participante.setApellido(rs.getString(BD_CL_APELLIDO));
            participante.setEmail(rs.getString(BD_CL_EMAIL));
            participante.setCelular(rs.getString(BD_CL_CELULAR));
            participante.setContrasena(rs.getString(BD_CL_CONTRASENA));
            participante.setFehcaNacimiento(rs.getDate(BD_CL_FECHA_NACIMIENTO));
        }
        // al finalizar retorna el participante.
        return participante;
    }

    /**
     * Retorna una lista de participantes.
     *
     * @return una lista de participantes
     * @throws SQLException Si ocurre un error con la base de datos.
     */
    public List<Participante> buscarTodos() throws SQLException {
        // crea una lista de participantes.
        ArrayList<Participante> listaParticipante = new ArrayList<>();
        // establece la conexion
        conexion = Conexion.darConexion();
        // define un query
        String sql = "";
        sql += "select * from " + BD_TABLA + " ";

        // prepara el query a ejecutar.
        pstm = conexion.prepareStatement(sql);

        // ejecuta el query y guarda el resultado en rs.
        rs = pstm.executeQuery();

        Participante participante = null;

        while (rs.next()) {
            // construye un participante.
            participante = new Participante();
            participante.setAlias(rs.getString(BD_PK_ALIAS));
            participante.setNombre(rs.getString(BD_CL_NOMBRE));
            participante.setApellido(rs.getString(BD_CL_APELLIDO));
            participante.setEmail(rs.getString(BD_CL_EMAIL));
            participante.setCelular(rs.getString(BD_CL_CELULAR));
            participante.setContrasena(rs.getString(BD_CL_CONTRASENA));
            participante.setFehcaNacimiento(rs.getDate(BD_CL_FECHA_NACIMIENTO));
            // agrega el participante a la lista.
            listaParticipante.add(participante);

        }
        // al finalizar retorna la lista
        return listaParticipante;
    }

    /**
     * Retorna una lista de participantes, segun un criterio de busqueda.
     *
     * @return una lista de participantes
     * @throws SQLException Si ocurre un error con la base de datos.
     */
    public List<Participante> buscarTodos(Participante unParticipante) throws SQLException {
        // crea una lista de participantes.
        ArrayList<Participante> listaParticipante = new ArrayList<>();
        // establece la conexion
        conexion = Conexion.darConexion();
        // define un query
        String sql = "";
        sql += "select * from " + BD_TABLA + " ";
        sql += "where " + BD_PK_ALIAS + " like ? ";

        // prepara el query a ejecutar.
        pstm = conexion.prepareStatement(sql);
        
        // seteamos los valores de los parametros
        pstm.setString(1, "%"+unParticipante.getAlias()+"%");

        // ejecuta el query y guarda el resultado en rs.
        rs = pstm.executeQuery();

        Participante participante = null;

        while (rs.next()) {
            // construye un participante.
            participante = new Participante();
            participante.setAlias(rs.getString(BD_PK_ALIAS));
            participante.setNombre(rs.getString(BD_CL_NOMBRE));
            participante.setApellido(rs.getString(BD_CL_APELLIDO));
            participante.setEmail(rs.getString(BD_CL_EMAIL));
            participante.setCelular(rs.getString(BD_CL_CELULAR));
            participante.setContrasena(rs.getString(BD_CL_CONTRASENA));
            participante.setFehcaNacimiento(rs.getDate(BD_CL_FECHA_NACIMIENTO));
            // agrega el participante a la lista.
            listaParticipante.add(participante);

        }
        // al finalizar retorna la lista
        return listaParticipante;
    }

    /**
     * Guarda un participante en la base de datos.
     *
     * @param unParticipante Objeto que encapsula las características de un
     * participante a guardar. <br />
     * @return <b>true</b> Si se pudo guardar. <br />
     * <b>false</b> si ocurre un error.
     * @throws SQLException Si ocurre un error con la base de datos.
     */
    public boolean guardar(Participante unParticipante) throws SQLException {
        // respuesta por defecto 0 filas afectadas
        int respuesta = 0;
        // establece la conexion
        conexion = Conexion.darConexion();
        // define un query
        String sql = "";
        sql += "insert into " + BD_TABLA + " values(?,?,?,?,?,?,?)";

        // prepara el query a ejecutar
        pstm = conexion.prepareStatement(sql);
        
        // seteamos los valores de los parametros
        pstm.setString(1, unParticipante.getAlias());
        pstm.setString(2, unParticipante.getNombre());
        pstm.setString(3, unParticipante.getApellido());
        pstm.setString(4, unParticipante.getEmail());
        pstm.setString(5, unParticipante.getCelular());
        pstm.setString(6, unParticipante.getContrasena());
        pstm.setDate(7, unParticipante.getFehcaNacimiento());

        // ejecuta el query y guarda el resultado de filas afectadas en
        // respuesta
        respuesta = pstm.executeUpdate();

        // retorna la respuesta
        if (respuesta >= 1) {
            return true;
        } else {
            return false;
        }
    }
    
    /**
     * Actualiza los datos de un participante.
     * 
     * @param unParticipante Objeto que encapsula el id del participante para
     * poder actualizar. <br />
     * @return <b>true</b> Si se pudo actualizar. <br />
     * <b>false</b> si ocurre un error.
     * @throws SQLException Si ocurre un error con la base de datos.
     */
    public boolean actualizar(Participante unParticipante) throws SQLException {
        // respuesta por defecto 0 filas afectadas
        int respuesta = 0;
        // establece la conexion
        conexion = Conexion.darConexion();
        // define un query
        String sql = "";
        sql += "update " + BD_TABLA + " set ";
        sql += BD_CL_NOMBRE + " = ?, ";
        sql += BD_CL_APELLIDO + " = ?, ";
        sql += BD_CL_EMAIL + " = ?, ";
        sql += BD_CL_CELULAR + " = ?, ";
        sql += BD_CL_CONTRASENA + " = ?, ";
        sql += BD_CL_FECHA_NACIMIENTO + " = ? ";
        sql += "where " + BD_PK_ALIAS + " = ? ";

        // prepara el query a ejecutar
        pstm = conexion.prepareStatement(sql);

        // seteamos los valores de los parametros
        pstm.setString(1, unParticipante.getNombre());
        pstm.setString(2, unParticipante.getApellido());
        pstm.setString(3, unParticipante.getEmail());
        pstm.setString(4, unParticipante.getCelular());
        pstm.setString(5, unParticipante.getContrasena());
        pstm.setDate(6, unParticipante.getFehcaNacimiento());
        pstm.setString(7, unParticipante.getAlias());

        // ejecuta el query y guarda el resultado de filas afectadas en
        // respuesta
        respuesta = pstm.executeUpdate();

        if (respuesta >= 1) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Elimina un participante en la base de datos.
     *
     * @param unParticipante Objeto que encapsula el id del participante para
     * poder eliminar. <br />
     * @return <b>true</b> Si se pudo eliminar. <br />
     * <b>false</b> si ocurre un error.
     * @throws SQLException Si ocurre un error con la base de datos.
     */
    public boolean eliminar(Participante unParticipante) throws SQLException {
        // respuesta por defecto 0 filas afectadas
        int respuesta = 0;
        // establece la conexion
        conexion = Conexion.darConexion();
        // define un query
        String sql = "";
        sql += "delete from " + BD_TABLA + " ";
        sql += "where " + BD_PK_ALIAS + " = ? ";

        // prepara el query a ejecutar
        pstm = conexion.prepareStatement(sql);

        // seteamos los valores de los parametros
        pstm.setString(1, unParticipante.getAlias());

        // ejecuta el query y guarda el resultado de filas afectadas en
        // respuesta
        respuesta = pstm.executeUpdate();

        if (respuesta >= 1) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Cierra todos los recursos utilizados.
     *
     * @throws SQLException Si ocurre un error con la base de datos.
     */
    public void cerrarConexion() throws SQLException {
        // cierra todos los recursos en orden inverso en que fueron
        // adquiridos
        if (rs != null) {
            rs.close();
        }

        if (pstm != null) {
            pstm.close();
        }
    }
}
