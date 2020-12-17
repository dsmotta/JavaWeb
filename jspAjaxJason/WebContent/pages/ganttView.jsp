<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>

<link rel="stylesheet" type="text/css" href="../scriptGanttView/jquery-ui-1.8.4.css" />
<link rel="stylesheet" type="text/css" href="../scriptGanttView/reset.css" />
<link rel="stylesheet" type="text/css" href="../scriptGanttView/jquery.ganttView.css" />
<script type="text/javascript" src="../scriptGanttView/jquery-1.4.2.js"></script>
<script type="text/javascript" src="../scriptGanttView/date.js"></script>
<script type="text/javascript" src="../scriptGanttView/jquery-ui-1.8.4.js"></script>
<script type="text/javascript" src="../scriptGanttView/jquery.ganttView.js"></script>

<style type="text/css">
		body {
			font-family: tahoma, verdana, helvetica;
			font-size: 0.8em;
			padding: 10px;
		}
	</style>

<meta charset="ISO-8859-1">
<title>Gantt View</title>
</head>
<body>
<h1>Gantt View</h1>

	<div id="ganttChart"></div>
	<br/><br/>
	<div id="eventMessage"></div>

</body>

<script type="text/javascript">


$(document).ready(function(){		

	$.get( "ServletBuscarDatasPlanejamento", function( response ) {
	
		var ganttDataResposta = JSON.parse(response);
		
		var ganttData = "";
		 ganttData += "[";
		 
		 $.each(ganttDataResposta, function(index, projeto) {
			 
			 gatnttData += "{ \"id\": \""+projeto.id+"\", \"name\": \""pojeto.nome"\", \"series\": [";
			 
			 $.each(projeto.series, function(idx, serie) {
				 
			 	var cores = "#3366FF, #00CC00".split(',');
			 	
			 	var cor;
			 	if(idx === 0){
			 		cor = "#CC33CC"			 		
			 	}else{
			 		cor = Number.isInteger(idx / 2) ? cores[0] : cores[1];
			 	}
			 		
			 	var datainicial = serie.datainicial.split('-');
			 	var datafinal = serie.datafinal.split('-');
			 	
			 	ganttData +="{ \"name\": \""+serie.nome+"\", \"start\": \""+new Date(datainicial[0],datainicial[1],datainicial[2])+"\", \"end\": \""+new Date(datafinal[0],datafinal[1],datafinal[2])+"\", \"color\": \""+cor+"\" }";
			 	
				if(idx < projeto.series.length - 1){
					gantData +=",";
				}
			 	
			 });
			 
			 ganttData +="]}";
			 
			 if(index < ganttDataResposta.length - 1){
				 ganttData +=",";
			 }
			 
		 });
		 
		 ganttData += "]";
		 
		 ganttData = JSON.parse(ganttData);
		
			$("#ganttChart").ganttView({ 
				data: ganttData,
				slideWidth: 600,
				behavior: {
					onClick: function (data) { 
						var msg = "Evento de click: { start: " + data.start.toString("M/d/yyyy") + ", end: " + data.end.toString("M/d/yyyy") + " }";
						$("#eventMessage").text(msg);
					},
					onResize: function (data) { 
						var msg = "Evento de redmensionar: { start: " + data.start.toString("M/d/yyyy") + ", end: " + data.end.toString("M/d/yyyy") + " }";
						$("#eventMessage").text(msg);
					},
					onDrag: function (data) { 
						var msg = "Evento de arrastar: { start: " + data.start.toString("M/d/yyyy") + ", end: " + data.end.toString("M/d/yyyy") + " }";
						$("#eventMessage").text(msg);
					}
				}
			});
			
	});
});

	</script>

</html>