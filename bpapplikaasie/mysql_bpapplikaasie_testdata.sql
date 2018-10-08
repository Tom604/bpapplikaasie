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

INSERT INTO klant (voornaam, achternaam, tussenvoegsel) VALUES ("joop", "groes", "van");
INSERT INTO klant (voornaam, achternaam) VALUES ("kees", "herns");
INSERT INTO klant (voornaam, achternaam, tussenvoegsel) VALUES ("bert", "vriend", "de");
INSERT INTO klant (voornaam, achternaam) VALUES ("jan", "brift");

-- -----------------------------------------------------
-- Table `bpapplikaasie`.`adres`
-- -----------------------------------------------------
-- DROP TABLE IF EXISTS `bpapplikaasie`.`adres` ;
-- 
-- CREATE TABLE IF NOT EXISTS `bpapplikaasie`.`adres` (
--   `id` INT NOT NULL AUTO_INCREMENT,
--   `straatnaam` VARCHAR(50) NOT NULL,
--   `huisnummer` INT NOT NULL,
--   `toevoeging` VARCHAR(5) NULL DEFAULT NULL,
--   `postcode` VARCHAR(6) NOT NULL,
--   `woonplaats` VARCHAR(50) NOT NULL,
--   `adrestype` ENUM('factuur', 'post', 'woon') NOT NULL,
--   `klant_id` INT NOT NULL,
--   PRIMARY KEY (`id`),
--   INDEX `fk_adres_klant_idx` (`klant_id` ASC),
--   UNIQUE INDEX `uq_klant_adrestype` (`klant_id` ASC, `adrestype` ASC),
--   CONSTRAINT `fk_adres_klant`
--     FOREIGN KEY (`klant_id`)
--     REFERENCES `bpapplikaasie`.`klant` (`id`)
--     ON DELETE CASCADE
--     ON UPDATE NO ACTION)
-- ENGINE = InnoDB;

INSERT INTO adres (straatnaam, huisnummer, toevoeging, postcode, woonplaats, adrestype, klant_id) VALUES
	("voort", 3, null, "3454AF", "utrecht", "post", "1");
INSERT INTO adres (straatnaam, huisnummer, toevoeging, postcode, woonplaats, adrestype, klant_id) VALUES
	("kruit", 56, "a", "2754EB", "voorthuizen", "post", "2");
INSERT INTO adres (straatnaam, huisnummer, toevoeging, postcode, woonplaats, adrestype, klant_id) VALUES
	("pollepel", 14, null, "2753AD", "voorthuizen", "factuur", "2");
INSERT INTO adres (straatnaam, huisnummer, toevoeging, postcode, woonplaats, adrestype, klant_id) VALUES
	("penning", 5, "bis", "3454AF", "giezemonden", "woon", "3");

-- -----------------------------------------------------
-- Table `bpapplikaasie`.`bestelling`
-- -----------------------------------------------------
-- DROP TABLE IF EXISTS `bpapplikaasie`.`bestelling` ;
-- 
-- CREATE TABLE IF NOT EXISTS `bpapplikaasie`.`bestelling` (
--   `id` INT NOT NULL AUTO_INCREMENT,
--   `totaalprijs` DECIMAL(6,2) NOT NULL,
--   `datum_tijd` TIMESTAMP(3) NOT NULL,
--   `klant_id` INT NOT NULL,
--   PRIMARY KEY (`id`),
--   INDEX `fk_bestelling_klant_idx` (`klant_id` ASC),
--   CONSTRAINT `fk_bestelling_klant`
--     FOREIGN KEY (`klant_id`)
--     REFERENCES `bpapplikaasie`.`klant` (`id`)
--     ON DELETE CASCADE
--     ON UPDATE NO ACTION)
-- ENGINE = InnoDB;

INSERT INTO bestelling (totaalprijs, datum_tijd, klant_id) VALUES (69.75, '2015-10-25 14:37:01', 1);
INSERT INTO bestelling (totaalprijs, datum_tijd, klant_id) VALUES (102.5, '2016-09-13 08:57:58', 2);
INSERT INTO bestelling (totaalprijs, datum_tijd, klant_id) VALUES (53.75, '2018-08-13 22:43:23', 1);
INSERT INTO bestelling (totaalprijs, datum_tijd, klant_id) VALUES (76.5, '2018-05-26 10:37:55', 4);

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

INSERT INTO artikel (naam, prijs, voorraad) VALUES ("brandnetel", 6, 21);
INSERT INTO artikel (naam, prijs, voorraad) VALUES ("fenegriek", 6.5, 22);
INSERT INTO artikel (naam, prijs, voorraad) VALUES ("mosterd", 5.25, 15);
INSERT INTO artikel (naam, prijs, voorraad) VALUES ("schimmel", 5, 18);
INSERT INTO artikel (naam, prijs, voorraad) VALUES ("goudse", 4.5, 24);

-- -----------------------------------------------------
-- Table `bpapplikaasie`.`bestelregel`
-- -----------------------------------------------------
-- DROP TABLE IF EXISTS `bpapplikaasie`.`bestelregel` ;
-- 
-- CREATE TABLE IF NOT EXISTS `bpapplikaasie`.`bestelregel` (
--   `id` INT NOT NULL AUTO_INCREMENT,
--   `aantal` INT NULL DEFAULT 1,
--   `bestelling_id` INT NOT NULL,
--   `artikel_id` INT NOT NULL,
--   INDEX `fk_bestelling_has_artikel_artikel1_idx` (`artikel_id` ASC),
--   INDEX `fk_bestelling_has_artikel_bestelling1_idx` (`bestelling_id` ASC),
--   PRIMARY KEY (`id`),
--   UNIQUE INDEX `uq_bestelling_artikel` (`bestelling_id` ASC, `artikel_id` ASC),
--   CONSTRAINT `fk_bestelling_has_artikel_bestelling1`
--     FOREIGN KEY (`bestelling_id`)
--     REFERENCES `bpapplikaasie`.`bestelling` (`id`)
--     ON DELETE CASCADE
--     ON UPDATE NO ACTION,
--   CONSTRAINT `fk_bestelling_has_artikel_artikel1`
--     FOREIGN KEY (`artikel_id`)
--     REFERENCES `bpapplikaasie`.`artikel` (`id`)
--     ON DELETE CASCADE
--     ON UPDATE NO ACTION)
-- ENGINE = InnoDB;

INSERT INTO bestelregel (aantal, bestelling_id, artikel_id) VALUES (4, 1, 1);
INSERT INTO bestelregel (aantal, bestelling_id, artikel_id) VALUES (3, 1, 2);
INSERT INTO bestelregel (aantal, bestelling_id, artikel_id) VALUES (5, 1, 3);

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
	("tomdevos", "tomtom", 'medewerker', null);
INSERT INTO account (username, wachtwoord, accounttype, klant_id) VALUES
	("boerpiet",
    "sha1:64000:18:7dOUeYCQBgTq48a+34Hn/wlnL2NN8SCF:DI4SMsJuzgG4PET75wmc9qaN",
    1, null);
INSERT INTO account (username, wachtwoord, accounttype, klant_id) VALUES
	("joop", "groesgroes", 'klant',
    (SELECT id FROM klant WHERE voornaam = 'joop'));
INSERT INTO account (username, wachtwoord, accounttype, klant_id) VALUES
	("kees", "hernsherns", 2, 2);
INSERT INTO account (username, wachtwoord, accounttype, klant_id) VALUES
	("bert", "vriendvriend", 'klant', 3);
INSERT INTO account (username, wachtwoord, accounttype, klant_id) VALUES
	("jan", "briftbrift", 2, 4);
