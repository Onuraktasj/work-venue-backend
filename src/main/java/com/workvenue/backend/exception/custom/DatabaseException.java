package com.workvenue.backend.exception.custom;

public class DatabaseException extends Exception {
    private String databaseTableName;
    private String operationType; // TODO: enum.

    public DatabaseException(String databaseTableName, String operationType) {
        this.databaseTableName = databaseTableName;
        this.operationType = operationType;
    }

    public String getDatabaseTableName() {
        return databaseTableName;
    }

    public void setDatabaseTableName(String databaseTableName) {
        this.databaseTableName = databaseTableName;
    }

    public String getOperationType() {
        return operationType;
    }

    public void setOperationType(String operationType) {
        this.operationType = operationType;
    }

    @Override
    public String toString() {
        return String.format("Does not %1$s object at the %2$s Table.", operationType, databaseTableName);
    }
}
