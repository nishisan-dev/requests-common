/*
 * Copyright (C) 2025 Lucas Nishimura 
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */
package dev.nishisan.requests.commom.request;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import dev.nishisan.requests.commom.annotations.RequiredField;
import dev.nishisan.requests.commom.uc.IUserCredential;

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
