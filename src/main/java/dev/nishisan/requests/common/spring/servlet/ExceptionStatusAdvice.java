/*
 * Copyright (C) 2023 Lucas Nishimura < lucas at nishisan.dev >
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package dev.nishisan.requests.common.spring.servlet;

import dev.nishisan.requests.common.exception.BasicException;
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
 * Provides centralized exception-handling logic and custom response mapping for controller methods.
 * This class implements {@link ResponseBodyAdvice} to manipulate HTTP responses when the response body
 * is an instance of {@link BasicException}.
 *
 * <h2>Key Features:</h2>
 * 1. Evaluates whether a response body is an instance of {@link BasicException} and, if so, allows customization of the HTTP status code.
 * 2. Ensures that the proper HTTP status code is set for responses containing a {@link BasicException} body.
 *
 * <h2>Configuration:</h2>
 * Annotated with {@code @RestControllerAdvice}, which makes this class globally applicable to all
 * controller methods in the application for the purpose of exception handling and response advice.
 */
@RestControllerAdvice
public class ExceptionStatusAdvice implements ResponseBodyAdvice<Object> {

    @Override
    public boolean supports(@NonNull MethodParameter returnType, @NonNull Class<? extends HttpMessageConverter<?>> converterType) {
        return BasicException.class.isAssignableFrom(returnType.getParameterType());
    }

    /**
     * Intercepts the response body before it is written to the HTTP response and applies custom behavior,
     * such as setting the HTTP status code if the response body is an instance of {@link BasicException}.
     *
     * @param body the body of the response, which may be null
     * @param returnType the {@link MethodParameter} describing the return type of the controller method
     * @param selectedContentType the {@link MediaType} of the response
     * @param selectedConverterType the chosen converter type for serializing the response body
     * @param request the current HTTP request
     * @param response the current HTTP response
     * @return the response body, either unmodified or updated
     */
    @Override
    public Object beforeBodyWrite(Object body, @NonNull MethodParameter returnType, @NonNull MediaType selectedContentType,
                                  @NonNull Class<? extends HttpMessageConverter<?>> selectedConverterType,
                                  @NonNull ServerHttpRequest request, @NonNull ServerHttpResponse response) {
        if (body instanceof BasicException exceptionResponse) {
            if (response instanceof ServletServerHttpResponse) {
                if (exceptionResponse.getStatusCode() > 0) {
                    ((ServletServerHttpResponse) response).getServletResponse().setStatus(exceptionResponse.getStatusCode());
                }
            }
        }
        return body;
    }
}
