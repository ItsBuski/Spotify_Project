-- drop if exists Spotify;
-- create database Spotify;

create table if not exists administradores(
	ID int not null,
	nombre varchar(30) not null,
	passw varchar(30) not null,
	primary key(ID)
);

create table if not exists listas(
	nombre varchar(30) not null,
	icono blob null,
	primary key(nombre)
);

create table if not exists canciones(
	ID int not null,
	nombre varchar(30) not null,
	album varchar(40) null,
	artista varchar(60) not null,
	duracion varchar(20) not null,
	icono blob not null,
	mp3 blob not null,
	primary key(ID)
);

create table if not exists usuarios(
	email varchar(20) not null,
	nombre varchar(20) not null,
	passw varchar (30) not null,
	iconoUsuario blob null,
	primary key(email)
);

create table if not exists cancionesListas(
	nombreLista varchar(30) not null,
	IDCancion int not null,
	foreign key (nombreLista) references listas(nombre),
	foreign key (IDCancion) references canciones(ID)
);

create table if not exists usuariosLastSongs(
	emailUsuario varchar(20) not null,
	lastSong int not null,
	foreign key (emailUsuario) references usuarios(email),
	foreign key (lastSong) references canciones(ID)
);

