package myattempt;

import java.util.List;

public class Main {
	public static void main(String[] args) {
		Department dep=new Department("someDepo");
		DepartmentDAO depdao=new DepartmentDAO();
		depdao.saveDepartment(dep);
		depdao.saveDepartment(dep);
		depdao.saveDepartment(dep);
		depdao.saveDepartment(dep);
		dep.setDeptname("hello");
		System.out.println(depdao.updateDepartment(dep));
		System.out.println(depdao.deleteBook(dep.getDeptid()));
		List<Department> list=depdao.getDepartments();
		System.out.println(list.toString());
	}
}
