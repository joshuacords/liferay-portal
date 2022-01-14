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
import com.liferay.portal.search.geolocation.Orientation;
import com.liferay.portal.search.geolocation.PolygonGeometry;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Joshua Cords
 */
public class PolygonGeometryImpl extends BaseGeometryImpl implements
	PolygonGeometry {

	@Override
	public <T> T accept(GeometryTranslator<T> geometryTranslator) {
		return geometryTranslator.translate(this);
	}

	@Override
	public List<LineGeometry> getHoles() {
		return Collections.unmodifiableList(_holeLineGeometries);
	}

	@Override
	public Orientation getOrientation() {
		return _orientation;
	}

	@Override
	public LineGeometry getShell() {
		return _shell;
	}

//	public static class PolygonShapeBuilderImpl implements PolygonShapeBuilder {
//
//		@Override
//		public PolygonShapeBuilder addCoordinate(Coordinate coordinate) {
//			_polygonShapeImpl.addCoordinate(coordinate);
//
//			return this;
//		}
//
//		@Override
//		public PolygonShapeBuilder addHole(LineStringShape lineStringShape) {
//			_polygonShapeImpl._holeLineStringGeometrys.add(lineStringShape);
//
//			return this;
//		}
//
//		@Override
//		public PolygonShape build() {
//			return new PolygonGeometryImpl(_polygonShapeImpl);
//		}
//
//		@Override
//		public PolygonShapeBuilder coordinates(Coordinate... coordinates) {
//			_polygonShapeImpl.setCoordinates(coordinates);
//
//			return this;
//		}
//
//		@Override
//		public PolygonShapeBuilder coordinates(List<Coordinate> coordinates) {
//			_polygonShapeImpl.setCoordinates(coordinates);
//
//			return this;
//		}
//
//		@Override
//		public PolygonShapeBuilder holes(LineStringShape... lineStringShapes) {
//			_polygonShapeImpl._holeLineStringGeometrys.clear();
//
//			Collections.addAll(
//				_polygonShapeImpl._holeLineStringGeometrys, lineStringShapes);
//
//			return this;
//		}
//
//		@Override
//		public PolygonShapeBuilder orientation(Orientation orientation) {
//			_polygonShapeImpl._orientation = orientation;
//
//			return this;
//		}
//
//		@Override
//		public PolygonShapeBuilder shell(LineStringShape shell) {
//			_polygonShapeImpl._shell = shell;
//
//			return this;
//		}
//
//		private final PolygonGeometryImpl _polygonShapeImpl =
//			new PolygonGeometryImpl();
//
//	}

	protected PolygonGeometryImpl() {
	}

	protected PolygonGeometryImpl(PolygonGeometryImpl polygonGeometryImpl) {
		_orientation = polygonGeometryImpl._orientation;
		_shell = polygonGeometryImpl._shell;

		_holeLineGeometries.addAll(
			polygonGeometryImpl._holeLineGeometries);

		setCoordinates(polygonGeometryImpl.getCoordinates());
	}

	private final List<LineGeometry> _holeLineGeometries =
		new ArrayList<>();
	private Orientation _orientation;
	private LineGeometry _shell;

}