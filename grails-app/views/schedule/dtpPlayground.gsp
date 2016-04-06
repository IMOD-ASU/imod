<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>jQuery UI Datepicker - Default functionality</title>


    <script src="//code.jquery.com/jquery-1.10.2.js"></script>
    <script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>

    <g:external dir="bower_components/jqueryui-timepicker-addon/dist" file="jquery-ui-timepicker-addon.js" />

    <link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">

    <g:external dir="bower_components/jqueryui-timepicker-addon/dist" file="jquery-ui-timepicker-addon.css" />

    <script>
    $(function() {
        $( "#datetimepicker" ).datetimepicker();
    });
    </script>
    <script>
    $(function() {
        $( "#datepicker2" ).datetimepicker();
    });
    </script>
</head>
<body>

    <p>Date: <input type="txt" id="datetimepicker"></p>

    <p>Date2: <input type="text" id="datepicker2" ></p>


    <script>
    $("#datetimepicker").datetimepicker({
        onSelect: function(dateText, inst) {
            var dateAsString = dateText; //the first parameter of this function
            var dateAsObject = $(this).datetimepicker( 'getDate' ); //the getDate method
            console.log(dateAsString);
            console.log(dateAsObject);
        }
    });

    $("#datepicker2").datetimepicker({
        onSelect: function(dateText, inst) {
            var dateAsString = dateText; //the first parameter of this function
            var dateAsObject = $(this).datetimepicker( 'getDate' ); //the getDate method
            console.log(dateAsString);
            console.log(dateAsObject);
        }
    })
    </script>

</body>
</html>
