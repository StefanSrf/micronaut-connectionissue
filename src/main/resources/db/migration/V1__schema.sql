CREATE TABLE demo_entity
(
    id   BIGINT      NOT NULL,
    name VARCHAR(10) NOT NULL,
    CONSTRAINT pk_demo_entity PRIMARY KEY (id)
);

insert into demo_entity values (1, 'test');