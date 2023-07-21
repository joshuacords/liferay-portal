/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.upgrade.step.util;

import com.liferay.portal.kernel.upgrade.UpgradeProcess;
import com.liferay.portal.kernel.upgrade.UpgradeStep;

import java.util.Arrays;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Stream;

/**
 * @author Carlos Sierra Andrés
 */
public class UpgradeStepFactory {

	public static UpgradeStep addColumns(
		Class<?> tableClass, String... columnDefinitions) {

		return new UpgradeProcess() {

			@Override
			protected void doUpgrade() throws Exception {
				alter(
					tableClass,
					_getAlterables(
						AlterTableAddColumn::new, columnDefinitions));
			}

		};
	}

	public static UpgradeStep alterColumnTypes(
		Class<?> tableClass, String newType, String... columnNames) {

		return new UpgradeProcess() {

			@Override
			protected void doUpgrade() throws Exception {
				alter(
					tableClass,
					_getAlterables(AlterColumnType::new, newType, columnNames));
			}

		};
	}

	public static UpgradeStep dropColumns(
		Class<?> tableClass, String... tableNames) {

		return new UpgradeProcess() {

			@Override
			protected void doUpgrade() throws Exception {
				alter(
					tableClass,
					_getAlterables(AlterTableDropColumn::new, tableNames));
			}

		};
	}

	public static UpgradeProcess runSql(String sql) {
		return new UpgradeProcess() {

			@Override
			protected void doUpgrade() throws Exception {
				runSQL(sql);
			}

		};
	}

	private static UpgradeProcess.Alterable[] _getAlterables(
		BiFunction<String, String, UpgradeProcess.Alterable>
			alterableBiFunction,
		String newType, String... columnNames) {

		Stream<String> stream = Arrays.stream(columnNames);

		return stream.map(
			columnName -> alterableBiFunction.apply(columnName, newType)
		).toArray(
			UpgradeProcess.Alterable[]::new
		);
	}

	private static UpgradeProcess.Alterable[] _getAlterables(
		Function<String, UpgradeProcess.Alterable> alterableFunction,
		String... alterableStrings) {

		Stream<String> stream = Arrays.stream(alterableStrings);

		return stream.map(
			alterableFunction
		).toArray(
			UpgradeProcess.Alterable[]::new
		);
	}

}