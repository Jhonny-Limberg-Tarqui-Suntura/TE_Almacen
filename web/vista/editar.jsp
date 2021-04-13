<%@page import="com.emergentes.modelo.AlmacenDAO"%>
<%@page import="com.emergentes.modelo.Almacen"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            Almacen item = (Almacen) request.getAttribute("item");
        %>
        <h1>Editar Producto</h1>
        <form action="Principal?op=guardar" method="post">
            Id: <input type="text" name="id" value="<%= item.getId() %>" size="2" pattern="[1-9]{1}[0-9]*"/>
            <input type="hidden" name="tipo" value="<%= item.getId() %>" required/>
            <br>
            Producto: <input type="text" name="producto" value="<%= item.getProducto() %>" required/>
            <br>
            Precio: <input type="number" name="precio" value="<%= item.getPrecio() %>" />
            <br>
            Cantidad: <input type="number" name="cantidad" value="<%= item.getCantidad() %>" />
            <br>
            <input type="submit" value="Enviar"/>
        </form>
    </body>
</html>
