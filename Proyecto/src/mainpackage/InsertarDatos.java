/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mainpackage;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Juanra
 */
public class InsertarDatos {
    
    //Para poder insertar datos, tenemos que conectarnos a la base de datos. Por lo tanto vamos a conectarnos
    //Creamos el objeto que nos dara la conexion
    static private Connection con;
    //Creamos el objeto que nos permitira ejecutar sentencias
    static private Statement sentencia;

    //Todos los objetos que creemos tenemos que cerrarlos en el finally

    public static void main(String[] args) {

        try {

            //Cargar el driver de mysql
            Class.forName("com.mysql.jdbc.Driver");

            //Cadena de conexion para conectar con MySQL en localhost,
            //seleccionar la base de datos llamada 'instituto'
            //con usuario y contraseña del servidor de MySQL: root y juanra(en mi caso)
            String connectionUrl = "jdbc:mysql://localhost/instituto?user=root&password=juanra&useSSL=false";

            //Obtener la conexion
            con = (Connection) DriverManager.getConnection(connectionUrl);

            System.out.println("Conexion establecida con exito");
            //------------------------------------------------------------------
            //Cuando nos hayamos conectado, procedemos a insertar los datos
            //Creamos la sentencia
            sentencia = con.createStatement();
            System.out.println("Sentencia creada con exito");
            //Ejecutamos las cadenas para crear tablas
            //Utilizamos executeUpdate ya que vamos a modificar los datos de la base de datos
            sentencia.executeUpdate("insert into alumno values (1,'Juan Raul','Mellado','Garcia','1997-11-30',0),"
                    + "(2,'Sergio','Moreno','Antequera','1999-12-24',1),"
                    + "(3,'Angel','Salas','Calvo','1998-10-12',0),"
                    + "(4,'Javier','Gonzalez','Rives','1997-08-10',0)");
            sentencia.executeUpdate("insert into modulo values (1,'ACDAT'),"
                    + "(2,'PSP')");
            sentencia.executeUpdate("insert into modulo_alumno values (102, 1, 2),"
                    + "(201, 2, 1),"
                    + "(301, 3, 1),"
                    + "(402, 4, 2)");
            sentencia.executeUpdate("insert into profesor values ('abc','Francisco','Benitez','Gonzalez','Calle Tierra 1 bajo a','692456789',1),"
                    + "('def','Paco','Balsamore','Rodrigez','Calle Tierra 2 bajo b','692789456',2),"
                    + "('ghi','Juan','Jimenez','Rubio','Calle Tierra 3 bajo c','692123456',2)");
            
            System.out.println("Datos introducidos con exito");

        } catch (SQLException e) {
            System.out.println("SQL Exception: " + e.toString());
        } catch (ClassNotFoundException cE) {
            System.out.println("Exception: " + cE.toString());
        } finally {
            if (sentencia != null) {
                try {
                    sentencia.close();
                    System.out.println("Sentencia cerrada con exito");
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            }
            if (con != null) {
                try {
                    con.close();
                    System.out.println("Conexion cerrada con exito");
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        }
    }
}
