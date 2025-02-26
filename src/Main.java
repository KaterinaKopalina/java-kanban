import model.Epic;
import model.Subtask;
import service.InMemoryTaskManager;
import service.TaskManager;

public class Main {

    public static void main(String[] args) {

        System.out.println("Поехали!");
      TaskManager taskManager = new InMemoryTaskManager();

        /*model.Task washWindows = new model.Task("Помыть окна", "На кухне");
        taskManager.addTask(washWindows);

        model.Task waterFlowers = new model.Task ("Полить цветы", "Кроме кактусов");
        taskManager.addTask(waterFlowers);
        taskManager.printAllTask();
        model.Task washWindows1 = new model.Task ("Помыть окна", "На кухне", model.Status.IN_PROGRESS);
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

       /* model.Subtask goOnHolidaySubtask4 = new model.Subtask("Ничего не делать", "Отдыхать",
                goOnHoliday.getId());
        goOnHolidaySubtask4.setId(5);
        taskManager.updateSubtask(goOnHolidaySubtask4);
        taskManager.printAllSubtask();

        System.out.println(taskManager.getSubtaskByEpic(3));

        model.Epic goOnHoliday123 = new model.Epic("Поехать на отдых123", "Летний отпуск123");
        goOnHoliday123.setId(3);
        taskManager.updateEpic(goOnHoliday123);
        taskManager.printAllEpic();

       /* goOnHolidaySubtask1.setStatus(model.Status.DONE);
        taskManager.updateSubtask(goOnHolidaySubtask1);
        goOnHolidaySubtask2.setStatus(model.Status.DONE);
        taskManager.updateSubtask(goOnHolidaySubtask2);
       goOnHolidaySubtask3.setStatus(model.Status.DONE);
        taskManager.updateSubtask(goOnHolidaySubtask3);
       // System.out.println(goOnHoliday);

       */


    }
    }

