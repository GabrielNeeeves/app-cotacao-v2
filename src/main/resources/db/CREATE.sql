-- Tabela Usuario v
CREATE TABLE Usuario (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    senha VARCHAR(100) NOT NULL,
    role  VARCHAR(100) NOT NULL --CLIENTE, ADMINISTRADOR, FUNCIONARIO
);
SELECT * FROM cliente lp ;
SELECT * FROM funcionario lp ;
SELECT * FROM Usuario lp ;
SELECT * FROM Administrador ;


SELECT * FROM ofertas

-- Tabela Administrador
CREATE TABLE Administrador (
    id SERIAL PRIMARY KEY,
    usuario_id INT NOT NULL,
    FOREIGN KEY (usuario_id) REFERENCES Usuario(id) ON DELETE CASCADE
);
INSERT INTO USUARIO (NOME, EMAIL, SENHA, ROLE) VALUES ('admin', 'admin@teste', 'admin', 'ADMINISTRADOR');

-- Tabela Cliente
CREATE TABLE Cliente (
    id SERIAL PRIMARY KEY,
    aluno_id INT,
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

INSERT INTO EMPRESA (NOME, ENDERECO, CNPJ)
VALUES ('EMPRESA C', 'Rua ABC', '40.751.032/0001-43')

SELECT * FROM empresa
SELECT * FROM escola


CREATE TABLE Escola (
	id SERIAL PRIMARY KEY,
	nome VARCHAR(200) NOT NULL,
	endereco VARCHAR(200) NOT NULL,
	cnpj VARCHAR(200) null,
	telefone varchar(200) NULL,
	tipo_escola varchar(20),
	check(tipo_escola IN('PUBLICA', 'PRIVADA'))
);
SELECT * FROM funcionario;

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

SELECT *
FROM aluno

ALTER TABLE Aluno
ADD COLUMN cliente_id INT,
ADD CONSTRAINT fk_aluno_cliente
    FOREIGN KEY (cliente_id) REFERENCES Cliente(id) ON DELETE CASCADE;



-- Lista padrão criada pelo funcionário
CREATE TABLE  lista_padrao (
    id SERIAL PRIMARY KEY,
    funcionario_id INT NOT NULL,
    escola_id INT NOT NULL,
    ano_letivo INT NOT NULL,
    serie VARCHAR(20) NOT NULL,
    materiais JSONB NOT NULL DEFAULT '[]',
    FOREIGN KEY (funcionario_id) REFERENCES Funcionario(id) ON DELETE CASCADE,
    FOREIGN KEY (escola_id) REFERENCES Escola(id) ON DELETE CASCADE
);
--ALTER TABLE lista_padrao
--ADD COLUMN materiais JSONB NOT NULL DEFAULT '[]';

SELECT * FROM lista_padrao;


-- Itens sugeridos na lista padrão
CREATE TABLE material_padrao (
    id SERIAL PRIMARY KEY,
    lista_padrao_id INT NOT NULL,
    nome VARCHAR(200) NOT NULL,
    quantidade INT NOT NULL,
    observacoes VARCHAR(200),
    FOREIGN KEY (lista_padrao_id) REFERENCES ListaPadrao(id) ON DELETE CASCADE
);


-- Lista personalizada criada pelo cliente para um aluno específico
CREATE TABLE lista_personalizada (
    id SERIAL PRIMARY KEY,
    cliente_id INT NOT NULL,
    aluno_id INT NOT NULL,
    lista_padrao_id INT NOT NULL, -- base que ele personalizou
    materiais JSONB NOT NULL DEFAULT '[]',
    FOREIGN KEY (cliente_id) REFERENCES Cliente(id) ON DELETE CASCADE,
    FOREIGN KEY (aluno_id) REFERENCES Aluno(id) ON DELETE CASCADE,
    FOREIGN KEY (lista_padrao_id) REFERENCES ListaPadrao(id) ON DELETE CASCADE
);
--ALTER TABLE lista_personalizada
--ADD COLUMN materiais JSONB NOT NULL DEFAULT '[]';


-- Oferta feita por um funcionário de empresa para esse item
CREATE TABLE oferta_material (
    id SERIAL PRIMARY KEY,
    item_padrao_id INT NOT NULL,
    funcionario_id INT NOT NULL,
    preco DECIMAL(10,2) NOT NULL,
    prazo_entrega INT, -- em dias
    quantidade_minima INT DEFAULT 1,
    observacoes TEXT,
    FOREIGN KEY (item_padrao_id) REFERENCES MaterialPadrao(id) ON DELETE CASCADE,
    FOREIGN KEY (funcionario_id) REFERENCES Funcionario(id) ON DELETE CASCADE
);
ALTER TABLE oferta_material
    DROP COLUMN item_padrao_id,
    ADD COLUMN lista_padrao_id INT NOT NULL,
    ADD COLUMN material_nome VARCHAR(200) NOT NULL,
    ADD CONSTRAINT oferta_material_lista_padrao_id_fkey
        FOREIGN KEY (lista_padrao_id) REFERENCES lista_padrao(id) ON DELETE CASCADE;

SELECT * FROM oferta_material om
SELECT * FROM material
SELECT * FROM oferta_material_lista

-- tabela principal
CREATE TABLE oferta_material_lista (
    id SERIAL PRIMARY KEY
);

-- tabela de relação (armazena a lista de IDs)
CREATE TABLE oferta_material_lista_ofertas (
    lista_id INT NOT NULL,
    oferta_material_id INT NOT NULL,
    PRIMARY KEY (lista_id, oferta_material_id),
    FOREIGN KEY (lista_id) REFERENCES oferta_material_lista(id) ON DELETE CASCADE,
    FOREIGN KEY (oferta_material_id) REFERENCES oferta_material(id) ON DELETE CASCADE
);


