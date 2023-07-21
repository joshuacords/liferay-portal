<%--
/**
 * SPDX-FileCopyrightText: (c) 2000 Liferay, Inc. https://liferay.com
 * SPDX-License-Identifier: LGPL-2.1-or-later OR LicenseRef-Liferay-DXP-EULA-2.0.0-2023-06
 */
--%>

<%@ include file="/init.jsp" %>

<c:choose>
	<c:when test="<%= Validator.isNull(amazonRankingsConfiguration.amazonAccessKeyId()) %>">
		<liferay-ui:message key="please-contact-the-administrator-to-configure-an-amazon-license" />
	</c:when>
	<c:otherwise>
		<table class="lfr-table">

			<%
			Set<AmazonRankings> amazonRankingsSet = new TreeSet<AmazonRankings>();

			for (int i = 0; i < isbns.length; i++) {
				AmazonRankings amazonRankings = AmazonRankingsUtil.getAmazonRankings(amazonRankingsConfiguration, isbns[i]);

				if (amazonRankings != null) {
					amazonRankingsSet.add(amazonRankings);
				}
			}

			int i = 0;

			for (AmazonRankings amazonRankings : amazonRankingsSet) {
			%>

				<tr>
					<td>
						<aui:a href='<%= "http://www.amazon.com/exec/obidos/ASIN/" + amazonRankings.getISBN() %>' target="_blank">
							<img alt="<liferay-ui:message escapeAttribute="<%= true %>" key="amazon-rankings" />" src="<%= amazonRankings.getSmallImageURL() %>" />
						</aui:a>
					</td>
					<td>
						<span style="font-size: xx-small;">
							<liferay-ui:message key="title" />: <%= StringUtil.shorten(amazonRankings.getProductName(), _DESCRIPTION_LENGTH) %><br />
							<liferay-ui:message key="author" />: <%= StringUtil.shorten(StringUtil.merge(amazonRankings.getAuthors(), ", "), _DESCRIPTION_LENGTH) %><br />
							<liferay-ui:message key="publisher" />: <%= StringUtil.shorten(amazonRankings.getManufacturer() + "; (" + amazonRankings.getReleaseDateAsString() + ")", _DESCRIPTION_LENGTH) %><br />
							<liferay-ui:message key="isbn" />: <%= amazonRankings.getISBN() %><br />
							<liferay-ui:message key="rank" />: <%= numberFormat.format(amazonRankings.getSalesRank()) %>
						</span>
					</td>
				</tr>

				<c:if test="<%= i < (amazonRankingsSet.size() - 1) %>">
					<tr>
						<td>
							<br />
						</td>
					</tr>
				</c:if>

			<%
				i++;
			}
			%>

		</table>
	</c:otherwise>
</c:choose>

<%!
private static final int _DESCRIPTION_LENGTH = 16;
%>