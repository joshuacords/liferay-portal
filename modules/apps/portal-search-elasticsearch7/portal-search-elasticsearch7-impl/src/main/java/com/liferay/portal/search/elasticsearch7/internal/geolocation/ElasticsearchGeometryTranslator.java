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

package com.liferay.portal.search.elasticsearch7.internal.geolocation;

import com.liferay.portal.search.geolocation.CircleGeometry;
import com.liferay.portal.search.geolocation.Coordinate;
import com.liferay.portal.search.geolocation.DistanceUnit;
import com.liferay.portal.search.geolocation.GeoDistance;
import com.liferay.portal.search.geolocation.GeometryCollectionGeometry;
import com.liferay.portal.search.geolocation.GeometryTranslator;
//import org.elasticsearch.common.unit.DistanceUnit;
import com.liferay.portal.search.geolocation.LineGeometry;
import com.liferay.portal.search.geolocation.MultiLineStringGeometry;
import com.liferay.portal.search.geolocation.PointGeometry;
import com.liferay.portal.search.geolocation.PolygonGeometry;
import com.liferay.portal.search.geolocation.RectangleGeometry;
import org.elasticsearch.geometry.Circle;
import org.elasticsearch.geometry.Geometry;
import org.elasticsearch.geometry.GeometryCollection;
import org.elasticsearch.geometry.Line;
import org.elasticsearch.geometry.MultiLine;
import org.elasticsearch.geometry.Point;
import org.elasticsearch.geometry.Polygon;
import org.elasticsearch.geometry.Rectangle;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * @author Joshua Cords
 */
public class ElasticsearchGeometryTranslator
	implements GeometryTranslator<Geometry> {

	@Override
	public Circle translate(CircleGeometry circleGeometry) {

		Coordinate center = circleGeometry.getCenter();

		GeoDistance radiusGeoDistance = circleGeometry.getRadius();

		DistanceUnit unit = radiusGeoDistance.getDistanceUnit();

		return new Circle(center.getX(), center.getY(), center.getZ(), radiusGeoDistance.getDistance());
//			unit.toMeters(radiusGeoDistance.getDistance()));
	}

	@Override
	public Rectangle translate(RectangleGeometry rectangleGeometry) {

		Coordinate topLeft = rectangleGeometry.getTopLeft();
		Coordinate bottomRight = rectangleGeometry.getBottomRight();

		return new Rectangle(
			topLeft.getX(), bottomRight.getX(), topLeft.getY(),
			bottomRight.getY(), topLeft.getZ(), bottomRight.getZ());
	}

	@Override
	public GeometryCollection translate(
		GeometryCollectionGeometry geometryCollectionGeometry) {

//		GeometryCollection geometryCollectionBuilder =
//			new GeometryCollection();
//
//		geometryCollectionBuilder.coordinates(
//			translate(geometryCollectionGeometry.getCoordinates()));
//
//		List<Geometry> geometrys = geometryCollectionGeometry.getShapeGeometrys();
//
//		Stream<Geometry> stream = geometrys.stream();
//
//		stream.map(
//			this::translate
//		).forEach(
//			geometryCollectionBuilder::geometry
//		);
//
//		return geometryCollectionBuilder;
		return null;
	}
//
	@Override
	public Line translate(LineGeometry lineGeometry) {

		List<Coordinate> coordinates = lineGeometry.getCoordinates();

		double[] x = new double[coordinates.size()];
		double[] y = new double[coordinates.size()];
		double[] z = new double[coordinates.size()];

		for(int i = 0; i < coordinates.size(); i++) {
			x[i] = coordinates.get(i).getX();
			y[i] = coordinates.get(i).getY();
			x[i] = coordinates.get(i).getX();	//z is optional, what do we do
		}

		return new Line(x, y, z);
	}

	@Override
	public MultiLine translate(
		MultiLineStringGeometry multiLineStringGeometry) {

		List<LineGeometry> lineGeometries =
			multiLineStringGeometry.getLineGeometries();

//		Stream<LineGeometry> stream = lineGeometries.stream();

		List<Line> lines = new ArrayList<Line>();

		for(LineGeometry lineGeometry : lineGeometries) {
			lines.add(translate(lineGeometry));
		}

//		stream.map(
//			this::translate
//		).forEach(
//			lineGeometries::add
//		);

		return new MultiLine(lines);
	}
//
//	@Override
//	public MultiPointBuilder translate(MultiPointGeometry multiPointGeometry) {
//		return new MultiPointBuilder(
//			translate(multiPointGeometry.getCoordinates()));
//	}
//
//	@Override
//	public MultiPolygonBuilder translate(MultiPolygonGeometry multiPolygonGeometry) {
//		MultiPolygonBuilder multiPolygonBuilder = new MultiPolygonBuilder(
//			translate(multiPolygonGeometry.getOrientation()));
//
//		multiPolygonBuilder.coordinates(
//			translate(multiPolygonGeometry.getCoordinates()));
//
//		List<PolygonGeometry> polygonGeometrys = multiPolygonGeometry.getPolygonGeometrys();
//
//		Stream<PolygonGeometry> stream = polygonGeometrys.stream();
//
//		stream.map(
//			this::translate
//		).forEach(
//			multiPolygonBuilder::polygon
//		);
//
//		return multiPolygonBuilder;
//	}
//
	@Override
	public Point translate(PointGeometry pointGeometry) {
		List<Coordinate> coordinates = pointGeometry.getCoordinates();

//		if(coordinates.size() != 1) {
//			throw new Exception();
//		}

		return new Point(
			coordinates.get(0).getX(), coordinates.get(0).getY(),
			coordinates.get(0).getZ());
	}

	@Override
	public Polygon translate(PolygonGeometry polygonGeometry) {
		Line shell = translate(polygonGeometry.getShell());

		List<Line> holes = new ArrayList<>();

		for(LineGeometry lineGeometry: polygonGeometry.getHoles()) {
			holes.add(translate(lineGeometry));
		}

		PolygonBuilder polygonBuilder = new PolygonBuilder(
			translate(polygonGeometry.getShell()),
			translate(polygonGeometry.getOrientation()));

		polygonBuilder.coordinates(translate(polygonGeometry.getCoordinates()));

		List<LineStringGeometry> holesLineStringGeometrys = polygonGeometry.getHoles();

		Stream<LineStringGeometry> stream = holesLineStringGeometrys.stream();

		stream.map(
			this::translate
		).forEach(
			polygonBuilder::hole
		);

		return polygonBuilder;
	}
//
//	protected org.locationtech.jts.geom.Coordinate translate(
//		Coordinate coordinate) {
//
//		return new org.locationtech.jts.geom.Coordinate(
//			coordinate.getX(), coordinate.getY(), coordinate.getZ());
//	}
//
//	protected List<org.locationtech.jts.geom.Coordinate> translate(
//		List<Coordinate> coordinates) {
//
//		Stream<Coordinate> stream = coordinates.stream();
//
//		return stream.map(
//			this::translate
//		).collect(
//			Collectors.toList()
//		);
//	}
//
//	protected org.elasticsearch.common.geo.Orientation translate(
//		Orientation orientation) {
//
//		if (orientation == Orientation.LEFT) {
//			return org.elasticsearch.common.geo.Orientation.LEFT;
//		}
//
//		if (orientation == Orientation.RIGHT) {
//			return org.elasticsearch.common.geo.Orientation.RIGHT;
//		}
//
//		throw new IllegalArgumentException(
//			"Invalid Orientation: " + orientation);
//	}
//
//	protected Geometry<?, ?, ?> translate(Shape shape) {
//		return shape.accept(this);
//	}

}