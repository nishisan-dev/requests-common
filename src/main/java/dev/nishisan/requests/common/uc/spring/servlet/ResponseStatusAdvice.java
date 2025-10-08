package dev.nishisan.requests.common.uc.spring.servlet;

import dev.nishisan.requests.common.exception.BasicException;
import dev.nishisan.requests.common.response.AbsResponse;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * A controller advice that provides behavior for modifying HTTP response statuses dynamically.
 *
 * This class implements the {@link ResponseBodyAdvice} interface to intercept and process the
 * response body of controller methods annotated with {@code @RestController}. Specifically,
 * it allows setting the HTTP status of the response based on the properties of the response
 * object.
 *
 * The implementation is designed to work with response objects that are instances of
 * {@code AbsResponse} or {@code BasicException}, where it dynamically applies the corresponding
 * HTTP status code to the {@code HttpServletResponse}.
 */
@RestControllerAdvice
public class ResponseStatusAdvice implements ResponseBodyAdvice<Object> {

    @Override
    public boolean supports(@NonNull MethodParameter returnType, @NonNull Class<? extends HttpMessageConverter<?>> converterType) {
        return AbsResponse.class.isAssignableFrom(returnType.getParameterType());
    }

    /**
     * Intercepts and processes the body of the HTTP response before it is written.
     *
     * This method evaluates the response body to determine if it is an instance
     * of AbsResponse or BasicException. If so, it extracts the HTTP status code
     * from the response object and sets it in the HttpServletResponse when applicable.
     *
     * @param body the response body object to be written
     * @param returnType the method return type of the controller
     * @param selectedContentType the media type selected for the response
     * @param selectedConverterType the converter type chosen to serialize the response
     * @param request the server-side HTTP request
     * @param response the server-side HTTP response
     * @return the processed response body, potentially modified but typically returned unaltered
     */
    @Override
    public Object beforeBodyWrite(Object body, @NonNull MethodParameter returnType, @NonNull MediaType selectedContentType,
                                  @NonNull Class<? extends HttpMessageConverter<?>> selectedConverterType,
                                  @NonNull ServerHttpRequest request, @NonNull ServerHttpResponse response) {
        if (body instanceof AbsResponse<?> absResponse) {
            if (response instanceof ServletServerHttpResponse) {
                if (absResponse.getStatusCode() > 0) {
                    ((ServletServerHttpResponse) response).getServletResponse().setStatus(absResponse.getStatusCode());
                }
            }
        }else if (body instanceof BasicException basicException){
            if (response instanceof ServletServerHttpResponse) {
                if (basicException.getStatusCode() > 0) {
                    ((ServletServerHttpResponse) response).getServletResponse().setStatus(basicException.getStatusCode());
                }
            }
        }
        return body;
    }
}
