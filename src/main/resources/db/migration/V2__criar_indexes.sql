CREATE INDEX IF NOT EXISTS idx_livros_id_editora ON livros(id_editora);
CREATE INDEX IF NOT EXISTS idx_livros_id_autor   ON livros(id_autor);
CREATE INDEX IF NOT EXISTS idx_livros_usuarios_id_livro ON livros_usuarios(id_livro);
CREATE INDEX IF NOT EXISTS idx_livros_usuarios_id_usuario ON livros_usuarios(id_usuario);
