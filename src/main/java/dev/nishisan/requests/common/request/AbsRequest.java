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
package dev.nishisan.requests.common.request;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import dev.nishisan.requests.common.uc.IUserCredential;

/**
 * Abstract class representing a generic request.
 *
 * @param <T> the type of the payload
 * 
 * @author Lucas Nishimura
 */
public abstract class AbsRequest<T> implements IRequest<T> {

    private String requestId;
    private String traceId;
    private T payload;
    private IUserCredential userCredential;
    private Map<String, String> requestHeaders = new ConcurrentHashMap<>();

    public AbsRequest() {

    }

    public AbsRequest(T payload) {
        this.payload = payload;
    }

    public AbsRequest(String requestId, T payload) {
        this.requestId = requestId;
        this.payload = payload;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getRequestId() {
        return this.requestId;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setRequestId(String id) {
        this.requestId = id;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T getPayload() {
        return this.payload;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setPayload(T payload) {
        this.payload = payload;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addRequestHeader(String name, String value) {
        this.requestHeaders.put(name, value);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Map<String, String> getRequestHeaders() {
        return this.requestHeaders;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getRequestHeader(String name) {
        return this.requestHeaders.get(name);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setUserCredential(IUserCredential credential) {
        this.userCredential = credential;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IUserCredential getUserCredential() {
        return this.userCredential;
    }


    public String getTraceId() {
        return traceId;
    }

    public void setTraceId(String traceId) {
        this.traceId = traceId;
    }
}
