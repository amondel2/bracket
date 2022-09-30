databaseChangeLog = {

    changeSet(author: "aaron (generated)", id: "1664506675420-1") {
        dropNotNullConstraint(columnDataType: "varchar(255)", columnName: "loser_id", tableName: "tournment_tracker")
    }
}
