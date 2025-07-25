-- View: Listar Funcionários com dados do usuário, empresa e escola (se houver
CREATE OR REPLACE VIEW vw_funcionarios_completos AS
SELECT 
    f.id AS funcionario_id,
    u.nome AS nome_usuario,
    u.email,
    f.salario,
    e.nome AS empresa_nome,
    esc.nome AS escola_nome
FROM Funcionario f
JOIN Usuario u ON f.usuario_id = u.id
LEFT JOIN Empresa e ON f.empresa_id = e.id
LEFT JOIN Escola esc ON f.escola_id = esc.id;

-- View: Listar alunos com a escola e cliente associado
CREATE OR REPLACE VIEW vw_alunos_clientes AS
SELECT
    a.id AS aluno_id,
    a.nome AS aluno_nome,
    a.serie,
    a.turno,
    a.ano_letivo,
    a.observacoes,
    esc.nome AS escola_nome,
    c.id AS cliente_id,
    u.nome AS cliente_nome,
    u.email AS cliente_email
FROM Aluno a
LEFT JOIN Escola esc ON a.escola_id = esc.id
LEFT JOIN Cliente c ON c.aluno_id = a.id
LEFT JOIN Usuario u ON c.usuario_id = u.id;

-- View: Listar listas padrão com o funcionário e escola
CREATE OR REPLACE VIEW vw_listas_padrao_completas AS
SELECT
    lp.id AS lista_padrao_id,
    f.id AS funcionario_id,
    u.nome AS funcionario_nome,
    esc.id AS escola_id,
    esc.nome AS escola_nome,
    lp.ano_letivo,
    lp.serie
FROM ListaPadrao lp
JOIN Funcionario f ON lp.funcionario_id = f.id
JOIN Usuario u ON f.usuario_id = u.id
JOIN Escola esc ON lp.escola_id = esc.id;

-- View: Itens da lista padrão com informações da lista
CREATE OR REPLACE VIEW vw_itens_lista_padrao AS
SELECT
    mp.id AS material_padrao_id,
    lp.id AS lista_padrao_id,
    lp.ano_letivo,
    lp.serie,
    mp.nome AS material_nome,
    mp.quantidade,
    mp.observacoes
FROM MaterialPadrao mp
JOIN ListaPadrao lp ON mp.lista_padrao_id = lp.id;

-- View: Ofertas para itens da lista padrão com dados do funcionário e empresa/escola
CREATE OR REPLACE VIEW vw_ofertas_material AS
SELECT
    om.id AS oferta_id,
    om.preco,
    om.prazo_entrega,
    om.quantidade_minima,
    om.observacoes AS oferta_observacoes,
    mp.id AS material_padrao_id,
    mp.nome AS material_nome,
    f.id AS funcionario_id,
    u.nome AS funcionario_nome,
    emp.nome AS empresa_nome,
    esc.nome AS escola_nome
FROM OfertaMaterial om
JOIN MaterialPadrao mp ON om.item_padrao_id = mp.id
JOIN Funcionario f ON om.funcionario_id = f.id
JOIN Usuario u ON f.usuario_id = u.id
LEFT JOIN Empresa emp ON f.empresa_id = emp.id
LEFT JOIN Escola esc ON f.escola_id = esc.id;

-- View: Listas personalizadas com cliente, aluno e lista padrão base
CREATE OR REPLACE VIEW vw_listas_personalizadas AS
SELECT
    lp.id AS lista_personalizada_id,
    c.id AS cliente_id,
    u.nome AS cliente_nome,
    a.id AS aluno_id,
    a.nome AS aluno_nome,
    lpb.id AS lista_padrao_id,
    lpb.ano_letivo,
    lpb.serie
FROM ListaPersonalizada lp
JOIN Cliente c ON lp.cliente_id = c.id
JOIN Usuario u ON c.usuario_id = u.id
JOIN Aluno a ON lp.aluno_id = a.id
JOIN ListaPadrao lpb ON lp.lista_padrao_id = lpb.id;

-- View: Itens personalizados com suas listas personalizadas
CREATE OR REPLACE VIEW vw_itens_personalizados AS
SELECT
    mp.id AS material_personalizado_id,
    lp.id AS lista_personalizada_id,
    mp.nome_material,
    mp.quantidade,
    mp.marca_escolhida,
    mp.observacoes
FROM MaterialPersonalizado mp
JOIN ListaPersonalizada lp ON mp.id_lista_personalizada = lp.id;


--View: Dados do cliente
CREATE OR REPLACE VIEW vw_cliente AS
SELECT
    c.id AS cliente_id,
    c.aluno_id,
    u.id AS usuario_id,
    u.nome,
    u.email,
    u.senha,
    u.role
FROM
    Cliente c
JOIN
    Usuario u ON c.usuario_id = u.id;


--View: Dados do Funcionario
CREATE OR REPLACE VIEW vw_funcionario AS
SELECT
    f.id AS funcionario_id,
    f.salario,
    f.empresa_id,
    f.escola_id,
    u.id AS usuario_id,
    u.nome AS usuario_nome,
    u.email AS usuario_email,
    u.senha AS usuario_senha,
    u.role AS usuario_role
FROM
    Funcionario f
INNER JOIN
    Usuario u ON f.usuario_id = u.id;
















