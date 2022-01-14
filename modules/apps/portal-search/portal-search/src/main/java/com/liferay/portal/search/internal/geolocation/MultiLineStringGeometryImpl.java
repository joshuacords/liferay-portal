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
import com.liferay.portal.search.geolocation.MultiLineStringGeometry;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Joshua Cords
 */
public class MultiLineStringGeometryImpl
	extends BaseGeometryImpl implements MultiLineStringGeometry {

	@Override
	public <T> T accept(GeometryTranslator<T> geometryTranslator) {
		return geometryTranslator.translate(this);
	}

	@Override
	public List<LineGeometry> getLineGeometries() {
		return Collections.unmodifiableList(_lineGeometries);
	}

//	public static class MultiLineStringShapeBuilderImpl
//		implements MultiLineStringShapeBuilder {
//
//		@Override
//		public MultiLineStringShapeBuilder addCoordinate(
//			Coordinate coordinate) {
//
//			_multiLineStringShapeImpl.addCoordinate(coordinate);
//
//			return this;
//		}
//
//		@Override
//		public MultiLineStringShapeBuilder addLineStringShape(
//			LineStringShape lineStringShape) {
//
//			_multiLineStringShapeImpl._lineStringShapes.add(lineStringShape);
//
//			return this;
//		}
//
//		@Override
//		public MultiLineStringShape build() {
//			return new MultiLineStringGeometryImpl(_multiLineStringShapeImpl);
//		}
//
//		@Override
//		public MultiLineStringShapeBuilder coordinates(
//			Coordinate... coordinates) {
//
//			_multiLineStringShapeImpl.setCoordinates(coordinates);
//
//			return this;
//		}
//
//		@Override
//		public MultiLineStringShapeBuilder coordinates(
//			List<Coordinate> coordinates) {
//
//			_multiLineStringShapeImpl.setCoordinates(coordinates);
//
//			return this;
//		}
//
//		@Override
//		public MultiLineStringShapeBuilder lineStringShapes(
//			LineStringShape... lineStringShapes) {
//
//			_multiLineStringShapeImpl._lineStringShapes.clear();
//
//			Collections.addAll(
//				_multiLineStringShapeImpl._lineStringShapes, lineStringShapes);
//
//			return this;
//		}
//
//		@Override
//		public MultiLineStringShapeBuilder lineStringShapes(
//			List<LineStringShape> lineStringShapes) {
//
//			_multiLineStringShapeImpl._lineStringShapes.clear();
//
//			_multiLineStringShapeImpl._lineStringShapes.addAll(
//				lineStringShapes);
//
//			return this;
//		}
//
//		private final MultiLineStringGeometryImpl _multiLineStringShapeImpl =
//			new MultiLineStringGeometryImpl();
//
//	}

	protected MultiLineStringGeometryImpl() {
	}

	protected MultiLineStringGeometryImpl(
		MultiLineStringGeometryImpl multiLineStringGeometryImpl) {

		_lineGeometries.addAll(multiLineStringGeometryImpl._lineGeometries);

		setCoordinates(multiLineStringGeometryImpl.getCoordinates());
	}

	private final List<LineGeometry> _lineGeometries = new ArrayList<>();

}