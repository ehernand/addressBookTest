package com.code.challenge.controller;

import com.code.challenge.domain.Event;
import com.code.challenge.exception.ContactNotFoundException;
import com.code.challenge.exception.EventNotFoundException;
import com.code.challenge.service.EventService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import util.HeaderUtil;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.code.challenge.domain.Event}.
 */
@RestController
public class EventController {

    private final Logger log = LoggerFactory.getLogger(EventController.class);

    private static final String ENTITY_NAME = "event";

    @Value("${app.name}")
    private String applicationName;

    public static final String REST_URI_PREFIX = "/v1/events";

    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    /**
     * {@code POST /events} : Create a new event.
     *
     * @param event the event to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new event,
     * or with status {@code 400 (Bad Request)} if the event has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping(REST_URI_PREFIX)
    public ResponseEntity<Event> createEvent(@Valid @RequestBody Event event) throws URISyntaxException {
        log.debug("REST request to save Event : {}", event);
        if (event.getId() != null) {
            throw new ContactNotFoundException("A new event cannot already have an ID");
        }
        Event result = eventService.save(event);
        return ResponseEntity.created(new URI(REST_URI_PREFIX + result.getId()))
                .headers(HeaderUtil.createEntityCreationAlert(applicationName, true,
                        ENTITY_NAME, result.getId().toString()))
                .body(result);
    }

    /**
     * {@code PUT /events} : Updates an existing event.
     *
     * @param event the event to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated event,
     * or with status {@code 400 (Bad Request)} if the event is not valid,
     * or with status {@code 500 (Internal Server Error)} if the event couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping(REST_URI_PREFIX)
    public ResponseEntity<Event> update(@Valid @RequestBody Event event) throws URISyntaxException {
        log.debug("REST request to update Event : {}", event);
        if (event.getId() == null) {
            throw new EventNotFoundException("Invalid id");
        }
        Event result = eventService.save(event);
        return ResponseEntity.ok()
                .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true,
                        ENTITY_NAME, event.getId().toString()))
                .body(result);
    }

    /**
     * {@code GET /events} : get all the events.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of events in body.
     */
    @GetMapping(REST_URI_PREFIX)
    public ResponseEntity<List<Event>> getAll(Pageable pageable) {
        log.debug("REST request to get a page of Events");
        Page<Event> page = eventService.findAll(pageable);
        //HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        //return ResponseEntity.ok().headers(headers).body(page.getContent());
        return ResponseEntity.ok().body(page.getContent());
    }

    /**
     * {@code GET /events/:id} : get the "id" event.
     *
     * @param id the id of the event to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the event, or with status {@code 404 (Not Found)}.
     */
    @GetMapping(REST_URI_PREFIX + "/{id}")
    public ResponseEntity<Event> get(@PathVariable Long id) {
        log.debug("REST request to get Event: {}", id);
        Optional<Event> result = eventService.findOne(id);
        if (result.isPresent()) {
            Optional<Event> event = eventService.findOne(id);
            return ResponseEntity.ok().body(event.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * {@code DELETE /events/:id} : delete the "id" event.
     *
     * @param id the id of the event to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping(REST_URI_PREFIX + "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        log.debug("REST request to delete Event: {}", id);
        Optional<Event> result = eventService.findOne(id);
        if (result.isPresent()) {
            eventService.delete(id);
            return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName,
                    true, ENTITY_NAME, id.toString())).build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
