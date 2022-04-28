package app.eventful.service.impl;

import app.eventful.model.Event;
import app.eventful.model.User;
import app.eventful.model.exceptions.InvalidEventIdException;
import app.eventful.model.exceptions.InvalidUserIdException;
import app.eventful.repository.EventRepo;
import app.eventful.repository.UserRepo;
import app.eventful.service.EventService;
import app.eventful.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

// TODO: add multiple filters when searching for events (sort by date, name, capacity etc...)

@Service
public class EventServiceImpl implements EventService {

    private final EventRepo eventRepo;
    UserService userService;

    public EventServiceImpl(EventRepo eventRepo, UserService userService) {
        this.eventRepo = eventRepo;
        this.userService = userService;
    }

    @Override
    public List<Event> findAll() {
        return eventRepo.findAll();
    }

    @Override
    public Event findById(Long id) {
        return eventRepo.findById(id).orElseThrow(() -> new InvalidEventIdException(id));
    }

    @Override
    public void deleteById(Long id) {
        eventRepo.deleteById(id);
    }

    @Override
    public Event createEvent(String name, String description, String image, float price, int capacity, Long userId) {
        // TODO: we can fetch the logged in user from spring security, change for later

        User host = userService.findById(userId);
        Event newEvent = new Event(name, description, image, price, capacity, host);

        return eventRepo.save(newEvent);
    }

    @Override
    public String joinEvent(Long eventId, Long userId) {
        User attendee = userService.findById(userId);
        Event event = findById(eventId);


        if(event.getCapacity() > 0) {
            List<User> attendees = event.getAttendees();
            attendees.add(attendee);
            event.setAttendees(attendees);
            List<Event> newEvents = attendee.getEnrolledEvents();
            newEvents.add(event);
            attendee.setEnrolledEvents(newEvents);
            // * TODO: maybe add a fixed capacity and another variable like ticketsLeft and decrease that one
            event.setCapacity(event.getCapacity() - 1);
            eventRepo.save(event);

            return "You have successfully purchased a ticket for this event!";
        } else return "No more tickets left! Sorry";
    }

    @Override
    public Event editEvent(Event editedEvent) {
        Event event = findById(editedEvent.getId());

        event.setName(editedEvent.getName());
        event.setDescription(editedEvent.getDescription());
        event.setImage(editedEvent.getImage());
        event.setPrice(editedEvent.getPrice());
        event.setCapacity(editedEvent.getCapacity());

        return eventRepo.save(event);
    }
}
