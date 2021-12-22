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
import com.liferay.portal.search.geolocation.ShapeGeometry;
import com.liferay.portal.search.geolocation.GeometryTranslator;
//import org.elasticsearch.common.unit.DistanceUnit;
import org.elasticsearch.geometry.Circle;
import org.elasticsearch.geometry.Envelope;
import org.elasticsearch.geometry.Geometry;

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

//	@Override
//	public Envelope translate(EnvelopeGeometry envelopeGeometry) {
//		return new EnvelopeBuilder(
//			translate(envelopeShape.getTopLeft()),
//			translate(envelopeShape.getBottomRight())
//		).coordinates(
//			translate(envelopeShape.getCoordinates())
//		);
//
//
//	}
//
//	@Override
//	public GeometryCollectionBuilder translate(
//		GeometryCollectionShape geometryCollectionShape) {
//
//		GeometryCollectionBuilder geometryCollectionBuilder =
//			new GeometryCollectionBuilder();
//
//		geometryCollectionBuilder.coordinates(
//			translate(geometryCollectionShape.getCoordinates()));
//
//		List<Shape> shapes = geometryCollectionShape.getShapes();
//
//		Stream<Shape> stream = shapes.stream();
//
//		stream.map(
//			this::translate
//		).forEach(
//			geometryCollectionBuilder::shape
//		);
//
//		return geometryCollectionBuilder;
//	}
//
//	@Override
//	public LineStringBuilder translate(LineStringShape lineStringShape) {
//		return new LineStringBuilder(
//			translate(lineStringShape.getCoordinates()));
//	}
//
//	@Override
//	public MultiLineStringBuilder translate(
//		MultiLineStringShape multiLineStringShape) {
//
//		MultiLineStringBuilder multiLineStringBuilder =
//			new MultiLineStringBuilder();
//
//		multiLineStringBuilder.coordinates(
//			translate(multiLineStringShape.getCoordinates()));
//
//		List<LineStringShape> lineStringShapes =
//			multiLineStringShape.getLineStringShapes();
//
//		Stream<LineStringShape> stream = lineStringShapes.stream();
//
//		stream.map(
//			this::translate
//		).forEach(
//			multiLineStringBuilder::linestring
//		);
//
//		return multiLineStringBuilder;
//	}
//
//	@Override
//	public MultiPointBuilder translate(MultiPointShape multiPointShape) {
//		return new MultiPointBuilder(
//			translate(multiPointShape.getCoordinates()));
//	}
//
//	@Override
//	public MultiPolygonBuilder translate(MultiPolygonShape multiPolygonShape) {
//		MultiPolygonBuilder multiPolygonBuilder = new MultiPolygonBuilder(
//			translate(multiPolygonShape.getOrientation()));
//
//		multiPolygonBuilder.coordinates(
//			translate(multiPolygonShape.getCoordinates()));
//
//		List<PolygonShape> polygonShapes = multiPolygonShape.getPolygonShapes();
//
//		Stream<PolygonShape> stream = polygonShapes.stream();
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
//	@Override
//	public PointBuilder translate(PointShape pointShape) {
//		List<Coordinate> coordinates = pointShape.getCoordinates();
//
//		PointBuilder pointBuilder = new PointBuilder();
//
//		Stream<Coordinate> stream = coordinates.stream();
//
//		stream.map(
//			this::translate
//		).forEach(
//			pointBuilder::coordinate
//		);
//
//		return pointBuilder;
//	}
//
//	@Override
//	public PolygonBuilder translate(PolygonShape polygonShape) {
//		PolygonBuilder polygonBuilder = new PolygonBuilder(
//			translate(polygonShape.getShell()),
//			translate(polygonShape.getOrientation()));
//
//		polygonBuilder.coordinates(translate(polygonShape.getCoordinates()));
//
//		List<LineStringShape> holesLineStringShapes = polygonShape.getHoles();
//
//		Stream<LineStringShape> stream = holesLineStringShapes.stream();
//
//		stream.map(
//			this::translate
//		).forEach(
//			polygonBuilder::hole
//		);
//
//		return polygonBuilder;
//	}
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
//	protected ShapeBuilder<?, ?, ?> translate(Shape shape) {
//		return shape.accept(this);
//	}

}