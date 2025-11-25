PRAGMA foreign_keys = ON;

CREATE TABLE IF NOT EXISTS editoras (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    data_cadastro TEXT NOT NULL DEFAULT (datetime('now')),
    ativo INTEGER NOT NULL DEFAULT 1,
    nome TEXT NOT NULL
);

CREATE TABLE IF NOT EXISTS autores (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    data_cadastro TEXT NOT NULL DEFAULT (datetime('now')),
    ativo INTEGER NOT NULL DEFAULT 1,
    nome TEXT NOT NULL
);

CREATE TABLE IF NOT EXISTS livros (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    data_cadastro TEXT NOT NULL DEFAULT (datetime('now')),
    ativo INTEGER NOT NULL DEFAULT 1,
    nome TEXT NOT NULL,
    data_lancamento TEXT,
    genero TEXT,
    id_editora INTEGER,
    id_autor INTEGER,
    FOREIGN KEY (id_editora) REFERENCES editoras(id) ON DELETE SET NULL ON UPDATE CASCADE,
    FOREIGN KEY (id_autor)   REFERENCES autores(id)  ON DELETE SET NULL ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS usuarios (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    data_cadastro TEXT NOT NULL DEFAULT (datetime('now')),
    ativo INTEGER NOT NULL DEFAULT 1,
    login TEXT NOT NULL UNIQUE,
    nome TEXT NOT NULL,
    senha TEXT NOT NULL,
    cpf TEXT UNIQUE,
    email TEXT UNIQUE,
    idade INTEGER CHECK (idade >= 0)
);

CREATE TABLE IF NOT EXISTS livros_usuarios (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    data_cadastro TEXT NOT NULL DEFAULT (datetime('now')),
    ativo INTEGER NOT NULL DEFAULT 1,
    id_livro INTEGER NOT NULL,
    id_usuario INTEGER NOT NULL,
    tipo INTEGER NOT NULL,
    FOREIGN KEY (id_livro)    REFERENCES livros(id)    ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (id_usuario)  REFERENCES usuarios(id)  ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT uq_livro_usuario_tipo UNIQUE (id_livro, id_usuario, tipo)
);

CREATE INDEX IF NOT EXISTS idx_livros_id_editora ON livros(id_editora);
CREATE INDEX IF NOT EXISTS idx_livros_id_autor   ON livros(id_autor);
CREATE INDEX IF NOT EXISTS idx_livros_usuarios_id_livro ON livros_usuarios(id_livro);
CREATE INDEX IF NOT EXISTS idx_livros_usuarios_id_usuario ON livros_usuarios(id_usuario);
