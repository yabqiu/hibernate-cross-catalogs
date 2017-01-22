INSERT INTO mart.account(id, name) VALUES(0, 'aa');
INSERT INTO mart.history(id, accountId, lastUpdate) VALUES(1, 0, '2017-01-01');


INSERT INTO client1.account(id, name) VALUES(1, 'bb');
INSERT INTO client1.history(id, accountId, lastUpdate) VALUES(1, 1, '2017-01-02');

INSERT INTO client2.account(id, name) VALUES(2, 'cc');
INSERT INTO client2.history(id, accountId, lastUpdate) VALUES(1, 2, '2017-01-03');