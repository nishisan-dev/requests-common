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
package dev.nishisan.requests.common.uc;

/**
 * Interface representing user credentials.
 *
 * @param <T> the type of the user data
 * 
 * @author Lucas Nishimura
 */
public interface IUserCredential<T> {

    /**
     * Sets the user ID.
     *
     * @param id the new user ID
     */
    public void setUserId(String id);

    /**
     * Gets the user ID.
     *
     * @return the user ID
     */
    public String getUserId();

    /**
     * Sets the user data.
     *
     * @param userData the new user data
     */
    public void setUserData(T userData);

    /**
     * Gets the user data.
     *
     * @return the user data
     */
    public T getUserData();

}
