databaseChangeLog = {

    changeSet(author: "aaron (generated)", id: "1664156362980-1") {
        createTable(tableName: "round") {
            column(name: "id", type: "VARCHAR(255)") {
                constraints(nullable: "false", primaryKey: "true", primaryKeyName: "roundPK")
            }

            column(name: "name", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "aaron (generated)", id: "1664156362980-2") {
        addColumn(tableName: "team_game") {
            column(name: "loser_id", type: "varchar(255)") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "aaron (generated)", id: "1664156362980-3") {
        addColumn(tableName: "team_game") {
            column(name: "round_id", type: "varchar(255)") {
                constraints(nullable: "true")
            }
        }
    }

    changeSet(author: "aaron (generated)", id: "1664156362980-4") {
        addColumn(tableName: "team_game") {
            column(name: "winner_id", type: "varchar(255)") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "aaron (generated)", id: "1664156362980-5") {
        addForeignKeyConstraint(baseColumnNames: "loser_id", baseTableName: "team_game", constraintName: "FK3ydo63pdhgiv09akb5ydtimgs", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "team", validate: "true")
    }

    changeSet(author: "aaron (generated)", id: "1664156362980-6") {
        addForeignKeyConstraint(baseColumnNames: "round_id", baseTableName: "team_game", constraintName: "FK77xnt4oa6d87y6ney3x6mv302", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "round", validate: "true")
    }

    changeSet(author: "aaron (generated)", id: "1664156362980-7") {
        addForeignKeyConstraint(baseColumnNames: "winner_id", baseTableName: "team_game", constraintName: "FKtkg01flpa35l20091lox2rcr6", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "team", validate: "true")
    }

    changeSet(author: "aaron (generated)", id: "1664156532144-1") {
        dropNotNullConstraint(columnDataType: "varchar(255)", columnName: "loser_id", tableName: "team_game")
    }

    changeSet(author: "aaron (generated)", id: "1664156532144-2") {
        dropNotNullConstraint(columnDataType: "varchar(255)", columnName: "round_id", tableName: "team_game")
    }

    changeSet(author: "aaron (generated)", id: "1664156532144-3") {
        dropNotNullConstraint(columnDataType: "varchar(255)", columnName: "winner_id", tableName: "team_game")
    }
}