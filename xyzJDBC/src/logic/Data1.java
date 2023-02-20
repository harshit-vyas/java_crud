package logic;

import java.util.Scanner;

import bean.Teacher;


public class Data1 {
	static public Teacher dataTeacher() {
		Scanner sc= new Scanner(System.in);
		Teacher t = new Teacher();
		System.out.println("enter id");
		t.setId(sc.nextInt());
		System.out.println("enter name");
		t.setName(sc.next());
		System.out.println("enter address");
		t.setAddress(sc.next());
		System.out.println("enter contact");
		t.setContact(sc.next());
		System.out.println("enter salary");
		t.setSalary(sc.nextInt());
		return t;
	 }
}
