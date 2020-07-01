package ru.zdb.web.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiResponse<T> {
    private ResponseStatus status;
    T content;

    public void setStatus(String code, String message) {
        status = new ResponseStatus(code, message);
    }
}
