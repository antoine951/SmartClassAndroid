package be.henallux.smartclass.services

import be.henallux.smartclass.model.Event
import java.util.*
import kotlin.collections.ArrayList

class EventBusiness(events: ArrayList<Event>) {
    val eventThisWeek: ArrayList<Event> = ArrayList()
    val eventThisMonth: ArrayList<Event> = ArrayList()
    val eventComing: ArrayList<Event> = ArrayList()

    private fun isBetweenTodayAndDate(dateToCheck: Date, dateEnd: Date): Boolean {
        val today = formatted(Date())
        return today.compareTo(dateToCheck) * dateToCheck.compareTo(dateEnd) >= 0
    }

    private fun computeEndDate(thisWeek: Boolean): Date {
        val today = formatted(Date())
        val calendar = Calendar.getInstance()
        calendar.time = today
        if (thisWeek) {
            calendar.add(Calendar.DATE, 8 - calendar[Calendar.DAY_OF_WEEK])
        } else {
            calendar.add(Calendar.DATE, calendar.getActualMaximum(Calendar.DAY_OF_MONTH) - calendar[Calendar.DAY_OF_MONTH])
        }
        return formatted(calendar.time)
    }

    // Affiche jusque la fin de la semaine donc dimache sauf si on est dimanche montre déjà la semaine pro, ou dernier jour du mois
    private fun sortDate(events: ArrayList<Event>) {
        for (event in events) {
            val eventDate = event.eventDate
            if (eventDate >= formatted(Date())) {
                when {
                    isBetweenTodayAndDate(eventDate, computeEndDate(true)) -> {
                        eventThisWeek.add(event)
                    }
                    isBetweenTodayAndDate(eventDate, computeEndDate(false)) -> {
                        eventThisMonth.add(event)
                    }
                    else -> {
                        eventComing.add(event)
                    }
                }
            }
        }
    }

    private fun formatted(date: Date): Date {
        val calendar = Calendar.getInstance()
        calendar.time = date
        calendar[Calendar.HOUR_OF_DAY] = 0
        calendar[Calendar.MINUTE] = 0
        calendar[Calendar.SECOND] = 0
        calendar[Calendar.MILLISECOND] = 0
        return calendar.time
    }

    init {
        sortDate(events)
    }

}