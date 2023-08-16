<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="iso-8859-1"%>
<jsp:include page="/header.jsp"/>
<jsp:include page="/menu.jsp"/>

        <h2>Unidade de Medida</h2>
        <table id="datatable" border="2" class="table table-striped">
            <thead>
                <tr>
                    <th align="left">ID</th>
                    <th align="left">Descrição</th>
                    <th align="left">Sigla</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="unidadeMedida" items="${unidadeMedidas}">
                    <tr>
                        <td align="left">${unidadeMedida.idUnidadeMedida}</td>
                        <td align="left">${unidadeMedida.descricao}</td>
                        <td align="left">${unidadeMedida.sigla}</td>
                        <td align="center">
                            <a href="${pageContext.request.contextPath}/UnidadeMedidaExcluir?idUnidadeMedida=${unidadeMedida.idUnidadeMedida}">Excluir</a></td>
                        <td align="center">
                            <a href="${pageContext.request.contextPath}/UnidadeMedidaCarregar?idUnidadeMedida=${unidadeMedida.idUnidadeMedida}">Alterar</a></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <div align="center">
            <a href="${pageContext.request.contextPath}/UnidadeMedidaNovo">Novo</a>
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
        