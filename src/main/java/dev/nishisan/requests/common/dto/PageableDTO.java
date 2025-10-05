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
package dev.nishisan.requests.common.dto;

/**
 *
 * @author Lucas Nishimura < lucas at nubsphere.com >
 * @created 25.07.2023
 */
public class PageableDTO {

    private Integer page = 0;
    private Integer size = 10;
    private String sort;
    private String direction;
    private String query;

    public PageableDTO() {
    }

    public PageableDTO(Integer page, Integer size) {
        this.page = page;
        this.size = size;
    }

    public PageableDTO(String sort, String direction) {
        this.sort = sort;
        this.direction = direction;
    }

    public PageableDTO(Integer page, Integer size, String sort, String direction) {
        this.sort = sort;
        this.direction = direction;
        this.page = page;
        this.size = size;
    }

    public PageableDTO(Integer page, Integer size, String sort, String direction, String query) {
        this.sort = sort;
        this.direction = direction;
        this.page = page;
        this.size = size;
        this.query = query;
    }

    /**
     * @return the page
     */
    public Integer getPage() {
        return page;
    }

    /**
     * @param page the page to set
     */
    public void setPage(Integer page) {
        this.page = page;
    }

    /**
     * @return the size
     */
    public Integer getSize() {
        return size;
    }

    /**
     * @param size the size to set
     */
    public void setSize(Integer size) {
        this.size = size;
    }

    /**
     * @return the sort
     */
    public String getSort() {
        return sort;
    }

    /**
     * @param sort the sort to set
     */
    public void setSort(String sort) {
        this.sort = sort;
    }

    /**
     * @return the direction
     */
    public String getDirection() {
        return direction;
    }

    /**
     * @param direction the direction to set
     */
    public void setDirection(String direction) {
        this.direction = direction;
    }

    /**
     * @return the query
     */
    public String getQuery() {
        return query;
    }

    /**
     * @param query the query to set
     */
    public void setQuery(String query) {
        this.query = query;
    }

}
