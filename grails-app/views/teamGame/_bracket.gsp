<div class="round r-of-4">
    <g:set var="minus"><g:if test="${totalTeams % 2 == 0}">1</g:if><g:else>0</g:else></g:set>
    <g:each status="i" in="${(0..<totalGames)}" var="item">
        <div class="bracket-game">
            <g:if test="${i == 0 && (totalTeams % 2 != 0)}">
                <div class="player top win">${sortedTeams[i]}
                    <div class="score"><input type="hidden" name="${sortedTeams[i].id}" type="number" value="1000" /></div>
                </div>
                <div class="player bot loss">
                    n/A
                    <div class="score"><input type="hidden" name="" type="number" value="0" /></div>
                </div>
            </g:if>
            <g:else>
                <div class="player <g:if test="${i == 0}">top</g:if><g:else>bot</g:else>">${sortedTeams[i]}
                    <div class="score"><input name="${sortedTeams[i].id}" type="number" value="0" required></div>
                </div>
                <div  class="player bot">${sortedTeams[totalTeams - i - Integer.valueOf(minus.toString())]}
                    <div class="score"><input name="${sortedTeams[totalTeams- i -Integer.valueOf(minus.toString())].id}" type="number" value="0" required></div>
                </div>
            </g:else>
        </div>
    </g:each>
</div>