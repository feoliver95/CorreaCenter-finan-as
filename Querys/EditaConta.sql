select 
    id_conta,
    id_item,
    titulo_conta,
    descricao,
    sum(valor_conta) valor_conta,
    min(data_venc) data_venc,
    count(parcelas) parcelas,
    numero_documento,
    f.nome_fornecedor,
    c.nome_categoria,
    t.nome_tipo,
    dt_pagamento,
    status

from conta
     left join TAB_TIPO_CONTA t on t.id_tipo = conta.tipo_conta
              LEFT join categoria c on Conta.id_categoria=c.id_categoria
              LEFT join fornecedor f on Conta.id_fornecedor=f.id_fornecedor 
where id_item=3
group by id_item
