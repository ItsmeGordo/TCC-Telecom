SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL';

CREATE SCHEMA IF NOT EXISTS `fsbilling` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci ;
USE `fsbilling` ;

-- -----------------------------------------------------
-- Table `fsbilling`.`ADMINISTRADOR`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `fsbilling`.`ADMINISTRADOR` (
  `ID` INT(11) NOT NULL AUTO_INCREMENT ,
  `LOGIN` VARCHAR(45) NOT NULL ,
  `PASSWORD` VARCHAR(100) NOT NULL ,
  `EMAIL` VARCHAR(150) NULL ,
  `PRIMEIRO_NOME` VARCHAR(45) NULL ,
  `ULTIMO_NOME` VARCHAR(100) NULL ,
  PRIMARY KEY (`ID`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `fsbilling`.`PLANO`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `fsbilling`.`PLANO` (
  `ID` INT(11) NOT NULL AUTO_INCREMENT ,
  `NOME_PLANO` VARCHAR(45) NULL ,
  PRIMARY KEY (`ID`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `fsbilling`.`USUARIO`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `fsbilling`.`USUARIO` (
  `ID` INT(11) NOT NULL AUTO_INCREMENT ,
  `USERNAME` VARCHAR(100) NULL ,
  `CALLER_ID` VARCHAR(100) NULL ,
  `PASSWORD` VARCHAR(100) NULL ,
  `CREDIT` DECIMAL NULL ,
  `NOME_COMPLETO` VARCHAR(150) NULL ,
  `EMAIL` VARCHAR(150) NULL ,
  `ID_PLANO` INT(11) NULL ,
  PRIMARY KEY (`ID`) ,
  INDEX `fk_USUARIO_1` (`ID_PLANO` ASC) ,
  CONSTRAINT `fk_USUARIO_1`
    FOREIGN KEY (`ID_PLANO` )
    REFERENCES `fsbilling`.`PLANO` (`ID` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `fsbilling`.`LOG_CREDITO`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `fsbilling`.`LOG_CREDITO` (
  `ID` INT(11) NOT NULL AUTO_INCREMENT ,
  `VALOR_ADICIONADO` DECIMAL NOT NULL ,
  `ID_ADMIN` INT(11) NOT NULL ,
  `ID_USUARIO` INT(11) NOT NULL ,
  PRIMARY KEY (`ID`) ,
  INDEX `fk_CREDIT_LOG_1` (`ID_USUARIO` ASC) ,
  INDEX `fk_CREDIT_LOG_2` (`ID_ADMIN` ASC) ,
  CONSTRAINT `fk_CREDIT_LOG_1`
    FOREIGN KEY (`ID_USUARIO` )
    REFERENCES `fsbilling`.`USUARIO` (`ID` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_CREDIT_LOG_2`
    FOREIGN KEY (`ID_ADMIN` )
    REFERENCES `fsbilling`.`ADMINISTRADOR` (`ID` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `fsbilling`.`PLANO_DE_DISCAGEM`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `fsbilling`.`PLANO_DE_DISCAGEM` (
  `ID` INT(11) NOT NULL AUTO_INCREMENT ,
  `REGULAR_EXPRESSION` VARCHAR(100) NULL ,
  `ACTION` VARCHAR(100) NULL ,
  `ACTION_ATTRIBUTES` VARCHAR(100) NULL ,
  `DESCRICAO` VARCHAR(500) NULL ,
  `ID_PLANO` INT(11) NULL ,
  PRIMARY KEY (`ID`) ,
  INDEX `fk_PLANO_DE_DISCAGEM_1` (`ID_PLANO` ASC) ,
  CONSTRAINT `fk_PLANO_DE_DISCAGEM_1`
    FOREIGN KEY (`ID_PLANO` )
    REFERENCES `fsbilling`.`PLANO` (`ID` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `fsbilling`.`TARIFA_VENDA`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `fsbilling`.`TARIFA_VENDA` (
  `ID` INT(11) NOT NULL AUTO_INCREMENT ,
  `VALOR_VENDA` DECIMAL NULL ,
  `CADENCIA` INT(11) NULL ,
  `DESCRICAO` VARCHAR(500) NULL ,
  `ID_PLANO_DE_DISCAGEM` INT(11) NULL ,
  PRIMARY KEY (`ID`) ,
  INDEX `fk_TARIFA_1` (`ID_PLANO_DE_DISCAGEM` ASC) ,
  CONSTRAINT `fk_TARIFA_1`
    FOREIGN KEY (`ID_PLANO_DE_DISCAGEM` )
    REFERENCES `fsbilling`.`PLANO_DE_DISCAGEM` (`ID` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `fsbilling`.`TARIFA_COMPRA`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `fsbilling`.`TARIFA_COMPRA` (
  `ID` INT(11) NOT NULL AUTO_INCREMENT ,
  `VALOR_COMPRA` DECIMAL NULL ,
  `CADENCIA` INT(11) NULL ,
  `DESCRICAO` VARCHAR(500) NULL ,
  `ID_TARIFA_VENDA` INT(11) NULL ,
  PRIMARY KEY (`ID`) ,
  INDEX `fk_TARIFA_COMPRA_1` (`ID_TARIFA_VENDA` ASC) ,
  CONSTRAINT `fk_TARIFA_COMPRA_1`
    FOREIGN KEY (`ID_TARIFA_VENDA` )
    REFERENCES `fsbilling`.`TARIFA_VENDA` (`ID` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `fsbilling`.`LOG_CREDITO`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `fsbilling`.`LOG_CREDITO` (
  `ID` INT(11) NOT NULL AUTO_INCREMENT ,
  `VALOR_ADICIONADO` DECIMAL NOT NULL ,
  `ID_ADMIN` INT(11) NOT NULL ,
  `ID_USUARIO` INT(11) NOT NULL ,
  PRIMARY KEY (`ID`) ,
  INDEX `fk_CREDIT_LOG_1` (`ID_USUARIO` ASC) ,
  INDEX `fk_CREDIT_LOG_2` (`ID_ADMIN` ASC) ,
  CONSTRAINT `fk_CREDIT_LOG_1`
    FOREIGN KEY (`ID_USUARIO` )
    REFERENCES `fsbilling`.`USUARIO` (`ID` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_CREDIT_LOG_2`
    FOREIGN KEY (`ID_ADMIN` )
    REFERENCES `fsbilling`.`ADMINISTRADOR` (`ID` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `fsbilling`.`PLANO_DE_DISCAGEM`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `fsbilling`.`PLANO_DE_DISCAGEM` (
  `ID` INT(11) NOT NULL AUTO_INCREMENT ,
  `REGULAR_EXPRESSION` VARCHAR(100) NULL ,
  `ACTION` VARCHAR(100) NULL ,
  `ACTION_ATTRIBUTES` VARCHAR(100) NULL ,
  `DESCRICAO` VARCHAR(500) NULL ,
  `ID_PLANO` INT(11) NULL ,
  PRIMARY KEY (`ID`) ,
  INDEX `fk_PLANO_DE_DISCAGEM_1` (`ID_PLANO` ASC) ,
  CONSTRAINT `fk_PLANO_DE_DISCAGEM_1`
    FOREIGN KEY (`ID_PLANO` )
    REFERENCES `fsbilling`.`PLANO` (`ID` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `fsbilling`.`TARIFA_VENDA`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `fsbilling`.`TARIFA_VENDA` (
  `ID` INT(11) NOT NULL AUTO_INCREMENT ,
  `VALOR_VENDA` DECIMAL NULL ,
  `CADENCIA` INT(11) NULL ,
  `DESCRICAO` VARCHAR(500) NULL ,
  `ID_PLANO_DE_DISCAGEM` INT(11) NULL ,
  PRIMARY KEY (`ID`) ,
  INDEX `fk_TARIFA_1` (`ID_PLANO_DE_DISCAGEM` ASC) ,
  CONSTRAINT `fk_TARIFA_1`
    FOREIGN KEY (`ID_PLANO_DE_DISCAGEM` )
    REFERENCES `fsbilling`.`PLANO_DE_DISCAGEM` (`ID` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `fsbilling`.`TARIFA_COMPRA`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `fsbilling`.`TARIFA_COMPRA` (
  `ID` INT(11) NOT NULL AUTO_INCREMENT ,
  `VALOR_COMPRA` DECIMAL NULL ,
  `CADENCIA` INT(11) NULL ,
  `DESCRICAO` VARCHAR(500) NULL ,
  `ID_TARIFA_VENDA` INT(11) NULL ,
  PRIMARY KEY (`ID`) ,
  INDEX `fk_TARIFA_COMPRA_1` (`ID_TARIFA_VENDA` ASC) ,
  CONSTRAINT `fk_TARIFA_COMPRA_1`
    FOREIGN KEY (`ID_TARIFA_VENDA` )
    REFERENCES `fsbilling`.`TARIFA_VENDA` (`ID` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `fsbilling`.`FREESWITCH_CDR`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `fsbilling`.`FREESWITCH_CDR` (
  `CALLER_ID_NAME` VARCHAR(30) NULL ,
  `CALLER_ID_NUMBER` VARCHAR(30) NULL ,
  `DESTINATION_NUMBER` VARCHAR(30) NULL ,
  `CONTEXT` VARCHAR(20) NULL ,
  `START_STAMP` DATETIME NULL ,
  `ANSWER_STAMP` DATETIME NULL ,
  `END_STAMP` DATETIME NULL ,
  `DURATION` INT(11) NULL ,
  `BILLSEC` INT(11) NULL ,
  `HANGUP_CAUSE` VARCHAR(50) NULL ,
  `UUID` VARCHAR(100) NULL ,
  `BLEG_UUID` VARCHAR(100) NULL ,
  `ACCOUNTCODE` VARCHAR(10) NULL ,
  `DOMAIN_NAME` VARCHAR(100) NULL )
ENGINE = InnoDB;



SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

