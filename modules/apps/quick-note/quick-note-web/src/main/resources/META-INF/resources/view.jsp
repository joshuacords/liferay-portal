<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<div id="<portlet:namespace />pad" style="background: <%= HtmlUtil.escapeAttribute(color) %>;">
	<c:if test="<%= portletDisplay.isShowConfigurationIcon() %>">
		<table width="100%">
			<tr>
				<td width="60%">
					<div class="portlet-title-default">&nbsp;</div>
				</td>
				<td>
					<c:if test="<%= portletDisplay.isShowCloseIcon() %>">
						<liferay-ui:icon
							cssClass="close-note"
							iconCssClass="icon-remove"
							message="close"
							url="<%= portletDisplay.getURLClose() %>"
						/>
					</c:if>

					<span class="note-color yellow"></span>
					<span class="green note-color"></span>
					<span class="blue note-color"></span>
					<span class="note-color red"></span>
				</td>
			</tr>
		</table>
	</c:if>

	<div class="note-content" id="<portlet:namespace />note"><%= StringUtil.replace(HtmlUtil.escape(data), "&lt;br /&gt;", "<br />") %></div>
</div>

<c:if test="<%= portletDisplay.isShowConfigurationIcon() %>">
	<aui:script use="aui-editable-deprecated,aui-io-request">
		var quickNotePad = A.one('#<portlet:namespace />pad');

		if (quickNotePad) {
			quickNotePad.all('.note-color').on('click', function(event) {
				var box = event.currentTarget;

				var bgColor = box.getStyle('backgroundColor');

				quickNotePad.setStyle('backgroundColor', bgColor);

				<portlet:actionURL name="save" var="saveURL" />

				A.io.request('<%= saveURL %>', {
					data: {
						<portlet:namespace />color: bgColor
					}
				});
			});
		}

		new A.Editable({
			inputType: 'textarea',
			node: '#<portlet:namespace />note',
			on: {
				contentTextChange: function(event) {
					var instance = this;

					if (!event.initial) {
						var newValue = event.newVal.replace(/\n/gi, '<br />');

						event.newVal = instance._toText(event.newVal);

						A.io.request('<%= saveURL %>', {
							data: {
								<portlet:namespace />data: newValue
							}
						});
					}
				}
			}
		});
	</aui:script>
</c:if>