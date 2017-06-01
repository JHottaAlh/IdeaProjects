create view user_control as
select
	id,
    login_id,
    name,
	is_stopped,
    password,
    branch_id,
    department_id
from
	users