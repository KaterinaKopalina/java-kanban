import model.Task;
import model.Epic;
import model.Subtask;
import service.FileBackedTaskManager;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {

        System.out.println("Поехали!");
        File newFile = File.createTempFile("test", ".csv");
        FileBackedTaskManager manager = new FileBackedTaskManager(newFile);

        //  TaskManager taskManager = new InMemoryTaskManager();

        Task task1 = new Task("Купить телефон", "Не дороже 20000 руб");
        Task task2 = new Task("Решить контрольную", "По математике");
        manager.addTask(task1);
        manager.addTask(task2);
        manager.getTaskByID(1);
        manager.printAllTask();
        Epic goOnHoliday = new Epic("Поехать на отдых", "Летний отпуск");
        manager.addEpic(goOnHoliday);
        Subtask goOnHolidaySubtask1 = new Subtask("Купить чемодан", "Не дороже 3000 р.",
                3);
        Subtask goOnHolidaySubtask2 = new Subtask("Купить путевки", "В Турцию",
                goOnHoliday.getId());
        Subtask goOnHolidaySubtask3 = new Subtask("Снять деньги со счета", "В банке", goOnHoliday.getId());
        manager.addSubtask(goOnHolidaySubtask1);
        manager.addSubtask(goOnHolidaySubtask2);
        manager.addSubtask(goOnHolidaySubtask3);


        // manager.loadFromFile(newFile);

        System.out.println(Files.readString(newFile.toPath()));

        manager = FileBackedTaskManager.loadFromFile(newFile);
        List<Task> tasks = manager.getTasks();
        System.out.println(tasks);
        manager.printAllEpic();

        Epic goOnHoliday1 = new Epic("Поехать", "отпуск1");
        manager.addEpic(goOnHoliday1);
        manager.printAllEpic();

        System.out.println(Files.readString(newFile.toPath()));
        manager = FileBackedTaskManager.loadFromFile(newFile);
        List<Epic> epiks = manager.getEpics();
        List<Subtask> subtasks = manager.getSubtasks();
        System.out.println(epiks);
        System.out.println(tasks);

        System.out.println(subtasks);

        System.out.println(manager.getSubtaskByEpic(3));







     /*   Epic goOnHoliday = new Epic("Поехать на отдых", "Летний отпуск");
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
        System.out.println(taskManager.getHistory()); */


    }
}

