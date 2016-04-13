<%@ page import="imod.Help" %>
<%@ page import="imod.KnowledgeDimensionEnum"%>

<html>
<head>
	<meta charset="utf-8" />
	<title>
		Schedule
	</title>

	<meta name="layout" content="imod">
	<!-- FIXME These should be loaded when needed, not prefetched -->
	<link id="imgNone" rel="prefetch" href="${resource(dir: 'images/content', file: 'knowDimNone.png')}">
	<link id="imgC" rel="prefetch" href="${resource(dir: 'images/content', file: 'knowDimC.png')}">
	<link id="imgF" rel="prefetch" href="${resource(dir: 'images/content', file: 'knowDimF.png')}">
	<link id="imgM" rel="prefetch" href="${resource(dir: 'images/content', file: 'knowDimM.png')}">
	<link id="imgP" rel="prefetch" href="${resource(dir: 'images/content', file: 'knowDimP.png')}">
	<link id="imgCF" rel="prefetch" href="${resource(dir: 'images/content', file: 'knowDimCF.png')}">
	<link id="imgCM" rel="prefetch" href="${resource(dir: 'images/content', file: 'knowDimCM.png')}">
	<link id="imgCP" rel="prefetch" href="${resource(dir: 'images/content', file: 'knowDimCP.png')}">
	<link id="imgFM" rel="prefetch" href="${resource(dir: 'images/content', file: 'knowDimFM.png')}">
	<link id="imgFP" rel="prefetch" href="${resource(dir: 'images/content', file: 'knowDimFP.png')}">
	<link id="imgMP" rel="prefetch" href="${resource(dir: 'images/content', file: 'knowDimMP.png')}">
	<link id="imgCFM" rel="prefetch" href="${resource(dir: 'images/content', file: 'knowDimCFM.png')}">
	<link id="imgCFP" rel="prefetch" href="${resource(dir: 'images/content', file: 'knowDimCFP.png')}">
	<link id="imgCMP" rel="prefetch" href="${resource(dir: 'images/content', file: 'knowDimCMP.png')}">
	<link id="imgFMP" rel="prefetch" href="${resource(dir: 'images/content', file: 'knowDimFMP.png')}">
	<link id="imgCFMP" rel="prefetch" href="${resource(dir: 'images/content', file: 'knowDimCFMP.png')}">

	<g:external dir="css/source" file="schedule.css" />
	<g:external dir="css/source" file="learningObjective.css" />
	<g:external dir="css/source" file="iconModule.css" />
	<!--<g:javascript src="source/assessment.js" defer="defer" />-->



	<link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" type="text/css" rel="stylesheet" />
	<g:external dir="bower_components/fullcalendar/dist" file="fullcalendar.min.css" />
	<link href="https://cdnjs.cloudflare.com/ajax/libs/jqueryui/1.11.4/jquery-ui.css" type="text/css" rel="stylesheet" />

	<!--
	Scheduler css and js imports here
	<g:javascript src="source/fullCal/jquery.js" defer="defer" />
-->



<!--
<g:javascript src="source/fullCal/moment.js" defer="defer" />
<g:javascript src="source/fullCal/fullcalendar.js" defer="defer" />

<g:external dir="css/source" file="fullcalendar.css" />
-->



<g:javascript src="source/schedule.js" defer="defer" />


</head>

<body>




<!--- Calendar starts after here
<div id='calendar'>
</div>
--->


<div class="container">
	<div class="row">
		<div class="col-xs-12">
			<h1></h1>
			<br />
			<div id="scheduleCalendar"></div>
		</div>
	</div>
</div>

<div id="taskContent" title="Task Details" style="display: none;">
	<div id="taskInfo"></div>
	<p>Start:</p>
	<div id="taskTime"></div>
	<p><strong><a id="taskLink" target="_blank">For more info.</a></strong></p>
</div>



<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/2.2.3/jquery.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.8.2/moment.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/fullcalendar/2.6.1/fullcalendar.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jqueryui/1.11.4/jquery-ui.min.js"></script>


<script>
//TypeError: $(...).qtip is not a function error?? googled researched this error and it is usually caused by "loading an additional jquery (perhaps built with your other scripts) that's causing a conflict."

$(document).ready(function() {
	$('#scheduleCalendar').fullCalendar({
		header: {
			left: 'prev title next',
			center: '',
			right: ''
		},
		eventClick: function (event, jsEvent, view) {
			//set the values and open the modal
			$("#taskInfo").html(event.description);
			$("#taskLink").attr('href', event.url);
			$("#taskTime").text(event.end);
			$("#taskContent").dialog({
				modal: true,
				title: event.title
			});
			return false;
		},
		events:
		[
			{
				"title":"Free Pizza",
				"allday":"false",
				"description":"<p>This is just a fake description for the Free Pizza.</p><p>Nothing to see!</p>",
				"start":moment().subtract('days',14),
				"end":moment().subtract('days',14),
				"url":"http://www.mikesmithdev.com/blog/coding-without-music-vs-coding-with-music/"
			},
			{
				"title":"DNUG Meeting",
				"allday":"false",
				"description":"<p>This is just a fake description for the DNUG Meeting.</p><p>Nothing to see!</p>",
				"start":moment().subtract('days',10),
				"end":moment().subtract('days',10),
				"url":"http://www.mikesmithdev.com/blog/youtube-video-event-tracking-with-google-analytics/"
			},
			{
				"title":"Staff Meeting",
				"allday":"false",
				"description":"<p>This is just a fake description for the Staff Meeting.</p><p>Nothing to see!</p>",
				"start":moment().subtract('days',6),
				"end":moment().subtract('days',6),
				"url":"http://www.mikesmithdev.com/blog/what-if-your-website-were-an-animal/"
			},
			{
				"title":"Poker Night",
				"allday":"false",
				"description":"<p>This is just a fake description for the Poker Night.</p><p>Nothing to see!</p>",
				"start":moment().subtract('days',2),
				"end":moment().subtract('days',2),
				"url":"http://www.mikesmithdev.com/blog/how-to-make-a-qr-code-in-asp-net/"
			},
			{
				"title":"NES Gamers",
				"allday":"false",
				"description":"<p>This is just a fake description for the NES Gamers.</p><p>Nothing to see!</p>",
				"start":moment(),
				"end":moment().add(1,'days'),
				"url":"http://www.mikesmithdev.com/blog/name-that-nes-soundtrack/"
			},
			{
				"title":"XBox Tourney",
				"allday":"false",
				"description":"<p>This is just a fake description for the XBox Tourney.</p><p>Nothing to see!</p>",
				"start":moment().add('days',3),
				"end":moment().add('days',3),
				"url":"http://www.mikesmithdev.com/blog/worst-job-titles-in-internet-and-info-tech/"
			},
			{
				"title":"Pool Party",
				"allday":"false",
				"description":"<p>This is just a fake description for the Pool Party.</p><p>Nothing to see!</p>",
				"start":moment().add('days',5),
				"end":moment().add('days',5),
				"url":"http://www.mikesmithdev.com/blog/jquery-full-calendar/"
			},
			{
				"title":"Staff Meeting",
				"allday":"false",
				"description":"<p>This is just a fake description for the Staff Meeting.</p><p>Nothing to see!</p>",
				"start":moment().add('days',9),
				"end":moment().add('days',9),
				"url":"http://www.mikesmithdev.com/blog/keep-important-licensing-comments-dotnet-bundling-minification/"
			},
			{
				"title":"Poker Night",
				"allday":"false",
				"description":"<p>This is just a fake description for the Poker Night.</p><p>Nothing to see!</p>",
				"start":moment().add('days',11),
				"end":moment().add('days',11),
				"url":"http://www.mikesmithdev.com/blog/aspnet-bundling-changes-output-with-user-agent-eureka-1/"
			},
			{
				"title":"Hackathon",
				"allday":"false",
				"description":"<p>This is just a fake description for the Hackathon.</p><p>Nothing to see!</p>",
				"start":moment().add('days',15),
				"end":moment().add('days',15),
				"url":"http://www.mikesmithdev.com/blog/worst-job-titles-in-internet-and-info-tech/"
			},
			{
				"title":"Beta Testing",
				"allday":"false",
				"description":"<p>This is just a fake description for the Beta Testing.</p><p>Nothing to see!</p>",
				"start":moment().add('days',22),
				"end":moment().add('days',22),
				"url":"http://www.mikesmithdev.com/blog/worst-job-titles-in-internet-and-info-tech/"
			},
			{
				"title":"Perl Meetup",
				"allday":"false",
				"description":"<p>This is just a fake description for the Perl Meetup.</p><p>Nothing to see... though no one would show up any way.</p>",
				"start":moment().subtract('days',20),
				"end":moment().subtract('days',20),
				"url":"http://www.mikesmithdev.com/blog/migrating-from-asp-net-to-ghost-node-js/"
			},
			{
				"title":"Node.js Meetup",
				"allday":"false",
				"description":"<p>This is just a fake description for the Node.js Meetup.</p><p>Nothing to see!</p>",
				"start":moment().add('days',25),
				"end":moment().add('days',25),
				"url":"http://www.mikesmithdev.com/blog/pdf-secure-access-and-log-downloads/"
			},
			{
				"title":"Javascript Meetup",
				"allday":"false",
				"description":"<p>This is just a fake description for the Javascript Meetup.</p><p>Nothing to see!</p>",
				"start":moment().subtract('days',27),
				"end":moment().subtract('days',27),
				"url":"http://www.mikesmithdev.com/blog/migrating-from-asp-net-to-ghost-node-js/"
			},
			{
				"title":"HTML Meetup",
				"allday":"false",
				"description":"<p>This is just a fake description for the HTML Meetup.</p><p>Nothing to see!</p>",
				"start":moment().subtract('days',22),
				"end":moment().subtract('days',22),
				"url":"http://www.mikesmithdev.com/blog/migrating-from-asp-net-to-ghost-node-js/"
			},
			{
				"title":"CSS Meetup",
				"allday":"false",
				"description":"<p>This is just a fake description for the CSS Meetup.</p><p>Nothing to see!</p>",
				"start":moment().add('days',27),
				"end":moment().add('days',27),
				"url":"http://www.mikesmithdev.com/blog/migrating-from-asp-net-to-ghost-node-js/"
			}
		]
	});
});
</script>

<!--
List out the imods course info dates (demo getting data from the controller file)
-->
<br>
<p id="startDate">Start Date Here:${startDate1}</p>
<p id="endDate">End Date Here:${endDate1}</p>
<p id="creditHours">Credit Hours Here:${creditHours1}</p>
<p id="timeRatio">Credit Hours/Time Ratio Here:${timeRatio1}</p>
<br>

<p >
	<form id="startDate" >
		Task Start Date -->
		<label>Choose Year:</label>
		<select id='chooseYear' name="years1">
		</select>

		<label>Choose Month:</label>
		<select id='chooseMonth' name="months1">
		</select>

		<label>Choose Day:</label>
		<select id='chooseDay' name="days1">
		</select>

		<label>Choose Hour:</label>
		<select id='chooseHour' name="hours1">
		</select>

		<br/>
	</form>
</p>


<p >
	<form id="endDate" >
		Task End Date -->
		<label>Choose Year:</label>
		<select id='chooseYear2' name="years1">
		</select>

		<label>Choose Month:</label>
		<select id='chooseMonth2' name="months1">
		</select>

		<label>Choose Day:</label>
		<select id='chooseDay2' name="days1">
		</select>

		<label>Choose Hour:</label>
		<select id='chooseHour2' name="hours1">
		</select>

		<br/>
	</form>
</p>

<form id="addTaskBut" >
	<input  type="button" value="Add Task" id = "addT" onclick="addTask()"/>
</form>







<!--



-->



</body>
</html>
