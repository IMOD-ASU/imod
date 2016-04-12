<!DOCTYPE html>

<html lang="en" xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Extend FullCalendar Events with Bootstrap Modal</title>
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" type="text/css" rel="stylesheet" />
	<g:external dir="bower_components/fullcalendar/dist" file="fullcalendar.min.css" />
    <link href="https://code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css" type="text/css" rel="stylesheet" />
    <style>.ui-dialog-title {background-image:url(http://image005.flaticon.com/1/svg/109/109978.svg);background-repeat:no-repeat;padding-left:25px;}</style>
</head>
<body>
    <div class="container">
        <div class="row">
            <div class="col-xs-12">
                <h1>FullCalendar Events with jQuery UI Modal</h1>
                <p>from <a href="http://www.mikesmithdev.com/blog/fullcalendar-event-details-with-bootstrap-modal/" target="_blank">http://www.mikesmithdev.com/blog/fullcalendar-event-details-with-bootstrap-modal/</a></p>
                <br />
                <div id="bootstrapModalFullCalendar"></div>
            </div>
        </div>
    </div>

    <div id="eventContent" title="Event Details">
        <div id="eventInfo"></div>
        <p><strong><a id="eventLink" target="_blank">Read More</a></strong></p>
    </div>


    <script src="https://code.jquery.com/jquery.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.12.0/moment.js"></script>
<g:external dir="bower_components/fullcalendar/dist" file="fullcalendar.min.js" />
    <script src="https://code.jquery.com/ui/1.11.4/jquery-ui.min.js"></script>

    <script>
        $(document).ready(function() {
            $('#bootstrapModalFullCalendar').fullCalendar({
                header: {
                    left: '',
                    center: 'prev title next',
                    right: ''
                },
                eventClick: function (event, jsEvent, view) {
                    //set the values and open the modal
                    $("#eventInfo").html(event.description);
                    $("#eventLink").attr('href', event.url);
                    $("#eventContent").dialog({
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
                      "end":moment(),
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
</body>
</html>
