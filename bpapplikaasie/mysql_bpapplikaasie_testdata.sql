use bpapplikaasie;

-- -----------------------------------------------------
-- Table `bpapplikaasie`.`klant`
-- -----------------------------------------------------
-- DROP TABLE IF EXISTS `bpapplikaasie`.`klant` ;
-- 
-- CREATE TABLE IF NOT EXISTS `bpapplikaasie`.`klant` (
--   `id` INT NOT NULL AUTO_INCREMENT,
--   `voornaam` VARCHAR(50) NOT NULL,
--   `achternaam` VARCHAR(50) NOT NULL,
--   `tussenvoegsel` VARCHAR(15) NULL DEFAULT NULL,
--   PRIMARY KEY (`id`))

INSERT INTO klant (voornaam, achternaam, tussenvoegsel) VALUES ("tom", "vos", "de");
INSERT INTO klant (voornaam, achternaam) VALUES ("boer", "piet");

-- -----------------------------------------------------
-- Table `bpapplikaasie`.`artikel`
-- -----------------------------------------------------
-- DROP TABLE IF EXISTS `bpapplikaasie`.`artikel` ;
-- 
-- CREATE TABLE IF NOT EXISTS `bpapplikaasie`.`artikel` (
--   `id` INT NOT NULL AUTO_INCREMENT,
--   `naam` VARCHAR(45) NOT NULL,
--   `prijs` DECIMAL(6,2) NOT NULL,
--   `voorraad` INT NULL DEFAULT NULL,
--   PRIMARY KEY (`id`),
--   UNIQUE INDEX `naam_UNIQUE` (`naam` ASC))

INSERT INTO artikel (naam, prijs, voorraad) VALUES ("brandnetel", 6, 20);
INSERT INTO artikel (naam, prijs, voorraad) VALUES ("fenegriek", 6.5, 25);
INSERT INTO artikel (naam, prijs, voorraad) VALUES ("mosterd", 5.5, 15);
INSERT INTO artikel (naam, prijs, voorraad) VALUES ("schimmel", 5, 18);
INSERT INTO artikel (naam, prijs, voorraad) VALUES ("goudse", 4.5, 24);

-- -----------------------------------------------------
-- Table `bpapplikaasie`.`account`
-- -----------------------------------------------------
-- DROP TABLE IF EXISTS `bpapplikaasie`.`account` ;
-- 
-- CREATE TABLE IF NOT EXISTS `bpapplikaasie`.`account` (
--   `id` INT NOT NULL AUTO_INCREMENT,
--   `username` VARCHAR(25) NOT NULL,
--   `wachtwoord` VARCHAR(180) NOT NULL,
--   `accounttype` ENUM('admin', 'klant', 'medewerker') NOT NULL,
--   `klant_id` INT NULL,
--   PRIMARY KEY (`id`),
--   UNIQUE INDEX `username_UNIQUE` (`username` ASC),
--   INDEX `fk_account_klant1_idx` (`klant_id` ASC),
--   UNIQUE INDEX `klant_id_UNIQUE` (`klant_id` ASC),
--   CONSTRAINT `fk_account_klant1`
--     FOREIGN KEY (`klant_id`)
--     REFERENCES `bpapplikaasie`.`klant` (`id`)
--     ON DELETE NO ACTION
--     ON UPDATE NO ACTION)

INSERT INTO account (username, wachtwoord, accounttype, klant_id) VALUES
	("tomdevos", "tomtom", 'medewerker',
	(SELECT id FROM klant WHERE voornaam = 'tom'));
INSERT INTO account (username, wachtwoord, accounttype, klant_id) VALUES
	("boerpiet", "pietpiet", 1,
    (SELECT id FROM klant WHERE voornaam = 'boer'));