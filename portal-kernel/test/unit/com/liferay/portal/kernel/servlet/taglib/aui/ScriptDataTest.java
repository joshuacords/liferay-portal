/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */

package com.liferay.portal.kernel.servlet.taglib.aui;

import com.liferay.portal.kernel.io.unsync.UnsyncStringWriter;
import com.liferay.portal.kernel.util.StringBundler;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Preston Crary
 */
public class ScriptDataTest {

	@Test
	public void testGenerateVariableReplacesInvalidFirstCharacter()
		throws Exception {

		ScriptData scriptData = new ScriptData();

		scriptData.append(
			"portletId", "content", "_Var,1Var,*Var,/Var",
			ScriptData.ModulesType.ES6);

		UnsyncStringWriter unsyncStringWriter = new UnsyncStringWriter();

		scriptData.writeTo(unsyncStringWriter);

		assertVariables(unsyncStringWriter, "_Var", "_Var1", "_Var2", "_Var3");
	}

	@Test
	public void testGenerateVariableStripsInvalidCharacters() throws Exception {
		ScriptData scriptData = new ScriptData();

		scriptData.append(
			"portletId", "content", "var,V ar,va*r,var/",
			ScriptData.ModulesType.ES6);

		UnsyncStringWriter unsyncStringWriter = new UnsyncStringWriter();

		scriptData.writeTo(unsyncStringWriter);

		assertVariables(unsyncStringWriter, "var", "vAr", "vaR", "var1");
	}

	@Test
	public void testGenerateVariableStripsLastInvalidCharacter()
		throws Exception {

		ScriptData scriptData = new ScriptData();

		scriptData.append(
			"portletId", "content", "var!", ScriptData.ModulesType.ES6);

		UnsyncStringWriter unsyncStringWriter = new UnsyncStringWriter();

		scriptData.writeTo(unsyncStringWriter);

		assertVariables(unsyncStringWriter, "var");
	}

	protected void assertVariables(
		UnsyncStringWriter unsyncStringWriter, String... variables) {

		StringBundler sb = unsyncStringWriter.getStringBundler();

		Set<String> strings = new HashSet<>(
			Arrays.asList(Arrays.copyOf(sb.getStrings(), sb.index())));

		for (String variable : variables) {
			Assert.assertTrue(
				variable + " was not found", strings.contains(variable));
		}
	}

}