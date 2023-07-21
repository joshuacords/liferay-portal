<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<style type="text/css">
	.geomap {
		margin: 10px 0;
	}

	.geomap svg {
		height: 500px !important;
		width: 100%;
	}
</style>

<div class="container-fluid">
	<div class="row">
		<div class="col">
			<chart:area-spline
				config="<%= chartSampleDisplayContext.getAreaSplineChartConfig() %>"
				id="area-spline"
			/>
		</div>

		<div class="col">
			<chart:area-step
				config="<%= chartSampleDisplayContext.getAreaStepChartConfig() %>"
				id="area-step"
			/>
		</div>
	</div>
</div>

<div class="container-fluid">
	<div class="row">
		<div class="col">
			<chart:line
				config="<%= chartSampleDisplayContext.getLineChartConfig() %>"
				id="line"
			/>
		</div>

		<div class="col">
			<chart:scatter
				config="<%= chartSampleDisplayContext.getScatterChartConfig() %>"
				id="scatter"
			/>
		</div>

		<div class="col">
			<chart:spline
				config="<%= chartSampleDisplayContext.getSplineChartConfig() %>"
				id="spline"
			/>
		</div>

		<div class="col">
			<chart:step
				config="<%= chartSampleDisplayContext.getStepChartConfig() %>"
				id="step"
			/>
		</div>
	</div>
</div>

<div class="container-fluid">
	<div class="row">
		<div class="col">
			<chart:bar
				config="<%= chartSampleDisplayContext.getBarChartConfig() %>"
				id="bar"
			/>
		</div>

		<div class="col">
			<chart:combination
				config="<%= chartSampleDisplayContext.getCombinationChartConfig() %>"
				id="combination"
			/>
		</div>
	</div>
</div>

<div class="container-fluid">
	<div class="row">
		<div class="col">
			<chart:donut
				config="<%= chartSampleDisplayContext.getDonutChartConfig() %>"
				id="donut"
			/>
		</div>

		<div class="col">
			<chart:pie
				config="<%= chartSampleDisplayContext.getPieChartConfig() %>"
				id="pie"
			/>
		</div>
	</div>
</div>

<div class="container-fluid">
		<div class="row">
			<div class="col">
				<chart:gauge
					config="<%= chartSampleDisplayContext.getGaugeChartConfig() %>"
					id="gauge"
				/>
			</div>
		</div>
	</div>
</div>

<div class="container-fluid">
	<div class="row">
		<div class="col geomap">
			<chart:geomap
				config="<%= chartSampleDisplayContext.getGeomapConfig1() %>"
				id="geomap-default-colors"
			/>
		</div>

		<div class="col geomap">
			<chart:geomap
				config="<%= chartSampleDisplayContext.getGeomapConfig2() %>"
				id="gemomap-custom-colors"
			/>
		</div>
	</div>
</div>

<div class="container-fluid">
	<div class="row">
		<div class="col polling-interval">
			<chart:line
				componentId="polling-interval-line-chart"
				config="<%= chartSampleDisplayContext.getPollingIntervalLineChartConfig() %>"
				id="polling-interval-line-chart"
			/>
		</div>
	</div>
</div>

<div class="container-fluid">
	<div class="row">
		<div class="col predictive">
			<chart:predictive
				componentId="predictive-chart"
				config="<%= chartSampleDisplayContext.getPredictiveChartConfig() %>"
				id="predictive-chart"
			/>
		</div>
	</div>
</div>

<aui:script>
	Liferay.componentReady('polling-interval-line-chart').then(function(chart) {
		chart.data = function() {
			return Promise.resolve([
				{
					data: [
						Math.random() * 100,
						Math.random() * 100,
						Math.random() * 100
					],
					id: 'data1'
				},
				{
					data: [
						Math.random() * 100,
						Math.random() * 100,
						Math.random() * 100
					],
					id: 'data2'
				}
			]);
		};
	});

	Liferay.componentReady('predictive-chart').then(function(chart) {
		var oldData = chart.data.slice();

		setTimeout(function() {
			var newData = {
				data: [
					[230, 230, 230],
					[20, 20, 20],
					[120, 120, 120],
					[450, 450, 450],
					[70, 70, 70],
					[280, 280, 280],
					[60, 60, 60],
					[140, 140, 140],
					[220, 245, 305],
					[240, 275, 295],
					[200, 235, 325],
					[110, 145, 235]
				],
				id: 'data3'
			};

			chart.data = new Promise(function(resolve, reject) {
				oldData.push(newData);
				resolve(oldData);
			});
		}, 4000);
	});
</aui:script>