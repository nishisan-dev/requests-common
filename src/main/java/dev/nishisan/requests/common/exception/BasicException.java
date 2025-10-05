package dev.nishisan.requests.common.exception;

import dev.nishisan.requests.common.request.IRequest;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public abstract class BasicException extends Exception implements Serializable, IBasicException {
    protected IRequest<?> request;
    protected Integer statusCode = 500;
    private Map<String, Object> details = new HashMap<>();


    public BasicException(){

    }

    public BasicException(IRequest<?> request) {
        this.request = request;
    }

    public BasicException(String message){
        super(message);
    }

    public BasicException(String message, Throwable th){
        super(message,th);
    }

    public BasicException(String message, IRequest<?> request){
        super(message);
        this.request = request;
    }

    public BasicException(IRequest<?> request, Integer statusCode, Map<String, Object> details) {
        this.request = request;
        this.statusCode = statusCode;
        this.details = details;
    }

    public BasicException(String message, IRequest<?> request, Integer statusCode, Map<String, Object> details) {
        super(message);
        this.request = request;
        this.statusCode = statusCode;
        this.details = details;
    }

    public BasicException(String message, Throwable cause, IRequest<?> request, Integer statusCode, Map<String, Object> details) {
        super(message, cause);
        this.request = request;
        this.statusCode = statusCode;
        this.details = details;
    }

    public BasicException(Throwable cause, IRequest<?> request, Integer statusCode, Map<String, Object> details) {
        super(cause);
        this.request = request;
        this.statusCode = statusCode;
        this.details = details;
    }

    public BasicException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, IRequest<?> request, Integer statusCode, Map<String, Object> details) {
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

    public Map<String, Object> details(){
        return  this.details;
    }
}
