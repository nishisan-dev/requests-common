/*
 * Copyright (C) 2025 Lucas Nishimura <lucas.nishimura at gmail.com>
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

import dev.nishisan.requests.commom.uc.IUserCredential;

/**
 * Interface representing a generic request.
 *
 * @param <T> the type of the payload
 * 
 * @author Lucas Nishimura
 */
public interface IRequest<T> {

    /**
     * Gets the request ID.
     *
     * @return the request ID
     */
    public String getRequestId();

    /**
     * Sets the request ID.
     *
     * @param id the new request ID
     */
    public void setRequestId(String id);

    /**
     * Gets the payload of the request.
     *
     * @return the payload
     */
    public T getPayload();

    /**
     * Sets the payload of the request.
     *
     * @param payload the new payload
     */
    public void setPayload(T payload);

    /**
     * Adds a header to the request.
     *
     * @param name the name of the header
     * @param value the value of the header
     */
    public void addRequestHeader(String name, String value);

    /**
     * Gets all the headers of the request.
     *
     * @return a map of header names to values
     */
    public Map<String, String> getRequestHeaders();

    /**
     * Gets a specific header by name.
     *
     * @param name the name of the header
     * @return the value of the header, or null if not found
     */
    public String getRequestHeader(String name);

    /**
     * Sets the user credentials for the request.
     *
     * @param credential the user credentials
     */
    public void setUserCredential(IUserCredential credential);

    /**
     * Gets the user credentials for the request.
     *
     * @return the user credentials
     */
    public IUserCredential getUserCredential();

}
