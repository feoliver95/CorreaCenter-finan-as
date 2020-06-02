select id_item,
		titulo_conta,
		numero_documento,
		t.nome_tipo as tipo_conta,
                          c.nome_categoria,
                          f.nome_fornecedor,
		descricao,
		valor_conta,
		data_venc,
		parcelas,
		valor_conta,
		status
              from Conta 
              inner join TAB_TIPO_CONTA t on t.id_tipo = conta.tipo_conta
              LEFT join categoria c on Conta.id_categoria=c.id_categoria
              LEFT join fornecedor f on Conta.id_fornecedor=f.id_fornecedor 
              WHERE strftime('%m',data_venc) ='05'
              order by id_item;


			  

		
		
		
select id_item,
		titulo_conta,
		numero_documento,
		t.nome_tipo as tipo_conta,
                          c.nome_categoria,
                          f.nome_fornecedor,
		descricao,
		valor_conta,
		data_venc,
		parcelas,
		valor_conta,
		status
              from Conta 
              inner join TAB_TIPO_CONTA t on t.id_tipo = conta.tipo_conta
              LEFT join categoria c on Conta.id_categoria=c.id_categoria
              LEFT join fornecedor f on Conta.id_fornecedor=f.id_fornecedor 
              WHERE strftime('%m',data_venc) ='05' and strftime('%Y',data_venc) ='2020'
              order by id_item;

			  
			  		
select id_item,
		titulo_conta,
		numero_documento,
		t.nome_tipo as tipo_conta,
                          c.nome_categoria,
                          f.nome_fornecedor,
		descricao,
		valor_conta,
		data_venc,
		parcelas,
		valor_conta,
		status
              from Conta 
              inner join TAB_TIPO_CONTA t on t.id_tipo = conta.tipo_conta
              LEFT join categoria c on Conta.id_categoria=c.id_categoria
              LEFT join fornecedor f on Conta.id_fornecedor=f.id_fornecedor 
              WHERE strftime('%Y',data_venc) ='2020'
              order by id_item;

