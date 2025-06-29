package dev.nishisan.requests.commom.exception;

import dev.nishisan.requests.commom.request.AbsRequest;
import dev.nishisan.requests.commom.request.IRequest;

import java.io.Serializable;
import java.util.Map;

public abstract class BasicExcepion extends Exception implements Serializable, IBasicException {
    protected IRequest<? extends AbsRequest> request;
    protected Integer statusCode = 500;
    private Map<String, Object> details;

    public BasicExcepion(IRequest<? extends AbsRequest> request, Integer statusCode, Map<String, Object> details) {
        this.request = request;
        this.statusCode = statusCode;
        this.details = details;
    }

    public BasicExcepion(String message, IRequest<? extends AbsRequest> request, Integer statusCode, Map<String, Object> details) {
        super(message);
        this.request = request;
        this.statusCode = statusCode;
        this.details = details;
    }

    public BasicExcepion(String message, Throwable cause, IRequest<? extends AbsRequest> request, Integer statusCode, Map<String, Object> details) {
        super(message, cause);
        this.request = request;
        this.statusCode = statusCode;
        this.details = details;
    }

    public BasicExcepion(Throwable cause, IRequest<? extends AbsRequest> request, Integer statusCode, Map<String, Object> details) {
        super(cause);
        this.request = request;
        this.statusCode = statusCode;
        this.details = details;
    }

    public BasicExcepion(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, IRequest<? extends AbsRequest> request, Integer statusCode, Map<String, Object> details) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.request = request;
        this.statusCode = statusCode;
        this.details = details;
    }

    public IRequest<? extends AbsRequest> getRequest() {
        return request;
    }

    public void setRequest(IRequest<? extends AbsRequest> request) {
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
}
