<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <g:applyLayout name="main" />
    <title>Tournament</title>
</head>

<body>
<table>
    <tr><g:sortableColumn property="round" title="Round"  titleKey="teamGame.round.name" /><th>Team 1</th><th>Team 2</th><th>Team 1 Score</th><th>Team 2 Score</th><th>Winner</th></tr>
    <g:each in="${tournmanet}" var="teamGame">
     <tr id="${teamGame.id}">
        <td>${teamGame.round.name}</td><td>${teamGame.homeTeam}</td><td>${teamGame.awayTeam}</td>
         <td><g:select name="homeSore" from="${0..25}" value="${teamGame.homeScore}"/></td>
         <td><g:select name="awaySore" from="${0..25}" value="${teamGame.awayScore}"/></td>
         <td>${teamGame.winner}</td>
     </tr>
    </g:each>
</table>
<button id="save">Save</button>

<script>
    $("#save").on('click',function() {
        let constarty = [];
        $.each($("tr"),
            function(index,value) {
                if($(value).attr('id')) {
                    let dataParts = '{"homeScore":"' + $(value).find('select[name="homeSore"]').val() + '","awayScore" : "' +  $(value).find('select[name="awaySore"]').val() + '", "id" : "' + $(value).attr('id') + '"}';
                    constarty.push(dataParts);
                }
            });
        let strData = "["  +  constarty.join(',') + "]"
        let data = {"items": strData}
            $.ajax({
                method: "POST",
                url: "/teamGame/saveRound",
                data: data
            }).done(function() {
                window.location.reload();
            });
    });
</script>

</body>
</html>
