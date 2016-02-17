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


<script src="https://apis.google.com/js/client:plusone.js"></script>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>


<g:javascript src="source/fullCal/moment.js" defer="defer" />

<!--
<g:javascript src="source/schedule.js" defer="defer" />
-->
<g:javascript src="source/fullCal/fullcalendar.js" defer="defer" />



<meta name='layout' content='schedule'/>

<!--
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>

http://fullcalendar.io/docs/usage/
-->

</head>
<body>


	<div id='calendar'>
	</div>
	<!--


	<g:form action="create">

</g:form>
-->


<!--
-->
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
<p >
	<form id="guessMonth" >
		<label>Choose Month:</label>
		<select id='chooseMonth' name="months1">
		</select>

		<br/>
	</form>
</p>

<p >
	<form id="guessDay" >
		<label>Choose Day:</label>
		<select id='chooseDay' name="days1">
		</select>

		<br/>
	</form>
</p>

<p >
	<form id="guessHour" >
		<label>Choose Hour:</label>
		<select id='chooseHour' name="hours1">
		</select>

		<br/>
	</form>
</p>
-->

<!--
-->
<div hidden>

	<div googCal>
		<br>
	<iframe src="https://calendar.google.com/calendar/embed?height=600&amp;wkst=1&amp;bgcolor=%23FFFFFF&amp;src=imodssched%40gmail.com&amp;color=%231B887A&amp;ctz=America%2FPhoenix" style="border-width:2" width="1280" height="720" frameborder="2" scrolling="no"></iframe>	<br>
	</div>


	<p id="startDate1">${startDate1}</p>
	<p id="endDate1">${endDate1}</p>
	<p id="creditHours1">${creditHours1}</p>
	<p id="timeRatio1">${timeRatio1}</p>
	<p id="currName1">${currName1}</p>


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
