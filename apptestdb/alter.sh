




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


$MYSQL/mysql --local-infile=1 -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "SOURCE $AST_CREATE_PATH/alter_ast_Timezone_M.sql"; 

$MYSQL/mysql --local-infile=1 -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "SOURCE $AST_CREATE_PATH/alter_ast_Language_M.sql"; 

$MYSQL/mysql --local-infile=1 -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "SOURCE $AST_CREATE_PATH/alter_ast_Country_M.sql"; 

$MYSQL/mysql --local-infile=1 -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "SOURCE $AST_CREATE_PATH/alter_ast_State_M.sql"; 

$MYSQL/mysql --local-infile=1 -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "SOURCE $AST_CREATE_PATH/alter_ast_City_M.sql"; 

$MYSQL/mysql --local-infile=1 -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "SOURCE $AST_CREATE_PATH/alter_ast_AddressType_M.sql"; 

$MYSQL/mysql --local-infile=1 -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "SOURCE $AST_CREATE_PATH/alter_ast_Address_T.sql"; 

$MYSQL/mysql --local-infile=1 -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "SOURCE $AST_CREATE_PATH/alter_ast_ContactType_M.sql"; 

$MYSQL/mysql --local-infile=1 -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "SOURCE $AST_CREATE_PATH/alter_ast_CommunicationGroup_M.sql"; 

$MYSQL/mysql --local-infile=1 -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "SOURCE $AST_CREATE_PATH/alter_ast_CommunicationType_M.sql"; 

$MYSQL/mysql --local-infile=1 -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "SOURCE $AST_CREATE_PATH/alter_ast_Gender_M.sql"; 

$MYSQL/mysql --local-infile=1 -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "SOURCE $AST_CREATE_PATH/alter_ast_Title_M.sql"; 

$MYSQL/mysql --local-infile=1 -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "SOURCE $AST_CREATE_PATH/alter_ast_CoreContacts_T.sql"; 

$MYSQL/mysql --local-infile=1 -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "SOURCE $AST_CREATE_PATH/alter_ast_AddressMap_B.sql"; 

$MYSQL/mysql --local-infile=1 -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "SOURCE $AST_CREATE_PATH/alter_ast_CommunicationData_TP.sql"; 

$MYSQL/mysql --local-infile=1 -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "SOURCE $AST_CREATE_PATH/alter_ast_CommunicationMap_B.sql"; 

$MYSQL/mysql --local-infile=1 -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "SOURCE $AST_CREATE_PATH/alter_ast_PasswordAlgo_M.sql"; 

$MYSQL/mysql --local-infile=1 -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "SOURCE $AST_CREATE_PATH/alter_ast_PasswordPolicy_M.sql"; 

$MYSQL/mysql --local-infile=1 -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "SOURCE $AST_CREATE_PATH/alter_ast_Question_M.sql"; 

$MYSQL/mysql --local-infile=1 -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "SOURCE $AST_CREATE_PATH/alter_ast_UserAccessLevel_M.sql"; 

$MYSQL/mysql --local-infile=1 -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "SOURCE $AST_CREATE_PATH/alter_ast_UserAccessDomain_M.sql"; 

$MYSQL/mysql --local-infile=1 -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "SOURCE $AST_CREATE_PATH/alter_ast_User_T.sql"; 

$MYSQL/mysql --local-infile=1 -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "SOURCE $AST_CREATE_PATH/alter_ast_Login_T.sql"; 

$MYSQL/mysql --local-infile=1 -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "SOURCE $AST_CREATE_PATH/alter_ast_LoginSession_T.sql"; 

$MYSQL/mysql --local-infile=1 -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "SOURCE $AST_CREATE_PATH/alter_ast_UserAppState_T.sql"; 

$MYSQL/mysql --local-infile=1 -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "SOURCE $AST_CREATE_PATH/alter_ast_PassRecovery_TP.sql"; 

$MYSQL/mysql --local-infile=1 -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "SOURCE $AST_CREATE_PATH/alter_ast_UserData_TP.sql"; 

$MYSQL/mysql --local-infile=1 -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "SOURCE $AST_CREATE_PATH/alter_ast_SessionData_T.sql"; 

$MYSQL/mysql --local-infile=1 -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "SOURCE $AST_CREATE_PATH/alter_ast_Roles_T.sql"; 

$MYSQL/mysql --local-infile=1 -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "SOURCE $AST_CREATE_PATH/alter_ast_AppMenus_M.sql"; 

$MYSQL/mysql --local-infile=1 -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "SOURCE $AST_CREATE_PATH/alter_ast_RoleMenuBridge_TP.sql"; 

$MYSQL/mysql --local-infile=1 -h$HOST -p$PORT -u$USER -p$PASSWORD $DB_NAME -e "SOURCE $AST_CREATE_PATH/alter_ast_UserRoleBridge_T.sql"; 

