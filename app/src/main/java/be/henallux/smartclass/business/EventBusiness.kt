package be.henallux.smartclass.business

import be.henallux.smartclass.model.Event
import java.util.*
import kotlin.collections.ArrayList

class EventBusiness {
    val eventThisWeek: ArrayList<Event> = ArrayList()
    val eventThisMonth: ArrayList<Event> = ArrayList()
    val eventComing: ArrayList<Event> = ArrayList()
    private val events: ArrayList<Event>
        get() {
            val events = ArrayList<Event>()
            events.add(Event("Photo de classe", GregorianCalendar(2020, Calendar.DECEMBER, 15).time, "Le Lorem Ipsum est simplement du faux texte employé dans la composition et la mise en page avant impression. Le Lorem Ipsum est le faux texte standard de l'imprimerie depuis les années 1500, quand un imprimeur anonyme assembla ensemble des morceaux de texte pour réaliser un livre spécimen de polices de texte. Il n'a pas fait que survivre cinq siècles, mais s'est aussi adapté à la bureautique informatique, sans que son contenu n'en soit modifié. Il a été popularisé dans les années 1960 grâce à la vente de feuilles Letraset contenant des passages du Lorem Ipsum, et, plus récemment, par son inclusion dans des applications de mise en page de texte, comme Aldus PageMaker."))
            events.add(Event("Fin du premier quadrimestre", GregorianCalendar(2020, Calendar.DECEMBER, 18).time, "Contrairement à une opinion répandue, le Lorem Ipsum n'est pas simplement du texte aléatoire. Il trouve ses racines dans une oeuvre de la littérature latine classique datant de 45 av. J.-C., le rendant vieux de 2000 ans. Un professeur du Hampden-Sydney College, en Virginie, s'est intéressé à un des mots latins les plus obscurs, consectetur, extrait d'un passage du Lorem Ipsum, et en étudiant tous les usages de ce mot dans la littérature classique, découvrit la source incontestable du Lorem Ipsum. Il provient en fait des sections 1.10.32 et 1.10.33 du \"De Finibus Bonorum et Malorum\" (Des Suprêmes Biens et des Suprêmes Maux) de Cicéron. Cet ouvrage, très populaire pendant la Renaissance, est un traité sur la théorie de l'éthique. Les premières lignes du Lorem Ipsum, \"Lorem ipsum dolor sit amet...\", proviennent de la section 1.10.32."))
            events.add(Event("Réunion Covid", GregorianCalendar(2020, Calendar.DECEMBER, 11).time, "L'extrait standard de Lorem Ipsum utilisé depuis le XVIè siècle est reproduit ci-dessous pour les curieux. Les sections 1.10.32 et 1.10.33 du \"De Finibus Bonorum et Malorum\" de Cicéron sont aussi reproduites dans leur version originale, accompagnée de la traduction anglaise de H. Rackham (1914)."))
            events.add(Event("Fancy-Fair", GregorianCalendar(2021, Calendar.APRIL, 2).time))
            return events
        }

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