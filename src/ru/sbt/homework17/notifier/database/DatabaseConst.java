package ru.sbt.homework17.notifier.database;

/**
 * Created by kirill on 16.09.16
 */
public class DatabaseConst {
    public static final String QUERY = "select emp.id as emp_id, emp.name as amp_name, sum(salary) as salary from employee emp left join" +
            "salary_payments sp on emp.id = sp.employee_id where emp.department_id = ? and" +
            " sp.date >= ? and sp.date <= ? group by emp.id, emp.name";
}
