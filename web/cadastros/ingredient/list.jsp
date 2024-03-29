<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix = "fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="iso-8859-1"%>
<jsp:include page="/header.jsp"/>
<jsp:include page="/menu.jsp"/>

<h2>Ingredientes</h2>
<div class="col-8 panel-body">
    <table id="datatable" class="table table-striped table-bordered basic-datatable">
        <thead>
            <tr>
                <th align="left">ID</th>
                <th align="left">Nome</th>
                <th align="right"></th>
                <th align="right"></th>           
            </tr>
        </thead>
        <tbody>
            <c:forEach var="ingredient" items="${ingredients}">
                <tr>
                    <td align="left">${ingredient.id}</td>
                    <td align="left">${ingredient.nameIngredient}</td>
                    <td align="center">
                        <a href="${pageContext.request.contextPath}/IngredientExcluir?id=${ingredient.id}">
                            Excluir
                    <td align="center">
                        <a href="${pageContext.request.contextPath}/IngredientCarregar?id=${ingredient.id}">
                            Alterar</a></td>
                </tr>
            </c:forEach>
        </tbody>
        
        <p>${mensagem}</p>

        <div align="center">
            <a href="${pageContext.request.contextPath}/IngredientNovo">Novo</a>
            <a href="index.jsp">Voltar � P�gina Inicial</a>
        </div>

    </table>

    <script>
        $(document).ready(function () {
            console.log('entrei ready');
            //carregamos a datatable
            //$("#datatable").DataTable({});
            $('#datatable').DataTable({
                "oLanguage": {
                    "sProcessing": "Processando...",
                    "sLengthMenu": "Mostrar _MENU_ registros",
                    "sZeroRecords": "Nenhum registro encontrado.",
                    "sInfo": "Mostrando de _START_ at� _END_ de _TOTAL_ registros",
                    "sInfoEmpty": "Mostrando de 0 at� 0 de 0 registros",
                    "sInfoFiltered": "",
                    "sInfoPostFix": "",
                    "sSearch": "Buscar:",
                    "sUrl": "",
                    "oPaginate": {
                        "sFirst": "Primeiro",
                        "sPrevious": "Anterior",
                        "sNext": "Seguinte",
                        "sLast": "�ltimo"
                    }
                }
            });
        });
    </script>

    <%@include file="/footer.jsp" %>