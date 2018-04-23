DROP PROCEDURE IF EXISTS marlodb.assignBIPermissions;
CREATE PROCEDURE marlodb.`assignBIPermissions` (IN `userID` bigint,type TINYINT(1),urlBI text, userBI text, passwordBI text)
BEGIN

IF (select COUNT('x') from bi_permissions p  where p.userID=`userID` and p.type = type) =0 THEN
    
    insert into bi_permissions (type,user,urlbi,userBi, userPass,is_active) values (type,`userID`,urlBI,userBI,passwordBI,1);

END IF;

END;
