package resources;

import pojo.AddEmployee;

public class DummyApiTestDataBuild {
    public AddEmployee addEmployeePayload(String name, int salary, int age){
        AddEmployee addEmp = new AddEmployee();
        addEmp.setName(name);
        addEmp.setSalary(salary);
        addEmp.setAge(age);
        return addEmp;
    }
}
