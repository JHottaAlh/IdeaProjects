create view comment_list as
select
	comments.id,
	comments.text,
    comments.timed_at,
    comments.updated_at,
    post_id,
    comments.user_id,
    name,
    comments.branch_id,
    comments.department_id
from
	users, posts, comments
where
	users.id = comments.user_id and
    posts.id = post_id
