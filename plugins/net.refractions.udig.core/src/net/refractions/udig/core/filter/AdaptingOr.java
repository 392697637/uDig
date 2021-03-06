/*
 *    uDig - User Friendly Desktop Internet GIS client
 *    http://udig.refractions.net
 *    (C) 2012, Refractions Research Inc.
 *
 *    This library is free software; you can redistribute it and/or
 *    modify it under the terms of the GNU Lesser General Public
 *    License as published by the Free Software Foundation;
 *    version 2.1 of the License.
 *
 *    This library is distributed in the hope that it will be useful,
 *    but WITHOUT ANY WARRANTY; without even the implied warranty of
 *    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 *    Lesser General Public License for more details.
 */
package net.refractions.udig.core.filter;

import java.util.List;

import org.opengis.filter.Filter;
import org.opengis.filter.Or;

/**
 * AdaptingFilter that implements Or interface.
 * 
 * @author Jody
 * @since 1.1.0
 */
class AdaptingOr extends AdaptingFilter<Or> implements Or {

    AdaptingOr( Or filter ) {
        super(filter);
    }

    public List<Filter> getChildren() {
        return wrapped.getChildren();
    }
    
}
