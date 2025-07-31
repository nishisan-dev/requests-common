/*
 * Copyright (C) 2023 Lucas Nishimura < lucas at nubsphere.com >
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
package dev.nishisan.requests.commom.dto;

/**
 *
 * @author Lucas Nishimura < lucas at nubsphere.com >
 * @created 11.07.2023
 */
public class ApiErrorDTO {

    private String msg;
    private Integer statusCode = 500;
    private String className = "";
    private Object details;
    private Object request;

    /**
     * @return the msg
     */
    public String getMsg() {
        return msg;
    }

    /**
     * @param msg the msg to set
     */
    public void setMsg(String msg) {
        this.msg = msg;
    }

    /**
     * @return the statusCode
     */
    public Integer getStatusCode() {
        return statusCode;
    }

    /**
     * @param statusCode the statusCode to set
     */
    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    /**
     * @return the className
     */
    public String getClassName() {
        return className;
    }

    /**
     * @param className the className to set
     */
    public void setClassName(String className) {
        this.className = className;
    }

    /**
     * @param details the details to set
     */
    public void setDetails(Object details) {
        this.details = details;
    }

    /**
     * @return the details
     */
    public Object getDetails() {
        return details;
    }

    /**
     * @return the request
     */
    public Object getRequest() {
        return request;
    }

    /**
     * @param request the request to set
     */
    public void setRequest(Object request) {
        this.request = request;
    }

}
