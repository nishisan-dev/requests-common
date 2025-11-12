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
package dev.nishisan.requests.common.uc;

/**
 * Class representing generic user credentials.
 * 
 * This class extends {@link AbsUserCredential} with a payload of type {@link Object}.
 * 
 * @author Lucas Nishimura
 */
public class GenericUserCredential extends AbsUserCredential<Object> {

    /**
     * Default constructor.
     */
    public GenericUserCredential() {
    }

    /**
     * Constructor with user ID.
     *
     * @param userId the user ID
     */
    public GenericUserCredential(String userId) {
        super(userId);
    }

}
