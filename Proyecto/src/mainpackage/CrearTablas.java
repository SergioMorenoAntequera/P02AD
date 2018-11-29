/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mainpackage;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Juanra
 */
public class CrearTablas {

    //Para poder crear tablas, tenemos que conectarnos a la base de datos. Por lo tanto vamos a conectarnos
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
            String connectionUrl = "jdbc:mysql://localhost/instituto?user=root&password=juanra&useSSL=false";//CAMBIAR LA CONTRASEÑA

            //Obtener la conexion
            con = (Connection) DriverManager.getConnection(connectionUrl);

            System.out.println("Conexion establecida con exito");
            //------------------------------------------------------------------
            //Cuando nos hayamos conectado, procedemos a crear las tablas
            //Creamos la sentencia
            sentencia = con.createStatement();
            
            System.out.println("Sentencia establecida con exito");
            //Ejecutamos las cadenas para crear tablas
            //Utilizamos executeUpdate ya que vamos a modificar los datos de la base de datos
            //Tambien podriamos utilizar execute
            sentencia.executeUpdate("CREATE TABLE IF NOT EXISTS `instituto`.`alumno` ("
                    + "  `Expediente` INT NOT NULL,"
                    + "  `Nombre` VARCHAR(25) NOT NULL,"
                    + "  `ApellidoP` VARCHAR(25) NOT NULL,"
                    + "  `ApellidoM` VARCHAR(25) NOT NULL,"
                    + "  `FechaNac` DATE NOT NULL,"
                    + "  `Delegado` BINARY NULL,"
                    + "  PRIMARY KEY (`Expediente`),"
                    + "  UNIQUE INDEX `Expediente_UNIQUE` (`Expediente` ASC) VISIBLE)");

            System.out.println("Tabla alumno creada o ya existía");

            sentencia.executeUpdate("CREATE TABLE IF NOT EXISTS `instituto`.`modulo` (\n"
                    + "  `Codigo` INT NOT NULL,\n"
                    + "  `Nombre` VARCHAR(45) NOT NULL,\n"
                    + "  PRIMARY KEY (`Codigo`))");

            System.out.println("Tabla modulo creada o ya existía");

            sentencia.executeUpdate("CREATE TABLE IF NOT EXISTS `instituto`.`modulo_alumno` (\n"
                    + "  `idModulo_Alumno` INT NOT NULL,\n"
                    + "  `Codigo_alumno` INT NOT NULL,\n"
                    + "  `Codigo_modulo` INT NOT NULL,\n"
                    + "  PRIMARY KEY (`idModulo_Alumno`),\n"
                    + "  INDEX `matriculado_m_idx` (`Codigo_modulo` ASC) VISIBLE,\n"
                    + "  INDEX `matriculado_a_idx` (`Codigo_alumno` ASC) VISIBLE,\n"
                    + "  CONSTRAINT `matriculado_m`\n"
                    + "    FOREIGN KEY (`Codigo_modulo`)\n"
                    + "    REFERENCES `instituto`.`modulo` (`Codigo`)\n"
                    + "    ON DELETE RESTRICT\n"
                    + "    ON UPDATE CASCADE,\n"
                    + "  CONSTRAINT `matriculado_a`\n"
                    + "    FOREIGN KEY (`Codigo_alumno`)\n"
                    + "    REFERENCES `instituto`.`alumno` (`Expediente`)\n"
                    + "    ON DELETE CASCADE\n"
                    + "    ON UPDATE CASCADE)");

            System.out.println("Tabla modulo_alumno creada o ya existía");

            sentencia.executeUpdate("CREATE TABLE IF NOT EXISTS `instituto`.`profesor` (\n"
                    + "  `R.F.C` CHAR(15) NOT NULL,\n"
                    + "  `Nombre` VARCHAR(25) NOT NULL,\n"
                    + "  `ApellidoP` VARCHAR(25) NOT NULL,\n"
                    + "  `ApellidoM` VARCHAR(25) NOT NULL,\n"
                    + "  `Direccion` VARCHAR(25) NOT NULL,\n"
                    + "  `Telefono` CHAR(10) NOT NULL,\n"
                    + "  `Codigo_modulo` INT NOT NULL,\n"
                    + "  PRIMARY KEY (`R.F.C`),\n"
                    + "  UNIQUE INDEX `R.F.C_UNIQUE` (`R.F.C` ASC) VISIBLE,\n"
                    + "  INDEX `imparte` (`Codigo_modulo` ASC) VISIBLE,\n"
                    + "  CONSTRAINT `imparte`\n"
                    + "    FOREIGN KEY (`Codigo_modulo`)\n"
                    + "    REFERENCES `instituto`.`modulo` (`Codigo`)\n"
                    + "    ON DELETE RESTRICT\n"
                    + "    ON UPDATE CASCADE)");

            System.out.println("Tabla profesor creada o ya existía");

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
