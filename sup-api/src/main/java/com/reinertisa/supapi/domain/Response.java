package com.reinertisa.supapi.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.http.HttpStatus;

import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class Response {
    private String time;
    private int code;
    private String path;
    private HttpStatus status;
    private String message;
    private String exception;
    private Map<?, ?> data;

    public Response() {
    }

    public Response(String time, int code, String path, HttpStatus status, String message, String exception, Map<?, ?> data) {
        this.time = time;
        this.code = code;
        this.path = path;
        this.status = status;
        this.message = message;
        this.exception = exception;
        this.data = data;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getException() {
        return exception;
    }

    public void setException(String exception) {
        this.exception = exception;
    }

    public Map<?, ?> getData() {
        return data;
    }

    public void setData(Map<?, ?> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Response{" +
                "time='" + time + '\'' +
                ", code=" + code +
                ", path='" + path + '\'' +
                ", status=" + status +
                ", message='" + message + '\'' +
                ", exception='" + exception + '\'' +
                ", data=" + data +
                '}';
    }
}
