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

import com.liferay.portal.search.geolocation.GeometryCollectionGeometry;
import com.liferay.portal.search.geolocation.GeometryTranslator;
import com.liferay.portal.search.geolocation.Shape;
import com.liferay.portal.search.geolocation.ShapeGeometry;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Joshua Cords
 */
public class GeometryCollectionGeometryImpl
	extends BaseGeometryImpl implements GeometryCollectionGeometry {

	@Override
	public <T> T accept(GeometryTranslator<T> geometryTranslator) {
		return geometryTranslator.translate(this);
	}

	@Override
	public List<ShapeGeometry> getShapeGeometrys() {
		return Collections.unmodifiableList(_shapes);
	}

//	public static class GeometryCollectionShapeBuilderImpl
//		implements GeometryCollectionShapeBuilder {
//
//		@Override
//		public GeometryCollectionShapeBuilder addCoordinate(
//			Coordinate coordinate) {
//
//			_geometryCollectionShapeImpl.addCoordinate(coordinate);
//
//			return this;
//		}
//
//		@Override
//		public GeometryCollectionShapeBuilder addShape(Shape shape) {
//			_geometryCollectionShapeImpl._shapes.add(shape);
//
//			return this;
//		}
//
//		@Override
//		public GeometryCollectionShape build() {
//			return new GeometryCollectionGeometryImpl(
//				_geometryCollectionShapeImpl);
//		}
//
//		@Override
//		public GeometryCollectionShapeBuilder coordinates(
//			Coordinate... coordinates) {
//
//			_geometryCollectionShapeImpl.setCoordinates(coordinates);
//
//			return this;
//		}
//
//		@Override
//		public GeometryCollectionShapeBuilder coordinates(
//			List<Coordinate> coordinates) {
//
//			_geometryCollectionShapeImpl.setCoordinates(coordinates);
//
//			return this;
//		}
//
//		@Override
//		public GeometryCollectionShapeBuilder shapes(Shape... shapes) {
//			_geometryCollectionShapeImpl._shapes.clear();
//
//			Collections.addAll(_geometryCollectionShapeImpl._shapes, shapes);
//
//			return this;
//		}
//
//		private final GeometryCollectionGeometryImpl
//			_geometryCollectionShapeImpl =
//			new GeometryCollectionGeometryImpl();
//
//	}

	protected GeometryCollectionGeometryImpl() {
	}

	protected GeometryCollectionGeometryImpl(
		GeometryCollectionGeometryImpl geometryCollectionGeometryImpl) {

		_shapes.addAll(geometryCollectionGeometryImpl._shapes);

		setCoordinates(geometryCollectionGeometryImpl.getCoordinates());
	}

	private final List<ShapeGeometry> _shapes = new ArrayList<>();

}