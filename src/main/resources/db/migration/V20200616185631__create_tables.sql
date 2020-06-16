create table gitlabtelegramintegration.gitlab_telegram_user_mapping
(
	id serial not null,
	telegram_username text not null,
	telegram_first_name text,
	telegram_last_name text,
	telegram_chat_id int not null,
	gitlab_username text not null
);

comment on column gitlabtelegramintegration.gitlab_telegram_user_mapping.telegram_username is 'Имя пользователя telegram';

comment on column gitlabtelegramintegration.gitlab_telegram_user_mapping.telegram_chat_id is 'Идентификатор чата с пользователем';

comment on column gitlabtelegramintegration.gitlab_telegram_user_mapping.gitlab_username is 'Имя пользователя в gitlab';

create unique index gitlab_telegram_user_mapping_id_uindex
	on gitlabtelegramintegration.gitlab_telegram_user_mapping (id);

alter table gitlabtelegramintegration.gitlab_telegram_user_mapping
	add constraint gitlab_telegram_user_mapping_pk
		primary key (id);

