
$(document).ready(function() {

 // page is now ready, initialize the calendar...

 $('#calendar').fullCalendar({
dayClick: function() {
	alert('a day has been clicked!');
}

});



        console.log($("#startDate1").text())
            console.log($("#endDate1").text())
        AddEvent(($("#startDate1").text()), ($("#endDate1").text()), $("#currName1").text());


});
moment().format();


function AddEvent(StartDate, EndDate, title1){
            $('#calendar').fullCalendar('renderEvent', {
                        id: StartDate,
                        title: title1,
                        start: StartDate,
                        end: EndDate,
                        allDay: false
                    }, true );
        }

    var years = [];

for (var i = 2010; i <= 2035; i++) {
   years.push(i);
}

//Populate the choice box with choices from the years [2010 to 2035] array
var choiceYear = document.getElementById("chooseYear");
for(var i = 0; i < years.length; i++){
//    console.log(i);
    var tempMonth = years[i];
    var tempEle = document.createElement("option");
    tempEle.textContent = tempMonth;
    tempEle.value = tempMonth;
    choiceYear.appendChild(tempEle);
}
choiceYear = document.getElementById("chooseYear2");
for(var i = 0; i < years.length; i++){
//    console.log(i);
    var tempMonth = years[i];
    var tempEle = document.createElement("option");
    tempEle.textContent = tempMonth;
    tempEle.value = tempMonth;
    choiceYear.appendChild(tempEle);
}



	var months = ["January","February","March","April","May","June","July","August","September","October","November","December"];

    //Populate the choice box with choices from the months array
    var choiceMonth = document.getElementById("chooseMonth");
    for(var i = 0; i < months.length; i++){
    //    console.log(i);
        var tempMonth = months[i];
        var tempEle = document.createElement("option");
        tempEle.textContent = tempMonth;
        tempEle.value = tempMonth;
        choiceMonth.appendChild(tempEle);
    }

     choiceMonth = document.getElementById("chooseMonth2");
    for(var i = 0; i < months.length; i++){
    //    console.log(i);
        var tempMonth = months[i];
        var tempEle = document.createElement("option");
        tempEle.textContent = tempMonth;
        tempEle.value = tempMonth;
        choiceMonth.appendChild(tempEle);
    }


    var days = [];

    for (var i = 1; i <= 31; i++) {
       days.push(i);
    }
    console.log(days);

    //Populate the choice box with choices from the days [1 to 31] array
    var choiceDay = document.getElementById("chooseDay");
    for(var i = 0; i < days.length; i++){
        //console.log(i);
        var tempMonth = days[i];
        var tempEle = document.createElement("option");
        tempEle.textContent = tempMonth;
        tempEle.value = tempMonth;
        choiceDay.appendChild(tempEle);
    }

     choiceDay = document.getElementById("chooseDay2");
    for(var i = 0; i < days.length; i++){
        //console.log(i);
        var tempMonth = days[i];
        var tempEle = document.createElement("option");
        tempEle.textContent = tempMonth;
        tempEle.value = tempMonth;
        choiceDay.appendChild(tempEle);
    }

    var hours = [];

    for (var i = 1; i <= 23; i++) {
       hours.push(i);
    }
    //Populate the choice box with choices from the hours [1 to 23] array
    var choiceHour = document.getElementById("chooseHour");
    for(var i = 0; i < hours.length; i++){
        //console.log(i);
        var tempMonth = days[i];
        var tempEle = document.createElement("option");
        tempEle.textContent = tempMonth;
        tempEle.value = tempMonth;
        choiceHour.appendChild(tempEle);
    }

     choiceHour = document.getElementById("chooseHour2");
    for(var i = 0; i < hours.length; i++){
        //console.log(i);
        var tempMonth = days[i];
        var tempEle = document.createElement("option");
        tempEle.textContent = tempMonth;
        tempEle.value = tempMonth;
        choiceHour.appendChild(tempEle);
    }

    function addTask() {
		var sYear = $( "#chooseYear option:selected" ).text();
        var sMonth = $( "#chooseMonth option:selected" ).index();
        var sDay = $( "#chooseDay option:selected" ).text();
        var sHour = $( "#chooseHour option:selected" ).text();

        var eYear = $( "#chooseYear2 option:selected" ).text();
        var eMonth = $( "#chooseMonth2 option:selected" ).index();
        var eDay = $( "#chooseDay2 option:selected" ).text();
        var eHour = $( "#chooseHour2 option:selected" ).text();
        console.log(sYear + sMonth + sDay + sHour);

        var d1 = new Date(sYear, sMonth, sDay, sHour, 0, 0, 0);
        var d2 = new Date(eYear, eMonth, eDay, eHour, 0, 0, 0);
        console.log(d1);

        AddEvent((d1), (d2), "testTask");
            //    AddEvent(d1), (d2), "new Task1");
    }

/*
var noTime = moment('2014-05-01');
var dateC = moment('2014-10-11');

console.log('Difference is ', noTime.diff(dateC, 'days'), 'days');
noTime.set('year', 2013);
*/

	//3.	Do not hardcode a set number of suspects, rooms, or weapons. This data will be initialized as global variable arrays named, aptly enough, suspects, weapons, and rooms.
	var suspects = ["Mrs. Peacock", "Mrs. Green", "Miss Scarlet", "Colonel Mustard", "Professor Plum"];
	var weapons = ['Pistol', 'Knife', 'Wrench', 'Lead Pipe', 'Candlestick'];
	var rooms = ['Kitchen', 'Study', 'Living Room', 'Dining Room', 'Library'];

	var hideOne = document.getElementById('hiddenDom');
	hideOne.style.display = 'none';
	var historyShown = false;
	var hideTwo = document.getElementById('hiddenDom2');
	hideTwo.style.display = 'none';
	var recordShown = false;

	var nameGot = false;
	document.getElementById("guessB").disabled = true;
	document.getElementById("continueB").disabled = true;

	var guessArray = [];
	var wrongPiece = "";
	var gameOver = false;
	var pastGuesses = [];
	var guessNum = -1;
	//var winRecord = {};
	var compWinsMsg = "Computer Successfully Won";
	var playerWinsMsg = "Computer Lost to Player";
	var compWinsNumber = 0;
	var compLossNumber = 0;
	var matchNum = 1;


	var	compPotentialRooms = [];
	var	compPotentialSuspects = [];
	var	compPotentialWeapons = [];


	//1.	Display the full set of suspects, rooms, and weapons at the top of an HTML page for the user to see.
	document.getElementById("roomInfo").innerHTML = "Rooms: "+rooms;
	document.getElementById("suspectInfo").innerHTML = "Suspects: "+suspects;
	document.getElementById("weaponsInfo").innerHTML = "Weapons: "+weapons;


	/*
	*	4.	Display an HTML form that allows you to select 1 suspect, 1 weapon, and 1 room. I suggest you use a choice box for this but in fact it does not matter what you use as long as it is an HTML form.
	*/

	//Populate the choice box with choices from the rooms array
	var choiceRoom = document.getElementById("chooseRoom");
	for(var i = 0; i < rooms.length; i++){
		var tempRoom = rooms[i];
		var tempEle = document.createElement("option");
		tempEle.textContent = tempRoom;
		tempEle.value = tempRoom;
		choiceRoom.appendChild(tempEle);
	}

	//Populate the choice box with choices from the suspects array
	var choiceSuspect = document.getElementById("chooseSuspect");
	for(var i = 0; i < suspects.length; i++){
		var tempSuspect = suspects[i];
		var tempEle = document.createElement("option");
		tempEle.textContent = tempSuspect;
		tempEle.value = tempSuspect;
		choiceSuspect.appendChild(tempEle);
	}

	//Populate the choice box with choices from the weapons array
	var choiceWeapon = document.getElementById("chooseWeapon");
	for(var i = 0; i < weapons.length; i++){
		var tempWeapon = weapons[i];
		var tempEle = document.createElement("option");
		tempEle.textContent = tempWeapon;
		tempEle.value = tempWeapon;
		choiceWeapon.appendChild(tempEle);
	}


	//2.	Underneath that, provide a textbox prompting for the user’s name with a Submit button. When the user’s enters her/his name, replace the form with a dynamic message saying “Welcome <name>”.
	//Also enables The Guess and Continue buttons after you enter in a player name. This is not a bug, this is intended for the records system.
	function getName() {
		var x = document.getElementById("nameEnt");
		var message = "";
		message = 'Welcome ' + x.elements[0].value + '<br>';

		//document.getElementById("dataName").value = x.elements[0].value ;



		var tempEle = document.createElement("p");
		tempEle.textContent = x.elements[0].value;
		tempEle.value = x.elements[0].value;
		document.getElementById("dataName").appendChild(tempEle);
		//for(var i = 0; i < document.getElementById("dataName").childElementCount; i++){
		//console.log(i);
		//}
		document.getElementById("nameEnt").innerHTML = message;
		//var y = document.getElementById("dataName").value;
		//console.log(y);
		nameGot = true;
		document.getElementById("guessB").disabled = false;
		document.getElementById("continueB").disabled = false;
	}

	var deck = rooms.concat(suspects.concat(weapons));

	for(var i = 0; i < rooms.length; i++){
		compPotentialRooms[i] = rooms [i];
	}

	for(var i = 0; i < suspects.length; i++){
		compPotentialSuspects[i] = suspects [i];
	}

	for(var i = 0; i < weapons.length; i++){
		compPotentialWeapons[i] = weapons [i];
	}


	var deckLengthPlay = 0.0;
	deckLengthPlay = deck.length-3;

	var deck = rooms.concat(suspects.concat(weapons));
	//deck = suspects + weapons + rooms;

	console.log(deckLengthPlay);
	console.log(deck);
	console.log(deck.length);

	randNumSus = 0.0;
	randNumSus = Math.abs(Math.round(Math.random()*(suspects.length-1)));
	console.log(randNumSus);

	var soluSuspect = suspects[randNumSus];

	console.log(soluSuspect);

	randNumWep = 0.0;
	randNumWep = Math.abs(Math.round(Math.random()*(weapons.length-1)));
	console.log(randNumWep);

	var soluWeapon = weapons[randNumWep];

	console.log(soluWeapon);

	randNumRom = 0.0;
	randNumRom = Math.abs(Math.round(Math.random()*(rooms.length-1)));
	console.log(randNumRom);

	var soluRoom = rooms[randNumRom];

	console.log(soluRoom);

	var solution = [soluSuspect, soluWeapon, soluRoom]
	console.log('Solution: '+ solution);

	var playDeck = deck.slice(0);

	var a = playDeck.indexOf(soluSuspect);

	if(a != -1) {
		playDeck.splice(a, 1);
	}

	var b = playDeck.indexOf(soluWeapon);
	if(b != -1) {
		playDeck.splice(b, 1);
	}

	var c = playDeck.indexOf(soluRoom);

	if(c != -1) {
		playDeck.splice(c, 1);
	}

	console.log('playDeck: ' + playDeck);
	console.log('playDeck Length : ' + playDeck.length);

	var shuffledDeck = playDeck.slice(0);

	shuffledDeck = shuffleDeck(shuffledDeck);
	/*
		* Shuffle our deck of playable cards
		* Algorithm from here: https://en.wikipedia.org/wiki/Fisher%E2%80%93Yates_shuffle#The_modern_algorithm
	*/
	function shuffleDeck(deck) {
		for (var i = deck.length - 1; i > 0; i--) {
			var j = Math.floor(Math.random() * (i + 1));
			var temp = deck[i];
			deck[i] = deck[j];
			deck[j] = temp;
		}
		return deck;
	}

	console.log('shuffledDeck: ' + shuffledDeck);
	console.log('shuffledDeck Length: ' + shuffledDeck.length);
	console.log('playDeck: ' + playDeck);
	console.log('playDeck Length : ' + playDeck.length);


	var playerHand = shuffledDeck.slice(0, Math.round((shuffledDeck.length)/2));

	console.log(playerHand);

	var compHand = shuffledDeck.slice(Math.round((shuffledDeck.length)/2), shuffledDeck.length);
	console.log(compHand);

	var completePlayDeck = playerHand.concat(compHand);
	console.log(completePlayDeck);

	//3.	Display the set of “cards” the human user “holds in her/his hand”.
	pHandString = 'Player Hand: ' + playerHand;
	document.getElementById("handInfo").innerHTML = pHandString;

	/*
	* This function Records the date when the player or computer wins and hides the data in the DOM
	*
	*/
	function playRecord(){

		var d = new Date();
		var tempEle2 = document.createElement("p");

		tempEle2.textContent = d;
		tempEle2.value = d;
		document.getElementById("dataDate").appendChild(tempEle2);

	}

	//assembles the entire win record with the win:loss ratio, the player's name, the date, and the match outcome
	function fullRecord(){

		var tempStr = '';
		var tempEle9 = document.createElement("p");
		//console.log(document.getElementById("dataWinRecord").childNodes[matchNum].value);
		tempStr = 			document.getElementById("dataWinRecord").childNodes[matchNum].value + ':' +  document.getElementById("dataLossRecord").childNodes[matchNum].value + ' ' + document.getElementById("dataName").childNodes[matchNum].value + ' ' + document.getElementById("dataDate").childNodes[matchNum].value + ' ' + document.getElementById("dataMatchOutcome").childNodes[matchNum].value;



		console.log(tempStr);
		tempEle9.textContent = tempStr;
		tempEle9.value = tempStr;
		document.getElementById("hiddenDom2").appendChild(tempEle9);
		console.log("OHHHHH BOYYY!!");
		matchNum++;

	}

	/*
	*5.	The submit button for your form should cause Javascript to read the form values, and compare them to the secret.
	*a.	If the guess matches the secret, dynamically display a “win” message
	*b.	If the guess does not match the secret, dynamically display a message stating 1) the guess did not match the secret, and 2) Reveal ONE AND ONLY ONE incorrect component of your guess.
	*/
	function getGuess() {
		var x = document.getElementById("guessRoom");
		var inputRoom = x.elements[0].value ;
		//document.getElementById("guessRoom").innerHTML = inputRoom;

		var y = document.getElementById("guessSuspect");
		var inputSuspect = y.elements[0].value ;
		//document.getElementById("guessSuspect").innerHTML = inputSuspect;

		var z = document.getElementById("guessWeapon");
		var inputWeapon = z.elements[0].value ;
		// document.getElementById("guessWeapon").innerHTML = inputWeapon;

		guessArray = [inputSuspect,inputWeapon,inputRoom];
		guessNum++;
		pastGuesses[guessNum] = guessArray;


		var tempEle3 = document.createElement("p");

		tempEle3.textContent = pastGuesses[guessNum];
		tempEle3.value = pastGuesses[guessNum];
		document.getElementById("hiddenDom").appendChild(tempEle3);
		//document.getElementById("hiddenDom").innerHTML = pastGuesses;

		console.log(pastGuesses);
		var winn = checkGuess(guessArray);

		console.log(guessArray);
		console.log(winn);
		if (winn == true){
			winString = 'WINNER! Players CORRECT SOLUTION WAS: ' + solution;
			document.getElementById("postGuessInfo").innerHTML = winString;
			playRecord();
			compLossNumber++;
			var tempEle4 = document.createElement("p");

			tempEle4.textContent = compLossNumber;
			tempEle4.value = compLossNumber;
			document.getElementById("dataLossRecord").appendChild(tempEle4);


			tempEle4 = document.createElement("p");

			tempEle4.textContent = playerWinsMsg;
			tempEle4.value = playerWinsMsg;
			document.getElementById("dataMatchOutcome").appendChild(tempEle4);


			tempEle4 = document.createElement("p");

			tempEle4.textContent = compWinsNumber;
			tempEle4.value = compWinsNumber;
			document.getElementById("dataWinRecord").appendChild(tempEle4);
			fullRecord();
			gameOver = true;
		}
		else{
			wrongString = 'WRONG! ONE AND ONLY ONE PIECE OF INCORRECT INFO WAS: ' + wrongPiece;
			document.getElementById("postGuessInfo").innerHTML = wrongString;
		}


	}

	function checkGuess(array){
		var win = true;
		if (solution.length !== array.length){
			win = false;
		}
		for(var i = 0; i < solution.length; i++) {
			if(solution[i] !== array[i] && win !== false){
				win = false;
				wrongPiece = array[i];
				//console.log(wrongPiece);
			}
		}
		return win;
	}

	//7.	Provide a button (outside the form) named “Show History” that dynamically shows the guesses you and the Computer have made so far in the current game. When shown, the “Show History” button should become “Hide History” (you are essentially creating a toggle button).
	function toggleHistory(){
		if (historyShown == false){
			hideOne.style.display = 'inline';
			//console.log("1");
			historyShown =true;
		}
		else{
			hideOne.style.display = 'none';
			//console.log("2");
			historyShown = false;
		}
	}

	//8.	Provide a button named “Show Record” that when pressed, dynamically displays the won-loss record for the Computer, and a history of who the Computer played, the date, and the outcome.
	function toggleRecord(){
		if (recordShown == false){
			hideTwo.style.display = 'inline';
			//console.log("1");
			recordShown =true;
		}
		else{
			hideTwo.style.display = 'none';
			//console.log("2");
			recordShown = false;
		}

	}

	/*
	*c.	Provide a “Continue” button that either resets the game to the beginning (if the user won), or allows the Computer to move (next step).
	*/
	function nextStep(){
		//console.log("GAMEOVERSTATE= "+gameOver);
		if (gameOver == true){
			reset();
		}
		else{
			console.log("will do comp turn");
			computerTurn();
		}
	}

	/*
	*6.	When the user’s (incorrect) move is complete, have the Computer make a guess. A simple random guess will do, though the program should know not to guess cards that it itself holds. Like #4, display a message indicating whether the Computer’s guess was *correct or not, and a Continue button at the end. CAVEAT: The distinction here is that you are told the Computer’s guess, but should not be told the incorrect component of the guess, which is different than what happens for the user (5.b.2).
	*/
	function computerTurn(){
		console.log(compHand);

		var i = 0;
		var o = 0;
		var p = 0;
		var realCompPotentialRoomLength = compPotentialRooms.length;
		var realCompPotentialSuspectLength = compPotentialSuspects.length;
		var realCompPotentialWeaponLength = compPotentialWeapons.length;
		var count = 0;

		var compChoiceRoom = [];
		var compChoiceSuspect = [];
		var compChoiceWeapon = [];


		//populates the compChoiceRoom array with rooms that are not in the computer's hand
		for (var j = 0; compPotentialRooms.length > j; j++) {
			if (compHand.indexOf(compPotentialRooms[j]) == -1) {
				compChoiceRoom.push(compPotentialRooms[j]);
				count++;
			}
		}




		//populates the compChoiceSuspect array with suspects that are not in the computer's hand
		for (var j = 0; compPotentialSuspects.length > j; j++) {
			if (compHand.indexOf(compPotentialSuspects[j]) == -1) {
				compChoiceSuspect.push(compPotentialSuspects[j]);
				count++;
			}
		}



		//populates the compPotentialWeapons array with weapons that are not in the computer's hand
		for (var j = 0; compPotentialWeapons.length > j; j++) {
			if (compHand.indexOf(compPotentialWeapons[j]) == -1) {
				compChoiceWeapon.push(compPotentialWeapons[j]);
				count++;
			}
		}


		var		randSuspectIndex = 0.0;
		randSuspectIndex = Math.abs(Math.round(Math.random()*(compChoiceSuspect.length-1)));


		var compGuessSuspect = compChoiceSuspect[randSuspectIndex];


		var randWeaponIndex = 0.0;
		randWeaponIndex = Math.abs(Math.round(Math.random()*(compChoiceWeapon.length-1)));


		var  compGuessWeapon = compChoiceWeapon[randWeaponIndex];



		randomRoomIndex = 0.0;
		randomRoomIndex = Math.abs(Math.round(Math.random()*(compChoiceRoom.length-1)));

		var compGuessRoom = compChoiceRoom[randomRoomIndex];



		var compSolution = [compGuessSuspect, compGuessWeapon, compGuessRoom]
		console.log('Computers Guess: '+ compSolution);

		guessNum++;
		pastGuesses[guessNum] = compSolution;

		console.log(pastGuesses);
		var winn = checkGuess(compSolution);

		var tempEle3 = document.createElement("p");

		tempEle3.textContent = pastGuesses[guessNum];
		tempEle3.value = pastGuesses[guessNum];
		document.getElementById("hiddenDom").appendChild(tempEle3);
		//document.getElementById("hiddenDom").innerHTML = pastGuesses;

		console.log(compSolution);
		console.log(winn);
		if (winn == true){
			winString = 'COMPUTER IS WINNER! CORRECT SOLUTION WAS: ' + solution;
			document.getElementById("postGuessInfo").innerHTML = winString;
			compWinsNumber++;
			var tempEle5 = document.createElement("p");

			tempEle5.textContent = compWinsNumber;
			tempEle5.value = compWinsNumber;
			document.getElementById("dataWinRecord").appendChild(tempEle5);

			tempEle5 = document.createElement("p");

			tempEle5.textContent = compWinsMsg;
			tempEle5.value = compWinsMsg;
			document.getElementById("dataMatchOutcome").appendChild(tempEle5);


			tempEle5 = document.createElement("p");

			tempEle5.textContent = compLossNumber;
			tempEle5.value = compLossNumber;
			document.getElementById("dataLossRecord").appendChild(tempEle5);
			playRecord();
			fullRecord();
			gameOver = true;
		}
		else{

			wrongString = 'COMPUTER WAS WRONG! COMPUTERS GUESS WAS (YOU ARE NOT TOLD the incorrect component of the guess): ' + compSolution;
			document.getElementById("postGuessInfo").innerHTML = wrongString;

		}




	}

	//resets the game but preserves the game records
	function reset(){
		console.log("RESET START!");

		compPotentialRooms = [];
		compPotentialSuspects = [];
		compPotentialWeapons = [];
		gameOver =false;

		for(var i = 0; i < rooms.length; i++){
			compPotentialRooms[i] = rooms [i];
		}

		for(var i = 0; i < suspects.length; i++){
			compPotentialSuspects[i] = suspects [i];
		}

		for(var i = 0; i < weapons.length; i++){
			compPotentialWeapons[i] = weapons [i];
		}

		randNumSus = 0.0;
		randNumSus = Math.abs(Math.round(Math.random()*(suspects.length-1)));
		console.log(randNumSus);

		soluSuspect = suspects[randNumSus];

		console.log(soluSuspect);

		randNumWep = 0.0;
		randNumWep = Math.abs(Math.round(Math.random()*(weapons.length-1)));
		console.log(randNumWep);

		soluWeapon = weapons[randNumWep];

		console.log(soluWeapon);

		randNumRom = 0.0;
		randNumRom = Math.abs(Math.round(Math.random()*(rooms.length-1)));
		console.log(randNumRom);

		soluRoom = rooms[randNumRom];

		console.log(soluRoom);

		solution = [soluSuspect, soluWeapon, soluRoom]
		console.log('Solution: '+ solution);

		playDeck = deck.slice(0);

		a = playDeck.indexOf(soluSuspect);

		if(a != -1) {
			playDeck.splice(a, 1);
		}

		b = playDeck.indexOf(soluWeapon);
		if(b != -1) {
			playDeck.splice(b, 1);
		}

		c = playDeck.indexOf(soluRoom);

		if(c != -1) {
			playDeck.splice(c, 1);
		}

		console.log('playDeck: ' + playDeck);
		console.log('playDeck Length : ' + playDeck.length);

		shuffledDeck = playDeck.slice(0);

		shuffledDeck = shuffleDeck(shuffledDeck);

		console.log('shuffledDeck: ' + shuffledDeck);
		console.log('shuffledDeck Length: ' + shuffledDeck.length);
		console.log('playDeck: ' + playDeck);
		console.log('playDeck Length : ' + playDeck.length);


		playerHand = shuffledDeck.slice(0, Math.round((shuffledDeck.length)/2));

		console.log(playerHand);

		compHand = shuffledDeck.slice(Math.round((shuffledDeck.length)/2), shuffledDeck.length);
		console.log(compHand);

		completePlayDeck = playerHand.concat(compHand);
		console.log(completePlayDeck);

		pHandString = 'Player Hand: ' + playerHand;
		document.getElementById("handInfo").innerHTML = pHandString;

		pastGuesses = [];
		guessNum = -1;

		document.getElementById("nameEnt").innerHTML = "";


		var tempEle2 = document.createElement("input");
		tempEle2.type = "text";
		tempEle2.value = "Wesley";
		tempEle2.name = "name";
		document.getElementById("nameEnt").appendChild(tempEle2);

		//http://www.w3schools.com/jsref/event_onclick.asp

		tempEle2 = document.createElement("input");
		tempEle2.type = "button";
		tempEle2.value = "Enter";
		//tempEle2.onclick = "getName()"
		tempEle2.setAttribute("onclick", "getName()");
		document.getElementById("nameEnt").appendChild(tempEle2);

		nameGot = false;
		document.getElementById("guessB").disabled = true;
		document.getElementById("continueB").disabled = true;

		hideOne.innerHTML = "This is where your and the computer's guess history will be. (GUESS HISTORY IS CLEARED AFTER EVERY COMPLETED ROUND)";

		document.getElementById("postGuessInfo").innerHTML = "";
		console.log("RESET END!");
	}
