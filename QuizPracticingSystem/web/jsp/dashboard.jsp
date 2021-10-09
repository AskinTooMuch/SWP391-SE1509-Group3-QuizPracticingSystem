<!--
   Copyright(C) 2021, Group Tree - SWP391, SE1509, FA21
   Created on : Oct 9, 2021
   MarketingController map
   Quiz practicing system
 
   Record of change:
   Date        Version     Author          Description
   9/10/21     1.0         NamDHHE150519   First Deploy
-->

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html>

    <head>   
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">     
        <link rel="stylesheet" href="${contextPath}/css/dashboard.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>

        <script>
            window.onload = function () {
                var data = new Array(${jsonString.size()});
                var i = 0;
            <c:forEach items="${jsonString}" var="string">
                data[i] =${string};
                i++;
            </c:forEach>

                var dataPointts = new Array(${jsonString.size()});
                for (var k = 0; k < dataPointts.length; k++) {
                    dataPointts[k] = [];
                }

                var chart = new CanvasJS.Chart("chartContainer", {
                    animationEnabled: true,
                    theme: "light2",
                    title: {
                        text: "Revenue"
                    },
                    axisY: {
                        title: "Revenue",
                        titleFontSize: 24,
                        includeZero: true
                    },
                    data: [
            <c:forEach var="k" begin="0" end="4">
                        {
                            type: "line",
                            yValueFormatString: "$#,###",
                            name: "asd",
                            showInLegend: true,
                            dataPoints: dataPointts[${k}]
                        },
            </c:forEach>
                    ]
                });
                function addData(data) {
                    for (var k = 0; k < data.length; k++) {
                        for (var j = 0; j < data[k].length; j++) {
                            dataPointts[k].push({
                                x: new Date(data[k][j].date),
                                y: data[k][j].revenue
                            });
                        }
                    }
                    chart.render();
                }
                addData(data);
            }
        </script>
    </head>

    <body>
        <div class="container-fluid">
            <jsp:include page="header.jsp"/>
            <div class="row">
                <h4>Dashboard</h4>
            </div>
            <div class="row">
                <div class="col-md-3"></div>
                <div class="tab col-md-9">
                    <button class="tablinks active" onclick="openTab(event, 'tab1')">New Subject</button>
                    <button class="tablinks" onclick="openTab(event, 'tab2')">New Registration</button>
                    <button class="tablinks" onclick="openTab(event, 'tab3')">Revenues</button>
                    <button class="tablinks" onclick="openTab(event, 'tab4')">Customer</button>
                    <button class="tablinks" onclick="openTab(event, 'tab5')">Trend of order counts</button>
                </div>
                <div class="col-md-3"></div>
            </div>
            <div id="tab1" class="tabcontent" style="display:block;">
                <div class="row">
                    <div class="row" style="padding-bottom: 100px;">
                        <div class="col-3" style="display: grid;">
                            <button class="subtablinks active" onclick="openSubTab(event, 'tab11')">New Subject</button>
                            <button class="subtablinks active" onclick="openSubTab(event, 'tab12')">New Subject</button>
                        </div>
                    </div>

                    <div class="col-12" style="width: 100%;height: 700px; background-color: orange;">
                        <div id="tab11" class="subtabcontent" style="">
                            <div class="row">
                                <div class="col-12" style="width: 100%;height: 100px; background-color: turquoise;">
                                </div>
                            </div>
                        </div>
                        <div id="tab12" class="subtabcontent" style="">
                            <div class="row">
                                <div class="col-12" style="width: 100%;height: 100px; background-color: silver;">
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div id="tab2" class="tabcontent" >
                <div class="row">
                    <div class="col-12" style="width: 100%;height: 700px; background-color: wheat;">

                    </div>
                </div>
            </div>
            <div id="tab3" class="tabcontent" >
                <div class="row">
                    <div class="col-12" style="width: 100%;height: 700px; background-color: yellowgreen;">

                    </div>
                </div>
            </div>
            <div id="tab4" class="tabcontent" >
                <div class="row">
                    <div class="col-12" style="width: 100%;height: 700px; background-color: tomato;">

                    </div>
                </div>
            </div>
            <div id="tab5" class="tabcontent">
                <div class="row">
                    <div class="col-12" style="width: 100%;height: 700px; background-color: skyblue;">

                    </div>
                </div>
            </div>
        </div>





        <script>
            function openTab(evt, tabName) {
                var i, tabcontent, tablinks;
                tabcontent = document.getElementsByClassName("tabcontent");
                for (i = 0; i < tabcontent.length; i++) {
                    tabcontent[i].style.display = "none";
                }
                tablinks = document.getElementsByClassName("tablinks");
                for (i = 0; i < tablinks.length; i++) {
                    tablinks[i].className = tablinks[i].className.replace(" active", "");
                }
                document.getElementById(tabName).style.display = "block";
                evt.currentTarget.className += " active";
            }

            $(".checkbox-menu").on("change", "input[type='checkbox']", function () {
                $(this).closest("li").toggleClass("active", this.checked);
            });

            $(document).on('click', '.allow-focus', function (e) {
                e.stopPropagation();
            });

            function openSubTab(evt, subtabName) {
                var i, subtabcontent, subtablinks;
                subtabcontent = document.getElementsByClassName("subtabcontent");
                for (i = 0; i < subtabcontent.length; i++) {
                    subtabcontent[i].style.display = "none";
                }
                subtablinks = document.getElementsByClassName("subtablinks");

                for (i = 0; i < subtablinks.length; i++) {
                    subtablinks[i].className = subtablinks[i].className.replace(" active", "");
                }

                document.getElementById(subtabName).style.display = "block";
                evt.currentTarget.className += " active";
            }
        </script>
        <div id="chartContainer" style="height: 300px; width: 100%;"></div>
        <script src="https://canvasjs.com/assets/script/jquery-1.11.1.min.js"></script>
        <script src="https://canvasjs.com/assets/script/canvasjs.min.js"></script>
    </body>
    <jsp:include page="footer.jsp"/>
</html>