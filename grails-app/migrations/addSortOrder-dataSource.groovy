databaseChangeLog = {

    changeSet(author: "aaron (generated)", id: "1664166361345-4") {
        addColumn(tableName: "round") {
            column(name: "sort_order", type: "integer") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "aaron (generated)", id: "1664166361345-1") {
        dropDefaultValue(columnDataType: "varchar(255)", columnName: "loser_id", tableName: "team_game")
    }

    changeSet(author: "aaron (generated)", id: "1664166361345-2") {
        dropDefaultValue(columnDataType: "varchar(255)", columnName: "round_id", tableName: "team_game")
    }

    changeSet(author: "aaron (generated)", id: "1664166361345-3") {
        dropDefaultValue(columnDataType: "varchar(255)", columnName: "winner_id", tableName: "team_game")
    }
}
