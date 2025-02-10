
public class Main {

    public static void main(String[] args) {

        System.out.println("Поехали!");
        TaskManager taskManager = new TaskManager();

        Task washWindows = new Task("Помыть окна", "На кухне");
        taskManager.addTask(washWindows);
        System.out.println(washWindows);
        Task waterFlowers = new Task ("Полить цветы", "Кроме кактусов");
        taskManager.addTask(waterFlowers);
        System.out.println(waterFlowers);

        Task washWindowsToUpdate = new Task(washWindows.getId(), "Помыть окна", "На кухне",
                Status.IN_PROGRESS);
        taskManager.updateTask(washWindowsToUpdate);
        System.out.println(washWindowsToUpdate);
        washWindows = taskManager.updateTask(washWindowsToUpdate);
        System.out.println(washWindows);


        Epic goOnHoliday = new Epic("Поехать на отдых", "Летний отпуск");
        taskManager.addEpic(goOnHoliday);
        System.out.println(goOnHoliday);
        Subtask goOnHolidaySubtask1 = new Subtask("Купить чемодан", "Не дороже 3000 р.",
                goOnHoliday.getId());
        Subtask goOnHolidaySubtask2 = new Subtask("Купить путевки", "В Турцию",
                goOnHoliday.getId());
        taskManager.addSubtask(goOnHolidaySubtask1);
        taskManager.addSubtask(goOnHolidaySubtask2);
        System.out.println(goOnHoliday);
        goOnHolidaySubtask2.setStatus(Status.DONE);
        taskManager.updateSubtask(goOnHolidaySubtask2);
        System.out.println(goOnHoliday);
        goOnHolidaySubtask1.setStatus(Status.DONE);
        taskManager.updateSubtask(goOnHolidaySubtask1);
        System.out.println(goOnHoliday);


        taskManager.deleteTaskByID(2);
        taskManager.printAllTask();
        taskManager.printAllEpic();
        taskManager.printAllSubtask();

        taskManager.deleteSubtasks();
        taskManager.printAll();

        taskManager.deleteEpicByID(3);
        taskManager.printAll();

    }
    }

