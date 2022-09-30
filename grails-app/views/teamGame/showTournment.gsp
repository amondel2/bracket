<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <g:applyLayout name="main" />
    <title>Tournament</title>
</head>

<body>
<form id="checkfrm" onsubmit="return false;">
<table>
    <tr><g:sortableColumn property="round" title="Round"  titleKey="teamGame.round.name" /><th>Team 1</th><th>Team 2</th><th id="team1score">Team 1 Score</th><th id="team2Score">Team 2 Score</th><th>Winner</th></tr>
    <g:each in="${tournmanet}" var="teamGame">
     <tr id="${teamGame.id}">
        <td>${teamGame.round.name}</td><td>${teamGame.homeTeam}</td><td>${teamGame.awayTeam}</td>
         <td><input type="number" max="25" min="0" step="1" required aria-required="true" aria-labelledby="team1score" name="homeSore" value="${teamGame.homeScore}"/></td>
         <td><input type="number" max="25" min="0" step="1" required aria-required="true" aria-labelledby="team2Score" name="awaySore" value="${teamGame.awayScore}"/></td>
         <td>${teamGame.winner}</td>
     </tr>
    </g:each>
</table>
<button id="save" type="button" class="btn btn-primary">Save</button>
<button id="contTournment" type="button" class="btn btn-secondary">Continue Tournament</button>
</form>
<script>
    $("#save").on('click',function() {
        let data = {"items": gatherData()};
            $.ajax({
                method: "POST",
                url: "/teamGame/saveRound",
                data: data
            }).done(function() {
                window.location.reload();
            });
    });
    $("#contTournment").on('click',function() {
        if($("#checkfrm :invalid").length === 0) {
            let data = {"items": gatherData()};
            $.ajax({
                method: "POST",
                url: "/teamGame/saveRound",
                data: data
            }).done(function () {
                window.location.href = '/teamGame/resumeTournment';
            });
        } else {
            alert("Fill Out All Data");
        }
    });
    function gatherData() {
        let constarty = [];
        $.each($("tr"),
            function(index,value) {
                if($(value).attr('id')) {
                    let dataParts = '{"homeScore":"' + $(value).find('input[name="homeSore"]').val() + '","awayScore" : "' +  $(value).find('input[name="awaySore"]').val() + '", "id" : "' + $(value).attr('id') + '"}';
                    constarty.push(dataParts);
                }
            });
        return "["  +  constarty.join(',') + "]";
    }
</script>

</body>
</html>
