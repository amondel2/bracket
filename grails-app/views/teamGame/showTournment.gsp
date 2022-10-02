<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <g:applyLayout name="main" />
    <title>Tournament</title>
</head>
<style>
.buttonDiv {
    height: 50px;
}
.scrollableForm {
    height: calc(100% - 50px);
    overflow-y: scroll;
    border-bottom: 2px solid;
    margin-bottom: 7px;
}
.roundDiv {
    display: inline-grid;
    margin: 0px 10px 10px 10px;

}
</style>
<body>
<div class="scrollableForm flex-box">
<form id="checkfrm" onsubmit="return false;">
<g:each in="${tournmanet}" var="teamRound">
    <div class="roundDiv">
        <h2>Round ${teamRound[0].round.name}</h2>
        <table>
            <tr><th>Team 1</th><th>Team 2</th><th>Winner</th></tr>
            <g:each in="${teamRound}" var="teamGame">
             <tr id="${teamGame.id}">
                 <td>${teamGame.homeTeam}<br><input type="number" max="25" min="0" step="1" required aria-required="true" aria-labelledby="team1score" name="homeSore" value="${teamGame.homeScore}"/></td>
                 <td>${teamGame.awayTeam}<br><input type="number" max="25" min="0" step="1" required aria-required="true" aria-labelledby="team2Score" name="awaySore" value="${teamGame.awayScore}"/></td>
                 <td>${teamGame.winner}</td>
             </tr>
            </g:each>
        </table>
    </div>
</g:each>
</form>
</div>
<div class="buttonDiv">
<button id="save" type="button" class="btn btn-primary">Save</button>
<button id="contTournment" type="button" class="btn btn-secondary">Continue Tournament</button>
</div>
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
