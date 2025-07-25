-- Quando excluir um registro de Cliente, excluir também o Usuario associado.
CREATE OR REPLACE FUNCTION trg_delete_usuario_cliente()
RETURNS TRIGGER AS $$
BEGIN
    DELETE FROM Usuario WHERE id = OLD.usuario_id;
    RETURN OLD;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER trg_cliente_delete
AFTER DELETE ON Cliente
FOR EACH ROW
EXECUTE FUNCTION trg_delete_usuario_cliente();

-- Quando excluir um registro de Administrador, excluir também o Usuario associado.
CREATE OR REPLACE FUNCTION tr_delete_usuario_after_admin()
RETURNS TRIGGER AS $$
BEGIN
    DELETE FROM Usuario WHERE id = OLD.usuario_id;
    RETURN OLD;
END;
$$ LANGUAGE plpgsql;

-- Trigger na tabela Administrador
CREATE TRIGGER tg_delete_usuario_after_admin
AFTER DELETE ON Administrador
FOR EACH ROW
EXECUTE FUNCTION tr_delete_usuario_after_admin();

-- Quando excluir um registro de Funcionario, excluir também o Usuario associado.
CREATE OR REPLACE FUNCTION delete_usuario_when_funcionario_deleted()
RETURNS trigger
LANGUAGE plpgsql
AS $$
BEGIN
    DELETE FROM Usuario WHERE id = OLD.usuario_id;
    RETURN OLD;
END;
$$;

CREATE TRIGGER trg_delete_usuario_after_funcionario
AFTER DELETE ON Funcionario
FOR EACH ROW
EXECUTE FUNCTION delete_usuario_when_funcionario_deleted();



