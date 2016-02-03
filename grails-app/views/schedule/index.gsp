<!DOCTYPE html>
<html><head><title>Scheduler</title>

	<!--
	<link href='fullcalendar.css' rel='stylesheet' />
	<link href='scheduler.css' rel='stylesheet' />
	<script src='moment.js'></script>
	<script src='jquery.js'></script>
	<script src='fullcalendar.js'></script>
	<script src='scheduler.js'></script>
	////////////////////////////////////////////////////////////////////////////////
	<link rel='stylesheet' href='fullcalendar/fullcalendar.css' />
	<script src='lib/jquery.min.js'></script>
	<script src='lib/moment.min.js'></script>
	<script src='fullcalendar/fullcalendar.js'></script>

	<script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>

	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>


	<g:external dir="bower_components/jquery/dist" file="jquery.js" />
	<g:external dir="bower_components/jquery/dist" file="jquery.min.js" />
	<g:javascript src="source/fullCal/jquery.js" defer="defer" />
-->

<g:external dir="css/source" file="fullcalendar.css" />


<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>


<g:javascript src="source/fullCal/moment.js" defer="defer" />

<g:javascript src="source/schedule.js" defer="defer" />

<g:javascript src="source/fullCal/fullcalendar.js" defer="defer" />




<meta name='layout' content='schedule'/>

<!--
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>

http://fullcalendar.io/docs/usage/
-->
</head>
<body>


	<div id='calendar'></div>
	<!--


	<g:form action="create">

</g:form>
-->


<!--
-->
<p>Start Date Here:${startDate1}</p>
<p>End Date Here:${endDate1}</p>
<p>Credit Hours Here:${creditHours1}</p>
<p>Credit Hours/Time Ratio Here:${timeRatio1}</p>





<!--
-->
<div hidden>

	<p id="roomInfo">
		<br/></p>
		<p id="suspectInfo">
			<br/></p>
			<p id="weaponsInfo">
				<br/></p>

			</p><p>

				<p id="nameChk">
					<form id="nameEnt" >
						Player Name: <input type="text" name="name" value="Wesley"><br>
						<input type="button" value="Enter" onclick="getName()"/>
					</form>
				</p>

				<p id="handInfo">
				</p>


				<p >
					<form id="guessRoom" >
						<label>Guess Room:</label>
						<select id='chooseRoom' name="rooms1">
						</select>

						<br/>
					</form>
				</p>

				<p >
					<form id="guessSuspect" >
						<label>Guess Suspect:</label>
						<select id='chooseSuspect' name="suspects2">
						</select>

						<br/>
					</form>
				</p>

				<p >
					<form id="guessWeapon" >
						<label>Guess Weapon:</label>
						<select id='chooseWeapon' name="weapons3">
						</select>

						<br/>
					</form>
				</p>



				<input  type="button" value="Guess" id = "guessB" onclick="getGuess()"/>

			</form>
		</p>

		<p id="postGuessInfo">
		</p>



		<p >
			<form id="showHist" >
				<input type="button" value="Show History" onclick="toggleHistory()"/>
			</form>

			<p id = "hiddenDom">This is where your and the computer's guess history will be (AFTER YOU OR THE COMPUTER MAKE A GUESS).</p>
		</p>




		<p >
			<form id="continueInfo" >
				<input  type="button" value="Continue"  id = "continueB" onclick="nextStep()"/>
			</form>
		</p>

		<p hidden  id = "dataWinRecord">"Computer's won record is stored in here"</p>
		<p  hidden id = "dataLossRecord">"Computer's loss record is stored in here"</p>
		<p  hidden id = "dataName">"Player's entered name is stored in here"</p>
		<p hidden  id = "dataDate">"Date when round is over is stored in here"</p>
		<p hidden  id = "dataMatchOutcome">"Outcome of the particular match (comp vs player) is stored in here"</p>


		<form id="showFullRec" >
			<input type="button" value="Show Record" onclick="toggleRecord()"/>
		</form>

		<p   id = "hiddenDom2">"Dynamically displays the WIN:LOSS record for the Computer, and a history of who the Computer played, the date, and the outcome."</p>
		<g:javascript src="source/schedule.js" defer="defer" />



	</p></div></body></html>
