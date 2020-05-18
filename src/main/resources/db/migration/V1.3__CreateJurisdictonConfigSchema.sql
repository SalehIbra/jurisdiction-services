CREATE TABLE jurisdiction_config (
  jurisdiction_id  int NOT NULL,
  configuration_id  int NOT NULL,
   PRIMARY KEY (jurisdiction_id, configuration_id),
  value VARCHAR(200) NOT NULL,
  comment VARCHAR(2000)
);

ALTER TABLE jurisdiction_config  ADD CONSTRAINT FK_jurisdiction_id FOREIGN KEY (jurisdiction_id) REFERENCES jurisdiction(jurisdiction_id );
ALTER TABLE jurisdiction_config  ADD CONSTRAINT FK_configuration_id FOREIGN KEY (configuration_id) REFERENCES configuration(configuration_id);