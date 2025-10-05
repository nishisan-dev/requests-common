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
package dev.nishisan.requests.common.response;

import java.util.Map;

/**
 * Interface representing a generic response.
 *
 * @param <T> the type of the payload
 * 
 * @author Lucas Nishimura
 */
public interface IResponse<T> {

    /**
     * Gets the source request ID.
     *
     * @return the source request ID
     */
    public String getSourceRequestId();

    /**
     * Sets the source request ID.
     *
     * @param id the new source request ID
     */
    public void setSourceRequestId(String id);

    /**
     * Sets the response ID.
     *
     * @param id the new response ID
     */
    public void setResponseId(String id);

    /**
     * Gets the response ID.
     *
     * @return the response ID
     */
    public String getResponseId();

    /**
     * Gets the payload of the response.
     *
     * @return the payload
     */
    public T getPayload();

    /**
     * Sets the payload of the response.
     *
     * @param payload the new payload
     */
    public void setPayload(T payload);

    /**
     * Sets the status code of the response.
     *
     * @param code the new status code
     */
    public void setStatusCode(int code);

    /**
     * Gets the status code of the response.
     *
     * @return the status code
     */
    public int getStatusCode();

    /**
     * Adds a header to the response.
     *
     * @param name the name of the header
     * @param value the value of the header
     */
    public void addResponseHeader(String name, String value);

    /**
     * Gets a specific header by name.
     *
     * @param name the name of the header
     * @return the value of the header, or null if not found
     */
    public String getResponseHeader(String name);

    /**
     * Gets all the headers of the response.
     *
     * @return a map of header names to values
     */
    public Map<String, String> getResponseHeaders();
}
