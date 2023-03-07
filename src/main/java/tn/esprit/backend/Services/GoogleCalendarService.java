package tn.esprit.backend.Services;

import com.fasterxml.jackson.core.JsonFactory;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.calendar.CalendarScopes;
import org.mortbay.jetty.security.Credential;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.GeneralSecurityException;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

public class GoogleCalendarService {

        private static final String APPLICATION_NAME = "My Application";
        private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
        private static final List<String> SCOPES = Collections.singletonList(CalendarScopes.CALENDAR);
        private static final String CREDENTIALS_FILE_PATH = "/credentials.json";

        private static Credential getCredentials() throws IOException {
            InputStream in = GoogleCalendarService.class.getResourceAsStream(CREDENTIALS_FILE_PATH);
            GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));
            GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                    GoogleNetHttpTransport.newTrustedTransport(), JSON_FACTORY, clientSecrets, SCOPES)
                    .setDataStoreFactory(new FileDataStoreFactory(new java.io.File("tokens")))
                    .setAccessType("offline")
                    .build();
            LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(8888).build();
            return new AuthorizationCodeInstalledApp(flow, receiver).authorize("user");
        }

        public static Calendar getCalendarService() throws IOException, GeneralSecurityException {
            final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
            return new Calendar.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials())
                    .setApplicationName(APPLICATION_NAME)
                    .build();
        }
    }


