<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="iso-8859-1"%>
<jsp:include page="/header.jsp"/>
<jsp:include page="/menu.jsp"/>

        <h2>Produtos</h2>
        <table id="datatable" border="2" class="table table-striped">
            <thead>
                <tr>
                    <th align="left">ID</th>
                    <th align="left">Nome do Produto</th>
                    <th align="left">Ultimo Preço Pago</th>
                    <th align="left">Saldo Atual</th>
                    <th align="right">ID do Tipo de Produto</th>
                    <th align="right">ID da Unidade de Medida</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="produto" items="${produtos}">
                    <tr>
                        <td align="left">${produto.idProduto}</td>
                        <td align="left">${produto.nomeProduto}</td>
                        <td align="left">${produto.ultimoPrecoPago}</td> 
                        <td align="left">${produto.saldoAtual}</td>
                        <td align="left">${produto.tipoProduto.descricao}</td>
                        <td align="left">${produto.unidadeMedida.sigla}</td>
                        <td align="center">
                            <a href="${pageContext.request.contextPath}/ProdutoExcluir?idProduto=${produto.idProduto}">Excluir</a></td>
                        <td align="center">
                            <a href="${pageContext.request.contextPath}/ProdutoCarregar?idProduto=${produto.idProduto}">Alterar</a></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <div align="center">
            <a href="${pageContext.request.contextPath}/ProdutoNovo">Novo</a>
            <a href="index.jsp">Voltar a pagina inicial</a>
        </div>
               
<script>
    $(document).ready(function (){
        console.log('entrei ready');
        //carregamo a database
        //$("#datable").DataTable({});
        $('#datatable').dataTable({
            "oLanguge":{
                "sProcessing":"Processando...",
                "sLengthMenu":"Mostrar _MENU_ registros",
                "sZeroRecords":"Nenhum registro encontrado",
                "sInfo":"Mostrando de _START_ ate _END_ de _TOTAL_ registros",
                "sInfoEmpty":"Mostrando de 0 ate 0 de 0 registros",
                "sInfoFiltered":"",
                "sInfoPostFix":"",
                "sSearch":"Buscar",
                "sUrl":"",
                "oPaginate":{
                    "sFirst": "Primeiro",
                    "sPrevius": "Anterior",
                    "sNext": "Seguinte",
                    "sLast": "Ultimo"
                }
            }
        });
    });

</script>

<%@include file="/footer.jsp" %>
        