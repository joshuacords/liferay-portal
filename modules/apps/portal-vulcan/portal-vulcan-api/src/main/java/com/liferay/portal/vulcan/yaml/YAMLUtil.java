/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.vulcan.yaml;

import com.liferay.portal.vulcan.yaml.config.ConfigYAML;
import com.liferay.portal.vulcan.yaml.config.Security;
import com.liferay.portal.vulcan.yaml.openapi.Items;
import com.liferay.portal.vulcan.yaml.openapi.OpenAPIYAML;
import com.liferay.portal.vulcan.yaml.openapi.Parameter;
import com.liferay.portal.vulcan.yaml.openapi.PathItem;
import com.liferay.portal.vulcan.yaml.openapi.Schema;

import java.util.List;
import java.util.Map;

import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.TypeDescription;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;
import org.yaml.snakeyaml.introspector.PropertyUtils;
import org.yaml.snakeyaml.representer.Representer;

/**
 * @author     Peter Shin
 * @deprecated As of Athanasius (7.3.x)
 */
@Deprecated
public class YAMLUtil {

	public static ConfigYAML loadConfigYAML(String yamlString) {
		return _YAML_CONFIG.loadAs(yamlString, ConfigYAML.class);
	}

	public static OpenAPIYAML loadOpenAPIYAML(String yamlString) {
		return _YAML_OPEN_API.loadAs(yamlString, OpenAPIYAML.class);
	}

	private static final Yaml _YAML_CONFIG;

	private static final Yaml _YAML_OPEN_API;

	static {
		Representer representer = new Representer(new DumperOptions());

		PropertyUtils propertyUtils = representer.getPropertyUtils();

		propertyUtils.setSkipMissingProperties(true);

		LoaderOptions loaderOptions = new LoaderOptions();

		loaderOptions.setAllowDuplicateKeys(false);

		Constructor configYAMLConstructor = new Constructor(
			ConfigYAML.class, loaderOptions);

		TypeDescription securityTypeDescription = new TypeDescription(
			Security.class);

		securityTypeDescription.substituteProperty(
			"oAuth2", String.class, "getOAuth2", "setOAuth2");

		configYAMLConstructor.addTypeDescription(securityTypeDescription);

		_YAML_CONFIG = new Yaml(configYAMLConstructor, representer);

		Constructor openAPIYAMLConstructor = new Constructor(
			OpenAPIYAML.class, loaderOptions);

		TypeDescription itemsTypeDescription = new TypeDescription(Items.class);

		itemsTypeDescription.substituteProperty(
			"$ref", String.class, "getReference", "setReference");
		itemsTypeDescription.substituteProperty(
			"additionalProperties", Schema.class, "getAdditionalPropertySchema",
			"setAdditionalPropertySchema");
		itemsTypeDescription.substituteProperty(
			"properties", Map.class, "getPropertySchemas",
			"setPropertySchemas");

		itemsTypeDescription.addPropertyParameters(
			"properties", String.class, Schema.class);

		openAPIYAMLConstructor.addTypeDescription(itemsTypeDescription);

		TypeDescription openAPIYAMLTypeDescription = new TypeDescription(
			OpenAPIYAML.class);

		openAPIYAMLTypeDescription.substituteProperty(
			"paths", Map.class, "getPathItems", "setPathItems");

		openAPIYAMLTypeDescription.addPropertyParameters(
			"paths", String.class, PathItem.class);

		openAPIYAMLConstructor.addTypeDescription(openAPIYAMLTypeDescription);

		TypeDescription parameterTypeDescription = new TypeDescription(
			Parameter.class);

		parameterTypeDescription.substituteProperty(
			"$ref", String.class, "getReference", "setReference");

		openAPIYAMLConstructor.addTypeDescription(parameterTypeDescription);

		// Schema

		TypeDescription schemaTypeDescription = new TypeDescription(
			Schema.class);

		schemaTypeDescription.substituteProperty(
			"$ref", String.class, "getReference", "setReference");

		schemaTypeDescription.substituteProperty(
			"additionalProperties", Schema.class, "getAdditionalPropertySchema",
			"setAdditionalPropertySchema");

		schemaTypeDescription.substituteProperty(
			"allOf", List.class, "getAllOfSchemas", "setAllOfSchemas");

		schemaTypeDescription.addPropertyParameters("allOf", Schema.class);

		schemaTypeDescription.substituteProperty(
			"anyOf", List.class, "getAnyOfSchemas", "setAnyOfSchemas");

		schemaTypeDescription.addPropertyParameters("anyOf", Schema.class);

		schemaTypeDescription.substituteProperty(
			"enum", List.class, "getEnumValues", "setEnumValues");

		schemaTypeDescription.addPropertyParameters("enum", String.class);

		schemaTypeDescription.substituteProperty(
			"oneOf", List.class, "getOneOfSchemas", "setOneOfSchemas");

		schemaTypeDescription.addPropertyParameters("oneOf", Schema.class);

		schemaTypeDescription.substituteProperty(
			"properties", Map.class, "getPropertySchemas",
			"setPropertySchemas");

		schemaTypeDescription.addPropertyParameters(
			"properties", String.class, Schema.class);

		schemaTypeDescription.substituteProperty(
			"required", List.class, "getRequiredPropertySchemaNames",
			"setRequiredPropertySchemaNames");

		schemaTypeDescription.addPropertyParameters("required", String.class);

		openAPIYAMLConstructor.addTypeDescription(schemaTypeDescription);

		_YAML_OPEN_API = new Yaml(openAPIYAMLConstructor, representer);
	}

}