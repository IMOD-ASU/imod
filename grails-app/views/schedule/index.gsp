<!DOCTYPE html>
<html>
	<head>
		<title>
			Scheduler
		</title>

		<g:external dir="bower_components/fullcalendar/dist" file="fullcalendar.min.css" />

		<g:external dir="bower_components/jquery/dist" file="jquery.min.js" />
		<g:external dir="bower_components/moment/min" file="moment.min.js" />
		<g:external dir="bower_components/fullcalendar/dist" file="fullcalendar.js" />
		<g:external dir="source" file="schedule.js" />

		<meta name='layout' content='schedule'/>
	</head>
	<body>
		<div id='calendar'></div>

		<p>
			Start Date Here:${startDate1}
		</p>
		<p>
			End Date Here:${endDate1}
		</p>
		<p>
			Credit Hours Here:${creditHours1}
		</p>
		<p>
			Credit Hours/Time Ratio Here:${timeRatio1}
		</p>

		<div hidden>
			<p id="roomInfo">
				<br/>
			</p>
			<p id="suspectInfo">
				<br/>
			</p>
			<p id="weaponsInfo">
				<br/>
			</p>

			<p id="nameChk">
				<form id="nameEnt" >
					Player Name: <input type="text" name="name" value="Wesley"><br>
					<input type="button" value="Enter" onclick="getName()"/>
				</form>
			</p>

			<p id="handInfo">
			</p>

			<p>
				<form id="guessRoom" >
					<label>Guess Room:</label>
					<select id='chooseRoom' name="rooms1">
					</select>

					<br/>
				</form>
			</p>

			<p>
				<form id="guessSuspect" >
					<label>Guess Suspect:</label>
					<select id='chooseSuspect' name="suspects2">
					</select>

					<br/>
				</form>
			</p>

			<p>
				<form id="guessWeapon" >
					<label>Guess Weapon:</label>
					<select id='chooseWeapon' name="weapons3">
					</select>

					<br/>
				</form>
			</p>

			<input  type="button" value="Guess" id = "guessB" onclick="getGuess()"/>

			<p id="postGuessInfo">
			</p>

			<p>
				<form id="showHist" >
					<input type="button" value="Show History" onclick="toggleHistory()"/>
				</form>

				<p id="hiddenDom">This is where your and the computer's guess history will be (AFTER YOU OR THE COMPUTER MAKE A GUESS).</p>
			</p>

			<p>
				<form id="continueInfo" >
					<input  type="button" value="Continue"  id = "continueB" onclick="nextStep()"/>
				</form>
			</p>

			<p hidden id="dataWinRecord">"Computer's won record is stored in here"</p>
			<p hidden id="dataLossRecord">"Computer's loss record is stored in here"</p>
			<p hidden id="dataName">"Player's entered name is stored in here"</p>
			<p hidden id="dataDate">"Date when round is over is stored in here"</p>
			<p hidden id="dataMatchOutcome">"Outcome of the particular match (comp vs player) is stored in here"</p>


			<form id="showFullRec" >
				<input type="button" value="Show Record" onclick="toggleRecord()"/>
			</form>

			<p id = "hiddenDom2">"Dynamically displays the WIN:LOSS record for the Computer, and a history of who the Computer played, the date, and the outcome."</p>
				<g:javascript src="source/schedule.js" defer="defer" />
			</p>
		</div>
	</body>
</html>
