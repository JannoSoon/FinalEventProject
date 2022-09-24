package com.example.eventwebapplication.controllers;

import com.example.eventwebapplication.exceptions.EventNotFoundException;
import com.example.eventwebapplication.models.Event;
import com.example.eventwebapplication.models.EventComment;
import com.example.eventwebapplication.services.EventCommentService;
import com.example.eventwebapplication.services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.UUID;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@Controller
@RequestMapping("/event")
public class EventController {
    @Autowired
    private EventService eventService;

    @Autowired
    private EventCommentService eventCommentService;
    @GetMapping
    public String showEventListPage(Model model, @ModelAttribute("message") String message,
                                     @ModelAttribute("messageType") String messageType) {
        model.addAttribute("events", eventService.getAllEvents());
        return "event/list-event";
    }

    @GetMapping("/{id}")
    public String showEventViewPage(@PathVariable UUID id, Model model, RedirectAttributes redirectAttributes) {
        try {
            model.addAttribute("event", eventService.findEventById(id));
            return "event/view-event";
        } catch (EventNotFoundException e) {
            return handleEventNotFoundExceptionById(id, redirectAttributes);
        }
    }

    @GetMapping("/event")
    public String showCreateEventPage(@ModelAttribute("event") Event event,
                                       @ModelAttribute("message") String message,
                                       @ModelAttribute("messageType") String messageType) {
        return "event/create-event";
    }

    @PostMapping
    public String createEvent(Event event, RedirectAttributes redirectAttributes) {
        try {
            Event searchEvent = eventService.findEventByName(event.getName()).get();
            redirectAttributes.addFlashAttribute("message",
                    String.format("Event(%s) already exists!", searchEvent.getName()));
            redirectAttributes.addFlashAttribute("messageType", "error");
            return "redirect:/event/list-event";
        } catch (Exception e) {
            eventService.createEvent(event);
            redirectAttributes.addFlashAttribute("message",
                    String.format("Event(%s) created successfully!", event.getName()));
            redirectAttributes.addFlashAttribute("messageType", "success");
            return "redirect:/event";
        }
    }

    @GetMapping("/update/{id}")
    public String showUpdateEventPage(@PathVariable UUID id, Model model, RedirectAttributes redirectAttributes,
                                       @RequestParam(value = "event", required = false) Event event) {
        if (event == null) {
            try {
                model.addAttribute("event", eventService.findEventById(id));
            } catch (EventNotFoundException e) {
                return handleEventNotFoundExceptionById(id, redirectAttributes);
            }
        }

        return "event/update-event";
    }

    @PostMapping("/update")
    public String updateEvent(Event event, RedirectAttributes redirectAttributes) {
        try {
            eventService.updateEvent(event);
            redirectAttributes.addFlashAttribute("message",
                    String.format("Event(id=%s) updated successfully!", event.getId()));
            redirectAttributes.addFlashAttribute("messageType", "success");
            return "redirect:/event";
        } catch (EventNotFoundException e) {
            return handleEventNotFoundExceptionById(event.getId(), redirectAttributes);
        }
    }

    @GetMapping("/delete/{id}")
    public String deleteEvent(@PathVariable UUID id, RedirectAttributes redirectAttributes) {
        try {
            eventService.deleteEventById(id);
            redirectAttributes.addFlashAttribute("message",
                    String.format("Event(id=%s) deleted successfully!", id));
            redirectAttributes.addFlashAttribute("messageType", "success");
            return "redirect:/event";

        } catch (Exception e) {
            return handleEventNotFoundExceptionById(id, redirectAttributes);
        }
    }

    @GetMapping("/restore/{id}")
    public String restoreEvent(@PathVariable UUID id, RedirectAttributes redirectAttributes) {
        try {
            eventService.restoreEventById(id);
            redirectAttributes.addFlashAttribute("message",
                    String.format("Event(id=%s) restored successfully!", id));
            redirectAttributes.addFlashAttribute("messageType", "success");
            return "redirect:/event";

        } catch (Exception e) {
            return handleEventNotFoundExceptionById(id, redirectAttributes);
        }
    }
    @GetMapping("/comments/{id}")
    public String getAllEventCommentBy(@PathVariable UUID id, Model model) {
        List<EventComment> comments = eventCommentService.getAllCommentsBy(id);
        model.addAttribute("comments", comments);
        return "event/comments";
    }

    private String handleEventNotFoundExceptionById(UUID id, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("message",
                String.format("Event(id=%s) not found!", id));
        redirectAttributes.addFlashAttribute("messageType", "error");
        return "redirect:/event";
    }
}

