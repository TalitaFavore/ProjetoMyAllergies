<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix = "fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="iso-8859-1"%>
<jsp:include page="/header.jsp"/>
<jsp:include page="/menu.jsp"/>

<h2>Intolerâncias</h2>
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
            <c:forEach var="Intolerance" items="${intolerances}">
                <tr>
                    <td align="left">${intolerance.id}</td>
                    <td align="left">${intolerance.nameIntolerance}</td>
                    <td align="center">
                        <a href="${pageContext.request.contextPath}/IntoleranceExcluir?id=${intolerance.id}">
                            Excluir
                    <td align="center">
                        <a href="${pageContext.request.contextPath}/IntoleranceCarregar?id=${intolerance.id}">
                            Alterar</a></td>
                </tr>
            </c:forEach>
        </tbody>
        
        <p>${mensagem}</p>

        <div align="center">
            <a href="${pageContext.request.contextPath}/IntoleranceNovo">Novo</a>
            <a href="index.jsp">Voltar à Página Inicial</a>
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
                    "sInfo": "Mostrando de _START_ até _END_ de _TOTAL_ registros",
                    "sInfoEmpty": "Mostrando de 0 até 0 de 0 registros",
                    "sInfoFiltered": "",
                    "sInfoPostFix": "",
                    "sSearch": "Buscar:",
                    "sUrl": "",
                    "oPaginate": {
                        "sFirst": "Primeiro",
                        "sPrevious": "Anterior",
                        "sNext": "Seguinte",
                        "sLast": "Último"
                    }
                }
            });
        });
    </script>

    <%@include file="/footer.jsp" %>

