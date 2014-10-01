package be.vdab.web;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import be.vdab.entities.Bedrag;
import be.vdab.entities.ToeTevoegenBedrag;

@Controller
@RequestMapping("/")
public class GoedeDoelControler{
	private Bedrag bedrag = new Bedrag();


	@RequestMapping(method = RequestMethod.GET)
	public String toPage(){
		return "index";
	}
	
	@MessageMapping("/stort")
	@SendTo("/topic/amount")
	public long getBedrag(ToeTevoegenBedrag tevoegenBedrag){
		bedrag.addAmount(tevoegenBedrag.getBedrag());
		return bedrag.getAmount();
	}
	
	@SubscribeMapping("/load")
	public long getBedragEersteLoad(){
		return bedrag.getAmount();
	}
}
