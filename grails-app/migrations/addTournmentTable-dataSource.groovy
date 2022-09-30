databaseChangeLog = {

    changeSet(author: "aaron (generated)", id: "1664250430716-1") {
        createTable(tableName: "tournment_tracker") {
            column(name: "id", type: "VARCHAR(255)") {
                constraints(nullable: "false", primaryKey: "true", primaryKeyName: "tournment_trackerPK")
            }

            column(name: "loser_score", type: "INT") {
                constraints(nullable: "false")
            }

            column(name: "winer_score", type: "INT") {
                constraints(nullable: "false")
            }

            column(name: "winner_id", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "loser_id", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "aaron (generated)", id: "1664250430716-2") {
        addForeignKeyConstraint(baseColumnNames: "winner_id", baseTableName: "tournment_tracker", constraintName: "FKmwe79g5vw7g91wu9whol1k8rp", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "team", validate: "true")
    }

    changeSet(author: "aaron (generated)", id: "1664250430716-3") {
        addForeignKeyConstraint(baseColumnNames: "loser_id", baseTableName: "tournment_tracker", constraintName: "FKt7gtre2obhc7s4yxmtyulr6i8", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "team", validate: "true")
    }
}
