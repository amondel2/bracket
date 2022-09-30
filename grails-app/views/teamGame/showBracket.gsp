<%--
  Created by IntelliJ IDEA.
  User: aaron
  Date: 9/26/22
  Time: 10:20 PM
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <g:applyLayout name="main" />
    <title>Bracket</title>
    <style>
    .round {
        float: left;
        border: 1px solid #AAA;
    }
    .round.r-of-4 {
        margin: 10px;
        border-radius: 3px 3px 3px 3px;
    }
    .round.r-of-2 {
        margin-top: 25px;
    }
    .bracket-game {
        max-width: 125px;
        margin: 10px 0;
        border-bottom: 3px solid #AAA;
    }
    .player {
        min-width: 100px;
        padding-left: 10px;
    }
    .player .score {
        padding-right: 10px;
    }
    .player.win {
        background-color: #B8F2B8;
    }
    .player.loss {
        background-color: #F2B8B8;
    }
    .connectors {
        float: left;
        min-width: 35px;
    }
    .connectors.r-of-2 .top-line {
        position: relative;
        top: 30px;
        width: 17px;
        border: 1px solid #AAA;
    }
    .connectors.r-of-2 .bottom-line {
        position: relative;
        top: 81px;
        width: 17px;
        border: 1px solid #AAA;
    }
    .connectors.r-of-2 .vert-line {
        position: relative;
        top: 26px;
        left: -16px;
        height: 55px;
        border-right: 2px solid #AAA;
    }
    .connectors.r-of-2 .next-line {
        position: relative;
        top: -4px;
        left: 17px;
        width: 17px;
        border: 1px solid #AAA;
    }
    .clear {
        clear: both;
    }
    .score INPUT {
        max-width: 102px;
    }
    </style>
</head>

<body>
<form id="showFrm">
${raw(bracketHtml)}
</form>
<button class="btn btn-primary" id="nxtRound">Get Next Round</button>
<script>
    function gatherData() {
        let constarty = [];
        $.each($(".bracket-game"),
            function(index,value) {
                  let scores = $(value).find('.score input');
                  let dataParts = '{"team1": { "id":"' + scores.first().attr('name') + '", "score":"' + scores.first().val() + '"},  "team2" : { "id":"' + scores.last().attr('name') + '", "score":"' + scores.last().val() + '"} }';
                  constarty.push(dataParts);

            });
        return "["  +  constarty.join(',') + "]"
    }
    $("#nxtRound").on('click',function() {
        if ($("#showFrm :invalid").length == 0) {
            let data = {"items": gatherData()}
            $.ajax({
                method: "POST",
                url: "/teamGame/nextRound",
                data: data
            }).done(function (data) {
                $("#showFrm").html(data);
            });
        }
    });

</script>

</body>
</html>