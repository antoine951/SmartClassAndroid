package be.henallux.smartclass.business;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import be.henallux.smartclass.model.Event;

public class EventBusiness {

    private ArrayList<Event> eventsWeek;
    private ArrayList<Event> eventsMonth;
    private ArrayList<Event> eventsComing;


    public EventBusiness() {
        this.eventsWeek = new ArrayList<>();
        this.eventsMonth = new ArrayList<>();
        this.eventsComing = new ArrayList<>();
        sortDate(getEvents());
    }

    public ArrayList<Event> getEvents() {
        ArrayList<Event> events = new ArrayList<>();
        events.add(new Event("Photo de classe",new GregorianCalendar(2020, Calendar.DECEMBER,11).getTime(),"Le Lorem Ipsum est simplement du faux texte employé dans la composition et la mise en page avant impression. Le Lorem Ipsum est le faux texte standard de l'imprimerie depuis les années 1500, quand un imprimeur anonyme assembla ensemble des morceaux de texte pour réaliser un livre spécimen de polices de texte. Il n'a pas fait que survivre cinq siècles, mais s'est aussi adapté à la bureautique informatique, sans que son contenu n'en soit modifié. Il a été popularisé dans les années 1960 grâce à la vente de feuilles Letraset contenant des passages du Lorem Ipsum, et, plus récemment, par son inclusion dans des applications de mise en page de texte, comme Aldus PageMaker."));
        events.add(new Event("Fin du premier quadrimestre",new GregorianCalendar(2020,Calendar.DECEMBER,18).getTime(),"Contrairement à une opinion répandue, le Lorem Ipsum n'est pas simplement du texte aléatoire. Il trouve ses racines dans une oeuvre de la littérature latine classique datant de 45 av. J.-C., le rendant vieux de 2000 ans. Un professeur du Hampden-Sydney College, en Virginie, s'est intéressé à un des mots latins les plus obscurs, consectetur, extrait d'un passage du Lorem Ipsum, et en étudiant tous les usages de ce mot dans la littérature classique, découvrit la source incontestable du Lorem Ipsum. Il provient en fait des sections 1.10.32 et 1.10.33 du \"De Finibus Bonorum et Malorum\" (Des Suprêmes Biens et des Suprêmes Maux) de Cicéron. Cet ouvrage, très populaire pendant la Renaissance, est un traité sur la théorie de l'éthique. Les premières lignes du Lorem Ipsum, \"Lorem ipsum dolor sit amet...\", proviennent de la section 1.10.32."));
        events.add(new Event("Réunion Covid",new GregorianCalendar(2020,Calendar.DECEMBER,11).getTime(),"L'extrait standard de Lorem Ipsum utilisé depuis le XVIè siècle est reproduit ci-dessous pour les curieux. Les sections 1.10.32 et 1.10.33 du \"De Finibus Bonorum et Malorum\" de Cicéron sont aussi reproduites dans leur version originale, accompagnée de la traduction anglaise de H. Rackham (1914)."));
        events.add(new Event("Fancy-Fair",new GregorianCalendar(2021,Calendar.APRIL,2).getTime()));
        return events;
    }

    public ArrayList<Event> getEventThisWeek() {
        return eventsWeek;
    }

    public ArrayList<Event> getEventThisMonth() {
        return eventsMonth;
    }

    public ArrayList<Event> getEventComing() {
        return eventsComing;
    }

    private boolean isBetweenTodayAndDate(Date dateToCheck, Date dateEnd) {
        Date today = formatted(new Date());
        return today.compareTo(dateToCheck) * dateToCheck.compareTo(dateEnd) >= 0;
    }

    private Date computeEndDate(boolean thisWeek) {
        Date today = formatted(new Date());
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(today);
        if (thisWeek) {
            calendar.add(Calendar.DATE, 8 - calendar.get(Calendar.DAY_OF_WEEK));
        } else {
            calendar.add(Calendar.DATE, calendar.getActualMaximum(Calendar.DAY_OF_MONTH) - calendar.get(Calendar.DAY_OF_MONTH));
        }
        return formatted(calendar.getTime());
    }

    // Affiche jusque la fin de la semaine donc dimache sauf si on est dimanche montre déjà la semaine pro, ou dernier jour du mois
    private void sortDate(ArrayList<Event> events) {
        for (Event event : events) {
            if (isBetweenTodayAndDate(event.getEventDate(), computeEndDate(true))) {
                eventsWeek.add(event);
            } else if (isBetweenTodayAndDate(event.getEventDate(), computeEndDate(false))) {
                eventsMonth.add(event);
            } else {
                eventsComing.add(event);
            }
        }
    }

    private Date formatted(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }



}
