<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<blockquote>
	<p>Labels are a mechanism to categorize information providing quick recognition.</p>
</blockquote>

<div class="mb-3 row">
	<div class="col-2">
		<div><clay:label label="Label text" style="info" /></div>
		<div><clay:label label="Label text" size="lg" style="info" /></div>
	</div>

	<div class="col-2">
		<div><clay:label label="Status" /></div>
		<div><clay:label label="Status" size="lg" /></div>
	</div>

	<div class="col-2">
		<div><clay:label label="Pending" style="warning" /></div>
		<div><clay:label label="Pending" size="lg" style="warning" /></div>
	</div>

	<div class="col-2">
		<div><clay:label label="Rejected" style="danger" /></div>
		<div><clay:label label="Rejected" size="lg" style="danger" /></div>
	</div>

	<div class="col-2">
		<div><clay:label label="Approved" style="success" /></div>
		<div><clay:label label="Approved" size="lg" style="success" /></div>
	</div>
</div>

<h3>LABEL REMOVABLE</h3>

<div class="mb-3 row">
	<div class="col-12">
		<clay:label
			closeable="<%= true %>"
			label="Normal Label"
		/>

		<clay:label
			closeable="<%= true %>"
			label="Large Label"
			size="lg"
			style="success"
		/>
	</div>
</div>

<h3>LABEL WITH LINK</h3>

<div class="row">
	<div class="col-12">
		<clay:label
			href="#"
			label="Label Text"
		/>
	</div>
</div>