/*
CREATED		27.8.2007 A.
MODIFIED		29.8.2007 A.
PROJECT		
MODEL		
COMPANY		
AUTHOR		
VERSION		
DATABASE		ORACLE 10G 
*/

ALTER TABLE "CATEGORIES" DROP CONSTRAINT "USERS_CATEGORIES"
/
ALTER TABLE "COMMENTS" DROP CONSTRAINT "USERS_COMMENTS"
/
ALTER TABLE "PHOTOS" DROP CONSTRAINT "CATEGORIES_PHOTOS"
/
ALTER TABLE "CATEGORIES" DROP CONSTRAINT "SELF"
/
ALTER TABLE "COMMENTS" DROP CONSTRAINT "PHOTOS_COMMENTS"
/

DROP TABLE "COMMENTS"
/
DROP TABLE "PHOTOS"
/
DROP TABLE "CATEGORIES"
/
DROP TABLE "USERS"
/


-- CREATE TYPES SECTION

-- CREATE TABLES SECTION

CREATE TABLE "USERS" (
	"USER_ID" INTEGER NOT NULL ,
	"USERNAME" NVARCHAR2(20) NOT NULL ,
	"FIRST_NAME" NVARCHAR2(20) NOT NULL ,
	"LAST_NAME" NVARCHAR2(20) NOT NULL ,
	"PASSWORD" NVARCHAR2(20) NOT NULL ,
 CONSTRAINT "PK_USERS" PRIMARY KEY ("USER_ID") 
) 
/

CREATE TABLE "CATEGORIES" (
	"CATEGORY_ID" INTEGER NOT NULL ,
	"CHILD_CATEGORY_ID" INTEGER ,
	"USER_ID" INTEGER ,
	"CAT_NAME" NVARCHAR2(20) NOT NULL ,
	"PATH" NVARCHAR2(1000) NOT NULL ,
 CONSTRAINT "PK_CATEGORIES" PRIMARY KEY ("CATEGORY_ID") 
) 
/

CREATE TABLE "PHOTOS" (
	"PHOTO_ID" INTEGER NOT NULL ,
	"PH_NAME" NVARCHAR2(20) NOT NULL ,
	"PATH" NVARCHAR2(1000) NOT NULL ,
	"CATEGORY_ID" INTEGER NOT NULL ,
 CONSTRAINT "PK_PHOTOS" PRIMARY KEY ("PHOTO_ID") 
) 
/

CREATE TABLE "COMMENTS" (
	"COMMENT_ID" INTEGER NOT NULL ,
	"TEXT" NVARCHAR2(200) NOT NULL ,
	"PHOTO_ID" INTEGER NOT NULL ,
	"USER_ID" INTEGER NOT NULL ,
 CONSTRAINT "PK_COMMENTS" PRIMARY KEY ("COMMENT_ID") 
) 
/

-- CREATE ALTERNATE KEYS SECTION

-- CREATE INDEXES SECTION

-- CREATE SECTION FOR PKS, AKS AND UNIQUE CONSTRAINTS, WHICH HAVE TO BE GENERATED AFTER INDEXES

-- CREATE FOREIGN KEYS SECTION

ALTER TABLE "CATEGORIES" ADD CONSTRAINT "USERS_CATEGORIES" FOREIGN KEY ("USER_ID") REFERENCES "USERS" ("USER_ID") 
/

ALTER TABLE "COMMENTS" ADD CONSTRAINT "USERS_COMMENTS" FOREIGN KEY ("USER_ID") REFERENCES "USERS" ("USER_ID") 
/

ALTER TABLE "PHOTOS" ADD CONSTRAINT "CATEGORIES_PHOTOS" FOREIGN KEY ("CATEGORY_ID") REFERENCES "CATEGORIES" ("CATEGORY_ID") 
/

ALTER TABLE "CATEGORIES" ADD CONSTRAINT "SELF" FOREIGN KEY ("CHILD_CATEGORY_ID") REFERENCES "CATEGORIES" ("CATEGORY_ID") 
/

ALTER TABLE "COMMENTS" ADD CONSTRAINT "PHOTOS_COMMENTS" FOREIGN KEY ("PHOTO_ID") REFERENCES "PHOTOS" ("PHOTO_ID") 
/

-- CREATE OBJECT TABLES SECTION

-- CREATE XMLTYPE TABLES SECTION

-- CREATE PROCEDURES SECTION

-- CREATE FUNCTIONS SECTION

-- CREATE VIEWS SECTION

-- CREATE SEQUENCES SECTION

-- CREATE TRIGGERS FROM REFERENTIAL INTEGRITY SECTION

-- CREATE USER TRIGGERS SECTION

-- CREATE PACKAGES SECTION

-- CREATE SYNONYMS SECTION

-- CREATE ROLES SECTION

-- USERS PERMISSIONS TO ROLES SECTION

-- ROLES PERMISSIONS SECTION
/* ROLES PERMISSIONS */

-- USER PERMISSIONS SECTION
/* USERS PERMISSIONS */

-- CREATE TABLE COMMENTS SECTION

-- CREATE ATTRIBUTE COMMENTS SECTION

-- AFTER SECTION


