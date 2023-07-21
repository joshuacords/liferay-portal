/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.model;

import com.liferay.portal.kernel.util.Tuple;

import java.io.InputStream;

import java.util.List;
import java.util.Map;

/**
 * @author Brian Wing Shun Chan
 */
public class ModelHintsUtil {

	public static String buildCustomValidatorName(String validatorName) {
		return getModelHints().buildCustomValidatorName(validatorName);
	}

	public static Map<String, String> getDefaultHints(String model) {
		return getModelHints().getDefaultHints(model);
	}

	public static Object getFieldsElement(String model, String field) {
		return getModelHints().getFieldsElement(model, field);
	}

	public static Map<String, String> getHints(String model, String field) {
		return getModelHints().getHints(model, field);
	}

	public static int getMaxLength(String model, String field) {
		return getModelHints().getMaxLength(model, field);
	}

	public static ModelHints getModelHints() {
		return _modelHints;
	}

	public static List<String> getModels() {
		return getModelHints().getModels();
	}

	public static Tuple getSanitizeTuple(String model, String field) {
		return getModelHints().getSanitizeTuple(model, field);
	}

	public static List<Tuple> getSanitizeTuples(String model) {
		return getModelHints().getSanitizeTuples(model);
	}

	public static String getType(String model, String field) {
		return getModelHints().getType(model, field);
	}

	public static List<Tuple> getValidators(String model, String field) {
		return getModelHints().getValidators(model, field);
	}

	public static String getValue(
		String model, String field, String name, String defaultValue) {

		return getModelHints().getValue(model, field, name, defaultValue);
	}

	public static boolean hasField(String model, String field) {
		return getModelHints().hasField(model, field);
	}

	public static boolean isCustomValidator(String validatorName) {
		return getModelHints().isCustomValidator(validatorName);
	}

	public static boolean isLocalized(String model, String field) {
		return getModelHints().isLocalized(model, field);
	}

	public static void read(ClassLoader classLoader, InputStream inputStream)
		throws Exception {

		getModelHints().read(classLoader, inputStream);
	}

	public static void read(ClassLoader classLoader, String source)
		throws Exception {

		getModelHints().read(classLoader, source);
	}

	public static String trimString(String model, String field, String value) {
		return getModelHints().trimString(model, field, value);
	}

	public void setModelHints(ModelHints modelHints) {
		_modelHints = modelHints;
	}

	private static ModelHints _modelHints;

}