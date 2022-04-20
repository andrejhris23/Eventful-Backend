package app.eventful.web.controller;


import app.eventful.model.Event;
import app.eventful.service.EventService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/event")
public class EventController {

    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping("/findAll")
    public List<Event> findAll() { return eventService.findAll(); }

    @GetMapping("/findById/{id}")
    public Event findById(@PathVariable("id") Long id) {
        return eventService.findById(id);
    }

    @PostMapping("/create")
    public Event createNewEvent(
            @Valid
            @RequestParam String name,
            @RequestParam String description,
            @RequestParam String image,
            @RequestParam int price,
            @RequestParam int capacity,
            @RequestParam Long userId) {

        return eventService.createEvent(name, description, image, price, capacity, userId);
    }

    @PostMapping("/join")
    public String joinEvent(@Valid @RequestParam Long eventId, @RequestParam Long userId ) {
        return eventService.joinEvent(eventId, userId);
    }

    @PatchMapping("/edit")
    public Event editEvent(@Valid @RequestBody Event editedEvent) {
        return eventService.editEvent(editedEvent);
    }

    @DeleteMapping("/deleteById/{id}")
    public void deleteById(@PathVariable("id") Long id) { eventService.deleteById(id); }
}
