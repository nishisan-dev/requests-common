package dev.nishisan.requests.commom.exception;

public interface IBasicException {
    public <T extends IBasicException> T addDetails(String key, Object value);
}
