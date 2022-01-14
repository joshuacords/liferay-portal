/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.portal.search.geolocation;

import org.osgi.annotation.versioning.ProviderType;

/**
 * @author Joshua Cords
 */
@ProviderType
public interface GeometryTranslator<T> {

	public T translate(CircleGeometry circleGeometry);

	public T translate(GeometryCollectionGeometry geometryCollectionGeometry);

	public T translate(LineGeometry lineGeometry);

	public T translate(MultiLineStringGeometry multiLineStringGeometry);

	public T translate(MultiPointGeometry multiPointGeometry);

	public T translate(MultiPolygonGeometry multiPolygonGeometry);

	public T translate(PointGeometry pointGeometry);

	public T translate(PolygonGeometry polygonGeometry);

	public T translate(RectangleGeometry rectangleGeometry);

}