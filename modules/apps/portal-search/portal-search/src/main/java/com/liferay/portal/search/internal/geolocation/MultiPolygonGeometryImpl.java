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

import com.liferay.portal.search.geolocation.Coordinate;
import com.liferay.portal.search.geolocation.GeometryTranslator;
import com.liferay.portal.search.geolocation.MultiPolygonGeometry;
import com.liferay.portal.search.geolocation.MultiPolygonShape;
import com.liferay.portal.search.geolocation.MultiPolygonShapeBuilder;
import com.liferay.portal.search.geolocation.Orientation;
import com.liferay.portal.search.geolocation.PolygonGeometry;
import com.liferay.portal.search.geolocation.PolygonShape;
import com.liferay.portal.search.geolocation.ShapeTranslator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Joshua Cords
 */
public class MultiPolygonGeometryImpl
	extends BaseGeometryImpl implements MultiPolygonGeometry {

	@Override
	public <T> T accept(GeometryTranslator<T> geometryTranslator) {
		return geometryTranslator.translate(this);
	}

	@Override
	public Orientation getOrientation() {
		return _orientation;
	}

	@Override
	public List<PolygonGeometry> getPolygonShapes() {
		return Collections.unmodifiableList(_polygonGeometrys);
	}

//	public static class MultiPolygonShapeBuilderImpl
//		implements MultiPolygonShapeBuilder {
//
//		@Override
//		public MultiPolygonShapeBuilder addCoordinate(Coordinate coordinate) {
//			_multiPolygonShapeImpl.addCoordinate(coordinate);
//
//			return this;
//		}
//
//		@Override
//		public MultiPolygonShapeBuilder addPolygonShape(
//			PolygonShape polygonShape) {
//
//			_multiPolygonShapeImpl._polygonShapes.add(polygonShape);
//
//			return this;
//		}
//
//		@Override
//		public MultiPolygonShape build() {
//			return new MultiPolygonGeometryImpl(_multiPolygonShapeImpl);
//		}
//
//		@Override
//		public MultiPolygonShapeBuilder coordinates(Coordinate... coordinates) {
//			_multiPolygonShapeImpl.setCoordinates(coordinates);
//
//			return this;
//		}
//
//		@Override
//		public MultiPolygonShapeBuilder coordinates(
//			List<Coordinate> coordinates) {
//
//			_multiPolygonShapeImpl.setCoordinates(coordinates);
//
//			return this;
//		}
//
//		@Override
//		public MultiPolygonShapeBuilder orientation(Orientation orientation) {
//			_multiPolygonShapeImpl._orientation = orientation;
//
//			return this;
//		}
//
//		@Override
//		public MultiPolygonShapeBuilder polygonShapes(
//			PolygonShape... polygonShapes) {
//
//			_multiPolygonShapeImpl._polygonShapes.clear();
//
//			Collections.addAll(
//				_multiPolygonShapeImpl._polygonShapes, polygonShapes);
//
//			return this;
//		}
//
//		private final MultiPolygonGeometryImpl _multiPolygonShapeImpl =
//			new MultiPolygonGeometryImpl();
//
//	}

	protected MultiPolygonGeometryImpl() {
	}

	protected MultiPolygonGeometryImpl(
		MultiPolygonGeometryImpl multiPolygonShapeImpl) {

		_orientation = multiPolygonShapeImpl._orientation;

		_polygonGeometrys.addAll(multiPolygonShapeImpl._polygonGeometrys);

		setCoordinates(multiPolygonShapeImpl.getCoordinates());
	}

	private Orientation _orientation;
	private final List<PolygonGeometry> _polygonGeometrys = new ArrayList<>();

}