select id_conta,id_item, numero_documento,c.nome_categoria,f.nome_fornecedor,titulo_conta,nome_tipo,data_venc,valor_conta,parcelas,status
from Conta
INNER JOIN TAB_TIPO_CONTA t on conta.tipo_conta = t.id_tipo
INNER JOIN categoria c on conta.id_categoria =c.id_categoria
INNER JOIN fornecedor f on conta.id_fornecedor=f.id_fornecedor
where strftime('%m-%Y' ,data_venc) like strftime('%m-%Y' ,'now') AND STATUS != "PAGO" OR STATUS ="VENCIDO"
order by data_venc