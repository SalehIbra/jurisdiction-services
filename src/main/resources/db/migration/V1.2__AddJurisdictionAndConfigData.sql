INSERT INTO  jurisdiction (jurisdiction_code , jurisdiction_name)
VALUES ('SE','Sweden'),
('FI','Finland'),
('IT','Italy'),
('MT','Malta'),
('PT','Portugal'),
('DK','Denmark'),
('GB','United Kingdom');

INSERT INTO configuration (config_key , definition)
VALUES ('autoplay.allowed','Autoplay allowed True/False/Null'),
('autoplay.maxspin','Maximum number of autoplay spin'),
('autoplay.stoppable','It must enable a player to stop autoplay at anytime');