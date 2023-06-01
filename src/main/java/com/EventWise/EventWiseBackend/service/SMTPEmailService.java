package com.EventWise.EventWiseBackend.service;

import com.EventWise.EventWiseBackend.DTO.EventParticipantDto;
import com.EventWise.EventWiseBackend.entities.Event;
import com.EventWise.EventWiseBackend.entities.Participation;
import com.EventWise.EventWiseBackend.entities.User;
import com.EventWise.EventWiseBackend.exceptions.EventNotFoundException;
import com.EventWise.EventWiseBackend.repository.EmailService;
import com.EventWise.EventWiseBackend.repository.EventRepository;
import com.EventWise.EventWiseBackend.repository.ParticipationRepository;
import com.EventWise.EventWiseBackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.mail.Session;
import java.time.LocalDateTime;
import java.util.Properties;

@Service
public class SMTPEmailService implements EmailService {

    private final Environment environment;
    private final JavaMailSender mailSender;
    private final UserRepository userRepository;
    private final EventRepository eventRepository;
    private final ParticipationRepository participationRepository;


    @Value("${spring.mail.username}")
    private String fromEmail;

    @Autowired
    public SMTPEmailService(Environment environment, JavaMailSender mailSender,
                            UserRepository userRepository, EventRepository eventRepository, ParticipationRepository participationRepository) {
        this.environment = environment;
        this.mailSender = mailSender;
        this.userRepository = userRepository;
        this.eventRepository = eventRepository;
        this.participationRepository = participationRepository;
    }

    @Override
    public void sendEmail(EventParticipantDto eventParticipantDto, Long eventId) {
        //check if email exist
        //add email and details if not exist
        //send email
        User user = userRepository.findByEmail(eventParticipantDto.getEmail());

        if (user == null) {
            user = new User();
            user.setEmail(eventParticipantDto.getEmail());
            user.setFirstName(eventParticipantDto.getFirstName());
            user.setLastName(eventParticipantDto.getLastName());
            userRepository.save(user);
        }

        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new EventNotFoundException("Event not found"));

        Participation participant = new Participation();
        participant.setEvent(event);
        participant.setRegisteredAt(LocalDateTime.now());
        participant.setUser(user);
        participant.setOrganiserHasApproved(true);
        participant.setParticipantHasApproved(false);
        participationRepository.save(participant);



        Properties properties = new Properties();
        properties.put("mail.smtp.host", environment.getProperty("spring.mail.host"));
        properties.put("mail.smtp.port", environment.getProperty("spring.mail.port"));
        properties.put("mail.smtp.auth", environment.getProperty("spring.mail.properties.mail.smtp.auth"));
        properties.put("mail.smtp.starttls.enable", environment.getProperty("spring.mail.properties.mail.smtp.starttls.enable"));

        Session session = Session.getInstance(properties);
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(fromEmail);
        message.setTo(eventParticipantDto.getEmail());
        message.setText(eventParticipantDto.getEmailBody());
        message.setSubject(eventParticipantDto.getEmailSubject());
        mailSender.send(message);

       /* if (eventParticipantDto.getEmailBody() == null) {
            eventParticipantDto.setEmailBody("Please join this event"); // Set a default value if the email body is null
        }


        if (eventParticipantDto.getEmailSubject() == null) {
            eventParticipantDto.setEmailSubject("party"); // Set a default value if the email subject is null
        }*/

       /* MimeMessage message = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(fromEmail);
            helper.setTo(eventParticipantDto.getEmail());
            helper.setText(eventParticipantDto.getEmailBody(), true);
            helper.setSubject(eventParticipantDto.getEmailSubject());

            mailSender.send(message);
        } catch ( MessagingException e) {
            // Handle the exception
        }*/
    }
}
