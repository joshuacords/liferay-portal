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

package com.liferay.portal.search.internal.geolocation;

import com.liferay.portal.search.geolocation.GeometryTranslator;
import com.liferay.portal.search.geolocation.LineGeometry;

/**
 * @author Joshua Cords
 */
public class LineGeometryImpl
	extends BaseGeometryImpl implements LineGeometry {

	@Override
	public <T> T accept(GeometryTranslator<T> geometryTranslator) {
		return geometryTranslator.translate(this);
	}

//	public static class LineStringShapeBuilderImpl
//		implements LineStringShapeBuilder {
//
//		@Override
//		public LineStringShapeBuilder addCoordinate(Coordinate coordinate) {
//			_lineStringShapeImpl.addCoordinate(coordinate);
//
//			return this;
//		}
//
//		@Override
//		public LineStringShape build() {
//			return new LineStringGeometryImpl(_lineStringShapeImpl);
//		}
//
//		@Override
//		public LineStringShapeBuilder coordinates(Coordinate... coordinates) {
//			_lineStringShapeImpl.setCoordinates(coordinates);
//
//			return this;
//		}
//
//		@Override
//		public LineStringShapeBuilder coordinates(
//			List<Coordinate> coordinates) {
//
//			_lineStringShapeImpl.setCoordinates(coordinates);
//
//			return this;
//		}
//
//		private final LineStringGeometryImpl _lineStringShapeImpl =
//			new LineStringGeometryImpl();
//
//	}

	protected LineGeometryImpl() {
	}

	protected LineGeometryImpl(LineGeometryImpl lineStringGeometryImpl) {
		setCoordinates(lineStringGeometryImpl.getCoordinates());
	}

}