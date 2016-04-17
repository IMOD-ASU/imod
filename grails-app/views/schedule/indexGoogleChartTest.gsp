<html>
  <head>
    <!--Load the AJAX API-->
    <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
    <script type="text/javascript">

      // Load the Visualization API and the corechart package.
      google.charts.load('current', {'packages':['corechart']});

      // Set a callback to run when the Google Visualization API is loaded.
      google.charts.setOnLoadCallback(drawChart);

      // Callback that creates and populates a data table,
      // instantiates the pie chart, passes in the data and
      // draws it.
      function drawChart() {

        // Create the data table.
		var data = new google.visualization.DataTable();
		  data.addColumn('string', 'Year');
		  data.addColumn('number', 'Sales');
		  data.addColumn('number', 'SalesMax');

		  data.addRows(4);
		  data.setValue(0, 0, '2004');
		  data.setValue(0, 1, 1000);
		  data.setValue(0, 2, 0);

		  data.setValue(1, 0, '2005');
		  data.setValue(1, 1, 1170);
		  data.setValue(1, 2, 0);

/* NEED TO MAKE THIS BAR RED? */
		  data.setValue(2, 0, '2006');
		  data.setValue(2, 1, 0);
		  data.setValue(2, 2, 1400);

		  data.setValue(3, 0, '2007');
		  data.setValue(3, 1, 1030);
		  data.setValue(3, 2, 0);


		  var chart = new google.visualization.BarChart(document.getElementById('visualization'));
chart.draw(data, {isStacked: true, width: 400, height: 240, title: 'Company Performance',
							vAxis: {title: 'Year', titleTextStyle: {color: 'red'}},
							series: [{color: 'blue', visibleInLegend: true}, {color: 'red', visibleInLegend: false}]
						   });
    </script>
  </head>

  <body>
    <!--Div that will hold the pie chart-->
    <div id="chart_div"></div>
  </body>
</html>
