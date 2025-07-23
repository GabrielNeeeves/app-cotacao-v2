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
