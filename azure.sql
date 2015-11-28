CREATE TABLE "avatars" (
"id" serial4 NOT NULL,
"user_id" int4 NOT NULL,
"username" varchar(255) NOT NULL,
"password" varchar(255) NOT NULL,
"figure" varchar(255) NOT NULL DEFAULT 'hr-115-42.hd-190-1.ch-215-62.lg-285-91.sh-290-62',
"gender" varchar(1) NOT NULL DEFAULT 'M',
"motto" varchar(255) NOT NULL,
"respect" int4 NOT NULL DEFAULT 0,
PRIMARY KEY ("id") ,
CONSTRAINT "uniq_username" UNIQUE ("username")
)
WITHOUT OIDS;

CREATE TABLE "users" (
"id" serial4 NOT NULL,
"email" varchar(255) NOT NULL,
"password" varchar(255) NOT NULL,
"created_at" timestamp DEFAULT CURRENT_TIMESTAMP,
"updated_at" timestamp DEFAULT CURRENT_TIMESTAMP,
"real_name" varchar(255),
PRIMARY KEY ("id") ,
CONSTRAINT "uniq_email" UNIQUE ("email")
)
WITHOUT OIDS;

CREATE TABLE "avatars_wallet" (
"avatar_id" int4 NOT NULL,
"credits" int4 NOT NULL DEFAULT 0,
"diamonds" int4 NOT NULL DEFAULT 0,
"achievement_points" int4 NOT NULL DEFAULT 0,
CONSTRAINT "uniq_user_id" UNIQUE ("avatar_id")
)
WITHOUT OIDS;

CREATE TABLE "avatars_badges" (
"avatar_id" int4 NOT NULL,
"badge_id" int4 NOT NULL
)
WITHOUT OIDS;

CREATE TABLE "avatars_achievements" (
"avatar_id" int4 NOT NULL,
"ach_id" int4 NOT NULL,
"ach_level" int4
)
WITHOUT OIDS;

CREATE TABLE "achievements" (
"id" serial4 NOT NULL,
"type" varchar(255) NOT NULL,
"max_level" int2 NOT NULL,
"badge_id" int4 NOT NULL,
PRIMARY KEY ("id") ,
CONSTRAINT "uniq_type" UNIQUE ("type")
)
WITHOUT OIDS;

CREATE TABLE "avatars_ranks" (
"id" serial4 NOT NULL,
"avatar_id" int4 NOT NULL,
"rank_id" int4 NOT NULL,
PRIMARY KEY ("id") 
)
WITHOUT OIDS;

CREATE TABLE "ranks" (
"id" serial4 NOT NULL,
"rank_name" varchar(255),
"rank_value" varchar(255),
PRIMARY KEY ("id") 
)
WITHOUT OIDS;

CREATE TABLE "badges" (
"id" serial4 NOT NULL,
"badge_name" varchar(255) NOT NULL,
PRIMARY KEY ("id") 
)
WITHOUT OIDS;

CREATE TABLE "server_properties" (
"property_key" varchar(255) NOT NULL,
"property_value" varchar(255) NOT NULL,
PRIMARY KEY ("property_key") 
)
WITHOUT OIDS;

CREATE TABLE "room_categories" (
"id" serial4 NOT NULL,
"name" varchar(255) NOT NULL,
"min_rank" int4 NOT NULL DEFAULT 0,
PRIMARY KEY ("id") 
)
WITHOUT OIDS;

CREATE TABLE "rooms" (
"id" serial4 NOT NULL,
"type" varchar(255) NOT NULL DEFAULT 'private',
"owner" int4 NOT NULL,
"name" varchar(255),
"description" varchar(255),
"model" varchar(255) NOT NULL,
"category_id" int4 NOT NULL,
"accessibility" varchar(255) NOT NULL DEFAULT 'open',
"trade_enabled" bool NOT NULL,
"curr_users" int4 NOT NULL DEFAULT 0,
"max_users" int4 NOT NULL DEFAULT 25,
"score" int4 NOT NULL DEFAULT 0,
"icon_bg" int4 NOT NULL,
"icon_fg" int4 NOT NULL,
"icon_items" varchar(255) NOT NULL DEFAULT '',
"password" varchar(255) NOT NULL DEFAULT '',
"wallpaper" varchar(255) NOT NULL DEFAULT '',
"floor" varchar(255) NOT NULL DEFAULT '',
"landscape" varchar(255) NOT NULL DEFAULT '',
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
"image_thumb" varchar(255) NOT NULL DEFAULT '',
PRIMARY KEY ("id") 
)
WITHOUT OIDS;

COMMENT ON COLUMN "rooms"."accessibility" IS 'open, locked, password';


ALTER TABLE "avatars" ADD CONSTRAINT "fk_avatars_users_1" FOREIGN KEY ("user_id") REFERENCES "users" ("id");
ALTER TABLE "avatars_wallet" ADD CONSTRAINT "fk_avatars_wallet_avatars_1" FOREIGN KEY ("avatar_id") REFERENCES "avatars" ("id");
ALTER TABLE "avatars_badges" ADD CONSTRAINT "fk_avatars_badges_avatars_1" FOREIGN KEY ("avatar_id") REFERENCES "avatars" ("id");
ALTER TABLE "avatars_achievements" ADD CONSTRAINT "fk_avatars_achievements_avatars_1" FOREIGN KEY ("avatar_id") REFERENCES "avatars" ("id");
ALTER TABLE "avatars_ranks" ADD CONSTRAINT "fk_avatars_ranks_avatars_1" FOREIGN KEY ("avatar_id") REFERENCES "avatars" ("id");
ALTER TABLE "avatars_ranks" ADD CONSTRAINT "fk_avatars_ranks_ranks_1" FOREIGN KEY ("rank_id") REFERENCES "ranks" ("id");
ALTER TABLE "avatars_achievements" ADD CONSTRAINT "fk_avatars_achievements_achievements_1" FOREIGN KEY ("ach_id") REFERENCES "achievements" ("id");
ALTER TABLE "avatars_badges" ADD CONSTRAINT "fk_avatars_badges_badges_1" FOREIGN KEY ("badge_id") REFERENCES "badges" ("id");
ALTER TABLE "rooms" ADD CONSTRAINT "fk_rooms_room_categories_1" FOREIGN KEY ("category_id") REFERENCES "room_categories" ("id");

