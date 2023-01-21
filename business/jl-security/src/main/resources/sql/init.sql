-- 用户表
create table jl_user
(
    id          varchar(32)                    not null comment '用户id'
        primary key,
    username    varchar(20)         default '' not null comment '用户名',
    password    varchar(32)         default '' not null comment '密码',
    nick_name   varchar(50)                    null comment '昵称',
    salt        varchar(255)                   null comment '用户头像',
    del_flag    tinyint(1) unsigned default 0  not null comment '逻辑删除 1（true）已删除， 0（false）未删除',
    create_time datetime                       not null comment '创建时间',
    update_time datetime                       not null comment '更新时间',
    constraint uk_username
        unique (username)
)
    comment '用户表' charset = utf8mb4;

-- 菜单表
create table jl_menu
(
    id               varchar(32)         default '' not null comment '编号'
        primary key,
    pid              varchar(32)         default '' not null comment '所属上级',
    name             varchar(20)         default '' not null comment '名称',
    type             tinyint(3)          default 0  not null comment '类型(1:菜单,2:按钮)',
    permission_value varchar(50)                    null comment '权限值',
    path             varchar(100)                   null comment '访问路径',
    component        varchar(100)                   null comment '组件路径',
    icon             varchar(50)                    null comment '图标',
    status           tinyint                        null comment '状态(0:禁止,1:正常)',
    del_flag         tinyint(1) unsigned default 0  not null comment '逻辑删除 1（true）已删除， 0（false）未删除',
    create_time      datetime                       null comment '创建时间',
    update_time      datetime                       null comment '更新时间'
)
    comment '权限' charset = utf8mb4;

create index idx_pid
    on jl_menu (pid);

-- 角色表
create table jl_role
(
    id          varchar(32)         default '' not null comment '角色id'
        primary key,
    role_name   varchar(20)         default '' not null comment '角色名称',
    role_code   varchar(20)                    null comment '角色编码',
    remark      varchar(255)                   null comment '备注',
    del_flag    tinyint(1) unsigned default 0  not null comment '逻辑删除 1（true）已删除， 0（false）未删除',
    create_time datetime                       not null comment '创建时间',
    update_time datetime                       not null comment '更新时间'
)
    charset = utf8mb4;

-- 用户角色关联表
create table jl_user_role
(
    id          varchar(32)            default ''  not null comment '主键id'
        primary key,
    role_id     char(19)            default '0' not null comment '角色id',
    user_id     char(19)            default '0' not null comment '用户id',
    del_flag    tinyint(1) unsigned default 0   not null comment '逻辑删除 1（true）已删除， 0（false）未删除',
    create_time datetime                        not null comment '创建时间',
    update_time datetime                        not null comment '更新时间'
)
    charset = utf8mb4;

create index idx_role_id
    on jl_user_role (role_id);

create index idx_user_id
    on jl_user_role (user_id);

-- 角色菜单关联表
create table jl_role_menu
(
    id          varchar(32)         default '' not null
        primary key,
    role_id     varchar(32)         default '' not null,
    menu_id     varchar(32)         default '' not null,
    del_flag    tinyint(1) unsigned default 0  not null comment '逻辑删除 1（true）已删除， 0（false）未删除',
    create_time datetime                       not null comment '创建时间',
    update_time datetime                       not null comment '更新时间'
)
    comment '角色权限' charset = utf8mb4;

create index idx_permission_id
    on jl_role_menu (menu_id);

create index idx_role_id
    on jl_role_menu (role_id);

