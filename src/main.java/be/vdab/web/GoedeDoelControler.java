package be.vdab.web;

import java.util.concurrent.atomic.AtomicBoolean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.messaging.core.MessageSendingOperations;
import org.springframework.messaging.simp.broker.BrokerAvailabilityEvent;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import be.vdab.entities.Bedrag;

@Controller
@RequestMapping("/")
public class GoedeDoelControler implements ApplicationListener<BrokerAvailabilityEvent>{
	private final MessageSendingOperations<String> messagingTemplate;
	private AtomicBoolean brokerAvailable = new AtomicBoolean();
	private Bedrag bedrag;
	
	@Autowired
	public GoedeDoelControler(MessageSendingOperations<String> messagingTemplate, Bedrag bedrag) {
		this.messagingTemplate = messagingTemplate;
		this.bedrag = bedrag;
	}

	@Override
	public void onApplicationEvent(BrokerAvailabilityEvent event) {
		this.brokerAvailable.set(event.isBrokerAvailable());
	}
	
	@Scheduled(fixedDelay=2000)
	public void sendCurrentAmount(){
		long amount = 0;
		messagingTemplate.convertAndSend("/topic/amount", amount);
	}

	@RequestMapping()
	public String getBedrag(){
		return null;
	}
}
