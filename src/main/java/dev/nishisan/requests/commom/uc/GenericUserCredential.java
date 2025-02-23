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
package dev.nishisan.requests.commom.uc;

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
