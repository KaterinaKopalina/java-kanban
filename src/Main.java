import model.Task;
import model.Epic;
import model.Subtask;
import service.InMemoryTaskManager;
import service.TaskManager;

public class Main {

    public static void main(String[] args) {

        System.out.println("Поехали!");
        TaskManager taskManager = new InMemoryTaskManager();

        Task task1 = new Task("Купить телефон", "Не дороже 20000 руб");
        Task task2 = new Task("Решить контрольную", "По математике");
        taskManager.addTask(task1);
        taskManager.addTask(task2);

        Epic goOnHoliday = new Epic("Поехать на отдых", "Летний отпуск");
        taskManager.addEpic(goOnHoliday);

        Subtask goOnHolidaySubtask1 = new Subtask("Купить чемодан", "Не дороже 3000 р.",
                3);
        Subtask goOnHolidaySubtask2 = new Subtask("Купить путевки", "В Турцию",
                goOnHoliday.getId());
        Subtask goOnHolidaySubtask3 = new Subtask("Снять деньги со счета", "В банке", goOnHoliday.getId());
        taskManager.addSubtask(goOnHolidaySubtask1);
        taskManager.addSubtask(goOnHolidaySubtask2);
        taskManager.addSubtask(goOnHolidaySubtask3);

        Epic buyCat = new Epic("Купить кота", "в питомнике");
        taskManager.addEpic(buyCat);

        Subtask buyCat1 = new Subtask("Найти питомник", "Позвонить продавцу", buyCat.getId());
        Subtask buyCat2 = new Subtask("Съездить в питомник", "Выбрать кота",
                buyCat.getId());
        taskManager.addSubtask(buyCat1);
        taskManager.addSubtask(buyCat2);

        taskManager.getTaskByID(1);
        taskManager.getTaskByID(2);
        taskManager.getEpicByID(3);
        taskManager.getSubtaskByID(4);
        taskManager.getSubtaskByID(5);
        taskManager.getSubtaskByID(6);


        taskManager.printAllTask();
        System.out.println(taskManager.getHistory());

        taskManager.deleteTasks();

        taskManager.deleteEpics();
        taskManager.printAllTask();
        System.out.println(taskManager.getHistory());


    }
}

