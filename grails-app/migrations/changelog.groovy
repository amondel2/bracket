databaseChangeLog = {
    include file: 'createTables-dataSource.groovy'
    include file: 'fixnullableTeamGame-dataSource.groovy'
    include file: 'addRoundsTbl-dataSource.groovy'
    include file: 'addSortOrder-dataSource.groovy'
}
