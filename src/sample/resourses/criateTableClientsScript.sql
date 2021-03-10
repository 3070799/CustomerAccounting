CREATE TABLE Clients (
  id INT NOT NULL AUTO_INCREMENT,
  last_name VARCHAR(45) NOT NULL,
  name VARCHAR(45) NOT NULL,
  sur_name VARCHAR(45) NOT NULL,
  date_of_birth DATE NOT NULL,
  inn INT NOT NULL,
  family_status VARCHAR(45) NOT NULL,
  type_of_education VARCHAR(45) NOT NULL,
  vip TINYINT NOT NULL,

   PRIMARY KEY (id));