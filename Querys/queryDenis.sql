select  a.id_item, 
a.numero_documento,
a.titulo_conta,  
a.descricao, 
a.dt_pagamento,
t.nome_tipo,
f.nome_fornecedor,
c.nome_categoria,
sum(a.valor_conta)as Valor_Total,
min(a.data_venc) as Data_vencimento_inicio,
max(a.data_venc)as Data_vencimento_fim, 
sum(a.parcelas)as Parcelas,	
CASE
	WHEN
	CASE WHEN (SELECT count(status) from Conta as c where STATUS="VENCIDO" AND C.id_item = a.id_item) >0 THEN "PENDENTE"
	ELSE "OK" 
	END  
	=  
	CASE (SELECT count(status) from Conta as d where STATUS="PAGO" AND d.id_item = a.id_item) 
		WHEN SUM(A.PARCELAS) THEN "PAGO" 
	ELSE "PENDENTE" 
    END  
	THEN "VENCIDO"
	ELSE  
		CASE (SELECT count(status) from Conta as d where STATUS="PAGO" AND d.id_item = a.id_item) 
			WHEN SUM(A.PARCELAS) THEN "PAGO" 
		ELSE "PENDENTE" 
		END 
END STATUS
	FROM Conta as a 
            LEFT JOIN TAB_TIPO_CONTA t on a.tipo_conta = t.id_tipo
            LEFT join categoria c on a.id_categoria=c.id_categoria
            LEFT join fornecedor f on a.id_fornecedor=f.id_fornecedor 
          GROUP BY id_item 
	order by max(a.data_venc) asc
 
