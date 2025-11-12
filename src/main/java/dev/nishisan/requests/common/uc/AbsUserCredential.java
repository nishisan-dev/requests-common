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
 * Abstract class representing user credentials.
 *
 * @param <T> the type of the user data
 * 
 * @author Lucas Nishimura
 */
public abstract class AbsUserCredential<T> implements IUserCredential<T> {

    private String userId;
    private T userData;

    /**
     * Default constructor.
     */
    public AbsUserCredential() {
    }

    /**
     * Constructor with user ID.
     *
     * @param userId the user ID
     */
    public AbsUserCredential(String userId) {
        this();
        this.userId = userId;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setUserId(String id) {
        this.userId = id;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getUserId() {
        return this.userId;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setUserData(T userData) {
        this.userData = userData;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public T getUserData() {
        return this.userData;
    }

}
