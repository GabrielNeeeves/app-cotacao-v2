-- Tabela Usuario v
CREATE TABLE Usuario (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    senha VARCHAR(100) NOT NULL,
    role  VARCHAR(100) NOT NULL --CLIENTE, ADMINISTRADOR, FUNCIONARIO
);
SELECT * FROM usuario;

-- Tabela Administrador
CREATE TABLE Administrador (
    id SERIAL PRIMARY KEY,
    usuario_id INT NOT NULL,
    FOREIGN KEY (usuario_id) REFERENCES Usuario(id) ON DELETE CASCADE
);


-- Tabela Cliente
CREATE TABLE Cliente (
    id SERIAL PRIMARY KEY,
    aluno_id INT NOT NULL,
    usuario_id INT NOT NULL,
    FOREIGN KEY (aluno_id) REFERENCES Aluno(id) ON DELETE CASCADE,
    FOREIGN KEY (usuario_id) REFERENCES Usuario(id) ON DELETE CASCADE
);

-- Tabela Funcionario
CREATE TABLE Funcionario (
    id SERIAL PRIMARY KEY,
    salario DECIMAL(8,2),
    usuario_id INT NOT NULL,
    empresa_id INT NULL,
    escola_id INT NULL,
    FOREIGN KEY (usuario_id) REFERENCES Usuario(id) ON DELETE CASCADE,
    FOREIGN KEY (empresa_id) REFERENCES Empresa(id) ON DELETE CASCADE,
    FOREIGN KEY (escola_id) REFERENCES Escola(id) ON DELETE CASCADE,
    CHECK (
    (empresa_id IS NOT NULL AND escola_id IS NULL)
    OR
    (empresa_id IS NULL AND escola_id IS NOT NULL)
	)
);

-- Tabela Empresa v
CREATE TABLE Empresa (
	id SERIAL PRIMARY KEY,
	nome VARCHAR(200) NOT NULL,
	endereco VARCHAR(200) NOT NULL,
	cnpj VARCHAR(20) NULL
);


CREATE TABLE Escola (
	id SERIAL PRIMARY KEY,
	nome VARCHAR(200) NOT NULL,
	endereco VARCHAR(200) NOT NULL,
	cnpj VARCHAR(200) null,
	telefone varchar(200) NULL,
	tipo_escola varchar(20),
	check(tipo_escola IN('PUBLICA', 'PRIVADA'))
);

CREATE TABLE Aluno (
	id SERIAL PRIMARY KEY,
	escola_id INT,
	nome VARCHAR(200) NOT NULL,
	serie varchar(50) NOT NULL,
	turno varchar(20),
	check(turno IN ('MATUTINO', 'VESPERTINO', 'NOTURNO')),
	ano_letivo int NOT NULL,
	observacoes VARCHAR(200) NULL, 
	FOREIGN KEY(escola_id) REFERENCES Escola(id)
);



-- Lista padrão criada pelo funcionário
CREATE TABLE  lista_padrao (
    id SERIAL PRIMARY KEY,
    funcionario_id INT NOT NULL,
    escola_id INT NOT NULL,
    ano_letivo INT NOT NULL,
    serie VARCHAR(20) NOT NULL,
    FOREIGN KEY (funcionario_id) REFERENCES Funcionario(id) ON DELETE CASCADE,
    FOREIGN KEY (escola_id) REFERENCES Escola(id) ON DELETE CASCADE
);

-- Itens sugeridos na lista padrão
CREATE TABLE MaterialPadrao (
    id SERIAL PRIMARY KEY,
    lista_padrao_id INT NOT NULL,
    nome VARCHAR(200) NOT NULL,
    quantidade INT NOT NULL,
    observacoes VARCHAR(200),
    FOREIGN KEY (lista_padrao_id) REFERENCES ListaPadrao(id) ON DELETE CASCADE
);
ALTER TABLE MaterialPadrao RENAME TO material_padrao;

-- Lista personalizada criada pelo cliente para um aluno específico
CREATE TABLE ListaPersonalizada (
    id SERIAL PRIMARY KEY,
    cliente_id INT NOT NULL,
    aluno_id INT NOT NULL,
    lista_padrao_id INT NOT NULL, -- base que ele personalizou
    FOREIGN KEY (cliente_id) REFERENCES Cliente(id) ON DELETE CASCADE,
    FOREIGN KEY (aluno_id) REFERENCES Aluno(id) ON DELETE CASCADE,
    FOREIGN KEY (lista_padrao_id) REFERENCES ListaPadrao(id) ON DELETE CASCADE
);
ALTER TABLE ListaPersonalizada RENAME TO lista_personalizada;

-- Itens escolhidos pelo cliente
CREATE TABLE MaterialPersonalizado (
    id SERIAL PRIMARY KEY,
    id_lista_personalizada INT NOT NULL,
    nome_material VARCHAR(200) NOT NULL,
    quantidade INT NOT NULL,
    marca_escolhida VARCHAR(100),
    observacoes TEXT,
    FOREIGN KEY (id_lista_personalizada) REFERENCES ListaPersonalizada(id) ON DELETE CASCADE
);
ALTER TABLE MaterialPersonalizado RENAME TO material_personalizado;

-- Oferta feita por um funcionário de empresa para esse item
CREATE TABLE OfertaMaterial (
    id SERIAL PRIMARY KEY,
    item_padrao_id INT NOT NULL,
    funcionario_id INT NOT NULL, -- funcionário que fez a oferta
    preco DECIMAL(10,2) NOT NULL,
    prazo_entrega INT, -- em dias
    quantidade_minima INT DEFAULT 1,
    observacoes TEXT,
    FOREIGN KEY (item_padrao_id) REFERENCES MaterialPadrao(id) ON DELETE CASCADE,
    FOREIGN KEY (funcionario_id) REFERENCES Funcionario(id) ON DELETE CASCADE
);
ALTER TABLE OfertaMaterial RENAME TO oferta_material;


