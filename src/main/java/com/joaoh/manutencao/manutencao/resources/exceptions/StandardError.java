package com.joaoh.manutencao.manutencao.resources.exceptions;

import java.io.Serializable;


public class StandardError implements Serializable {
    private Long timestamp;
    private Integer Status;
    private String error;
    private String message;
    private String path;

    public StandardError(Long timestamp, Integer status, String error, String message, String path) {
        this.timestamp = timestamp;
        Status = status;
        this.error = error;
        this.message = message;
        this.path = path;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public Integer getStatus() {
        return Status;
    }

    public void setStatus(Integer status) {
        Status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public String toString() {
        return "{" +
                " timestamp='" + getTimestamp() + "'" +
                ", Status='" + getStatus() + "'" +
                ", error='" + getError() + "'" +
                ", message='" + getMessage() + "'" +
                ", path='" + getPath() + "'" +
                "}";
    }
}
