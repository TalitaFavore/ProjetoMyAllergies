<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix = "fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="iso-8859-1"%>
<jsp:include page="/header.jsp"/>
<jsp:include page="/menu.jsp"/>

        <h2>Usuários</h2>
        <table id="datatable" class="display">
            <thead>
                <tr>
                    <th align="left">ID</th>
                    <th align="left">Nome</th>
                    <th align="left">E-mail</th>
                    <th align="left">Senha</th>
                    <th align="right"></th>
                    <th align="right"></th>           
                </tr>
            </thead>
            <tbody>
                <c:forEach var="profile" items="${profiles}">
                    <tr>
                        <td align="left">${profile.id}</td>
                        <td align="left">${profile.username}</td>
                        <td align="left">${profile.email}</td>
                        <td align="left">${profile.passcode}</td>
                        <td align="center">
                            <a href="${pageContext.request.contextPath}/ProfileExcluir?id=${profile.id}">
                                Excluir</a></td>
                            <td align="center">
                                <a href="${pageContext.request.contextPath}/ProfileCarregar?id=${profile.id}">
                                Alterar</a></td>
                    </tr>
                </c:forEach>
            </tbody>
            
            <div align="center">
                <a href="${pageContext.request.contextPath}/ProfileNovo">Novo</a>
                <a href="index.jsp">Voltar à Página Inicial</a>
            </div>
        
        </table>
                
<script>
             $(document).ready(function() {
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