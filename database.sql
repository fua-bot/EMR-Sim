-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema hospital
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema hospital
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `hospital` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `hospital` ;

-- -----------------------------------------------------
-- Table `hospital`.`allergy`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `hospital`.`allergy` (
  `mrn` INT NOT NULL,
  `item` VARCHAR(250) NULL DEFAULT NULL)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `hospital`.`med_history`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `hospital`.`med_history` (
  `mrn` INT NOT NULL,
  `item` VARCHAR(250) NULL DEFAULT NULL)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `hospital`.`medication`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `hospital`.`medication` (
  `mrn` INT NOT NULL,
  `item` VARCHAR(250) NULL DEFAULT NULL)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `hospital`.`patients`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `hospital`.`patients` (
  `surname` VARCHAR(15) NOT NULL,
  `first_name` VARCHAR(15) NOT NULL,
  `initial` VARCHAR(1) NOT NULL,
  `dob` DATE NOT NULL,
  `mrn` INT NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`mrn`),
  UNIQUE INDEX `mrn_UNIQUE` (`mrn` ASC) VISIBLE)
ENGINE = InnoDB
AUTO_INCREMENT = 6
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `hospital`.`soc_history`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `hospital`.`soc_history` (
  `mrn` INT NOT NULL,
  `item` VARCHAR(250) NULL DEFAULT NULL)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `hospital`.`surg_history`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `hospital`.`surg_history` (
  `mrn` INT NOT NULL,
  `item` VARCHAR(250) NULL DEFAULT NULL)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `hospital`.`vitals`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `hospital`.`vitals` (
  `mrn` INT NOT NULL,
  `item` VARCHAR(250) NULL DEFAULT NULL)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
