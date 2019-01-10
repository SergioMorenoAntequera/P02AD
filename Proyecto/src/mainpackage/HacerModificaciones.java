/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mainpackage;

import com.mysql.jdbc.Connection;
import java.sql.Blob;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Juanra
 */
public class HacerModificaciones {
    //Para poder crear tablas, tenemos que conectarnos a la base de datos. Por lo tanto vamos a conectarnos
    //Creamos el objeto que nos dara la conexion
    static private Connection con;
    //Creamos el objeto que nos permitira ejecutar sentencias
    static private Statement sentencia;
    //Creamos el objeto que nos confirmará que los datos se han modificado
    static private int correcto;

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
            //Cuando nos hayamos conectado, procedemos a crear hacer las consultas
            //Creamos la sentencia
            sentencia = con.createStatement();
            System.out.println("Sentencia creada con exito");
            //Realizamos la consulta y guardamos los datos

            correcto=sentencia.executeUpdate("update alumno set Nombre='Angel', Delegado=0 where Expediente=3"); 
            if(correcto==1){
                System.out.println("Alumno modificado");
            }
            
            correcto=sentencia.executeUpdate("update modulo_alumno set notaFinal=6 where Codigo_alumno=4");           
            if(correcto==1){
                System.out.println("Alumno modificado");
            }
            
            correcto=sentencia.executeUpdate("delete from alumno where Expediente=4");           
            if(correcto==1){
                System.out.println("Alumno modificado");
            }
            
            correcto=sentencia.executeUpdate("delete from modulo where Codigo=4");           
            if(correcto==1){
                System.out.println("Alumno modificado");
            }
            
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
