
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

ALTER TABLE configuration ADD UNIQUE UC_CONFIG_KEY (config_key);
