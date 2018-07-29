CREATE TABLE company (
  id                 BIGINT       NOT NULL AUTO_INCREMENT PRIMARY KEY,
  name         VARCHAR(20)  NOT NULL,
  employees_number  INT NOT NULL
);
CREATE TABLE employee (
  id                 BIGINT       NOT NULL AUTO_INCREMENT PRIMARY KEY,
  company_id  BIGINT,
  name         VARCHAR(20)  NOT NULL,
  age   SMALLINT    NOT NULL,
  gender    VARCHAR(10) NOT NULL,
  salary    INT NOT NULL,
  FOREIGN KEY (company_id) REFERENCES company (id)
);