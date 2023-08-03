CREATE SCHEMA IF NOT EXISTS catalog_vehicle;
-- CREATE TYPE catalog_vehicle.type_Transport AS ENUM('CARGO', 'PASSENGER', 'BUS');
create table if not exists catalog_vehicle.vehicle

    (
        id serial primary key,
        mark varchar(50) not null,
        model varchar(50) not null,
        category char(1),
        state_number varchar(9) not null unique,
        year_release int not null,
        type_transport varchar(9) not null
);

