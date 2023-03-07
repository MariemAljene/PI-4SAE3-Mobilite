package tn.esprit.backend.Services;

import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.model.*;

import java.util.Calendar;

public class MeetLinkGenerator {
        public static String generateMeetLink() {
            try {
                Calendar service = GoogleCalendarService.getCalendarService();
                Event event = new Event()
                        .setSummary("Meeting")
                        .setDescription("This is a test meeting");
                DateTime startDateTime = new DateTime("2023-03-07T09:00:00-07:00");
                EventDateTime start = new EventDateTime()
                        .setDateTime(startDateTime)
                        .setTimeZone("America/Los_Angeles");
                event.setStart(start);
                DateTime endDateTime = new DateTime("2023-03-07T10:00:00-07:00");
                EventDateTime end = new EventDateTime()
                        .setDateTime(endDateTime)
                        .setTimeZone("America/Los_Angeles");
                event.setEnd(end);
                ConferenceSolutionKey conferenceSolutionKey = new ConferenceSolutionKey();
                conferenceSolutionKey.setType("hangoutsMeet");
                CreateConferenceRequest createConferenceRequest = new CreateConferenceRequest();
                createConferenceRequest.setConferenceSolutionKey(conferenceSolutionKey);
                ConferenceData conferenceData =

}
