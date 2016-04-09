// commenting this out as it's never used...
// $(document).ready(function() {

// 	// page is now ready, initialize the calendar...
//  	$('#calendar').fullCalendar({
// 		dayClick: function() {
// 	    	alert('a day has been clicked!');
// 		}

// 	});

//     console.log($('#startDate1').text())
//     console.log($('#endDate1').text())
//     AddEvent(
//     	$('#startDate1').text(),
//     	$('#endDate1').text(),
//     	$('#currName1').text()
//     );

// 	moment().format();

// 	var years = [];

// 	for (var i = 2010; i <= 2035; i++) {
// 		years.push(i);
// 	}

// 	// Populate the choice box with choices from the years [2010 to 2035] array
// 	var choiceYear = document.getElementById('chooseYear');

// 	for(var i = 0; i < years.length; i++){

// 		// console.log(i);
// 	    var tempMonth = years[i];
// 	    var tempEle = document.createElement('option');
// 	    tempEle.textContent = tempMonth;
// 	    tempEle.value = tempMonth;
// 	    choiceYear.appendChild(tempEle);

// 	}

// 	choiceYear = document.getElementById('chooseYear2');

// 	for(var i = 0; i < years.length; i++){
// 		// console.log(i);
// 	    var tempMonth = years[i];
// 	    var tempEle = document.createElement('option');
// 	    tempEle.textContent = tempMonth;
// 	    tempEle.value = tempMonth;
// 	    choiceYear.appendChild(tempEle);
// 	}

//     var months = ['January','February','March','April','May','June','July','August','September','October','November','December'];

//     // Populate the choice box with choices from the months array
//     var choiceMonth = document.getElementById('chooseMonth');
//     for(var i = 0; i < months.length; i++){
//     	// console.log(i);
//         var tempMonth = months[i];
//         var tempEle = document.createElement('option');
//         tempEle.textContent = tempMonth;
//         tempEle.value = tempMonth;
//         choiceMonth.appendChild(tempEle);
//     }

//      choiceMonth = document.getElementById('chooseMonth2');
//     for(var i = 0; i < months.length; i++){
//     //    console.log(i);
//         var tempMonth = months[i];
//         var tempEle = document.createElement('option');
//         tempEle.textContent = tempMonth;
//         tempEle.value = tempMonth;
//         choiceMonth.appendChild(tempEle);
//     }

//     var days = [];

//     for (var i = 1; i <= 31; i++) {
//        days.push(i);
//     }
//     console.log(days);

//     // Populate the choice box with choices from the days [1 to 31] array
//     var choiceDay = document.getElementById('chooseDay');
//     for(var i = 0; i < days.length; i++){
//         // console.log(i);
//         var tempMonth = days[i];
//         var tempEle = document.createElement('option');
//         tempEle.textContent = tempMonth;
//         tempEle.value = tempMonth;
//         choiceDay.appendChild(tempEle);
//     }

//      choiceDay = document.getElementById('chooseDay2');
//     for(var i = 0; i < days.length; i++){
//         // console.log(i);
//         var tempMonth = days[i];
//         var tempEle = document.createElement('option');
//         tempEle.textContent = tempMonth;
//         tempEle.value = tempMonth;
//         choiceDay.appendChild(tempEle);
//     }

//     var hours = [];

//     for (var i = 1; i <= 23; i++) {
//        hours.push(i);
//     }

//     // Populate the choice box with choices from the hours [1 to 23] array
//     var choiceHour = document.getElementById('chooseHour');
//     for(var i = 0; i < hours.length; i++){
//         // console.log(i);
//         var tempMonth = days[i];
//         var tempEle = document.createElement('option');
//         tempEle.textContent = tempMonth;
//         tempEle.value = tempMonth;
//         choiceHour.appendChild(tempEle);
//     }

//      choiceHour = document.getElementById('chooseHour2');
//     for(var i = 0; i < hours.length; i++){
//         // console.log(i);
//         var tempMonth = days[i];
//         var tempEle = document.createElement('option');
//         tempEle.textContent = tempMonth;
//         tempEle.value = tempMonth;
//         choiceHour.appendChild(tempEle);
//     }

//     function addTask() {
//         var sYear = $( '#chooseYear option:selected' ).text();
//         var sMonth = $( '#chooseMonth option:selected' ).index();
//         var sDay = $( '#chooseDay option:selected' ).text();
//         var sHour = $( '#chooseHour option:selected' ).text();

//         var eYear = $( '#chooseYear2 option:selected' ).text();
//         var eMonth = $( '#chooseMonth2 option:selected' ).index();
//         var eDay = $( '#chooseDay2 option:selected' ).text();
//         var eHour = $( '#chooseHour2 option:selected' ).text();
//         console.log(sYear + sMonth + sDay + sHour);

//         var d1 = new Date(sYear, sMonth, sDay, sHour, 0, 0, 0);
//         var d2 = new Date(eYear, eMonth, eDay, eHour, 0, 0, 0);
//         console.log(d1);

//         AddEvent((d1), (d2), 'testTask');
//         // AddEvent(d1), (d2), 'new Task1');
//     }

// });
// /*
// var noTime = moment('2014-05-01');
// var dateC = moment('2014-10-11');

// console.log('Difference is ', noTime.diff(dateC, 'days'), 'days');
// noTime.set('year', 2013);
// */


// //Test JS code for Wcoomber
// /*

//     //3.    Do not hardcode a set number of suspects, rooms, or weapons. This data will be initialized as global variable arrays named, aptly enough, suspects, weapons, and rooms.
//     var suspects = ['Mrs. Peacock', 'Mrs. Green', 'Miss Scarlet', 'Colonel Mustard', 'Professor Plum'];
//     var weapons = ['Pistol', 'Knife', 'Wrench', 'Lead Pipe', 'Candlestick'];
//     var rooms = ['Kitchen', 'Study', 'Living Room', 'Dining Room', 'Library'];

//     var hideOne = document.getElementById('hiddenDom');
//     hideOne.style.display = 'none';
//     var historyShown = false;
//     var hideTwo = document.getElementById('hiddenDom2');
//     hideTwo.style.display = 'none';
//     var recordShown = false;

//     var nameGot = false;
//     document.getElementById('guessB').disabled = true;
//     document.getElementById('continueB').disabled = true;

//     var guessArray = [];
//     var wrongPiece = '';
//     var gameOver = false;
//     var pastGuesses = [];
//     var guessNum = -1;
//     //var winRecord = {};
//     var compWinsMsg = 'Computer Successfully Won';
//     var playerWinsMsg = 'Computer Lost to Player';
//     var compWinsNumber = 0;
//     var compLossNumber = 0;
//     var matchNum = 1;


//     var compPotentialRooms = [];
//     var compPotentialSuspects = [];
//     var compPotentialWeapons = [];


//     //1.    Display the full set of suspects, rooms, and weapons at the top of an HTML page for the user to see.
//     document.getElementById('roomInfo').innerHTML = 'Rooms: '+rooms;
//     document.getElementById('suspectInfo').innerHTML = 'Suspects: '+suspects;
//     document.getElementById('weaponsInfo').innerHTML = 'Weapons: '+weapons;
// */
