create view users_posts as
select
	posts.id,
    user_id,
    title,
    category,
    text,
    name,
    password,
    branch_id,
    department_id,
    posts.timed_at,
    posts.updated_at
from
	users, posts
where
	users.id = user_id