/*
Navicat PGSQL Data Transfer

Source Server         : localhost_5432
Source Server Version : 90405
Source Host           : localhost:5432
Source Database       : azure
Source Schema         : public

Target Server Type    : PGSQL
Target Server Version : 90405
File Encoding         : 65001

Date: 2015-11-28 02:22:54
*/


-- ----------------------------
-- Sequence structure for achievements_id_seq
-- ----------------------------
DROP SEQUENCE "public"."achievements_id_seq";
CREATE SEQUENCE "public"."achievements_id_seq"
 INCREMENT 1
 MINVALUE 1
 MAXVALUE 9223372036854775807
 START 1
 CACHE 1;

-- ----------------------------
-- Sequence structure for avatars_id_seq
-- ----------------------------
DROP SEQUENCE "public"."avatars_id_seq";
CREATE SEQUENCE "public"."avatars_id_seq"
 INCREMENT 1
 MINVALUE 1
 MAXVALUE 9223372036854775807
 START 1
 CACHE 1;

-- ----------------------------
-- Sequence structure for avatars_ranks_id_seq
-- ----------------------------
DROP SEQUENCE "public"."avatars_ranks_id_seq";
CREATE SEQUENCE "public"."avatars_ranks_id_seq"
 INCREMENT 1
 MINVALUE 1
 MAXVALUE 9223372036854775807
 START 1
 CACHE 1;

-- ----------------------------
-- Sequence structure for badges_id_seq
-- ----------------------------
DROP SEQUENCE "public"."badges_id_seq";
CREATE SEQUENCE "public"."badges_id_seq"
 INCREMENT 1
 MINVALUE 1
 MAXVALUE 9223372036854775807
 START 1
 CACHE 1;

-- ----------------------------
-- Sequence structure for ranks_id_seq
-- ----------------------------
DROP SEQUENCE "public"."ranks_id_seq";
CREATE SEQUENCE "public"."ranks_id_seq"
 INCREMENT 1
 MINVALUE 1
 MAXVALUE 9223372036854775807
 START 1
 CACHE 1;

-- ----------------------------
-- Sequence structure for room_categories_id_seq
-- ----------------------------
DROP SEQUENCE "public"."room_categories_id_seq";
CREATE SEQUENCE "public"."room_categories_id_seq"
 INCREMENT 1
 MINVALUE 1
 MAXVALUE 9223372036854775807
 START 1
 CACHE 1;

-- ----------------------------
-- Sequence structure for rooms_id_seq
-- ----------------------------
DROP SEQUENCE "public"."rooms_id_seq";
CREATE SEQUENCE "public"."rooms_id_seq"
 INCREMENT 1
 MINVALUE 1
 MAXVALUE 9223372036854775807
 START 1
 CACHE 1;

-- ----------------------------
-- Sequence structure for users_id_seq
-- ----------------------------
DROP SEQUENCE "public"."users_id_seq";
CREATE SEQUENCE "public"."users_id_seq"
 INCREMENT 1
 MINVALUE 1
 MAXVALUE 9223372036854775807
 START 1
 CACHE 1;

-- ----------------------------
-- Table structure for achievements
-- ----------------------------
DROP TABLE IF EXISTS "public"."achievements";
CREATE TABLE "public"."achievements" (
"id" int4 DEFAULT nextval('achievements_id_seq'::regclass) NOT NULL,
"type" varchar(255) COLLATE "default" NOT NULL,
"max_level" int2 NOT NULL,
"badge_id" int4 NOT NULL
)
WITH (OIDS=FALSE)

;

-- ----------------------------
-- Records of achievements
-- ----------------------------

-- ----------------------------
-- Table structure for avatars
-- ----------------------------
DROP TABLE IF EXISTS "public"."avatars";
CREATE TABLE "public"."avatars" (
"id" int4 DEFAULT nextval('avatars_id_seq'::regclass) NOT NULL,
"user_id" int4 NOT NULL,
"username" varchar(255) COLLATE "default" NOT NULL,
"password" varchar(255) COLLATE "default" NOT NULL,
"figure" varchar(255) COLLATE "default" DEFAULT 'hr-115-42.hd-190-1.ch-215-62.lg-285-91.sh-290-62'::character varying NOT NULL,
"gender" varchar(1) COLLATE "default" DEFAULT 'M'::character varying NOT NULL,
"motto" varchar(255) COLLATE "default" NOT NULL,
"respect" int4 DEFAULT 0 NOT NULL
)
WITH (OIDS=FALSE)

;

-- ----------------------------
-- Records of avatars
-- ----------------------------

-- ----------------------------
-- Table structure for avatars_achievements
-- ----------------------------
DROP TABLE IF EXISTS "public"."avatars_achievements";
CREATE TABLE "public"."avatars_achievements" (
"avatar_id" int4 NOT NULL,
"ach_id" int4 NOT NULL,
"ach_level" int4
)
WITH (OIDS=FALSE)

;

-- ----------------------------
-- Records of avatars_achievements
-- ----------------------------

-- ----------------------------
-- Table structure for avatars_badges
-- ----------------------------
DROP TABLE IF EXISTS "public"."avatars_badges";
CREATE TABLE "public"."avatars_badges" (
"avatar_id" int4 NOT NULL,
"badge_id" int4 NOT NULL
)
WITH (OIDS=FALSE)

;

-- ----------------------------
-- Records of avatars_badges
-- ----------------------------

-- ----------------------------
-- Table structure for avatars_ranks
-- ----------------------------
DROP TABLE IF EXISTS "public"."avatars_ranks";
CREATE TABLE "public"."avatars_ranks" (
"id" int4 DEFAULT nextval('avatars_ranks_id_seq'::regclass) NOT NULL,
"avatar_id" int4 NOT NULL,
"rank_id" int4 NOT NULL
)
WITH (OIDS=FALSE)

;

-- ----------------------------
-- Records of avatars_ranks
-- ----------------------------

-- ----------------------------
-- Table structure for avatars_wallet
-- ----------------------------
DROP TABLE IF EXISTS "public"."avatars_wallet";
CREATE TABLE "public"."avatars_wallet" (
"avatar_id" int4 NOT NULL,
"credits" int4 DEFAULT 0 NOT NULL,
"diamonds" int4 DEFAULT 0 NOT NULL,
"achievement_points" int4 DEFAULT 0 NOT NULL
)
WITH (OIDS=FALSE)

;

-- ----------------------------
-- Records of avatars_wallet
-- ----------------------------

-- ----------------------------
-- Table structure for badges
-- ----------------------------
DROP TABLE IF EXISTS "public"."badges";
CREATE TABLE "public"."badges" (
"id" int4 DEFAULT nextval('badges_id_seq'::regclass) NOT NULL,
"badge_name" varchar(255) COLLATE "default" NOT NULL
)
WITH (OIDS=FALSE)

;

-- ----------------------------
-- Records of badges
-- ----------------------------

-- ----------------------------
-- Table structure for ranks
-- ----------------------------
DROP TABLE IF EXISTS "public"."ranks";
CREATE TABLE "public"."ranks" (
"id" int4 DEFAULT nextval('ranks_id_seq'::regclass) NOT NULL,
"rank_name" varchar(255) COLLATE "default",
"rank_value" varchar(255) COLLATE "default"
)
WITH (OIDS=FALSE)

;

-- ----------------------------
-- Records of ranks
-- ----------------------------

-- ----------------------------
-- Table structure for room_categories
-- ----------------------------
DROP TABLE IF EXISTS "public"."room_categories";
CREATE TABLE "public"."room_categories" (
"id" int4 DEFAULT nextval('room_categories_id_seq'::regclass) NOT NULL,
"name" varchar(255) COLLATE "default" NOT NULL,
"min_rank" int4 DEFAULT 0 NOT NULL
)
WITH (OIDS=FALSE)

;

-- ----------------------------
-- Records of room_categories
-- ----------------------------

-- ----------------------------
-- Table structure for rooms
-- ----------------------------
DROP TABLE IF EXISTS "public"."rooms";
CREATE TABLE "public"."rooms" (
"id" int4 DEFAULT nextval('rooms_id_seq'::regclass) NOT NULL,
"type" varchar(255) COLLATE "default" DEFAULT 'private'::character varying NOT NULL,
"owner" int4 NOT NULL,
"name" varchar(255) COLLATE "default",
"description" varchar(255) COLLATE "default",
"model" varchar(255) COLLATE "default" NOT NULL,
"category_id" int4 NOT NULL,
"accessibility" varchar(255) COLLATE "default" DEFAULT 'open'::character varying NOT NULL,
"trade_enabled" bool NOT NULL,
"curr_users" int4 DEFAULT 0 NOT NULL,
"max_users" int4 DEFAULT 25 NOT NULL,
"score" int4 DEFAULT 0 NOT NULL,
"icon_bg" int4 NOT NULL,
"icon_fg" int4 NOT NULL,
"icon_items" varchar(255) COLLATE "default" DEFAULT ''::character varying NOT NULL,
"password" varchar(255) COLLATE "default" DEFAULT ''::character varying NOT NULL,
"wallpaper" varchar(255) COLLATE "default" DEFAULT ''::character varying NOT NULL,
"floor" varchar(255) COLLATE "default" DEFAULT ''::character varying NOT NULL,
"landscape" varchar(255) COLLATE "default" DEFAULT ''::character varying NOT NULL,
"allow_pets" bool NOT NULL,
"allow_pets_eat" bool NOT NULL,
"allow_walkthrough" bool NOT NULL,
"wall_hidden" bool NOT NULL,
"wall_height" int4 NOT NULL,
"wall_thickness" int2 NOT NULL,
"floor_thickness" int2 NOT NULL,
"group_id" int4 NOT NULL,
"mute_settings" int2 NOT NULL,
"ban_settings" int2 NOT NULL,
"kick_settings" int2 NOT NULL,
"chat_type" int2 NOT NULL,
"chat_balloon" int2 NOT NULL,
"chat_speed" int2 NOT NULL,
"chat_max_distance" int2 NOT NULL,
"chat_flood_protection" int2 NOT NULL,
"image_thumb" varchar(255) COLLATE "default" DEFAULT ''::character varying NOT NULL
)
WITH (OIDS=FALSE)

;
COMMENT ON COLUMN "public"."rooms"."accessibility" IS 'open, locked, password';

-- ----------------------------
-- Records of rooms
-- ----------------------------

-- ----------------------------
-- Table structure for server_properties
-- ----------------------------
DROP TABLE IF EXISTS "public"."server_properties";
CREATE TABLE "public"."server_properties" (
"property_key" varchar(255) COLLATE "default" NOT NULL,
"property_value" varchar(255) COLLATE "default" DEFAULT ''::character varying NOT NULL
)
WITH (OIDS=FALSE)

;

-- ----------------------------
-- Records of server_properties
-- ----------------------------
INSERT INTO "public"."server_properties" VALUES ('org.azure.network.Server.host', '0.0.0.0');
INSERT INTO "public"."server_properties" VALUES ('org.azure.network.Server.port', '38101');

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS "public"."users";
CREATE TABLE "public"."users" (
"id" int4 DEFAULT nextval('users_id_seq'::regclass) NOT NULL,
"email" varchar(255) COLLATE "default" NOT NULL,
"password" varchar(255) COLLATE "default" NOT NULL,
"created_at" timestamp(6) DEFAULT now(),
"updated_at" timestamp(6) DEFAULT now(),
"real_name" varchar(255) COLLATE "default"
)
WITH (OIDS=FALSE)

;

-- ----------------------------
-- Records of users
-- ----------------------------

-- ----------------------------
-- Alter Sequences Owned By 
-- ----------------------------
ALTER SEQUENCE "public"."achievements_id_seq" OWNED BY "achievements"."id";
ALTER SEQUENCE "public"."avatars_id_seq" OWNED BY "avatars"."id";
ALTER SEQUENCE "public"."avatars_ranks_id_seq" OWNED BY "avatars_ranks"."id";
ALTER SEQUENCE "public"."badges_id_seq" OWNED BY "badges"."id";
ALTER SEQUENCE "public"."ranks_id_seq" OWNED BY "ranks"."id";
ALTER SEQUENCE "public"."room_categories_id_seq" OWNED BY "room_categories"."id";
ALTER SEQUENCE "public"."rooms_id_seq" OWNED BY "rooms"."id";
ALTER SEQUENCE "public"."users_id_seq" OWNED BY "users"."id";

-- ----------------------------
-- Uniques structure for table achievements
-- ----------------------------
ALTER TABLE "public"."achievements" ADD UNIQUE ("type");

-- ----------------------------
-- Primary Key structure for table achievements
-- ----------------------------
ALTER TABLE "public"."achievements" ADD PRIMARY KEY ("id");

-- ----------------------------
-- Uniques structure for table avatars
-- ----------------------------
ALTER TABLE "public"."avatars" ADD UNIQUE ("username");

-- ----------------------------
-- Primary Key structure for table avatars
-- ----------------------------
ALTER TABLE "public"."avatars" ADD PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table avatars_ranks
-- ----------------------------
ALTER TABLE "public"."avatars_ranks" ADD PRIMARY KEY ("id");

-- ----------------------------
-- Uniques structure for table avatars_wallet
-- ----------------------------
ALTER TABLE "public"."avatars_wallet" ADD UNIQUE ("avatar_id");

-- ----------------------------
-- Primary Key structure for table badges
-- ----------------------------
ALTER TABLE "public"."badges" ADD PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table ranks
-- ----------------------------
ALTER TABLE "public"."ranks" ADD PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table room_categories
-- ----------------------------
ALTER TABLE "public"."room_categories" ADD PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table rooms
-- ----------------------------
ALTER TABLE "public"."rooms" ADD PRIMARY KEY ("id");

-- ----------------------------
-- Primary Key structure for table server_properties
-- ----------------------------
ALTER TABLE "public"."server_properties" ADD PRIMARY KEY ("property_key");

-- ----------------------------
-- Uniques structure for table users
-- ----------------------------
ALTER TABLE "public"."users" ADD UNIQUE ("email");

-- ----------------------------
-- Primary Key structure for table users
-- ----------------------------
ALTER TABLE "public"."users" ADD PRIMARY KEY ("id");

-- ----------------------------
-- Foreign Key structure for table "public"."avatars"
-- ----------------------------
ALTER TABLE "public"."avatars" ADD FOREIGN KEY ("user_id") REFERENCES "public"."users" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;

-- ----------------------------
-- Foreign Key structure for table "public"."avatars_achievements"
-- ----------------------------
ALTER TABLE "public"."avatars_achievements" ADD FOREIGN KEY ("avatar_id") REFERENCES "public"."avatars" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE "public"."avatars_achievements" ADD FOREIGN KEY ("ach_id") REFERENCES "public"."achievements" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;

-- ----------------------------
-- Foreign Key structure for table "public"."avatars_badges"
-- ----------------------------
ALTER TABLE "public"."avatars_badges" ADD FOREIGN KEY ("avatar_id") REFERENCES "public"."avatars" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE "public"."avatars_badges" ADD FOREIGN KEY ("badge_id") REFERENCES "public"."badges" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;

-- ----------------------------
-- Foreign Key structure for table "public"."avatars_ranks"
-- ----------------------------
ALTER TABLE "public"."avatars_ranks" ADD FOREIGN KEY ("rank_id") REFERENCES "public"."ranks" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE "public"."avatars_ranks" ADD FOREIGN KEY ("avatar_id") REFERENCES "public"."avatars" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;

-- ----------------------------
-- Foreign Key structure for table "public"."avatars_wallet"
-- ----------------------------
ALTER TABLE "public"."avatars_wallet" ADD FOREIGN KEY ("avatar_id") REFERENCES "public"."avatars" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;

-- ----------------------------
-- Foreign Key structure for table "public"."rooms"
-- ----------------------------
ALTER TABLE "public"."rooms" ADD FOREIGN KEY ("category_id") REFERENCES "public"."room_categories" ("id") ON DELETE NO ACTION ON UPDATE NO ACTION;
