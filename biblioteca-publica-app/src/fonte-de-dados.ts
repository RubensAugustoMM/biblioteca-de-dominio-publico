import "reflect-metadata";
import { DataSource } from "typeorm";
import { Autor } from "./dominio/Autor";
import { Editora } from "./dominio/Editora";
import { Livro } from "./dominio/Livro";
import { Usuario } from "./dominio/Usuario";
import { LivroUsuario } from "./dominio/LivroUsuario";

export const fonteDeDados = new DataSource({
  type: "sqlite",
  database: "./data/biblioteca_publica.db",
  synchronize: false,
  logging: false,
  entities: [Autor, Editora, Livro, Usuario, LivroUsuario],
});