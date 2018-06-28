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
-- Table `bpapplikaasie`.`account_type`
-- -----------------------------------------------------
-- DROP TABLE IF EXISTS `bpapplikaasie`.`account_type` ;
-- 
-- CREATE TABLE IF NOT EXISTS `bpapplikaasie`.`account_type` (
--   `id` CHAR(1) NOT NULL,
--   `type` VARCHAR(45) NOT NULL,
--   PRIMARY KEY (`id`),
--   UNIQUE INDEX `type_UNIQUE` (`type` ASC))

INSERT INTO account_type (id, type) VALUES ("A", "admin");
INSERT INTO account_type (id, type) VALUES ("M", "medewerker");
INSERT INTO account_type (id, type) VALUES ("K", "klant");

-- -----------------------------------------------------
-- Table `bpapplikaasie`.`account`
-- -----------------------------------------------------
-- DROP TABLE IF EXISTS `bpapplikaasie`.`account` ;
-- 
-- CREATE TABLE IF NOT EXISTS `bpapplikaasie`.`account` (
--   `id` INT NOT NULL AUTO_INCREMENT,
--   `username` VARCHAR(25) NOT NULL,
--   `wachtwoord` VARCHAR(180) NOT NULL,
--   `klant_id` INT NOT NULL,
--   `account_type_id` CHAR(1) NOT NULL,
--   PRIMARY KEY (`id`),
--   UNIQUE INDEX `username_UNIQUE` (`username` ASC),
--   INDEX `fk_account_klant1_idx` (`klant_id` ASC),
--   UNIQUE INDEX `klant_id_UNIQUE` (`klant_id` ASC),
--   INDEX `fk_account_account_type1_idx` (`account_type_id` ASC),
--   CONSTRAINT `fk_account_klant1`
--     FOREIGN KEY (`klant_id`)
--     REFERENCES `bpapplikaasie`.`klant` (`id`)
--     ON DELETE NO ACTION
--     ON UPDATE NO ACTION,
--   CONSTRAINT `fk_account_account_type1`
--     FOREIGN KEY (`account_type_id`)
--     REFERENCES `bpapplikaasie`.`account_type` (`id`)
--     ON DELETE NO ACTION
--     ON UPDATE NO ACTION)

INSERT INTO account (username, wachtwoord, klant_id, account_type_id) VALUES
	("tomdevos", "tomtom",
	(SELECT id FROM klant WHERE voornaam = 'tom'),
    (SELECT id FROM account_type WHERE type = 'medewerker'));
-- moet "M" zijn
INSERT INTO account (username, wachtwoord, klant_id, account_type_id) VALUES
	("boerpiet", "pietpiet",
    (SELECT id FROM klant WHERE voornaam = 'boer'),
    (SELECT id FROM account_type WHERE type = 'admin'));
-- moet "A" zijn