<!doctype html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <title>jQuery UI Datepicker - Default functionality</title>
  <link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
  <script src="//code.jquery.com/jquery-1.10.2.js"></script>
  <script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-ui-timepicker-addon/1.6.1/i18n/jquery-ui-timepicker-addon-i18n.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-ui-timepicker-addon/1.6.1/i18n/jquery-ui-timepicker-af.js"></script>


    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-ui-timepicker-addon/1.6.1/jquery-ui-sliderAccess.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-ui-timepicker-addon/1.6.1/jquery-ui-sliderAccess.js"></script>
          <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jquery-ui-timepicker-addon/1.6.1/jquery-ui-timepicker-addon.css">
  <g:external dir="bower_components/jqueryui-timepicker-addon/dist" file="jquery-ui-timepicker-addon.js" />
    <g:external dir="bower_components/jqueryui-timepicker-addon/dist/i18n/" file="jquery-ui-timepicker-fr.js" />
        <g:external dir="bower_components/jqueryui-timepicker-addon/dist" file="jquery-ui-timepicker-addon.css" />
  <!--<link rel="stylesheet" href="/resources/demos/style.css"> -->
  <script>
  $(function() {
    $( "#datepicker" ).datetimepicker();
  });
  </script>
  <script>
  $(function() {
    $( "#datepicker2" ).datepicker();
  });
  </script>
</head>
<body>

<p>Date: <input type="txt" id="datepicker"></p>

<p>Date2: <input type="text" id="datepicker2" ></p>


<script>
$("#datepicker").datepicker({
   onSelect: function(dateText, inst) {
      var dateAsString = dateText; //the first parameter of this function
      var dateAsObject = $(this).datetimepicker( 'getDate' ); //the getDate method
      console.log(dateAsString);
      console.log(dateAsObject);
   }
});

$("#datepicker2").datepicker({
   onSelect: function(dateText, inst) {
      var dateAsString = dateText; //the first parameter of this function
      var dateAsObject = $(this).datepicker( 'getDate' ); //the getDate method
      console.log(dateAsString);
      console.log(dateAsObject);
   }
})
</script>

</body>
</html>
