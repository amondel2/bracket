databaseChangeLog = {

    changeSet(author: "aaron (generated)", id: "1663990617398-1") {
        dropNotNullConstraint(columnDataType: "int", columnName: "away_score", tableName: "team_game")
    }

    changeSet(author: "aaron (generated)", id: "1663990617398-2") {
        dropNotNullConstraint(columnDataType: "int", columnName: "home_score", tableName: "team_game")
    }
}
