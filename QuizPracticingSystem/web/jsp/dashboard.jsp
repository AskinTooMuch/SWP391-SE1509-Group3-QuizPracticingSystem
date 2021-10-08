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
                var dataPoints = [];
                var chart = new CanvasJS.Chart("chartContainer", {
                    animationEnabled: true,
                    theme: "light2",
                    title: {
                        text: "Daily Sales Data"
                    },
                    axisY: {
                        title: "Units",
                        titleFontSize: 24,
                        includeZero: true
                    },
                    data: [{
                            type: "column",
                            yValueFormatString: "#,### Units",
                            dataPoints: dataPoints
                        }]
                });

                function addData(data) {
                    for (var i = 0; i < data.length; i++) {
                        dataPoints.push({
                            x: new Date(data[i].date),
                            y: data[i].units
                        });
                    }
                    chart.render();
                }
                $.getJSON("https://canvasjs.com/data/gallery/javascript/daily-sales-data.json", addData);
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
            <div id="tab1" class="tabcontent" style="display: block">
                <div class="row">
                    <button class="tablinks active" onclick="openTab(event, 'tab11')">New Subject</button>
                    <button class="tablinks active" onclick="openTab(event, 'tab12')">New Subject</button>
                    <div class="col-12" style="width: 100%;height: 700px; background-color: orange;">
                        <div id="tab11" class="tabcontent" style="display: block">
                            <div class="row">
                                <div class="col-12" style="width: 100%;height: 100px; background-color: turquoise;">
                                </div>
                            </div>
                        </div
                        <div id="tab12" class="tabcontent" style="display: block">
                            <div class="row">
                                <div class="col-12" style="width: 100%;height: 100px; background-color: silver;">

                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div id="tab2" class="tabcontent" style="display: block">
                <div class="row">
                    <div class="col-12" style="width: 100%;height: 700px; background-color: wheat;">

                    </div>
                </div>
            </div>
            <div id="tab3" class="tabcontent" style="display: block">
                <div class="row">
                    <div class="col-12" style="width: 100%;height: 700px; background-color: yellowgreen;">

                    </div>
                </div>
            </div>
            <div id="tab4" class="tabcontent" style="display: block">
                <div class="row">
                    <div class="col-12" style="width: 100%;height: 700px; background-color: tomato;">

                    </div>
                </div>
            </div>
            <div id="tab5" class="tabcontent" style="display: block">
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
        </script>
        <!--<div id="chartContainer" style="height: 300px; width: 100%;"></div>-->
        <script src="https://canvasjs.com/assets/script/jquery-1.11.1.min.js"></script>
        <script src="https://canvasjs.com/assets/script/canvasjs.min.js"></script>
    </body>
    <jsp:include page="footer.jsp"/>
</html>