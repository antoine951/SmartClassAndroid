package be.henallux.smartclass.services

import be.henallux.smartclass.model.Task
import java.util.*
import kotlin.collections.ArrayList


class TaskBusiness (tasksInput: ArrayList<Task>){

    val dates: ArrayList<Date> = ArrayList()
    val tasks: ArrayList<ArrayList<Task>> = ArrayList()


    init {
        for (day in 0..6) {
            dates.add(computeNDayAfter(day))
            tasks.add(ArrayList())
        }
        sortDate(tasksInput)
    }

    private fun sortDate(tasksInput: ArrayList<Task>) {
        for (task in tasksInput) {
            when (task.date) {
                dates[0] -> tasks[0].add(task)
                dates[1] -> tasks[1].add(task)
                dates[2] -> tasks[2].add(task)
                dates[3] -> tasks[3].add(task)
                dates[4] -> tasks[4].add(task)
                dates[5] -> tasks[6].add(task)
                else -> tasks[6].add(task)
            }
        }
    }

    private fun computeNDayAfter(n: Int): Date {
        val calendar = Calendar.getInstance()
        calendar[Calendar.HOUR_OF_DAY] = 0
        calendar[Calendar.MINUTE] = 0
        calendar[Calendar.SECOND] = 0
        calendar[Calendar.MILLISECOND] = 0
        calendar.add(Calendar.DATE, n)
        return calendar.time
    }
}

