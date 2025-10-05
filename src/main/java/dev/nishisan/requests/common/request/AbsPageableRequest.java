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
package dev.nishisan.requests.common.request;

import dev.nishisan.requests.common.dto.PageableDTO;

/**
 *
 * @author Lucas Nishimura < lucas at nubsphere.com >
 * @created 25.07.2023
 */
public class AbsPageableRequest extends AbsRequest<PageableDTO> {

    public AbsPageableRequest(Integer page, Integer size) {

        this.setPayload(new PageableDTO(page, size));
    }

    public AbsPageableRequest(Integer page, Integer size, String sort, String sortDirection, String query) {
        this.setPayload(new PageableDTO(page, size, sort, sortDirection, query));
    }

}
