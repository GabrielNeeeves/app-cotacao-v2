-- Cadastrar Usuário
CREATE OR REPLACE PROCEDURE sp_cadastrar_usuario(
    p_nome VARCHAR,
    p_email VARCHAR,
    p_senha VARCHAR,
    p_role VARCHAR
)
LANGUAGE plpgsql
AS $$
BEGIN
    INSERT INTO Usuario (nome, email, senha, role)
    VALUES (p_nome, p_email, p_senha, p_role);
END;
$$;



-- Cadastrar Administrador em Usuario também
CREATE OR REPLACE PROCEDURE sp_cadastrar_administrador(
	p_nome VARCHAR,
	p_email VARCHAR,
	p_senha VARCHAR
)
LANGUAGE plpgsql
AS $$
DECLARE
	v_usuario_id BIGINT;
BEGIN
    -- Inserir na tabela Usuario
	    INSERT INTO Usuario (nome, email, senha, role)
	    VALUES (p_nome, p_email, p_senha, 'ADMINISTRADOR')
	    RETURNING id INTO v_usuario_id;
    -- Inserir na tabela Administrador
	    INSERT INTO Administrador (usuario_id)
	    VALUES (v_usuario_id);
END;
$$;




-- Cadastrar Empresa
CREATE OR REPLACE PROCEDURE sp_cadastrar_empresa(
    p_nome VARCHAR,
    p_endereco VARCHAR,
    p_cnpj VARCHAR DEFAULT NULL
)
LANGUAGE plpgsql
AS $$
BEGIN
    INSERT INTO Empresa (nome, endereco, cnpj)
    VALUES (p_nome, p_endereco, p_cnpj);
END;
$$;

-- Cadastrar Escola
CREATE OR REPLACE PROCEDURE sp_cadastrar_escola(
    p_nome VARCHAR,
    p_endereco VARCHAR,
    p_tipo_escola VARCHAR,
    p_cnpj VARCHAR DEFAULT NULL,
    p_telefone VARCHAR DEFAULT NULL
)
LANGUAGE plpgsql
AS $$
BEGIN
    INSERT INTO Escola (nome, endereco, tipo_escola, cnpj, telefone)
    VALUES (p_nome, p_endereco, p_tipo_escola, p_cnpj, p_telefone);
END;
$$;

-- Cadastrar Funcionario
CREATE OR REPLACE PROCEDURE sp_cadastrar_funcionario(
    p_nome VARCHAR,
    p_email VARCHAR,
    p_senha VARCHAR,
    p_salario DECIMAL,
    p_empresa_id INT DEFAULT NULL,
    p_escola_id INT DEFAULT NULL
)
LANGUAGE plpgsql
AS $$
DECLARE
    v_usuario_id INT;
BEGIN
    -- Inserir na tabela Usuario
    INSERT INTO Usuario (nome, email, senha, role)
    VALUES (p_nome, p_email, p_senha, 'FUNCIONARIO')
    RETURNING id INTO v_usuario_id;

    -- Inserir na tabela Funcionario
    INSERT INTO Funcionario (salario, usuario_id, empresa_id, escola_id)
    VALUES (p_salario, v_usuario_id, p_empresa_id, p_escola_id);
END;
$$;

-- Cadastrar Aluno
CREATE OR REPLACE PROCEDURE sp_cadastrar_aluno(
    p_escola_id BIGINT,
    p_nome VARCHAR,
    p_serie VARCHAR,
    p_turno VARCHAR,
    p_ano_letivo INT,
    p_observacoes VARCHAR DEFAULT NULL
)
LANGUAGE plpgsql
AS $$
BEGIN
    INSERT INTO Aluno (escola_id, nome, serie, turno, ano_letivo, observacoes)
    VALUES (p_escola_id, p_nome, p_serie, p_turno, p_ano_letivo, p_observacoes);
END;
$$;


-- Cadastrar Cliente com aluno e usuário ao "mesmo tempo"
CREATE OR REPLACE PROCEDURE sp_cadastrar_cliente_usuario_aluno(
    p_nome VARCHAR,
    p_email VARCHAR,
    p_senha VARCHAR,
    p_aluno_nome VARCHAR,
    p_aluno_serie VARCHAR,
    p_aluno_turno VARCHAR,
    p_aluno_ano_letivo INT,
    p_aluno_observacoes VARCHAR DEFAULT NULL,
    p_aluno_escola_id INT DEFAULT NULL,
    p_role VARCHAR DEFAULT 'CLIENTE'
)
LANGUAGE plpgsql
AS $$
DECLARE
    v_usuario_id INT;
    v_aluno_id INT;
BEGIN
    -- Inserir na tabela Usuario
    INSERT INTO Usuario (nome, email, senha, role)
    VALUES (p_nome, p_email, p_senha, p_role)
    RETURNING id INTO v_usuario_id;

    -- Inserir na tabela Aluno
    INSERT INTO Aluno (nome, serie, turno, ano_letivo, observacoes, escola_id)
    VALUES (p_aluno_nome, p_aluno_serie, p_aluno_turno, p_aluno_ano_letivo, p_aluno_observacoes, p_aluno_escola_id)
    RETURNING id INTO v_aluno_id;

    -- Inserir na tabela Cliente
    INSERT INTO Cliente (usuario_id, aluno_id)
    VALUES (v_usuario_id, v_aluno_id);
END;
$$;



-- Cadastrar Cliente em Usuário
CREATE OR REPLACE PROCEDURE sp_cadastrar_cliente_com_usuario(
    p_nome VARCHAR,
    p_email VARCHAR,
    p_senha VARCHAR,
    p_aluno_id BIGINT
)
LANGUAGE plpgsql
AS $$
DECLARE
    v_usuario_id BIGINT;
BEGIN
    -- Inserir na tabela Usuario
    INSERT INTO Usuario (nome, email, senha, role)
    VALUES (p_nome, p_email, p_senha, 'CLIENTE')
    RETURNING id INTO v_usuario_id;

    -- Inserir na tabela Cliente
    INSERT INTO Cliente (usuario_id, aluno_id)
    VALUES (v_usuario_id, p_aluno_id);
END;
$$;




-- Cadastrar ListaPadrao
CREATE OR REPLACE PROCEDURE sp_cadastrar_lista_padrao(
    p_funcionario_id INT,
    p_escola_id INT,
    p_ano_letivo INT,
    p_serie VARCHAR
)
LANGUAGE plpgsql
AS $$
BEGIN
    INSERT INTO ListaPadrao (funcionario_id, escola_id, ano_letivo, serie)
    VALUES (p_funcionario_id, p_escola_id, p_ano_letivo, p_serie);
END;
$$;

-- Cadastrar MaterialPadrao
CREATE OR REPLACE PROCEDURE sp_cadastrar_material_padrao(
    p_lista_padrao_id INT,
    p_nome VARCHAR,
    p_quantidade INT,
    p_observacoes VARCHAR DEFAULT NULL
)
LANGUAGE plpgsql
AS $$
BEGIN
    INSERT INTO MaterialPadrao (lista_padrao_id, nome, quantidade, observacoes)
    VALUES (p_lista_padrao_id, p_nome, p_quantidade, p_observacoes);
END;
$$;

-- Cadastrar ListaPersonalizada
CREATE OR REPLACE PROCEDURE sp_cadastrar_lista_personalizada(
    p_cliente_id INT,
    p_aluno_id INT,
    p_lista_padrao_id INT
)
LANGUAGE plpgsql
AS $$
BEGIN
    INSERT INTO ListaPersonalizada (cliente_id, aluno_id, lista_padrao_id)
    VALUES (p_cliente_id, p_aluno_id, p_lista_padrao_id);
END;
$$;

-- Cadastrar MaterialPersonalizado
CREATE OR REPLACE PROCEDURE sp_cadastrar_material_personalizado(
    p_id_lista_personalizada INT,
    p_nome_material VARCHAR,
    p_quantidade INT,
    p_marca_escolhida VARCHAR DEFAULT NULL,
    p_observacoes TEXT DEFAULT NULL
)
LANGUAGE plpgsql
AS $$
BEGIN
    INSERT INTO MaterialPersonalizado (id_lista_personalizada, nome_material, quantidade, marca_escolhida, observacoes)
    VALUES (p_id_lista_personalizada, p_nome_material, p_quantidade, p_marca_escolhida, p_observacoes);
END;
$$;

-- Cadastrar OfertaMaterial
CREATE OR REPLACE PROCEDURE sp_cadastrar_oferta_material(
    p_item_padrao_id INT,
    p_funcionario_id INT,
    p_preco DECIMAL,
    p_prazo_entrega INT DEFAULT NULL,
    p_quantidade_minima INT DEFAULT 1,
    p_observacoes TEXT DEFAULT NULL
)
LANGUAGE plpgsql
AS $$
BEGIN
    INSERT INTO OfertaMaterial (item_padrao_id, funcionario_id, preco, prazo_entrega, quantidade_minima, observacoes)
    VALUES (p_item_padrao_id, p_funcionario_id, p_preco, p_prazo_entrega, p_quantidade_minima, p_observacoes);
END;
$$;





