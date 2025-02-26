import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        System.out.println("Поехали!");
      TaskManager taskManager = new InMemoryTaskManager();

        /*Task washWindows = new Task("Помыть окна", "На кухне");
        taskManager.addTask(washWindows);

        Task waterFlowers = new Task ("Полить цветы", "Кроме кактусов");
        taskManager.addTask(waterFlowers);
        taskManager.printAllTask();
        Task washWindows1 = new Task ("Помыть окна", "На кухне", Status.IN_PROGRESS);
        washWindows1.id = 1;
        taskManager.updateTask(washWindows1);
        taskManager.printAllTask(); */


        Epic goOnHoliday = new Epic("Поехать на отдых", "Летний отпуск");
        taskManager.addEpic(goOnHoliday);

        Subtask goOnHolidaySubtask1 = new Subtask("Купить чемодан", "Не дороже 3000 р.",
                1);

        Subtask goOnHolidaySubtask2 = new Subtask("Купить путевки", "В Турцию",
                goOnHoliday.getId());
        Subtask goOnHolidaySubtask3 = new Subtask("Снять деньги со счета", "В банке", goOnHoliday.getId());
        taskManager.addSubtask(goOnHolidaySubtask1);
        taskManager.addSubtask(goOnHolidaySubtask2);
        taskManager.addSubtask(goOnHolidaySubtask3);
        System.out.println(goOnHolidaySubtask1);
        System.out.println(goOnHolidaySubtask2);
        System.out.println(goOnHolidaySubtask3);

        System.out.println(goOnHoliday);

       /* Subtask goOnHolidaySubtask4 = new Subtask("Ничего не делать", "Отдыхать",
                goOnHoliday.getId());
        goOnHolidaySubtask4.setId(5);
        taskManager.updateSubtask(goOnHolidaySubtask4);
        taskManager.printAllSubtask();

        System.out.println(taskManager.getSubtaskByEpic(3));

        Epic goOnHoliday123 = new Epic("Поехать на отдых123", "Летний отпуск123");
        goOnHoliday123.setId(3);
        taskManager.updateEpic(goOnHoliday123);
        taskManager.printAllEpic();

       /* goOnHolidaySubtask1.setStatus(Status.DONE);
        taskManager.updateSubtask(goOnHolidaySubtask1);
        goOnHolidaySubtask2.setStatus(Status.DONE);
        taskManager.updateSubtask(goOnHolidaySubtask2);
       goOnHolidaySubtask3.setStatus(Status.DONE);
        taskManager.updateSubtask(goOnHolidaySubtask3);
       // System.out.println(goOnHoliday);

       */


    }
    }

