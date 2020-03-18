package br.com.charges.app.setup;

import java.util.Calendar;
import java.util.stream.Stream;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import br.com.charges.app.model.Debtorable;
import br.com.charges.app.model.Debtorable.Debtable;
import br.com.charges.app.model.EmailContent;
import br.com.charges.app.model.EmailProperties;
import br.com.charges.app.model.EmailPropertiesConstants;
import br.com.charges.app.service.ChargesService;
import br.com.charges.app.utils.ChargesUtils;

@Configuration
@EnableScheduling
public class ChargeSchedule {

	@Autowired
	ChargesService chargesService;

	@Value("${email.adress}")
	String emailAdress;

	@Value("${email.password}")
	String emailPassword;

	@Scheduled(cron = "0,30 0-59 * * * ?")
	public void charge() {

		EmailProperties emailProperties = new EmailProperties(EmailPropertiesConstants.LIVE.getHost(),
				EmailPropertiesConstants.LIVE.getPort(), emailAdress, emailPassword);
		EmailSenderManager emailSenderManager = new EmailSenderManager();

		Stream<Debtorable> debtors = chargesService.execute();
		debtors.forEach(debtor -> {
			if(!debtor.getDebts().isEmpty()) {	
				sendMessage(debtor, emailProperties, emailSenderManager);
				System.out.println("Enviado para " + debtor.getEmail());
			}
		});

	}
	
	private void sendMessage(Debtorable debtor, EmailProperties emailProperties, EmailSenderManager emailSenderManager) {
		
		String toAddresTo = debtor.getEmail();
		String nickName = debtor.getDebtorNick();

		final StringBuilder message = new StringBuilder();
		message.append(nickName + ChargesUtils.BODY_MESSAGE);

		debtor.getDebts().forEach(debt -> message.append(this.extractDebt(debt) + "\n"));

		EmailContent emailContent = new EmailContent(null, null, null, toAddresTo, ChargesUtils.SUJECT_MESSAGE,
				message.toString());

			try {
				emailSenderManager.send(emailProperties, emailContent);
			} catch (MessagingException e) {
				e.printStackTrace();
			}
	}

	private String extractDebt(Debtable debt) {
    	
    	StringBuilder debtMessage = new StringBuilder();
    	
    	String moneyValue = "R$ " + debt.getMoneyValue().toString();
    	moneyValue = moneyValue.replace('.', ',');
    
    	Calendar dateDebt = Calendar.getInstance();
    	dateDebt.setTime(debt.getDateDebt());
		String dateValue = verifyLessthanTenthDay(dateDebt.get(Calendar.DAY_OF_MONTH))
				+ "/" + verifyLessthanTenthDay(dateDebt.get(Calendar.MONTH)+1) 
				+ "/" + dateDebt.get(Calendar.YEAR) + "\n";
    	
		debtMessage.append(moneyValue);
		debtMessage.append(" referente a ");
		debtMessage.append(debt.getDescription());
		debtMessage.append(" do dia ");
		debtMessage.append(dateValue);
		
    	return debtMessage.toString();
    	
    }

	private String verifyLessthanTenthDay(int day) {

		if (day >= 10) {
			return "" + day;
		} else {
			return "0" + day;
		}

	}

}
