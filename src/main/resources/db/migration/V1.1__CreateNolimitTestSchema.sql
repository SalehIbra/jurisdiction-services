
CREATE TABLE jurisdiction (
  jurisdiction_id int AUTO_INCREMENT PRIMARY KEY,
  jurisdiction_code VARCHAR(100) NOT NULL,
  jurisdiction_name VARCHAR(100) NOT NULL
);

ALTER TABLE jurisdiction ADD UNIQUE UC_JURISDICTION_CODE (jurisdiction_code);

CREATE TABLE configuration (
  configuration_id int AUTO_INCREMENT PRIMARY KEY,
  config_key VARCHAR(200) NOT NULL,
  definition VARCHAR(1000)
);

CREATE TABLE jurisdiction_config (
  jurisdiction_id  int NOT NULL,
  configuration_id  int NOT NULL,
   PRIMARY KEY (jurisdiction_id, configuration_id),
  value VARCHAR(200) NOT NULL,
  comment VARCHAR(2000)
);

ALTER TABLE jurisdiction_config  ADD CONSTRAINT FK_jurisdiction_id FOREIGN KEY (jurisdiction_id) REFERENCES jurisdiction(jurisdiction_id );
ALTER TABLE jurisdiction_config  ADD CONSTRAINT FK_configuration_id FOREIGN KEY (configuration_id) REFERENCES configuration(configuration_id);