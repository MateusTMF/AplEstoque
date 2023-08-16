<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="iso-8859-1"%>
<jsp:include page="/header.jsp"/>
<jsp:include page="/menu.jsp"/>

<form name="cadastrarunidademedida" action="UnidadeMedidaCadastrar" 
      method="POST">
    
    <table align="center" border="0">
        <thead>
            <tr>
                <th colspan="2" align="center">
                    Cadastro de Unidades
                </th>
            </tr>
            <tr>
                <th colspan="2" align="center">
                    ${mensagem}
                </th>
            </tr>
        </thead> 
        <tbody>
            <tr><td>ID: </td>
            <td><input type="text" name="idunidademedida" id="idunidademedida" value="${unidadeMedida.idUnidadeMedida}" readonly="readonly"/></td></tr>
            <tr><td>Descricao: </td>
            <td><input type="text" name="descricao" id="descricao" value="${unidadeMedida.descricao}" size="50"
                           maxlength="50" /></td></tr>
            <tr><td>Sigla: </td>
            <td><input type="text" name="sigla" id="sigla" value="${unidadeMedida.sigla}" size="50"
                           maxlength="50" /></td></tr>
                <tr><td colspan="2" align="center">
                        <input type="submit" name="Cadastrar" id="Cadastrar" value="Cadastrar"/>
                        <input type="reset" name="limpar" id="limpar" value="Limpar"/>
                    </td>
                </tr>
                <tr>
                    <td colspan="2" align="center"><h5><a href="index.jsp">Voltar a Página Inicial</a></h5></td>
                </tr>
        </tbody>
    </table>
</form>
<%@include file="/footer.jsp" %>