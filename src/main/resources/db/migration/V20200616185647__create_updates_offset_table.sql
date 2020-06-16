create table gitlabtelegramintegration.update_offset
(
	id serial not null,
	update_id int not null,
	date_insert timestamptz default now() not null
);

comment on column gitlabtelegramintegration.update_offset.update_id is 'Идентификатор последнего update''а';

comment on column gitlabtelegramintegration.update_offset.date_insert is 'Дата вставки значения';

create unique index update_offset_id_uindex
	on gitlabtelegramintegration.update_offset (id);

create unique index update_offset_update_id_uindex
	on gitlabtelegramintegration.update_offset (update_id);

alter table gitlabtelegramintegration.update_offset
	add constraint update_offset_pk
		primary key (id);

