




echo $PATH
OSNAME=`uname -s`
DB_PATH=/tmp/applifire/db/EERXPJTYG0KKIURV6LQYZQ/AE1D8466-F127-46DE-8BE0-0FBF411C1725
ART_CREATE_PATH=/tmp/applifire/db/EERXPJTYG0KKIURV6LQYZQ/AE1D8466-F127-46DE-8BE0-0FBF411C1725/art/create
AST_CREATE_PATH=/tmp/applifire/db/EERXPJTYG0KKIURV6LQYZQ/AE1D8466-F127-46DE-8BE0-0FBF411C1725/ast/create
MYSQL=/usr/bin
APPLFIREUSER=root
APPLFIREPASSWORD=Glass4#21
APPLFIREHOST=localhost

if [ $OSNAME == "Darwin" ]; then
echo "Setting up MYSQL PATH for OS $OSNAME"
MYSQL=/usr/local/mysql/bin/
fi



DB_NAME=apptest
USER=algo
PASSWORD=algo
PORT=3306
HOST=localhost


echo 'grant previliges to user starts....'
$MYSQL/mysql -h$APPLFIREHOST -u$APPLFIREUSER -e "SOURCE $DB_PATH/grant_previliges.sql";
echo 'grant previliges to user ends....'

echo 'drop db starts....'
$MYSQL/mysql -h$HOST -u$USER -p$PASSWORD -e "SOURCE $DB_PATH/drop_db.sql";
echo 'drop db ends....'

echo 'create db starts....'
$MYSQL/mysql -h$APPLFIREHOST -u$APPLFIREUSER -e "SOURCE $DB_PATH/create_db.sql";
echo 'create db ends....'

echo 'create ART table process starts...'
$MYSQL/mysql -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "SOURCE $ART_CREATE_PATH/art_query.sql";

$MYSQL/mysql -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "SOURCE $ART_CREATE_PATH/art_report_ui.sql";

$MYSQL/mysql -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "SOURCE $ART_CREATE_PATH/art_component_config.sql";

$MYSQL/mysql -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "SOURCE $ART_CREATE_PATH/art_log_severity_m.sql";

$MYSQL/mysql -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "SOURCE $ART_CREATE_PATH/art_log_events_t.sql";

$MYSQL/mysql -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "SOURCE $ART_CREATE_PATH/art_chart_json.sql";

$MYSQL/mysql -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "SOURCE $ART_CREATE_PATH/art_chart_category.sql";

$MYSQL/mysql -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "SOURCE $ART_CREATE_PATH/art_chart_type.sql";

$MYSQL/mysql -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "SOURCE $ART_CREATE_PATH/art_chart_data_field_json.sql";

$MYSQL/mysql -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "SOURCE $ART_CREATE_PATH/art_lang_master.sql";

$MYSQL/mysql -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "SOURCE $ART_CREATE_PATH/art_chart_properties.sql";

$MYSQL/mysql -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "SOURCE $ART_CREATE_PATH/art_chart_prop_language.sql";

$MYSQL/mysql -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "SOURCE $ART_CREATE_PATH/art_chart_template.sql";

$MYSQL/mysql -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "SOURCE $ART_CREATE_PATH/art_report_builder_details.sql";

$MYSQL/mysql -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "SOURCE $ART_CREATE_PATH/art_log_config_m.sql";

$MYSQL/mysql -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "SOURCE $ART_CREATE_PATH/art_log_config_attributes.sql";

$MYSQL/mysql -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "SOURCE $ART_CREATE_PATH/art_log_connector_m.sql";

$MYSQL/mysql -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "SOURCE $ART_CREATE_PATH/art_log_connector_attributes_m.sql";

$MYSQL/mysql -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "SOURCE $ART_CREATE_PATH/zen_health.sql";

$MYSQL/mysql -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "SOURCE $ART_CREATE_PATH/zen_health_counter.sql";

$MYSQL/mysql -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "SOURCE $ART_CREATE_PATH/zen_health_gauge.sql";

$MYSQL/mysql -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "SOURCE $ART_CREATE_PATH/zen_health_status.sql";

$MYSQL/mysql -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "SOURCE $ART_CREATE_PATH/zen_request_details.sql";

$MYSQL/mysql -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "SOURCE $ART_CREATE_PATH/art_loginSession.sql";

$MYSQL/mysql -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "SOURCE $ART_CREATE_PATH/art_session_data.sql";

$MYSQL/mysql -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "SOURCE $ART_CREATE_PATH/art_user_last_status.sql";

$MYSQL/mysql -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "SOURCE $ART_CREATE_PATH/art_log_module.sql";

$MYSQL/mysql -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "SOURCE $ART_CREATE_PATH/art_app_alarm.sql";

$MYSQL/mysql -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "SOURCE $ART_CREATE_PATH/art_password_algorithm.sql";

$MYSQL/mysql -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "SOURCE $ART_CREATE_PATH/art_password_policy.sql";

$MYSQL/mysql -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "SOURCE $ART_CREATE_PATH/art_health_scheduler_config_m.sql";

$MYSQL/mysql -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "SOURCE $ART_CREATE_PATH/art_health_status_config_m.sql";

$MYSQL/mysql -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "SOURCE $ART_CREATE_PATH/cloud_drive_file_content_type.sql";

$MYSQL/mysql -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "SOURCE $ART_CREATE_PATH/cloud_drive_tags.sql";

$MYSQL/mysql -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "SOURCE $ART_CREATE_PATH/cloud_drive_files.sql";

$MYSQL/mysql -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "SOURCE $ART_CREATE_PATH/cloud_drive_tags_file.sql";

$MYSQL/mysql -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "SOURCE $ART_CREATE_PATH/cloud_drive_file_versions.sql";

$MYSQL/mysql -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "SOURCE $ART_CREATE_PATH/cloud_drive_tag_shared.sql";

$MYSQL/mysql -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "SOURCE $ART_CREATE_PATH/cloud_drive_file_shared.sql";

$MYSQL/mysql -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "SOURCE $ART_CREATE_PATH/art_external_integration.sql";

$MYSQL/mysql -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "SOURCE $ART_CREATE_PATH/art_job_details.sql";

$MYSQL/mysql -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "SOURCE $ART_CREATE_PATH/art_scheduler_details.sql";

$MYSQL/mysql -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "SOURCE $ART_CREATE_PATH/art_schedule_config.sql";

echo 'create ART table process ends...'

echo 'create AST table process starts...'
$MYSQL/mysql --local-infile=1 -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "SOURCE $AST_CREATE_PATH/ast_Timezone_M.sql"; 

$MYSQL/mysql --local-infile=1 -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "SOURCE $AST_CREATE_PATH/ast_Language_M.sql"; 

$MYSQL/mysql --local-infile=1 -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "SOURCE $AST_CREATE_PATH/ast_Country_M.sql"; 

$MYSQL/mysql --local-infile=1 -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "SOURCE $AST_CREATE_PATH/ast_State_M.sql"; 

$MYSQL/mysql --local-infile=1 -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "SOURCE $AST_CREATE_PATH/ast_City_M.sql"; 

$MYSQL/mysql --local-infile=1 -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "SOURCE $AST_CREATE_PATH/ast_AddressType_M.sql"; 

$MYSQL/mysql --local-infile=1 -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "SOURCE $AST_CREATE_PATH/ast_Address_T.sql"; 

$MYSQL/mysql --local-infile=1 -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "SOURCE $AST_CREATE_PATH/ast_ContactType_M.sql"; 

$MYSQL/mysql --local-infile=1 -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "SOURCE $AST_CREATE_PATH/ast_CommunicationGroup_M.sql"; 

$MYSQL/mysql --local-infile=1 -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "SOURCE $AST_CREATE_PATH/ast_CommunicationType_M.sql"; 

$MYSQL/mysql --local-infile=1 -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "SOURCE $AST_CREATE_PATH/ast_Gender_M.sql"; 

$MYSQL/mysql --local-infile=1 -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "SOURCE $AST_CREATE_PATH/ast_Title_M.sql"; 

$MYSQL/mysql --local-infile=1 -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "SOURCE $AST_CREATE_PATH/ast_CoreContacts_T.sql"; 

$MYSQL/mysql --local-infile=1 -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "SOURCE $AST_CREATE_PATH/ast_AddressMap_B.sql"; 

$MYSQL/mysql --local-infile=1 -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "SOURCE $AST_CREATE_PATH/ast_CommunicationData_TP.sql"; 

$MYSQL/mysql --local-infile=1 -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "SOURCE $AST_CREATE_PATH/ast_CommunicationMap_B.sql"; 

$MYSQL/mysql --local-infile=1 -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "SOURCE $AST_CREATE_PATH/ast_PasswordAlgo_M.sql"; 

$MYSQL/mysql --local-infile=1 -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "SOURCE $AST_CREATE_PATH/ast_PasswordPolicy_M.sql"; 

$MYSQL/mysql --local-infile=1 -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "SOURCE $AST_CREATE_PATH/ast_Question_M.sql"; 

$MYSQL/mysql --local-infile=1 -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "SOURCE $AST_CREATE_PATH/ast_UserAccessLevel_M.sql"; 

$MYSQL/mysql --local-infile=1 -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "SOURCE $AST_CREATE_PATH/ast_UserAccessDomain_M.sql"; 

$MYSQL/mysql --local-infile=1 -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "SOURCE $AST_CREATE_PATH/ast_User_T.sql"; 

$MYSQL/mysql --local-infile=1 -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "SOURCE $AST_CREATE_PATH/ast_Login_T.sql"; 

$MYSQL/mysql --local-infile=1 -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "SOURCE $AST_CREATE_PATH/ast_LoginSession_T.sql"; 

$MYSQL/mysql --local-infile=1 -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "SOURCE $AST_CREATE_PATH/ast_UserAppState_T.sql"; 

$MYSQL/mysql --local-infile=1 -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "SOURCE $AST_CREATE_PATH/ast_PassRecovery_TP.sql"; 

$MYSQL/mysql --local-infile=1 -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "SOURCE $AST_CREATE_PATH/ast_UserData_TP.sql"; 

$MYSQL/mysql --local-infile=1 -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "SOURCE $AST_CREATE_PATH/ast_SessionData_T.sql"; 

$MYSQL/mysql --local-infile=1 -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "SOURCE $AST_CREATE_PATH/ast_Roles_T.sql"; 

$MYSQL/mysql --local-infile=1 -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "SOURCE $AST_CREATE_PATH/ast_AppMenus_M.sql"; 

$MYSQL/mysql --local-infile=1 -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "SOURCE $AST_CREATE_PATH/ast_RoleMenuBridge_TP.sql"; 

$MYSQL/mysql --local-infile=1 -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "SOURCE $AST_CREATE_PATH/ast_UserRoleBridge_T.sql"; 

echo 'create AST table process ends...'

