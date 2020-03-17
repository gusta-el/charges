package br.com.charges.app.setup;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.emailSender.app.EmailSenderManager;
import com.emailSender.app.model.EmailContent;
import com.emailSender.app.model.EmailProperties;
import com.emailSender.app.model.EmailPropertiesConstants;

import br.com.charges.app.model.Debtorable;
import br.com.charges.app.service.ChargesService;
	
@Configuration
@EnableScheduling
public class ChargeSchedule {

	@Autowired
	ChargesService chargesService;
	
	@Value("${email.adress}")
	String emailAdress;
	
	@Value("${email.password}")
	String emailPassword;
		
    @Scheduled(cron = "0 0 9 1 * ?")
    public void charge() throws MessagingException{
    	
    	Stream<Debtorable> debtors = chargesService.execute();
    	
    	
    	List<String> toAddressesTo = debtors.map(Debtorable::getEmail).collect(Collectors.toList());
    	
     	EmailContent emailContent = new EmailContent(toAddressesTo, null, null, null, "BITCH BETTER HAVE MY MONEY!", "Me paga logo calotera!");
     	EmailProperties emailProperties = new EmailProperties(EmailPropertiesConstants.LIVE.getHost(), EmailPropertiesConstants.LIVE.getPort(), emailAdress, emailPassword);
    	
    	EmailSenderManager emailSenderManager = new EmailSenderManager();
    	emailSenderManager.send(emailProperties, emailContent);
    	    	
        System.out.println("Enviei...");
    }
		
}
