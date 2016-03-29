




echo $PATH
OSNAME=`uname -s`
ART_DATA_PATH=/tmp/applifire/db/EERXPJTYG0KKIURV6LQYZQ/AE1D8466-F127-46DE-8BE0-0FBF411C1725/art/data
AST_DATA_PATH=/tmp/applifire/db/EERXPJTYG0KKIURV6LQYZQ/AE1D8466-F127-46DE-8BE0-0FBF411C1725/ast/data
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


echo 'load ART data starts....'
echo 'load AST data ends....'
echo ' load ART data starts....'
$MYSQL/mysql --local-infile=1 -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "load data local infile '$ART_DATA_PATH/art_query.csv' into table art_query FIELDS TERMINATED BY '#appfire#' LINES TERMINATED BY '#appfirenewline#' ";

$MYSQL/mysql --local-infile=1 -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "load data local infile '$ART_DATA_PATH/art_report_ui.csv' into table art_report_ui FIELDS TERMINATED BY '#appfire#' LINES TERMINATED BY '#appfirenewline#' ";

$MYSQL/mysql --local-infile=1 -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "load data local infile '$ART_DATA_PATH/art_component_config.csv' into table art_component_config FIELDS TERMINATED BY '#appfire#' LINES TERMINATED BY '#appfirenewline#' ";

$MYSQL/mysql --local-infile=1 -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "load data local infile '$ART_DATA_PATH/art_log_severity_m.csv' into table art_log_severity_m FIELDS TERMINATED BY '#appfire#' LINES TERMINATED BY '#appfirenewline#' ";

$MYSQL/mysql --local-infile=1 -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "load data local infile '$ART_DATA_PATH/art_log_events_t.csv' into table art_log_events_t FIELDS TERMINATED BY '#appfire#' LINES TERMINATED BY '#appfirenewline#' ";

$MYSQL/mysql --local-infile=1 -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "load data local infile '$ART_DATA_PATH/art_chart_json.csv' into table art_chart_json FIELDS TERMINATED BY '#appfire#' LINES TERMINATED BY '#appfirenewline#' ";

$MYSQL/mysql --local-infile=1 -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "load data local infile '$ART_DATA_PATH/art_chart_category.csv' into table art_chart_category FIELDS TERMINATED BY '#appfire#' LINES TERMINATED BY '#appfirenewline#' ";

$MYSQL/mysql --local-infile=1 -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "load data local infile '$ART_DATA_PATH/art_chart_type.csv' into table art_chart_type FIELDS TERMINATED BY '#appfire#' LINES TERMINATED BY '#appfirenewline#' ";

$MYSQL/mysql --local-infile=1 -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "load data local infile '$ART_DATA_PATH/art_chart_data_field_json.csv' into table art_chart_data_field_json FIELDS TERMINATED BY '#appfire#' LINES TERMINATED BY '#appfirenewline#' ";

$MYSQL/mysql --local-infile=1 -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "load data local infile '$ART_DATA_PATH/art_lang_master.csv' into table art_lang_master FIELDS TERMINATED BY '#appfire#' LINES TERMINATED BY '#appfirenewline#' ";

$MYSQL/mysql --local-infile=1 -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "load data local infile '$ART_DATA_PATH/art_chart_properties.csv' into table art_chart_properties FIELDS TERMINATED BY '#appfire#' LINES TERMINATED BY '#appfirenewline#' ";

$MYSQL/mysql --local-infile=1 -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "load data local infile '$ART_DATA_PATH/art_chart_prop_language.csv' into table art_chart_prop_language FIELDS TERMINATED BY '#appfire#' LINES TERMINATED BY '#appfirenewline#' ";

$MYSQL/mysql --local-infile=1 -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "load data local infile '$ART_DATA_PATH/art_chart_template.csv' into table art_chart_template FIELDS TERMINATED BY '#appfire#' LINES TERMINATED BY '#appfirenewline#' ";

$MYSQL/mysql --local-infile=1 -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "load data local infile '$ART_DATA_PATH/art_report_builder_details.csv' into table art_report_builder_details FIELDS TERMINATED BY '#appfire#' LINES TERMINATED BY '#appfirenewline#' ";

$MYSQL/mysql --local-infile=1 -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "load data local infile '$ART_DATA_PATH/art_log_config_m.csv' into table art_log_config_m FIELDS TERMINATED BY '#appfire#' LINES TERMINATED BY '#appfirenewline#' ";

$MYSQL/mysql --local-infile=1 -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "load data local infile '$ART_DATA_PATH/art_log_config_attributes.csv' into table art_log_config_attributes FIELDS TERMINATED BY '#appfire#' LINES TERMINATED BY '#appfirenewline#' ";

$MYSQL/mysql --local-infile=1 -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "load data local infile '$ART_DATA_PATH/art_log_connector_m.csv' into table art_log_connector_m FIELDS TERMINATED BY '#appfire#' LINES TERMINATED BY '#appfirenewline#' ";

$MYSQL/mysql --local-infile=1 -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "load data local infile '$ART_DATA_PATH/art_log_connector_attributes_m.csv' into table art_log_connector_attributes_m FIELDS TERMINATED BY '#appfire#' LINES TERMINATED BY '#appfirenewline#' ";

$MYSQL/mysql --local-infile=1 -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "load data local infile '$ART_DATA_PATH/art_log_module.csv' into table art_log_module FIELDS TERMINATED BY '#appfire#' LINES TERMINATED BY '#appfirenewline#' ";

$MYSQL/mysql --local-infile=1 -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "load data local infile '$ART_DATA_PATH/art_app_alarm.csv' into table art_app_alarm FIELDS TERMINATED BY '#appfire#' LINES TERMINATED BY '#appfirenewline#' ";

$MYSQL/mysql --local-infile=1 -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "load data local infile '$ART_DATA_PATH/art_password_algorithm.csv' into table art_password_algorithm FIELDS TERMINATED BY '#appfire#' LINES TERMINATED BY '#appfirenewline#' ";

$MYSQL/mysql --local-infile=1 -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "load data local infile '$ART_DATA_PATH/art_password_policy.csv' into table art_password_policy FIELDS TERMINATED BY '#appfire#' LINES TERMINATED BY '#appfirenewline#' ";

$MYSQL/mysql --local-infile=1 -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "load data local infile '$ART_DATA_PATH/art_health_scheduler_config_m.csv' into table art_health_scheduler_config_m FIELDS TERMINATED BY '#appfire#' LINES TERMINATED BY '#appfirenewline#' ";

$MYSQL/mysql --local-infile=1 -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "load data local infile '$ART_DATA_PATH/art_health_status_config_m.csv' into table art_health_status_config_m FIELDS TERMINATED BY '#appfire#' LINES TERMINATED BY '#appfirenewline#' ";

$MYSQL/mysql --local-infile=1 -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "load data local infile '$ART_DATA_PATH/cloud_drive_file_content_type.csv' into table cloud_drive_file_content_type FIELDS TERMINATED BY '#appfire#' LINES TERMINATED BY '#appfirenewline#' ";

$MYSQL/mysql --local-infile=1 -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "load data local infile '$ART_DATA_PATH/cloud_drive_tags.csv' into table cloud_drive_tags FIELDS TERMINATED BY '#appfire#' LINES TERMINATED BY '#appfirenewline#' ";

$MYSQL/mysql --local-infile=1 -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "load data local infile '$ART_DATA_PATH/art_external_integration.csv' into table art_external_integration FIELDS TERMINATED BY '#appfire#' LINES TERMINATED BY '#appfirenewline#' ";

$MYSQL/mysql --local-infile=1 -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "load data local infile '$ART_DATA_PATH/art_job_details.csv' into table art_job_details FIELDS TERMINATED BY '#appfire#' LINES TERMINATED BY '#appfirenewline#' ";

$MYSQL/mysql --local-infile=1 -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "load data local infile '$ART_DATA_PATH/art_scheduler_details.csv' into table art_scheduler_details FIELDS TERMINATED BY '#appfire#' LINES TERMINATED BY '#appfirenewline#' ";

$MYSQL/mysql --local-infile=1 -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "load data local infile '$ART_DATA_PATH/art_schedule_config.csv' into table art_schedule_config FIELDS TERMINATED BY '#appfire#' LINES TERMINATED BY '#appfirenewline#' ";

echo 'load ART data ends....'

echo 'load AST data starts....'
$MYSQL/mysql --local-infile=1 -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "load data local infile '$AST_DATA_PATH/Timezone.csv' into table ast_Timezone_M FIELDS TERMINATED BY '#appfire#' LINES TERMINATED BY '\n' ";

$MYSQL/mysql --local-infile=1 -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "load data local infile '$AST_DATA_PATH/Language.csv' into table ast_Language_M FIELDS TERMINATED BY '#appfire#' LINES TERMINATED BY '\n' ";

$MYSQL/mysql --local-infile=1 -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "load data local infile '$AST_DATA_PATH/Country.csv' into table ast_Country_M FIELDS TERMINATED BY '#appfire#' LINES TERMINATED BY '\n' ";

$MYSQL/mysql --local-infile=1 -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "load data local infile '$AST_DATA_PATH/State.csv' into table ast_State_M FIELDS TERMINATED BY '#appfire#' LINES TERMINATED BY '\n' ";

$MYSQL/mysql --local-infile=1 -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "load data local infile '$AST_DATA_PATH/City.csv' into table ast_City_M FIELDS TERMINATED BY '#appfire#' LINES TERMINATED BY '\n' ";

$MYSQL/mysql --local-infile=1 -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "load data local infile '$AST_DATA_PATH/AddressType.csv' into table ast_AddressType_M FIELDS TERMINATED BY '#appfire#' LINES TERMINATED BY '\n' ";

$MYSQL/mysql --local-infile=1 -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "load data local infile '$AST_DATA_PATH/Address.csv' into table ast_Address_T FIELDS TERMINATED BY '#appfire#' LINES TERMINATED BY '\n' ";

$MYSQL/mysql --local-infile=1 -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "load data local infile '$AST_DATA_PATH/ContactType.csv' into table ast_ContactType_M FIELDS TERMINATED BY '#appfire#' LINES TERMINATED BY '\n' ";

$MYSQL/mysql --local-infile=1 -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "load data local infile '$AST_DATA_PATH/CommunicationGroup.csv' into table ast_CommunicationGroup_M FIELDS TERMINATED BY '#appfire#' LINES TERMINATED BY '\n' ";

$MYSQL/mysql --local-infile=1 -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "load data local infile '$AST_DATA_PATH/CommunicationType.csv' into table ast_CommunicationType_M FIELDS TERMINATED BY '#appfire#' LINES TERMINATED BY '\n' ";

$MYSQL/mysql --local-infile=1 -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "load data local infile '$AST_DATA_PATH/Gender.csv' into table ast_Gender_M FIELDS TERMINATED BY '#appfire#' LINES TERMINATED BY '\n' ";

$MYSQL/mysql --local-infile=1 -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "load data local infile '$AST_DATA_PATH/Title.csv' into table ast_Title_M FIELDS TERMINATED BY '#appfire#' LINES TERMINATED BY '\n' ";

$MYSQL/mysql --local-infile=1 -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "load data local infile '$AST_DATA_PATH/CoreContacts.csv' into table ast_CoreContacts_T FIELDS TERMINATED BY '#appfire#' LINES TERMINATED BY '\n' ";

$MYSQL/mysql --local-infile=1 -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "load data local infile '$AST_DATA_PATH/AddressMap.csv' into table ast_AddressMap_B FIELDS TERMINATED BY '#appfire#' LINES TERMINATED BY '\n' ";

$MYSQL/mysql --local-infile=1 -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "load data local infile '$AST_DATA_PATH/CommunicationData.csv' into table ast_CommunicationData_TP FIELDS TERMINATED BY '#appfire#' LINES TERMINATED BY '\n' ";

$MYSQL/mysql --local-infile=1 -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "load data local infile '$AST_DATA_PATH/CommunicationMap.csv' into table ast_CommunicationMap_B FIELDS TERMINATED BY '#appfire#' LINES TERMINATED BY '\n' ";

$MYSQL/mysql --local-infile=1 -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "load data local infile '$AST_DATA_PATH/UserAccessLevel.csv' into table ast_UserAccessLevel_M FIELDS TERMINATED BY '#appfire#' LINES TERMINATED BY '\n' ";

$MYSQL/mysql --local-infile=1 -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "load data local infile '$AST_DATA_PATH/UserAccessDomain.csv' into table ast_UserAccessDomain_M FIELDS TERMINATED BY '#appfire#' LINES TERMINATED BY '\n' ";

$MYSQL/mysql --local-infile=1 -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "load data local infile '$AST_DATA_PATH/User.csv' into table ast_User_T FIELDS TERMINATED BY '#appfire#' LINES TERMINATED BY '\n' ";

$MYSQL/mysql --local-infile=1 -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "load data local infile '$AST_DATA_PATH/Login.csv' into table ast_Login_T FIELDS TERMINATED BY '#appfire#' LINES TERMINATED BY '\n' ";

$MYSQL/mysql --local-infile=1 -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "load data local infile '$AST_DATA_PATH/LoginSession.csv' into table ast_LoginSession_T FIELDS TERMINATED BY '#appfire#' LINES TERMINATED BY '\n' ";

$MYSQL/mysql --local-infile=1 -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "load data local infile '$AST_DATA_PATH/UserAppState.csv' into table ast_UserAppState_T FIELDS TERMINATED BY '#appfire#' LINES TERMINATED BY '\n' ";

$MYSQL/mysql --local-infile=1 -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "load data local infile '$AST_DATA_PATH/PassRecovery.csv' into table ast_PassRecovery_TP FIELDS TERMINATED BY '#appfire#' LINES TERMINATED BY '\n' ";

$MYSQL/mysql --local-infile=1 -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "load data local infile '$AST_DATA_PATH/UserData.csv' into table ast_UserData_TP FIELDS TERMINATED BY '#appfire#' LINES TERMINATED BY '\n' ";

$MYSQL/mysql --local-infile=1 -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "load data local infile '$AST_DATA_PATH/SessionData.csv' into table ast_SessionData_T FIELDS TERMINATED BY '#appfire#' LINES TERMINATED BY '\n' ";

$MYSQL/mysql --local-infile=1 -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "load data local infile '$AST_DATA_PATH/Roles.csv' into table ast_Roles_T FIELDS TERMINATED BY '#appfire#' LINES TERMINATED BY '\n' ";

$MYSQL/mysql --local-infile=1 -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "load data local infile '$AST_DATA_PATH/UserRoleBridge.csv' into table ast_UserRoleBridge_T FIELDS TERMINATED BY '#appfire#' LINES TERMINATED BY '\n' ";

echo 'load AST data ends....'

echo 'load AST data starts....'
$MYSQL/mysql --local-infile=1 -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "load data local infile '$AST_DATA_PATH/ast_AppMenus_M.csv' into table ast_AppMenus_M FIELDS TERMINATED BY '#appfire#' LINES TERMINATED BY '\n' (menuId,menuTreeId,menuIcon,menuAction,menuCommands,menuDisplay,menuHead,menuLabel,uiType,RefObjectId,menuAccessRights,appType,appId,autoSave,updatedBy,updatedDate,createdBy,createdDate,versionId,activeStatus,txnAccessCode,autoSave)";

$MYSQL/mysql --local-infile=1 -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "load data local infile '$AST_DATA_PATH/ast_RoleMenuBridge_TP.csv' into table ast_RoleMenuBridge_TP FIELDS TERMINATED BY '#appfire#' LINES TERMINATED BY '\n' (roleMenuMapId,roleId,menuId,isRead,isWrite,isExecute,updatedBy,updatedDate,createdBy,createdDate,versionId,activeStatus,txnAccessCode)";

$MYSQL/mysql --local-infile=1 -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "load data local infile '$AST_DATA_PATH/ast_PasswordPolicy_M.csv' into table ast_PasswordPolicy_M FIELDS TERMINATED BY '#appfire#' LINES TERMINATED BY '\n' (policyId, policyName, policyDescription, maxPwdLength,  minPwdLength, minCapitalLetters, minSmallLetters, minNumericValues, minSpecialLetters, allowedSpecialLetters, createdBy, createdDate,  updatedBy, updatedDate, versionId, activeStatus, txnAccessCode)";

$MYSQL/mysql --local-infile=1 -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "load data local infile '$AST_DATA_PATH/ast_Question_M.csv' into table ast_Question_M FIELDS TERMINATED BY '#appfire#' LINES TERMINATED BY '\n' (questionId, levelid, question, questionDetails, questionIcon,createdBy, createdDate, updatedBy, updatedDate, versionId, activeStatus,txnAccessCode)";

$MYSQL/mysql --local-infile=1 -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "load data local infile '$AST_DATA_PATH/ast_PasswordAlgo_M.csv' into table ast_PasswordAlgo_M FIELDS TERMINATED BY '#appfire#' LINES TERMINATED BY '\n' (algoId, algorithm, algoName, algoDescription, algoIcon, algoHelp, createdBy, createdDate, updatedBy, updatedDate, versionId, activeStatus,txnAccessCode)";

echo 'load AST data ends....'
