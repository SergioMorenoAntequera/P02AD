/*Crear base de datos*/
create database if not exists Instituto;

/*Usar la base de datos que hemos creado*/

use Instituto;

/*Primera tabla*/

CREATE TABLE `instituto`.`alumno` (
  `Expediente` INT NOT NULL,
  `Nombre` VARCHAR(25) NOT NULL,
  `ApellidoP` VARCHAR(25) NOT NULL,
  `ApellidoM` VARCHAR(25) NOT NULL,
  `FechaNac` DATE NOT NULL,
  `Delegado` BINARY NULL,
  PRIMARY KEY (`Expediente`),
  UNIQUE INDEX `Expediente_UNIQUE` (`Expediente` ASC));

/*Segunda tabla*/

CREATE TABLE `instituto`.`modulo` (
  `Codigo` INT NOT NULL,
  `Nombre` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`Codigo`));

/*Tercera tabla*/

CREATE TABLE `instituto`.`modulo_alumno` (
  `idModulo_Alumno` INT NOT NULL,
  `Codigo_alumno` INT NOT NULL,
  `Codigo_modulo` INT NOT NULL,
  PRIMARY KEY (`idModulo_Alumno`),
  INDEX `matriculado_m_idx` (`Codigo_modulo` ASC),
  INDEX `matriculado_a_idx` (`Codigo_alumno` ASC),
  CONSTRAINT `matriculado_m`
    FOREIGN KEY (`Codigo_modulo`)
    REFERENCES `instituto`.`modulo` (`Codigo`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE,
  CONSTRAINT `matriculado_a`
    FOREIGN KEY (`Codigo_alumno`)
    REFERENCES `instituto`.`alumno` (`Expediente`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);
	
/*Cuarta tabla*/

CREATE TABLE `instituto`.`profesor` (
  `R.F.C` CHAR(15) NOT NULL,
  `Nombre` VARCHAR(25) NOT NULL,
  `ApellidoP` VARCHAR(25) NOT NULL,
  `ApellidoM` VARCHAR(25) NOT NULL,
  `Direccion` VARCHAR(25) NOT NULL,
  `Telefono` CHAR(10) NOT NULL,
  `Codigo_modulo` INT NOT NULL,
  PRIMARY KEY (`R.F.C`),
  UNIQUE INDEX `R.F.C_UNIQUE` (`R.F.C` ASC),
  INDEX `imparte` (`Codigo_modulo` ASC),
  CONSTRAINT `imparte`
    FOREIGN KEY (`Codigo_modulo`)
    REFERENCES `instituto`.`modulo` (`Codigo`)
    ON DELETE RESTRICT
    ON UPDATE CASCADE);
