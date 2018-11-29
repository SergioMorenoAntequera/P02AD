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
public class HacerConsultas {

    //Para poder crear tablas, tenemos que conectarnos a la base de datos. Por lo tanto vamos a conectarnos
    //Creamos el objeto que nos dara la conexion
    static private Connection con;
    //Creamos el objeto que nos permitira ejecutar sentencias
    static private Statement sentencia;
    //Creamos el objeto que nos guardara el resultado de la consulta
    static private ResultSet result;

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
            
            result = sentencia.executeQuery("select * from alumno");
            
            System.out.println("Sentencia establecida con exito\n");
            //Imprimimos los datos
            while (result.next()) {
                //-----------Esto es para binarys-----------------
                Blob prueba=result.getBlob("Delegado");
                byte[] bytes = prueba.getBytes(1, (int) prueba.length());
                String ultimo=new String(bytes);
                String delegado="No";
                if(ultimo.equals("1")){
                    delegado="Si";
                }
                //-----------Esto es para binarys-----------------
                System.out.println("Alumno: "+result.getInt("Expediente")+"\nNombre: "+result.getString("Nombre")+"\nApellidoP: "+result.getString("ApellidoP")+"\nApellidoM: "+result.getString("ApellidoM")+"\nFecha de nacimiento: "+result.getDate("FechaNac")+"\nDelegado: "+delegado+"\n");  
            }

        } catch (SQLException e) {
            System.out.println("SQL Exception: " + e.toString());
        } catch (ClassNotFoundException cE) {
            System.out.println("Exception: " + cE.toString());
        } finally {
            if (result != null) {
                try {
                    result.close();
                    System.out.println("Result cerrado con exito");
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            }
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
