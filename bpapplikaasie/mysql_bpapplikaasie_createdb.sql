-- MySQL Script generated by MySQL Workbench
-- Fri Jul 13 11:56:52 2018
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema bpapplikaasie
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `bpapplikaasie` ;

-- -----------------------------------------------------
-- Schema bpapplikaasie
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `bpapplikaasie` DEFAULT CHARACTER SET utf8 ;
USE `bpapplikaasie` ;

-- -----------------------------------------------------
-- Table `bpapplikaasie`.`klant`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bpapplikaasie`.`klant` ;

CREATE TABLE IF NOT EXISTS `bpapplikaasie`.`klant` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `voornaam` VARCHAR(50) NOT NULL,
  `achternaam` VARCHAR(50) NOT NULL,
  `tussenvoegsel` VARCHAR(15) NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `bpapplikaasie`.`adrestype`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bpapplikaasie`.`adrestype` ;

CREATE TABLE IF NOT EXISTS `bpapplikaasie`.`adrestype` (
  `id` CHAR(1) NOT NULL,
  `type` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `type_UNIQUE` (`type` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `bpapplikaasie`.`adres`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bpapplikaasie`.`adres` ;

CREATE TABLE IF NOT EXISTS `bpapplikaasie`.`adres` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `straatnaam` VARCHAR(50) NOT NULL,
  `huisnummer` INT NOT NULL,
  `toevoeging` VARCHAR(5) NULL DEFAULT NULL,
  `postcode` VARCHAR(6) NOT NULL,
  `woonplaats` VARCHAR(50) NOT NULL,
  `klant_id` INT NOT NULL,
  `adrestype_id` CHAR(1) NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_adres_klant_idx` (`klant_id` ASC),
  UNIQUE INDEX `uq_klant_adrestype` (`klant_id` ASC),
  INDEX `fk_adres_adrestype1_idx` (`adrestype_id` ASC),
  CONSTRAINT `fk_adres_klant`
    FOREIGN KEY (`klant_id`)
    REFERENCES `bpapplikaasie`.`klant` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_adres_adrestype1`
    FOREIGN KEY (`adrestype_id`)
    REFERENCES `bpapplikaasie`.`adrestype` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `bpapplikaasie`.`bestelling`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bpapplikaasie`.`bestelling` ;

CREATE TABLE IF NOT EXISTS `bpapplikaasie`.`bestelling` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `totaalprijs` DECIMAL(6,2) NOT NULL,
  `datum_tijd` DATETIME NOT NULL,
  `klant_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_bestelling_klant1_idx` (`klant_id` ASC),
  CONSTRAINT `fk_bestelling_klant1`
    FOREIGN KEY (`klant_id`)
    REFERENCES `bpapplikaasie`.`klant` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `bpapplikaasie`.`artikel`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bpapplikaasie`.`artikel` ;

CREATE TABLE IF NOT EXISTS `bpapplikaasie`.`artikel` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `naam` VARCHAR(45) NOT NULL,
  `prijs` DECIMAL(6,2) NOT NULL,
  `voorraad` INT NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `naam_UNIQUE` (`naam` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `bpapplikaasie`.`bestel_regel`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bpapplikaasie`.`bestel_regel` ;

CREATE TABLE IF NOT EXISTS `bpapplikaasie`.`bestel_regel` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `bestelling_id` INT NOT NULL,
  `artikel_id` INT NOT NULL,
  `aantal` INT NULL DEFAULT 1,
  INDEX `fk_bestelling_has_artikel_artikel1_idx` (`artikel_id` ASC),
  INDEX `fk_bestelling_has_artikel_bestelling1_idx` (`bestelling_id` ASC),
  PRIMARY KEY (`id`),
  UNIQUE INDEX `uq_bestelling_artikel` (`bestelling_id` ASC, `artikel_id` ASC),
  CONSTRAINT `fk_bestelling_has_artikel_bestelling1`
    FOREIGN KEY (`bestelling_id`)
    REFERENCES `bpapplikaasie`.`bestelling` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_bestelling_has_artikel_artikel1`
    FOREIGN KEY (`artikel_id`)
    REFERENCES `bpapplikaasie`.`artikel` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `bpapplikaasie`.`account_type`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bpapplikaasie`.`account_type` ;

CREATE TABLE IF NOT EXISTS `bpapplikaasie`.`account_type` (
  `id` CHAR(1) NOT NULL,
  `type` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `type_UNIQUE` (`type` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `bpapplikaasie`.`account`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `bpapplikaasie`.`account` ;

CREATE TABLE IF NOT EXISTS `bpapplikaasie`.`account` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(25) NOT NULL,
  `wachtwoord` VARCHAR(180) NOT NULL,
  `klant_id` INT NULL,
  `account_type_id` CHAR(1) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `username_UNIQUE` (`username` ASC),
  INDEX `fk_account_klant1_idx` (`klant_id` ASC),
  UNIQUE INDEX `klant_id_UNIQUE` (`klant_id` ASC),
  INDEX `fk_account_account_type1_idx` (`account_type_id` ASC),
  CONSTRAINT `fk_account_klant1`
    FOREIGN KEY (`klant_id`)
    REFERENCES `bpapplikaasie`.`klant` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_account_account_type1`
    FOREIGN KEY (`account_type_id`)
    REFERENCES `bpapplikaasie`.`account_type` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
