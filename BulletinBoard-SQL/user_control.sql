alter view user_control as
select
	users.id
	,login_id
	,users.name
	,is_stopped
	,password
	,branch_id
	,department_id
	,branches.name as branch_name
	,departments.name as department_name
from
	users, branches, departments
WHERE
	users.branch_id = branches.id and
	users.department_id = departments.id
