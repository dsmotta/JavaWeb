<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>

<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script type="text/javascript"
	src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

<meta charset="ISO-8859-1">

<style type="text/css">
/* fundo da barra de progresso - cor cinza*/
#myProgress {
	width: 100%;
	background-color: #eeeeee;
}
/*cor da barra de progresso*/
#myBar {
	width: 1%;
	heigth: 30px;
	background-color: #4CAF50;
}

#jBar {
            width: 1%;
            height: 30px;
            background-color: #4CAF50;
            }

.ui-progressbar {
	position: relative:
}

.progress-Label {
	position: relative;
	left: 50%;
	top: 4px;
	font-weight: bold;
	text-shadow: 1px 1px 0 #fff;
}
</style>

<title>Barra de Progresso</title>
</head>
<body>
	 <h1>Exemplo com java Script</h1>

    <div id="myProgress">
        <div id="myBar"></div>
    </div>

    <br>
    <br>
    <button id="btn" onclick="exibirBarra()">Iniciar a Barra de progresso</button>

    <br>
    <h1>Barra progresso com jQuery</h1>

    <div class="progress-Label">Carregando...</div>
    <div id="progressbar">
        <div id="jbar"></div>
    </div>


    <script type="text/javascript">
        //Script da barra de progresso por javascript	
        function exibirBarra() {

            var elem = document.getElementById("myBar");
            var width = 1;
            var id = setInterval(frame, 10);

            function frame() {
                if (width >= 100) {
                    clearInterval(id);
                } else {
                    width++;
                    elem.style.width = width + "%";
                }
            }
        }

        $("button").click(function() {
            var progressbar = $("#progressbar");
            var progresslabel = $(".progress-Label");
            var jbar = $("#jbar");
            progressbar.progressbar({ //cria a barra no div
                value: false,
                change: function() {
                    progresslabel.text(progressbar.progressbar('value') + "%");
                },
                complete: function() {
                    progresslabel.text('Completo!');
                }
            });

            function progress() {
                var width = jbar[0].style.width.replace("%", "");
                var val = progressbar.progressbar("value") || 0;
                progressbar.progressbar("value", val + 2);
                if (width != "") {
                    width = parseInt(width);
                }
                jbar[0].style.width = (width + 2) + "%";

                if (val < 99) {
                    setTimeout(progress, 80);
                }
            }
            setTimeout(progress, 2000);
        })
    </script>

</body>
</html>