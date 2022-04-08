use day15db;

insert into users(username, password) values
    ('fred', sha1('fred')),
    ('wilma', sha1('wilma')),
    ('barney', sha1('barney')),
    ('betty', sha1('betty'));
