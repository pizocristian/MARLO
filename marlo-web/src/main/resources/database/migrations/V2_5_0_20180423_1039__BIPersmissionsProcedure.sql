SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Procedure structure for assignBIPermissions
-- ----------------------------
DROP PROCEDURE
IF EXISTS marlodb.`assignBIPermissions`;
DELIMITER ;;

CREATE PROCEDURE marlodb.`assignBIPermissions` (IN `userID` bigint, IN `globalId` bigint,`typeBi` TINYINT(1),`urlBI` varchar(500), `userBI` varchar(500), `passwordBI` varchar(500))
BEGIN

    if (select COUNT('x') from bi_permissions p  where p.user=`userID` and p.type = `typeBi`) =0 THEN
        insert into bi_permissions (`type`,`user`,`urlbi`,`userBi`, `userPass`,`is_active`) values (`typeBi`,`userID`,`urlBI`,`userBI`,`passwordBI`,1);
    END IF;

    IF (typeBi = 1) THEN

      IF(select count(1) from user_roles
         where user_id = `userID`
         and role_id = (select id from roles where roles.acronym = 'BID' and roles.global_unit_id= `globalId`)) = 0 THEN
         
         insert into user_roles (user_id,role_id)values(`userID`,(select id from roles where roles.acronym = 'BID' and roles.global_unit_id= `globalId`)); 
  
      END IF;
  
    END IF;
    
    IF (typeBi = 2) THEN
      IF(select count(1) from user_roles
         where user_id = `userID`
         and role_id = (select id from roles where roles.acronym = 'BIA' and roles.global_unit_id= `globalId`)) = 0 THEN
         
         insert into user_roles (user_id,role_id)values(`userID`,(select id from roles where roles.acronym = 'BIA' and roles.global_unit_id= `globalId`));
     
      END IF; 

    END IF;

END;;
DELIMITER;

