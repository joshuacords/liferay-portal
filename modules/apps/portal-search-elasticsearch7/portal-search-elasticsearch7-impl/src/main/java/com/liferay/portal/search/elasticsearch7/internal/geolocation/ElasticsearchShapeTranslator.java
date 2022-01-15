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

import com.liferay.portal.search.geolocation.CircleShape;
import com.liferay.portal.search.geolocation.Coordinate;
import com.liferay.portal.search.geolocation.EnvelopeShape;
import com.liferay.portal.search.geolocation.GeoDistance;
import com.liferay.portal.search.geolocation.GeometryCollectionShape;
import com.liferay.portal.search.geolocation.LineStringShape;
import com.liferay.portal.search.geolocation.MultiLineStringShape;
import com.liferay.portal.search.geolocation.MultiPointShape;
import com.liferay.portal.search.geolocation.MultiPolygonShape;
import com.liferay.portal.search.geolocation.Orientation;
import com.liferay.portal.search.geolocation.PointShape;
import com.liferay.portal.search.geolocation.PolygonShape;
import com.liferay.portal.search.geolocation.Shape;
import com.liferay.portal.search.geolocation.ShapeTranslator;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

//import org.elasticsearch.common.geo.builders.CircleBuilder;
//import org.elasticsearch.common.geo.builders.EnvelopeBuilder;
//import org.elasticsearch.common.geo.builders.GeometryCollectionBuilder;
//import org.elasticsearch.common.geo.builders.LineStringBuilder;
//import org.elasticsearch.common.geo.builders.MultiLineStringBuilder;
//import org.elasticsearch.common.geo.builders.MultiPointBuilder;
//import org.elasticsearch.common.geo.builders.MultiPolygonBuilder;
//import org.elasticsearch.common.geo.builders.PointBuilder;
//import org.elasticsearch.common.geo.builders.PolygonBuilder;
//import org.elasticsearch.common.geo.builders.ShapeBuilder;

import org.elasticsearch.geometry.Circle;
import org.elasticsearch.geometry.Geometry;
import org.elasticsearch.geometry.GeometryCollection;
import org.elasticsearch.geometry.Line;
import org.elasticsearch.geometry.LinearRing;
import org.elasticsearch.geometry.MultiLine;
import org.elasticsearch.geometry.MultiPoint;
import org.elasticsearch.geometry.MultiPolygon;
import org.elasticsearch.geometry.Point;
import org.elasticsearch.geometry.Polygon;
import org.elasticsearch.geometry.Rectangle;

/**
 * @author Michael C. Han
 */
public class ElasticsearchShapeTranslator
	implements ShapeTranslator<Geometry> {

	@Override
	public Circle translate(CircleShape circleShape) {
		GeoDistance radiusGeoDistance = circleShape.getRadius();

		Coordinate center = circleShape.getCenter();

//		DistanceUnit unit = radiusGeoDistance.getDistanceUnit();

		return new Circle(center.getX(), center.getY(), center.getZ(),
			radiusGeoDistance.getDistance());
	}

	@Override
	public Rectangle translate(EnvelopeShape envelopeShape) {
		Coordinate topLeft = envelopeShape.getTopLeft();
		Coordinate bottomRight = envelopeShape.getBottomRight();

		return new Rectangle(
			topLeft.getX(), bottomRight.getX(), topLeft.getY(),
			bottomRight.getY(), topLeft.getZ(), bottomRight.getZ());
	}

	@Override
	public GeometryCollection translate(
		GeometryCollectionShape geometryCollectionShape) {

		List<Shape> shapes = geometryCollectionShape.getShapes();

		List<Geometry> geometries = new ArrayList<Geometry>(shapes.size());

//		for(Shape shape : shapes) {
//			geometries.add(translate(shape));
//		}
//
//		return new GeometryCollection(geometries);

		return new GeometryCollection();
	}

	@Override
	public Line translate(LineStringShape lineStringShape) {
		List<Coordinate> coordinates = lineStringShape.getCoordinates();

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
		MultiLineStringShape multiLineStringShape) {

		List<LineStringShape> lineStringShapes =
			multiLineStringShape.getLineStringShapes();

		List<Line> lines = new ArrayList<Line>();

		for(LineStringShape lineStringShape : lineStringShapes) {
			lines.add(translate(lineStringShape));
		}

		return new MultiLine(lines);
	}

	@Override
	public MultiPoint translate(MultiPointShape multiPointShape) {
		List<Point> points = new ArrayList<Point>();

		for(Coordinate coordinate : multiPointShape.getCoordinates()) {
			points.add(new Point(
				coordinate.getX(), coordinate.getY(),
				coordinate.getZ()));
		}

		return new MultiPoint(points);
	}

	@Override
	public MultiPolygon translate(MultiPolygonShape multiPolygonShape) {
		List<PolygonShape> polygonShapes = multiPolygonShape.getPolygonShapes();
		List<Polygon> polygons = new ArrayList<Polygon>(polygonShapes.size());

		for(PolygonShape polygonShape : polygonShapes) {
			polygons.add(translate(polygonShape));
		}

		return new MultiPolygon(polygons);
	}

	@Override
	public Point translate(PointShape pointShape) {
		List<Coordinate> coordinates = pointShape.getCoordinates();

//		if(coordinates.size() != 1) {
//			throw new Exception();
//		}

		return new Point(
			coordinates.get(0).getX(), coordinates.get(0).getY(),
			coordinates.get(0).getZ());
	}

	private LinearRing translateLinearRing(LineStringShape lineStringShape) {
		List<Coordinate> coordinates = lineStringShape.getCoordinates();

		double[] x = new double[coordinates.size()];
		double[] y = new double[coordinates.size()];
		double[] z = new double[coordinates.size()];

		for(int i = 0; i < coordinates.size(); i++) {
			x[i] = coordinates.get(i).getX();
			y[i] = coordinates.get(i).getY();
			x[i] = coordinates.get(i).getX();	//z is optional, what do we do
		}

		return new LinearRing(x, y, z);
	}

	@Override
	public Polygon translate(PolygonShape polygonShape) {
		LinearRing shell = translateLinearRing(polygonShape.getShell());

		List<LinearRing> holes = new ArrayList<LinearRing>();

		for(LineStringShape lineStringShape: polygonShape.getHoles()) {
			holes.add(translateLinearRing(lineStringShape));
		}

		return new Polygon(shell, holes);
	}

	protected org.locationtech.jts.geom.Coordinate translate(
		Coordinate coordinate) {

		return new org.locationtech.jts.geom.Coordinate(
			coordinate.getX(), coordinate.getY(), coordinate.getZ());
	}

	protected List<org.locationtech.jts.geom.Coordinate> translate(
		List<Coordinate> coordinates) {

		Stream<Coordinate> stream = coordinates.stream();

		return stream.map(
			this::translate
		).collect(
			Collectors.toList()
		);
	}

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

//	protected Geometry translate(Shape shape) {
//		return shape.accept(this);
//	}

}