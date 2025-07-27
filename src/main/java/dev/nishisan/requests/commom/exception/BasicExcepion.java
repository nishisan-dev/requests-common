package dev.nishisan.requests.commom.exception;

import dev.nishisan.requests.commom.annotations.RequiredField;
import dev.nishisan.requests.commom.request.AbsRequest;
import dev.nishisan.requests.commom.request.IRequest;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public abstract class BasicExcepion extends Exception implements Serializable, IBasicException {
    protected IRequest<?> request;
    protected Integer statusCode = 500;
    private Map<String, Object> details = new HashMap<>();

    public BasicExcepion(IRequest request, Integer statusCode, Map<String, Object> details) {
        this.request = request;
        this.statusCode = statusCode;
        this.details = details;
    }

    public BasicExcepion(String message, IRequest<?> request, Integer statusCode, Map<String, Object> details) {
        super(message);
        this.request = request;
        this.statusCode = statusCode;
        this.details = details;
    }

    public BasicExcepion(String message, Throwable cause, IRequest<?> request, Integer statusCode, Map<String, Object> details) {
        super(message, cause);
        this.request = request;
        this.statusCode = statusCode;
        this.details = details;
    }

    public BasicExcepion(Throwable cause, IRequest<?> request, Integer statusCode, Map<String, Object> details) {
        super(cause);
        this.request = request;
        this.statusCode = statusCode;
        this.details = details;
    }

    public BasicExcepion(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, IRequest<?> request, Integer statusCode, Map<String, Object> details) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.request = request;
        this.statusCode = statusCode;
        this.details = details;
    }

    public IRequest<?> getRequest() {
        return request;
    }

    public void setRequest(IRequest<?> request) {
        this.request = request;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    @Override
    public void printStackTrace() {
        if (this.details != null) {
            this.details.forEach((k, v) -> {
                System.err.println("k: -> " + v);
            });

        }
        super.printStackTrace();
    }

    protected void addDetail(String key, Object value) {
        this.details.put(key, value);
    }

    public abstract <T extends IBasicException> T details(String key,Object value);
}
