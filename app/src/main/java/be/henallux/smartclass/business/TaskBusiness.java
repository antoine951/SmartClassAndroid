package be.henallux.smartclass.business;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import be.henallux.smartclass.model.Task;

public class TaskBusiness {

    /*private ArrayList<Task> firstDayTasks;
    private Date firstDay;

    private ArrayList<Task> secondDayTasks;
    private Date secondDay;

    private ArrayList<Task> thirdDayTasks;
    private Date thirdDay;

    private ArrayList<Task> forthDayTasks;
    private Date forthDay;

    private ArrayList<Task> fifthDayTasks;
    private Date fifthDay;

    private ArrayList<Task> sixthDayTasks;
    private Date sixthDay;

    private ArrayList<Task> seventhDayTasks;
    private Date seventhDay;*/

    private ArrayList<Date> dates;
    private ArrayList<ArrayList<Task>> tasks;


    public TaskBusiness() {
        this.dates = new ArrayList<>();
        this.tasks = new ArrayList<>();
        for (int day = 1; day < 8; day++) {
            this.dates.add(computeNDayAfter(day));
            this.tasks.add(new ArrayList<>());
        }



        /*this.firstDay = computeNDayAfter(1);
        this.secondDay = computeNDayAfter(2);
        this.thirdDay = computeNDayAfter(3);
        this.forthDay = computeNDayAfter(4);
        this.fifthDay = computeNDayAfter(5);
        this.sixthDay = computeNDayAfter(6);
        this.seventhDay = computeNDayAfter(7);


        this.firstDayTasks = new ArrayList<>();
        this.secondDayTasks = new ArrayList<>();
        this.thirdDayTasks = new ArrayList<>();
        this.forthDayTasks = new ArrayList<>();
        this.fifthDayTasks = new ArrayList<>();
        this.sixthDayTasks = new ArrayList<>();
        this.seventhDayTasks = new ArrayList<>();*/
        sortDate();
    }

    private void sortDate() {
        ArrayList<Task> allTasks = getTask();
        for (Task task : allTasks) {
            if (task.getDate().equals(dates.get(0))) {
                tasks.get(0).add(task);
            } else if (task.getDate().equals(dates.get(1))) {
                tasks.get(1).add(task);
            } else if (task.getDate().equals(dates.get(2))) {
                tasks.get(2).add(task);
            } else if (task.getDate().equals(dates.get(3))) {
                tasks.get(3).add(task);
            } else if (task.getDate().equals(dates.get(4))) {
                tasks.get(4).add(task);
            } else if (task.getDate().equals(dates.get(5))) {
                tasks.get(5).add(task);
            } else {
                tasks.get(6).add(task);
            }
        }
    }

    private Date computeNDayAfter(int n) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        calendar.add(Calendar.DATE, n);
        return calendar.getTime();
    }

    private ArrayList<Task> getTask() {
        ArrayList<Task> tasks = new ArrayList<>();
        tasks.add(new Task("Interrogation tables de 3", "interrogation", new GregorianCalendar(2020, Calendar.DECEMBER, 8).getTime()));
        tasks.add(new Task("Apporter argent repas", "annonce", new GregorianCalendar(2020, Calendar.DECEMBER, 9).getTime()));
        tasks.add(new Task("Lire le chapitre 2 du livre", "devoir", new GregorianCalendar(2020, Calendar.DECEMBER, 9).getTime()));
        tasks.add(new Task("Interrogation tables de 4", "interrogation", new GregorianCalendar(2020, Calendar.DECEMBER, 10).getTime()));
        tasks.add(new Task("Interrogation tables de 5", "interrogation", new GregorianCalendar(2020, Calendar.DECEMBER, 10).getTime()));
        tasks.add(new Task("Terminer les exercices sur les fractions", "devoir", new GregorianCalendar(2020, Calendar.DECEMBER, 10).getTime()));
        tasks.add(new Task("Interrogation tables de 6", "interrogation", new GregorianCalendar(2020, Calendar.DECEMBER, 10).getTime()));
        tasks.add(new Task("Interrogation tables de 7", "interrogation", new GregorianCalendar(2020, Calendar.DECEMBER, 11).getTime()));
        tasks.add(new Task("Interrogation tables de 8", "interrogation", new GregorianCalendar(2020, Calendar.DECEMBER, 12).getTime()));
        tasks.add(new Task("Interrogation tables de 9", "interrogation", new GregorianCalendar(2020, Calendar.DECEMBER, 11).getTime()));
        tasks.add(new Task("Interrogation tables de 10", "interrogation", new GregorianCalendar(2020, Calendar.DECEMBER, 14).getTime()));
        return tasks;
    }

    public ArrayList<Date> getDates() {
        return dates;
    }

    public ArrayList<ArrayList<Task>> getTasks() {
        return tasks;
    }
}
