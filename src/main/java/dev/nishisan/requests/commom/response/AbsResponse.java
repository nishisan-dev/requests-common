/*
 * Copyright (C) 2025 Lucas Nishimura < lucas at nishisan.dev > 
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
package dev.nishisan.requests.commom.response;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 * @author Lucas Nishimura < lucas at nishisan.dev >
 */
public abstract class AbsResponse<T> implements IResponse<T> {

    private String sourceRequestId;

    private String responseId;

    private T payload;

    private int statusCode;

    private Map<String, String> responseHeaders = new ConcurrentHashMap<>();

    public AbsResponse() {
        this.responseId = UUID.randomUUID().toString();
    }

    public AbsResponse(String sourceRequestId, T payload) {
        this();
        this.sourceRequestId = sourceRequestId;
        this.payload = payload;

    }

    public AbsResponse(T payload) {
        this();
        this.payload = payload;
    }

    @Override
    public String getSourceRequestId() {
        return this.sourceRequestId;
    }

    @Override
    public void setSourceRequestId(String id) {
        this.sourceRequestId = id;
    }

    @Override
    public void setResponseId(String id) {
        this.responseId = id;
    }

    @Override
    public String getResponseId() {
        return this.responseId;
    }

    @Override
    public T getPayload() {
        return this.payload;
    }

    @Override
    public void setPayload(T payload) {
        this.payload = payload;
    }

    @Override
    public void setStatusCode(int code) {
        this.statusCode = code;
    }

    @Override
    public int getStatusCode() {
        return this.statusCode;
    }

    @Override
    public void addResponseHeader(String name, String value) {
        this.responseHeaders.put(name, value);
    }

    @Override
    public String getResponseHeader(String name) {
        return this.responseHeaders.get(name);
    }

    @Override
    public Map<String, String> getResponseHeaders() {
        return this.getResponseHeaders();
    }

}
