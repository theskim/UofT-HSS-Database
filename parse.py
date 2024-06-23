import json

with open('courses.json') as f:
    courses = json.load(f)

# Parse course code
def parse_course_code(course_code):
    department = course_code[:3]
    course_number = course_code[3:6]
    campus = course_code[6:]
    return department, course_number, campus

sql_statements = []
for idx, course in enumerate(courses, start=1):
    code = course.get('code', '').replace("'", "''")
    title = course.get('title', '').replace("'", "''")
    url = course.get('url', '').replace("'", "''")
    course_avg = course.get('course_avg', '').replace("'", "''")

    if course_avg == "":
      course_avg = 'N'

    # course code
    department, course_number, campus = parse_course_code(course.get('code', ''))

    # description and ctype
    description = course.get('description', '').replace("'", "''")
    if description == "CS Elective":
        description = ""
        ctype = "'CS'"
    else:
        ctype = "'HSS'"

    summer = 'TRUE' if course.get('summer') else 'FALSE'
    fall = 'TRUE' if course.get('fall') else 'FALSE'
    winter = 'TRUE' if course.get('winter') else 'FALSE'

    sql = f"INSERT INTO Course (id, dept, code, campus, title, url, workload, cavg, ctype, summer, fall, winter) VALUES ({idx}, '{department}', '{course_number}', '{campus}', '{title}', '{url}', 0, '{course_avg}', {ctype}, {summer}, {fall}, {winter});"
    sql_statements.append(sql)

# Write the SQL statements to insert.sql
with open('insert.sql', 'w') as f:
    f.write('\n'.join(sql_statements))
