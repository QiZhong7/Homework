package com.atguigu.team.service;

import com.atguigu.team.domain.*;

/**
 * @Description Responsible for encapsulating the data in "Data" into Employee[]，and providing related operations.
 * @auther Qi Zhong
 * @version v1.0
 * @date 2020/5/27
 */

public class NameListService {
    Employee[] employees;

    /**
     * The method initializes employees and its element.
     */
    public NameListService() {
        employees = new Employee[Data.EMPLOYEES.length];
        for (int i = 0; i < employees.length; i++) {
            int type = Integer.parseInt(Data.EMPLOYEES[i][0]);
            int id = Integer.parseInt(Data.EMPLOYEES[i][1]);
            String name = Data.EMPLOYEES[i][2];
            int age = Integer.parseInt(Data.EMPLOYEES[i][3]);
            double salary = Double.parseDouble(Data.EMPLOYEES[i][4]);
            Equipment equipment;
            double bonus;
            switch (type) {
                case Data.EMPLOYEE:
                    employees[i] = new Employee(id, name, age, salary);
                    break;
                case Data.PROGRAMMER:
                    equipment = createEquipment(i);
                    employees[i] = new Programmer(id, name, age, salary, equipment);
                    break;
                case Data.DESIGNER:
                    equipment = createEquipment(i);
                    bonus = Double.parseDouble(Data.EMPLOYEES[i][5]);
                    employees[i] = new Designer(id, name, age, salary, equipment, bonus);
                    break;
                case Data.ARCHITECT:
                    equipment = createEquipment(i);
                    bonus = Double.parseDouble(Data.EMPLOYEES[i][5]);
                    int stock = Integer.parseInt(Data.EMPLOYEES[i][6]);
                    employees[i] = new Arichitect(id, name, age, salary, equipment, bonus, stock);
                    break;
            }
        }
    }

    private Equipment createEquipment(int i) {
        int type = Integer.parseInt(Data.EQUIPMENTS[i][0]);
        switch (type) {
            case Data.PC:
                return new PC(Data.EQUIPMENTS[i][1], Data.EQUIPMENTS[i][2]);
            case Data.NOTEBOOK:
                return new NoteBook(Data.EQUIPMENTS[i][1], Double.parseDouble(Data.EQUIPMENTS[i][2]));
            case Data.PRINTER:
                return new Printer(Data.EQUIPMENTS[i][1], Data.EQUIPMENTS[i][2]);
        }
        return null;
    }

    /**
     * Retrieve all current employees.
     *
     * @return
     * @author Qi Zhong
     * @date 2020/5/27
     */
    public Employee[] getAllEmployees() {
        return employees;
    }

    /**
     * Search for a employee with assigned id.
     *
     * @param id
     * @return
     */
    public Employee getEmployee(int id) throws TeamException{
        for (int i = 0; i < employees.length; i++) {
            if (employees[i].getId() == id) {
                return employees[i];

            }
        }
        throw new TeamException("Can't find assigned employee");
    }
}
