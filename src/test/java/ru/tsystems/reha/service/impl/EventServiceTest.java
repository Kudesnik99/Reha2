package ru.tsystems.reha.service.impl;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import ru.tsystems.reha.converters.EventConverter;
import ru.tsystems.reha.dao.api.EventDao;
import ru.tsystems.reha.dao.api.TreatmentDao;
import ru.tsystems.reha.dto.EventDto;
import ru.tsystems.reha.entity.Event;
import ru.tsystems.reha.entity.Treatment;
import ru.tsystems.reha.entity.enums.EventStatus;
import ru.tsystems.reha.entity.enums.TreatmentStatus;
import ru.tsystems.reha.jms.JmsProducer;
import ru.tsystems.reha.service.api.EventService;
import ru.tsystems.reha.service.api.TreatmentService;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.powermock.api.mockito.PowerMockito.when;

@RunWith(PowerMockRunner.class)
@PrepareForTest({EventDao.class, JmsProducer.class, TreatmentDao.class, TreatmentService.class, EventConverter.class})
public class EventServiceTest {

    private static final Long EVENT_ID = 1L;
    private final String UPDATE_MSG = "Update_event";

    private EventDao mockEventDao;
    private JmsProducer mockJmsProducer;
    private TreatmentDao mockTreatmentDao;
    private TreatmentService mockTreatmentService;
    private EventService eventService;

    @Before
    public void setUp() {
        PowerMockito.mockStatic(EventConverter.class);
        mockEventDao = PowerMockito.mock(EventDao.class);
        mockJmsProducer = PowerMockito.mock(JmsProducer.class);
        mockTreatmentDao = PowerMockito.mock(TreatmentDao.class);
        mockTreatmentService = PowerMockito.mock(TreatmentService.class);
        eventService = new EventServiceImpl(mockEventDao, mockTreatmentDao, mockTreatmentService, mockJmsProducer);
    }

    @Test
    public void saveEventSameStatusTest() throws Exception {
        EventDto eventDto = createEventDto(EVENT_ID, EventStatus.PLANNED);
        Event event = createEvent(EVENT_ID, EventStatus.PLANNED);
        // return instance method
        when(mockEventDao.findById(eventDto.getEventId())).thenReturn(event);
        when(mockEventDao.saveOrUpdate(event)).thenReturn(event);
        // static
        PowerMockito.doNothing().when(EventConverter.class, "updateEvent", event, eventDto);
        // void
        doNothing().when(mockJmsProducer).send(UPDATE_MSG);

        eventService.saveEvent(eventDto);
        assertEquals(TreatmentStatus.PLANNED, event.getTreatment().getStatus());

    }

    private EventDto createEventDto(Long id, EventStatus status) {
        EventDto eventDto = new EventDto();
        eventDto.setEventId(id);
        eventDto.setStatus(status);
        return eventDto;
    }

    private Event createEvent(Long id, EventStatus status) {
        Treatment treatment = new Treatment();
        treatment.setStatus(TreatmentStatus.PLANNED);
        Event event = new Event();
        event.setEventId(id);
        event.setStatus(status);
        event.setTreatment(treatment);
        return event;
    }
}
