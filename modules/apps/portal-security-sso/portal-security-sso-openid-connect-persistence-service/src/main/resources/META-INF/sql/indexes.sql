create index IX_C2F20300 on OpenIdConnectSession (configurationPid[$COLUMN_LENGTH:256$]);
create unique index IX_E356F9BA on OpenIdConnectSession (userId, configurationPid[$COLUMN_LENGTH:256$]);