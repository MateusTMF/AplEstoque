<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="iso-8859-1"%>
<jsp:include page="/header.jsp"/>
<jsp:include page="/menu.jsp"/>

        <h2>Movimento de Estoques</h2>
        <table id="datatable" border="2" class="table table-striped">
            <thead>
                <tr>
                    <th align="left">ID</th>
                    <th align="left">Entrada e Saida</th>
                    <th align="left">Documento</th>
                    <th align="left">Data</th>
                    <th align="right">Quantidade</th>
                    <th align="right">Valor Movimento</th>
                    <th align="right">Id Produto</th>
                    <th align="right">Id Tipo de Movimento</th>
                    <th align="right">Id do Funcionario</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="movimentoEstoque" items="${movimentoEstoques}">
                    <tr>
                        <td align="left">${movimentoEstoque.idMovimento}</td>
                        <td align="left">${movimentoEstoque.entradaSaida}</td>
                        <td align="left">${movimentoEstoque.documento}</td> 
                        <td align="left">${movimentoEstoque.data}</td>
                        <td align="left">${movimentoEstoque.quantidade}</td>
                        <td align="left">${movimentoEstoque.valorMovimento}</td>
                        <td align="left">${movimentoEstoque.produto.nomeProduto}</td>
                        <td align="left">${movimentoEstoque.tipoMovimento.descricao}</td>
                        <td align="left">${movimentoEstoque.funcionario.nomeFuncionario}</td>
                        <td align="center">
                            <a href="${pageContext.request.contextPath}/MovimentoEstoqueExcluir?idMovimento=${movimentoEstoque.idMovimento}">Excluir</a></td>
                        <td align="center">
                            <a href="${pageContext.request.contextPath}/MovimentoEstoqueCarregar?idMovimento=${movimentoEstoque.idMovimento}">Alterar</a></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <div align="center">
            <a href="${pageContext.request.contextPath}/MovimentoEstoqueNovo">Novo</a>
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
        