select 	id_conta,
		id_item,
		titulo_conta,
                          f.nome_fornecedor,
                          c.nome_categoria,
		t.nome_tipo as tipo_conta,
		descricao,
		valor_conta,
	data_venc,
	parcelas,
	valor_conta,
	status
            from Conta
            inner join TAB_TIPO_CONTA t on t.id_tipo = conta.tipo_conta
           left join fornecedor f on conta.id_fornecedor = f.id_fornecedor
           left join categoria c on conta.id_categoria = c.id_categoria
            where id_item = 2
            order by data_venc asc