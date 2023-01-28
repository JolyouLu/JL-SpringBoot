-- 用户表
create table jl_user
(
    id          varchar(32)            not null comment '用户id'
        primary key,
    username    varchar(20) default '' not null comment '用户名',
    password    varchar(32) default '' not null comment '密码',
    nick_name   varchar(50) null comment '昵称',
    salt        varchar(255) null comment '用户头像',
    del_flag    tinyint(1) unsigned default 0 not null comment '逻辑删除 1（true）已删除， 0（false）未删除',
    create_time datetime               not null comment '创建时间',
    update_time datetime               not null comment '更新时间',
    constraint uk_username
        unique (username)
) comment '用户表' charset = utf8mb4;

INSERT INTO jl.jl_user (id, username, password, nick_name, salt, del_flag, create_time, update_time)
VALUES ('1', 'admin', 'e10adc3949ba59abbe56e057f20f883e', 'admin', '', 0, '2019-11-01 10:39:47', '2019-11-01 10:39:47');

-- 菜单表
create table jl_menu
(
    id               varchar(32) default '' not null comment '编号'
        primary key,
    pid              varchar(32) default '' not null comment '所属上级',
    name             varchar(20) default '' not null comment '名称',
    type             tinyint(3) default 0 not null comment '类型(1:菜单,2:按钮)',
    permission_value varchar(50) null comment '权限值',
    path             varchar(100) null comment '访问路径',
    component        varchar(100) null comment '组件路径',
    icon             varchar(50) null comment '图标',
    status           tinyint null comment '状态(0:禁止,1:正常)',
    del_flag         tinyint(1) unsigned default 0 not null comment '逻辑删除 1（true）已删除， 0（false）未删除',
    create_time      datetime null comment '创建时间',
    update_time      datetime null comment '更新时间'
) comment '权限' charset = utf8mb4;

create index idx_pid
    on jl_menu (pid);

insert into jl.jl_menu (id, pid, name, type, permission_value, path, component, icon, status, del_flag, create_time,
                        update_time)
values ('1', '0', '全部数据', 0, null, null, null, null, null, 0, '2019-11-15 17:13:06', '2019-11-15 17:13:06'),
       ('1195268474480156673', '1', '权限管理', 1, null, '/acl', 'Layout', null, null, 0, '2019-11-15 17:13:06',
        '2019-11-18 13:54:25'),
       ('1195268616021139457', '1195268474480156673', '用户管理', 1, null, 'user/list', '/acl/user/list', null, null, 0,
        '2019-11-15 17:13:40', '2019-11-18 13:53:12'),
       ('1195268788138598401', '1195268474480156673', '角色管理', 1, null, 'role/list', '/acl/role/list', null, null, 0,
        '2019-11-15 17:14:21', '2019-11-15 17:14:21'),
       ('1195268893830864898', '1195268474480156673', '菜单管理', 1, null, 'menu/list', '/acl/menu/list', null, null, 0,
        '2019-11-15 17:14:46', '2019-11-15 17:14:46'),
       ('1195269143060602882', '1195268616021139457', '查看', 2, 'user.list', '', '', null, null, 0,
        '2019-11-15 17:15:45', '2019-11-17 21:57:16'),
       ('1195269295926206466', '1195268616021139457', '添加', 2, 'user.add', 'user/add', '/acl/user/form', null, null,
        0, '2019-11-15 17:16:22', '2019-11-15 17:16:22'),
       ('1195269473479483394', '1195268616021139457', '修改', 2, 'user.update', 'user/update/:id', '/acl/user/form',
        null, null, 0, '2019-11-15 17:17:04', '2019-11-15 17:17:04'),
       ('1195269547269873666', '1195268616021139457', '删除', 2, 'user.remove', '', '', null, null, 0,
        '2019-11-15 17:17:22', '2019-11-15 17:17:22'),
       ('1195269821262782465', '1195268788138598401', '修改', 2, 'role.update', 'role/update/:id', '/acl/role/form',
        null, null, 0, '2019-11-15 17:18:27', '2019-11-15 17:19:53'),
       ('1195269903542444034', '1195268788138598401', '查看', 2, 'role.list', '', '', null, null, 0,
        '2019-11-15 17:18:47', '2019-11-15 17:18:47'),
       ('1195270037005197313', '1195268788138598401', '添加', 2, 'role.add', 'role/add', '/acl/role/form', null, null,
        0, '2019-11-15 17:19:19', '2019-11-18 11:05:42'),
       ('1195270442602782721', '1195268788138598401', '删除', 2, 'role.remove', '', '', null, null, 0,
        '2019-11-15 17:20:55', '2019-11-15 17:20:55'),
       ('1195270621548568578', '1195268788138598401', '角色权限', 2, 'role.acl', 'role/distribution/:id',
        '/acl/role/roleForm', null, null, 0, '2019-11-15 17:21:38', '2019-11-15 17:21:38'),
       ('1195270744097742849', '1195268893830864898', '查看', 2, 'permission.list', '', '', null, null, 0,
        '2019-11-15 17:22:07', '2019-11-15 17:22:07'),
       ('1195270810560684034', '1195268893830864898', '添加', 2, 'permission.add', '', '', null, null, 0,
        '2019-11-15 17:22:23', '2019-11-15 17:22:23'),
       ('1195270862100291586', '1195268893830864898', '修改', 2, 'permission.update', '', '', null, null, 0,
        '2019-11-15 17:22:35', '2019-11-15 17:22:35'),
       ('1195270887933009922', '1195268893830864898', '删除', 2, 'permission.remove', '', '', null, null, 0,
        '2019-11-15 17:22:41', '2019-11-15 17:22:41');

-- 角色表
create table jl_role
(
    id          varchar(32) default '' not null comment '角色id'
        primary key,
    role_name   varchar(20) default '' not null comment '角色名称',
    role_code   varchar(20) null comment '角色编码',
    remark      varchar(255) null comment '备注',
    del_flag    tinyint(1) unsigned default 0 not null comment '逻辑删除 1（true）已删除， 0（false）未删除',
    create_time datetime               not null comment '创建时间',
    update_time datetime               not null comment '更新时间'
) charset = utf8mb4;

INSERT INTO jl.jl_role (id, role_name, role_code, remark, del_flag, create_time, update_time)
VALUES ('1', '普通管理员', null, null, 0, '2019-11-11 13:09:32', '2019-11-18 10:27:18');

-- 用户角色关联表
create table jl_user_role
(
    id          varchar(32) default ''  not null comment '主键id'
        primary key,
    role_id     char(19)    default '0' not null comment '角色id',
    user_id     char(19)    default '0' not null comment '用户id',
    del_flag    tinyint(1) unsigned default 0 not null comment '逻辑删除 1（true）已删除， 0（false）未删除',
    create_time datetime                not null comment '创建时间',
    update_time datetime                not null comment '更新时间'
) charset = utf8mb4;

INSERT INTO jl.jl_user_role (id, role_id, user_id, del_flag, create_time, update_time)
VALUES ('1', '1', '1', 0, '2019-11-11 13:09:53', '2019-11-11 13:09:53');

create index idx_role_id
    on jl_user_role (role_id);

create index idx_user_id
    on jl_user_role (user_id);

-- 角色菜单关联表
create table jl_role_menu
(
    id          varchar(32) default '' not null
        primary key,
    role_id     varchar(32) default '' not null,
    menu_id     varchar(32) default '' not null,
    del_flag    tinyint(1) unsigned default 0 not null comment '逻辑删除 1（true）已删除， 0（false）未删除',
    create_time datetime               not null comment '创建时间',
    update_time datetime               not null comment '更新时间'
) comment '角色权限' charset = utf8mb4;

create index idx_permission_id
    on jl_role_menu (menu_id);

create index idx_role_id
    on jl_role_menu (role_id);

insert into MY_TABLE (id, role_id, menu_id, del_flag, create_time, update_time)
values ('1196301979754455041', '1', '1', 1, '2019-11-18 13:39:53', '2019-11-18 13:39:53'),
       ('1196301979792203778', '1', '1195268474480156673', 1, '2019-11-18 13:39:53', '2019-11-18 13:39:53'),
       ('1196301979821563906', '1', '1195268616021139457', 1, '2019-11-18 13:39:53', '2019-11-18 13:39:53'),
       ('1196301979842535426', '1', '1195268788138598401', 1, '2019-11-18 13:39:53', '2019-11-18 13:39:53'),
       ('1196301979855118338', '1', '1195268893830864898', 1, '2019-11-18 13:39:53', '2019-11-18 13:39:53'),
       ('1196301979880284161', '1', '1195269143060602882', 1, '2019-11-18 13:39:53', '2019-11-18 13:39:53'),
       ('1196301979913838593', '1', '1195269295926206466', 1, '2019-11-18 13:39:53', '2019-11-18 13:39:53'),
       ('1196301979951587330', '1', '1195269473479483394', 1, '2019-11-18 13:39:53', '2019-11-18 13:39:53'),
       ('1196301980014501889', '1', '1195269547269873666', 1, '2019-11-18 13:39:53', '2019-11-18 13:39:53'),
       ('1196301980035473410', '1', '1195269821262782465', 1, '2019-11-18 13:39:53', '2019-11-18 13:39:53'),
       ('1196301980052250626', '1', '1195269903542444034', 1, '2019-11-18 13:39:53', '2019-11-18 13:39:53'),
       ('1196301980077416450', '1', '1195270037005197313', 1, '2019-11-18 13:39:53', '2019-11-18 13:39:53'),
       ('1196301980094193665', '1', '1195270442602782721', 1, '2019-11-18 13:39:53', '2019-11-18 13:39:53'),
       ('1196301980119359489', '1', '1195270621548568578', 1, '2019-11-18 13:39:53', '2019-11-18 13:39:53'),
       ('1196301980136136706', '1', '1195270744097742849', 1, '2019-11-18 13:39:53', '2019-11-18 13:39:53'),
       ('1196301980249382913', '1', '1195270810560684034', 1, '2019-11-18 13:39:53', '2019-11-18 13:39:53'),
       ('1196301980270354434', '1', '1195270862100291586', 1, '2019-11-18 13:39:53', '2019-11-18 13:39:53'),
       ('1196301980287131649', '1', '1195270887933009922', 1, '2019-11-18 13:39:53', '2019-11-18 13:39:53'),

