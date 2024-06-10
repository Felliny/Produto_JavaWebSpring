<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href='<c:url value="./resources/css/styles.css"/>'>
    <title>Consulta Produto Java Spring Web</title>
</head>
<body>
    <header>
        <a href="index">Consultar</a>
        <a href="produto">Produto</a>
    </header>
    <div>
        <main>
            <form action="index" method="post">
                <h1 align="center"> Consultar Produtos </h1>
                <div>
                    <label for="quantidade">Quantidade:</label>
                    <input type="number" name="quantidade" min="0" value="0" id="quantidade">
                </div>
                <div>
                    <input type="submit" name="botao" id="botao" value="Buscar quantidade de Produtos abaixo do estoque">
                    <input type="submit" name="botao" id="botao" value="Buscar Produtos">
                </div>
                <br>
                <br>
                <div>
                    <c:if test="${not empty erro}">
                        <b><c:out value="${erro}" /></b>
                    </c:if>
                </div>
                <br>
                <br>
                <br>
                <c:if test="${not empty produtos}">
                    <div class="tabela_container">
                        <table>
                            <thead>
                            <th>codigo</th>
                            <th>nome</th>
                            <th>quantidade</th>
                            </thead>
                            <tbody>
                                <c:forEach var="p" items="${produtos}">
                                    <tr>
                                        <td><c:out value="${p.codigo}" /></td>
                                        <td><c:out value="${p.nome}" /></td>
                                        <td><c:out value="${p.quantidade}" /></td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </c:if>
                <c:if test="${quantidade != -1 && quantidade != null}">
                    <div>
                        <label for="quant">Quantidade de Produtos:</label>
                        <input type="number" id="quant" value="${quantidade}" disabled>
                    </div>
                </c:if>
            </form>
            <br>
            <br>
            <form action="produtoRelatorio" method="post" target="_blank">
                <div>
                    <div>
                        <label for="quantidade">Quantidade:</label>
                        <input type="number" name="quantidade" min="0" value="0" id="quantidade">
                        <input type="submit" name="botao" id="botao" value="Gerar">
                    </div>
                </div>
            </form>
        </main>
    </div>
</body>
</html>
