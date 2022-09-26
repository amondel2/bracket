databaseChangeLog = {

    changeSet(author: "aaron (generated)", id: "1663988727105-1") {
        createTable(tableName: "team") {
            column(name: "id", type: "VARCHAR(255)") {
                constraints(nullable: "false", primaryKey: "true", primaryKeyName: "teamPK")
            }

            column(name: "name", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "aaron (generated)", id: "1663988727105-2") {
        createTable(tableName: "team_game") {
            column(name: "id", type: "VARCHAR(255)") {
                constraints(nullable: "false", primaryKey: "true", primaryKeyName: "team_gamePK")
            }

            column(name: "away_score", type: "INT") {
                constraints(nullable: "false")
            }

            column(name: "home_score", type: "INT") {
                constraints(nullable: "false")
            }

            column(name: "home_team_id", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "away_team_id", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "aaron (generated)", id: "1663988727105-3") {
        addUniqueConstraint(columnNames: "name", constraintName: "UC_TEAMNAME_COL", tableName: "team")
    }

    changeSet(author: "aaron (generated)", id: "1663988727105-4") {
        addForeignKeyConstraint(baseColumnNames: "away_team_id", baseTableName: "team_game", constraintName: "FKnqcahxo8e42kredk4puf3lsb8", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "team", validate: "true")
    }

    changeSet(author: "aaron (generated)", id: "1663988727105-5") {
        addForeignKeyConstraint(baseColumnNames: "home_team_id", baseTableName: "team_game", constraintName: "FKph58drqr9qf7qbqw5h1j99xu2", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "team", validate: "true")
    }
}
