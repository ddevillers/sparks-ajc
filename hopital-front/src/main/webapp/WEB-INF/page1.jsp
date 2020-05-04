<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>



<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Ma 1ère page</title>
</head>
<body>

	<table>
		<tr>
			<td>Nom</td>
			<td>Prenom</td>
			<td>Secu</td>
		</tr>


		<c:forEach items="${patients}" var="p">

			<c:if test="${p.prenom!='Jordan'}">
				<tr>
					<td>${p.nom}</td>
					<td>${p.prenom}</td>
					<td>${p.secu}</td>
				</tr>
			</c:if>

		</c:forEach>


	</table>


	<form id="formP"method="POST" action="patient">
		<input type="text" name="secu" placeholder="Secu"> <input
			type="text" name="nom" placeholder="Nom"> <input type="text"
			name="prenom" placeholder="Prenom"> <input type="button"
			id="btnValidate" value="Valider">
	</form>

</body>


<script>


$("#btnValidate").click(function(){
	alert("toto");	
	var data = $("#formP").serialize();
	data+="&action='ajax'";
	$.ajax("patient", {
			type: "POST",
			data: data,
			success: function (resp) 
			{
				location.reload();
			}
	});
});
</script>



</html>
