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

import com.liferay.portal.search.geolocation.CircleGeometry;
import com.liferay.portal.search.geolocation.CircleShape;
import com.liferay.portal.search.geolocation.CircleShapeBuilder;
import com.liferay.portal.search.geolocation.Coordinate;
import com.liferay.portal.search.geolocation.GeoDistance;
import com.liferay.portal.search.geolocation.GeometryTranslator;
import com.liferay.portal.search.geolocation.ShapeTranslator;

import java.util.List;

/**
 * @author Joshua Cords
 */
public class CircleGeometryImpl extends BaseGeometryImpl implements
	CircleGeometry {

	@Override
	public <T> T accept(GeometryTranslator<T> geometryTranslator) {
		return geometryTranslator.translate(this);
	}

	@Override
	public Coordinate getCenter() {
		return _centerCoordinate;
	}

	@Override
	public GeoDistance getRadius() {
		return _radiusGeoDistance;
	}

//	public static class CircleShapeBuilderImpl implements CircleShapeBuilder {
//
//		@Override
//		public CircleShapeBuilder addCoordinate(Coordinate coordinate) {
//			_circleShapeImpl.addCoordinate(coordinate);
//
//			return this;
//		}
//
//		@Override
//		public CircleShape build() {
//			return new CircleGeometryImpl(_circleShapeImpl);
//		}
//
//		@Override
//		public CircleShapeBuilder center(Coordinate coordinate) {
//			_circleShapeImpl._centerCoordinate = coordinate;
//
//			return this;
//		}
//
//		@Override
//		public CircleShapeBuilder coordinates(Coordinate... coordinates) {
//			_circleShapeImpl.setCoordinates(coordinates);
//
//			return this;
//		}
//
//		@Override
//		public CircleShapeBuilder coordinates(List<Coordinate> coordinates) {
//			_circleShapeImpl.setCoordinates(coordinates);
//
//			return this;
//		}
//
//		@Override
//		public CircleShapeBuilder radius(GeoDistance geoDistance) {
//			_circleShapeImpl._radiusGeoDistance = geoDistance;
//
//			return this;
//		}
//
//		private final CircleGeometryImpl
//			_circleShapeImpl = new CircleGeometryImpl();
//
//	}

	protected CircleGeometryImpl() {
	}

	protected CircleGeometryImpl(CircleGeometryImpl circleGeometryImpl) {
		_centerCoordinate = circleGeometryImpl._centerCoordinate;
		_radiusGeoDistance = circleGeometryImpl._radiusGeoDistance;

		setCoordinates(circleGeometryImpl.getCoordinates());
	}

	private Coordinate _centerCoordinate;
	private GeoDistance _radiusGeoDistance;

}