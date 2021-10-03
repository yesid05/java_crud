/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 *
 * @author YCA
 */
public class Conexion {

    /**
     * Constante para la ruta del archivo de configuracion
     */
    public static String BD_CONF_RUTA = "modelo.conexion.conf";

    /**
     * Constante para el usuario bd
     */
    public static String BD_USR = "usr";

    /**
     * Constante para la contraseña de bd
     */
    public static String BD_PWD = "pwd";

    /**
     * Constante para el driver de la bd
     */
    public static String BD_DRIVER = "driver";

    /**
     * Constante para la ruta de bd
     */
    public static String BD_URL = "url";

    /**
     * variable para guardar la conexion
     */
    private static Connection con = null;

    public static Connection darConexion() {
        try {

            if (con == null) {


                // carga el archivo de configuracion de la bd
                ResourceBundle rb = ResourceBundle.getBundle(BD_CONF_RUTA);

                // obtenemos los parametros de la conexion
                String usr = rb.getString(BD_USR);
                String pwd = rb.getString(BD_PWD);
                String driver = rb.getString(BD_DRIVER);
                String url = rb.getString(BD_URL);

                // levanto el driver
                Class.forName(driver);

                // se establece la conexion
                con = DriverManager.getConnection(url, usr, pwd);
            }

            return con;
        } catch (SQLException e) {
            System.out.println(e.getMessage());

        } catch (Exception e) {
            // TODO: handle exception
        }
        return con;
    }

}
