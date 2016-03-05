### MySQL ###

```
CREATE TABLE `users` (
	`username`   varchar(100) NOT NULL,
	`pwd`        varchar(100),
	`first_name` varchar(100),
	`last_name`  varchar(100),
	`email`      varchar(100),
	PRIMARY KEY (`username`)
);

CREATE TABLE `blog` (
	`id`          varchar(100) NOT NULL,
	`name`        varchar(100),
	`description` varchar(200),
	`owner`       varchar(100),
	`created_at`  date,
	`updated_at`  date,
	PRIMARY KEY (`id`)
);

CREATE TABLE `post` (
	`id`         varchar(100) NOT NULL,
	`blog_id`    varchar(100),
	`title`      varchar(200),
	`author`     varchar(100),
	`created_at` date,
	`updated_at` date,
	`content`    text,
	PRIMARY KEY (`id`)
);

CREATE TABLE `comments` (
	`id`         varchar(100) NOT NULL,
	`post_id`    varchar(100),
	`author`     varchar(100),
	`created_at` date,
	`content`    varchar(1000),
	PRIMARY KEY (`id`)
);
```

### PostgreSQL ###

CREATE TABLE users (
> username character varying(100) NOT NULL,
> pwd character varying(100),
> first\_name character varying(100),
> last\_name character varying(100),
> email character varying(100)
);
ALTER TABLE ONLY users
> ADD CONSTRAINT users\_pkey PRIMARY KEY (username);

CREATE TABLE blog (
> id character varying(100) NOT NULL,
> name character varying(100),
> description character varying(200),
> owner character varying(100),
> created\_at date,
> updated\_at date
);
ALTER TABLE ONLY blog
> ADD CONSTRAINT blog\_pkey PRIMARY KEY (id);

CREATE TABLE post (
> id character varying(100) NOT NULL,
> blog\_id character varying(100),
> title character varying(200),
> author character varying(100),
> created\_at date,
> updated\_at date,
> content text
);
ALTER TABLE ONLY post
> ADD CONSTRAINT post\_pkey PRIMARY KEY (id);

CREATE TABLE comments (
> id character varying(100) NOT NULL,
> post\_id character varying(100),
> author character varying(100),
> created\_at date,
> content character varying(1000)
);
ALTER TABLE ONLY comments
> ADD CONSTRAINT comments\_pkey PRIMARY KEY (id);

### Oracle ###

```
CREATE TABLE "USERS" (
	"USERNAME"   VARCHAR2(100 CHAR) NOT NULL,
	"PWD"        VARCHAR2(100 CHAR),
	"FIRST_NAME" VARCHAR2(100 CHAR),
	"LAST_NAME"  VARCHAR2(100 CHAR),
	"EMAIL"      VARCHAR2(100 CHAR),
	PRIMARY KEY ("USERNAME")
);

CREATE TABLE "BLOG" (
	"ID"          VARCHAR2(100 CHAR) NOT NULL,
	"NAME"        VARCHAR2(100 CHAR),
	"DESCRIPTION" VARCHAR2(200 CHAR),
	"OWNER"       VARCHAR2(100 CHAR),
	"CREATED_AT"  DATE,
	"UPDATED_AT"  DATE,
	PRIMARY KEY ("ID")
);

CREATE TABLE "POST" (
	"ID"         VARCHAR2(100 CHAR) NOT NULL,
	"BLOG_ID"    VARCHAR2(100 CHAR),
	"TITLE"      VARCHAR2(200 CHAR),
	"AUTHOR"     VARCHAR2(100 CHAR),
	"CREATED_AT" DATE,
	"UPDATED_AT" DATE,
	"CONTENT"    CLOB,
	PRIMARY KEY ("ID")
);

CREATE TABLE "COMMENTS" (
	"ID"         VARCHAR2(100 CHAR) NOT NULL,
	"POST_ID"    VARCHAR2(100 CHAR),
	"AUTHOR"     VARCHAR2(100 CHAR),
	"CREATED_AT" DATE,
	"CONTENT"    VARCHAR2(1000 CHAR),
	PRIMARY KEY ("ID")
);
```

### SQL Server ###

```
CREATE TABLE [USERS] (
	[username]   [varchar](100) NOT NULL,
	[pwd]        [varchar](100),
	[first_name] [varchar](100),
	[last_name]  [varchar](100),
	[email]      [varchar](100),
	PRIMARY KEY ([username])
);

CREATE TABLE [BLOG] (
	[id]          [varchar](100) NOT NULL,
	[name]        [varchar](100),
	[description] [varchar](200),
	[owner]       [varchar](100),
	[created_at]  [datetime],
	[updated_at]  [datetime],
	PRIMARY KEY ([id])
);

CREATE TABLE [POST] (
	[id]         [varchar](100) NOT NULL,
	[blog_id]    [varchar](100),
	[title]      [varchar](200),
	[author]     [varchar](100),
	[created_at] [datetime],
	[updated_at] [datetime],
	[content]    [text],
	PRIMARY KEY ([id])
);

CREATE TABLE [COMMENTS] (
	[id]         [varchar](100) NOT NULL,
	[post_id]    [varchar](100),
	[author]     [varchar](100),
	[created_at] [datetime],
	[content]    [varchar](1000),
	PRIMARY KEY ([id])
);
```

### DB2 ###

```
CREATE TABLE "USERS" (
	"USERNAME"   VARCHAR(100) NOT NULL,
	"PWD"        VARCHAR(100),
	"FIRST_NAME" VARCHAR(100),
	"LAST_NAME"  VARCHAR(100),
	"EMAIL"      VARCHAR(100),
	PRIMARY KEY ("USERNAME")
);

CREATE TABLE "BLOG" (
	"ID"          VARCHAR(100) NOT NULL,
	"NAME"        VARCHAR(100),
	"DESCRIPTION" VARCHAR(200),
	"OWNER"       VARCHAR(100),
	"CREATED_AT"  DATE,
	"UPDATED_AT"  DATE,
	PRIMARY KEY ("ID")
);

CREATE TABLE "POST" (
	"ID"         VARCHAR(100) NOT NULL,
	"BLOG_ID"    VARCHAR(100),
	"TITLE"      VARCHAR(200),
	"AUTHOR"     VARCHAR(100),
	"CREATED_AT" DATE,
	"UPDATED_AT" DATE,
	"CONTENT"    CLOB,
	PRIMARY KEY ("ID")
);

CREATE TABLE "COMMENTS" (
	"ID"         VARCHAR(100) NOT NULL,
	"POST_ID"    VARCHAR(100),
	"AUTHOR"     VARCHAR(100),
	"CREATED_AT" DATE,
	"CONTENT"    VARCHAR(1000),
	PRIMARY KEY ("ID")
);
```