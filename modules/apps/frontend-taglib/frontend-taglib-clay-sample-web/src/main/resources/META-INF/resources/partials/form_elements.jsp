<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<h3>CHECKBOX</h3>

<blockquote>
	<p>A checkbox is a component that allows the user selecting something written in its associated text label. A list of consecutive checkboxes would allow the user to select multiple things.</p>
</blockquote>

<table class="table">
	<thead>
		<tr>
			<th>STATE</th>
			<th>DEFINITION</th>
		</tr>
	</thead>

	<tbody>
		<tr>
			<td><clay:checkbox checked="<%= true %>" label="My Input" name="name" showLabel="<%= false %>" /></td>
			<td>On</td>
		</tr>
		<tr>
			<td><clay:checkbox label="My Input" name="name" showLabel="<%= false %>" /></td>
			<td>Off</td>
		</tr>
		<tr>
			<td><clay:checkbox checked="<%= true %>" disabled="<%= true %>" label="My Input" name="name" showLabel="<%= false %>" /></td>
			<td>On disabled</td>
		</tr>
		<tr>
			<td><clay:checkbox disabled="<%= true %>" label="My Input" name="name" showLabel="<%= false %>" /></td>
			<td>Off disabled</td>
		</tr>
		<tr>
			<td><clay:checkbox indeterminate="<%= true %>" label="My Input" name="name" showLabel="<%= false %>" /></td>
			<td>Checkbox Variable for multiple selection</td>
		</tr>
	</tbody>
</table>

<h3>RADIO</h3>

<blockquote>
	<p>A radio button is a component that allows the user selecting something written in its associated text label. A list of consecutive radio buttons would allow the user to select just one thing.</p>
</blockquote>

<table class="table">
	<thead>
		<tr>
			<th>STATE</th>
			<th>DEFINITION</th>
		</tr>
	</thead>

	<tbody>
		<tr>
			<td><clay:radio checked="<%= true %>" label="My Input" name="name" showLabel="<%= false %>" /></td>
			<td>On</td>
		</tr>
		<tr>
			<td><clay:radio label="My Input" name="name" showLabel="<%= false %>" /></td>
			<td>Off</td>
		</tr>
		<tr>
			<td><clay:radio checked="<%= true %>" disabled="<%= true %>" label="My Input" name="name" showLabel="<%= false %>" /></td>
			<td>On disabled</td>
		</tr>
		<tr>
			<td><clay:radio disabled="<%= true %>" label="My Input" name="name" showLabel="<%= false %>" /></td>
			<td>Off disabled</td>
		</tr>
	</tbody>
</table>

<h3>SELECTOR</h3>

<blockquote>
	<p>Selectors are frequently used as a part of forms. This elements are used when we need to select one or more within several options. These options are displayed in the button once selected.</p>
</blockquote>

<%
List<SelectOption> selectOptions = new ArrayList<>();

for (int i = 0; i < 8; i++) {
	selectOptions.add(new SelectOption("Sample " + i, String.valueOf(i)));
}
%>

<clay:select
	label="Regular Select Element"
	name="name"
	options="<%= selectOptions %>"
/>

<clay:select
	disabled="<%= true %>"
	label="Disabled Regular Select Element"
	name="name"
	options="<%= selectOptions %>"
/>

<clay:select
	label="Multiple Select Element"
	multiple="<%= true %>"
	name="name"
	options="<%= selectOptions %>"
/>

<clay:select
	disabled="<%= true %>"
	label="Disabled Multiple Select Element"
	multiple="<%= true %>"
	name="name"
	options="<%= selectOptions %>"
/>